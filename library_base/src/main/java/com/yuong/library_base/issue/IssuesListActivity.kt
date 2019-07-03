/*
 * Tencent is pleased to support the open source community by making wechat-matrix available.
 * Copyright (C) 2018 THL A29 Limited, a Tencent company. All rights reserved.
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuong.library_base.issue

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.tencent.matrix.report.Issue
import com.yuong.library_base.R

import org.json.JSONException

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.Date
import java.util.HashMap

class IssuesListActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_issue_list)

        setTitle(IssueFilter.currentFilter)
        initRecyclerView()

        if (currToastCount < toastCount) {
            currToastCount++
            Toast.makeText(this, "click view to hide or show issue detail", Toast.LENGTH_LONG).show()
        }


    }

    fun initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = Adapter(this)
    }

    class Adapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        internal var context: WeakReference<Context>

        init {
            this.context = WeakReference(context)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context.get()).inflate(R.layout.item_issue_list, parent, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindPosition(position)
            val issue = IssuesMap.get(IssueFilter.currentFilter)!![position]
            holder.bind(issue)

            holder.itemView.setOnClickListener {
                if (!holder.isShow)
                    holder.showIssue(issue)
                else
                    holder.hideIssue()
            }
        }

        override fun getItemCount(): Int {
            return IssuesMap.count
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvTime: TextView
        internal var tvTag: TextView
        internal var tvKey: TextView
        internal var tvType: TextView
        internal var tvContent: TextView
        internal var tvIndex: TextView

        internal var position: Int = 0

        public var isShow = true

        init {
            tvTime = itemView.findViewById(R.id.item_time) as TextView
            tvTag = itemView.findViewById(R.id.item_tag) as TextView
            tvKey = itemView.findViewById(R.id.item_key) as TextView
            tvType = itemView.findViewById(R.id.item_type) as TextView
            tvContent = itemView.findViewById(R.id.item_content) as TextView
            tvIndex = itemView.findViewById(R.id.item_index) as TextView
        }

        fun bindPosition(position: Int) {
            this.position = position
        }

        fun bind(issue: Issue) {

            val simpleDateFormat = SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss:SSS")
            val date = Date(java.lang.Long.parseLong(issue.content.optString("time")))
            tvTime.text = "IssueTime -> " + simpleDateFormat.format(date)

            if (TextUtils.isEmpty(issue.tag))
                tvTag.visibility = View.GONE
            else
                tvTag.text = "TAG -> " + issue.tag

            if (TextUtils.isEmpty(issue.key))
                tvKey.visibility = View.GONE
            else
                tvKey.text = "KEY -> " + issue.key

            if (issue.type == null)
                tvType.visibility = View.GONE
            else
                tvType.text = "TYPE -> " + issue.type!!
            tvIndex.text = (IssuesMap.count - position).toString()
            tvIndex.setTextColor(getColor(position))
            if (isShow)
                showIssue(issue)
            else
                hideIssue()
        }

        fun readMappingFile(methoMap: MutableMap<Int, String>) {
            var reader: BufferedReader? = null
            var tempString: String? = null
            try {
                reader = BufferedReader(FileReader(methodFilePath))
                var tempString = reader.readLine()

                while (tempString != null) {
                    tempString = reader.readLine()
                    val contents = tempString!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    methoMap[Integer.parseInt(contents[0])] = contents[2].replace('\n', ' ')
                }
                reader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (e1: IOException) {
                    }

                }
            }
        }


        fun showIssue(issue: Issue) {
            val key = "stack"
            if (issue.content.has(key)) {
                try {
                    val stack = issue.content.getString(key)
                    val map = HashMap<Int, String>()
                    readMappingFile(map)

                    if (map.size > 0) {
                        val stringBuilder = StringBuilder(" ")

                        val lines = stack.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        for (line in lines) {
                            val args = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                            val method = Integer.parseInt(args[1])
                            val isContainKey = map.containsKey(method)
                            if (!isContainKey) {
                                print("error!!!")
                                continue
                            }

                            args[1] = map[method]!!
                            stringBuilder.append(args[0])
                            stringBuilder.append(",")
                            stringBuilder.append(args[1])
                            stringBuilder.append(",")
                            stringBuilder.append(args[2])
                            stringBuilder.append(",")
                            stringBuilder.append(args[3] + "\n")
                        }

                        issue.content.remove(key)
                        issue.content.put(key, stringBuilder.toString())
                    }

                } catch (ex: JSONException) {
                    println(ex.message)
                }

            }

            tvContent.text = ParseIssueUtil.parseIssue(issue, true)
            tvContent.visibility = View.VISIBLE
            isShow = true
        }

        fun hideIssue() {
            tvContent.visibility = View.GONE
            isShow = false
        }

        fun getColor(index: Int): Int {
            when (index) {
                0 -> return Color.RED
                1 -> return Color.GREEN
                2 -> return Color.BLUE
                else -> return Color.GRAY
            }
        }
    }

    companion object {

        private val toastCount = 3
        private var currToastCount = 0
        private val methodFilePath = File(Environment.getExternalStorageDirectory(), "Debug.methodmap")
    }
}
