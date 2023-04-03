package com.tresdos.imc_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.tresdos.imc_app.MainActivity.Companion.IMC_KEY

class ResultActivity : AppCompatActivity() {
    private lateinit var tvStatus: TextView
    private lateinit var tvResultWeight: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initComponent()
        initListeners()
        initUI(getWeight())
    }

    private fun initComponent() {
        tvStatus = findViewById(R.id.tvStatus)
        tvResultWeight = findViewById(R.id.tvResultWeight)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initUI(result: Double) {
        tvResultWeight.text = result.toString()
        when(result) {
            in 0.00..18.50 -> {
                tvStatus.text = getString(R.string.under_weight)
                tvDescription.text = getString(R.string.under_weight_desc)
            }
            in 18.51..24.99-> {
                tvStatus.text = getString(R.string.under_normal)
                tvDescription.text = getString(R.string.under_normal_desc)
            }
            in 25.00..29.99-> {
                tvStatus.text = getString(R.string.under_overweight)
                tvDescription.text = getString(R.string.under_overweight_desc)
            }
            in 030.00..99.00 -> {
                tvStatus.text = getString(R.string.under_obesity)
                tvDescription.text = getString(R.string.under_obesity_desc)
            }
            else -> {
                tvStatus.text = getString(R.string.error)
                tvResultWeight.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun getWeight(): Double {
        return intent.extras?.getDouble(IMC_KEY) ?: -1.0
    }

}