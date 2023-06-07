package com.example.newbd2.Collections.book.item

import android.os.Build
import com.example.newbd2.Collections.book.BookNW
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.R
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class BookItemAdapter(
    private val onBookItemCopyChange: (bookItemCopy: BookNW.BookNWItem.Copy) -> Unit
) : ListAdapter<BookNW.BookNWItem.Copy, RecyclerView.ViewHolder>(BookDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_edit_book_copies_holder, parent, false)
        return BookItemViewHolder(view, onBookItemCopyChange = {
            onBookItemCopyChange(it)
        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookItemViewHolder).bind(getItem(position))
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }
}

class BookItemViewHolder(private val view: View, private val onBookItemCopyChange: (bookItemCopy: BookNW.BookNWItem.Copy) -> Unit) :
    RecyclerView.ViewHolder(view.rootView) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(bookCopy: BookNW.BookNWItem.Copy) {
        var copiesId = view.findViewById<EditText>(R.id.editBookCopiesId)
        copiesId.append(bookCopy._id.toString())

//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
////        var dateTime = OffsetDateTime.parse(bookCopy.returnDate, formatter)
//        var dateTime = LocalDate.parse(bookCopy.returnDate, formatter)
//            .atStartOfDay(ZoneOffset.UTC)

        var returnDate = view.findViewById<EditText>(R.id.editBookCopiesReturnDate)
        returnDate.doAfterTextChanged {
            val newBook = bookCopy.copy(returnDate = it.toString())
            onBookItemCopyChange(newBook)
        }

        returnDate.append(bookCopy.returnDate.substring(0,10))
//        dateTime = LocalDate.parse(bookCopy.borrowDate, formatter)
//            .atStartOfDay(ZoneOffset.UTC)

        var borrowDate = view.findViewById<EditText>(R.id.editBookCopiesBorrowDate)
        borrowDate.append(bookCopy.borrowDate.substring(0,10))

//        borrowDate.append(dateTime.toLocalDateTime().toLocalDate().toString())
        var pointId = view.findViewById<EditText>(R.id.editBookCopiesPointId)
        pointId.append(bookCopy.pointId.toString())
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