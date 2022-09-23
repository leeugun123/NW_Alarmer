package org.techtown.nw_alarmer

import android.content.Context
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

class RecyclerWtAdapter (private val items: ArrayList<WTData>,val context : Context) : RecyclerView.Adapter<RecyclerWtAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    private val myWebToonlist = mutableListOf<MyWtList>()
    var db : ListDatabase? = null//myList DB
    val myAdapter = MyWtListRecycler(myWebToonlist,context)

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

           Glide.with(itemView.context)
               .load(item.img)
               .into(binding.imgUserIcon)


           binding.webTitle.text = item.title
           //작가 제목

           binding.upText.text = item.up

           itemView.setOnClickListener {

               Toast.makeText(context,item.title,Toast.LENGTH_SHORT).show()

               var myWebList = MyWtList(item.title,item.title,item.up)
               db?.wtListDao()?.insertList(myWebList)//DB에추가
              // myWebToonlist.add(myWebList)//리스트 추가

               myAdapter.notifyDataSetChanged()//리사이클러뷰 갱신

           }



       }

   }


}
