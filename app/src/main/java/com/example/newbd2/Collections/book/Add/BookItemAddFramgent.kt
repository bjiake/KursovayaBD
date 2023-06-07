package com.example.newbd2.Collections.book.Add

import android.annotation.SuppressLint
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
import com.example.newbd2.Collections.book.item.BookItemAdapter
import com.example.newbd2.Collections.goToFragment
import com.example.newbd2.R
import com.example.newbd2.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Response

class BookItemAddFragment(
) : Fragment() {
    private var bookItem = BookNW.BookNWItem(
        _id = 0,
        author = "",
        copies = emptyList(),
        loanDuration = 0,
        name = "",
        year = 0,
        yearAcquired = 0,
    )
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
        },
        onLongClickDelete = {bookCopyId ->
            val newBookItemCopies = bookItem.copies.toMutableList()
            newBookItemCopies.removeIf{
                it._id == bookCopyId
            }
            bookItem = bookItem.copy(copies = newBookItemCopies)
            updateListAdapter(newBookItemCopies)
            Log.d("aaa", "deleted")
        }
    )
    private fun updateListAdapter(newBookItemCopies: List<BookNW.BookNWItem.Copy>){
        adapter.addItems(newBookItemCopies)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_item_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtnApply()
        initBtnDeny()
        initBtnAdd()

        rvCopy = view.findViewById(R.id.addRVCopies)
        recyclerViewInit()
    }
    private fun initBtnAdd(){
        val btnAdd = view?.findViewById<Button>(R.id.bookItemAddExamplar)
        if (btnAdd != null) {
            btnAdd.setOnClickListener {
                Log.d("aaa","Click")

                val bookItemFragment = BookItemCopyAddFragment(
                    onBookItemCopyChange = { newBookCopy ->
                        val newBookItemCopies = bookItem.copies.toMutableList()
                        newBookItemCopies.add(newBookCopy)
                        bookItem = bookItem.copy(copies = newBookItemCopies)
                        updateListAdapter(newBookItemCopies)
                    }
                )
                requireActivity().goToFragment(bookItemFragment, true)
            }
        }
    }
    @SuppressLint("CutPasteId")
    private fun initBtnApply(){
        val btnApply = view?.findViewById<Button>(R.id.bookItemAddApply)
        val idBook = view?.findViewById<EditText>(R.id.bookItemAddEditIdBook)
        val nameBook = view?.findViewById<EditText>(R.id.bookItemAddEditNameBook)
        val authorBook = view?.findViewById<EditText>(R.id.bookItemAddEditAuthorBook)
        val dateIssue = view?.findViewById<EditText>(R.id.bookItemAddEditDateIssueBook)
        val yearRelease = view?.findViewById<EditText>(R.id.bookItemAddEditYearRealeaseBook)
        val loanDuration = view?.findViewById<EditText>(R.id.bookItemAddEditYearToLibraryBook)
        btnApply?.setOnClickListener {
            bookItem._id = idBook!!.text.toString().toInt()
            bookItem.author = authorBook!!.text.toString()
            bookItem.name = nameBook!!.text.toString()
            bookItem.loanDuration = loanDuration!!.text.toString().toInt()
            bookItem.year = dateIssue!!.text.toString().toInt()
            bookItem.yearAcquired = yearRelease!!.text.toString().toInt()
            retrofitAPI.postBook(bookItem)
                .enqueue(object: retrofit2.Callback<BookNW.BookNWItem>{
                    override fun onResponse(
                        call: Call<BookNW.BookNWItem>,
                        response: Response<BookNW.BookNWItem>
                    ) {
                        Toast.makeText(activity,"Post", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFailure(call: Call<BookNW.BookNWItem>, t: Throwable) {
                        Log.d("aaa", t.toString())
                    }
                })
        }
    }
    private fun initBtnDeny(){
        val btnDeny = view?.findViewById<Button>(R.id.bookItemAddDeny)
        btnDeny?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
    private fun recyclerViewInit() {
        rvCopy.layoutManager = LinearLayoutManager(activity)
        rvCopy.adapter = adapter
    }
}