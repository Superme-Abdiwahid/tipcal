package edu.us.ischool.tipcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var total = 0.0;
    private var count = 0.0
    private var overall = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var text: TextView = findViewById<TextView>(R.id.text_id)

        var editText: EditText = findViewById<EditText>(R.id.editTextNumber)

        var getButton: Button = findViewById<Button>(R.id.btn_button)
        getButton.isEnabled = false;

        editText.addTextChangedListener{
            getButton.isEnabled = true;
            var input = editText.text.toString().toDouble();

            Log.v("MAIN_ACTIVITY", input.toString())
            total = input;
            count = total * 0.15;




        }
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        getButton.doOnTextChanged { text, start, before, count ->
            getButton.text = format.format(overall)
        }
        getButton.setOnClickListener{
            val message = Toast.makeText(this, "Thanks for your tip your tip was $" + "$overall", Toast.LENGTH_LONG)
            message.show();
        }




    }
}