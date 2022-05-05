package com.example.totalclass

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.att_fragment.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.InputStream
import java.nio.Buffer
import java.util.*

class Attribute(file_name: String):Fragment(R.layout.att_fragment) {
    private val dice = Dice()
    private var charName = file_name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        gen_att.setOnClickListener(){
            str.setText(generate().toString())
            dex.setText(generate().toString())
            inte.setText(generate().toString())
            wis.setText(generate().toString())
            con.setText(generate().toString())
            cha.setText(generate().toString())
            calc_modifier(str, str_mod)
            calc_modifier(dex, dex_mod)
            calc_modifier(inte, int_mod)
            calc_modifier(wis, wis_mod)
            calc_modifier(con, con_mod)
            calc_modifier(cha, cha_mod)
            Toast.makeText(context, charName, Toast.LENGTH_SHORT).show()
        }

        attr_save.setOnClickListener(){

            var file = File(context?.filesDir, charName)
            file.printWriter().use{ out ->
                out.println("_____Atributes_____")
                out.println("Strength Modifier "+ str_mod.text.toString())
                out.println("Dexterity Modifier "+ dex_mod.text.toString())
                out.println("Intelligence Modifier "+ int_mod.text.toString())
                out.println("Wisdom Modifier "+ wis_mod.text.toString())
                out.println("Constitution Modifier "+ con_mod.text.toString())
                out.println("Charisma Modifier "+ cha_mod.text.toString())
                out.println()
                out.println()
            }
            var file_content = file.readText()
            test_area.text = file_content

        }

    }

    fun generate(): Int{

        dice.d_six(4)
        var temp = dice.dice_roll.sum()
        temp -= dice.dice_roll[0]
        dice.dice_roll.clear()
        return temp
    }

    fun calc_modifier(value: EditText, calc: TextView){
        var temp = value.text.toString().toInt()
        when (temp) {
            1 -> calc.text = "-5"
            2 -> calc.text = "-4"
            3 -> calc.text = "-4"
            4 -> calc.text = "-3"
            5 -> calc.text = "-3"
            6 -> calc.text = "-2"
            7 -> calc.text = "-2"
            8 -> calc.text = "-1"
            9 -> calc.text = "-1"
            10 -> calc.text = "0"
            11 -> calc.text = "0"
            12 -> calc.text = "1"
            13 -> calc.text = "1"
            14 -> calc.text = "2"
            15 -> calc.text = "2"
            16 -> calc.text = "3"
            17 -> calc.text = "3"
            18 -> calc.text = "4"
            19 -> calc.text = "4"
            20 -> calc.text = "5"
            21 -> calc.text = "5"
            22 -> calc.text = "6"
            23 -> calc.text = "6"
            24 -> calc.text = "7"
            25 -> calc.text = "7"
            26 -> calc.text = "8"
            27 -> calc.text = "8"
            28 -> calc.text = "9"
            29 -> calc.text = "9"
            30 -> calc.text = "10"
        }


    }

}