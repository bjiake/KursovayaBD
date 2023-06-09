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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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

    @GET("collectionsGet/pointIssue")
    fun getAllPointIssue() : Call<PointIssueNW>

    @GET("collectionsGet/readRooms")
    fun getAllReadRooms() : Call<ReadRoomNW>

    @GET("collectionsGet/readerCards")
    fun getAllReaderCard() : Call<ReaderCardNW>

    @GET("collectionsGet/readers")
    fun getAllReader() : Call<ReaderNW>

    @GET("collectionsGet/books/{id}")
    fun getOneBookById(
        @Path("id")
        id:String
    ): Call<BookNW>

    @GET("/collectionsDelete/books/{id}")
    fun deleteOneBookById(
        @Path("id")
        id:String
    ):Call<BookNW>
    @POST("/collectionsPost/books")
    fun postBook(
        @Body
        book:BookNW
    ):Call<BookNW>

    @PUT("/collectionsPut/books/{id}")
    fun putBook(
        @Path("id")
        id: String,
        @Body
        book: BookNW.BookNWItem
    ):Call<BookNW.BookNWItem>

    @POST("/collectionsPost/books")
    fun postBook(
        @Body
        book: BookNW.BookNWItem
    ):Call<BookNW.BookNWItem>
}
