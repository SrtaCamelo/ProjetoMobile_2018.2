package ufrpe.br.visualizadoratividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import ufrpe.br.visualizadoratividades.fragments.*


class Main2Activity : AppCompatActivity() {

    private var mMainFrame : FrameLayout? = null

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.nav_home -> {
                    val fragment = HomeFragment()
                    addFragment(fragment)
                    return true
                }

                R.id.nav_favoritos -> {
                    val fragment = FavoritosFragment()
                    addFragment(fragment)
                    return true
                }
            }
            return false

        }
    }

    fun gotoCadastrar(view: View){
        Toast.makeText(applicationContext, "clicado", Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext, CadastrarAtividadeActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mMainFrame = findViewById(R.id.main_frame) as FrameLayout
        val mMainNav = findViewById(R.id.main_nav) as BottomNavigationView

        mMainNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragmentHome = HomeFragment()
        addFragment(fragmentHome)
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit()
    }

    fun detalhes(view: View?){
        val fragment = AtividadeDetalhesFragment()
        addFragment(fragment)
    }
}

