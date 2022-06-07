package com.vexmodeldes.orlymeditation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso

class description : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        var imageView = findViewById<ImageView>(R.id.imageView)
        var closeBut = findViewById<Button>(R.id.closeBut)
        Picasso.get().load("https://asanaonline.ru/upload/iblock/0f3/0f3b3ca9b433c11cdc2dbf62b5c40f16.jpg").into(imageView);

        closeBut!!.setOnClickListener {
            this.finish()
        }
    }
}