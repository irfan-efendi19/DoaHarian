package com.sekolahbetasurabaya.doaharian

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvnamadoa = findViewById<TextView>(R.id.juduldoa)
        val tvarab = findViewById<TextView>(R.id.juduldoaarab)
        val tvlatin = findViewById<TextView>(R.id.latin)
        val tvarti = findViewById<TextView>(R.id.artinya)

        val doa = intent.getParcelableExtra<dataDoa>(DETAIL_DOA)

        tvnamadoa.text = doa?.title
        tvarab.text = doa?.arabic
        tvlatin.text = doa?.latin
        tvarti.text = doa?.arti

        fun share() {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    tvnamadoa.text.toString() + "\n\n" + tvarab.text.toString() + "\n\n" + tvlatin.text.toString() + "\n\n\n" + tvarti.text.toString() + "\n\n" + "Sumber : https://jabar.nu.or.id/doa/kumpulan-doa-sehari-hari-arab-latin-dan-terjemah-1-mD2xA"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        findViewById<Button>(R.id.share).apply {
            setOnClickListener {
                share()
            }
        }
        findViewById<Button>(R.id.web).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://jabar.nu.or.id/doa/kumpulan-doa-sehari-hari-arab-latin-dan-terjemah-1-mD2xA"))
            startActivity(intent)
        }


    }

    companion object {
        const val DETAIL_DOA = "detail_doa"
    }
}