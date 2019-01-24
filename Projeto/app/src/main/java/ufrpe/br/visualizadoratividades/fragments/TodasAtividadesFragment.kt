package ufrpe.br.visualizadoratividades.fragments

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.adapters.AtividadesAdapter
import ufrpe.br.visualizadoratividades.beans.Atividade
import java.time.LocalDateTime
import java.util.*

class TodasAtividadesFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var database : FirebaseDatabase? = null
        var atividades_database : DatabaseReference? = null

        database = FirebaseDatabase.getInstance()
        atividades_database = database.getReference("Atividades")

        val atividade_list = ArrayList<Atividade>()

        val view : View = inflater.inflate(R.layout.fragment_todas_atividades, container, false)
        val listview: ListView = view.findViewById(R.id.listTodas)

        atividades_database.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    atividade_list.clear()
                    for (e in p0.children) {
                        val atividade = e.getValue(Atividade::class.java)
                        atividade_list.add(atividade!!)
                    }
                    Collections.sort(atividade_list, object : Comparator<Atividade> {
                        override fun compare(o1: Atividade, o2: Atividade): Int {
                            return o1.dataCadastro.compareTo(o2.dataCadastro)
                        }
                    })
                    val adapter = AtividadesAdapter(activity, atividade_list)
                    adapter.notifyDataSetChanged()
                    listview.adapter = adapter

                }
            }
        })

        return view
    }



}