package com.example.totalclass

import java.util.*

class Dice {
    val dice_roll = Vector<Int>()

    fun d_four(number: Int){
        repeat(number){
            dice_roll.addElement((1..4).random())
        }
        dice_roll.sort()
    }
    fun d_six(number: Int){
        repeat(number){
            dice_roll.addElement((1..6).random())
        }
        dice_roll.sort()
    }
    fun d_eight(number: Int){
        repeat(number){
            dice_roll.addElement((1..8).random())
        }
        dice_roll.sort()
    }
    fun d_ten(number: Int){
        repeat(number){
            dice_roll.addElement((1..10).random())
        }
        dice_roll.sort()
    }
    fun d_twelve(number: Int){
        repeat(number){
            dice_roll.addElement((1..12).random())
        }
        dice_roll.sort()
    }
    fun d_twenty(number: Int){
        repeat(number){
            dice_roll.addElement((1..20).random())
        }
        dice_roll.sort()
    }
}