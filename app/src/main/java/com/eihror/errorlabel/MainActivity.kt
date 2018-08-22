package com.eihror.errorlabel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnValidate.setOnClickListener {
            elEmail.setErrorEnabled(false)
            elPass.setErrorEnabled(false)

            if (edtEmail.text.isNullOrEmpty()) {
                configureErrorMessages(elEmail, R.drawable.ic_close, "Invalid User", android.R.color.holo_red_dark, null, 16F)
                elEmail.setErrorEnabled(true)
            }
            if (edtPass.text.isNullOrEmpty()){
                configureErrorMessages(elPass, null, "Invalid Password", android.R.color.holo_red_dark, null, 16F)
                elPass.setErrorEnabled(true)
            }
        }

    }

    private fun configureErrorMessages(il: ErrorLabel, icon: Int?, value: String, color: Int, font: String?, size: Float) {
        il.setIcon(icon)
        il.setError(value)
        il.setTextColor(color)
        il.setFont(font)
        il.setTextSize(size)
    }
}
