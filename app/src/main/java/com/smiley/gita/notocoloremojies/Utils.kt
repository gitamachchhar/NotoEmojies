package com.smiley.gita.notocoloremojies

import android.content.Context
import android.content.SharedPreferences
import java.lang.StringBuilder

class Utils {

    companion object {

        private const val PREF_EMOJI: String = "emoji_pref"
        private const val PREF_RECENTEMOJI: String = "recent_emoji_pref"

        fun getString(codeP1: Int): String {
            return String(Character.toChars(codeP1))
        }

        fun getString(codeP1: Int, codeP2: Int): String {
            val codePoints = intArrayOf(codeP1, codeP2)
            return String(codePoints, 0, codePoints.size)
        }

        fun getString(codeP1: Int, codeP2: Int, codeP3: Int): String {
            val codePoints = intArrayOf(codeP1, codeP2, codeP3)
            return String(codePoints, 0, codePoints.size)
        }

        fun getString(codeP1: Int, codeP2: Int, codeP3: Int, codeP4: Int): String {
            val codePoints = intArrayOf(codeP1, codeP2, codeP3, codeP4)
            return String(codePoints, 0, codePoints.size)
        }

        fun getString(codeP1: Int, codeP2: Int, codeP3: Int, codeP4: Int, codeP5: Int): String {
            val codePoints = intArrayOf(codeP1, codeP2, codeP3, codeP4, codeP5)
            return String(codePoints, 0, codePoints.size)
        }

        fun getString(codeP1: Int, codeP2: Int, codeP3: Int, codeP4: Int, codeP5: Int, codeP6: Int): String {
            val codePoints = intArrayOf(codeP1, codeP2, codeP3, codeP4, codeP5, codeP6)
            return String(codePoints, 0, codePoints.size)
        }

        fun getString(codeP1: Int, codeP2: Int, codeP3: Int, codeP4: Int, codeP5: Int, codeP6: Int, codeP7: Int): String {
            val codePoints = intArrayOf(codeP1, codeP2, codeP3, codeP4, codeP5, codeP6, codeP7)
            return String(codePoints, 0, codePoints.size)
        }

        fun setRecentEmoji(context: Context, codeP1: String) {

            val emojis : MutableList<String> = getRecentEmojis(context)

            if (!emojis.contains(codeP1)) {

                if (emojis.size >= 30) {
                    emojis.removeAt(0)
                }

                emojis.add(codeP1)
                getPref(context)?.edit()?.putString(PREF_RECENTEMOJI, arrayToString(emojis))?.apply()
            }
        }

        private fun arrayToString(emoji: MutableList<String>): String {

            val strEmoji = StringBuilder()

            for (i in 0 until emoji.size) {

                strEmoji.append(emoji[i])
                if( i < emoji.size - 1)
                    strEmoji.append(",")

            }
            return strEmoji.toString()

        }

        fun getRecentEmojis(context: Context): MutableList<String> {
            val emojiList = getPref(context)!!.getString(PREF_RECENTEMOJI, "").split(",").toMutableList()
            emojiList.remove("")
            return emojiList

        }

        private fun getPref(context: Context): SharedPreferences? {
            return context.getSharedPreferences(PREF_EMOJI, Context.MODE_PRIVATE)
        }
    }
}

