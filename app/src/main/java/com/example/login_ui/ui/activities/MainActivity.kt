package com.example.login_ui.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.login_ui.MyApplication
import com.example.login_ui.databinding.ActivityMainBinding
import com.example.login_ui.extentions.Category
import com.example.login_ui.extentions.CategoryError
import com.example.login_ui.utils.Constant
import com.example.login_ui.viewModel.UserLoginViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModel: UserLoginViewModel

    private var account: String?= null
    private var password: String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as MyApplication).appComponent.inject(this)
        setEvenClickLogin()
    }

    private fun setEvenClickLogin() {
        binding.floatingBottomLogin.setOnClickListener {
            if (checkEmpty()){
                Log.d("this", "Click bottom login")
                viewModel.getUserData(account!!.toInt(), password!!.toInt()).observe(this){Success->
                    Log.d("this", "Result: $Success")
                    if (Success.isNotEmpty()){
                        Snackbar.make(binding.layoutContainer, Category.Successfully.CategoryError, Snackbar.LENGTH_LONG).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Snackbar.make(binding.layoutContainer, Category.WrongAccount.CategoryError, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun checkEmpty(): Boolean{
        account = binding.edtAccount.text.toString().trim()
        password = binding.edtPassword.text.toString().trim()
        return if (account == Constant.NULL && password == Constant.NULL){
            Snackbar.make(binding.layoutContainer, Category.EmptyAll.CategoryError, Snackbar.LENGTH_SHORT).show()
            false
        }else if (account == Constant.NULL){
            Snackbar.make(binding.layoutContainer, Category.EmptyAccount.CategoryError, Snackbar.LENGTH_LONG).show()
            false
        }else if(password == Constant.NULL){
            Snackbar.make(binding.layoutContainer, Category.EmptyPassword.CategoryError, Snackbar.LENGTH_LONG).show()
            false
        }else{
            true
        }
    }
}