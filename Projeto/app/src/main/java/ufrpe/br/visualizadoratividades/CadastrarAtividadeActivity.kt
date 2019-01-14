package ufrpe.br.visualizadoratividades


import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_cadastrar_atividade.*
import ufrpe.br.visualizadoratividades.beans.Atividade
import ufrpe.br.visualizadoratividades.fragments.TimePickerFragment
import java.util.*

class CadastrarAtividadeActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        var hora = hourOfDay.toString()
        var minuto = minute.toString()
        val resultado = "$hora:$minuto"
        horaButton.setText(resultado)
    }

    var mAuth: FirebaseAuth? = null
    val tipo = arrayOf("Cursos","Esportes","Idiomas", "Outros")
    var database : FirebaseDatabase? = null
    var usuarios : DatabaseReference? = null
    var dias = arrayOf("Segunda", "Terça", "Quarta", "Quinta", "Sexta")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_cadastrar_atividade)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        usuarios = database!!.getReference("Atividades")

        val adapter1 = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tipo)
        tipoSpinner.adapter = adapter1
        val adapter2 = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dias)
        diaSpinner.adapter = adapter2
    }

    fun cadastrar(view : View){
        var usuario = mAuth!!.currentUser
        val uuid = UUID.randomUUID()

        var atividade = Atividade(tituloEdit.text.toString(), descricaoET.text.toString(),
                horaButton.text.toString(), localEdit.text.toString(), tipoSpinner.selectedItem.toString(),
                diaSpinner.selectedItem.toString(), usuario!!.email.toString(), uuid.toString())


        usuarios!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (verificarDados(atividade)){

                    usuarios!!.child(uuid.toString()).setValue(atividade)
                    Toast.makeText(applicationContext, R.string.registro_sucesso, Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })
    }

    fun definirHora(view : View){
        var timePicker = TimePickerFragment()
        timePicker.show(supportFragmentManager, "time picker")
    }

    private fun verificarDados(atividade: Atividade): Boolean {
        if(tituloEdit.text.isBlank() or localEdit.text.isBlank())
            return false
        return true
    }

}