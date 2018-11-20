package ufrpe.br.visualizadoratividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastrar_atividade.*

class CadastrarAtividadeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_atividade)

        cadastrarButton.setOnClickListener{
            Toast.makeText(this, R.string.registro_sucesso, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }
    }



}
