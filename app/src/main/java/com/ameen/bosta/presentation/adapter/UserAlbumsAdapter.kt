package com.ameen.bosta.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.ameen.bosta.data.model.AlbumResponse
import com.ameen.bosta.databinding.ItemAlbumBinding

class UserAlbumsAdapter :
    RecyclerView.Adapter<UserAlbumsAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemAlbumBinding? = null

    val diff = AsyncListDiffer(this, AlbumDiffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentAlbum = diff.currentList[position]
        holder.binding.albumTitleTextView.text = currentAlbum.albumTitle
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onItemClickListener: ((AlbumResponse) -> Unit)? = null
    fun onItemClicked(listener: (AlbumResponse) -> Unit) {
        onItemClickListener = listener
    }
}