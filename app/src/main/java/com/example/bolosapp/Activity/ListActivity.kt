package com.example.bolosapp.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bolosapp.Model.Items.Model
import com.example.bolosapp.R
import com.example.bolosapp.ViewModel.MainViewModel
import com.example.bolosapp.adapter.ListItemAdapter
import com.example.bolosapp.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var context: Context
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        context = this@ListActivity
        setContentView(binding.root)

        binding.menuBtn.setOnClickListener {
            startActivity(
                Intent(this@ListActivity, MainViewModel::class.java) // Substitua 'AnotherActivity' pela Activity correta
            )
        }

        binding.titleTxt.text = intent.getStringExtra("title")
        val i = intent.getIntExtra("id", 0)

        when (i) {
            1 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_1))
            2 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_2))
            3 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_3))
            4 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_4))
            5 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_5))
            6 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_6))
            7 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_7))
            8 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_8))
            9 -> binding.catImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_9))
        }

        binding.progressBar.visibility = View.VISIBLE

        viewModel.loadFiltered(i).observe(this) { item ->
            if (item.isEmpty()) {
                binding.emptyText.visibility = View.VISIBLE
                binding.view.visibility = View.GONE
            } else {
                binding.emptyText.visibility = View.GONE
                binding.view.visibility = View.VISIBLE

                binding.adapter.layoutManager = LinearLayoutManager(
                    this@ListActivity,
                    LinearLayoutManager.VERTICAL, false
                )
                binding.adapter.adapter = ListItemAdapter(item)

            }
            binding.progressBar.visibility = View.GONE
        }
    }
}
