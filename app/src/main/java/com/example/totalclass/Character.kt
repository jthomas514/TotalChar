package com.example.totalclass

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.loadchar.*
import java.io.File
import java.lang.StringBuilder

/*create save file here or load the file if character already made*/

class Character : AppCompatActivity() {
    var nameOfChar = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loadchar)



        start.setOnClickListener(){
            var char_name = name.text.toString()
            createFile(char_name)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("file_name", nameOfChar)
            startActivity(intent)
        }


    }
    fun createFile(file_name: String){
        val sb = StringBuilder()
        sb.append(file_name).append(".txt")
        nameOfChar = sb.toString()
        //Toast.makeText(applicationContext, nameOfChar, Toast.LENGTH_SHORT).show()
        val file = File(applicationContext.filesDir, nameOfChar)
        val isNewFileCreated :Boolean = file.createNewFile()
//        if(isNewFileCreated){
//            Toast.makeText(applicationContext, "File created", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(applicationContext, "File not created", Toast.LENGTH_SHORT).show()
//        }
    }

}