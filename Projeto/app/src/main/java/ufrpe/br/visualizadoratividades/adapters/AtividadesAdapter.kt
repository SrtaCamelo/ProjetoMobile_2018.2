package ufrpe.br.visualizadoratividades.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ufrpe.br.visualizadoratividades.beans.Atividade
import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.beans.Usuario
import java.util.*


class AtividadesAdapter (private var activity: Activity?,
                         private var items : ArrayList<Atividade>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var tvTitulo: TextView? = null
        var tvHorario: TextView? = null
        var btFavorito: Button? = null

        init {
            this.tvTitulo = row?.findViewById(R.id.tvTitulo)
            this.tvHorario = row?.findViewById(R.id.tvHorario)
            this.btFavorito = row?.findViewById(R.id.btFavoritar)
        }
    }

    var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    var database : FirebaseDatabase? = FirebaseDatabase.getInstance()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.atividade_list_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var atividadeDto = items[position]
        viewHolder.tvTitulo?.text = atividadeDto.titulo
        viewHolder.tvHorario?.text = atividadeDto.horario

        viewHolder.btFavorito?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                favoritar(atividadeDto)
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

    private fun favoritar(atividade : Atividade){
        var usuario_database : DatabaseReference? = database!!.getReference("Usuarios")
        var usuario = mAuth!!.currentUser
        var email = usuario!!.email.toString()
        email = email.replace(".", ",")


        usuario_database?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var usuario_aux = dataSnapshot.child(email).getValue(Usuario::class.java) as Usuario

                val key_atividade = buscarAtividade(atividade)

                usuario_aux!!.addFavorito(key_atividade)

                usuario_database!!.child(email).setValue(usuario_aux)
            }
        })
    }

    private fun buscarAtividade(atividade: Atividade): String{
        var atividade_database : DatabaseReference? = database!!.getReference("Atividades")

        atividade_database?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (e in p0.children) {
                        val atividade_aux = e.getValue(Atividade::class.java)

                        TODO("Implementar a pesquisa da atividade no banco de dados e retornar o ID")
                    }
                }
            }
        })

        return ""
    }
}