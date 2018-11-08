package ufrpe.br.visualizadoratividades

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import ufrpe.br.visualizadoratividades.fragments.FragmentCursos
import ufrpe.br.visualizadoratividades.fragments.FragmentEsportes
import ufrpe.br.visualizadoratividades.fragments.FragmentIdiomas
import ufrpe.br.visualizadoratividades.fragments.FragmentOutras

class ListarAtividadeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_atividade)

        var adapter : ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        var fragmentCursos : FragmentCursos = FragmentCursos()
        var fragmentEsportes : FragmentEsportes = FragmentEsportes()
        var fragmentIdiomas : FragmentIdiomas = FragmentIdiomas()
        var fragmentOutras : FragmentOutras = FragmentOutras()

        adapter.AddFragment(fragmentCursos, "Cursos")
        adapter.AddFragment(fragmentEsportes, "Esportes")
        adapter.AddFragment(fragmentIdiomas, "Idiomas")
        adapter.AddFragment(fragmentOutras, "Outros")

        var listarAtividade : ViewPager

    }
}
