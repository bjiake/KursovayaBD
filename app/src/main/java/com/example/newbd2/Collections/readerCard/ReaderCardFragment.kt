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
import com.example.newbd2.Collections.reader.ReaderAdapter
import com.example.newbd2.Collections.reader.ReaderNW
import com.example.newbd2.Collections.readerCard.ReaderCardAdapter
import com.example.newbd2.Collections.readerCard.ReaderCardNW
import com.example.newbd2.MainActivity
import com.example.newbd2.R
import com.example.newbd2.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReaderCardFragment : Fragment() {
    private lateinit var rvbook: RecyclerView
    var retrofitAPI = RetrofitAPI.createAPI()
    private val adapter =ReaderCardAdapter(
        onReaderCardClick= {
//            requireActivity().goToFragment()
            Log.d("aaa","Click")
            //В конце рекуклера плюсик, который едиты добавляет
            //минус,убирает s
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reader_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Перейти в другой фрагмент
//        requireActivity().goToFragment()
        super.onViewCreated(view, savedInstanceState)
        rvbook = view.findViewById(R.id.rvReaderCard)
        recyclerViewInit()
        loadAllBooks()
    }

    private fun recyclerViewInit() {
        rvbook.layoutManager = LinearLayoutManager(activity)
        rvbook.adapter = adapter
    }

    private fun loadAllBooks() {
        retrofitAPI.getAllReaderCard()
            .enqueue(object : Callback<ReaderCardNW> {
                override fun onResponse(call: Call<ReaderCardNW>, response: Response<ReaderCardNW>) {
                    if (response.isSuccessful) {
                        var respone = response.body()?.data!!
                        Log.d("aaa", respone.toString())
                        adapter.submitList(respone)
                    }
                }

                override fun onFailure(call: Call<ReaderCardNW>, t: Throwable) {
                    Log.e("aaa", "$t")
                }

            })
    }
}