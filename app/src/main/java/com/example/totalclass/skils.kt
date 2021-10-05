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
            var temp = 0
            temp = getInt(rope_misc) + getInt(rope_mod) + getInt(rope_rank)
            rope_tot.setText(temp.toString())
            temp = getInt(device_misc) + getInt(device_mod) + getInt(device_rank)
            device_tot.setText(temp.toString())
            temp = getInt(tumble_misc) + getInt(tumble_mod) + getInt(tumble_rank)
            tumble_tot.setText(temp.toString())
            temp = getInt(swim_misc) + getInt(swim_mod) + getInt(swim_rank)
            swim_tot.setText(temp.toString())
            temp = getInt(surv_misc) + getInt(surv_mod) + getInt(surv_rank)
            surv_tot.setText(temp.toString())
            temp = getInt(spot_misc) + getInt(spot_mod) + getInt(spot_rank)
            spot.setText(temp.toString())
            temp = getInt(spell_misc) + getInt(spell_mod) + getInt(spell_rank)
            spell_tot.setText(temp.toString())
            temp = getInt(pick_misc) + getInt(pick_mod) + getInt(pick_rank)
            pick_tot.setText(temp.toString())
            temp = getInt(motive_misc) + getInt(motive_mod) + getInt(motive_rank)
            motive_tot.setText(temp.toString())
            temp = getInt(search_misc) + getInt(search_mod) + getInt(search_rank)
            search_tot.setText(temp.toString())
        }
    }

    fun getInt(edit: EditText):Int
    {
        return edit.text.toString().toInt()
    }
}