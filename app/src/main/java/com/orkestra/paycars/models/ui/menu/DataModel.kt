package com.orkestra.paycars.models.ui.menu

class DataModel {
    var judul: String? = null
    var gambar: String? = null
    // Konstruktor default
    constructor()

    // Konstruktor dengan parameter
    constructor(judul: String?, gambar: String?) {
        this.judul = judul
        this.gambar = gambar
    }
}