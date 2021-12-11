package com.example.assignment4.data.entities

// Model for each Dairy Entry
data class DairyEntry(
    var date: String = "???",
    var location: String = "???",
    var imageUrls: List<String> = listOf(),
    var description: String = "???",
    var title: String = "???"
)