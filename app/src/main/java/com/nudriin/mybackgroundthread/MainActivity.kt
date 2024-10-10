package com.nudriin.mybackgroundthread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nudriin.mybackgroundthread.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create executor
        val executor = Executors.newSingleThreadExecutor()
        // create handler
        val handler = Handler(Looper.getMainLooper())

        binding.btnStart.setOnClickListener {
            executor.execute {
                try {
                    for (i in 0..10) {
                        Thread.sleep(500)
                        val percentage = i * 10
                        if(percentage == 100) {
                            // handle async with handler
                            handler.post{
                                binding.tvStatus.setText(R.string.task_completed)
                            }
                        } else {
                            binding.tvStatus.text = String.format(getString(R.string.compressing), percentage)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}