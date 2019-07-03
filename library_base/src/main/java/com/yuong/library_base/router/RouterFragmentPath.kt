package com.yuong.library_base.router

class RouterFragmentPath {
    //主页
    class Home{
        companion object {
            private const val HOME = "/home"
            /*首页*/
            const val PAGER_HOME = "$HOME/Home"
        }
    }

    //分类
    class Category{
        companion object {
            private const val CATEGORY = "/catogory"
            const val PAGE_CATEGORY = "$CATEGORY/Category"
        }
    }

    //福利
    class Welfare {
        companion object {
            private const val WELFARE = "/welfare"

            const val PAGE_WELFARE = "$WELFARE/Welfare"
        }
    }

    //闲读
    class LeisureReading {
        companion object {
            private const val LEISUREREADING = "/LeisureReading"

            const val PAGE_LEISUREREADING = "$LEISUREREADING/LeisureReading"
        }

    }
}