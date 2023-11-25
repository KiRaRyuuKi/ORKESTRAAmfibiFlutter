package com.orkestra.paycars.controllers.model

class ModelDataMessage {
    var judul: String? = null
    var gambar: String? = null
    constructor()

    constructor(judul: String?, gambar: String?) {
        this.judul = judul
        this.gambar = gambar
    }
}