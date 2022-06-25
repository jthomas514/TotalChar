package com.example.totalclass

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.loadchar.*
import java.io.File
import java.lang.StringBuilder

/*create save file here or load the file if character already made*/

class Character : AppCompatActivity() {
    private var nameOfChar = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loadchar)



        start.setOnClickListener{
            val charName = name.text.toString()
            createFile(charName)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("file_name", nameOfChar)
            startActivity(intent)
        }


    }
    private fun createFile(file_name: String){
        val sb = StringBuilder()
        sb.append(file_name).append(".txt")
        nameOfChar = sb.toString()
        File(applicationContext.filesDir, nameOfChar)
    }

}