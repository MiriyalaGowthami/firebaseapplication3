package com.example.firebaseapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private  val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.sendData.setOnClickListener{
            SendDataActivity.launch(this@MainActivity)

        }

        binding.getData.setOnClickListener{
            GetDataActivity.launch(this@MainActivity)

        }


    }
}