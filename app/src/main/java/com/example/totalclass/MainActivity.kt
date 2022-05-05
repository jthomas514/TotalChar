package com.example.totalclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    var charName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        charName = intent.getStringExtra("file_name").toString()

        //Toast.makeText(this, charName, Toast.LENGTH_LONG).show()


        attribut.setOnClickListener(){
            setCurrentFragment(Attribute(charName))
        }
        skills.setOnClickListener(){
            setCurrentFragment(Skills(charName))
        }
        sav_throw.setOnClickListener(){
            setCurrentFragment(SavingThrow(charName))
        }
        weapon.setOnClickListener(){
            setCurrentFragment(Weap(charName))
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}