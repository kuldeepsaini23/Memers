package com.example.memers

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var currentImageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     loadMeme()
    }


private fun loadMeme() {

            progressBar.visibility = View.VISIBLE


            val url = "https://meme-api.herokuapp.com/gimme"

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    currentImageUrl = response.getString("url")
                    Glide.with(this).load(url).into(memeImageView)
                },
                Response.ErrorListener {}
            )

         MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }


    fun shareMeme(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey! checkout this Cool Meme $currentImageUrl")

        val chooser = Intent.createChooser(intent,"share this meme choosing..")
        startActivity(chooser)
    }

    fun nextMeme(view: View) {
        loadMeme()
    }

}

