package com.example.newbd2.Collections.readRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.R

class ReadRoomAdapter(
    private val onReadRoomClick: (workTime: List<ReadRoomNW.ReadRoomNWItem.WorkTime>) -> Unit
) : ListAdapter<ReadRoomNW.ReadRoomNWItem, RecyclerView.ViewHolder>(ReadRoomDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_read_room_holder, parent, false)
        return ReadRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ReadRoomViewHolder).bind(getItem(position))
        holder.itemView.setOnClickListener {
            onReadRoomClick((getItem(position) as ReadRoomNW.ReadRoomNWItem).workingHours)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}
class ReadRoomViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view.rootView) {
    fun bind(readRoom: ReadRoomNW.ReadRoomNWItem) {
        view.findViewById<TextView>(R.id.idReadRoom).text = readRoom._id.toString()
        view.findViewById<TextView>(R.id.nameReadRoom).text = readRoom.name
        view.findViewById<TextView>(R.id.countReadRoom).text = readRoom.seatCount.toString()
    }
}
class ReadRoomDiffCallBack: DiffUtil.ItemCallback<ReadRoomNW.ReadRoomNWItem>() {
    override fun areItemsTheSame(
        oldItem: ReadRoomNW.ReadRoomNWItem,
        newItem: ReadRoomNW.ReadRoomNWItem
    ): Boolean = oldItem == newItem
    override fun areContentsTheSame(
        oldItem: ReadRoomNW.ReadRoomNWItem,
        newItem: ReadRoomNW.ReadRoomNWItem
    ): Boolean = oldItem == newItem
}
