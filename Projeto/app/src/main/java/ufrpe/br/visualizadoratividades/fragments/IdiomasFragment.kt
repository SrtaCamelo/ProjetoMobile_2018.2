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

class IdiomasFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_idiomas, container, false)
        var listview: ListView = view.findViewById(R.id.listIdiomas)
        var adapter = AtividadesAdapter(activity, generateData())
        listview?.adapter = adapter
        return view
    }

    private fun generateData(): ArrayList<Atividade> {
        var result = ArrayList<Atividade>()

        for (i in 1..10){
            var atividade2: Atividade = Atividade("Ingles Básico", "Aula de inglês nível básico",
                    "19:00", "CEGOE- Sala 10")
            result.add(atividade2)
            var atividade3: Atividade = Atividade("Inglês Avançado", "Aula de inglês para nível avançado",
                    "10:00", "CEGOE - Sala 4")
            result.add(atividade3)
        }

        return result
    }
}