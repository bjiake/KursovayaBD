package com.example.newbd2.Collections.pointIssue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.R

class PointIssueAdapter(
    private val onPointIssueClick: (workTime: List<PointIssueNW.PointIssueNWItem.WorkTime>) -> Unit
) : ListAdapter<PointIssueNW.PointIssueNWItem, RecyclerView.ViewHolder>(PointIssueDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_point_issue_holder, parent, false)
        return PointIssueViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PointIssueViewHolder).bind(getItem(position))
        holder.itemView.setOnClickListener {
            onPointIssueClick((getItem(position) as PointIssueNW.PointIssueNWItem).workingHours)
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}
class PointIssueViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view.rootView) {
    fun bind(pointIssue: PointIssueNW.PointIssueNWItem) {
        view.findViewById<TextView>(R.id.idPointIssue).text = pointIssue._id.toString()
        view.findViewById<TextView>(R.id.namePointIssue).text = pointIssue.name
    }
}
class PointIssueDiffCallBack: DiffUtil.ItemCallback<PointIssueNW.PointIssueNWItem>() {
    override fun areItemsTheSame(
        oldItem: PointIssueNW.PointIssueNWItem,
        newItem: PointIssueNW.PointIssueNWItem
    ): Boolean = oldItem == newItem
    override fun areContentsTheSame(
        oldItem: PointIssueNW.PointIssueNWItem,
        newItem: PointIssueNW.PointIssueNWItem
    ): Boolean = oldItem == newItem
}
