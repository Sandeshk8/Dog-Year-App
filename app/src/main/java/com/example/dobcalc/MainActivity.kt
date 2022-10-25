package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvselectdate : TextView? = null
    private var diffinmin : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        tvselectdate = findViewById(R.id.tv4)
        diffinmin = findViewById(R.id.tv6)
        button.setOnClickListener {
            datepick()
        }
    }
    private fun datepick(){
        val mycal = Calendar.getInstance()
        val year = mycal.get(Calendar.YEAR)
        val month = mycal.get(Calendar.MONTH)
        val day = mycal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, year, month, day ->
            Toast.makeText(this, "$day/${month+1}/$year" , Toast.LENGTH_LONG).show()
            val selectdate = "$day/${month+1}/$year"
            tvselectdate?.text = selectdate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date = sdf.parse(selectdate)
            date?.let{
                val selecteddateinmin = date.time/60000
                val currendate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currendate?.let{
                    val currentdateinmin = currendate.time/60000
                    var difinminutes = (currentdateinmin- selecteddateinmin)/525600

                    val dage= 16*Math.log(difinminutes.toDouble())+31

                    difinminutes = dage.toLong()
                    if(difinminutes<0)difinminutes=31
                    diffinmin?.text = difinminutes.toString()
                }
            }
        },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}