package com.example.totalclass

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.skills.*

class skils: Fragment(R.layout.skills) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        skill_save.setOnClickListener(){
            rope_tot.setText(getInt(rope_misc, rope_mod, rope_rank).toString())
            device_tot.setText(getInt(device_misc, device_mod, device_rank).toString())
            tumble_tot.setText(getInt(tumble_misc, tumble_mod, tumble_rank).toString())
            swim_tot.setText(getInt(swim_misc, swim_mod, swim_rank).toString())
            surv_tot.setText(getInt(surv_misc, surv_mod, surv_rank).toString())
            spot_tot.setText(getInt(spot_misc, spot_mod, spot_rank).toString())
            spell_tot.setText(getInt(spell_misc, spell_mod, spell_rank).toString())
            pick_tot.setText(getInt(pick_misc, pick_mod, pick_rank).toString())
            motive_tot.setText(getInt(motive_misc, motive_mod, motive_rank).toString())
            search_tot.setText(getInt(search_misc, search_mod, search_rank).toString())
        }
    }

    fun getInt(edit: EditText, edit_2: EditText, edit_3: EditText):Int
    {

        return edit.text.toString().toInt() + edit_2.text.toString().toInt() + edit_3.text.toString().toInt()
    }
}