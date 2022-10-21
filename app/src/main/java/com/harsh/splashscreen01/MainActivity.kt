package com.harsh.splashscreen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.harsh.kafka.ProducerAndConsumer
import com.harsh.splashscreen01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sendingData()
        kafka()
    }

    private fun sendingData() {
        binding.SendMsgBtn.setOnClickListener {
            Log.e("msg", binding.Message.text.toString())
            binding.text.text = binding.Message.text.toString()
        }
    }


    private fun kafka() {
        binding.Kafka.setOnClickListener {
            ProducerAndConsumer.senderAndReciver()
        }
    }
}
