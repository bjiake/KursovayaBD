package com.example.newbd2.Collections.pointIssue

data class PointIssueNW(
    val `data`: List<PointIssueNWItem>
){
    data class PointIssueNWItem(
        val _id: Int,
        val name: String,
        val workingHours: List<WorkTime>
    ){
        data class WorkTime(
            val close: String,
            val days: String,
            val `open`: String
        )
    }
}