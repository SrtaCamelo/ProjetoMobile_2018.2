package ufrpe.br.visualizadoratividades

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import ufrpe.br.visualizadoratividades.beans.Usuario
import ufrpe.br.visualizadoratividades.R.id.loginBT




class LoginActivity : AppCompatActivity() {

    var database : FirebaseDatabase? = null
    var usuarios : DatabaseReference? = null

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = FirebaseDatabase.getInstance()
        usuarios = database!!.getReference("Usuarios")

        loginBT.setOnClickListener {
            var usuario = Usuario(loginET.text.toString(), senhaET.text.toString())
            usuario.EncodeString()
            this.entrar(usuario)
        }

    }

    fun entrar(usuario: Usuario){

        usuarios!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (verificarDados(usuario)){
                    if (dataSnapshot.child(usuario.email).exists()) {
                        var login = dataSnapshot.child(usuario.email).getValue(Usuario::class.java)
                        if(login!!.senha.equals(usuario.senha)){
                            Toast.makeText(applicationContext, R.string.logado_sucesso, Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, Main2Activity::class.java)
                            startActivity(intent)
                        } else{
                            Toast.makeText(applicationContext, R.string.senha_incorreta, Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(applicationContext, R.string.email_nao_cadastrado, Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(applicationContext, "Digite corretamente", Toast.LENGTH_SHORT).show()
                }

            }
        })

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
