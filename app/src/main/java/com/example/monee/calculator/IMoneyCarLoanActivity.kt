package com.example.monee.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.monee.R

class IMoneyCarLoanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imoney_car_loan)
        val webViewCarLoan: WebView = findViewById(R.id.carLoan_webview)

        webViewCarLoan.loadUrl("https://www.imoney.my/car-loan")
    }
}