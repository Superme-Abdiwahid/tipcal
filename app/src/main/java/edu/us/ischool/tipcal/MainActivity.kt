package edu.us.ischool.tipcal

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var total = 0.0;
    private var count = 0.0
    private var overall = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getButton: Button = findViewById<Button>(R.id.btn_button)
        getButton.isEnabled = false;

        var editText: EditText = findViewById<EditText>(R.id.editTextNumber)
        try {
            editText.addTextChangedListener(object : TextWatcher {
                private var current = ""
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (s.toString() != current) {
                        editText.removeTextChangedListener(this)
                        val currency = String.format("[%s,.\\s]", "$")
                        val cleanString = s.toString().replace(currency.toRegex(), "")
                        var inputNum = 0.0;
                        if (!cleanString.equals("")) {
                            inputNum = cleanString.toDouble()
                            System.clearProperty(this.toString())
                            getButton.isEnabled = true;
                            total = inputNum;
                            count = total * 0.15;
                    //        System.arraycopy(arrayOf(this), 1, cleanString.split("/s"), 1, 1)
                           // System.arraycopy(0,0, cleanString.split("/s"), 1, currency.toString().length)
                            overall = NumberFormat.getCurrencyInstance().format(count / 100)
                        } else {
                            getButton.isEnabled = false;
                        }
                        val formatted = NumberFormat.getCurrencyInstance().format(inputNum / 100)
                        current = formatted
                        editText.setText(formatted)
                        editText.setSelection(formatted.length)
                        editText.addTextChangedListener(this)
                    }
                }
            })
            getButton.setOnClickListener {
                val message = Toast.makeText(
                    this,
                    "Thanks for your tip your tip was $overall",
                    Toast.LENGTH_LONG
                )
                message.show();
            }
        }catch(e: Exception){
            val message = Toast.makeText(
                this,
                "An erorr of" + e.stackTrace + "Has occured",
                Toast.LENGTH_LONG
            )
            message.show();
        }
    }
}