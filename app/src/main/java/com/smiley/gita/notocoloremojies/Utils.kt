package com.smiley.gita.notocoloremojies

class Utils {

    companion object {

        fun getString(codeP1: Int, codeP2: Int) : String {
            val codepoints = intArrayOf(codeP1, codeP2)
            val s = String(codepoints, 0, codepoints.size)
            return s;
        }

        fun getString(codeP1: Int) : String {
            return String(Character.toChars(codeP1))
        }


        fun getString(codeP1: Int, codeP2: Int, codeP3: Int) : String {
            val codepoints = intArrayOf(codeP1, codeP2, codeP3)
            val s = String(codepoints, 0, codepoints.size)
            return s;
        }

    }
}