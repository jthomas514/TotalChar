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
import java.io.*
import java.nio.Buffer
import java.util.*

class Attribute(file_name: String):Fragment(R.layout.att_fragment) {
    private val dice = Dice()
    private var charName = file_name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val load = File(context?.filesDir, charName)
        val fileContent = load.readText()

        val str_pattern = "Strength\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val dex_pattern = "Dexterity\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val int_pattern = "Intelligence\\sModifier\\s\\d+-?\\d".toRegex()
        val wis_pattern = "Wisdom\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val con_pattern = "Constitution\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val char_pattern = "Charisma\\sModifier\\s\\d+\\s-?\\d+".toRegex()

        var tempMatch = str_pattern.find(fileContent)
        var tempRange = tempMatch?.range
        str?.setText(fileContent[tempRange!!.last + 1].toString()) //only going to give one digit even if its multiple digit, so need to fix
        //need to run a regex twice to load the values
        //Only to find the first value, then generate the modifier off them



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
        }

        attr_save.setOnClickListener(){

            var file = File(context?.filesDir, charName)
            var string_replacer = ""

            var file_content = file.readText()
            test_area.text = file_content

            if(file_content == "") {
                file.printWriter().use{ out ->
                    out.println("_____Attributes_____")
                    out.println("Strength Modifier "+ str.text.toString() +" "+ str_mod.text.toString())
                    out.println("Dexterity Modifier "+ dex.text.toString() +" "+ dex_mod.text.toString())
                    out.println("Intelligence Modifier "+ inte.text.toString() +" "+ int_mod.text.toString())
                    out.println("Wisdom Modifier "+ wis.text.toString() +" "+ wis_mod.text.toString())
                    out.println("Constitution Modifier "+ con.text.toString() +" "+ con_mod.text.toString())
                    out.println("Charisma Modifier "+ cha.text.toString() +" "+ cha_mod.text.toString())
                    out.println()
                    out.println()
                }
            }

            else{
                val pattern = "_____Attributes_____".toRegex()
                val match = pattern.find(file_content)


                val index = match?.value
                val range = match?.range

                if(match != null){
                    var temp_new = ""
                    var temp_match = str_pattern.find(file_content)

                    if(temp_match != null){
                        temp_new = "Strength Modifier "+ str.text.toString() +" "+ str_mod.text.toString()
                        string_replacer = file_content.replace(str_pattern, temp_new)
                        file_content = string_replacer
                        temp_new = "Dexterity Modifier "+ dex.text.toString() +" "+ dex_mod.text.toString()
                        string_replacer = file_content.replace(dex_pattern, temp_new)
                        file_content = string_replacer
                        temp_new = "Intelligence Modifier "+ inte.text.toString() +" "+ int_mod.text.toString()
                        string_replacer = file_content.replace(int_pattern, temp_new)
                        file_content = string_replacer
                        temp_new = "Wisdom Modifier "+ wis.text.toString() +" "+ wis_mod.text.toString()
                        string_replacer = file_content.replace(wis_pattern, temp_new)
                        file_content = string_replacer
                        temp_new = "Constitution Modifier "+ con.text.toString() +" "+ con_mod.text.toString()
                        string_replacer = file_content.replace(wis_pattern, temp_new)
                        file_content = string_replacer
                        temp_new = "Charisma Modifier "+ cha.text.toString() +" "+ cha_mod.text.toString()
                        string_replacer = file_content.replace(char_pattern, temp_new)
                        file_content = string_replacer
                    }
                    else
                        Toast.makeText(context, "This is dumb", Toast.LENGTH_SHORT).show()
                    file.writeText(file_content)
                }
            }

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