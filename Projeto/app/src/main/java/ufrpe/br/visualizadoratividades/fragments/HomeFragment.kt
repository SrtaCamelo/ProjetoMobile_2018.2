package ufrpe.br.visualizadoratividades.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import ufrpe.br.visualizadoratividades.R
import ufrpe.br.visualizadoratividades.adapters.ViewPagerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v : View = inflater.inflate(R.layout.fragment_listar_atividade, container, false)

        val fragmentCursos = CursosFragment()
        val fragmentEsportes = EsportesFragment()
        val fragmentIdiomas = IdiomasFragment()
        val fragmentOutras = OutrasFragment()
        val fragmentTodas = TodasAtividadesFragment()

        var adapter_var = ViewPagerAdapter(childFragmentManager)
        val vp_listarAtividade : ViewPager = v.findViewById(R.id.viewpager_id)
        val tb_listarAtividade : TabLayout = v.findViewById(R.id.tbAtividadeListar)


        adapter_var.AddFragment(fragmentTodas, getString(R.string.todos))
        adapter_var.AddFragment(fragmentCursos, getString(R.string.cursos))
        adapter_var.AddFragment(fragmentEsportes, getString(R.string.esportes))
        adapter_var.AddFragment(fragmentIdiomas, getString(R.string.idiomas))
        adapter_var.AddFragment(fragmentOutras, getString(R.string.outros))

        vp_listarAtividade.adapter = adapter_var
        tb_listarAtividade.setupWithViewPager(vp_listarAtividade)

        return v
    }



}
