package com.example.electronics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class Product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        onInit()
        gotoHome()
    }

    fun gotoHome() {
        val home = findViewById<Button>(R.id.home)
        home.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun onInit() {
        var sintent = intent
        var product = sintent.getSerializableExtra("product")
        if(product != null) {
            product = product as Electronics
            println("the product is" + product.name)
            val name = findViewById<TextView>(R.id.name)
            val img = findViewById<ImageView>(R.id.image)
            val price = findViewById<TextView>(R.id.price)
            val desc = findViewById<TextView>(R.id.description)
            val imgResource = resources.getIdentifier(product.img, "drawable", packageName)

            img.setImageResource(imgResource)
            name.text = product.name
            desc.text = product.description
            price.text = product.price.toString()
        }
    }
}