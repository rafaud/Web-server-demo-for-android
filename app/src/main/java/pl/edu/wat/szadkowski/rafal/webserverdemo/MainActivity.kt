package pl.edu.wat.szadkowski.rafal.webserverdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import pl.edu.wat.szadkowski.rafal.webserverdemo.model.Person
import pl.edu.wat.szadkowski.rafal.webserverdemo.model.enum.Sex
import pl.edu.wat.szadkowski.rafal.webserverdemo.network.ApiClient
import pl.edu.wat.szadkowski.rafal.webserverdemo.network.appInterface.PersonInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity: AppCompatActivity() {

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Spinner>(R.id.acticity_main_spinner_sex_selector)
            .adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Sex.values()
        )
        
        findViewById<Button>(R.id.activity_main_button_add_person)
            .setOnClickListener {
                val name = findViewById<EditText>(R.id.activity_main_edit_text_name)
                    .text.toString()
                val surname = findViewById<EditText>(R.id.activity_main_edit_text_surname)
                    .text.toString()
                val sex = findViewById<Spinner>(R.id.acticity_main_spinner_sex_selector)
                    .selectedItem as Sex
                val personInterface = ApiClient.getApiClient().create(PersonInterface::class.java)

                val person = Person(name, surname, sex)
                val call = personInterface.addPerson(person)

                call.enqueue(object : Callback<Person> {
                    override fun onResponse(call: Call<Person>, response: Response<Person>) {
                        Toast
                            .makeText(this@MainActivity,
                                "Person created, id: ${response.body()?.id}.",
                                Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onFailure(call: Call<Person>, t: Throwable) {
                        Log.e("post person", t.toString())
                    }

                })


            }

        findViewById<Button>(R.id.activity_main_button_show_all_people)
            .setOnClickListener {
                val personInterface = ApiClient.getApiClient().create(PersonInterface::class.java)

                val call = personInterface.getAllPeople()

                call.enqueue( object : Callback<List<Person>> {
                    override fun onResponse(
                        call: Call<List<Person>>,
                        response: Response<List<Person>>
                    ) {
                        Log.e("test tag", response.body()!!.joinToString("; "))
                    }

                    override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                        Log.e("get all failure", t.toString())
                    }

                })
            }
    }
}