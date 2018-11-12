package ufrpe.br.visualizadoratividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class CadastrarAtividadeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_atividade)
    }

    fun cadastrar(view: View?){
        Toast.makeText(this, R.string.registro_sucesso, Toast.LENGTH_SHORT).show()
        val i = Intent(this, ListarAtividadeActivity::class.java)
        startActivity(i)
    }
}
