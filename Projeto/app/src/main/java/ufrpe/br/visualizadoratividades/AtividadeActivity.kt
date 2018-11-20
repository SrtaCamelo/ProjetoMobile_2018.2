package ufrpe.br.visualizadoratividades

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.atividades_tela.*

class AtividadeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_atividade)
        textViewNome.setText(intent.getStringExtra("titulo"))
        textViewHorario.setText(intent.getStringExtra("horario"))
    }
}