package com.example.totalclass

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.att_fragment.*
import kotlinx.android.synthetic.main.sav_throw.*
import kotlinx.android.synthetic.main.skills.*
import java.io.File

class SavingThrow(file_name: String): Fragment(R.layout.sav_throw) {
    private var charName = file_name
    private val att = Attribute(file_name)
    val valPattern = "-?\\d+".toRegex()
    var fileContent = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val load = File(context?.filesDir, charName)
        fileContent = load.readText()

        val str_pattern = "Strength\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val dex_pattern = "Dexterity\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val int_pattern = "Intelligence\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val wis_pattern = "Wisdom\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val con_pattern = "Constitution\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val char_pattern = "Charisma\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val health_pattern = "Health\\s\\d+".toRegex()
        val damage_pattern = "Damage\\s\\d+".toRegex()
        val fortitude_pattern = "Fortitude\\s-?\\d+\\s-?\\d+\\s+-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val reflex_pattern = "Reflex\\s-?\\d+\\s-?\\d+\\s+-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val will_pattern = "Will\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val base_att_pattern = "Base\\sAttack\\sBonus\\s-?\\d+".toRegex()
        val grapple_pattern = "Grapple\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val armor_pattern = "Armor\\sClass\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        var str_mod = 0
        var dex_mod = 0
        var int_mod = 0
        var wis_mod = 0
        var con_mod = 0
        var char_mod = 0

        var pattern = "_____Attributes_____".toRegex()
        var checker = pattern.find(fileContent)

        if(checker != null) {
            str_mod = att.calc_modifier(findValue(str_pattern))
            dex_mod = att.calc_modifier(findValue(dex_pattern))
            con_mod = att.calc_modifier(findValue(con_pattern))
            int_mod = att.calc_modifier(findValue(int_pattern))
            wis_mod = att.calc_modifier(findValue(wis_pattern))
            char_mod = att.calc_modifier(findValue(char_pattern))
            fort_mod.setText(con_mod.toString())
            ref_mod.setText(dex_mod.toString())
            will_mod.setText(wis_mod.toString())
            grap_str.setText(str_mod.toString())
        }

        pattern = "_____Saving Throws_____".toRegex()
        checker = pattern.find(fileContent)

        if(checker != null){
            health_tot.setText(findValue(health_pattern).toString())
            dmg_tot.setText(findValue(damage_pattern).toString())
            var tempList = findValue(fortitude_pattern, 6)
            fort_total.setText(tempList!!.elementAt(0).toString())
            fort_base.setText(tempList!!.elementAt(1).toString())
            fort_mag.setText(tempList!!.elementAt(3).toString())
            fort_misc.setText(tempList!!.elementAt(4).toString())
            fort_temp.setText(tempList!!.elementAt(5).toString())
            tempList = findValue(reflex_pattern, 6)
            ref_total.setText(tempList!!.elementAt(0).toString())
            ref_base.setText(tempList!!.elementAt(1).toString())
            ref_mag.setText(tempList!!.elementAt(3).toString())
            ref_misc.setText(tempList!!.elementAt(4).toString())
            ref_temp.setText(tempList!!.elementAt(5).toString())
            tempList = findValue(will_pattern, 6)
            will_total.setText(tempList!!.elementAt(0).toString())
            will_base.setText(tempList!!.elementAt(1).toString())
            will_mag.setText(tempList!!.elementAt(3).toString())
            will_misc.setText(tempList!!.elementAt(4).toString())
            will_temp.setText(tempList!!.elementAt(5).toString())
            baseAtt.setText(findValue(base_att_pattern).toString())
            tempList = findValue(grapple_pattern, 6)
            grap_tot.setText(tempList!!.elementAt(0).toString())
            grap_base.setText(tempList!!.elementAt(1).toString())
            grap_size.setText(tempList.elementAt(3).toString())
            grap_misc.setText(tempList!!.elementAt(4).toString())
            var armList = findValue(armor_pattern,8)
            tot_ac.text = armList!!.elementAt(0).toString()
            nat_arm.setText(armList.elementAt(1).toString())
            deflect.setText(armList.elementAt(2).toString())
            armor_misc.setText(armList.elementAt(3).toString())
            armor_bonus.setText(armList.elementAt(4).toString())
            shield_bonus.setText(armList.elementAt(5).toString())
            arm_dex_mod.setText(armList.elementAt(6).toString())//eventually will calculate but for now user must add themselves
            size_mod.setText(armList.elementAt(7).toString())
        }


        bat_save.setOnClickListener(){
            health_tot.setText((health_tot.text.toString().toInt()-dmg_tot.text.toString().toInt()).toString())
            fort_total.setText(calcTotal(fort_base, fort_mod, fort_mag, fort_misc, fort_temp).toString())
            ref_total.setText(calcTotal(ref_base, ref_mod, ref_mag, ref_misc, ref_temp).toString())
            will_total.setText(calcTotal(will_base, will_mod, will_mag, will_misc, will_temp).toString())
            grap_tot.setText(calcTotal(grap_base, grap_str, grap_misc,grap_size).toString())
            tot_ac.text = calcTotal(nat_arm, deflect, armor_misc, armor_bonus, shield_bonus,
                arm_dex_mod, size_mod).toString()
            checker = pattern.find(fileContent)

            if(checker == null){
                fileContent = fileContent +  "Health " + health_tot.text.toString() + "\n"
                fileContent = fileContent + "Damage " + dmg_tot.text.toString() + "\n"
                fileContent = fileContent+ "_____Saving Throws_____\n"
                fileContent = fileContent + "Fortitude " + fort_total.text.toString() + " " +
                        fort_base.text.toString() + " " + fort_mod.text.toString() +
                        " " + fort_mag.text.toString() + " " + fort_misc.text.toString() + " " + fort_temp.text.toString() + "\n"
                fileContent = fileContent + "Reflex " + ref_total.text.toString() + " " + ref_base.text.toString() + " " + ref_mod.text.toString() +
                        " " + ref_mag.text.toString() + " " + ref_misc.text.toString() + " " + ref_temp.text.toString() + "\n"
                fileContent = fileContent + "Will " + will_total.text.toString() + " " + will_base.text.toString() + " " + will_mod.text.toString() + " " +
                        will_mag.text.toString() + " " + will_misc.text.toString() + " " + will_temp.text.toString() + "\n"
                fileContent += "Combat Base\n"
                fileContent = fileContent + "Base Attack Bonus " + baseAtt.text.toString() + "\n"
                fileContent = fileContent + "Grapple " + grap_tot.text.toString() + " " + grap_base.text.toString() + " " + grap_str.text.toString() +
                        " " + grap_size.text.toString() + " " + grap_misc.text.toString() + "\n"
                fileContent = fileContent + "Armor Class " + tot_ac.text.toString() + " " + nat_arm.text.toString() + " " + deflect.text.toString() + " " + armor_misc.text.toString() +
                        " " + armor_bonus.text.toString() + " " + shield_bonus.text.toString() + " " + arm_dex_mod.text.toString() +
                        " " + size_mod.text.toString() + "\n"
                load.writeText(fileContent)
            }
            else{
                var string_replacer = "Health " + health_tot.text.toString()
                fileContent = fileContent.replace(health_pattern, string_replacer)
                string_replacer = "Damage " + dmg_tot.text.toString()
                fileContent = fileContent.replace(damage_pattern, string_replacer)
                string_replacer = "Fortitude " + fort_total.text.toString() + " " + fort_base.text.toString() + " " + fort_mod.text.toString() +
                        " " + fort_mag.text.toString() + " " + fort_misc.text.toString() + " " + fort_temp.text.toString()
                fileContent = fileContent.replace(fortitude_pattern, string_replacer)
                string_replacer = "Reflex " + ref_total.text.toString() + " " + ref_base.text.toString() + " " + ref_mod.text.toString() +
                        " " + ref_mag.text.toString() + " " + ref_misc.text.toString() + " " + ref_temp.text.toString()
                fileContent = fileContent.replace(reflex_pattern, string_replacer)
                string_replacer = "Will " + will_total.text.toString() + " " + will_base.text.toString() + " " + will_mod.text.toString() + " " +
                        will_mag.text.toString() + " " + will_misc.text.toString() + " " + will_temp.text.toString()
                fileContent = fileContent.replace(will_pattern, string_replacer)
                string_replacer = "Base Attack Bonus " + baseAtt.text.toString()
                fileContent = fileContent.replace(base_att_pattern, string_replacer)
                string_replacer = "Grapple " + grap_tot.text.toString() + " " + grap_base.text.toString() + " " + grap_str.text.toString() +
                        " " + grap_size.text.toString() + " " + grap_misc.text.toString()
                fileContent = fileContent.replace(grapple_pattern, string_replacer)
                string_replacer = "Armor Class " +tot_ac.text.toString() + " " + nat_arm.text.toString() + " " + deflect.text.toString() + " " + armor_misc.text.toString() +
                        " " + armor_bonus.text.toString() + " " + shield_bonus.text.toString() + " " + arm_dex_mod.text.toString() +
                        " " + size_mod.text.toString()
                fileContent = fileContent.replace(armor_pattern, string_replacer)
                load.writeText(fileContent)
            }
        }

    }

    //Only finding and returning one value
    fun findValue(findReg:Regex):Int{
        var match = findReg.find(fileContent)
        if(match != null) {
            val matched_value = valPattern.find(match!!.value)
            return matched_value!!.value.toInt()
        }
        return 0
    }

    //For finding multiples associated with section in save file
    fun findValue(findReg:Regex, num:Int): MutableList<Int>? {
        val pattern = "-?\\d+".toRegex()
        var match = findReg.find(fileContent)
        var return_list = mutableListOf<Int>()
        var list = pattern.findAll(match!!.value).map{it.value}.toList()
        for(i in list){
            return_list.add(i.toInt())
        }
        var iter = return_list.size
        if(iter < num){
            while(iter < num){
                return_list.add(0)
                iter++
            }
        }
        return return_list
    }
    fun calcTotal(editText: EditText, editText1: EditText, editText2: EditText,
                  editText3: EditText):Int {
        return editText.text.toString().toInt() + editText1.text.toString().toInt() +
                editText2.text.toString().toInt() + editText3.text.toString().toInt()
    }
    fun calcTotal(editText: EditText, editText1: EditText, editText2: EditText,
                  editText3: EditText,editText4: EditText):Int {
        return editText.text.toString().toInt() + editText1.text.toString().toInt() +
                editText2.text.toString().toInt() + editText3.text.toString().toInt() +
                editText4.text.toString().toInt()
    }
    fun calcTotal(editText: EditText, editText1: EditText, editText2: EditText,
                  editText3: EditText, editText4: EditText, editText5: EditText, editText6:EditText):Int {
        return 10 + editText.text.toString().toInt() + editText1.text.toString().toInt() +
                editText2.text.toString().toInt() + editText3.text.toString().toInt() +
                editText4.text.toString().toInt() + editText5.text.toString().toInt() + editText6.text.toString().toInt()
    }
}