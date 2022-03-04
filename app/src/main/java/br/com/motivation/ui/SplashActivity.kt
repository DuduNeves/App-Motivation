package br.com.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import br.com.motivation.R
import br.com.motivation.infra.MotivationConstant
import br.com.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        //verificar e tirar a action bar
        if (supportActionBar != null){
            supportActionBar!!.hide()
        }

        findViewById<Button>(R.id.botaoSalvar).setOnClickListener(this)

        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeString("","")

        verifyName()


    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.botaoSalvar){
            handleSave()
        }
    }

    private fun verifyName(){
        val name = mSecurityPreferences.getString(MotivationConstant.KEY.PERSON_NAME)
        if(name != ""){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun handleSave(){
        val name = inputname.text.toString()
        if (name != ""){
            mSecurityPreferences.storeString("name", name)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Por gentileza, preencha o campo com o seu nome.", Toast.LENGTH_LONG).show()

        }
    }
}