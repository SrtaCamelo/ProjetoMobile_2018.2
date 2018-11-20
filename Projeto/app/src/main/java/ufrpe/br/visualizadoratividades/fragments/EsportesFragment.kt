package ufrpe.br.visualizadoratividades.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.adapters.AtividadesAdapter
import ufrpe.br.visualizadoratividades.beans.Atividade

class EsportesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_esportes, container, false)
        var listview: ListView = view.findViewById(R.id.listEsportes)
        var adapter = AtividadesAdapter(activity, generateData())
        listview?.adapter = adapter
        return view
    }

    private fun generateData(): ArrayList<Atividade> {
        var result = ArrayList<Atividade>()

        for (i in 1..10){
            var atividade1: Atividade = Atividade("Karate", "Aula Karate para todos os níveis",
                    "19:30", "Predio de Educação Fisica - Sala 05")
            result.add(atividade1)
            var atividade4: Atividade = Atividade("Judô", "Aula de Judô para todos os níveis.",
                    "18:30", "Predio de Educação Fisica - Sala 03")
            result.add(atividade4)
        }

        return result
    }
}