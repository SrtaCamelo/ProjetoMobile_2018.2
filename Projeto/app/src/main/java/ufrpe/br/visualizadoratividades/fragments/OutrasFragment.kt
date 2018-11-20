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

class OutrasFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_outras, container, false)
        var listview: ListView = view.findViewById(R.id.listOutras)
        var adapter = AtividadesAdapter(activity, generateData())
        listview?.adapter = adapter
        return view
    }

    private fun generateData(): ArrayList<Atividade> {
        var result = ArrayList<Atividade>()

        for (i in 1..10){
            var atividade1: Atividade = Atividade("Yoga", "Aula de yoga para alunos com experiência " +
                    "ou sem", "12:00", "Predio de Educação Fisica - Sala 05")
            result.add(atividade1)
            var atividade6: Atividade = Atividade("Meditação", "Aula de meditação, para quem já " +
                    "tem uma conhecimento ou não.", "13:00", "DB - Sala 30")
            result.add(atividade6)
        }

        return result
    }
}