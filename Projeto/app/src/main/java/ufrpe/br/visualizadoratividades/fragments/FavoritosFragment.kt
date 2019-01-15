package ufrpe.br.visualizadoratividades.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.adapters.AtividadesAdapter
import ufrpe.br.visualizadoratividades.adapters.FavoritoAdapter
import ufrpe.br.visualizadoratividades.beans.Atividade
import ufrpe.br.visualizadoratividades.beans.Usuario

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritosFragment : Fragment() {

    private var database : FirebaseDatabase? = FirebaseDatabase.getInstance()
    private var atividades_database : DatabaseReference? = database!!.getReference("Atividades")
    private var usuario_database : DatabaseReference? = database!!.getReference("Usuarios")
    private var favoritosIds : ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_favoritos, container, false)

        val atividade_list = ArrayList<Atividade>()

        val listview: ListView = view.findViewById(R.id.listFavoritos)

        getFavoritosUsuario()

        atividades_database!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (e in p0.children) {
                        val atividade = e.getValue(Atividade::class.java)

                        if (favoritosIds.contains(atividade!!.id)) {
                            atividade_list.add(atividade!!)
                        }
                    }

                    val adapter = FavoritoAdapter(activity, atividade_list)
                    listview.adapter = adapter
                }
            }
        })

        return view
    }

    private fun getFavoritosUsuario(){
        val mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
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

                    favoritosIds = usuario_aux.getFavorito()
                }
            }
        })
    }

}
