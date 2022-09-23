package com.skill_factory.unit6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skill_factory.unit6.R
import com.skill_factory.unit6.model.Download
import com.skill_factory.unit6.model.Item

class DownloadDelegateAdapter :
    AbsListItemAdapterDelegate<Download, Item, DownloadDelegateAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textDownload = itemView.findViewById<TextView>(R.id.download_text)
    }

    override fun isForViewType(item: Item, items: MutableList<Item>, position: Int): Boolean {
        return item is Download
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_download, parent, false)
        )
    }

    override fun onBindViewHolder(item: Download, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.textDownload.text = item.downloadText
    }

}