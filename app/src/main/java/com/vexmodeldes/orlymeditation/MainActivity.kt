package com.vexmodeldes.orlymeditation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import com.view.circulartimerview.CircularTimerListener
import com.view.circulartimerview.CircularTimerView
import com.view.circulartimerview.TimeFormatEnum

class MainActivity : AppCompatActivity() {

    var timerIsRunning: Boolean? = null
    lateinit var buttonView: Button
    lateinit var openDesc: Button
    lateinit var progressBar: CircularTimerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonView = findViewById<Button>(R.id.timerStartPause)
        openDesc = findViewById<Button>(R.id.openDesc)
        progressBar = findViewById<CircularTimerView>(R.id.progress_circular)

        progressBar.progress = 0f
        progressBar.setText("Запустите таймер")
        progressBar.setPrefix("")
        progressBar.setSuffix("")

        progressBar.setCircularTimerListener(object : CircularTimerListener {
            override fun updateDataOnTick(remainingTimeInMs: Long): String? {
                return Math.ceil((remainingTimeInMs / 1000f).toDouble()).toString()
            }

            override fun onTimerFinished() {
                progressBar.setPrefix("")
                progressBar.setSuffix("")
                progressBar.setText("Вы справились!")
                buttonView.text = ("Заново")
                timerIsRunning = false
            }
        }, 60, TimeFormatEnum.SECONDS, 10)

        openDesc!!.setOnClickListener {
            val intent = Intent(this, description::class.java)
            startActivity(intent)
        }

        buttonView!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (timerIsRunning == true) {
                    stopTimer()
                } else {
                    startTimer()
                }
            }

            fun startTimer() {
                timerIsRunning = true
                findViewById<CircularTimerView>(R.id.progress_circular).startTimer()
                progressBar.setPrefix("Дышите ")
                progressBar.setSuffix(" сек.")
                buttonView.text = ("Пауза")
            }

            fun stopTimer() {
                timerIsRunning = false
                findViewById<CircularTimerView>(R.id.progress_circular).stopTimer()
                Handler().postDelayed({
                    buttonView.text = ("Заново")
                }, 50)
            }
        })

    }
}