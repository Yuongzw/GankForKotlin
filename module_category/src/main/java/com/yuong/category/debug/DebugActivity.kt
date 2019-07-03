package com.yuong.category.debug

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.yuong.category.ui.fragment.CategoryFragment
import com.yuong.library_base.debug.ContainerActivity

class DebugActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ContainerActivity::class.java)
        intent.putExtra("fragment", CategoryFragment::class.java.canonicalName)
        this.startActivity(intent)
        finish()
    }
}