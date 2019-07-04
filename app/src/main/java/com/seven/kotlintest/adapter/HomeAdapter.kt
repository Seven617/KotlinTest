package com.seven.kotlintest.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.seven.kotlintest.model.Data
import com.seven.kotlintest.widget.HomeItemView
import com.seven.kotlintest.widget.LoadMoreView


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private var list = ArrayList<Data>()

    fun updateList(list: List<Data>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun loadMore(list: List<Data>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size) {
            return 1
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(p0: HomeHolder, p1: Int) {
        if(p1==list.size)return
        val data = list.get(p1)
        val itemView = p0.itemView as HomeItemView
        itemView.setData(data)
    }


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): HomeHolder {
        if (viewType == 1) {
            return HomeHolder(LoadMoreView(p0.context))
        } else {
            return HomeHolder(HomeItemView(p0.context))
        }

    }

    class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}