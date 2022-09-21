// learning from euTutorials on Youtube

package edna.ho.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

/*
MainActivity class provides the program that contains the functioning of button clicks, calendar
control, and age in minutes calculation. A user should be able to click the "Select Date" button
and choose a birthdate. After clicking "OK" in the DatePickerDialog, the user's birthdate and
age in minutes should appear on screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //when user clicks "Select Date", it initiates an onClick listener that will call the
        //clickDatePicker() function
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    /*
    param: (view) "Select Date" button
    This function uses the Calendar class to obtain the current date. It also uses the
    DatePickerDialog function to show a calendar that the user can pick a date from. With the stored
    values, it displays the selected date and then uses the SimpleDateFormat to turn into
    milliseconds the day, month, and year altogether. It displays the difference of current time
    and birthdate minutes.
     */
    fun clickDatePicker(view: View) {
        //current date
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        //DataPickerDialog will store selected day, month, and year
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "${selectedMonth+1}/$selectedDayOfMonth/$selectedYear"

            //display selected date
            val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
            tvSelectedDate.setText(selectedDate)

            //simpleDateFormat class
            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
            //theDate is of type Date. parsed means it is converted from string into format of date object
            //this will parse the selected date into milliseconds
            val theDate = sdf.parse(selectedDate)


            //get the date of birth in millisecond form
            val selectedDateToMinutes = theDate!!.time / 60000

            //get the current date in millisecond form
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
            val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

        }, year, month, day)
            dpd.datePicker.setMaxDate(Date().time - 86400000)
                dpd.show()
    }
}