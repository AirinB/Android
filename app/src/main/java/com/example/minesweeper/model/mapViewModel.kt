package com.example.minesweeper.model

import android.util.Log

object mapViewModel {
    val EMPTY: Short = 0
    val BOMB:  Short = 1

    var toggleOn = false
    var isGameEnd = false
    var isGameWon = false
    var checkedBombs = 0


    var fieldMatrix: Array<Array<Field>> = arrayOf(
        arrayOf(
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
        ),

        arrayOf(
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(BOMB, 8, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, false, wasClicked = false),
            Field(BOMB, 8, false, wasClicked = false),
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
            Field(BOMB, 8, isFlagged = false, wasClicked = false),
            Field(BOMB, 8, isFlagged = false, wasClicked = false),
            Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            Field(EMPTY, 0, isFlagged = false, wasClicked = false),
        )
    )



    fun isGameOver(tX:Int, tY:Int){
        var field = fieldMatrix[tX][tY]
        if (field.isFlagged){

            if(field.type != BOMB){
                isGameWon = false
                isGameEnd = true
            }else{
                checkedBombs += 1
                Log.i("CHECKED BOMBS", "$checkedBombs")
                if (checkedBombs == 4){
                    isGameEnd = true
                    isGameWon = true
                }
            }

        }else{
            if(field.type == BOMB){
                isGameWon = false
                isGameEnd = true
            }
        }
    }
    fun getFieldContent(x:Int, y: Int): Field {
        return  fieldMatrix[x][y]
    }

    fun resetModel() {
        isGameWon = false
        isGameEnd = false
        checkedBombs = 0
        fieldMatrix  = arrayOf(
            arrayOf(
                Field(EMPTY, 1, isFlagged = false, wasClicked = false),
                Field(EMPTY, 1, isFlagged = false, wasClicked = false),
                Field(EMPTY, 1, isFlagged = false, wasClicked = false),
                Field(EMPTY, 1, isFlagged = false, wasClicked = false),
                Field(EMPTY, 1, isFlagged = false, wasClicked = false),
            ),

            arrayOf(
                Field(EMPTY, 1, isFlagged = false, wasClicked = false),
                Field(BOMB, 8, isFlagged = false, wasClicked = false),
                Field(EMPTY, 1, isFlagged = false, wasClicked = false),
                Field(EMPTY, 1, false, wasClicked = false),
                Field(BOMB, 8, false, wasClicked = false),
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
                Field(BOMB, 8, isFlagged = false, wasClicked = false),
                Field(BOMB, 8, isFlagged = false, wasClicked = false),
                Field(EMPTY, 1, isFlagged = false, wasClicked = false),
                Field(EMPTY, 0, isFlagged = false, wasClicked = false),
            )
        )
    }

}