@file:Suppress("DEPRECATION")
package com.example.mybandlistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.BuildConfig

class Detail : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_detail)
        val tvTitle: TextView = findViewById(R.id.tv_item_title)
        val tvDescription: TextView = findViewById(R.id.tv_item_description)
        val tvYear: TextView = findViewById(R.id.tv_item_year)
        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        @Suppress("DEPRECATION") val dataBand = intent.getParcelableExtra<Data>(EXTRA_PERSON)
        if (dataBand != null) {
            Glide.with(this)
                .load(dataBand.photo) // URL Gambar
                .into(imgPhoto) // imageView mana yang akan diterapkan
            tvTitle.text = dataBand.title
            tvYear.text = dataBand.year
            tvDescription.text = dataBand.description
        }

        val actionbar = supportActionBar
        actionbar!!.title = "Detail"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
                )
                shareIntent.type = "text/plain"
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}