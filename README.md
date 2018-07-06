# Catch The Fruits

![Screenshot](kotlin.png)

# Kotlin Nedir ?

Kotlin Nedir ? adlı bir blog yazısı yazdım. Kotlin dilinin ne olduğu ne işe yaradığı, Java ile farkı benzerlikleri, Kotlin'in
desteklediği platformlar ve birbirinden güzel kaynaklar ekledim. Yazıya aşağıdaki linkten ulaşabilirsiniz :

https://medium.com/@halilozel1903/kotlin-nedir-43e312d2dca6


# Kotlin Dersleri 

Kotlin ile ilgili bir seri hazırladım. Kotlin temellerinden başlayıp ileriye doğru ilerleyen bir proje.
Derslere eklemeler devam edecek. Kotlin'e yeni başlıyorsanız aşağıdaki linkteki örnekleri inceleminizi tavsiye ederim : 

https://github.com/halilozel1903/KotlinTutorials


## Projenin Amacı

Kotlin ile Android uygulama geliştirmenin Java diline göre daha az kod ve kolay bir şekilde oluşturulduğunu göstermek.

## Projenin Oynanışı 

Uygulamada 9 adet farklı meyve resimleri bulunmaktadır. Rastgele 1 tanesi yarım saniyede bir değişmektedir. Diğer 8 tane
meyve oyun bitine kadar gizlenmektedir. Oyun süresi 10 saniyedir. Bu süre zarfında en çok ne kadar meyve yakalayabilirsiniz.
Onun ölçümünü ve reflekslerinizin kuvvetini ölçen bir uygulamadır.

<p>
  <img src="game.gif" width="350" >
</p>




## Projenin İçeriği

Projede aşağıdaki yapılar ve bileşenler kullanılmıştır :  <br>

- Alert Dialog
- Timers
- Runnable ve Handler
- Grid Layout
- Constraint Layout
- Text View
- Image View

## Projenin Ekran Tasarımı

![Dizayn](pic1.png)

Proje şimdilik tek ekran olarak tasarlandı. En dışta Constraint Layout ve içerisine Grid Layout eklenmiştir. Grid Layout
içinde ise 9 adet Image View kullanıldı. Üst kısımda zamanı tutmak için bir text view ve aşağıda skoru göstermek için başka
bir text view kullanılmıştır. Aşağıdaki yapıyı inceleyerek dediklerimi daha iyi anlayacaksınız.

https://gist.github.com/halilozel1903/fa265cdd77678d68c5d4d17efcd576aa

Tasarım kısmında Grid Layout eklemek için gradle dosyasına aşağıdaki ifadeyi ekleminiz gerekmektedir.

```java 
 implementation 'com.android.support:gridlayout-v7:28.0.0-alpha3' 
```

Image View'lara eklenen resimleri aşağıdaki siteden aratıp bulabilirsiniz : 

https://www.flaticon.com/search?word=fruit

İsterseniz farklı meyveler bulup değiştirebilirsiniz. Ben projede bulunan resimleri aşağıya ekliyorum.

![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/apple.png)
![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/bananas.png)
![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/cherry.png)<br>
![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/grapes.png)
![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/kiwi.png)
![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/orange.png)<br>
![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/pear.png)
![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/strawberry.png)
![Screenshot](https://github.com/halilozel1903/CatchTheFruits/blob/master/app/src/main/res/drawable/watermelon.png)

<br>

## Alert Dialog Tanımı ve Kullanımı

Alert Dialog kullanıcıya bir seçim yapması için belli bir işlem bittikten sonra sorulan soru cümlecikleri diyebiliriz.
Biz projemizde kullanıcıya 10 saniye boyunca kaç adet meyve yakalayabildi onun sayısını verip. 
Tekrardan oyunu oynamak isteyip istemediğini sormak için kullanılıyoruz.

Aşağıda Alert Dialog tanımı ve kullanımı ile ilgili kodlar mevcuttur : 

```java 
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
```

<br>

## Runnables, Handler ve Timer Kullanımı

**Runnables** : İçerisindeki run metodu içinde yapılması gereken işlemler belirtilir.

**Handler ve Timer** : Belirli aralıklarla tekrarlanmasını istediğimiz olaylara yön vermemizi ve bunları 
yönetmemizi sağlayan yapılardır.

Projemizde 10'dan 0'a geriye doğru sayma işlemi yapılmaktadır. Ondan dolayı yukarıdaki yapılar kullanılmıştır.

```java 
    var handler: Handler = Handler() // handler nesnesi

    var runnable: Runnable = Runnable { } // runnable nesnesi
```
Nesne tanımları yapılmıştır.


```java 
   // 10 saniye boyunca 1 er 1 er azalan bir timer
        object : CountDownTimer(10000, 1000) {


            override fun onFinish() { // oyun bitiminde neler olacak

                tvTime.text = "Zaman Doldu." // zaman dolunca mesaj yaz.
                handler.removeCallbacks(runnable) // gelen çeğrıları sil


                for (image in imageArray) { // image array içinde dön

                    image.visibility = View.INVISIBLE // resimler gizle.
                }
          
            }

            override fun onTick(p0: Long) { // herbir saniyede neler olacak

                tvTime.text = "Time : " + p0 / 1000 // saniye cinsinden değerini yazdır.
            }


        }.start()
```

10'dan geriye doğru sayma işlemini yapan bir sınıf bulunmaktadır. Sınıfın 2 adet ise metodu eklenmelidir.
Bunlar aşağıdaki metodlardır. Bu metodlar eklenmezse uyarı verip metodları eklemenizi ister.

 - _onFinish_ : Bu metod Timer ile ilgili işlem tamamlandıktan sonra ne yapılacak onu içeren bir fonksiyondur.
Örneğimizde zaman dolunca text'e mesaj yazdırıp ekrandaki görünür meyveleri görünmez yaparak kullanıcının
meyvelere tıklayıp puanı artmasın diye. Gelecek çağrıları silerek oyunumuzu bitirmiş oluyoruz.

 - _onTick_ : Her bir saniyede ne işlem yapılacak onu belirten metoddur. Her bir saniyede saniyeyi güncelleme
işlemi yapılıp text kısmına yazılır.


