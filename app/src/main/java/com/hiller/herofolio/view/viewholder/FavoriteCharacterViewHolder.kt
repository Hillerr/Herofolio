package com.hiller.herofolio.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hiller.herofolio.R
import com.hiller.herofolio.service.listener.CharacterListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.service.model.FavoriteCharacter
import com.hiller.herofolio.view.utils.getImageByUrlCenterCrop

class FavoriteCharacterViewHolder(itemView: View, val listener: CharacterListener) :
    RecyclerView.ViewHolder(itemView) {
    private var mName: TextView = itemView.findViewById(R.id.character_name)
    private var mThumbnail: ImageView = itemView.findViewById(R.id.hero_thumbnail)
    private var mFavoriteButton: ImageView = itemView.findViewById(R.id.favorite_marker)

    /**
     * Atribui valores aos elementos de interface e também eventos
     */
    fun bindData(character: FavoriteCharacter) {

        this.mName.text = character.name
        var thumbnail = character.thumbnail
        this.mThumbnail.getImageByUrlCenterCrop(thumbnail)

        if (character.isFavorite) {
            mFavoriteButton.setImageResource(R.drawable.ic_favorite_selected)
        } else {
            mFavoriteButton.setImageResource(R.drawable.ic_favorite_unselected)
        }


        // Eventos
        mName.setOnClickListener {

            listener.onDetailClick(
                character.id,
                character.name,
                character.description,
                character.thumbnail
            )
        }
        mFavoriteButton.setOnClickListener { listener.onRemoveFavorite(character.id) }


    }
}