package com.example.minesweeper.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.minesweeper.MainActivity
import com.example.minesweeper.model.mapViewModel

//TODO
// 2. draw the letters
// 3. make the toggle button make sense
// 4. put flaggs (change the background)
// 5. if there is a mine draw another background
// 6. end game if there is a mine
// 7. put some icons (flaggs, mines)
// 8. play with the styles


class mapView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintBackground = Paint()
    var paintLine = Paint()
    var paintText = Paint()
    var paintBackgroundClicked = Paint()

    init {
        paintBackground.color = Color.rgb(255, 227, 220)
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintText.strokeWidth = 40f

        paintText.color = Color.GREEN
        paintText.textSize = 60f

        paintBackgroundClicked.color = Color.rgb(129, 240, 229)
        paintBackgroundClicked.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintText.textSize = height/5f

        //bitmapBg = Bitmap.createScaledBitmap(bitmapBg, width,
          //  height, false)
    }

    override fun onDraw(canvas: Canvas) {
        canvas?.drawRect(0f, 0f, width.toFloat(),
            height.toFloat(), paintBackground)

        //canvas?.drawText("3", width/2f, height/2f, paintText)

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

                canvas?.drawRect(left, top, right, bottom, paintBackgroundClicked)

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
        if (event?.action == MotionEvent.ACTION_DOWN) {
            // 0,1; 0,2...
            val tX = event.x.toInt() / (width / 5)
            val tY = event.y.toInt() / (height / 5)

            if (tX < 5 && tY < 5){



                Toast.makeText((context as MainActivity), "$tX $tY", Toast.LENGTH_SHORT).show()
                mapViewModel.getFieldContent(tX, tY).wasClicked = true;

                invalidate() // redraws the view, the onDraw(...) will be called
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