package com.orkestra.paycars.controllers.view

sealed class ViewDataHome {

    class Title(
        val id: Int,
        val title: String,
        val date: String
    ) : ViewDataHome()

    class Banner(
        val id: Int,
        val thumbnail: String
    ) : ViewDataHome()

    class Subtitle(
        val id: Int,
        val subtitle: String
    ) : ViewDataHome()

    class Seller(
        val id:Int,
        val thumbnail: String,
        val title: String,
        val description: String
    ) : ViewDataHome()

    class Content(
        val id: Int,
        val thumbnail: String,
        val title: String,
        val description: String,
        val brand: String,
        val price: Double
    ) : ViewDataHome()
}
