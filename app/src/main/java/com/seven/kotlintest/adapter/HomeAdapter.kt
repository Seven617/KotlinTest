package com.seven.kotlintest.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.seven.kotlintest.widget.HomeItemView


class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(p0: HomeHolder, p1: Int) {

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeHolder {
        return HomeHolder(HomeItemView(p0?.context))
    }

    class HomeHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}