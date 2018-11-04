package com.smiley.gita.notocoloremojies.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.smiley.gita.notocoloremojies.fragments.EmojiFragment

class EmojiPagerAdapter(fragmentManager: FragmentManager, private val emojiFragmentList: ArrayList<EmojiFragment>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return emojiFragmentList[position]
    }

    override fun getCount(): Int {
        return emojiFragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }

}