package com.example.electronics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    var items: Array<Electronics> = arrayOf(
        Electronics("Ipad 9th gen 10.1\"", "128 GB Storage\n3GB RAM", "apple", "ipad", 329.0),
        Electronics("Iphone SE 2023", "64GB Storage\n2GB RAM", "apple", "iphonese", 299.0)
    )
    var selectedItems: Array<String> = arrayOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listItems()
    }

    fun listItems() {
        val container = findViewById<LinearLayout>(R.id.cotainer)

        for (e: Electronics in items) {
            val wrapper = LinearLayout(this)
            wrapper.orientation = LinearLayout.HORIZONTAL
            wrapper.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            val img = ImageView(this)
            img.layoutParams = LinearLayout.LayoutParams(
                300,
                400
            )
            val imgResource = resources.getIdentifier(e.img, "drawable", packageName)
            img.setImageResource(imgResource)
            wrapper.addView(img)

            val details = LinearLayout(this)
            details.orientation = LinearLayout.VERTICAL

//            title and logo
            val titleLogo = LinearLayout(this)
            titleLogo.orientation = LinearLayout.HORIZONTAL
            val logo = ImageView(this)
            logo.layoutParams = LinearLayout.LayoutParams(
                30,
                30
            )
            val logoResource = resources.getIdentifier(e.logo, "drawable", packageName)
            logo.setImageResource(logoResource)
            titleLogo.setOnClickListener{
                var intent = Intent(this, Product::class.java)
                intent.putExtra("product", e)
                startActivity(intent)
            }
            titleLogo.addView(logo)


            val product = TextView(this)
            product.text = e.name
            titleLogo.addView(product)
            details.addView(titleLogo)

//            Price and Description
            val priceDesc = LinearLayout(this)
            priceDesc.orientation = LinearLayout.HORIZONTAL

//            Description
            val desc = TextView(this)
            desc.text = e.description
            desc.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            priceDesc.addView(desc)

//            Price
            val price = TextView(this)
            price.text = e.price.toString()
            priceDesc.addView(price)

            details.addView(priceDesc)

//            Add Product
            val add = Button(this)
            add.text = "Add"
            details.addView(add)
            add.setOnClickListener{
                val pos = selectedItems.indexOf(e.name)
                if(pos == -1) {
                    selectedItems += e.name.toString()
                }
            }


            wrapper.addView(details)
            container.addView(wrapper)

        }
        val viewCart = Button(this)
        viewCart.text = "View Cart"
        viewCart.setOnClickListener{
            var msg: String = ""
            for(str in selectedItems) {
                if(msg == "") {
                    msg += str
                } else {
                    msg += "\n$str"
                }
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        container.addView(viewCart)
    }
}

data class Electronics(
    var name: String,
    var description: String,
    var logo: String,
    var img: String,
    var price: Double
):Serializable