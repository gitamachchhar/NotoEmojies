package com.smiley.gita.notocoloremojies.adapter

import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.smiley.gita.notocoloremojies.R
import kotlinx.android.synthetic.main.emoji_holder.view.*

class EmojiAdapter(items: ArrayList<String>?, val context: FragmentActivity?): BaseAdapter() {

    var emoji: ArrayList<String>? = items

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = LayoutInflater.from(context).inflate(R.layout.emoji_holder, parent, false)
        val tvEmojiView = v.emoji_text_view
        tvEmojiView.text = emoji?.get(position)
        return v;
    }

    fun addEmojis(item: ArrayList<String>?){
        emoji = item
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any {
        return emoji!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
      return emoji!!.size
    }

}
