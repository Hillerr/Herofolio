package com.hiller.herofolio.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.hiller.herofolio.R
import com.hiller.herofolio.service.constants.AppConstants
import com.hiller.herofolio.view.utils.getImageByUrl
import com.hiller.herofolio.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*

class CharacterDetailActivity : AppCompatActivity() {

    private var mCharacterId: Int = 0
    private var mCharacterName: String = ""
    private var mCharacterDescription: String = ""
    private var mThumbnail: String =  ""
    private lateinit var mViewModel: CharacterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mViewModel = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)

        loadDataFromActivity()
    }

    /**
     * Obtem o id do personagem selecionado
     */
    private fun loadDataFromActivity() {
        val bundle = intent.extras

        if(bundle != null){
            mCharacterId = bundle.getInt(AppConstants.BUNDLE.TASKID)
            mCharacterName = bundle.getString(AppConstants.BUNDLE.NAME, "")
            mCharacterDescription = bundle.getString(AppConstants.BUNDLE.DESCRIPTION, "")
            mThumbnail = bundle.getString(AppConstants.BUNDLE.THUMBNAIL, "")
            mViewModel.getCharacter(mCharacterId)

            character_name.text = mCharacterName
            character_description.text = mCharacterDescription
            character_thumbnail.getImageByUrl(mThumbnail)
        }
    }

}
