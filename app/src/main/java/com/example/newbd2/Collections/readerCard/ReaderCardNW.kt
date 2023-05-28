package com.example.newbd2.Collections.readerCard

data class ReaderCardNW(
    val `data`: List<ReaderCardNWItem>
){
    data class ReaderCardNWItem(
        val _id: Int,
        val bookIds: List<IdExamplars>,
        val issueDate: String,
        val returnDate: String,
        val returnStatus: String
    ){
        data class IdExamplars(
            val _id: Int,
            val borrowDate: String,
            val pointId: Int,
            val returnDate: String
        )
    }
}