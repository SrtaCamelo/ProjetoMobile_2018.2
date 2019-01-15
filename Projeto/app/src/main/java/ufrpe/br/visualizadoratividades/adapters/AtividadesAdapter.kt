package ufrpe.br.visualizadoratividades.adapters

import android.app.Activity
import android.content.Context
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
            this.tvHorario = row?.findViewById(R.id.dHorario)
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
        viewHolder.tvHorario?.text = atividadeDto.local

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
        val usuario_database : DatabaseReference? = database!!.getReference("Usuarios")
        val usuario = mAuth!!.currentUser
        var email = usuario!!.email.toString()
        email = email.replace(".", ",")


        usuario_database?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.child(email).exists()){
                    var usuario_aux = dataSnapshot.child(email).getValue(Usuario::class.java) as Usuario

                    if(!usuario_aux.getFavorito().contains(atividade.id)){
                        usuario_aux!!.addFavorito(atividade.id)

                        usuario_database!!.child(email).setValue(usuario_aux)

//                        Toast.makeText(this@AtividadesAdapter, R.string.favoritos_sucesso, Toast.LENGTH_SHORT).show()
                    }else{
//                        Toast.makeText(this@AtividadesAdapter, R.string.favorito_ja_existe, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

}