package com.example.vizesinavihazirlikornek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActivitySonuclar : AppCompatActivity() {

    lateinit var textViewAd: TextView
    lateinit var textViewTakim: TextView
    lateinit var textViewLider: TextView
    lateinit var textViewUlkeler: TextView
    lateinit var textViewNotOrtalama: TextView
    lateinit var textViewHarfNotu: TextView
    lateinit var textViewHesapSonucu: TextView
    lateinit var textViewSecilenislem: TextView

    fun init(){
        textViewAd = findViewById(R.id.textViewAd)
        textViewTakim = findViewById(R.id.textViewTakim)
        textViewLider = findViewById(R.id.textViewLider)
        textViewUlkeler = findViewById(R.id.textViewUlkeler)
        textViewNotOrtalama = findViewById(R.id.textViewNotOrtalama)
        textViewHarfNotu = findViewById(R.id.textViewHarfNotu)
        textViewHesapSonucu = findViewById(R.id.textViewHesapSonucu)
        textViewSecilenislem = findViewById(R.id.textViewSecilenislem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_)

        init()

        var bundle : Bundle
        bundle = intent.extras!!

        var Ad = bundle?.get("isim")
        textViewAd.text = Ad.toString()

        var Takim = bundle?.get("secilenTakim")
        textViewTakim.text = Takim.toString()

        var Lider = bundle?.get("secilenlider")
        textViewLider.text = Lider.toString()

        var NotSonuc = bundle?.get("NotSonuc")
        textViewNotOrtalama.text = NotSonuc.toString()

        var NotSonucHarf = bundle?.get("HarfNotu")
        textViewHarfNotu.text = NotSonucHarf.toString()

        var GidilenYerler = bundle?.get("GidilenYerler")
        textViewUlkeler.text = GidilenYerler.toString()

        var HesapSonucu = bundle?.get("HesaplamaSonucu")
        textViewHesapSonucu.text = HesapSonucu.toString()

        var Secilenislem = bundle?.get("Secilenislem")
        textViewSecilenislem.text = Secilenislem.toString()








    }
}