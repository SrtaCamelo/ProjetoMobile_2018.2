package ufrpe.br.visualizadoratividades

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.widget.TableLayout
import ufrpe.br.visualizadoratividades.fragments.FragmentCursos
import ufrpe.br.visualizadoratividades.fragments.FragmentEsportes
import ufrpe.br.visualizadoratividades.fragments.FragmentIdiomas
import ufrpe.br.visualizadoratividades.fragments.FragmentOutras

class ListarAtividadeActivity : AppCompatActivity() {
    private var fragmentCursos = FragmentCursos()
    private var fragmentEsportes = FragmentEsportes()
    private var fragmentIdiomas = FragmentIdiomas()
    private var fragmentOutras = FragmentOutras()

    private var adapter_var : ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

    private var vp_listarAtividade : ViewPager = findViewById(R.id.viewpager_id)
    private var tb_listarAtividade : TabLayout = findViewById(R.id.tbAtividadeListar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_atividade)

        adapter_var.AddFragment(fragmentCursos, "Cursos")
        adapter_var.AddFragment(fragmentEsportes, "Esportes")
        adapter_var.AddFragment(fragmentIdiomas, "Idiomas")
        adapter_var.AddFragment(fragmentOutras, "Outros")

        vp_listarAtividade.adapter = adapter_var
        tb_listarAtividade.setupWithViewPager(vp_listarAtividade)

    }
}
