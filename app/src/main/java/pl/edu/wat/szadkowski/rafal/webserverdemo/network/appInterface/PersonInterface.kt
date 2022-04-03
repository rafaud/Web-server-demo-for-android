package pl.edu.wat.szadkowski.rafal.webserverdemo.network.appInterface

import pl.edu.wat.szadkowski.rafal.webserverdemo.model.Person
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PersonInterface {
    @GET("/person/all")
    fun getAllPeople(): Call<List<Person>>

    @POST("/person")
    fun addPerson(@Body person: Person): Call<Person>
}