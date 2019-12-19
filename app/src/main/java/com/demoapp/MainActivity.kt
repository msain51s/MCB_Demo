package com.demoapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demoapp.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var value1: EditText
    private lateinit var value2: EditText
    private lateinit var seats: EditText
    private lateinit var treats: EditText
    private lateinit var draw: EditText
    private lateinit var calculate:Button
    private lateinit var result:Button
    private lateinit var output1:TextView
    private lateinit var output2:TextView
    private lateinit var output3:TextView
    private lateinit var pb:ProgressBar
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initUI()
        configureObserver()

        calculate.setOnClickListener {

            pb.visibility=View.VISIBLE
            var firstValue = value1.text.toString()
            var secondValue = value2.text.toString()
            viewModel.doCalculation(firstValue, secondValue)
        }

        result.setOnClickListener {

            viewModel.getResults(treats.text.toString().toInt(),
                seats.text.toString().toInt(),draw.text.toString().toInt())
        }

    }

    private fun configureObserver() {

        viewModel.getResponse().observe(this, Observer {
            pb.visibility=View.GONE
            output1.text = "Output : " + it.result
        })

        viewModel.getLastSeatNoGettingTreat().observe(this, Observer {
            output2.text="Output : Last Seat No. "+it
        })

        viewModel.getResultMap().observe(this, Observer {
            output3.text=it.toString()
        })
    }

    private fun initUI() {
        value1 = findViewById(R.id.value1)
        value2 = findViewById(R.id.value2)
        calculate = findViewById(R.id.calculate)
        output1 = findViewById(R.id.output1)
        output2 = findViewById(R.id.output2)
        output3 = findViewById(R.id.output3)
        treats = findViewById(R.id.treats)
        seats = findViewById(R.id.seats)
        draw = findViewById(R.id.draw)
        draw = findViewById(R.id.draw)
        result = findViewById(R.id.resultButton)
        pb = findViewById(R.id.pb)
    }
}
