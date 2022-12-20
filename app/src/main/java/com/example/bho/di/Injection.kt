package com.example.bho.di

import android.content.Context

class Injection {

    companion object{
        fun providePersonLocalSource(context: Context): PersonDataSource{
            return PersonLocalSource(context)
        }
    }
}