package com.example.newbd2.Collections.book

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbd2.Collections.reader.ReaderAdapter
import com.example.newbd2.Collections.reader.ReaderNW
import com.example.newbd2.R
import com.example.newbd2.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReaderFragment : Fragment() {
    private lateinit var rvReader: RecyclerView
    var retrofitAPI = RetrofitAPI.createAPI()
    private val adapter = ReaderAdapter(
        onReaderClick = {
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
        return inflater.inflate(R.layout.reader, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Перейти в другой фрагмент
//        requireActivity().goToFragment()
        super.onViewCreated(view, savedInstanceState)
        rvReader = view.findViewById(R.id.rvReader)
        recyclerViewInit()
        loadAllBooks()
    }

    private fun recyclerViewInit() {
        rvReader.layoutManager = LinearLayoutManager(activity)
        rvReader.adapter = adapter
    }

    private fun loadAllBooks() {
        retrofitAPI.getAllReader()
            .enqueue(object : Callback<ReaderNW> {
                override fun onResponse(call: Call<ReaderNW>, response: Response<ReaderNW>) {
                    if (response.isSuccessful) {
                        var respone = response.body()?.data!!
                        adapter.submitList(respone)
                    }
                }

                override fun onFailure(call: Call<ReaderNW>, t: Throwable) {
                    Log.e("aaa", "$t")
                }

            })
    }
}


