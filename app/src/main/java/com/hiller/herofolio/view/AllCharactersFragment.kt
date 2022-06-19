package com.hiller.herofolio.view

import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
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
import kotlinx.android.synthetic.main.fragment_all_heros.*

class AllCharactersFragment : Fragment(), View.OnClickListener {

    private lateinit var mViewModel: AllCharactersViewModel
    private lateinit var mListener: CharacterListener
    private val mAdapter = CharacterAdapter()
    private lateinit var mLoading: ProgressBar
    private lateinit var mFilterBox: ConstraintLayout
    private lateinit var mSearchText: TextView
    private lateinit var mSearchButton: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        mViewModel = ViewModelProvider(this).get(AllCharactersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all_heros, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_characters)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        // Eventos disparados ao clicar nas linhas da RecyclerView
        mListener = object : CharacterListener {
            // Visualizar detalhes do persongem
            override fun onDetailClick(id: Int, name: String, description: String, thumbnail: String) {
                val intent = Intent(context, CharacterDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(AppConstants.BUNDLE.TASKID, id)
                bundle.putString(AppConstants.BUNDLE.NAME, name)
                bundle.putString(AppConstants.BUNDLE.DESCRIPTION, description)
                bundle.putString(AppConstants.BUNDLE.THUMBNAIL, thumbnail)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            // Salvar/Remover personagem como favorito
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

            override fun onOrderClick() {
                mViewModel.orderCharacters()
                mAdapter.notifyDataSetChanged()
            }
        }

        // Cria os observadores
        observe()

        // Listeners
        listeners(root)

        // Retorna view
        mFilterBox = root.findViewById<ConstraintLayout>(R.id.filter_box)
        mFilterBox.visibility = View.GONE
        mViewModel.getCharacters()
        mLoading = root.findViewById<ProgressBar>(R.id.loading_indicator)
        mLoading.visibility = View.VISIBLE

        return root
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
    }

    private fun observe() {
        mViewModel.mValidation.observe(viewLifecycleOwner, Observer{
            if(it){

            } else {
                Toast.makeText(context, R.string.ERROR_LOAD_HERO, Toast.LENGTH_SHORT).show()
            }
            mLoading.visibility = View.GONE
            mFilterBox.visibility = View.VISIBLE
        })
        mViewModel.characters.observe(viewLifecycleOwner,{
            if(it.count() > 0){
                mAdapter.updateListener(it)
            }
        })
        mViewModel.orderListener.observe(viewLifecycleOwner, {
            if(it){
                filter_arrow.setImageResource(R.drawable.ic_arrow_upward)
            } else {
                filter_arrow.setImageResource(R.drawable.ic_arrow_downward)
            }
        })
        mViewModel.searchListener.observe(viewLifecycleOwner, {
            if(it){
                mAdapter.updateListener(mViewModel.searchedCharacters.value!!)
                search_text.visibility = View.GONE
                search_icon.visibility = View.GONE
                search_clean.visibility = View.VISIBLE
            } else {
                mAdapter.updateListener(mViewModel.characters.value!!)
                search_text.visibility = View.VISIBLE
                search_icon.visibility = View.VISIBLE
                search_clean.visibility = View.GONE
            }
        })
    }

    private fun listeners(root: View){
        val filterArrow = root.findViewById<ImageView>(R.id.filter_arrow)
        val searchArrow = root.findViewById<ImageView>(R.id.search_icon)
        val searchClean = root.findViewById<TextView>(R.id.search_clean)
        filterArrow.setOnClickListener(this)
        searchArrow.setOnClickListener(this)
        searchClean.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.filter_arrow -> {
                mViewModel.orderCharacters()
            }
            R.id.search_icon -> {
                val name: String = search_text.text.toString()
                mViewModel.filterCharactersByName(name)
            }
            R.id.search_clean -> {
                mViewModel.clearSearch()
            }
        }
    }



}
