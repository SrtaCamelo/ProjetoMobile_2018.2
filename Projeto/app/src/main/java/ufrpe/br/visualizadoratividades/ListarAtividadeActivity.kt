package ufrpe.br.visualizadoratividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import kotlinx.android.synthetic.main.atividade_list_row.*
import ufrpe.br.visualizadoratividades.fragments.*

class ListarAtividadeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_atividade)

        var fragmentCursos = CursosFragment()
        var fragmentEsportes = EsportesFragment()
        var fragmentIdiomas = IdiomasFragment()
        var fragmentOutras = OutrasFragment()
        var fragmentTodas = TodasAtividadesFragment()
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
