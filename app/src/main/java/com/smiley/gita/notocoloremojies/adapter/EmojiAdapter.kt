package com.smiley.gita.notocoloremojies.adapter

import android.database.DataSetObserver
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import com.smiley.gita.notocoloremojies.R
import kotlinx.android.synthetic.main.emoji_holder.view.*

class EmojiAdapter(val items: ArrayList<String>?, val context: FragmentActivity?) :ListAdapter {

    override fun isEmpty(): Boolean {
      return false
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {

    }

    override fun getItemViewType(position: Int): Int {
       return position;
    }

    override fun getViewTypeCount(): Int {
       return 1;
    }

    override fun isEnabled(position: Int): Boolean {
       return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun areAllItemsEnabled(): Boolean {
        return true
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {

    }

    override fun getCount(): Int {
        return items!!.size
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItem(position: Int): Any {
        return items!!.get(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = LayoutInflater.from(context).inflate(R.layout.emoji_holder, parent, false)
        val tvEmojiView = v.emoji_text_view
        tvEmojiView.text = items?.get(position)
        return  v;
    }


}
