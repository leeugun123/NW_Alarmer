package org.techtown.nw_alarmer

import android.content.Context
import android.content.Intent
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.wt_view.view.*
import org.techtown.nw_alarmer.databinding.FragmentMondayBinding
import org.techtown.nw_alarmer.databinding.WtViewBinding
import org.techtown.nw_alarmer.localDB.ListDatabase
import org.techtown.nw_alarmer.localDB.MyWtList
import org.techtown.nw_alarmer.localDB.MyWtListRecycler
import org.techtown.nw_alarmer.localDB.WTViewModel

class RecyclerWtAdapter (private val items: ArrayList<WTData>,val context : Context) : RecyclerView.Adapter<RecyclerWtAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerWtAdapter.ViewHolder, position: Int) {

        val item = items[position]

        val listener = View.OnClickListener {

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

           Glide.with(itemView.context)
               .load(item.img)
               .into(binding.imgUserIcon)


           binding.webTitle.text = item.title
           //작가 제목

           binding.upText.text = item.up

           itemView.setOnClickListener {

              val wtIntent = Intent(context,RegisterActivity::class.java)
              wtIntent.putExtra("title",item.title)//웹툰 제목
              wtIntent.putExtra("img",item.img) //웹툰 사진
              wtIntent.putExtra("upState",item.up)//웹툰 업데이트 상태

              wtIntent.run {
                  context.startActivity(this)
              } //액티비티로 넘어감


           }



       }

   }


}
