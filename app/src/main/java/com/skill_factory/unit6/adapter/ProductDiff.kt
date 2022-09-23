package com.skill_factory.unit6.adapter

import androidx.recyclerview.widget.DiffUtil
import com.skill_factory.unit6.model.Ad
import com.skill_factory.unit6.model.Download
import com.skill_factory.unit6.model.Item
import com.skill_factory.unit6.model.Product

class ProductDiff(val oldList: ArrayList <Item>, val newList: ArrayList <Item> ): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    //Элементы одинаковые
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].javaClass == newList[newItemPosition].javaClass
    }

    //Содержимое одинаковое
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        if (oldItem is Product && newItem is Product)
            return oldItem.name == newItem.name &&
                    oldItem.desc == newItem.desc &&
                    oldItem.idIcon == newItem.idIcon
        if (oldItem is Ad && newItem is Ad)
            return oldItem.title == newItem.title &&
                    oldItem.content == newItem.content
        if (oldItem is Download && newItem is Download)
            return oldItem.downloadText == newItem.downloadText
        return false
    }
}