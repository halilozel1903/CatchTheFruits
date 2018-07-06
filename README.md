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


## Projenin İçeriği

Projede aşağıdaki yapılar ve bileşenler kullanılmıştır :  <br>

- Alert Dialog
- Timers
- Runnable ve Handler
- Grid Layout
- Constraint Layout
- Button
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
