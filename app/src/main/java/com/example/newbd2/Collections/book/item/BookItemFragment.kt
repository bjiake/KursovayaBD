package com.example.newbd2.Collections.book.item

import android.os.Bundle
import android.os.Handler.Callback
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.Collections.book.BookAdapter
import com.example.newbd2.Collections.book.BookNW
import com.example.newbd2.R
import com.example.newbd2.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Response

class BookItemFragment(): Fragment() {
    private lateinit var rvCopy: RecyclerView
    var retrofitAPI = RetrofitAPI.createAPI()
    private val adapter = BookItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_item, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Перейти в другой фрагмент
//        requireActivity().goToFragment()
        super.onViewCreated(view, savedInstanceState)
        val bookId = arguments?.getString("bookId")
        rvCopy = view.findViewById(R.id.editRVCopies)
        recyclerViewInit()
        if (bookId != null) {
            loadBookItem(bookId)
        }
    }
    private fun recyclerViewInit() {
        rvCopy.layoutManager = LinearLayoutManager(activity)
        rvCopy.adapter = adapter
    }
    private fun loadBookItem(bookId: String){
        retrofitAPI.getOneBookById(bookId)
            .enqueue(object: retrofit2.Callback<BookNW>{
                override fun onResponse(
                    call: Call<BookNW>,
                    response: Response<BookNW>
                ) {
                    var response: List<BookNW.BookNWItem> = response.body()?.data!!
                    var bookItem = response.last()
                    view?.findViewById<EditText>(R.id.editIdBook)?.append(bookItem._id.toString())
                    view?.findViewById<EditText>(R.id.editNameBook)?.append(bookItem.name)
                    view?.findViewById<EditText>(R.id.editAuthorBook)?.append(bookItem.author)
                    view?.findViewById<EditText>(R.id.editYearRealeaseBook)?.append(bookItem.year.toString())
                    view?.findViewById<EditText>(R.id.editYearToLibraryBook)?.append(bookItem.loanDuration.toString())
                    view?.findViewById<EditText>(R.id.editDateIssueBook)?.append(bookItem.yearAcquired.toString())
                    //Дополнить лэйаут
                    adapter.submitList(bookItem.copies)
                }

                override fun onFailure(call: Call<BookNW>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}