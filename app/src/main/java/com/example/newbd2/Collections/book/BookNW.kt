package com.example.newbd2.Collections.book

data class BookNW(
    val `data`: List<BookNWItem>
){
    data class BookNWItem(
        var _id: Int,
        var author: String,
        var copies: List<Copy>,
        var loanDuration: Int,
        var name: String,
        var year: Int,
        var yearAcquired: Int
    ){
        data class Copy(
            var _id: Int,
            var borrowDate: String,
            var pointId: Int,
            var returnDate: String
        )
    }
}