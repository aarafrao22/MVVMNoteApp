package com.aarafrao.mvvm.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aarafrao.mvvm.R
import com.aarafrao.mvvm.User

class Adapter(var context: Context, var userList: ArrayList<User>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var age: TextView = itemView.findViewById(R.id.age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        var view: View = layoutInflater.inflate(R.layout.itemuser, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = userList[position]
        holder.age.text = user.age.toString()
        holder.name.text = user.name
    }

    fun setData(userList: ArrayList<User>){
        this.userList=userList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}