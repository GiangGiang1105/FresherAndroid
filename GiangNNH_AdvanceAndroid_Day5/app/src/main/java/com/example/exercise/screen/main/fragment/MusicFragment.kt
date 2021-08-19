package com.example.exercise.screen.main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.exercise.R
import com.example.exercise.data.model.Music
import com.example.exercise.utils.Resource
import com.example.exercise.databinding.FragmentMusicBinding
import com.example.exercise.screen.main.MainActivity
import com.example.exercise.screen.main.adapter.MusicAdapter
import com.example.exercise.service.MusicService
import com.example.exercise.service.media.MediaManager

class MusicFragment : Fragment() {

    private lateinit var fragmentMusicBinding: FragmentMusicBinding
    private val musicAdapter by lazy {
        MusicAdapter()
    }
    private val mediaManager by lazy {
        MediaManager.getInstance(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMusicBinding = FragmentMusicBinding.inflate(inflater)
        initView()
        handleDataFromNetWork()
        return fragmentMusicBinding.root
    }

    private fun initView() {
        fragmentMusicBinding.musicRecyclerView.apply {
            setHasFixedSize(false)
            adapter = musicAdapter
        }
        musicAdapter.setOnItemClickListener { music ->
            onClickItemRecyclerViewListener(music)
        }
    }

    private fun handleDataFromNetWork() {
        (activity as MainActivity).viewModel.listMusicLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    musicAdapter.mDiffer.submitList(it.data)
                    mediaManager.updateListMusics(it.data)
                }
                is Resource.Error -> {
                    if (it.isNetworkError) showToast(getString(R.string.error_network))
                    if (it.errorCode != null) showToast(getString(R.string.error_code + it.errorCode))
                    if (it.response != null) showToast(it.response.toString())
                }
            }

        })
    }

    private fun onClickItemRecyclerViewListener(music: Music) {
        context?.startService(Intent(context, MusicService::class.java))
        mediaManager._indexListMusics = musicAdapter.mDiffer.currentList.indexOf(music)
        mediaManager.playASong(true)
    }

    private fun showToast(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}