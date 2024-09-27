package com.example.latihandicoding2

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoveWithObjectActivity : AppCompatActivity() {

    companion object {
        const val  EXTRA_PERSON = "extra_person"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_move_with_object)

        val ObjectRec : TextView = findViewById(R.id.ObjectReceived)

        val person = if(Build.VERSION.SDK_INT >= 33){
          intent.getParcelableExtra<Person>(EXTRA_PERSON,Person::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Person>(EXTRA_PERSON)
        }

        if(person != null){
            val text = " Name : ${person.name.toString()},\n Age : ${person.age.toString()},\n Email : ${person.email.toString()},\n City : ${person.city.toString()}"
            ObjectRec.text = text
        }
    }
}