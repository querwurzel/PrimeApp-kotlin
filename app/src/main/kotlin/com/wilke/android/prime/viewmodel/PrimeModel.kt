package com.wilke.android.prime.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class PrimeModel @Inject constructor(
        application: Application
) : AndroidViewModel(application) {

    val meta = MutableLiveData<String>()

}