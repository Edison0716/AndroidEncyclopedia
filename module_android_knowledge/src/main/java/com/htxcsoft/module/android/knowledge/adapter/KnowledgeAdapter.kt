package com.htxcsoft.module.android.knowledge.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.htxcsoft.module.android.knowledge.R

import com.htxcsoft.module.android.knowledge.model.KnowledgeBean
import kotlinx.android.synthetic.main.item_knowledge.view.*

import java.util.ArrayList

/**
 * @author: 巴黎没有摩天轮Li
 * @description:
 * @date: Created in 下午4:01 2018/2/10
 * @modified by:
 */
class KnowledgeAdapter(val knowledgeList: ArrayList<KnowledgeBean>, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<KnowledgeAdapter.KnowledgeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnowledgeViewHolder? {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_knowledge, parent, false)
        var knowledgeViewHolder = KnowledgeViewHolder(view)
        return knowledgeViewHolder
    }

    override fun onBindViewHolder(holder: KnowledgeViewHolder, position: Int) {
        holder.button.text = knowledgeList[position].title
        holder.button.setOnClickListener {
            onItemClickListener.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return knowledgeList.size
    }

    inner class KnowledgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var button: Button = itemView.findViewById(R.id.bt_knowledge)
    }

    interface OnItemClickListener {
        fun onItemClickListener(position:Int)
    }
}
