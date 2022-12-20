package com.example.bho.viewModels

import com.example.bho.di.PersonDataSource
import com.example.bho.models.Person
import io.reactivex.Observable
import io.realm.kotlin.query.RealmResults

class PersonViewModel(private val dataSource: PersonDataSource) {

    fun insertPerson(namePerson: String, agePerson: Int): Observable<Person> {
        return dataSource.insertPerson(namePerson, agePerson)
    }

    fun getAllPerson(): Observable<RealmResults<Person>>{
        return dataSource.getAllPerson()
    }

}