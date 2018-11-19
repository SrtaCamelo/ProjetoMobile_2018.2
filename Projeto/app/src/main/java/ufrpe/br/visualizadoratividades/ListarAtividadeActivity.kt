package ufrpe.br.visualizadoratividades

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import ufrpe.br.visualizadoratividades.fragments.*

class ListarAtividadeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_atividade)

        var fragmentCursos = FragmentCursos()
        var fragmentEsportes = FragmentEsportes()
        var fragmentIdiomas = FragmentIdiomas()
        var fragmentOutras = FragmentOutras()
        var fragmentTodas = FragmentTodasAtividades()
        var adapter_var = ViewPagerAdapter(supportFragmentManager)
        var vp_listarAtividade : ViewPager = findViewById(R.id.viewpager_id)
        var tb_listarAtividade : TabLayout = findViewById(R.id.tbAtividadeListar)


        adapter_var.AddFragment(fragmentTodas, getString(R.string.todos))
        adapter_var.AddFragment(fragmentCursos, getString(R.string.cursos))
        adapter_var.AddFragment(fragmentEsportes, getString(R.string.esportes))
        adapter_var.AddFragment(fragmentIdiomas, getString(R.string.idiomas))
        adapter_var.AddFragment(fragmentOutras, getString(R.string.outros))

        vp_listarAtividade.adapter = adapter_var
        tb_listarAtividade.setupWithViewPager(vp_listarAtividade)

    }
}
