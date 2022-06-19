package com.hiller.herofolio.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hiller.herofolio.R
import com.hiller.herofolio.service.listener.CharacterListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.service.model.FavoriteCharacter
import com.hiller.herofolio.view.viewholder.CharacterViewHolder
import com.hiller.herofolio.view.viewholder.FavoriteCharacterViewHolder

class FavoriteCharacterAdapter : RecyclerView.Adapter<FavoriteCharacterViewHolder>() {
    private var mList: List<FavoriteCharacter> = arrayListOf()
    private lateinit var mListener: CharacterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCharacterViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.row_character_list, parent, false)
        return FavoriteCharacterViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: FavoriteCharacterViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun attachListener(listener: CharacterListener) {
        mListener = listener
    }

    fun updateListener(list: List<FavoriteCharacter>) {
        mList = list
        notifyDataSetChanged()
    }
}