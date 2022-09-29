package org.techtown.nw_alarmer.localDB

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.nw_alarmer.databinding.WtViewBinding

class MyWtListRecycler (listener : OnItemClick): RecyclerView.Adapter<MyWtListRecycler.ViewHolder>(){

    private val items = ArrayList<MyWtList>()
    private val mCallback = listener

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyWtListRecycler.ViewHolder, position: Int) {

        holder.bind(items[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = WtViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)

    }

    fun setList(list : List<MyWtList>){
        items.clear()
        items.addAll(list)
    }

    //  각 항목에 필요한 기능을 구현
    inner class ViewHolder(private val binding : WtViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : MyWtList?) {

            Glide.with(itemView.context)
                .load(item?.wtImg)
                .into(binding.imgUserIcon)

            if (item != null) {
                binding.webTitle.text = item.wtTitle
            }
            //작가 제목

            if (item != null) {
                binding.upText.text = item.upImg
            }
            //업 이미지

            itemView.setOnClickListener {

            }//클릭시 이벤트



        }

    }


}