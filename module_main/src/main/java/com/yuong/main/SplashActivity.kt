package com.yuong.main

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.yuong.main.ui.activity.Main2Activity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: AppCompatActivity() {

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
        setContentView(R.layout.activity_splash)
        countDownTimer.start()
        btn_jump.setOnClickListener {
            gotoMain()
        }
    }

    @SuppressLint("SetTextI18n")
    private var countDownTimer = object :CountDownTimer(4000, 1000){
        override fun onTick(p0: Long) {
            btn_jump.text = "跳过(" + p0 / 1000 + "s)"
        }

        override fun onFinish() {
            btn_jump.text = "跳过(0s)"
            gotoMain()
        }
    }

    private fun gotoMain() {
        val intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }


}