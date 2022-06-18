package com.hiller.herofolio.view.viewholder

import android.app.AlertDialog
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hiller.herofolio.R
import com.hiller.herofolio.service.listener.CharacterListener
import com.hiller.herofolio.service.model.CharacterResponse
import java.text.SimpleDateFormat

class CharacterViewHolder(itemView: View, val listener: CharacterListener) :
    RecyclerView.ViewHolder(itemView) {

    private var mName: TextView = itemView.findViewById(R.id.character_name)
    private var mThumbnail: ImageView = itemView.findViewById(R.id.hero_thumbnail)

    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy")

    /**
     * Atribui valores aos elementos de interface e também eventos
     */
    fun bindData(character: CharacterResponse) {

        this.mName.text = character.name
        var thumbnail = "${character.thumbnail.path}.${character.thumbnail.extension}"


        // Eventos
        mName.setOnClickListener { listener.onDetailClick(character.id) }
        mThumbnail.setOnClickListener {
//            if(character.isFavorite){
//                listener.onAddFavoriteClick(character.id)
//            } else {
//                listener.onRemoveFavorite(character.id)
//            }
        }

    }

}