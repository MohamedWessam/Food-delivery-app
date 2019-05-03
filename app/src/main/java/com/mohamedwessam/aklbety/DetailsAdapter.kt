package com.mohamedwessam.aklbety

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class DetailsAdapter(var myArrayList: ArrayList<DetailsRecyclerView>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.details_list, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return myArrayList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        var infList = myArrayList[p1]
        p0.detailsName.text = infList.mDetailsName
        p0.detailsImg.setImageResource(infList.mDetailsImg)
        p0.detailsPrice.text = infList.mDetailsPrice
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        val detailsName = itemView.findViewById(R.id.txt_details) as TextView
        val detailsPrice = itemView.findViewById(R.id.txt_details_price) as TextView
        val detailsImg = itemView.findViewById(R.id.img_details) as CircleImageView

        init {
            itemView.setOnClickListener(this)
        }
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }
}
