package com.example.latihandicoding2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult : TextView

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if (result.resultCode == MainForResultActivity.RESULT_CODE && result.data != null){
            val selectedValue = result.data?.getIntExtra(MainForResultActivity.EXTRA_SELECTED_VALUE,0)
            tvResult.text = "Hasil Pilih : $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnMove : Button = findViewById(R.id.pindah_activity)
        btnMove.setOnClickListener(this)

        val btnWithData : Button = findViewById(R.id.WithData)
        btnWithData.setOnClickListener(this)

        val btnWithObject : Button = findViewById(R.id.WithObject)
        btnWithObject.setOnClickListener(this)

        val btnDial : Button = findViewById(R.id.Dial)
        btnDial.setOnClickListener(this)

        val btnForResult : Button = findViewById(R.id.ForResult)
        btnForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.pindah_activity -> {
                startActivity(Intent(this@MainActivity, MoveActivity::class.java))
            }
            R.id.WithData -> {
                val moveWithData = Intent(this@MainActivity,MoveWithData::class.java)
                moveWithData.putExtra(MoveWithData.EXTRA_NAME,"DicodingAcademy Boy")
                moveWithData.putExtra(MoveWithData.EXTRA_AGE,10)
                startActivity(moveWithData)
            }
            R.id.WithObject -> {
                val person = Person(
                    "DicodingAcademy",10,"Academy@Dicoding.com","Bandung"
                )

                val MoveWithObjectIntent = Intent(this@MainActivity,MoveWithObjectActivity::class.java)
                MoveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON,person)
                startActivity(MoveWithObjectIntent)
            }
            R.id.Dial -> {
                val phoneNumber = "0812341212"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.ForResult -> {
                val resultLaunch = Intent(this@MainActivity,MainForResultActivity::class.java)
                resultLauncher.launch(resultLaunch)
            }
        }
    }
}