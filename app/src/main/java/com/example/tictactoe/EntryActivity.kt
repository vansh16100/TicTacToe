package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_entry.*

class EntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        btnMulti.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }

        btnSingle.setOnClickListener {
            val i = Intent(this,SinglePlayerActivity::class.java)
            startActivity(i)
        }


    }
}