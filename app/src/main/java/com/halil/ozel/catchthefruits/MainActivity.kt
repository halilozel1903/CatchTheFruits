package com.halil.ozel.catchthefruits

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*



class MainActivity : AppCompatActivity() {

    var score: Int = 0 // skor değişkeni

    var imageArray = ArrayList<ImageView>() // array tanımı

    var handler: Handler = Handler() // handler nesnesi

    var runnable: Runnable = Runnable { } // runnable nesnesi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0 // skor değeri sıfırlandı.

        // tanımlanan array içine imageler eklendi.
        imageArray = arrayListOf(ivApple, ivBanana, ivCherry, ivGrapes, ivKiwi, ivOrange, ivPear, ivStrawberry, ivWatermelon)

        hideImages() // metod cagrildi.


        // 10 saniye boyunca 1 er 1 er azalan bir timer
        object : CountDownTimer(10000, 1000) {


            override fun onFinish() { // oyun bitiminde neler olacak

                tvTime.text = "Zaman Doldu." // zaman dolunca mesaj yaz.
                handler.removeCallbacks(runnable) // gelen çeğrıları sil


                for (image in imageArray) { // image array içinde dön

                    image.visibility = View.INVISIBLE // resimler gizle.
                }


                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setCancelable(false)
                dialog.setTitle("Catch The Fruits")
                dialog.setMessage("Yaptığın Skor : $score\nTekrardan oynamak ister misiniz ?")
                dialog.setPositiveButton("YES") { dialog, id ->

                   Restart()

                }
                        .setNegativeButton("NO ") { dialog, which ->
                            score = 0
                            tvScore.setText("Score : $score")
                            tvTime.setText("Time : " + "0")

                            for (image in imageArray) { // image array içinde dön

                                image.visibility = View.INVISIBLE // resimler gizle.
                            }

                        }

                val alert = dialog.create()
                alert.show()
            }

            override fun onTick(p0: Long) { // herbir saniyede neler olacak

                tvTime.text = "Time : " + p0 / 1000 // saniye cinsinden değerini yazdır.
            }


        }.start()

    }


    // resimleri gizleme metodu

    fun hideImages() {

        runnable = object : Runnable { // runnable ile ilgili işlemler yapılıyor.
            override fun run() {
                for (image in imageArray) { // image array içinde dön

                    image.visibility = View.INVISIBLE // resimler gizle.
                }

                val random = Random() // random nesnesi olusturma
                val index = random.nextInt(8 - 0) // 9 adet random sayı olusturma
                imageArray[index].visibility = View.VISIBLE // rastgele bir index görünür yapma

                handler.postDelayed(runnable, 500) // resimleri yarım saniyede bir değiştirme
            }

        }

        handler.post(runnable) // handler'a runnable atama işlemi yapılıyor.


    }

    // resimlere tıklanınca puan arttıran fonksiyon

    fun increaseScore(view: View) {

        score++ // skor arttırma

        tvScore.text = "Score : " + score // skor değeri ekranda gösteriliyor.

    }


    // tekrar oynama metodu

    fun Restart() {


        score = 0
        tvScore.setText("Score : $score")
        hideImages()
        tvTime.setText("Time : " +10000/1000)

        for (image in imageArray) { // image array içinde dön

            image.visibility = View.INVISIBLE // resimler gizle.
        }


        object : CountDownTimer(10000, 1000) {


            override fun onFinish() { // oyun bitiminde neler olacak

                tvTime.text = "Zaman Doldu !!!" // zaman dolunca mesaj yaz.
                handler.removeCallbacks(runnable) // gelen çeğrıları sil



                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setCancelable(false)
                dialog.setTitle("Catch The Fruits")
                dialog.setMessage("Yaptığın Skor : $score\nTekrardan oynamak ister misiniz ?")
                dialog.setPositiveButton("YES") { dialog, id ->

                    Restart()

                }
                        .setNegativeButton("NO ") { dialog, which ->
                            score = 0
                            tvScore.setText("Score : $score")
                            tvTime.setText("Time : " + "0")


                            for (image in imageArray) { // image array içinde dön

                                image.visibility = View.INVISIBLE // resimler gizle.
                            }

                        }

                val alert = dialog.create()
                alert.show()
            }





            override fun onTick(p0: Long) { // herbir saniyede neler olacak

                tvTime.text = "Time : " + p0 / 1000 // saniye cinsinden değerini yazdır.
            }


        }.start()

    }

}
