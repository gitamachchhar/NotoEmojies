package com.smiley.gita.notocoloremojies.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import com.smiley.gita.notocoloremojies.R
import com.smiley.gita.notocoloremojies.adapter.EmojiAdapter

class EmojiFragment : Fragment(), AdapterView.OnItemClickListener {

    private var emojiList: ArrayList<String>? = null
    private var mOnEmojiconClickedListener: OnEmojiconClickedListener? = null
    private var emojiAdapter: EmojiAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_emoji, container, false)
        val args = arguments

        emojiList = args?.getStringArrayList("data")

        val gridView = view.findViewById<GridView>(R.id.grid_emoji)
        emojiAdapter = EmojiAdapter(emojiList, activity)
        gridView.adapter = emojiAdapter
        gridView.onItemClickListener = this
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnEmojiconClickedListener) {
            mOnEmojiconClickedListener = context
        }
    }

    override fun onDetach() {
        mOnEmojiconClickedListener = null
        super.onDetach()
    }

    fun newInstance(emoji: ArrayList<String>, tag: String): EmojiFragment {
        val args = Bundle()
        args.putStringArrayList("data", emoji)
        val fragment = EmojiFragment()
        fragment.arguments = args
        return fragment
    }

    interface OnEmojiconClickedListener {
        fun onEmojiconClicked(emoji: String)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        mOnEmojiconClickedListener?.onEmojiconClicked(emojiList!![position])
    }

    fun setNewEmoji(emoji: ArrayList<String>) {
        emojiAdapter?.addEmojis(emoji)

    }

}