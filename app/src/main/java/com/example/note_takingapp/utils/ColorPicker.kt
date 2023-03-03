package com.example.note_takingapp.utils

object ColorPicker {
    val colors = arrayOf(
        "#3685BC",
        "#6096B4",
        "#A7727D",
        "#609966",
        "#F0A04B",
        "#FFB84C",
        "A84448"
    )
    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}