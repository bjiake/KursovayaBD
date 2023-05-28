package com.example.newbd2.Collections.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.R

class BookAdapter(
    private val onBookClick: (bookExamplars: List<BookNW.BookNWItem.Copy>) -> Unit
) : ListAdapter<BookNW.BookNWItem, RecyclerView.ViewHolder>(BookDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book_holder, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookViewHolder).bind(getItem(position))
        holder.itemView.setOnClickListener {
            onBookClick((getItem(position) as BookNW.BookNWItem).copies)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}
class BookViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view.rootView) {
    fun bind(book: BookNW.BookNWItem) {
        view.findViewById<TextView>(R.id.idBook).text = book._id.toString().orEmpty()
        view.findViewById<TextView>(R.id.authorBook).text = book.author
        view.findViewById<TextView>(R.id.yearRelease).text = book.year.toString()
        view.findViewById<TextView>(R.id.yearToLibrary).text = book.yearAcquired.toString()
        view.findViewById<TextView>(R.id.DateIssue).text = book.loanDuration.toString()
        view.findViewById<TextView>(R.id.nameBook).text = book.name
    }
}
class BookDiffCallBack: DiffUtil.ItemCallback<BookNW.BookNWItem>() {
    override fun areItemsTheSame(
        oldItem: BookNW.BookNWItem,
        newItem: BookNW.BookNWItem
    ): Boolean = oldItem == newItem
    override fun areContentsTheSame(
        oldItem: BookNW.BookNWItem,
        newItem: BookNW.BookNWItem
    ): Boolean = oldItem == newItem
}