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


    } // Bunlar?? "fun init(){}" metod'u kullanarak bu ??ekilde burada da ba??layabiliriz.

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

            // RadioGrouplar daki se??ilen se??ene??in veri g??nderimi :
            intent.putExtra("secilenlider",secilenLider)
            intent.putExtra("secilenTakim",secilenTakim)

            // Not hesaplamalar?? ve veri g??nderimi :

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

            // checkBox (??lkeler) :

            if(amerika) { gidilenUlkeler += "Amerika, " }
            if(hawai) { gidilenUlkeler += "Hawai, " }
            if(rusya) { gidilenUlkeler += "Rusya, " }
            if(misir) { gidilenUlkeler += "M??s??r, " }

            intent.putExtra("GidilenYerler",gidilenUlkeler) //-> Burada checkbox tan se??ilen ??lkeleri g??nderdik !


            // Hesap Makinesi (Spinner) :

            intent.putExtra("HesaplamaSonucu",HesapSonuc)
            intent.putExtra("Secilenislem",Secilenislem)




            startActivity(intent)

        } // button sonu

        // Hesap Makinesi (Spinner) :

        // Dizi'yi (topla,????kart,??arp,b??l) string.xml de olu??tururuz ve spinner a tasar??m ekran??ndan
        // spinner daki "entries" k??sm??na "@dizinin ad??n?? yazarak" ba??lant?? sa??lar??z.

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
                            Secilenislem = "????kartma"
                        }
                        2->{ sayi1 = editTextSayi1.text.toString().toInt()
                            sayi2 = editTextSayi2.text.toString().toInt()
                            HesapSonuc = sayi1 * sayi2
                            textViewIslemSonuc.text = HesapSonuc.toString()
                            Secilenislem = "??arpma"
                        }
                        3->{ sayi1 = editTextSayi1.text.toString().toInt()
                            sayi2 = editTextSayi2.text.toString().toInt()
                            HesapSonuc = sayi1 / sayi2
                            textViewIslemSonuc.text = HesapSonuc.toString()
                            Secilenislem = "B??lme"
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }  // spinner sonu

        // RadioGroup ve RadioButton (Tak??mlar) Ayarlamalar?? :
        // RadioGroup ta metot olarak "CheckedChangeListener" ve i??erisinde
        // her zaman "checkedId" olan?? se??eriz (genel)!
        // RadioGroup/Button da sadece tek bir se??enek (RadioButton) se??ilebilir !

        RGtakimlar.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId==R.id.radioButtonFener){ secilenTakim = "Fener Bah??e"}
            else if (checkedId==R.id.radioButtonBjk){ secilenTakim = "Be??ikta??"}
            else if (checkedId==R.id.radioButtonGS){ secilenTakim = "Galatasaray"}
            else if (checkedId==R.id.radioButtonTr){ secilenTakim = "TrabzonSpor"}
            else if (checkedId==R.id.radioButtonSv){ secilenTakim = "SivasSpor"}

        } // Se??ilen tak??m ayarlamalar?? sonu


        // Se??ilen Lider Ayarlamalar?? :

        RGtarihilider.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId== R.id.RBAtaturk){ secilenLider = "Mustafa Kemal Atat??rk" }
            else if(checkedId== R.id.RBInonu){ secilenLider = "??smet ??n??n??" }
            else if(checkedId== R.id.RBKanuni){ secilenLider = "Kanuni Sultan S??leymen" }
            else if(checkedId== R.id.RBFatih){ secilenLider = "Fatih Sultan Mehmet" }
            else if(checkedId== R.id.RBCengizHan){ secilenLider = "Cengiz Han" }
        } // Se??ilen Lider Ayarlar?? sonu




        // CheckBox Ayarlamalar?? :
        // checkBox ta metot olarak "CheckedChangeListener" ve i??erisinde
        // her zaman "??sChecked" olan?? se??eriz Bu ayr??m ??ok ??nemli Kar????t??r??lmamal??d??r !
        // CheckBox larda bir den fazla se??enek (checkBox) se??ebiliriz !

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

// ??nemli Not :
/*
 Bir liste ve Adapter de??i??keni olu??turup adapter ??zerinden spinner ??n tasar??m??n?? yap??p, olu??turdu??umuz diziyi
 bu adapter a aktar??p daha sonra bu olu??turdu??umuz adapter tasar??m??n?? Adapter de??i??kenine atay??p ve onu da
 spinner nesnesine atamam??za gerek yoktur bunun ??ok daha k??sa bir yolu vard??r o da ;
 "string" dosyas??nda bir dizi olu??turup daha sonra tasar??m ekran??n??n "code" veya "split" (ayn?? ??eyler)
 k??s??m??ndan spinner nesnesine gelip "entries" yaz??p ????kan yap??py?? se??ip sonra t??rnak i??erisindeki
 k??sma ba????na "@" i??areti koyarak "string.xml" deki olu??turdu??umuz liste nin ad??n?? yazar??z
 ya da "code" veya "split" k??s??m??na girmeden tasar??m ekran??ndan spinner ??n "arttibutes" ten "entries"
 k??sm??na ula??arak oraya ba????na "@" i??areti koyarak string.xml dosyas??nda olu??turdu??umuz ve spinner a aktarmak
 istedi??imiz liste'nin ad??n?? yazar??z.
 */