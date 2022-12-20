package com.example.bho

import android.content.Context
import com.example.bho.models.Person
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject

abstract class AppDatabase: RealmObject {


    companion object {
        private var instance: RealmConfiguration? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = RealmConfiguration.Builder(schema = setOf(
            Person::class)).build()

    }
}