package com.yuong.welfare.ui

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yuong.welfare.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity: AppCompatActivity() {
    private var tv_title: TextView? = null
    private var iv_back: ImageView? = null
    private var iv_search: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT
            //            getWindow().setNavigationBarColor(Color.BLACK);
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        tv_title = findViewById(R.id.tv_title)
        iv_back = findViewById(R.id.iv_back)
        iv_search = findViewById(R.id.iv_search)
        iv_search!!.visibility = View.GONE
        iv_back!!.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        val url = intent.getStringExtra("url")
        val date = intent.getStringExtra("date")
        tv_title!!.text = date.substring(0, 10)
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.default_project_img)
            .error(R.drawable.default_project_img)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(welfare_Image)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_bottom_in, R.anim.silde_bottom_out)
    }

}