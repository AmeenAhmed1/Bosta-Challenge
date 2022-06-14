package com.ameen.bosta.presentation.fragment.photoDetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ameen.bosta.databinding.FragmentImageViewerBinding
import com.ameen.bosta.presentation.util.loadImageFromUrl

class PhotoDetailsFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentImageViewerBinding.inflate(layoutInflater)
    }

    private val args: PhotoDetailsFragmentArgs by navArgs()
    private val photoDataArgs by lazy {
        args.selectedPhotoData
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

        initViews()
    }

    private fun initViews() {
        binding.photoTitleTextView.text = photoDataArgs.photoTitle
        binding.photoView.loadImageFromUrl(photoDataArgs.photoUrl)

        binding.sharePhotoButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, photoDataArgs.photoUrl)
                type = "text/plain"
            }

            activity?.let {
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}