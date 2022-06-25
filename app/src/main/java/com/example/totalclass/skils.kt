package com.example.totalclass

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.skills.*

import java.io.File

class Skills(file_name: String): Fragment(R.layout.skills) {

    private val att = Attribute(file_name)
    private val charName = file_name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val load = File(context?.filesDir, charName)
        var fileContent = load.readText()

        val strPattern = "Strength\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val dexPattern = "Dexterity\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val intPattern = "Intelligence\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val wisPattern = "Wisdom\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val conPattern = "Constitution\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val charPattern = "Charisma\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val valPattern = "\\d+".toRegex()
        var strMod = 0
        var dexMod = 0
        var intMod = 0
        var wisMod = 0
        var conMod = 0
        var charMod = 0
        val modifiers = arrayOf<EditText>(appr_mod, bal_mod, bluff_mod, climb_mod, conc_mod, decscript_mod,
                                        diplo_mod, dis_dev_mod, disg_mod, esc_mod, forge_mod, info_mod, anim_mod,
                                        heal_mod, hide_mod, intim_mod, jump_mod, listen_mod, silent_mod,
                                        open_mod, ride_mod, search_mod, motive_mod, pick_mod, spell_mod,
                                        spot_mod, surv_mod, swim_mod, tumble_mod, device_mod, rope_mod)
        val ranks = arrayOf<EditText>(appr_rank, bal_rank, bluff_rank, climb_rank, conc_rank, decscript_rank,
                                    diplo_rank, dis_dev_rank, disg_rank, esc_rank, forge_rank, info_rank, anim_rank,
                                    heal_rank, hide_rank, intim_rank, jump_rank, listen_rank, silent_rank,
                                    open_rank, ride_rank, search_rank, motive_rank, pick_rank, spell_rank,
                                    spot_rank, surv_rank, swim_rank, tumble_rank, device_rank, rope_rank)
        val miscs = arrayOf<EditText>(appr_misc, bal_misc, bluff_misc, climb_misc, conc_misc, decscript_misc,
                                    diplo_misc, dis_dev_misc, disg_misc, esc_misc, forge_misc, info_misc, anim_misc,
                                    heal_misc, hide_misc, intim_misc, jump_misc, listen_misc, silent_misc,
                                    open_misc, ride_misc, search_misc, motive_misc, pick_misc, spell_misc,
                                    spot_misc, surv_misc, swim_misc, tumble_misc, device_misc, rope_misc)
        val skills = arrayOf<TextView>(appr_tot, bal_tot, bluff_tot, climb_tot, conc_tot, decscript_tot,
                                    diplo_tot, dis_dev_tot, disg_tot, esc_tot, forge_tot, info_tot, anim_tot,
                                    heal_tot, hide_tot, intim_tot, jump_tot, listen_tot, silent_tot,
                                    open_tot, ride_tot, search_tot, motive_tot, pick_tot, spell_tot,
                                    spot_tot, surv_tot, swim_tot, tumble_tot, device_tot, rope_tot)
        val names = arrayOf(appr, bal, bluff, climb, conc, decscript, diplo, dis_dev, disg, esc, forge,
                            info, anim, heal, hide, intim, jump, listen, silent, open, ride, search,
                            motive, pick, spell, spot, surv, swim, tumble, device, rope)

        if(fileContent != ""){
            var tempMatch = strPattern.find(fileContent)
            var tempValue:MatchResult?
            if(tempMatch!= null) {
                tempValue = valPattern.find(tempMatch.value)
                strMod = att.calcModifier(tempValue!!.value.toInt())
            }

            tempMatch = dexPattern.find(fileContent)
            if(tempMatch != null) {
                tempValue = valPattern.find(tempMatch.value)
                dexMod = att.calcModifier(tempValue!!.value.toInt())
            }

            tempMatch = intPattern.find(fileContent)
            if(tempMatch != null) {
                tempValue = valPattern.find(tempMatch.value)
                intMod = att.calcModifier(tempValue!!.value.toInt())
            }

            tempMatch = wisPattern.find(fileContent)
            if(tempMatch != null) {
                tempValue = valPattern.find(tempMatch.value)
                wisMod = att.calcModifier(tempValue!!.value.toInt())
            }

            tempMatch = conPattern.find(fileContent)
            if(tempMatch != null) {
                tempValue = valPattern.find(tempMatch.value)
                conMod = att.calcModifier(tempValue!!.value.toInt())
            }

            tempMatch = charPattern.find(fileContent)
            if(tempMatch != null) {
                tempValue = valPattern.find(tempMatch.value)
                charMod = att.calcModifier(tempValue!!.value.toInt())
            }
            var iter = 0
            while(iter < names.size){
                val tempPattern = (names.elementAt(iter).text.toString() + "\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+").toRegex()
                tempMatch = tempPattern.find(fileContent)
                if(tempMatch == null)
                    break
                val tempSequence = valPattern.findAll(tempMatch.value).map{it.value}.toList()
                skills.elementAt(iter).text = tempSequence.elementAt(0).toString()
                modifiers.elementAt(iter).setText(tempSequence.elementAt(1).toString())
                ranks.elementAt(iter).setText(tempSequence.elementAt(2).toString())
                miscs.elementAt(iter).setText(tempSequence.elementAt(3).toString())
                iter++
            }
        }

        for(i in modifiers){
            when (i.hint) {
                "str" -> i.setText(strMod.toString())
                "dex" -> i.setText(dexMod.toString())
                "int" -> i.setText(intMod.toString())
                "wis" -> i.setText(wisMod.toString())
                "con" -> i.setText(conMod.toString())
                "cha" -> i.setText(charMod.toString())
            }
        }

        skill_save.setOnClickListener{//breaking in here
            var skillToText:String
            var stringReplacer:String
            var tempPat = (names.elementAt(0).text.toString()+"\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+").toRegex()
            val tempValue = tempPat.find(fileContent)
            if(tempValue == null){//if break after the first check then have major problem
                var i = 0
                fileContent += "_____Skill_____\n"

                while(i < skills.size){
                    skills.elementAt(i).text = getInt(modifiers.elementAt(i), ranks.elementAt(i), miscs.elementAt(i)).toString()
                    skills[i].text = getInt(modifiers.elementAtOrNull(i)!!, ranks.elementAtOrNull(i)!!,
                        miscs.elementAtOrNull(i)!!).toString()
                    skillToText = (names.elementAt(i).text.toString() +" "+ skills.elementAt(i).text.toString()+
                            " " + modifiers.elementAt(i).text.toString() +" " +
                            ranks.elementAt(i).text.toString() + " " + miscs.elementAt(i).text.toString()+ "\n")
                    fileContent += skillToText
                    i++
                }
                load.writeText(fileContent)
            }
            else {
                var iter = 0
                while (iter < names.size) {
                    skills.elementAt(iter).text = getInt(modifiers.elementAt(iter), ranks.elementAt(iter), miscs.elementAt(iter)).toString()
                    tempPat = (names.elementAt(iter).text.toString() + "\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+").toRegex()
                    skillToText = names.elementAt(iter).text.toString() + " " + skills.elementAt(iter).text.toString() +
                                " " + modifiers.elementAt(iter).text.toString() + " " +
                                ranks.elementAt(iter).text.toString() + " " + miscs.elementAt(iter).text.toString()
                    stringReplacer = fileContent.replace(tempPat, skillToText)
                    fileContent = stringReplacer
                    iter++
                }

                load.writeText(fileContent)
            }
        }
    }

    private fun getInt(edit: EditText, edit_2: EditText, edit_3: EditText):Int
    {
        return edit.text.toString().toInt() + edit_2.text.toString().toInt() + edit_3.text.toString().toInt()
    }
}