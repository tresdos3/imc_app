package com.tresdos.imc_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var isViewMaleSelected = true
    private var isViewFemaleSelected = false
    private var initialAge: Int = 18
    private var initialWeight: Int = 60
    private var initialHeight: Int = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnWeightMinus: FloatingActionButton
    private lateinit var btnWeightPlus: FloatingActionButton
    private lateinit var btnAgeMinus: FloatingActionButton
    private lateinit var btnAgePlus: FloatingActionButton
    private lateinit var btnCalculate: AppCompatButton

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initListeners()
        initUI()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        btnWeightPlus = findViewById(R.id.btnWeightPlus)
        btnWeightMinus = findViewById(R.id.btnWeightMinus)
        btnAgePlus = findViewById(R.id.btnAgePlus)
        btnAgeMinus = findViewById(R.id.btnAgeMinus)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val decimalForm = DecimalFormat("#.##")
            initialHeight = decimalForm.format(value).toInt()
            tvHeight.text = "$initialHeight cm"
        }

        btnAgeMinus.setOnClickListener {
            if (initialAge > 0) {
                initialAge -= 1
                setAgeText()
            }
        }

        btnAgePlus.setOnClickListener {
            if (initialAge < 100) {
                initialAge += 1
                setAgeText()
            }
        }

        btnWeightMinus.setOnClickListener {
            if (initialWeight > 0) {
                initialWeight -= 1
                setWeightText()
            }
        }

        btnWeightPlus.setOnClickListener {
            if (initialWeight < 100) {
                initialWeight += 1
                setWeightText()
            }
        }

        btnCalculate.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(IMC_KEY, calculateIMC())
            startActivity(intent)
        }
    }

    private fun calculateIMC(): Double {
        val dFormat = DecimalFormat("#.##")
        val imc = initialWeight / (initialHeight.toDouble() /100 * initialHeight.toDouble()/100)
        return dFormat.format(imc).toDouble()
    }

    private fun setAgeText() {
        tvAge.text = initialAge.toString()
    }

    private fun setWeightText() {
        tvWeight.text = initialWeight.toString()
    }

    private fun changeGender() {
        isViewMaleSelected = !isViewMaleSelected
        isViewFemaleSelected = !isViewFemaleSelected
    }

    private fun setAge() {
        tvAge.text = initialAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = initialWeight.toString()
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isViewMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isViewFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedColor: Boolean): Int {
        val selectedColor = if (isSelectedColor) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, selectedColor)
    }

    private fun initUI() {
        setGenderColor()
        setAge()
        setWeight()
    }
}