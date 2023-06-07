package com.example.newbd2.Collections.book.item

import android.os.Build
import android.util.Log
import com.example.newbd2.Collections.book.BookNW
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.R

class BookItemAdapter(
    private val onBookItemCopyChange: (bookItemCopy: BookNW.BookNWItem.Copy) -> Unit,
    private val onLongClickDelete: (bookCopyId: Int) -> Unit
) : ListAdapter<BookNW.BookNWItem.Copy, RecyclerView.ViewHolder>(BookDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_edit_book_copies_holder, parent, false)
        return BookItemViewHolder(
            view,
            onBookItemCopyChange = {
                onBookItemCopyChange(it)
            },
            onLongClickDelete = {
                onLongClickDelete(it)
            })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookItemViewHolder).bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    fun addItems(newItems: List<BookNW.BookNWItem.Copy>) {
        val currentList = ArrayList(currentList)
        currentList.addAll(newItems)
        submitList(currentList)
    }
}

class BookItemViewHolder(
    private val view: View,
    private val onBookItemCopyChange: (bookItemCopy: BookNW.BookNWItem.Copy) -> Unit,
    private val onLongClickDelete: (copyId: Int) -> Unit
) :
    RecyclerView.ViewHolder(view.rootView) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(bookCopy: BookNW.BookNWItem.Copy) {
        var copiesId = view.findViewById<EditText>(R.id.editBookCopiesId)
        copiesId.append(bookCopy._id.toString())

        var returnDate = view.findViewById<EditText>(R.id.editBookCopiesReturnDate)
        returnDate.doAfterTextChanged {
            val newBook = bookCopy.copy(returnDate = it.toString())
            onBookItemCopyChange(newBook)
        }
        if (bookCopy.returnDate.length > 10) {
            returnDate.append(bookCopy.returnDate.substring(0, 10))
        } else {
            returnDate.append(bookCopy.returnDate)
        }

        var borrowDate = view.findViewById<EditText>(R.id.editBookCopiesBorrowDate)
        borrowDate.doAfterTextChanged {
            val newBook = bookCopy.copy(borrowDate = it.toString())
            onBookItemCopyChange(newBook)
        }
        if (bookCopy.borrowDate.length > 10) {
            borrowDate.append(bookCopy.borrowDate.substring(0, 10))
        } else {
            borrowDate.append(bookCopy.borrowDate)
        }

        var pointId = view.findViewById<EditText>(R.id.editBookCopiesPointId)
        pointId.append(bookCopy.pointId.toString())

        //
        this.bookCopyId = bookCopy._id
    }

    private var bookCopyId = 0

    init {
        itemView.setOnLongClickListener {
            Log.d("aaa", "LongClick")
            onLongClickDelete(bookCopyId)
            return@setOnLongClickListener true
        }
    }
}

class BookDiffCallBack : DiffUtil.ItemCallback<BookNW.BookNWItem.Copy>() {
    override fun areItemsTheSame(
        oldItem: BookNW.BookNWItem.Copy,
        newItem: BookNW.BookNWItem.Copy
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: BookNW.BookNWItem.Copy,
        newItem: BookNW.BookNWItem.Copy
    ): Boolean = oldItem == newItem
}