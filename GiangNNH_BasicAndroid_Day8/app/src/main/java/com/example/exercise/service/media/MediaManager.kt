package com.example.exercise.service.media

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer

import com.example.exercise.data.model.Music
import com.example.exercise.utils.Constants

class MediaManager private constructor(private val context: Context?) {

    init {
        initMediaPlayer()
    }

    private lateinit var _mediaPlayer: MediaPlayer

    val mediaPlayer: MediaPlayer
        get() = _mediaPlayer

    private var listMusics: MutableList<Music> = mutableListOf()
    private var mediaState = Constants.MEDIA_IDLE
    var _indexListMusics = 0

    fun updateListMusics(list: List<Music>?) {
        list?.let {
            listMusics.clear()
            listMusics.addAll(it)
        }
    }

    private fun initMediaPlayer() {
        _mediaPlayer = MediaPlayer()
        _mediaPlayer.setOnCompletionListener {
            nextASong()
        }
    }

    fun playASong(statePlay: Boolean) {
        if (statePlay || mediaState == Constants.MEDIA_IDLE || mediaState == Constants.MEDIA_STOP) {
            context?.let { context ->
                Intent(Constants.ACTION_SEND_DATA).apply {
                    putExtra(Constants.KEY_TITLE_SONG, listMusics[_indexListMusics].title)
                    putExtra(Constants.KEY_IMAGE_SONG, listMusics[_indexListMusics].image)
                    context.sendBroadcast(this)
                }
            }
            _mediaPlayer.apply {
                reset()
                setAudioAttributes(
                    AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA).build()
                )
                setDataSource(listMusics[_indexListMusics].source)
                prepare()
                start()
            }

            mediaState = Constants.MEDIA_PLAYING
        } else if (mediaState == Constants.MEDIA_PAUSE) {
            _mediaPlayer.start()
            mediaState = Constants.MEDIA_PLAYING
        } else if (mediaState == Constants.MEDIA_PLAYING) {
            _mediaPlayer.pause()
            mediaState = Constants.MEDIA_PAUSE
        }
    }

    fun nextASong() {
        if (_indexListMusics == listMusics.size - 1) _indexListMusics = 0
        else _indexListMusics++
        playASong(true)
    }

    fun prevASong() {
        if (_indexListMusics == 0) _indexListMusics = listMusics.size - 1
        else _indexListMusics--
        playASong(true)
    }

    fun stop() {
        if (mediaState == Constants.MEDIA_IDLE) return
        _mediaPlayer.stop()
        mediaState = Constants.MEDIA_STOP
    }

    fun release() {
        _mediaPlayer.release()
    }

    companion object {
        private var instance: MediaManager? = null

        fun getInstance(context: Context?) = instance ?: MediaManager(context).also {
            instance = it
        }
    }
}