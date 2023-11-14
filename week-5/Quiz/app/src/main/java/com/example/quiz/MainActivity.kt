package com.example.quiz

import android.R.id.message
import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitResult()
    }

    fun submitResult() {
        val submit = findViewById<Button>(R.id.submit)
        val reset = findViewById<Button>(R.id.reset)
        val q1Group = findViewById<RadioGroup>(R.id.q1Group)
        val q1Ans2 = findViewById<RadioButton>(R.id.q1Ans2)
        val q2Ans1 = findViewById<CheckBox>(R.id.q2Ans1)
        val q2Ans2 = findViewById<CheckBox>(R.id.q2Ans2)
        val q2Ans3 = findViewById<CheckBox>(R.id.q2Ans3)
        val q2Ans4 = findViewById<CheckBox>(R.id.q2Ans4)

        reset.setOnClickListener{
            q1Group.clearCheck();
            q2Ans1.isChecked = false;
            q2Ans2.isChecked = false;
            q2Ans3.isChecked = false;
            q2Ans4.isChecked = false;
        }

        submit.setOnClickListener{
            var result: Int = 0
            if(q1Group.checkedRadioButtonId == q1Ans2.id) {
                result = 50
            }
            if(q2Ans1.isChecked && !q2Ans2.isChecked && !q2Ans3.isChecked && q2Ans4.isChecked) {
                result += 50
            }
            val builder: AlertDialog.Builder = Builder(this)
            builder.setTitle("Quiz!")
            builder.setMessage("Congratulations!\nYou submitted on "+ getCurrentDateTime() +", you achieved " + result + "%")
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }

    private fun getCurrentDateTime(): String {
        val calendar = Calendar.getInstance()
        val currentDateTime: Date = calendar.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        return dateFormat.format(currentDateTime)
    }
}