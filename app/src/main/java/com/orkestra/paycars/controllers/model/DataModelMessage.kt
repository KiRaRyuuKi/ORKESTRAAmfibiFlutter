package com.orkestra.paycars.controllers.model

class DataModelMessage {
    var judul: String? = null
    var gambar: String? = null
    constructor()

    constructor(judul: String?, gambar: String?) {
        this.judul = judul
        this.gambar = gambar
    }
}