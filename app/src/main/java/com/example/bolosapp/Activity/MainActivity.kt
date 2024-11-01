package com.example.bolosapp.Activity

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import eightbitlab.com.blurview.RenderScriptBlur
import com.example.bolosapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setBlurEffect()
    }

    private fun setVariable(){
        binding.apply {
            btn1.setOnClickListener {startListActivity(1, "Ice Drink")}
            btn2.setOnClickListener {startListActivity(2, "Hot Drink")}
            btn3.setOnClickListener {startListActivity(3, "Coffee")}
            btn4.setOnClickListener {startListActivity(4, "Milk-Shake")}
            btn5.setOnClickListener {startListActivity(5, "Juices")}
            btn6.setOnClickListener {startListActivity(6, "Ice Cream")}
            btn7.setOnClickListener {startListActivity(7, "Lunch")}
            btn8.setOnClickListener {startListActivity(8, "Desserts")}
            btn9.setOnClickListener {startListActivity(9, "Cakes")}
        }
    }

    private fun startListActivity(id:Int,title:String){
        val intent = Intent(this,ListActivity::class.java)
        intent.putExtra("id",id)
        intent.putExtra("title", title)
        startActivity(intent)
    }

    private fun setBlurEffect() {
        val radius = 10f
        val decorView = window.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background

        binding.blurView.setupWith(rootView, RenderScriptBlur(this))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)

        binding.blurView.setOutlineProvider(ViewOutlineProvider.BACKGROUND)
        binding.blurView.setClipToOutline(true)
    }
}


