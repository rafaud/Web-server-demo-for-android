package pl.edu.wat.szadkowski.rafal.webserverdemo.model

import pl.edu.wat.szadkowski.rafal.webserverdemo.model.enum.Sex

data class Person(
    val name: String,
    val surname: String,
    val sex: Sex,
    val id: Long? = null,
    )