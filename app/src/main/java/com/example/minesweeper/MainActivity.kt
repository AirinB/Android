package com.example.minesweeper

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import com.example.minesweeper.model.mapViewModel
import com.example.minesweeper.model.mapViewModel.resetModel
import com.example.minesweeper.ui.mapView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleBtn.setOnClickListener {
            mapViewModel.toggleOn = toggleBtn.isChecked
        }


        btnRestart.setOnClickListener {
            mapView.restartGame()
            shakePhone()
        }



    }


    public fun shakePhone() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) { // Vibrator availability checking
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        500,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                ) // New vibrate method for API Level 26 or higher
            } else {
                vibrator.vibrate(500) // Vibrate method for below API Level 26
            }
        }
    }


    fun mapView.restartGame() {
        resetModel()
        mapView.invalidate()
    }






}