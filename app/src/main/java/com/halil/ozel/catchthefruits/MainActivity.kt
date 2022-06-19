package com.halil.ozel.catchthefruits

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.halil.ozel.catchthefruits.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var score: Int = 0

    var imageArray = ArrayList<ImageView>()

    var handler: Handler = Handler(Looper.getMainLooper())

    var runnable: Runnable = Runnable { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.catchFruits = this

        score = 0

        imageArray = arrayListOf(
            binding.ivApple,
            binding.ivBanana,
            binding.ivCherry,
            binding.ivGrapes,
            binding.ivKiwi,
            binding.ivOrange,
            binding.ivPear,
            binding.ivStrawberry,
            binding.ivWatermelon
        )

        hideImages()

        playAndRestart()
    }


    private fun hideImages() {
        runnable = Runnable {
            for (image in imageArray) {
                image.visibility = View.INVISIBLE
            }
            val random = Random()
            val index = random.nextInt(8 - 0)
            imageArray[index].visibility = View.VISIBLE
            handler.postDelayed(runnable, 500)
        }
        handler.post(runnable)
    }


    @SuppressLint("SetTextI18n")
    fun increaseScore() {
        score++
        binding.tvScore.text = "Score : $score"
    }

    @SuppressLint("SetTextI18n")
    fun playAndRestart() {
        score = 0
        binding.tvScore.text = "Score : $score"
        hideImages()
        binding.tvTime.text = "Time : " + 10000 / 1000

        for (image in imageArray) {
            image.visibility = View.INVISIBLE
        }

        object : CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.tvTime.text = "Time's up!!!"
                handler.removeCallbacks(runnable)

                val dialog = AlertDialog.Builder(this@MainActivity).apply {
                    setCancelable(false)
                    setTitle(getString(R.string.game_name))
                    setMessage("Your score : $score\nWould you like play again?")
                }
                dialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
                    playAndRestart()
                }
                    .setNegativeButton(getString(R.string.no)) { _, _ ->
                        score = 0
                        "Score : $score".apply { binding.tvScore.text = this }
                        ("Time : " + "0").apply { binding.tvTime.text = this }

                        for (image in imageArray) {
                            image.visibility = View.INVISIBLE
                        }
                        finish()
                    }

                val alert = dialog.create()
                alert.show()
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                binding.tvTime.text = "Time : " + p0 / 1000
            }
        }.start()
    }
}
