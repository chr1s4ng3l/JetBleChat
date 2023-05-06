package com.tamayo.jetblechat.di

import android.content.Context
import com.tamayo.jetblechat.data.chat.AndroidBluetoothController
import com.tamayo.jetblechat.data.chat.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BluetoothModule {

    @Provides
    @Singleton
    fun providesBluetoothController(@ApplicationContext context: Context): BluetoothController =
        AndroidBluetoothController(context)


}