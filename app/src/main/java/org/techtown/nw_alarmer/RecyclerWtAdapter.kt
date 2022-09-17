package org.techtown.nw_alarmer

import android.content.Context
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wt_view.view.*
import org.techtown.nw_alarmer.databinding.FragmentMondayBinding
import org.techtown.nw_alarmer.databinding.WtViewBinding

class RecyclerWtAdapter (private val items: ArrayList<WTData>,val context : Context) : RecyclerView.Adapter<RecyclerWtAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerWtAdapter.ViewHolder, position: Int) {

        val item = items[position]

        val listener = View.OnClickListener {

                it ->
            //Toast.makeText(it.context, "Clicked -> ID : ${item.user_id}, Name : ${item.user_name}", Toast.LENGTH_SHORT).show()


        }

        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = WtViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)

    }

   //  각 항목에 필요한 기능을 구현
   inner class ViewHolder(private val binding : WtViewBinding) : RecyclerView.ViewHolder(binding.root) {

       fun bind(listener: View.OnClickListener, item: WTData) {

           binding.webTitle.text = item.title
           //작가 제목

       }



   }
}
