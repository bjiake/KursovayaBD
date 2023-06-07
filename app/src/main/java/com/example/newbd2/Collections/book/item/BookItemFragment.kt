package com.example.newbd2.Collections.book.item

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.Collections.book.BookNW
import com.example.newbd2.R
import com.example.newbd2.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Response

class BookItemFragment(): Fragment() {
    private lateinit var rvCopy: RecyclerView
    var retrofitAPI = RetrofitAPI.createAPI()
    private val adapter = BookItemAdapter(
        onBookItemCopyChange = { newBook ->
            val newBookItemCopies = bookItem.copies.toMutableList()
            newBookItemCopies.removeIf{
                it._id == newBook._id
            }
            newBookItemCopies.add(newBook)
            bookItem = bookItem.copy(copies = newBookItemCopies)
        }
    )
    private lateinit var bookItem: BookNW.BookNWItem
    fun updateBookItem(bookCopy: BookNW.BookNWItem.Copy, position: Int) {
        bookItem.copies[position]._id = bookCopy._id
        // выполнение желаемых действий с новым значением
    }

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
        if (bookId != null) {
            deleteBtn(bookId)
        }
        if (bookId != null){
            putBtn(bookId)
        }

    }
    private fun putBtn(bookId: String){
        var btnPut = view?.findViewById<Button>(R.id.editBtnBook)
        val nameBook = view?.findViewById<EditText>(R.id.editNameBook)
        val authorBook = view?.findViewById<EditText>(R.id.editAuthorBook)
        val dateIssue = view?.findViewById<EditText>(R.id.editDateIssueBook)
        val yearRelease = view?.findViewById<EditText>(R.id.editYearRealeaseBook)
        val loanDuration = view?.findViewById<EditText>(R.id.editYearToLibraryBook)
        if (btnPut !=null){
            btnPut.setOnClickListener {
                bookItem.name = nameBook!!.text.toString()
                bookItem.author = authorBook!!.text.toString()
                bookItem.year = dateIssue!!.text.toString().toInt()
                bookItem.yearAcquired = yearRelease!!.text.toString().toInt()
                bookItem.loanDuration = loanDuration!!.text.toString().toInt()

                retrofitAPI.putBook(bookId, bookItem)
                    .enqueue(object: retrofit2.Callback<BookNW.BookNWItem>{
                        override fun onResponse(
                            call: Call<BookNW.BookNWItem>,
                            response: Response<BookNW.BookNWItem>
                        ) {
                            Toast.makeText(activity,"put", Toast.LENGTH_SHORT).show()
                        }
                        override fun onFailure(call: Call<BookNW.BookNWItem>, t: Throwable) {
                            Log.d("aaa", t.toString())
                        }
                    })
            }
        }
    }
    private fun deleteBtn(bookId: String){
        var btnDelete = view?.findViewById<Button>(R.id.deleteBtnBook)
        if (btnDelete != null) {
            btnDelete.setOnClickListener {
                retrofitAPI.deleteOneBookById(bookId)
                    .enqueue(object: retrofit2.Callback<BookNW>{
                        override fun onResponse(call: Call<BookNW>, response: Response<BookNW>) {
                            Toast.makeText(activity,"deleted", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(call: Call<BookNW>, t: Throwable) {
                            Log.d("aaa", t.toString())
                        }

                    })
            }
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
                    bookItem = response.last()

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