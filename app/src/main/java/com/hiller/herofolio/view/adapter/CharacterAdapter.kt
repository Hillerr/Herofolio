package com.hiller.herofolio.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hiller.herofolio.R
import com.hiller.herofolio.service.listener.CharacterListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.view.viewholder.CharacterViewHolder

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private var mList: List<CharacterResponse> = arrayListOf()
    private lateinit var mListener: CharacterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.row_character_list, parent, false)
        return CharacterViewHolder(item, mListener)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun attachListener(listener: CharacterListener) {
        mListener = listener
    }

    fun updateListener(list: List<CharacterResponse>) {
        mList = list
        notifyDataSetChanged()
    }

}