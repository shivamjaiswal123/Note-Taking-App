package com.example.note_takingapp.utils

object ColorPicker {
    val colors = arrayOf(
        "#3685BC",
        "#D36280",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#7D659F",
        "#51BAB3",
        "#4FB66C",
        "#E3AD17",
        "#627991",
        "#EF8EAD",
        "#B5BFC6",
        "#ffab91",
        "#81deea",
        "#f48fb1"
    )
    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}