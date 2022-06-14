package com.ameen.bosta.presentation.fragment.albumPhotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.ameen.bosta.R
import com.ameen.bosta.core.util.RECYCLER_SPAN_COUNT
import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.databinding.FragmentAlbumPhotosBinding
import com.ameen.bosta.domain.model.Photo
import com.ameen.bosta.presentation.adapter.AlbumPhotosAdapter
import com.ameen.bosta.presentation.listener.PhotoClickListener
import com.ameen.bosta.presentation.util.hide
import com.ameen.bosta.presentation.util.show
import com.ameen.bosta.presentation.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AlbumPhotosFragment : Fragment(), PhotoClickListener {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentAlbumPhotosBinding.inflate(layoutInflater)
    }

    private lateinit var recAdapter: AlbumPhotosAdapter
    private val albumPhotosViewModel: AlbumPhotosViewModel by viewModels()

    private lateinit var currentPhotosList: List<Photo>

    private val args: AlbumPhotosFragmentArgs by navArgs()
    private val albumDataArgs by lazy {
        args.albumData
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumPhotosViewModel.getAlbumPhotos(albumDataArgs.albumId)
        initObservers()
        searchInPhotos()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            albumPhotosViewModel.albumPhotoData.collect {
                when (it) {
                    is ResultWrapper.Loading -> binding.progressBar.show()
                    is ResultWrapper.Success -> initViews(albumDataArgs.albumTitle, it.value)
                    is ResultWrapper.Error -> {
                        binding.progressBar.hide()
                        requireContext().showToast(it.error)
                    }
                }
            }
        }
    }

    private fun initViews(albumTitle: String, photosList: List<Photo>) {
        binding.progressBar.hide()
        binding.albumTitleTextView.text = albumTitle
        initRecycler(photosList)
    }

    private fun initRecycler(photosList: List<Photo>) {

        currentPhotosList = photosList

        if (!this::recAdapter.isInitialized) {

            recAdapter = AlbumPhotosAdapter(this)
            recAdapter.diff.submitList(photosList)

            binding.albumPhotosRecycler.apply {
                adapter = recAdapter
                layoutManager =
                    GridLayoutManager(requireContext(), RECYCLER_SPAN_COUNT)
            }
        }
    }

    private fun searchInPhotos() {
        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            if (!text.isNullOrEmpty()) {
                val filteredList =
                    currentPhotosList.filter {
                        it.photoTitle.contains(text)
                    }

                //Log.e("Search", "searchInPhotos: $text, List: $filteredList")

                if (!filteredList.isNullOrEmpty()) {
                    binding.noImageHintTextView.hide()
                    recAdapter.diff.submitList(filteredList)
                } else {
                    recAdapter.diff.submitList(filteredList)
                    binding.noImageHintTextView.show()
                    binding.noImageHintTextView.text = getString(R.string.no_images_hint, text)
                }
            } else
                recAdapter.diff.submitList(currentPhotosList)
        }
    }

    override fun onPhotoSelectedListener(photoData: Photo) {
        val action =
            AlbumPhotosFragmentDirections.actionAlbumPhotosFragmentToPhotoDetailsFragment(photoData)
        findNavController().navigate(action)
    }
}