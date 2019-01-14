package ufrpe.br.visualizadoratividades.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.R.string.favoritar
import ufrpe.br.visualizadoratividades.beans.Atividade
import ufrpe.br.visualizadoratividades.beans.Usuario
import java.util.ArrayList

class MinhasAtividadesAdapter (private var activity: Activity?,
                         private var items : ArrayList<Atividade>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var tvTitulo: TextView? = null
        var tvHorario: TextView? = null
        var btExcluir: ImageButton? = null

        init {
            this.tvTitulo = row?.findViewById(R.id.tvTitulo)
            this.tvHorario = row?.findViewById(R.id.tvHorario)
            this.btExcluir = row?.findViewById(R.id.deleteButton)
        }
    }

    var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    var database : FirebaseDatabase? = FirebaseDatabase.getInstance()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.minhas_atividades_list_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var atividadeDto = items[position]
        viewHolder.tvTitulo?.text = atividadeDto.titulo
        viewHolder.tvHorario?.text = atividadeDto.horario

        viewHolder.btExcluir?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                excluir(atividadeDto)
            }
        })

        return view as View
    }

    override fun getItem(i: Int): Atividade {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    fun excluir(atividade: Atividade){
        var reference = database!!.reference.child("Atividades").child(atividade.id)
        reference.removeValue()
    }


}