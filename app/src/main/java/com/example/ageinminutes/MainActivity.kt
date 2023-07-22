package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null
    private var tvAgeInDays : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            tvSelectedDate = findViewById(R.id.tvSelectedDate)
            tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
            tvAgeInHours = findViewById(R.id.tvAgeInHours)
            tvAgeInDays = findViewById(R.id.tvAgeInDays)
            clickDatePicker()
        }
    }

     private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            theDate?.let {
                val selectedDateInMinutes = theDate.time/60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let{
                    val currentDateInMinutes = currentDate.time/60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    val differenceInHours = differenceInMinutes/60

                    val differenceInDays = differenceInHours/24

                    tvAgeInMinutes?.text = differenceInMinutes.toString()
                    tvAgeInHours?.text = differenceInHours.toString()
                    tvAgeInDays?.text = differenceInDays.toString()
                }
            }

        },
            year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}