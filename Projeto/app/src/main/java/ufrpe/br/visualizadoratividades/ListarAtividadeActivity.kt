package ufrpe.br.visualizadoratividades

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import ufrpe.br.visualizadoratividades.fragments.FragmentCursos
import ufrpe.br.visualizadoratividades.fragments.FragmentEsportes
import ufrpe.br.visualizadoratividades.fragments.FragmentIdiomas
import ufrpe.br.visualizadoratividades.fragments.FragmentOutras

class ListarAtividadeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_atividade)

        var fragmentCursos = FragmentCursos()
        var fragmentEsportes = FragmentEsportes()
        var fragmentIdiomas = FragmentIdiomas()
        var fragmentOutras = FragmentOutras()

        var adapter_var : ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        var vp_listarAtividade : ViewPager = findViewById(R.id.viewpager_id)
        var tb_listarAtividade : TabLayout = findViewById(R.id.tbAtividadeListar)

        adapter_var.AddFragment(fragmentCursos, "Cursos")
        adapter_var.AddFragment(fragmentEsportes, "Esportes")
        adapter_var.AddFragment(fragmentIdiomas, "Idiomas")
        adapter_var.AddFragment(fragmentOutras, "Outros")

        vp_listarAtividade.adapter = adapter_var
        tb_listarAtividade.setupWithViewPager(vp_listarAtividade)

    }
}
