package com.smiley.gita.notocoloremojies.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.smiley.gita.notocoloremojies.fragments.EmojiFragment

class EmojiPagerAdapter(fragmentManager: FragmentManager, private val emojis: ArrayList<String>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return EmojiFragment.newInstance(emojis)
    }

    override fun getCount(): Int {
        return emojis.size
    }

}