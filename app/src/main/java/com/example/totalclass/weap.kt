package com.example.totalclass

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.weap.*
import java.util.*

class Weap(file_name: String) : Fragment(R.layout.weap) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weap_save.setOnClickListener(){
        }
    }
}