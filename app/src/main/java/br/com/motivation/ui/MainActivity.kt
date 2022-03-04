package br.com.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.motivation.R
import br.com.motivation.infra.MotivationConstant
import br.com.motivation.infra.SecurityPreferences
import br.com.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter: Int = MotivationConstant.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }
        mSecurityPreferences = SecurityPreferences(this)
        val name = mSecurityPreferences.getString(MotivationConstant.KEY.PERSON_NAME)
        texteName.text = "Olá, $name"


        //logica inicial de seleção
        imageAll.setColorFilter(resources.getColor(R.color.purpleG))
        handleNewFrase()

        buttonNewFrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        val id = view.id

        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning )

        if(id == R.id.buttonNewFrase){
            handleNewFrase()
        }else if(id in listFilter){
                handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {
        imageAll.setColorFilter(resources.getColor(R.color.white))
        imageHappy.setColorFilter(resources.getColor(R.color.white))
        imageMorning.setColorFilter(resources.getColor(R.color.white))

        when (id) {

            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.purpleG))
                mPhraseFilter = MotivationConstant.PHRASEFILTER.ALL
            }

            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.purpleG))
                mPhraseFilter = MotivationConstant.PHRASEFILTER.HAPPY

            }

            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.purpleG))
                mPhraseFilter = MotivationConstant.PHRASEFILTER.MORNING

            }

        }

    }

    private fun handleNewFrase() {
            textPhrase.text = Mock().getPhrase(mPhraseFilter)
       }


}