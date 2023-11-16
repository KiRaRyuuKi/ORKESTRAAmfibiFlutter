package com.orkestra.paycars.controllers

class DataModelTransaction {

    var imageShowroomTransaction: String? = null
    var nameShowroomTransaction: String? = null

    var imageTransaction: String? = null
    var nameTransaction: String? = null
    var descriptionTransaction: String? = null

    var priceTransaction: String? = null

    var buttonStatusTransaction: String? = null

    constructor()
    constructor(
        imageShowroomTransaction: String?,
        nameShowroomTransaction: String?,
        imageTransaction: String?,
        nameTransaction: String?,
        descriptionTransaction: String?,
        priceTransaction: String?,
        buttonStatusTransaction: String?
    ) {
        this.imageShowroomTransaction = imageShowroomTransaction
        this.nameShowroomTransaction = nameShowroomTransaction
        this.imageTransaction = imageTransaction
        this.nameTransaction = nameTransaction
        this.descriptionTransaction = descriptionTransaction
        this.priceTransaction = priceTransaction
        this.buttonStatusTransaction = buttonStatusTransaction
    }
}