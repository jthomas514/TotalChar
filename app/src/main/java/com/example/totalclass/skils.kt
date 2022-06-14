package com.example.totalclass

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.att_fragment.*
import kotlinx.android.synthetic.main.skills.*
import kotlinx.android.synthetic.main.skills.view.*
import org.w3c.dom.Attr
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
        val modifiers = arrayOf<EditText>(appr_mod, bal_mod, bluff_mod, climb_mod, conc_mod, decscrit_mod,
                                        diplo_mod, dis_dev_mod, disg_mod, esc_mod, forge_mod, info_mod, anim_mod,
                                        heal_mod, hide_mod, intim_mod, jump_mod, listen_mod, silent_mod,
                                        open_mod, ride_mod, search_mod, motive_mod, pick_mod, spell_mod,
                                        spot_mod, surv_mod, swim_mod, tumble_mod, device_mod, rope_mod)

        if(file_content != ""){
            var temp_match = str_pattern.find(file_content)
            var temp_value = val_pattern.find(temp_match!!.value)
            str_mod = att.calc_modifier(temp_value!!.value.toInt())

            temp_match = dex_pattern.find(file_content)
            temp_value = val_pattern.find(temp_match!!.value)
            dex_mod = att.calc_modifier(temp_value!!.value.toInt())

            temp_match = int_pattern.find(file_content)
            temp_value = val_pattern.find(temp_match!!.value)
            int_mod = att.calc_modifier(temp_value!!.value.toInt())

            temp_match = wis_pattern.find(file_content)
            temp_value = val_pattern.find(temp_match!!.value)
            wis_mod = att.calc_modifier(temp_value!!.value.toInt())

            temp_match = con_pattern.find(file_content)
            temp_value = val_pattern.find(temp_match!!.value)
            con_mod = att.calc_modifier(temp_value!!.value.toInt())

            temp_match = char_pattern.find(file_content)
            temp_value = val_pattern.find(temp_match!!.value)
            char_mod = att.calc_modifier(temp_value!!.value.toInt())
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

        skill_save.setOnClickListener(){
            rope_tot.text = getInt(rope_misc, rope_mod, rope_rank).toString()
            device_tot.text = getInt(device_misc, device_mod, device_rank).toString()
            tumble_tot.text = getInt(tumble_misc, tumble_mod, tumble_rank).toString()
            swim_tot.text = getInt(swim_misc, swim_mod, swim_rank).toString()
            surv_tot.text = getInt(surv_misc, surv_mod, surv_rank).toString()
            spot_tot.text = getInt(spot_misc, spot_mod, spot_rank).toString()
            spell_tot.text = getInt(spell_misc, spell_mod, spell_rank).toString()
            pick_tot.text = getInt(pick_misc, pick_mod, pick_rank).toString()
            motive_tot.text = getInt(motive_misc, motive_mod, motive_rank).toString()
            search_tot.text = getInt(search_misc, search_mod, search_rank).toString()
            ride_tot.text = getInt(ride_misc, ride_mod, ride_rank).toString()
            open_tot.text = getInt(open_misc, open_mod, open_rank).toString()
            silent_tot.text = getInt(silent_misc, silent_mod, silent_rank).toString()
            listen_tot.text = getInt(listen_misc, listen_mod, listen_rank).toString()
            jump_tot.text = getInt(jump_misc, jump_mod, jump_rank).toString()
            intim_tot.text = getInt(intim_misc, intim_mod, intim_rank).toString()
            hide_tot.text = getInt(hide_misc, hide_mod, hide_rank).toString()
            heal_tot.text = getInt(heal_misc, heal_mod, heal_rank).toString()
            anim_tot.text = getInt(anim_misc, anim_mod, anim_rank).toString()
            info_tot.text = getInt(info_misc, info_mod, info_rank).toString()
            forge_tot.text = getInt(forge_misc, forge_mod, forge_rank).toString()
            esc_tot.text = getInt(esc_misc, esc_mod, esc_rank).toString()
            disg_tot.text = getInt(disg_misc, disg_mod, disg_rank).toString()
            dis_dev_tot.text = getInt(dis_dev_misc, dis_dev_mod, dis_dev_rank).toString()
            diplo_tot.text = getInt(diplo_misc, diplo_mod, diplo_rank).toString()
            decscript_tot.text = getInt(decscript_misc, decscrit_mod, decscript_rank).toString()
            conc_tot.text = getInt(conc_misc, conc_mod, conc_rank).toString()
            climb_tot.text = getInt(climb_misc, climb_mod, climb_rank).toString()
            bluff_tot.text = getInt(bluff_misc, bluff_mod, bluff_rank).toString()
            bal_tot.text = getInt(bal_misc, bal_mod, bal_rank).toString()
            appr_tot.text = getInt(appr_misc, appr_mod, appr_rank).toString()
        }
    }

    fun getInt(edit: EditText, edit_2: EditText, edit_3: EditText):Int
    {
        return edit.text.toString().toInt() + edit_2.text.toString().toInt() + edit_3.text.toString().toInt()
    }

    fun skill_mod(edit: EditText){
    }
}