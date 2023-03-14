package com.ilya.shopinglist.di

import android.app.Application
import com.ilya.shopinglist.data.AppDataBase
import com.ilya.shopinglist.data.ShopListDao
import com.ilya.shopinglist.data.ShopListRepositoryImpl
import com.ilya.shopinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDataBase.getInstance(application).shopListDao()
        }
    }
}