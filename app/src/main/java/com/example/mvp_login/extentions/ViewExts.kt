package com.example.mvp_login.extentions

enum class Category{
    EmptyAccount,
    EmptyPassword,
    EmptyAll,
    WrongAccount,
    Successfully,
}
val Category.CategoryError: String
    get() = when (this) {
        Category.EmptyAccount -> "Bạn chưa nhập tài khoản"
        Category.EmptyPassword -> "Bạn chưa nhập mật khẩu"
        Category.EmptyAll -> "Vui lòng nhập tài khoản và mật khẩu"
        Category.WrongAccount -> "Sai tài khoản hoặc mật khẩu"
        Category.Successfully -> "Đăng nhập thành công"
    }
