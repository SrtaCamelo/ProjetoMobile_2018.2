package ufrpe.br.visualizadoratividades.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ufrpe.br.visualizadoratividades.beans.Atividade
import ufrpe.br.visualizadoratividades.R


class Todas_atividades_adapter (private var activity : Activity,
                                private var items : ArrayList<Atividade>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var tvTitulo: TextView? = null
        var tvHorario: TextView? = null

        init {
            this.tvTitulo = row?.findViewById<TextView>(R.id.tvTitulo)
            this.tvHorario = row?.findViewById<TextView>(R.id.tvHorario)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.atividade_list_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var atividadeDto = items[position]
        viewHolder.tvTitulo?.text = atividadeDto.titulo
        viewHolder.tvHorario?.text = atividadeDto.horario

        return view as View
    }

    override fun getItem(i: Int): Atividade {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}