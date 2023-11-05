package com.halil.ozel.catchthefruits

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.halil.ozel.catchthefruits.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var score = 0
    private val imageArray = ArrayList<ImageView>()
    private val handler = android.os.Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.catchFruits = this
        binding.score = getString(R.string.score_0)
        score = 0
        imageArray.addAll(listOf(
            binding.ivApple,
            binding.ivBanana,
            binding.ivCherry,
            binding.ivGrapes,
            binding.ivKiwi,
            binding.ivOrange,
            binding.ivPear,
            binding.ivStrawberry,
            binding.ivWatermelon
        ))
        hideImages()
        playAndRestart()
    }

    private fun hideImages() {
        runnable = Runnable {
            imageArray.forEach { it.visibility = View.INVISIBLE }
            val randomIndex = Random().nextInt(8)
            imageArray[randomIndex].visibility = View.VISIBLE
            handler.postDelayed(runnable, FIVE_HUNDRED)
        }
        handler.post(runnable)
    }

    @SuppressLint("SetTextI18n")
    fun increaseScore() {
        score++
        binding.score = "Score : $score"
    }

    @SuppressLint("SetTextI18n")
    fun playAndRestart() {
        score = 0
        binding.score = "Score : $score"
        hideImages()
        binding.time = "Time : " + 10000 / 1000
        imageArray.forEach { it.visibility = View.INVISIBLE }

        object : CountDownTimer(TEN_THOUSAND, ONE_THOUSAND) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.time = getString(R.string.time_up)
                handler.removeCallbacks(runnable)

                AlertDialog.Builder(this@MainActivity).apply {
                    setCancelable(false)
                    setTitle(getString(R.string.game_name))
                    setMessage("Your score : $score\nWould you like to play again?")
                    setPositiveButton(getString(R.string.yes)) { _, _ ->
                        playAndRestart()
                    }
                    setNegativeButton(getString(R.string.no)) { _, _ ->
                        score = 0
                        binding.score = "Score : $score"
                        binding.time = "Time : 0"
                        imageArray.forEach { it.visibility = View.INVISIBLE }
                        finish()
                    }
                }.create().show()
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(tick: Long) {
                binding.time = getString(R.string.time) + tick / 1000
            }
        }.start()
    }

    companion object {
        private const val TEN_THOUSAND = 10000L
        private const val ONE_THOUSAND = 1000L
        private const val FIVE_HUNDRED = 500L
    }
}