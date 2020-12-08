package com.wilke.android.prime.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fasterxml.jackson.databind.ObjectMapper
import com.wilke.android.prime.api.PrimeClient
import com.wilke.android.prime.api.model.PrimeMeta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PrimeModel @Inject constructor(
        application: Application,
        private val primeClient: PrimeClient
) : AndroidViewModel(application) {

    private val objectMapper = ObjectMapper().findAndRegisterModules()

    private val sharedPreferences = application.getSharedPreferences(this.javaClass.name, Context.MODE_PRIVATE)

    val meta = MutableLiveData<PrimeMeta>(this.deserializeMeta())

    val results = MutableLiveData<String>()

    fun fetchMeta() {
        Log.d(this.javaClass.simpleName, "Fetching prime meta")

        primeClient.fetchMeta().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    serializeMeta(response.body())
                    deserializeMeta()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(this.javaClass.simpleName, t.message!!)
            }
        })
    }

    private fun deserializeMeta(): PrimeMeta? {
        val value = sharedPreferences.getString(META_KEY, null)

        return if (value == null) {
            null
        } else {
            objectMapper.readValue(value, PrimeMeta::class.java)
        }
    }

    private fun serializeMeta(value: String?) {
        if (value != null) {
            Log.d(this.javaClass.simpleName, "Persisting prime meta: $value")

            sharedPreferences.edit {
                this.putString(META_KEY, value)
            }
        }
    }

    private companion object {
        const val META_KEY = "meta"
        const val RESULT_KEY = "results"
    }

}