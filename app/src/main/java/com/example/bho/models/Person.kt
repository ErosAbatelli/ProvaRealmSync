package com.example.bho.models

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Person: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()

    var name: String = ""
    var age: Int = 0

}