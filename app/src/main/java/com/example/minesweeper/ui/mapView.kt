package com.example.minesweeper.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import com.example.minesweeper.MainActivity
import com.example.minesweeper.R
import com.example.minesweeper.model.mapViewModel
import com.example.minesweeper.model.mapViewModel.BOMB
import com.example.minesweeper.model.mapViewModel.EMPTY
import com.example.minesweeper.model.mapViewModel.isGameEnd
import com.example.minesweeper.model.mapViewModel.isGameOver
import com.example.minesweeper.model.mapViewModel.resetModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.view.*


//TODO
// 5. if there is a mine draw another background
// 7. put some icons (flaggs, mines)
// 10. play with the style of the button



class mapView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintBackground = Paint()
    var paintLine = Paint()
    var paintText = Paint()
    var paintBackgroundClicked = Paint()
    var paintBackgroundFlagg = Paint()
    var mine = BitmapFactory.decodeResource(
        context?.resources,
        R.drawable.mine)


    init {
        paintBackground.color = Color.rgb(254,234,230)
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintText.strokeWidth = 40f

        paintText.color = Color.rgb(227,101, 136)
        paintText.textSize = 60f

        paintBackgroundClicked.color = Color.rgb(129, 240, 229)
        paintBackgroundClicked.style = Paint.Style.FILL
        paintBackgroundFlagg.color = Color.rgb(154, 39, 90)
        paintBackgroundFlagg.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintText.textSize = height/8f

    }

    override fun onDraw(canvas: Canvas) {
        canvas?.drawRect(0f, 0f, width.toFloat(),
            height.toFloat(), paintBackground)


        drawBoard(canvas)
        drawClickedTiles(canvas)
    }

    private fun drawClickedTiles(canvas: Canvas) {
        //Y
        val tileHeight = height.toFloat()/5
        //X
        val tileWidth = width.toFloat()/5
        val matrix = mapViewModel.fieldMatrix

        for(row_index in matrix.indices){
            var row = matrix[row_index]
            for (column_index in row.indices){
                //draw filled rect
                if(!matrix[row_index][column_index].wasClicked)
                    continue


                val left = (row_index * tileWidth)
                val right = ((row_index + 1) * tileWidth)
                val top = (column_index * tileHeight)
                val bottom = ((column_index + 1) * tileHeight)

                    if (!matrix[row_index][column_index].isFlagged) {
                        canvas?.drawRect(left, top, right, bottom, paintBackgroundClicked)
                        if (matrix[row_index][column_index].minesAround != 0) {
                            canvas.drawText(
                                "${matrix[row_index][column_index].minesAround}",
                                left + tileWidth / 3.toFloat(),
                                bottom - tileHeight / 4.toFloat(),
                                paintText
                            )
                        }
                    }else{
                        canvas?.drawRect(left, top, right, bottom, paintBackgroundFlagg)
                    }
                }


        }
    }

    private fun drawBoard(canvas: Canvas){
        //border
        canvas?.drawRect(
            0f, 0f, width.toFloat(), height.toFloat(), paintLine
        )

        //two horizontal lines
        canvas?.drawLine(
            0f, (height/5).toFloat(), width.toFloat(), (height/5).toFloat(), paintLine
        )
        canvas?.drawLine(
            0f, (2*height/5).toFloat(), width.toFloat(), (2*height/5).toFloat(), paintLine
        )
        canvas?.drawLine(
            0f, (3*height/5).toFloat(), width.toFloat(), (3*height/5).toFloat(), paintLine
        )
        canvas?.drawLine(
            0f, (4*height/5).toFloat(), width.toFloat(), (4*height/5).toFloat(), paintLine
        )

        // two vertical lines
        canvas?.drawLine(
            (width / 5).toFloat(), 0f, (width / 5).toFloat(), height.toFloat(),
            paintLine
        )
        canvas?.drawLine(
            (2 * width / 5).toFloat(), 0f, (2 * width / 5).toFloat(), height.toFloat(),
            paintLine
        )
        canvas?.drawLine(
            (3 * width / 5).toFloat(), 0f, (3 * width / 5).toFloat(), height.toFloat(),
            paintLine
        )
        canvas?.drawLine(
            (4 * width / 5).toFloat(), 0f, (4 * width / 5).toFloat(), height.toFloat(),
            paintLine
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (isGameEnd)return true
        if (event?.action == MotionEvent.ACTION_DOWN) {
            // 0,1; 0,2...
            val tX = event.x.toInt() / (width / 5)
            val tY = event.y.toInt() / (height / 5)

            if (tX < 5 && tY < 5){


                mapViewModel.getFieldContent(tX, tY).wasClicked = true;
                if (mapViewModel.toggleOn) mapViewModel.getFieldContent(tX, tY).isFlagged = true

                isGameOver(tX, tY)
                invalidate() // redraws the view, the onDraw(...) will be called
                if (mapViewModel.isGameEnd){
                    var endgame = if (mapViewModel.isGameWon) "Won" else "Lost"
                    Snackbar.make(
                        this, "The game is ${endgame}", Snackbar.LENGTH_LONG
                       ).setAction("Retry") {
                        // Responds to click on the action
                        resetModel()
                        invalidate()

                    }.setBackgroundTint(resources.getColor(R.color.backgroundTint))
                        .setActionTextColor(resources.getColor(R.color.actionTextColor)).show()


                }

            }
        }

        return true
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = View.MeasureSpec.getSize(widthMeasureSpec)
        val h = View.MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }
}