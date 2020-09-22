package com.example.minesweeper.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.minesweeper.MainActivity
import com.example.minesweeper.R
import com.example.minesweeper.model.mapViewModel

class mapView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paintBackground = Paint()
    var paintLine = Paint()
    var paintText = Paint()

    init {
        paintBackground.color = Color.argb(255,255, 227, 220)
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintText.strokeWidth = 40f

        paintText.color = Color.GREEN
        paintText.textSize = 60f
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

                 //Toast.makeText(
                  //   (context as MainActivity),
                  //   "Square$tX $tY ${mapViewModel.getFieldContent(tX,tY)}",
                   //  Toast.LENGTH_LONG).show()
                val b = mapViewModel.getFieldContent(tX, tY)
                Toast.makeText((context as MainActivity), "$tX $tY", Toast.LENGTH_SHORT).show()
                //call the function to calculate how many bombs are arround

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