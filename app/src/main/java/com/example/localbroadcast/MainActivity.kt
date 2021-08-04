package com.example.localbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.localbroadcast.Myfirebasemessagingservice.Companion.factorupdate
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var localBroadcastManager: LocalBroadcastManager
    private lateinit var getB_Receiver: GetBroadcast
    private lateinit var factorfilter: IntentFilter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            localBroadcastManager = LocalBroadcastManager.getInstance(applicationContext)
            getB_Receiver = GetBroadcast()
            factorfilter = IntentFilter(factorupdate)

        }

        override fun onResume() {
            super.onResume()
            localBroadcastManager.registerReceiver(getB_Receiver, factorfilter)
        }

        override fun onPause() {
            super.onPause()
            localBroadcastManager.unregisterReceiver(getB_Receiver)
        }

        inner class GetBroadcast : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {


                if(intent.action == factorupdate)
                {

                    val is_msg2 = intent.getStringExtra("DATA")
                    hello.text = intent.getStringExtra(("DATA"))

                    Toast.makeText(this@MainActivity, "$is_msg2", Toast.LENGTH_LONG).show()
                }
            }
        }
        companion object {
            private const val TAG = "MainActivity"
            const val factorupdate = "com.example.localbroadcast"
        }
    }
