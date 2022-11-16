package com.example.vizesinavihazirlikornek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var editTextAd: EditText
    lateinit var editTextVize: EditText
    lateinit var editTextFinal: EditText
    lateinit var editTextSayi1: EditText
    lateinit var editTextSayi2: EditText
    lateinit var RGtakimlar :RadioGroup
    lateinit var RGtarihilider:RadioGroup
    lateinit var RadioButtonFener:RadioButton
    lateinit var RadioButtonGS:RadioButton
    lateinit var RadioButtonBjk:RadioButton
    lateinit var RadioButtonTr:RadioButton
    lateinit var RadioButtonSv:RadioButton
    lateinit var RBataturk:RadioButton
    lateinit var RBKanuni:RadioButton
    lateinit var RBFatih:RadioButton
    lateinit var RBInonu:RadioButton
    lateinit var RBCengizHan:RadioButton
    lateinit var CBamerika:CheckBox
    lateinit var CBhawai:CheckBox
    lateinit var CBmisir:CheckBox
    lateinit var CBrusya:CheckBox
    lateinit var textViewIslemSonuc: TextView
    lateinit var spinner: Spinner
    lateinit var button: Button

    fun init(){
        editTextAd = findViewById(R.id.editTextAd)
        editTextFinal = findViewById(R.id.editTextFinal)
        editTextVize = findViewById(R.id.editTextVize)
        editTextSayi1 = findViewById(R.id.editTextSayi1)
        editTextSayi2 = findViewById(R.id.editTextSayi2)
        RGtakimlar = findViewById(R.id.RGtakimlar)
        RGtarihilider = findViewById(R.id.RGtarihilider)
        RadioButtonFener = findViewById(R.id.radioButtonFener)
        RadioButtonGS = findViewById(R.id.radioButtonGS)
        RadioButtonBjk = findViewById(R.id.radioButtonBjk)
        RadioButtonSv = findViewById(R.id.radioButtonSv)
        RadioButtonTr = findViewById(R.id.radioButtonTr)
        RBFatih = findViewById(R.id.RBFatih)
        RBKanuni = findViewById(R.id.RBKanuni)
        RBCengizHan = findViewById(R.id.RBCengizHan)
        RBataturk = findViewById(R.id.RBAtaturk)
        RBInonu = findViewById(R.id.RBInonu)
        CBamerika = findViewById(R.id.CBamerika)
        CBhawai = findViewById(R.id.CBhawai)
        CBmisir = findViewById(R.id.CBmisir)
        CBrusya = findViewById(R.id.CBrusya)
        textViewIslemSonuc = findViewById(R.id.textViewIslemSonuc)
        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.button)


    } // Bunları "fun init(){}" metod'u kullanarak bu şekilde burada da bağlayabiliriz.

    var sayac = 0
    var notDurumHarf =""
    var NotSonuc = 0
    var HesapSonuc = 0
    var sayi1 = 0
    var sayi2 = 0
    var Secilenislem =""
    var Ad = ""
    var gidilenUlkeler = "" // -> sadece bu checkBox
    var secilenLider = ""
    var secilenTakim = ""
    var amerika = false; var hawai = false; var misir = false; var rusya = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        button.setOnClickListener {

            intent = Intent(this@MainActivity,ActivitySonuclar::class.java)

            Ad = editTextAd.text.toString()
            intent.putExtra("isim",Ad)

            // RadioGrouplar daki seçilen seçeneğin veri gönderimi :
            intent.putExtra("secilenlider",secilenLider)
            intent.putExtra("secilenTakim",secilenTakim)

            // Not hesaplamaları ve veri gönderimi :

            var vize = editTextVize.text.toString().toInt()
            var final = editTextFinal.text.toString().toInt()
            NotSonuc = ((vize*40)/100) + ((final*60)/100)


            intent.putExtra("NotSonuc",NotSonuc)

            if(NotSonuc<30 && NotSonuc>0) notDurumHarf = "FF"
            else if(NotSonuc<50 && NotSonuc>=30) notDurumHarf = "DD"
            else if(NotSonuc<75 && NotSonuc>=50) notDurumHarf = "CC"
            else if(NotSonuc<85 && NotSonuc>=75) notDurumHarf = "BB"
            else if(NotSonuc<=100 && NotSonuc>=85) notDurumHarf = "AA"

            intent.putExtra("HarfNotu",notDurumHarf)

            // checkBox (ülkeler) :

            if(amerika) { gidilenUlkeler += "Amerika, " }
            if(hawai) { gidilenUlkeler += "Hawai, " }
            if(rusya) { gidilenUlkeler += "Rusya, " }
            if(misir) { gidilenUlkeler += "Mısır, " }

            intent.putExtra("GidilenYerler",gidilenUlkeler) //-> Burada checkbox tan seçilen ülkeleri gönderdik !


            // Hesap Makinesi (Spinner) :

            intent.putExtra("HesaplamaSonucu",HesapSonuc)
            intent.putExtra("Secilenislem",Secilenislem)




            startActivity(intent)

        } // button sonu

        // Hesap Makinesi (Spinner) :

        // Dizi'yi (topla,çıkart,çarp,böl) string.xml de oluştururuz ve spinner a tasarım ekranından
        // spinner daki "entries" kısmına "@dizinin adını yazarak" bağlantı sağlarız.

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (sayac==0){
                    sayac++
                }
                else{
                    when(position){

                        0->{ sayi1 = editTextSayi1.text.toString().toInt()
                            sayi2 = editTextSayi2.text.toString().toInt()
                            HesapSonuc = sayi1 + sayi2
                            textViewIslemSonuc.text = HesapSonuc.toString()
                            Secilenislem = "Toplama"
                        }
                        1->{ sayi1 = editTextSayi1.text.toString().toInt()
                            sayi2 = editTextSayi2.text.toString().toInt()
                            HesapSonuc = sayi1 - sayi2
                            textViewIslemSonuc.text = HesapSonuc.toString()
                            Secilenislem = "Çıkartma"
                        }
                        2->{ sayi1 = editTextSayi1.text.toString().toInt()
                            sayi2 = editTextSayi2.text.toString().toInt()
                            HesapSonuc = sayi1 * sayi2
                            textViewIslemSonuc.text = HesapSonuc.toString()
                            Secilenislem = "Çarpma"
                        }
                        3->{ sayi1 = editTextSayi1.text.toString().toInt()
                            sayi2 = editTextSayi2.text.toString().toInt()
                            HesapSonuc = sayi1 / sayi2
                            textViewIslemSonuc.text = HesapSonuc.toString()
                            Secilenislem = "Bölme"
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }  // spinner sonu

        // RadioGroup ve RadioButton (Takımlar) Ayarlamaları :
        // RadioGroup ta metot olarak "CheckedChangeListener" ve içerisinde
        // her zaman "checkedId" olanı seçeriz (genel)!
        // RadioGroup/Button da sadece tek bir seçenek (RadioButton) seçilebilir !

        RGtakimlar.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId==R.id.radioButtonFener){ secilenTakim = "Fener Bahçe"}
            else if (checkedId==R.id.radioButtonBjk){ secilenTakim = "Beşiktaş"}
            else if (checkedId==R.id.radioButtonGS){ secilenTakim = "Galatasaray"}
            else if (checkedId==R.id.radioButtonTr){ secilenTakim = "TrabzonSpor"}
            else if (checkedId==R.id.radioButtonSv){ secilenTakim = "SivasSpor"}

        } // Seçilen takım ayarlamaları sonu


        // Seçilen Lider Ayarlamaları :

        RGtarihilider.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId== R.id.RBAtaturk){ secilenLider = "Mustafa Kemal Atatürk" }
            else if(checkedId== R.id.RBInonu){ secilenLider = "İsmet İnönü" }
            else if(checkedId== R.id.RBKanuni){ secilenLider = "Kanuni Sultan Süleymen" }
            else if(checkedId== R.id.RBFatih){ secilenLider = "Fatih Sultan Mehmet" }
            else if(checkedId== R.id.RBCengizHan){ secilenLider = "Cengiz Han" }
        } // Seçilen Lider Ayarları sonu




        // CheckBox Ayarlamaları :
        // checkBox ta metot olarak "CheckedChangeListener" ve içerisinde
        // her zaman "İsChecked" olanı seçeriz Bu ayrım Çok Önemli Karıştırılmamalıdır !
        // CheckBox larda bir den fazla seçenek (checkBox) seçebiliriz !

        CBamerika.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){ amerika=true }
        }
        CBhawai.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){ hawai=true }
        }
        CBmisir.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){ misir=true }
        }
        CBrusya.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){ rusya=true }
        }

    }
}

// Önemli Not :
/*
 Bir liste ve Adapter değişkeni oluşturup adapter üzerinden spinner ın tasarımını yapıp, oluşturduğumuz diziyi
 bu adapter a aktarıp daha sonra bu oluşturduğumuz adapter tasarımını Adapter değişkenine atayıp ve onu da
 spinner nesnesine atamamıza gerek yoktur bunun çok daha kısa bir yolu vardır o da ;
 "string" dosyasında bir dizi oluşturup daha sonra tasarım ekranının "code" veya "split" (aynı şeyler)
 kısımından spinner nesnesine gelip "entries" yazıp çıkan yapıpyı seçip sonra tırnak içerisindeki
 kısma başına "@" işareti koyarak "string.xml" deki oluşturduğumuz liste nin adını yazarız
 ya da "code" veya "split" kısımına girmeden tasarım ekranından spinner ın "arttibutes" ten "entries"
 kısmına ulaşarak oraya başına "@" işareti koyarak string.xml dosyasında oluşturduğumuz ve spinner a aktarmak
 istediğimiz liste'nin adını yazarız.
 */