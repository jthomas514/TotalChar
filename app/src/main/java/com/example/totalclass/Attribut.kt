package com.example.totalclass

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.att_fragment.*
import java.io.*

class Attribute(file_name: String):Fragment(R.layout.att_fragment) {
    private val dice = Dice()
    private var charName = file_name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val load = File(context?.filesDir, charName)
        var fileContent = load.readText()
        //file clearer
        //val fileContent = ""
        //load.writeText(fileContent)

        val strPattern = "Strength\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val dexPattern = "Dexterity\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val intPattern = "Intelligence\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val wisPattern = "Wisdom\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val conPattern = "Constitution\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val charPattern = "Charisma\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val valPattern = "\\d+".toRegex()
        var tempMatch = strPattern.find(fileContent)

        if(fileContent != "" && tempMatch != null) {
            var tempValue = valPattern.find(tempMatch.value)
            var tempNum = calcModifier(tempValue!!.value.toInt())
            str.setText(tempValue.value)
            str_mod.text = tempNum.toString()

            tempMatch = dexPattern.find(fileContent)
            tempValue = valPattern.find(tempMatch!!.value)
            tempNum = calcModifier(tempValue!!.value.toInt())
            dex.setText(tempValue.value)
            dex_mod.text = tempNum.toString()

            tempMatch = intPattern.find(fileContent)
            tempValue = valPattern.find(tempMatch!!.value)
            tempNum = calcModifier(tempValue!!.value.toInt())
            inte.setText(tempValue.value)
            int_mod.text = tempNum.toString()

            tempMatch = wisPattern.find(fileContent)
            tempValue = valPattern.find(tempMatch!!.value)
            tempNum = calcModifier(tempValue!!.value.toInt())
            wis.setText(tempValue.value)
            wis_mod.text = tempNum.toString()

            tempMatch = conPattern.find(fileContent)
            tempValue = valPattern.find(tempMatch!!.value)
            tempNum = calcModifier(tempValue!!.value.toInt())
            con.setText(tempValue.value)
            con_mod.text = tempNum.toString()

            tempMatch = charPattern.find(fileContent)
            tempValue = valPattern.find(tempMatch!!.value)
            tempNum = calcModifier(tempValue!!.value.toInt())
            cha.setText(tempValue.value)
            cha_mod.text = tempNum.toString()
        }


        gen_att.setOnClickListener{
            if(strPattern.find(fileContent) == null) {
                str.setText(generate().toString())
                dex.setText(generate().toString())
                inte.setText(generate().toString())
                wis.setText(generate().toString())
                con.setText(generate().toString())
                cha.setText(generate().toString())
                calcModifier(str, str_mod)
                calcModifier(dex, dex_mod)
                calcModifier(inte, int_mod)
                calcModifier(wis, wis_mod)
                calcModifier(con, con_mod)
                calcModifier(cha, cha_mod)
            }
        }

        attr_save.setOnClickListener{

            val file = File(context?.filesDir, charName)
            var stringReplacer:String

            calcModifier(str, str_mod)
            calcModifier(dex, dex_mod)
            calcModifier(inte, int_mod)
            calcModifier(wis, wis_mod)
            calcModifier(con, con_mod)
            calcModifier(cha, cha_mod)

            val checker = strPattern.find(fileContent)//this is to check to see if this is here
                                                    //if it is not then we assume this page has not been saved

            if(checker == null){
                fileContent += "_____Attributes_____\n"
                fileContent = (fileContent + "Strength Modifier "+ str.text.toString() +" "+ str_mod.text.toString()+"\n")
                fileContent = (fileContent + "Dexterity Modifier "+ dex.text.toString() +" "+ dex_mod.text.toString()+"\n")
                fileContent = (fileContent + "Intelligence Modifier "+ inte.text.toString() +" "+ int_mod.text.toString()+"\n")
                fileContent = (fileContent + "Wisdom Modifier "+ wis.text.toString() +" "+ wis_mod.text.toString()+"\n")
                fileContent = (fileContent + "Constitution Modifier "+ con.text.toString() +" "+ con_mod.text.toString()+"\n")
                fileContent = (fileContent + "Charisma Modifier "+ cha.text.toString() +" "+ cha_mod.text.toString()+"\n")
                file.writeText(fileContent)
            }

            else{
                val pattern = "_____Attributes_____".toRegex()
                val match = pattern.find(fileContent)

                if(match != null){
                    var tempNew:String

                    if(strPattern.find(fileContent) != null){
                        tempNew = "Strength Modifier "+ str.text.toString() +" "+ str_mod.text.toString()
                        stringReplacer = fileContent.replace(strPattern, tempNew)
                        fileContent = stringReplacer
                        tempNew = "Dexterity Modifier "+ dex.text.toString() +" "+ dex_mod.text.toString()
                        stringReplacer = fileContent.replace(dexPattern, tempNew)
                        fileContent = stringReplacer
                        tempNew = "Intelligence Modifier "+ inte.text.toString() +" "+ int_mod.text.toString()
                        stringReplacer = fileContent.replace(intPattern, tempNew)
                        fileContent = stringReplacer
                        tempNew = "Wisdom Modifier "+ wis.text.toString() +" "+ wis_mod.text.toString()
                        stringReplacer = fileContent.replace(wisPattern, tempNew)
                        fileContent = stringReplacer
                        tempNew = "Constitution Modifier "+ con.text.toString() +" "+ con_mod.text.toString()
                        stringReplacer = fileContent.replace(conPattern, tempNew)
                        fileContent = stringReplacer
                        tempNew = "Charisma Modifier "+ cha.text.toString() +" "+ cha_mod.text.toString()
                        stringReplacer = fileContent.replace(charPattern, tempNew)
                        fileContent = stringReplacer
                    }
                    else
                        Toast.makeText(context, "This is dumb", Toast.LENGTH_SHORT).show()
                    file.writeText(fileContent)
                }
            }
        }

    }

    private fun generate(): Int{

        dice.d_six(4)
        var temp = dice.dice_roll.sum()
        temp -= dice.dice_roll[0]
        dice.dice_roll.clear()
        return temp
    }

    fun calcModifier(value: EditText, calc: TextView){
        var temp = -5
        var iter = 0
        while(iter <= value.text.toString().toInt()){
            if(iter % 2 == 0 && iter != 0)
                temp++
            iter++
        }
        calc.text = temp.toString()
    }

    fun calcModifier(value: Int): Int{
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