package com.seven.kotlintest.net

import com.seven.kotlintest.model.HomeItemBean
import com.seven.kotlintest.util.URLProviderUtils

class HomeRequest(type:Int,page:Int,handler: ResponseHandler<List<HomeItemBean>>):
    MRequest<List<HomeItemBean>>(type,URLProviderUtils.getHomeUrl("BBM54PGAwangning",page,8),handler)