package ufrpe.br.visualizadoratividades

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    var preferences : SharedPreferences? = null
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }
        preferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
        supportActionBar!!.hide()


        Handler().postDelayed({
            checkLogin(preferences!!.getString("email", ""), preferences!!.getString("senha", ""))
        }, 2000)
    }

    private fun checkLogin(email: String?, senha: String?){
        if (email.isNullOrEmpty() or senha.isNullOrEmpty()){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            mAuth!!.signInWithEmailAndPassword(email!!, senha!!)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(applicationContext, Main2Activity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    .addOnFailureListener { exception ->
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
        }
    }

}
