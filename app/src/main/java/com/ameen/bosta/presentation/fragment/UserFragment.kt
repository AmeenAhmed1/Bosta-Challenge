package com.ameen.bosta.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.bosta.core.wrapper.ResultWrapper
import com.ameen.bosta.presentation.util.hide
import com.ameen.bosta.presentation.util.show
import com.ameen.bosta.databinding.FragmentUserBinding
import com.ameen.bosta.domain.model.Album
import com.ameen.bosta.domain.model.User
import com.ameen.bosta.presentation.adapter.UserAlbumsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UserFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentUserBinding.inflate(layoutInflater)
    }

    private lateinit var recAdapter: UserAlbumsAdapter
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        userViewModel.getUser()
        lifecycleScope.launchWhenCreated {
            userViewModel.userData.collect {
                when (it) {
                    is ResultWrapper.Loading -> binding.progressBar.show()
                    is ResultWrapper.Success -> initViews(it.value)
                    is ResultWrapper.Error -> {
                        binding.progressBar.hide()
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        lifecycleScope.launchWhenCreated {
            userViewModel.userAlbum.collect {
                when (it) {
                    is ResultWrapper.Loading -> binding.progressBar.show()
                    is ResultWrapper.Success -> initRecycler(it.value)
                    is ResultWrapper.Error -> {
                        binding.progressBar.hide()
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initViews(userData: User) {
        binding.progressBar.hide()
        binding.apply {
            userNameTextView.text = userData.userName
            userAddressTextView.text = userData.userAddress
        }
    }

    private fun initRecycler(albums: List<Album>) {

        if (!this::recAdapter.isInitialized) {

            recAdapter = UserAlbumsAdapter()
            recAdapter.diff.submitList(albums)

            binding.albumsRecycler.apply {
                adapter = recAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

            binding.albumsRecycler.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

            recAdapter.onItemClicked {
                TODO("Selected Item Go To Details.")
            }
        }
    }
}