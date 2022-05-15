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
import com.halil.ozel.catchthefruits.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var score: Int = 0 // skor değişkeni

    var imageArray = ArrayList<ImageView>() // array tanımı

    var handler: Handler = Handler(Looper.getMainLooper()) // handler nesnesi

    var runnable: Runnable = Runnable { } // runnable nesnesi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        score = 0 // skor değeri sıfırlandı.

        // tanımlanan array içine imageler eklendi.
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

        hideImages() // metod cagrildi.

        // 10 saniye boyunca 1 er 1 er azalan bir timer
        object : CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() { // oyun bitiminde neler olacak
                binding.tvTime.text = "Zaman Doldu." // zaman dolunca mesaj yaz.
                handler.removeCallbacks(runnable) // gelen çeğrıları sil

                for (image in imageArray) { // image array içinde dön
                    image.visibility = View.INVISIBLE // resimler gizle.
                }

                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setCancelable(false)
                dialog.setTitle("Catch The Fruits")
                dialog.setMessage("Yaptığın Skor : $score\nTekrardan oynamak ister misiniz ?")
                dialog.setPositiveButton("YES") { _, _ ->
                    restart()

                }
                    .setNegativeButton("NO ") { _, _ ->
                        score = 0
                        binding.tvScore.text = "Score : $score"
                        binding.tvTime.text = "Time : " + "0"

                        for (image in imageArray) { // image array içinde dön
                            image.visibility = View.INVISIBLE // resimler gizle.
                        }
                        finish()
                    }

                val alert = dialog.create()
                alert.show()
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) { // herbir saniyede neler olacak
                binding.tvTime.text = "Time : " + p0 / 1000 // saniye cinsinden değerini yazdır.
            }
        }.start()
    }


    // resimleri gizleme metodu
    private fun hideImages() {
        runnable = Runnable()// runnable ile ilgili işlemler yapılıyor.
        {
            for (image in imageArray) { // image array içinde dön
                image.visibility = View.INVISIBLE // resimler gizle.
            }
            val random = Random() // random nesnesi olusturma
            val index = random.nextInt(8 - 0) // 9 adet random sayı olusturma
            imageArray[index].visibility = View.VISIBLE // rastgele bir index görünür yapma
            handler.postDelayed(runnable, 500) // resimleri yarım saniyede bir değiştirme
        }
        handler.post(runnable) // handler'a runnable atama işlemi yapılıyor.
    }

    // resimlere tıklanınca puan arttıran fonksiyon
    @SuppressLint("SetTextI18n")
    fun increaseScore(view: View) {
        score++ // skor arttırma
        binding.tvScore.text = "Score : $score" // skor değeri ekranda gösteriliyor.
    }

    // tekrar oynama metodu
    @SuppressLint("SetTextI18n")
    fun restart() {
        score = 0
        binding.tvScore.text = "Score : $score"
        hideImages()
        binding.tvTime.text = "Time : " + 10000 / 1000

        for (image in imageArray) { // image array içinde dön
            image.visibility = View.INVISIBLE // resimler gizle.
        }

        object : CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onFinish() { // oyun bitiminde neler olacak
                binding.tvTime.text = "Zaman Doldu !!!" // zaman dolunca mesaj yaz.
                handler.removeCallbacks(runnable) // gelen çeğrıları sil

                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setCancelable(false)
                dialog.setTitle("Catch The Fruits")
                dialog.setMessage("Yaptığın Skor : $score\nTekrardan oynamak ister misiniz ?")
                dialog.setPositiveButton("YES") { _, _ ->
                    restart()
                }
                    .setNegativeButton("NO") { _, _ ->
                        score = 0
                        binding.tvScore.text = "Score : $score"
                        binding.tvTime.text = "Time : " + "0"

                        for (image in imageArray) { // image array içinde dön
                            image.visibility = View.INVISIBLE // resimler gizle.
                        }
                    }

                val alert = dialog.create()
                alert.show()
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) { // herbir saniyede neler olacak
                binding.tvTime.text = "Time : " + p0 / 1000 // saniye cinsinden değerini yazdır.
            }
        }.start()
    }
}
