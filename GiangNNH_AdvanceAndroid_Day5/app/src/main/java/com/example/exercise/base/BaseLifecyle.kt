package com.example.exercise.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.exercise.service.media.MediaManager

class BaseLifecyle(private val mediaManager: MediaManager) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun playMediaSong() {
        mediaManager.playASong(true)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopMediaSong() {
        mediaManager.release()
    }
}