package com.example.totalclass

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.att_fragment.*
import kotlinx.android.synthetic.main.skills.*

class Skills(file_name: String): Fragment(R.layout.skills) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skill_save.setOnClickListener(){
            skill_mod(swim_mod)
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
        when(edit.hint){
            "str" -> edit.setText(800.toString())
        }
    }
}