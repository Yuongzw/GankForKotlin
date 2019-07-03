package com.yuong.library_base

import android.app.Application
import android.content.Context
import com.tencent.matrix.plugin.DefaultPluginListener
import android.content.Intent
import com.tencent.matrix.util.MatrixLog
import com.tencent.matrix.report.Issue
import com.yuong.library_base.issue.IssueFilter
import com.yuong.library_base.issue.IssuesListActivity
import com.yuong.library_base.issue.IssuesMap
import java.lang.ref.SoftReference


class TestPluginListener(context: Context): DefaultPluginListener(context) {

    val TAG = "TestPluginListener"
    var softReference: SoftReference<Context>
    init {
        softReference = SoftReference<Context>(context)
    }


    override fun onReportIssue(issue: Issue) {
        super.onReportIssue(issue)
        MatrixLog.e(TAG, issue.toString())

        IssuesMap.put(IssueFilter.currentFilter, issue)
        jumpToIssueActivity()
    }

    fun jumpToIssueActivity() {
        val context = softReference.get()
        val intent = Intent(context, IssuesListActivity::class.java)

        if (context is Application) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context!!.startActivity(intent)
    }
}