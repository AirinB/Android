package com.example.minesweeper.model


data class Field(
    var type: Short, var minesAround: Int,
    var isFlagged: Boolean, var wasClicked: Boolean)