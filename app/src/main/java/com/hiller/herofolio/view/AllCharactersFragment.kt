package com.hiller.herofolio.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hiller.herofolio.R
import com.hiller.herofolio.service.constants.AppConstants
import com.hiller.herofolio.service.listener.CharacterListener
import com.hiller.herofolio.service.model.CharacterResponse
import com.hiller.herofolio.view.adapter.CharacterAdapter
import com.hiller.herofolio.viewmodel.AllCharactersViewModel

class AllCharactersFragment : Fragment() {

    private lateinit var mViewModel: AllCharactersViewModel
    private lateinit var mListener: CharacterListener
    private val mAdapter = CharacterAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        mViewModel = ViewModelProvider(this).get(AllCharactersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all_heros, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_characters)
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

            override fun onFavoriteClick(character: CharacterResponse) {
                mViewModel.favoriteCharacter(character)
                mAdapter.notifyDataSetChanged()

                if(character.isFavorite){
                    Toast.makeText(context, "${character.name} salvo", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "${character.name} removido", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onRemoveFavorite(id: Int) {
                TODO("Not yet implemented")
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
        mViewModel.mValidation.observe(viewLifecycleOwner, Observer{
            if(it){
                Toast.makeText(context, "Deu bom", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Deu ruim", Toast.LENGTH_SHORT).show()
            }
        })
        mViewModel.characters.observe(viewLifecycleOwner,{
            if(it.count() > 0){
                mAdapter.updateListener(it)
            }
        })
    }

}
