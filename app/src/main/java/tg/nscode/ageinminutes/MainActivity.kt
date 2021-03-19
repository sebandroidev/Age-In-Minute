package tg.nscode.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.minutes

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencing UI elements
        val btnDatePicker: Button = findViewById(R.id.btnSelectDate)

        // Show datePicker
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {

        // Referencing to UI Elements
        val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
        val tvSelectedDateInMinutes: TextView = findViewById(R.id.tvSelectedDateInMinutes)

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        // Variable containing Date Picker Dialog
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

            // Show a Toast on the screen on validate button clicked on datepicker dialog
            Toast.makeText(
                this,
                "The chosen year is $selectedYear, the month is $selectedMonth and the day is $selectedDayOfMonth",
                Toast.LENGTH_LONG
            ).show()

            // Concatenate day, month and year in a String variable
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            // Set textView text property
            tvSelectedDate.text = selectedDate

            // Format date using SimpleDateFormat Class, check the documentation for more details
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            // Calculate number of minutes from January 1, 1970
            val selectedDateInMinute = theDate!!.time / 60000

            // Get number of minutes from currentDate
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            // Calculate the difference
            val currentDateToMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateToMinutes - selectedDateInMinute

            tvSelectedDateInMinutes.text = "$differenceInMinutes"

        }, year, month, day)

        // Limit the datepicker
        dpd.datePicker.maxDate = Date().time - 86400000

        // Show the DatePicker Dialog
        dpd.show()
    }
}