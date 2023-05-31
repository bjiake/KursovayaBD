package com.example.newbd2.Collections.readerCard

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.R
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ReaderCardAdapter(
    private val onReaderCardClick: (idExamplars: List<ReaderCardNW.ReaderCardNWItem.IdExamplars>) -> Unit
) : ListAdapter<ReaderCardNW.ReaderCardNWItem, RecyclerView.ViewHolder>(ReaderCardDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_reader_card_holder, parent, false)
        return ReaderCardViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ReaderCardViewHolder).bind(getItem(position))
        holder.itemView.setOnClickListener {
            onReaderCardClick((getItem(position) as ReaderCardNW.ReaderCardNWItem).bookIds)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}
class ReaderCardViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view.rootView) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(readerCard: ReaderCardNW.ReaderCardNWItem) {
        view.findViewById<TextView>(R.id.idReaderCard).text = readerCard._id.toString()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        var dateTime = OffsetDateTime.parse(readerCard.issueDate, formatter)
        view.findViewById<TextView>(R.id.DateIssueReaderCard).text = dateTime.toLocalDateTime().toLocalDate().toString()
        dateTime = OffsetDateTime.parse(readerCard.returnDate, formatter)
        view.findViewById<TextView>(R.id.DateToReturnReaderCard).text = dateTime.toLocalDateTime().toLocalDate().toString()
        view.findViewById<TextView>(R.id.WhenReturnDateReaderCard).text =readerCard.returnStatus
    }
}
class ReaderCardDiffCallBack: DiffUtil.ItemCallback<ReaderCardNW.ReaderCardNWItem>() {
    override fun areItemsTheSame(
        oldItem: ReaderCardNW.ReaderCardNWItem,
        newItem: ReaderCardNW.ReaderCardNWItem
    ): Boolean = oldItem == newItem
    override fun areContentsTheSame(
        oldItem: ReaderCardNW.ReaderCardNWItem,
        newItem: ReaderCardNW.ReaderCardNWItem
    ): Boolean = oldItem == newItem
}
