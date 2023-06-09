package com.example.newbd2.Collections.book

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.Collections.pointIssue.PointIssueAdapter
import com.example.newbd2.Collections.pointIssue.PointIssueNW
import com.example.newbd2.MainActivity
import com.example.newbd2.R
import com.example.newbd2.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PointIssueFragment : Fragment() {
    private lateinit var rvbook: RecyclerView
    var retrofitAPI = RetrofitAPI.createAPI()
    private val adapter = PointIssueAdapter(
        onPointIssueClick = {
//            requireActivity().goToFragment()
            Log.d("aaa", "Click")
            //В конце рекуклера плюсик, который едиты добавляет
            //минус,убирает s
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.point_issue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Перейти в другой фрагмент
//        requireActivity().goToFragment()
        super.onViewCreated(view, savedInstanceState)
        rvbook = view.findViewById(R.id.rvPointIssue)
        recyclerViewInit()
        loadAllBooks()
    }

    private fun recyclerViewInit() {
        rvbook.layoutManager = LinearLayoutManager(activity)
        rvbook.adapter = adapter
    }

    private fun loadAllBooks() {
        retrofitAPI.getAllPointIssue()
            .enqueue(object : Callback<PointIssueNW> {
                override fun onResponse(
                    call: Call<PointIssueNW>,
                    response: Response<PointIssueNW>
                ) {
                    if (response.isSuccessful) {
                        var respone = response.body()?.data!!
                        adapter.submitList(respone)
                    }
                }

                override fun onFailure(call: Call<PointIssueNW>, t: Throwable) {
                    Log.e("aaa", "$t")
                }

            })
    }
}
