package com.example.minesweeper.model



object mapViewModel {
    val EMPTY: Short = 0
    val BOMB:  Short = 1


    val fieldMatrix: Array<Array<Field>> = arrayOf(
        arrayOf(
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
        ),

        arrayOf(
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(BOMB, 0, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, false, wasClicked = false),
            Field(BOMB, 1, false, wasClicked = false),
        ),
        arrayOf(
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
        ),
        arrayOf(
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 2, isFlagged = false, wasClicked = false),
            Field(EMPTY, 2, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 0, isFlagged = false, wasClicked = false),
        ),
        arrayOf(
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(BOMB, 0, isFlagged = false, wasClicked = false),
            Field(BOMB, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 0, isFlagged = false, wasClicked = false),
        ),
    )





    fun getFieldContent(x:Int, y: Int): Field {
        return  fieldMatrix[x][y]
    }
}