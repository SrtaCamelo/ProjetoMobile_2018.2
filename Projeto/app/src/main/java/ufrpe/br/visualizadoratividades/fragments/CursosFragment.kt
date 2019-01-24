package ufrpe.br.visualizadoratividades.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.ListView
import com.google.firebase.database.*
import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.adapters.AtividadesAdapter
import ufrpe.br.visualizadoratividades.beans.Atividade
import java.util.*

class CursosFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var database : FirebaseDatabase? = null
        var atividades_database : DatabaseReference? = null

        database = FirebaseDatabase.getInstance()
        atividades_database = database.getReference("Atividades")

        var atividade_list = ArrayList<Atividade>()

        var view : View = inflater.inflate(R.layout.fragment_cursos, container, false)
        var listview: ListView = view.findViewById(R.id.listCursos)

        atividades_database.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    for (e in p0.children){
                        val atividade = e.getValue(Atividade::class.java)
                        if (atividade?.tipo.equals("Cursos")) {
                            atividade_list.add(atividade!!)
                        }
                    }
                    Collections.sort(atividade_list, object : Comparator<Atividade> {
                        override fun compare(o1: Atividade, o2: Atividade): Int {
                            return o1.dataCadastro.compareTo(o2.dataCadastro)
                        }
                    })

                    val adapter = AtividadesAdapter(activity, atividade_list)
                    listview.adapter = adapter
                }
            }
        })

        return view
    }
}