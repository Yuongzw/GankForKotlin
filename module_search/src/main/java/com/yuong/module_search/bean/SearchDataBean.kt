package com.yuong.module_search.bean

class SearchDataBean {

    /**
     * count : 10
     * error : false
     * results : [{"desc":"还在用ListView？","ganhuo_id":"57334c9d67765903fb61c418","publishedAt":"2016-05-12T12:04:43.857000","readability":"","type":"Android","url":"http://www.jianshu.com/p/a92955be0a3e","who":"陈宇明"},{"desc":"Android Data Binding Adapter for ListView and RecyclerView.","ganhuo_id":"56cc6d23421aa95caa707ac9","publishedAt":"2015-07-21T04:10:11.904000","readability":"","type":"Android","url":"https://github.com/evant/binding-collection-adapter","who":"mthli"},{"desc":"listview的折叠效果","ganhuo_id":"56cc6d1d421aa95caa7076fa","publishedAt":"2015-07-17T03:43:22.395000","readability":"","type":"Android","url":"https://github.com/dodola/ListItemFold","who":"Jason"},{"desc":"在ListView中实现日历视图","ganhuo_id":"573d2a896776591c9fd0cd77","publishedAt":"2016-05-19T12:09:29.617000","readability":"","type":"Android","url":"https://github.com/traex/CalendarListview","who":"大熊"},{"desc":"给scrollview、listview、recycleview添加header与footer","ganhuo_id":"56cc6d23421aa95caa707ab2","publishedAt":"2015-07-02T03:50:48.024000","readability":"","type":"Android","url":"https://github.com/lawloretienne/QuickReturn","who":"Jason"},{"desc":"一个滋瓷 Android RecyclerView, ListView, GridView, ScrollView ...的scroll","ganhuo_id":"56cc6d26421aa95caa707fa2","publishedAt":"2015-12-10T04:13:03.804000","readability":"","type":"Android","url":"https://github.com/EverythingMe/overscroll-decor","who":"有时放纵"},{"desc":"过滤出多重选择的ListView","ganhuo_id":"56cc6d23421aa95caa707be6","publishedAt":"2015-08-10T04:09:39.936000","readability":"","type":"Android","url":"https://github.com/pchauhan/FilterSelectorListView","who":"Jason"},{"desc":"YLListView仿IOS弹簧效果的ListView","ganhuo_id":"56cc6d29421aa95caa70827e","publishedAt":"2016-02-29T12:19:00.015000","readability":"","type":"Android","url":"https://github.com/yll2wcf/YLListView","who":"Jason"},{"desc":"滑动listview的顶部菜单动画效果","ganhuo_id":"56cc6d26421aa95caa707d66","publishedAt":"2015-09-25T03:35:19.842000","readability":"","type":"Android","url":"https://github.com/xuechinahb/AnimatorMenu","who":"Jason"},{"desc":"ListView 滑动到边缘的缩放效果","ganhuo_id":"56cc6d26421aa95caa707f4c","publishedAt":"2015-12-03T03:55:57.073000","readability":"","type":"Android","url":"https://github.com/dodola/OverscrollScale","who":"mthli"}]
     */

    var count: Int = 0
    var isError: Boolean = false
    var results: List<ResultsBean>? = null

    class ResultsBean {
        /**
         * desc : 还在用ListView？
         * ganhuo_id : 57334c9d67765903fb61c418
         * publishedAt : 2016-05-12T12:04:43.857000
         * readability :
         * type : Android
         * url : http://www.jianshu.com/p/a92955be0a3e
         * who : 陈宇明
         */

        var desc: String? = null
        var ganhuo_id: String? = null
        var publishedAt: String? = null
        var readability: String? = null
        var type: String? = null
        var url: String? = null
        var who: String? = null
    }
}
