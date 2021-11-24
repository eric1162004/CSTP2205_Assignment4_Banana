package com.example.assignment4.data.entities

data class DairyEntry(
    var date: String = "???",
    var location: String = "???",
    var imageUrls: List<String> = listOf(),
    var description: String = "???",
    var title: String = "???"
)