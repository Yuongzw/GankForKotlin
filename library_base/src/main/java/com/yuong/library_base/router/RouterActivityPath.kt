package com.yuong.library_base.router

class RouterActivityPath {

        class Main{
            companion object {
                private const val MAIN = "/main"
                /*主业务界面*/
                const val PAGER_MAIN = "$MAIN/Main"
            }
        }

        /**
         * 搜索
         */
        class Search {
            companion object {
                private const val SEARCH = "/search"

                const val PAGER_SEARCH = "$SEARCH/Search"
            }
        }

//        object LeisureReading {
//            private val LEISUREREADING = "LeisureReading"
//
//            val PAGE_LEISUREREADING = "$LEISUREREADING/LeisureReading";
//        }


}