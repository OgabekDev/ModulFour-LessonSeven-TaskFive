package dev.ogabek.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.text.method.LinkMovementMethod

import android.text.Spannable

import android.graphics.Color

import android.text.TextPaint

import android.util.Log

import android.view.View

import android.text.style.ClickableSpan

import android.text.SpannableString
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    private lateinit var tv_main: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        tv_main = findViewById(R.id.tv_main)

        setTags(tv_main, tv_main.text.toString())

    }

    private fun setTags(tv_main: TextView, text: String) {
        val string = SpannableString(text)
        var start = -1
        var i = 0
        while (i < text.length) {
            if (text[i] == '#') {
                start = i
            } else if (text[i] == ' ' || text[i] == '\n' || i == text.length - 1 && start != -1) {
                if (start != -1) {
                    if (i == text.length - 1) {
                        i++
                    }
                    val tag = text.substring(start, i)
                    string.setSpan(object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            Log.d("Hash", String.format("Clicked %s!", tag))
                        }
                        override fun updateDrawState(ds: TextPaint) {
                            // link color
                            ds.color = Color.parseColor("#33b5e5")
                            ds.isUnderlineText = false
                        }
                    }, start, i, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    start = -1
                }
            }
            i++
        }
        tv_main.movementMethod = LinkMovementMethod.getInstance()
        tv_main.text = string
    }

}