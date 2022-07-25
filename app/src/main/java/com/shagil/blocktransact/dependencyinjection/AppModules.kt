package com.shagil.blocktransact.dependencyinjection

import android.app.Application
import androidx.room.Room
import com.shagil.blocktransact.data.local.BlockTransactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

//    @Provides
//    @Singleton
//    fun provideStockApi():StockApi {
//        return Retrofit.Builder()
//            .baseUrl(StockApi.BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//            .create()
//    }

    @Provides
    @Singleton
    fun provideAppDataBase(app: Application) : BlockTransactDatabase {
        return Room.databaseBuilder(
            app,
            BlockTransactDatabase::class.java,
            "blockTransactDB.db"
        ).build()
    }
}