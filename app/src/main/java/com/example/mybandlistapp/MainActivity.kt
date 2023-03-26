package com.example.mybandlistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvBand: RecyclerView
    private val list = ArrayList<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() //adding a splash screen
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        rvBand = findViewById(R.id.rv_band)
        rvBand.setHasFixedSize(true)

        list.addAll(getMetalcore())
        showRecyclerList()
    }

    private fun getMetalcore(): ArrayList<Data> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listMetalcore = ArrayList<Data>()
        for (i in 0 until 10) {
            listMetalcore.add(Data(dataTitle[i], dataYear[i], dataDescription[i], dataPhoto[i]))
        }
        return listMetalcore
    }

    private fun showRecyclerList() {
        rvBand.layoutManager = LinearLayoutManager(this)
        val listBandadapter = ListAdapter(list)
        rvBand.adapter = listBandadapter

        listBandadapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Data) {
                val detailbanddata = Intent(this@MainActivity, Detail::class.java)
                detailbanddata.putExtra(Detail.EXTRA_PERSON, data)
                startActivity(detailbanddata)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_menu -> {
                val goAbout = Intent(this@MainActivity, About::class.java)
                startActivity(goAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
