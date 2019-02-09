package com.thgcode.kotlinrv.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thgcode.kotlinrv.R
import com.thgcode.kotlinrv.api.Repository
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryAdapter(
    private val items: List<Repository>,
    private val context: Context,
    private val listener: (Repository) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.repository_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(
            item: Repository,
            listener: (Repository) -> Unit
        ) = with(itemView) {
            //            ivMain.setImageDrawable(ContextCompat.getDrawable(context, item.imageResourceId))
            tvTitle.text = item.full_name ?: ""
            tvOwner.text = item.owner?.login ?: ""
            tvDescription.text = item.description ?: ""

            setOnClickListener { listener(item) }
        }

    }
}

