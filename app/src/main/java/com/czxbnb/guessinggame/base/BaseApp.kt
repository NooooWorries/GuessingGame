package com.czxbnb.guessinggame.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        MultiDex.install(this)
    }
    companion object {
        private lateinit var context: Context
        fun getApplicationContext() : Context {
            return context
        }
    }
}