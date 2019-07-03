package com.yuong.xiandu.bean

class XianduSubCategoryBean {

    /**
     * error : false
     * results : [{"_id":"57c83792421aa97cada9b79d","created_at":"2016-09-01T22:13:38.420Z","icon":"http://ww2.sinaimg.cn/large/610dc034gw1f9sg2pq9ufj202s02s0sj.jpg","id":"qdaily","title":"好奇心日报"},{"_id":"57c83831421aa97cb162d8b6","created_at":"2016-09-01T22:16:17.450Z","icon":"http://ww4.sinaimg.cn/large/610dc034jw1f9sfzr2gmnj204v04va9y.jpg","id":"zhihu","title":"知乎日报"},{"_id":"57c8395a421aa97cb162d8b8","created_at":"2016-09-01T22:21:14.107Z","icon":"http://ww1.sinaimg.cn/large/0066P23Wjw1f7eg7480luj300g00g0lt.jpg","id":"vice","title":"Vice"},{"_id":"5829bc8c421aa911e32d87e6","created_at":"2016-11-14T21:30:52.338Z","icon":"http://ww3.sinaimg.cn/large/0066P23Wjw1f9rylijz6rj3030030gle.jpg","id":"ifanr","title":"爱范儿"},{"_id":"5829bcae421aa911cf2e1567","created_at":"2016-11-14T21:31:26.880Z","icon":"http://ww1.sinaimg.cn/large/0066P23Wjw1f9rym3y697j30300300sj.jpg","id":"engadget","title":"Engadget 中文版"},{"_id":"5829bcda421aa911cf2e1569","created_at":"2016-11-14T21:32:10.921Z","icon":"http://ww1.sinaimg.cn/large/610dc034gw1f9sgtc7v1mj204602uq2s.jpg","id":"ipc","title":"iPc.me"},{"_id":"5829bd90421aa911dbc9156f","created_at":"2016-11-14T21:35:12.685Z","icon":"http://ww4.sinaimg.cn/large/0066P23Wjw1f9ryq134rfj3030030a9u.jpg","id":"techcrunch","title":"TechCrunch 中国"},{"_id":"5829c274421aa911dbc91572","created_at":"2016-11-14T21:56:04.370Z","icon":"http://ww3.sinaimg.cn/large/0066P23Wjw1f9rzbqacpyj3030030742.jpg","id":"wanqu","title":"湾区日报第758期 2016/11/14"},{"_id":"582c5312421aa95006efc053","created_at":"2016-11-16T20:37:38.958Z","icon":"http://ww1.sinaimg.cn/large/610dc034jw1f9u8eekc4lj2074074jri.jpg","id":"solidot","title":"Solidot"},{"_id":"582c53aa421aa95006efc055","created_at":"2016-11-16T20:40:10.334Z","icon":"http://ww2.sinaimg.cn/large/0066P23Wjw1f9u8ddodymj3020020wea.jpg","id":"williamlong","title":"月光博客"},{"_id":"582cf810421aa94ffa9f7633","created_at":"2016-11-17T08:21:36.755Z","icon":"http://ww4.sinaimg.cn/large/610dc034gw1f9usojtqdfj21kw1kwn5e.jpg","id":"toodaylab","title":"理想生活实验室"}]
     */

    var isError: Boolean = false
    var results: List<ResultsBean>? = null

    class ResultsBean {
        /**
         * _id : 57c83792421aa97cada9b79d
         * created_at : 2016-09-01T22:13:38.420Z
         * icon : http://ww2.sinaimg.cn/large/610dc034gw1f9sg2pq9ufj202s02s0sj.jpg
         * id : qdaily
         * title : 好奇心日报
         */

        var _id: String? = null
        var created_at: String? = null
        var icon: String? = null
        var id: String? = null
        var title: String? = null
    }
}
