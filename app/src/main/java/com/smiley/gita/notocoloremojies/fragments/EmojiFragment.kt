package com.smiley.gita.notocoloremojies.fragments

import android.content.Context
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
import com.smiley.gita.notocoloremojies.emojies.RecentUsed

class EmojiFragment : Fragment(), AdapterView.OnItemClickListener {

    var emojiList: ArrayList<String>? = null
    private var mOnEmojiconClickedListener: OnEmojiconClickedListener? = null
    private var mRecents: RecentUsed = RecentUsed()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_list_emoji, container, false)
        val args = arguments

        emojiList = args?.getStringArrayList("data")

        val gridView = view.findViewById<GridView>(R.id.grid_emoji)
        gridView.setPadding(-5, 0, -5, 0);
        gridView.adapter = EmojiAdapter(emojiList, activity)
        gridView.onItemClickListener = this
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnEmojiconClickedListener) {
            mOnEmojiconClickedListener = context as OnEmojiconClickedListener?
        } else if (parentFragment is OnEmojiconClickedListener) {
            mOnEmojiconClickedListener = parentFragment as OnEmojiconClickedListener?
        } else {
            throw IllegalArgumentException(context.toString() + " must implement interface " + OnEmojiconClickedListener::class.java.simpleName)
        }
    }

    override fun onDetach() {
        mOnEmojiconClickedListener = null
        super.onDetach()
    }

    fun newInstance(emoji: ArrayList<String>): EmojiFragment {
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
        mRecents.addRecentEmoji(emojiList!![position])
    }

}