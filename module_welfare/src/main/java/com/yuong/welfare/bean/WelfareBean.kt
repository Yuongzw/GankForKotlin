package com.yuong.welfare.bean

class WelfareBean {

    /**
     * error : false
     * results : [{"_id":"5cc43d8e9d212258d8b3f455","createdAt":"2019-04-27T11:31:26.124Z","desc":"#这个笑声够我笑一年了 ","publishedAt":"2019-04-27T11:31:48.553Z","source":"web","type":"休息视频","url":"https://v.douyin.com/6RgqG8/","used":true,"who":"lijinshanmx"},{"_id":"5c6a4b2e9d212226806fd419","createdAt":"2019-02-18T06:05:34.265Z","desc":"#抖音，记录美好生活# 回首掏！😄😆","publishedAt":"2019-04-10T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/YDmdxx/ ","used":true,"who":"lijinshanmx"},{"_id":"5c2dabf89d21226e00cb7699","createdAt":"2019-01-03T06:30:16.909Z","desc":"##冬日恋歌 期盼着下雪❄️期盼着你 ","publishedAt":"2019-01-03T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/LYVFNT/","used":true,"who":"lijinshanmx"},{"_id":"5c25db4f9d21221e8ada8665","createdAt":"2018-12-28T08:14:07.276Z","desc":"#好嗨呦 据说发第二遍会火🔥","publishedAt":"2018-12-28T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/8EXApX/","used":true,"who":"lijinshanmx"},{"_id":"5c12212c9d21223f57f13479","createdAt":"2018-12-13T09:06:52.785Z","desc":"#看看寒冷的哈尔滨，刚洗完不到半个小时的衣服.","publishedAt":"2018-12-13T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/8AQBdu/","used":true,"who":"lijinshanmx"},{"_id":"5bfe1a2f9d2122309624cbb6","createdAt":"2018-11-28T04:31:43.412Z","desc":"#1995年马云接受浙江卫视采访，快来看看过去的马爸爸~ ~","publishedAt":"2018-11-28T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/RoL8sn/","used":true,"who":"lijinshanmx"},{"_id":"5bf22ff29d21223dd5066119","createdAt":"2018-11-19T03:37:22.4Z","desc":"嗯\u2026\u2026没毛病😂 😂😂😂","publishedAt":"2018-11-19T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/RGHw8q/ ","used":true,"who":"lijinshanmx"},{"_id":"5be14eb39d21223d7a1b0abb","createdAt":"2018-11-06T08:20:03.58Z","desc":"#行不行😂😂","publishedAt":"2018-11-06T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/RPTYPR/ ","used":true,"who":"lijinshanmx"},{"_id":"5bcd71bb9d21220318e77ba8","createdAt":"2018-10-22T06:44:11.10Z","desc":"#如何快速鉴别#程序员 的资深程度\u2014\u2014学会的老板都发财啦!!!!!!!!","publishedAt":"2018-10-22T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/RLWkMT/","used":true,"who":"lijinshanmx"},{"_id":"5bc4347f9d212279160c4c9d","createdAt":"2018-10-15T06:32:31.693Z","desc":"人生苦短，岁月沧桑，短短几十年  一晃就过，朋友们请珍惜身边的一切！","publishedAt":"2018-10-15T00:00:00.0Z","source":"web","type":"休息视频","url":"https://v.douyin.com/dEnpy7/","used":true,"who":"lijinshanmx"}]
     */

    var isError: Boolean = false
    var results: List<ResultsBean>? = null

    class ResultsBean {
        /**
         * _id : 5cc43d8e9d212258d8b3f455
         * createdAt : 2019-04-27T11:31:26.124Z
         * desc : #这个笑声够我笑一年了
         * publishedAt : 2019-04-27T11:31:48.553Z
         * source : web
         * type : 休息视频
         * url : https://v.douyin.com/6RgqG8/
         * used : true
         * who : lijinshanmx
         */

        var _id: String? = null
        var createdAt: String? = null
        var desc: String? = null
        var publishedAt: String? = null
        var source: String? = null
        var type: String? = null
        var url: String? = null
        var isUsed: Boolean = false
        var who: String? = null
    }
}
