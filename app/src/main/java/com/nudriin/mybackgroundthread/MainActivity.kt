package com.nudriin.mybackgroundthread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.nudriin.mybackgroundthread.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // Create executor
//        val executor = Executors.newSingleThreadExecutor() // create only one thread
//        // create handler
//        val handler = Handler(Looper.getMainLooper()) // proses yang di dalam Handler dijalankan di main/ui thread

        binding.btnStart.setOnClickListener {

            lifecycleScope.launch(Dispatchers.Default) {
                try {
                    for (i in 0..10) {
                        delay(500)
                        val percentage = i * 10

                        //update ui in main thread
                        // change thread to main thread
                        withContext(Dispatchers.Main) {
                            if(percentage == 100) {
                                binding.tvStatus.setText(R.string.task_completed)
                            } else {
                                binding.tvStatus.text = String.format(getString(R.string.compressing), percentage)
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}