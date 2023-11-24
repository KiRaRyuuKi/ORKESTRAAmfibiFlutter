package com.orkestra.paycars.controllers.model

data class DataModelItem(val viewType: Int) {

    var recyclerViewItem: List<RecyclerItem>? = null
    var banner: Banner? = null

    constructor(viewType: Int, recyclerViewItem: List<RecyclerItem>) : this(viewType) {
        this.recyclerViewItem = recyclerViewItem
    }

    constructor(viewType: Int, banner: Banner) : this(viewType) {
        this.banner = banner
    }

}

data class RecyclerItem(val image: Int, val offer: String)

data class Banner(val image: Int)