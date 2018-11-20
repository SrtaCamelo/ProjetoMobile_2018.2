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

class TodasAtividadesFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view : View = inflater.inflate(R.layout.fragment_todas_atividades, container, false)
        var listview: ListView = view.findViewById(R.id.listTodas)
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
            var atividade2: Atividade = Atividade("Ingles Básico", "Aula de inglês nível básico",
                    "19:00", "CEGOE- Sala 10")
            result.add(atividade2)
            var atividade3: Atividade = Atividade("Inglês Avançado", "Aula de inglês para nível avançado",
                    "10:00", "CEGOE - Sala 4")
            result.add(atividade3)
            var atividade4: Atividade = Atividade("Judô", "Aula de Judô para todos os níveis.",
                    "18:30", "Predio de Educação Fisica - Sala 03")
            result.add(atividade4)
            var atividade5: Atividade = Atividade("Karate", "Aula Karate para todos os níveis",
                    "19:30", "Predio de Educação Fisica - Sala 05")
            result.add(atividade5)
            var atividade6: Atividade = Atividade("Meditação", "Aula de meditação, para quem já " +
                    "tem uma conhecimento ou não.", "13:00", "DB - Sala 30")
            result.add(atividade6)
        }

        return result
    }
}