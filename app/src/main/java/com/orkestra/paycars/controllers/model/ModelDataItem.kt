package com.orkestra.paycars.controllers.model

import com.orkestra.paycars.controllers.view.ViewDataBanner
import com.orkestra.paycars.controllers.view.ViewDataContent

data class ModelDataItem(val viewType: Int) {

    var recyclerViewItem: List<ViewDataContent>? = null
    var banner: ViewDataBanner? = null

    constructor(viewType: Int, recyclerViewItem: List<ViewDataContent>) : this(viewType) {
        this.recyclerViewItem = recyclerViewItem
    }

    constructor(viewType: Int, banner: ViewDataBanner) : this(viewType) {
        this.banner = banner
    }

}