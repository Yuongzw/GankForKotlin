package com.yuong.library_base.debug

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.yuong.library_base.R
import com.yuong.library_base.base.BaseFragment
import java.lang.ref.WeakReference

class ContainerActivity : AppCompatActivity() {
    protected lateinit var mFragment: WeakReference<Fragment>
    private lateinit var mianLayout: ViewGroup

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(32)
        super.onCreate(savedInstanceState)
        this.mianLayout = LinearLayout(this)
        this.mianLayout.layoutParams = LinearLayout.LayoutParams(-1, -1)
        this.mianLayout.id = View.generateViewId()
        this.setContentView(this.mianLayout)
        val fm = this.supportFragmentManager
        val fragment = fm.findFragmentById(this.mianLayout.id)
        if (fragment == null) {
            this.initFromIntent(this.intent)
        }
    }

    protected fun initFromIntent(data: Intent?) {
        if (data == null) {
            throw RuntimeException("you must provide a page info to display")
        } else {
            try {
                val fragmentName = data.getStringExtra("fragment")
                if (fragmentName == null || "" == fragmentName) {
                    throw IllegalArgumentException("can not find page fragmentName")
                }

                val fragmentClass = Class.forName(fragmentName)
                val fragment = fragmentClass.newInstance() as BaseFragment<*, *>
                val args = data.getBundleExtra("bundle")
                if (args != null) {
                    fragment.setArguments(args)
                }

                val trans = this.supportFragmentManager.beginTransaction()
                trans.replace(this.mianLayout.id, fragment)
                trans.commitAllowingStateLoss()
                this.mFragment = WeakReference(fragment)
            } catch (var7: ClassNotFoundException) {
                var7.printStackTrace()
            } catch (var8: InstantiationException) {
                var8.printStackTrace()
            } catch (var9: IllegalAccessException) {
                var9.printStackTrace()
            }

        }
    }

//    override fun startActivity(intent: Intent?) {
//        super.startActivity(intent)
//        overridePendingTransition(R.anim.slide_bottom_in, R.anim.silde_bottom_out)
//    }
}