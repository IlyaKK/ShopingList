package com.ilya.shopinglist.presentation

import android.app.Application
import com.ilya.shopinglist.di.DaggerApplicationComponent

class ShoppingListApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}