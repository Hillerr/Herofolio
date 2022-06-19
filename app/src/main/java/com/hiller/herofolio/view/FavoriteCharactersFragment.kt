package com.hiller.herofolio.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiller.herofolio.R
import com.hiller.herofolio.service.constants.AppConstants
import com.hiller.herofolio.service.listener.CharacterListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.view.adapter.CharacterAdapter
import com.hiller.herofolio.view.adapter.FavoriteCharacterAdapter
import com.hiller.herofolio.viewmodel.AllCharactersViewModel
import com.hiller.herofolio.viewmodel.FavoriteCharactersViewModel
import kotlinx.android.synthetic.main.fragment_favorite_heros.*

class FavoriteCharactersFragment : Fragment() {

    private lateinit var mViewModel: FavoriteCharactersViewModel
    private lateinit var mListener: CharacterListener
    private val mAdapter = FavoriteCharacterAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(FavoriteCharactersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorite_heros, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_fav_characters)

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        // Eventos disparados ao clicar nas linhas da RecyclerView
        mListener = object : CharacterListener {
            override fun onDetailClick(id: Int) {
                val intent = Intent(context, CharacterDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(AppConstants.BUNDLE.TASKID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            /**
             * Função não utilizada para este fragment
             */
            override fun onFavoriteClick(character: CharacterResponse) {

            }

            override fun onRemoveFavorite(id: Int) {
                mViewModel.deleteFavoriteCharacter(id)
                mAdapter.notifyDataSetChanged()
                Toast.makeText(context, "Removido", Toast.LENGTH_SHORT).show()
            }
        }

        // Cria os observadores
        observe()

        // Retorna view
        mViewModel.getCharacters()
        return root
    }


    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.getCharacters()
    }

    private fun observe() {
        mViewModel.characters.observe(viewLifecycleOwner, {
            if (it.count() > 0) {
                mAdapter.updateListener(it)
            }
        })
        mViewModel.isEmpty.observe(viewLifecycleOwner, {

            if(it){
                recycler_fav_characters.visibility = View.GONE
                empty_view.visibility = View.VISIBLE
            } else {
                recycler_fav_characters.visibility = View.VISIBLE
                empty_view.visibility = View.GONE
            }
        })
    }
}
