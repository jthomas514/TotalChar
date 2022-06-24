package com.example.totalclass

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.att_fragment.*
import kotlinx.android.synthetic.main.skills.*

import java.io.File

class Skills(file_name: String): Fragment(R.layout.skills) {

    private val att = Attribute(file_name)
    var char_name = file_name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val load = File(context?.filesDir, char_name)
        var file_content = load.readText()

        val str_pattern = "Strength\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val dex_pattern = "Dexterity\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val int_pattern = "Intelligence\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val wis_pattern = "Wisdom\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val con_pattern = "Constitution\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        val char_pattern = "Charisma\\sModifier\\s\\d+\\s-?\\d+".toRegex()
        var val_pattern = "\\d+".toRegex()
        var str_mod = 0
        var dex_mod = 0
        var int_mod = 0
        var wis_mod = 0
        var con_mod = 0
        var char_mod = 0
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

        if(file_content != ""){
            var temp_match = str_pattern.find(file_content)
            var temp_value:MatchResult?
            if(temp_match!= null) {
                temp_value = val_pattern.find(temp_match!!.value)
                str_mod = att.calc_modifier(temp_value!!.value.toInt())
            }

            temp_match = dex_pattern.find(file_content)
            if(temp_match != null) {
                temp_value = val_pattern.find(temp_match!!.value)
                dex_mod = att.calc_modifier(temp_value!!.value.toInt())
            }

            temp_match = int_pattern.find(file_content)
            if(temp_match != null) {
                temp_value = val_pattern.find(temp_match!!.value)
                int_mod = att.calc_modifier(temp_value!!.value.toInt())
            }

            temp_match = wis_pattern.find(file_content)
            if(temp_match != null) {
                temp_value = val_pattern.find(temp_match!!.value)
                wis_mod = att.calc_modifier(temp_value!!.value.toInt())
            }

            temp_match = con_pattern.find(file_content)
            if(temp_match != null) {
                temp_value = val_pattern.find(temp_match!!.value)
                con_mod = att.calc_modifier(temp_value!!.value.toInt())
            }

            temp_match = char_pattern.find(file_content)
            if(temp_match != null) {
                temp_value = val_pattern.find(temp_match!!.value)
                char_mod = att.calc_modifier(temp_value!!.value.toInt())
            }
            var iter = 0
            while(iter < names.size){
                var temp_pattern = (names.elementAt(iter).text.toString() + "\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+").toRegex()
                temp_match = temp_pattern.find(file_content)
                if(temp_match == null)
                    break
                var temp_sequence = val_pattern.findAll(temp_match!!.value).map{it.value}.toList()
                skills.elementAt(iter).text = temp_sequence.elementAt(0).toString()
                modifiers.elementAt(iter).setText(temp_sequence.elementAt(1).toString())
                ranks.elementAt(iter).setText(temp_sequence.elementAt(2).toString())
                miscs.elementAt(iter).setText(temp_sequence.elementAt(3).toString())
                iter++
            }
        }

        for(i in modifiers){
            when (i.hint) {
                "str" -> i.setText(str_mod.toString())
                "dex" -> i.setText(dex_mod.toString())
                "int" -> i.setText(int_mod.toString())
                "wis" -> i.setText(wis_mod.toString())
                "con" -> i.setText(con_mod.toString())
                "cha" -> i.setText(char_mod.toString())
            }
        }

        skill_save.setOnClickListener(){//breaking in here
            var skill_to_text = ""
            var string_replacer = ""
            var temp_new = ""
            var temp_pattern = (names.elementAt(0).text.toString()+"\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+").toRegex()
            var temp_value = temp_pattern.find(file_content)
            if(temp_value == null){//if break after the first check then have major problem
                var i = 0
                file_content += "_____Skill_____\n"

                while(i < skills.size){
                    skills.elementAt(i).text = getInt(modifiers.elementAt(i), ranks.elementAt(i), miscs.elementAt(i)).toString()
                    skills[i].text = getInt(modifiers.elementAtOrNull(i)!!, ranks.elementAtOrNull(i)!!,
                        miscs.elementAtOrNull(i)!!).toString()
                    skill_to_text = (names.elementAt(i).text.toString() +" "+ skills.elementAt(i).text.toString()+
                            " " + modifiers.elementAt(i).text.toString() +" " +
                            ranks.elementAt(i).text.toString() + " " + miscs.elementAt(i).text.toString()+ "\n")
                    file_content = file_content + skill_to_text
                    i++
                }
                Toast.makeText(context, "it breaked", Toast.LENGTH_SHORT).show()
                load.writeText(file_content)
            }
            else {
                var iter = 0
                while (iter < names.size) {
                    skills.elementAt(iter).text = getInt(modifiers.elementAt(iter), ranks.elementAt(iter), miscs.elementAt(iter)).toString()
                    temp_pattern = (names.elementAt(iter).text.toString() + "\\s-?\\d+\\s-?\\d+\\s-?\\d+\\s-?\\d+").toRegex()
                    skill_to_text = names.elementAt(iter).text.toString() + " " + skills.elementAt(iter).text.toString() +
                                " " + modifiers.elementAt(iter).text.toString() + " " +
                                ranks.elementAt(iter).text.toString() + " " + miscs.elementAt(iter).text.toString()
                    string_replacer = file_content.replace(temp_pattern, skill_to_text)
                    file_content = string_replacer
                    iter++
                }

                load.writeText(file_content)
            }
        }
    }

    fun getInt(edit: EditText, edit_2: EditText, edit_3: EditText):Int
    {
        return edit.text.toString().toInt() + edit_2.text.toString().toInt() + edit_3.text.toString().toInt()
    }
}