package com.ilya.shopinglist.di

import android.app.Application
import com.ilya.shopinglist.data.ShopListProvider
import com.ilya.shopinglist.presentation.MainActivity
import com.ilya.shopinglist.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(shopItemFragment: ShopItemFragment)

    fun inject(shopListProvider: ShopListProvider)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}