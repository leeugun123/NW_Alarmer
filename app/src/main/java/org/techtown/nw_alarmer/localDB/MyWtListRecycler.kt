package org.techtown.nw_alarmer.localDB

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.nw_alarmer.databinding.WtViewBinding

class MyWtListRecycler (private val items : MutableList<MyWtList>,val context: Context): RecyclerView.Adapter<MyWtListRecycler.ViewHolder>(){

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyWtListRecycler.ViewHolder, position: Int) {

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

        fun bind(listener: View.OnClickListener, item: MyWtList) {

            Glide.with(itemView.context)
                .load(item.wtImg)
                .into(binding.imgUserIcon)

            binding.webTitle.text = item.wtTitle
            //작가 제목

            binding.upText.text = item.upImg
            //업 이미지

            itemView.setOnClickListener {

            }



        }

    }


}