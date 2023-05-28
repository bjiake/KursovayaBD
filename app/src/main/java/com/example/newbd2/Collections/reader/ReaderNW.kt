package com.example.newbd2.Collections.reader

class ReaderNW(
    val data: List<ReaderNWItem>
){
    data class ReaderNWItem(
        val _id: Int,
        val idReadRoom: Int,
        val fullName: String,
        val reader: Reader
    ){
        data class Reader(
            val group: String,
            val title: String,
            val department: String,
            val degree: String,
            val type: String
        )
    }
}