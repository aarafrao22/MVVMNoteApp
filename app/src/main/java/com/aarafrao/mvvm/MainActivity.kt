package com.aarafrao.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aarafrao.mvvm.Adapter.Adapter
import com.aarafrao.mvvm.UserViewModel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var builder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog
    private lateinit var edName: EditText
    private lateinit var edAge: EditText
    private lateinit var btnSave: Button
    private lateinit var userAdapter: Adapter
    private lateinit var rvMain: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton = findViewById(R.id.floatingActionButton)
        rvMain = findViewById(R.id.rvMain)


        userAdapter = Adapter(applicationContext, ArrayList<User>())
        rvMain.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getAllUserData(applicationContext)?.observe(this, Observer {
            userAdapter.setData(it as ArrayList<User>)
        })

        floatingActionButton.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        builder = AlertDialog.Builder(this)
        var itemView: View = LayoutInflater.from(applicationContext).inflate(R.layout.dialog, null)
        alertDialog = builder.create()
        alertDialog.setView(itemView)
        edAge = itemView.findViewById(R.id.edAge)
        edName = itemView.findViewById(R.id.edName)
        btnSave = itemView.findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            storeDataIntoRoom()
        }
        alertDialog.show()
    }

    private fun storeDataIntoRoom() {
        val getName = edName.text.toString().trim()
        val getAge = edAge.text.toString().trim()

        if (!TextUtils.isEmpty(getName) && !TextUtils.isEmpty(getAge)) {
            userViewModel.insert(this,User(getName,getAge as Int))
            alertDialog.dismiss()
        } else {
            Toast.makeText(this, "Enter Something first", Toast.LENGTH_LONG).show()
            alertDialog.dismiss()
        }
    }
}