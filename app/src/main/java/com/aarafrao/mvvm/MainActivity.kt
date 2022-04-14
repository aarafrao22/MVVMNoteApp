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
import com.aarafrao.mvvm.UserViewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var builder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog
    private lateinit var edName: EditText
    private lateinit var edAge: EditText
    private lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getAllUserData(applicationContext)?.observe(this, Observer {

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

        } else {
            Toast.makeText(this, "Enter Something first", Toast.LENGTH_LONG).show()
            alertDialog.dismiss()
        }
    }
}