package com.seven.kotlintest.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.seven.kotlintest.widget.LoadMoreView

abstract class BaseListAdapter<ITEMBEAN, ITEMVIEW : View> : RecyclerView.Adapter<BaseListAdapter.BaseListHolder>() {
    var list = ArrayList<ITEMBEAN>()

    fun updateList(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun loadMore(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    private var itemClickListener: ItemClickListener? = null

    //自定义接口
    interface ItemClickListener {
        fun onItemClickListener(position: Int)
    }

    fun ItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            1
        } else {
            0
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(p0: BaseListHolder, p1: Int) {
        if (p1 == list.size) return
        val data = list[p1]
        val itemView = p0.itemView as ITEMVIEW
        //刷新条目
        // 点击事件
        p0.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(p1)
        }
        refreshItemView(itemView, data)
    }

    //刷新条目View
    abstract fun refreshItemView(itemView: ITEMVIEW, data: ITEMBEAN)


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): BaseListHolder {
        if (viewType == 1) {
            return BaseListHolder(LoadMoreView(p0.context))
        } else {
            return BaseListHolder(getItemView(p0.context))
        }

    }

    //获取条目view
    abstract fun getItemView(context: Context?): ITEMVIEW

    class BaseListHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}