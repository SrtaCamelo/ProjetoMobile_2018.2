package ufrpe.br.visualizadoratividades.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.adapters.Todas_atividades_adapter
import ufrpe.br.visualizadoratividades.beans.Atividade

class TodasAtividadesFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view : View = inflater.inflate(R.layout.fragment_todas_atividades, container, false)
        var listview: ListView = view.findViewById(R.id.listTodas)
        var adapter = Todas_atividades_adapter(activity, generateData())
        listview?.adapter = adapter
        return view
    }

    private fun generateData(): ArrayList<Atividade> {
        var result = ArrayList<Atividade>()

        for (i in 1..10){
            var atividade1: Atividade = Atividade("Yoga", "Aula de yoga para alunos com experiência " +
                    "ou sem", "12:00", "Predio de Educação Fisica - Sala 05")
            result.add(atividade1)

            var atividade2: Atividade = Atividade("Ingles Básico", "Aula de inglês nível básico",
                    "19:00", "CEGOE- Sala 10")
            result.add(atividade2)

            var atividade3: Atividade = Atividade("Inglês Avançado", "Aula de inglês para nível avançado",
                    "10:00", "CEGOE - Sala 4")
            result.add(atividade3)

            var atividade4: Atividade = Atividade("Judô", "Aula de Judô para todos os níveis.",
                    "18:30", "Predio de Educação Fisica - Sala 03")
            result.add(atividade4)
        }

        return result
    }
}