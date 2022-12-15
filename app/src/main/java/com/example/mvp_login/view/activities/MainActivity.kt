package com.example.mvp_login.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mvp_login.data.model.UserEntity
import com.example.mvp_login.databinding.ActivityMainBinding
import com.example.mvp_login.extentions.Category
import com.example.mvp_login.extentions.CategoryError
import com.example.mvp_login.presenter.MainPresenter
import com.example.mvp_login.utils.Constant
import com.example.mvp_login.view.`interface`.MainView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter
    private var account: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        setEvenClickLogin()

    }

    private fun setEvenClickLogin() {
        binding.floatingBottomLogin.setOnClickListener {
            if (checkEmpty()) {
                presenter.login(account!!.toInt(), password!!.toInt())
            }
        }
    }

    private fun checkEmpty(): Boolean {
        account = binding.edtAccount.text.toString().trim()
        password = binding.edtPassword.text.toString().trim()
        return if (account == Constant.NULL && password == Constant.NULL) {
            Snackbar.make(
                binding.layoutHeartder,
                Category.EmptyAll.CategoryError,
                Snackbar.LENGTH_SHORT
            ).show()
            false
        } else if (account == Constant.NULL) {
            Snackbar.make(
                binding.layoutHeartder,
                Category.EmptyAccount.CategoryError,
                Snackbar.LENGTH_LONG
            ).show()
            false
        } else if (password == Constant.NULL) {
            Snackbar.make(
                binding.layoutHeartder,
                Category.EmptyPassword.CategoryError,
                Snackbar.LENGTH_LONG
            ).show()
            false
        } else {
            true
        }
    }

    override fun LoginSuccessful(list: List<UserEntity>) {
        if (list.isNotEmpty()) {
            Snackbar.make(
                binding.layoutHeartder,
                Category.Successfully.CategoryError,
                Snackbar.LENGTH_LONG
            ).show()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Snackbar.make(
                binding.layoutHeartder,
                Category.WrongAccount.CategoryError,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun LoginError(e: Throwable) {
        Log.d("this", e.toString())
    }
}