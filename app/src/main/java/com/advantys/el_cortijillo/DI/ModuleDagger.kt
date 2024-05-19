package com.advantys.el_cortijillo.DI

import android.content.Context
import com.advantys.el_cortijillo.Data.Database.BD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDagger {
    @Singleton
    @Provides
    fun provideBD(@ApplicationContext contexto: Context): BD {
        return BD(contexto)
    }
}