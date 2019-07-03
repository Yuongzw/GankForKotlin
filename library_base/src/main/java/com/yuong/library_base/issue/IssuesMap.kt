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

import com.tencent.matrix.report.Issue

import java.util.ArrayList
import java.util.concurrent.ConcurrentHashMap

object IssuesMap {

    private val issues = ConcurrentHashMap<String, List<Issue>>()

    val count: Int
        get() {
            val list = issues[IssueFilter.currentFilter]
            return list?.size ?: 0
        }

    fun put(@IssueFilter.FILTER filter: String, issue: Issue) {
        var list: MutableList<Issue>? = issues[filter] as MutableList<Issue>?
        if (list == null) {
            list = ArrayList()
        }
        list.add(0, issue)
        issues[filter] = list
    }

    operator fun get(@IssueFilter.FILTER filter: String): List<Issue>? {
        return issues[filter]
    }

    fun clear() {
        issues.clear()
    }

}
