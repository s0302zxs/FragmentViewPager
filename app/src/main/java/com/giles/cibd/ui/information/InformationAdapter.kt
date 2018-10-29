package com.giles.cibd.ui.information

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.giles.cibd.data.model.Repo
import com.giles.cibd.databinding.RepoItemBinding

class InformationAdapter constructor(private val items: MutableList<Repo>?) : PagedListAdapter<Repo, RecyclerView.ViewHolder>(RepoDiffCallback) {

    inner class InformationViewHolder(private val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo) {
            binding.repo = repo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
        return InformationViewHolder(binding)
    }

//    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
//        val repo = items!![position]
//        holder.bind(repo)
//    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repo = items!![position]
        (holder as InformationViewHolder).bind(repo)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

//    fun swapItems(newItems: List<Repo>?) {
//        if (newItems == null) {
//            val oldSize = this.items!!.size
//            this.items.clear()
//            notifyItemRangeRemoved(0, oldSize)
//        } else {
//            val result = DiffUtil.calculateDiff(RepoDiffCallback(this.items, newItems))
//            this.items!!.clear()
//            this.items.addAll(newItems)
//            result.dispatchUpdatesTo(this)
//        }
//    }

    companion object {
        val RepoDiffCallback = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }



//    private inner class RepoDiffCallback internal constructor(private val mOldList: List<Repo>?, private val mNewList: List<Repo>?) : DiffUtil.Callback() {
//
//        override fun getOldListSize(): Int {
//            return mOldList?.size ?: 0
//        }
//
//        override fun getNewListSize(): Int {
//            return mNewList?.size ?: 0
//        }
//
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            val oldId = mOldList!![oldItemPosition].id
//            val newId = mNewList!![newItemPosition].id
//            return oldId == newId
//        }
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//            val oldRepo = mOldList!![oldItemPosition]
//            val newRepo = mNewList!![newItemPosition]
//            return oldRepo == newRepo
//        }
//    }
}