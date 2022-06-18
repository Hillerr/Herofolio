package com.hiller.herofolio.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hiller.herofolio.R
import com.hiller.herofolio.viewmodel.FavoriteCharactersViewModel

class FavoriteCharactersFragment : Fragment() {

    private lateinit var mViewModel: FavoriteCharactersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(FavoriteCharactersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorite_heros, container, false)

        return root
    }
}
