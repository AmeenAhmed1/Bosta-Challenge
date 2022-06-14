package com.ameen.bosta.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.ameen.bosta.databinding.ItemPhotoBinding
import com.ameen.bosta.presentation.listener.PhotoClickListener
import com.ameen.bosta.presentation.util.loadImageFromUrl

class AlbumPhotosAdapter(
    private val photoClickListener: PhotoClickListener
) : RecyclerView.Adapter<AlbumPhotosAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemPhotoBinding? = null

    val diff = AsyncListDiffer(this, AlbumPhotosDiffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPhoto = diff.currentList[position]
        holder.binding.albumImage.loadImageFromUrl(currentPhoto.photoUrl)

        holder.binding.albumImage.setOnClickListener {
            photoClickListener.onPhotoSelectedListener(currentPhoto)
        }
    }

    override fun getItemCount(): Int = diff.currentList.size
}