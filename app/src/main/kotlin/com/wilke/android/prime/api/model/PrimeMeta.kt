package com.wilke.android.prime.api.model

import java.time.LocalDateTime

data class PrimeMeta(
    val lastUpdated: LocalDateTime,
    val imports: Imports,
    val results: Results,
    val user: User
)

data class Imports(
    val states: Map<String, Long>,
    val total: Long
)

data class Results(
    val total: Long
)

data class User(
    val name: String,
    val total: Long
)