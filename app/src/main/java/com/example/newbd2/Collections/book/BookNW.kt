package com.example.newbd2.Collections.book

data class BookNW(
    val `data`: List<BookNWItem>
){
    data class BookNWItem(
        val _id: Int,
        val author: String,
        val copies: List<Copy>,
        val loanDuration: Int,
        val name: String,
        val year: Int,
        val yearAcquired: Int
    ){
        data class Copy(
            val _id: Int,
            val borrowDate: String,
            val pointId: Int,
            val returnDate: String
        )
    }
}