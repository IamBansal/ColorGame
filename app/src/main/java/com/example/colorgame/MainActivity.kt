package com.example.colorgame

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private var score: TextView? = null
    private var firstColor: Button? = null
    private var secondColor: Button? = null
    private var thirdColor: Button? = null
    private var fourthColor: Button? = null
    private var restart: Button? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = findViewById(R.id.tvScore)
        firstColor = findViewById(R.id.first)
        secondColor = findViewById(R.id.second)
        thirdColor = findViewById(R.id.third)
        fourthColor = findViewById(R.id.fourth)
        restart = findViewById(R.id.restart)
        toolbar = findViewById(R.id.toolbar)

        var count = 0

        toolbar?.title = "Color Game"

        drivingCode()

        restart?.setOnClickListener {
            count = 0
            score?.text = count.toString()
            drivingCode()
            restart?.visibility = View.GONE
            restart?.tag = "invisible"
        }

        firstColor?.setOnClickListener {
            count = whatToDo(firstColor!!, count)
        }

        secondColor?.setOnClickListener {
            count = whatToDo(secondColor!!, count)
        }

        thirdColor?.setOnClickListener {
            count = whatToDo(thirdColor!!, count)
        }

        fourthColor?.setOnClickListener {
            count = whatToDo(fourthColor!!, count)
        }

    }

    private fun whatToDo(button: Button, count: Int): Int {
        var a = count
        if (button.tag!! == "grey") {
            a++
            restart?.visibility = View.GONE
            restart?.tag = "invisible"
        } else {
            Toast.makeText(
                this,
                "GAME OVER!!\nYour score is : ${score?.text}.",
                Toast.LENGTH_SHORT
            ).show()
            restart?.visibility = View.VISIBLE
            restart?.tag = "visible"
        }
        score?.text = a.toString()
        return a
    }

    private fun checkColor(originalColor: String) {

        Handler().postDelayed({
            when (originalColor) {
                "black" -> {
                    firstColor?.setBackgroundColor(resources.getColor(R.color.black))
                    firstColor?.tag = originalColor
                }
                "green" -> {
                    secondColor?.setBackgroundColor(resources.getColor(R.color.teal_700))
                    secondColor?.tag = originalColor
                }
                "blue" -> {
                    thirdColor?.setBackgroundColor(resources.getColor(R.color.teal_200))
                    thirdColor?.tag = originalColor
                }
                "purple" -> {
                    fourthColor?.setBackgroundColor(resources.getColor(R.color.purple_700))
                    fourthColor?.tag = originalColor
                }
            }
        }, 1000)

    }

    private fun changeColor(button: Button) {
        val originalColor = button.tag.toString()
        button.setBackgroundColor(resources.getColor(R.color.grey))
        button.tag = "grey"
        checkColor(originalColor)
    }

    private fun drivingCode() {
        var n = 0
        while (n < 10) {
        Handler().postDelayed({
            when ((0..3).random()) {
                0 -> {
                    changeColor(firstColor!!)
                }
                1 -> {
                    changeColor(secondColor!!)
                }
                2 -> {
                    changeColor(thirdColor!!)
                }
                3 -> {
                    changeColor(fourthColor!!)
                }
            }
        }, 6000)
            n++
        }
    }

}