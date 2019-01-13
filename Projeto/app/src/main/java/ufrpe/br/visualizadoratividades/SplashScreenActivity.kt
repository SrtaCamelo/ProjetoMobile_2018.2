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
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        supportActionBar!!.hide()


        Handler().postDelayed({
            if(checkLogin(preferences!!.getString("email", ""), preferences!!.getString("senha", ""))){
                val intent = Intent(applicationContext, Main2Activity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000)
    }

    private fun checkLogin(email: String?, senha: String?): Boolean {
        var result: Boolean = false

        if (email.isNullOrBlank() or senha.isNullOrBlank()){
            return result
        }
        mAuth!!.signInWithEmailAndPassword(email!!, senha!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, R.string.logado_sucesso, Toast.LENGTH_SHORT).show()
                        result = true
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
        return result
    }

}
