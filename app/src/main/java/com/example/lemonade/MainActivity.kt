/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var lemonadeState = "SELECT"
    private var lemonSize = -1
    private var squeezeCount = -1

    private var lemonImage: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lemonImage = findViewById(R.id.imageView)
        setViewElements()
        lemonImage!!.setOnClickListener {
            clickLemonImage()
        }
        lemonImage!!.setOnLongClickListener {
            showSnackbar()
        }
    }

    private fun clickLemonImage() {
        val  lem =  LemonTree()
        when(lemonadeState){
            "SELECT" -> {
                        lemonadeState="SQUEEZE"
                        lemonSize = lem.pick()
                        squeezeCount=0
                        setViewElements()
            }
            "SQUEEZE" -> {
                        squeezeCount++
                        lemonSize--
                        if(lemonSize==0)
                        {
                            lemonadeState="DRINK"
                            setViewElements()
                            lemonSize=-1
                        }
                        }
            "DRINK"-> {
                        lemonadeState="RESTART"
                        setViewElements()
                        }
            "RESTART"->{
                        lemonadeState="SELECT"
                        setViewElements()
                        }
        }
    }

    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.text_action)
        when(lemonadeState) {
            "SELECT" -> {
                val lemImage: ImageView = findViewById(R.id.imageView)
                textAction.text = "Click to select a lemon!"
                val resLemImage = R.drawable.lemon_tree
                lemImage.setImageResource(resLemImage)
            }
            "SQUEEZE" -> {
                val lemImage: ImageView = findViewById(R.id.imageView)
                textAction.text = "Click to juice the lemon!"
                val resLemImage = R.drawable.lemon_squeeze
                lemImage.setImageResource(resLemImage)
            }
            "DRINK" -> {
                val lemImage: ImageView = findViewById(R.id.imageView)
                textAction.text = "Click to drink your lemonade!"
                val resLemImage = R.drawable.lemon_drink
                lemImage.setImageResource(resLemImage)
            }
            "RESTART" -> {
                val lemImage: ImageView = findViewById(R.id.imageView)
                textAction.text = "Click to start again!"
                val resLemImage = R.drawable.lemon_restart
                lemImage.setImageResource(resLemImage)}
        }
    }
    private fun showSnackbar(): Boolean {
        if (lemonadeState != "SQUEEZE") {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(findViewById(R.id.constraint_Layout), squeezeText, Snackbar.LENGTH_SHORT).show()
        return true
    }
}
class LemonTree {
    fun pick(): Int {
        return (2..4).random()
    }
}
