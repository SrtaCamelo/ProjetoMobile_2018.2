package ufrpe.br.visualizadoratividades

import android.app.ListActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_cadastrar.*
import ufrpe.br.visualizadoratividades.beans.Usuario


class CadastrarActivity : AppCompatActivity() {

    val cursos = arrayOf("Agronomia", "Administração", "Ciência da Computação", "Ciências Biológicas", "Ciências Sociais", "Ciência do Consumo", "Engenharia Agrícola", "Engenharia Florestal", "Engenharia Civil", "Engenharia de Pesca", "Engenharia Mecânica", "Engenharia Elétrica", "Engenharia Química", "Educação Física", "Letras", "Licenciatura em Computação", "Filosofia", "Física", "Gastronomia", "Matemática", "Medicina Veterinária", "Psicologia", "Química", "Sistemas da Informação", "Zootecnia")
    val campus = arrayOf("Sede","UACSA", "UAST")
    var database : FirebaseDatabase? = null
    var usuarios : DatabaseReference? = null
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        database = FirebaseDatabase.getInstance()
        usuarios = database!!.getReference("Usuarios")
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

        cursos.sort()

        val adapter1 = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, cursos)
        cursoSpinner.adapter = adapter1
        val adapter2 = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, campus)
        campusSpinner.adapter = adapter2


    }

    fun cadastrar(view: View){
        var usuario = Usuario(nomeET.text.toString(), cursoSpinner.selectedItem.toString(),
                campusSpinner.selectedItem.toString(), loginET.text.toString(), senhaET.text.toString())
        usuario.EncodeString()

        usuarios!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (verificarDados(usuario)){
                    if (usuario.senha.length < 6){
                        Toast.makeText(applicationContext, R.string.senha_pequena,
                                Toast.LENGTH_SHORT).show()
                    }else{
                        if (dataSnapshot.child(usuario.email).exists()){
                            Toast.makeText(applicationContext, R.string.email_cadastrado,
                                    Toast.LENGTH_SHORT).show()
                        }else{
                            usuario.DecodeString()
                            mAuth!!.createUserWithEmailAndPassword(usuario.email,usuario.senha)

                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            usuario.EncodeString()
                                            usuarios!!.child(usuario.email).setValue(usuario)
                                            Toast.makeText(applicationContext,
                                                    R.string.usuario_criado, Toast.LENGTH_SHORT).show()
                                            val intent = Intent(applicationContext, Main2Activity::class.java)
                                            startActivity(intent)
                                            finish()
                                        }
                                    }

                                    .addOnFailureListener { exception ->
                                        if (exception != null) {
                                            Toast.makeText(applicationContext,
                                                    exception.localizedMessage, Toast.LENGTH_LONG).show()
                                        }
                                    }
                        }
                    }
                }
            }
        })

    }

    private fun verificarDados(usuario: Usuario): Boolean {
        if(usuario.nome.isBlank() || usuario.campus.isBlank() || usuario.curso.isBlank()
                || usuario.email.isBlank() || usuario.senha.isBlank()){
            return false
        }

        return true
    }

    fun gotoLogin(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

