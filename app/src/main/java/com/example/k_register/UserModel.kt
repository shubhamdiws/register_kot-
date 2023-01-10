package com.example.k_register

import androidx.activity.result.contract.ActivityResultContracts
import androidx.room.*

@Entity(tableName = "User34")
class UserModel {

    @PrimaryKey val email :String
    @ColumnInfo(name = "firstName") val name: String?
    @ColumnInfo(name = "Phone") val pnum: String?
    @ColumnInfo(name = "gender") val gender: String?
    @ColumnInfo(name="password")val pass:String?
   // @ColumnInfo(name = "picture", typeAffinity = ColumnInfo.BLOB)
   // var picture: ByteArray? = null
    @ColumnInfo(name="picture")val imageurl:String?




    constructor(email: String, name: String?, pnum: String, gender: String?, pass: String?,imageurl:String?){
        this.email = email
        this.name = name
        this.pnum = pnum
        this.gender = gender
        this.pass = pass
        this.imageurl=imageurl

    }



}