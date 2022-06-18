package com.hiller.herofolio.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.hiller.herofolio.R
import com.hiller.herofolio.service.constants.AppConstants
import com.hiller.herofolio.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.SimpleDateFormat
import java.util.*

class CharacterDetailActivity : AppCompatActivity(), View.OnClickListener {

    private var mTaskId: Int = 0
    private lateinit var mViewModel: CharacterDetailViewModel
    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    private val mListPriorityId: MutableList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mViewModel = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)

        // Inicializa eventos
        listeners()
        observe()

        loadDataFromActivity()
    }

    private fun loadDataFromActivity() {
        val bundle = intent.extras

        if(bundle != null){
            mTaskId = bundle.getInt(AppConstants.BUNDLE.TASKID)
//            mViewModel.load(mTaskId)
        }
    }

    override fun onClick(v: View) {

    }


    private fun observe() {

    }


    private fun listeners() {
        button_save.setOnClickListener(this)
        button_date.setOnClickListener(this)
    }


}
