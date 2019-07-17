package com.seven.kotlintest.net

import com.seven.kotlintest.model.NewsBean
import com.seven.kotlintest.util.URLProviderUtils

class NewsListRequest(type: Int, code:String, page:Int, handler: ResponseHandler<List<NewsBean>>) :
    MRequest<List<NewsBean>>(type, URLProviderUtils.getNewsUrl(code,page,8), handler)