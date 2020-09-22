package com.example.minesweeper.model


object mapViewModel {
    val EMPTY: Short = 0
    val BOMB:  Short = 1


    private val model = arrayOf(
        shortArrayOf(EMPTY, BOMB, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, BOMB),
        shortArrayOf(BOMB, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY),
    )

    fun resetModel(){
        for (i:Int in 0..2){
            for(j:Int in 0..2){
                model[i][j] = EMPTY
            }
        }

    }

    fun getFieldContent(x:Int, y: Int): Short {
        return  model[x][y]
    }
}