package ufrpe.br.visualizadoratividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import ufrpe.br.visualizadoratividades.beans.Usuario
import android.content.Context

class LoginActivity : AppCompatActivity() {

    var database : FirebaseDatabase? = null
    var usuarios : DatabaseReference? = null
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth?.currentUser
        Log.i( "FireBase", "Email:" + currentUser?.email)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = FirebaseDatabase.getInstance()
        usuarios = database!!.getReference("Usuarios")
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

        loginBT.setOnClickListener {
            var usuario = Usuario(loginET.text.toString(), senhaET.text.toString())
            usuario.EncodeString()
            this.entrar(usuario)
        }

    }

    fun entrar(usuario: Usuario){

       if (verificarDados(usuario)){

           usuario.DecodeString()
           mAuth!!.signInWithEmailAndPassword(usuario.email,usuario.senha)
                   .addOnCompleteListener { task ->
                       if (task.isSuccessful) {
                           Toast.makeText(applicationContext, R.string.logado_sucesso, Toast.LENGTH_SHORT).show()
                           val intent = Intent(applicationContext, Main2Activity::class.java)
                           startActivity(intent)
                           val sp = getSharedPreferences("Login", Context.MODE_PRIVATE)
                           val ed = sp.edit()
                           ed.putString("email", usuario.email)
                           ed.putString("senha", usuario.senha)
                           ed.commit()
                           finish()
                       }
                   }
                   .addOnFailureListener { exception ->
                       Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
                   }

       }else{
           Toast.makeText(applicationContext, "Digite corretamente", Toast.LENGTH_SHORT).show()
       }
    }

    private fun verificarDados(usuario: Usuario): Boolean {
        if(usuario.email.isBlank() || usuario.senha.isBlank()){
            return false
        }

        return true
    }

    fun gotoCadastrar(view: View){
        val intent = Intent(this, CadastrarActivity::class.java)
        startActivity(intent)
    }
}
