package com.example.newbd2.Collections.book.item

import android.os.Build
import com.example.newbd2.Collections.book.BookNW
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.R
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class BookItemAdapter : ListAdapter<BookNW.BookNWItem.Copy, RecyclerView.ViewHolder>(BookDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_edit_book_copies_holder, parent, false)
        return BookItemViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookItemViewHolder).bind(getItem(position))
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }
    //material tree

}
class BookItemViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view.rootView) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(bookCopy: BookNW.BookNWItem.Copy) {
        view.findViewById<EditText>(R.id.editBookCopiesId).append(bookCopy._id.toString())
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        var dateTime = OffsetDateTime.parse(bookCopy.returnDate, formatter)

        view.findViewById<EditText>(R.id.editBookCopiesReturnDate).append(dateTime.toLocalDateTime().toLocalDate().toString())
        dateTime = OffsetDateTime.parse(bookCopy.borrowDate, formatter)

        view.findViewById<EditText>(R.id.editBookCopiesBorrowDate).append(dateTime.toLocalDateTime().toLocalDate().toString())
        view.findViewById<EditText>(R.id.editBookCopiesPointId).append(bookCopy.pointId.toString())
    }
}
class BookDiffCallBack: DiffUtil.ItemCallback<BookNW.BookNWItem.Copy>() {
    override fun areItemsTheSame(
        oldItem: BookNW.BookNWItem.Copy,
        newItem: BookNW.BookNWItem.Copy
    ): Boolean = oldItem == newItem
    override fun areContentsTheSame(
        oldItem: BookNW.BookNWItem.Copy,
        newItem: BookNW.BookNWItem.Copy
    ): Boolean = oldItem == newItem
}