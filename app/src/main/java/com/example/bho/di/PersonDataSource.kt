package com.example.bho.di

import com.example.bho.models.Person
import io.reactivex.Observable
import io.realm.kotlin.query.RealmResults

interface PersonDataSource {

    fun insertPerson(namePerson: String, agePerson: Int): Observable<Person>
    fun getAllPerson(): Observable<RealmResults<Person>>
}