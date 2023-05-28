package com.example.newbd2.Collections.readRoom

data class ReadRoomNW(
    val `data`: List<ReadRoomNWItem>
){
    data class ReadRoomNWItem(
        val _id: Int,
        val name: String,
        val seatCount: Int,
        val workingHours: List<WorkTime>
    ){
        data class WorkTime(
            val close: String,
            val days: String,
            val `open`: String
        )
    }
}