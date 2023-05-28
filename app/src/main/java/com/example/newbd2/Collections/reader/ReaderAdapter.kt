package com.example.newbd2.Collections.reader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.R

class ReaderAdapter(
    private val onReaderClick: (reader: List<ReaderNW.ReaderNWItem.Reader>) -> Unit
) : ListAdapter<ReaderNW.ReaderNWItem, RecyclerView.ViewHolder>(ReaderDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_reader_card_holder, parent, false)
        return ReaderCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ReaderCardViewHolder).bind(getItem(position))
        holder.itemView.setOnClickListener {
//            onReaderClick((getItem(position) as ReaderNW.ReaderNWItem).reader)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}
class ReaderCardViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view.rootView) {
    fun bind(reader: ReaderNW.ReaderNWItem) {
        view.findViewById<TextView>(R.id.idReaderCard).text = reader._id.toString()
//        view.findViewById<TextView>(R.id.DateIssue).text = reader.DateIssue
//        view.findViewById<TextView>(R.id.DateToReturn).text = reader.DateToReturn
//        view.findViewById<TextView>(R.id.WhenReturnDate).text = reader.WhenReturnDate
    }
}
class ReaderDiffCallBack: DiffUtil.ItemCallback<ReaderNW.ReaderNWItem>() {
    override fun areItemsTheSame(
        oldItem: ReaderNW.ReaderNWItem,
        newItem: ReaderNW.ReaderNWItem
    ): Boolean = oldItem == newItem
    override fun areContentsTheSame(
        oldItem: ReaderNW.ReaderNWItem,
        newItem: ReaderNW.ReaderNWItem
    ): Boolean = oldItem == newItem
}
