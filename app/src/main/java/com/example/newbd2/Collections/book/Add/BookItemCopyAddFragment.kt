package com.example.newbd2.Collections.book.Add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.newbd2.Collections.book.BookNW
import com.example.newbd2.R

class BookItemCopyAddFragment(
    private val onBookItemCopyChange:(bookCopy: BookNW.BookNWItem.Copy) -> Unit
): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_item_copy_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtnApply(
            onBookItemCopyChange = {
                onBookItemCopyChange(it)
                requireActivity().supportFragmentManager.popBackStack()
            }
        )
        initBtnDeny()
    }
    private fun initBtnApply(onBookItemCopyChange:(bookCopy: BookNW.BookNWItem.Copy) -> Unit){
        val btnApply = view?.findViewById<Button>(R.id.bookCopyAddApply)
        val id = view?.findViewById<EditText>(R.id.bookCopyAddEditBookCopiesId)
        val returnDate = view?.findViewById<EditText>(R.id.bookCopyAddEditBookCopiesReturnDate)
        val dateIssue = view?.findViewById<EditText>(R.id.bookCopyAddEditBookCopiesBorrowDate)
        val pointId = view?.findViewById<EditText>(R.id.bookCopyAddEditBookCopiesPointId)
        btnApply?.setOnClickListener {
            Log.d("aaa", "Click")
            var bookCopy = BookNW.BookNWItem.Copy(
                _id = id!!.text.toString().toInt(),
                borrowDate = dateIssue!!.text.toString(),
                pointId = pointId!!.text.toString().toInt(),
                returnDate = returnDate!!.text.toString()
            )
            onBookItemCopyChange(bookCopy)
        }
    }
    private fun initBtnDeny(){
        val btnDeny = view?.findViewById<Button>(R.id.bookCopyAddDeny)
        btnDeny?.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}