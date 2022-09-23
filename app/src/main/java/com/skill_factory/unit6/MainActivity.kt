package com.skill_factory.unit6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skill_factory.unit6.adapter.ProductAdapter
import com.skill_factory.unit6.adapter.ProductDiff
import com.skill_factory.unit6.model.Ad
import com.skill_factory.unit6.model.Download
import com.skill_factory.unit6.model.Item
import com.skill_factory.unit6.model.Product


class MainActivity : AppCompatActivity() {
    private var firstItem: Int = 0
    private var itemCount: Int = 6
    private val fullList = arrayListOf(
        Product(
            0,
            R.drawable.ic_apple,
            "Apple",
            "Juicy Apple fruit, which is eaten fresh, serves as a raw material in cooking and for making drinks."
        ),
        Ad("Акция", "Скидка на бананы 15%"),
        Product(
            1,
            R.drawable.ic_banana,
            "Banana",
            "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
        ),
        Product(
            2,
            R.drawable.ic_lemon,
            "Lemon",
            "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
        ),
        Product(
            3,
            R.drawable.ic_pear,
            "Pear",
            "Under favorable conditions, the pear reaches a large size-up to 5-25 meters in height and 5 meters in diameter of the crown."
        ),
        Product(
            4,
            R.drawable.ic_strawberry,
            "Strawberry",
            "A perennial herbaceous plant 5-20 cm high, with a thick brown rhizome. \"Mustache\" is short. The stem is thin."
        ),
        Product(
            5,
            R.drawable.ic_orange,
            "Orange",
            "Orange juice is widely used as a drink in restaurants and cafes."
        ),
        Product(
            6,
            R.drawable.ic_banana,
            "Banana",
            "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
        ),
        Product(
            7,
            R.drawable.ic_banana,
            "Banana",
            "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
        ),
        Product(
            8,
            R.drawable.ic_banana,
            "Banana",
            "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
        ),
        Product(
            9,
            R.drawable.ic_banana,
            "Banana",
            "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
        ),
        Product(
            10,
            R.drawable.ic_banana,
            "Banana",
            "It is one of the oldest food crops, and for tropical countries it is the most important food plant and the main export item."
        ),
        Product(
            11,
            R.drawable.ic_lemon,
            "Lemon",
            "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
        ),
        Product(
            12,
            R.drawable.ic_lemon,
            "Lemon",
            "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
        ),
        Product(
            13,
            R.drawable.ic_lemon,
            "Lemon",
            "Lemons are eaten fresh, and are also used in the manufacture of confectionery and soft drinks, in the liquor and perfume industry."
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapter = ProductAdapter()


        adapter.items = getPage(itemCount).toList()
        recyclerView.adapter = adapter

        fun changeData(newList: ArrayList<Item>) {
            val diff = ProductDiff(adapter.items as ArrayList<Item>, newList)
            val diffResult = DiffUtil.calculateDiff(diff)
            adapter.items = newList
            diffResult.dispatchUpdatesTo(adapter)
        }


        val scrollListener = object : RecyclerView.OnScrollListener() {
            var isLoading = false

            @Override
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as RecyclerView.LayoutManager
                //смотрим сколько элементов на экране
                val visibleItemCount: Int = layoutManager.childCount
                //сколько всего элементов
                val totalItemCount: Int = layoutManager.itemCount

                //какая позиция первого элемента
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()


                //проверяем, грузим мы что-то или нет
                if (!isLoading) {
                    if (visibleItemCount + firstVisibleItems >= totalItemCount) {
                        //ставим флаг, что мы попросили еще элементы
                        isLoading = true
                        //Вызывает загрузку данных в RecyclerView

                        changeData(getPage(itemCount))
                        recyclerView.smoothScrollToPosition(0)
                        isLoading = false
                    }
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    // из полного списки продуктов и рекламы формируем список для отображения в RV
    fun getPage(count: Int): ArrayList<Item> {
        val newList = ArrayList<Item>()
        // вычисляем до какого элемента из полного списка берем элементы
        var end = firstItem + count
        if (fullList.size < end)
            end = fullList.size
        // копируем с текущего первого по вычисленный последний элементы
        for (i in firstItem until end)
            newList.add(fullList[i])
        // добавляем 2 элемента индикации загрузки.
        // 2 потому что работает у меня не так как хотелось бы,
        // ... и при одном элементе загрузки его просто не видно когда скроллишь вниз
        newList.add(Download(getString(R.string.download_text)))
        newList.add(Download(getString(R.string.download_text)))
        // вычисляем новый первый элемент, который еще не был показан
        firstItem = if (end >= fullList.size)
            0
        else
            end
        return newList
    }


}