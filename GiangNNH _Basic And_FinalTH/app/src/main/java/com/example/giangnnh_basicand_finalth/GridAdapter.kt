package com.example.giangnnh_basicand_finalth

import android.content.Context
import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.giangnnh_basicand_finalth.databinding.ItemGridviewBinding

class GridAdapter(private val context: Context,private val  listApplication: List<ApplicationInfo>) : BaseAdapter() {

    override fun getCount(): Int = listApplication.size

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemgridViewBinding: ItemGridviewBinding
        var view: View
        if (convertView == null) {
            itemgridViewBinding = ItemGridviewBinding.inflate(LayoutInflater.from(context), parent, false)
            if (listApplication[position].loadIcon(context.packageManager) != null)
                itemgridViewBinding.applicationImage.setImageDrawable(
                    listApplication[position].loadIcon(
                        context.packageManager
                    )
                )
            else itemgridViewBinding.applicationImage.setImageDrawable(context.getDrawable(R.drawable.ic_launcher_background))
            itemgridViewBinding.applicationText.text =
                listApplication[position].loadLabel(context.packageManager)
            view = itemgridViewBinding.root
        } else view = convertView
        return view
    }
}