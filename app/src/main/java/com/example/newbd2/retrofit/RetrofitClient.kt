package com.example.newbd2.retrofit

import com.example.newbd2.Collections.book.BookNW
import com.example.newbd2.Collections.pointIssue.PointIssueNW
import com.example.newbd2.Collections.readRoom.ReadRoomNW
import com.example.newbd2.Collections.reader.ReaderNW
import com.example.newbd2.Collections.readerCard.ReaderCardNW
import com.example.newbd2.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitAPI {
    companion object{
        fun createAPI(): RetrofitAPI {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofitBuilder.create(RetrofitAPI::class.java)
        }
    }

    @GET("collectionsGet/books")
    fun getAllBooks(): Call<BookNW>

    @GET("collectionsGet/CollectionPointIssue")
    fun getAllPointIssue() : Call<PointIssueNW>

    @GET("collectionsGet/CollectionReadRooms")
    fun getAllReadRooms() : Call<ReadRoomNW>

    @GET("collectionsGet/CollectionReaderCards")
    fun getAllReaderCard() : Call<ReaderCardNW>

    @GET("collectionsGet/CollectionReaders")
    fun getAllReader() : Call<ReaderNW>
}
