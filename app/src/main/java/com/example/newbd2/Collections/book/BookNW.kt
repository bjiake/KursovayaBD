package com.example.newbd2.Collections.book

data class BookNW(
    val `data`: List<BookNWItem>
){
    data class BookNWItem(
        val _id: Int,
        var author: String,
        val copies: List<Copy>,
        var loanDuration: Int,
        var name: String,
        var year: Int,
        var yearAcquired: Int
    ){
        data class Copy(
            var _id: Int,
            val borrowDate: String,
            val pointId: Int,
            val returnDate: String
        )
    }
}