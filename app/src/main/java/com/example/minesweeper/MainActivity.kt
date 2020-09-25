package com.example.minesweeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        }
    }

    fun mapView.restartGame() {
        resetModel()
        mapView.invalidate()
    }






}