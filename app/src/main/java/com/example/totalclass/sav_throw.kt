package com.example.totalclass

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.sav_throw.*
import java.io.File

class SavingThrow(file_name: String): Fragment(R.layout.sav_throw) {
    private var charName = file_name
    private val att = Attribute(file_name)
    private val valPattern = "-?\\d+".toRegex()
    private var fileContent = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val load = File(context?.filesDir, charName)
        fileContent = load.readText()

        val strPattern = "Strength\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val dexPattern = "Dexterity\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        //val intPattern = "Intelligence\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val wisPattern = "Wisdom\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val conPattern = "Constitution\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        //val charPattern = "Charisma\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val healthPattern = "Health\\s\\d+".toRegex()
        val damagePattern = "Damage\\s\\d+".toRegex()
        val fortitudePattern = "Fortitude\\s-?\\d+\\s-?\\d+\\s+-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val reflexPattern = "Reflex\\s-?\\d+\\s-?\\d+\\s+-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val willPattern = "Will\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val baseAttPattern = "Base\\sAttack\\sBonus\\s-?\\d+".toRegex()
        val grapplePattern = "Grapple\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val armorPattern = "Armor\\sClass\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+".toRegex()
        val strMod:Int
        val dexMod:Int
        //val intMod:Int
        val wisMod:Int
        val conMod:Int
        //val charMod:Int

        var pattern = "_____Attributes_____".toRegex()
        var checker = pattern.find(fileContent)

        if(checker != null) {
            strMod = att.calcModifier(findValue(strPattern))
            dexMod = att.calcModifier(findValue(dexPattern))
            conMod = att.calcModifier(findValue(conPattern))
            //intMod = att.calc_modifier(findValue(intPattern))
            wisMod = att.calcModifier(findValue(wisPattern))
            //charMod = att.calc_modifier(findValue(charPattern))
            fort_mod.setText(conMod.toString())
            ref_mod.setText(dexMod.toString())
            will_mod.setText(wisMod.toString())
            grap_str.setText(strMod.toString())
        }

        pattern = "_____Saving Throws_____".toRegex()
        checker = pattern.find(fileContent)

        if(checker != null){
            health_tot.setText(findValue(healthPattern).toString())
            dmg_tot.setText(findValue(damagePattern).toString())
            var tempList = findValue(fortitudePattern, 6)
            fort_total.setText(tempList.elementAt(0).toString())
            fort_base.setText(tempList.elementAt(1).toString())
            fort_mag.setText(tempList.elementAt(3).toString())
            fort_misc.setText(tempList.elementAt(4).toString())
            fort_temp.setText(tempList.elementAt(5).toString())
            tempList = findValue(reflexPattern, 6)
            ref_total.setText(tempList.elementAt(0).toString())
            ref_base.setText(tempList.elementAt(1).toString())
            ref_mag.setText(tempList.elementAt(3).toString())
            ref_misc.setText(tempList.elementAt(4).toString())
            ref_temp.setText(tempList.elementAt(5).toString())
            tempList = findValue(willPattern, 6)
            will_total.setText(tempList.elementAt(0).toString())
            will_base.setText(tempList.elementAt(1).toString())
            will_mag.setText(tempList.elementAt(3).toString())
            will_misc.setText(tempList.elementAt(4).toString())
            will_temp.setText(tempList.elementAt(5).toString())
            baseAtt.setText(findValue(baseAttPattern).toString())
            tempList = findValue(grapplePattern, 6)
            grap_tot.setText(tempList.elementAt(0).toString())
            grap_base.setText(tempList.elementAt(1).toString())
            grap_size.setText(tempList.elementAt(3).toString())
            grap_misc.setText(tempList.elementAt(4).toString())
            val armList = findValue(armorPattern,8)
            tot_ac.text = armList.elementAt(0).toString()
            nat_arm.setText(armList.elementAt(1).toString())
            deflect.setText(armList.elementAt(2).toString())
            armor_misc.setText(armList.elementAt(3).toString())
            armor_bonus.setText(armList.elementAt(4).toString())
            shield_bonus.setText(armList.elementAt(5).toString())
            arm_dex_mod.setText(armList.elementAt(6).toString())//eventually will calculate but for now user must add themselves
            size_mod.setText(armList.elementAt(7).toString())
        }


        bat_save.setOnClickListener{
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
                fileContent += "_____Saving Throws_____\n"
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
                var stringReplacer = "Health " + health_tot.text.toString()
                fileContent = fileContent.replace(healthPattern, stringReplacer)
                stringReplacer = "Damage " + dmg_tot.text.toString()
                fileContent = fileContent.replace(damagePattern, stringReplacer)
                stringReplacer = "Fortitude " + fort_total.text.toString() + " " + fort_base.text.toString() + " " + fort_mod.text.toString() +
                        " " + fort_mag.text.toString() + " " + fort_misc.text.toString() + " " + fort_temp.text.toString()
                fileContent = fileContent.replace(fortitudePattern, stringReplacer)
                stringReplacer = "Reflex " + ref_total.text.toString() + " " + ref_base.text.toString() + " " + ref_mod.text.toString() +
                        " " + ref_mag.text.toString() + " " + ref_misc.text.toString() + " " + ref_temp.text.toString()
                fileContent = fileContent.replace(reflexPattern, stringReplacer)
                stringReplacer = "Will " + will_total.text.toString() + " " + will_base.text.toString() + " " + will_mod.text.toString() + " " +
                        will_mag.text.toString() + " " + will_misc.text.toString() + " " + will_temp.text.toString()
                fileContent = fileContent.replace(willPattern, stringReplacer)
                stringReplacer = "Base Attack Bonus " + baseAtt.text.toString()
                fileContent = fileContent.replace(baseAttPattern, stringReplacer)
                stringReplacer = "Grapple " + grap_tot.text.toString() + " " + grap_base.text.toString() + " " + grap_str.text.toString() +
                        " " + grap_size.text.toString() + " " + grap_misc.text.toString()
                fileContent = fileContent.replace(grapplePattern, stringReplacer)
                stringReplacer = "Armor Class " +tot_ac.text.toString() + " " + nat_arm.text.toString() + " " + deflect.text.toString() + " " + armor_misc.text.toString() +
                        " " + armor_bonus.text.toString() + " " + shield_bonus.text.toString() + " " + arm_dex_mod.text.toString() +
                        " " + size_mod.text.toString()
                fileContent = fileContent.replace(armorPattern, stringReplacer)
                load.writeText(fileContent)
            }
        }

    }

    //Only finding and returning one value
    private fun findValue(findReg:Regex):Int{
        val match = findReg.find(fileContent)
        if(match != null) {
            val matchedValue = valPattern.find(match.value)
            return matchedValue!!.value.toInt()
        }
        return 0
    }

    //For finding multiples associated with section in save file
    private fun findValue(findReg:Regex, num:Int): MutableList<Int> {
        val pattern = "-?\\d+".toRegex()
        val match = findReg.find(fileContent)
        val returnList = mutableListOf<Int>()
        val list = pattern.findAll(match!!.value).map{it.value}.toList()
        for(i in list){
            returnList.add(i.toInt())
        }
        var iter = returnList.size
        if(iter < num){
            while(iter < num){
                returnList.add(0)
                iter++
            }
        }
        return returnList
    }
    private fun calcTotal(editText: EditText, editText1: EditText, editText2: EditText,
                  editText3: EditText):Int {
        return editText.text.toString().toInt() + editText1.text.toString().toInt() +
                editText2.text.toString().toInt() + editText3.text.toString().toInt()
    }
    private fun calcTotal(editText: EditText, editText1: EditText, editText2: EditText,
                  editText3: EditText,editText4: EditText):Int {
        return editText.text.toString().toInt() + editText1.text.toString().toInt() +
                editText2.text.toString().toInt() + editText3.text.toString().toInt() +
                editText4.text.toString().toInt()
    }
    private fun calcTotal(editText: EditText, editText1: EditText, editText2: EditText,
                  editText3: EditText, editText4: EditText, editText5: EditText, editText6:EditText):Int {
        return 10 + editText.text.toString().toInt() + editText1.text.toString().toInt() +
                editText2.text.toString().toInt() + editText3.text.toString().toInt() +
                editText4.text.toString().toInt() + editText5.text.toString().toInt() + editText6.text.toString().toInt()
    }
}