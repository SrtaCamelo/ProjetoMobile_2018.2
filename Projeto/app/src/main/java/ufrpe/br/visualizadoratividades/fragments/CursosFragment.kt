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

class CursosFragment : Fragment(){

    var database : FirebaseDatabase? = null
    var atividades_database : DatabaseReference? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        database = FirebaseDatabase.getInstance()
        atividades_database = database!!.getReference("Atividades")

        var view : View = inflater.inflate(R.layout.fragment_cursos, container, false)
        var listview: ListView = view.findViewById(R.id.listCursos)
        var adapter = AtividadesAdapter(activity, generateData())
        listview?.adapter = adapter
        return view
    }

    private fun generateData(): ArrayList<Atividade> {
        var result = ArrayList<Atividade>()

        atividades_database!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    for (e in p0.children){
                        val atividade = e.getValue(Atividade::class.java)
                        if (atividade?.tipo.equals("Cursos")) {
                            result.add(atividade!!)
                        }
                    }
                }
            }
        })

        return result
    }
}