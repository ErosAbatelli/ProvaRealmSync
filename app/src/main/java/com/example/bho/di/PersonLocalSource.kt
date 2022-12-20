package com.example.bho.di

import android.content.Context
import com.example.bho.AppDatabase
import com.example.bho.models.Person
import io.reactivex.Observable
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.Dispatchers

class PersonLocalSource(private val context: Context): PersonDataSource {
    private val config = AppDatabase.invoke(context)
    private val realm = Realm.open(config)

    override fun insertPerson(namePerson: String, agePerson: Int): Observable<Person> {
        val obs = Observable.create<Person>{
            val person = Person().apply {
                name = namePerson
                age = agePerson
            }
            realm.writeBlocking {
                copyToRealm(person)
            }
            it.onNext(person)
        }
        return obs
    }

    override fun getAllPerson(): Observable<RealmResults<Person>> {
        val obs = Observable.create<RealmResults<Person>>{
            val allPerson = realm.query<Person>().find()
            it.onNext(allPerson)
        }
        return obs
    }


}

