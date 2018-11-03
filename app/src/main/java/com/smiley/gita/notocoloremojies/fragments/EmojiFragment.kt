package com.smiley.gita.notocoloremojies.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import com.smiley.gita.notocoloremojies.R
import com.smiley.gita.notocoloremojies.adapter.EmojiAdapter

class EmojiFragment: Fragment() {

    var emojiList: ArrayList<String>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_emoji, container, false)
        val args = arguments

        emojiList = args?.getStringArrayList("data")

        val gridView = view.findViewById<GridView>(R.id.grid_emoji)

        gridView.adapter = EmojiAdapter(emojiList, activity)


        gridView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->

            Log.e("system out", "Value selected " + emojiList?.get(position));

        })

        return view
    }

    companion object {
        fun newInstance(emoji: ArrayList<String>): EmojiFragment {
            val args = Bundle()
            args.putStringArrayList("data", emoji)
            val fragment = EmojiFragment()
            fragment.arguments = args
            return fragment
        }
    }
}