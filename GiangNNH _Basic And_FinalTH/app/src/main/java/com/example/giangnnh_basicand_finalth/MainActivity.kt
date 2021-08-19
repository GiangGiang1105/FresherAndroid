package com.example.giangnnh_basicand_finalth

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.giangnnh_basicand_finalth.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var listApplicationInfo: List<ApplicationInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        GlobalScope.launch(Dispatchers.IO) {
            getPackagesAndUpdateUI()
            withContext(Dispatchers.Main) {
                initView()
            }
        }

        setContentView(mainBinding.root)
    }

    private fun initView() {
        mainBinding.applicationGridView.apply {
            adapter = GridAdapter(context = context, listApplication = listApplicationInfo)
            setOnItemClickListener { parent, view, position, id ->
                startApplication(position)
            }
        }
        nextGridView()
        prevGridView()
    }

    private fun nextGridView() {
        mainBinding.applicationGridView.smoothScrollByOffset(0)
    }

    private fun prevGridView() {
        mainBinding.applicationGridView.smoothScrollByOffset(listApplicationInfo.size)
    }


    @SuppressLint("QueryPermissionsNeeded")
    private fun getPackagesAndUpdateUI() {
        listApplicationInfo = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        Log.e("Tag", listApplicationInfo.toString())
    }

    private fun startApplication(index: Int) {
        val launchIntent: Intent? = packageManager.getLaunchIntentForPackage(
            listApplicationInfo[index].loadLabel(packageManager).toString()
        )
        launchIntent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(launchIntent)
    }
}