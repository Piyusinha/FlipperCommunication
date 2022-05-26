package com.teen.flippercommunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val interfaceFlipper = object :FlipperPlugin{
            override fun getResult() {
                runOnUiThread {
                    Toast.makeText(this@MainActivity,"Toast from Flipper",Toast.LENGTH_SHORT).show()
                }
            }

        }
        (application as? FlipperApplication)?.custom?.setInterface(interfaceFlipper)
        findViewById<AppCompatButton>(R.id.submit)?.setOnClickListener {
            (application as? FlipperApplication)?.custom?.update(findViewById<EditText>(R.id.editbox)?.text.toString())
        }
        findViewById<EditText>(R.id.editbox)?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                (application as? FlipperApplication)?.custom?.update(findViewById<EditText>(R.id.editbox)?.text.toString())
                true
            } else
                false
        }

    }
}