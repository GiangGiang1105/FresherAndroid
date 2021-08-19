package com.example.exercise2

import android.app.IntentService
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.exercise2.utils.Constant
import java.io.*
import java.net.URL
import java.util.*

@Suppress("DEPRECATION")
class DownloadService : IntentService("DownloadService") {

    override fun onHandleIntent(intent: Intent?) {
        val url_image = intent?.getStringExtra(Constant.URL)
        if (url_image?.isNotEmpty() == true) {
            sendProcessToActivity(Constant.PROCESS_START)
            val url = URL(url_image)
            val httpConnection = url.openConnection()
            httpConnection.connect()

            try {
                val inputStream: InputStream = httpConnection.getInputStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)

                if (bitmap != null) {
                    val uri = saveImageToInternalStorage(bitmap)
                    sendBitMapToActivity(UriBitMap(uri))
                    sendProcessToActivity(Constant.PROCESS_DONE)
                } else{
                    sendMessageToActivity(ERROR_NULL)
                }
                inputStream.close()

            } catch (e: IOException) {
                sendMessageToActivity(ERROR_EXCEPTION)
            }
        }
        stopSelf()
    }

    private fun sendMessageToActivity(message: String) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().apply {
            action = Constant.ACTION_DOWNLOAD
            putExtra(Constant.EXTRA_ERROR_DOWNLOAD, message)
        })
    }

    private fun sendProcessToActivity(process: String) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().apply {
            action = Constant.ACTION_DOWNLOAD
            putExtra(Constant.EXTRA_PROCESS, process)
        })
    }

    private fun sendBitMapToActivity(bitmap: UriBitMap) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().apply {
            action = Constant.ACTION_DOWNLOAD
            putExtra(Constant.EXTRAS_BITMAP, bitmap)
        })
    }

    private fun saveImageToInternalStorage(bitmap: android.graphics.Bitmap): Uri {
        val wrapper = ContextWrapper(applicationContext)
        val fileName = UUID.randomUUID().toString() + ".jpg"
        val path = wrapper.getDir("Images", MODE_PRIVATE)
        val file = File(path, fileName)
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {

        }
        return Uri.parse(file.absolutePath)
    }

    companion object {
        private const val ERROR_NULL = "Can't download image this! Because image is null!"
        private const val ERROR_EXCEPTION = "Server is error! "
    }
}

