package ufrpe.br.visualizadoratividades.fragments

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.adapters.AtividadesAdapter
import ufrpe.br.visualizadoratividades.adapters.MinhasAtividadesAdapter
import ufrpe.br.visualizadoratividades.beans.Atividade

class MinhasAtividadesFragment() : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var database : FirebaseDatabase? = null
        var atividades_database : DatabaseReference? = null
        var mAuth: FirebaseAuth? = null

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        atividades_database = database.getReference("Atividades")

        var atividade_list = ArrayList<Atividade>()

        var view : View = inflater.inflate(R.layout.fragment_minhas_atividades, container, false)
        var listview: ListView = view.findViewById(R.id.listMinhas)

        atividades_database.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (e in p0.children) {
                        val atividade = e.getValue(Atividade::class.java)
                        if (atividade?.autor.equals(mAuth.currentUser!!.email.toString())) {
                            atividade_list.add(atividade!!)
                        }
                    }

                    var adapter = MinhasAtividadesAdapter(activity, atividade_list)
                    adapter.notifyDataSetChanged()
                    listview.adapter = adapter
                }
            }
        })

        return view
    }
}