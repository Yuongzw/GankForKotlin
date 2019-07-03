package com.yuong.xiandu.debug

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.yuong.library_base.debug.ContainerActivity
import com.yuong.xiandu.ui.fragment.XianduFragment

class DebugActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ContainerActivity::class.java)
        intent.putExtra("fragment", XianduFragment::class.java.canonicalName)
        this.startActivity(intent)
        finish()
    }
}