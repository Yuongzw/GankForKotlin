package com.yuong.module_search.debug

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.yuong.module_search.ui.activity.SearchActivity

class DebugActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, SearchActivity::class.java)
        this.startActivity(intent)
        finish()
    }
}