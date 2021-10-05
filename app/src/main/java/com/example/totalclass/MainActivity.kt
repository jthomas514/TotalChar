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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val attFrag = Attribut()
        val skillfrag = skils()
        val savingThrow = sav_throw()
        val weap_c = weap()


        attribut.setOnClickListener(){
            setCurrentFragment(attFrag)
        }
        skills.setOnClickListener(){
            setCurrentFragment(skillfrag)
        }
        sav_throw.setOnClickListener(){
            setCurrentFragment(savingThrow)
        }
        weapon.setOnClickListener(){
            setCurrentFragment(weap_c)
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}