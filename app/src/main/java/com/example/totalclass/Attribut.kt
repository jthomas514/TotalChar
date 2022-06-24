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
        //val fileContent = load.readText()
        //file clearer
        val fileContent = ""
        load.writeText(fileContent)

        val str_pattern = "Strength\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val dex_pattern = "Dexterity\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val int_pattern = "Intelligence\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val wis_pattern = "Wisdom\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val con_pattern = "Constitution\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val char_pattern = "Charisma\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        var val_pattern = "\\d+".toRegex()
        var tempMatch = str_pattern.find(fileContent)

        if(fileContent != "" && tempMatch != null) {
            var temp_value = val_pattern.find(tempMatch!!.value)
            var temp_num = calc_modifier(temp_value!!.value.toInt())
            str.setText(temp_value?.value)
            str_mod.text = temp_num.toString()

            tempMatch = dex_pattern.find(fileContent)
            temp_value = val_pattern.find(tempMatch!!.value)
            temp_num = calc_modifier(temp_value!!.value.toInt())
            dex.setText(temp_value?.value)
            dex_mod.text = temp_num.toString()

            tempMatch = int_pattern.find(fileContent)
            temp_value = val_pattern.find(tempMatch!!.value)
            temp_num = calc_modifier(temp_value!!.value.toInt())
            inte.setText(temp_value?.value)
            int_mod.text = temp_num.toString()

            tempMatch = wis_pattern.find(fileContent)
            temp_value = val_pattern.find(tempMatch!!.value)
            temp_num = calc_modifier(temp_value!!.value.toInt())
            wis.setText(temp_value?.value)
            wis_mod.text = temp_num.toString()

            tempMatch = con_pattern.find(fileContent)
            temp_value = val_pattern.find(tempMatch!!.value)
            temp_num = calc_modifier(temp_value!!.value.toInt())
            con.setText(temp_value?.value)
            con_mod.text = temp_num.toString()

            tempMatch = char_pattern.find(fileContent)
            temp_value = val_pattern.find(tempMatch!!.value)
            temp_num = calc_modifier(temp_value!!.value.toInt())
            cha.setText(temp_value?.value)
            cha_mod.text = temp_num.toString()
        }


        gen_att.setOnClickListener(){
            if(fileContent == "") {
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
        }

        attr_save.setOnClickListener(){

            var file = File(context?.filesDir, charName)
            var string_replacer = ""

            var file_content = file.readText()

            calc_modifier(str, str_mod)
            calc_modifier(dex, dex_mod)
            calc_modifier(inte, int_mod)
            calc_modifier(wis, wis_mod)
            calc_modifier(con, con_mod)
            calc_modifier(cha, cha_mod)

            var checker = str_pattern.find(fileContent)//this is to check to see if this is here
                                                    //if it is not then we assume this page has not been saved

            if(checker == null){
                file_content = file_content + "_____Attributes_____\n"
                file_content = (file_content + "Strength Modifier "+ str.text.toString() +" "+ str_mod.text.toString()+"\n")
                file_content = (file_content + "Dexterity Modifier "+ dex.text.toString() +" "+ dex_mod.text.toString()+"\n")
                file_content = (file_content + "Intelligence Modifier "+ inte.text.toString() +" "+ int_mod.text.toString()+"\n")
                file_content = (file_content + "Wisdom Modifier "+ wis.text.toString() +" "+ wis_mod.text.toString()+"\n")
                file_content = (file_content + "Constitution Modifier "+ con.text.toString() +" "+ con_mod.text.toString()+"\n")
                file_content = (file_content + "Charisma Modifier "+ cha.text.toString() +" "+ cha_mod.text.toString()+"\n")
                file.writeText(file_content)
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
                        string_replacer = file_content.replace(con_pattern, temp_new)
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
        var temp = -5
        var iter = 0
        while(iter <= value.text.toString().toInt()){
            if(iter % 2 == 0 && iter != 0)
                temp++
            iter++
        }
        calc.text = temp.toString()
    }

    fun calc_modifier(value: Int): Int{
        var temp = -5
        var iter = 0
        while(iter <= value){
            if(iter % 2 == 0 && iter != 0)
                temp++
            iter++
        }
        return temp
    }

}