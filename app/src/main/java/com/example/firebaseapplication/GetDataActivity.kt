package com.example.firebaseapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseapplication.databinding.ActivityGetDataBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class GetDataActivity : AppCompatActivity() {
    private  val binding by lazy {
        ActivityGetDataBinding.inflate(layoutInflater)
    }
    companion object{
        fun launch(activity: Activity) {
            activity.startActivity(Intent(activity, GetDataActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
         fetchUsers()
    }
    private fun fetchUsers(){
        val tempList= mutableListOf<UserInfo>()
        Firebase.database.getReference("REPO")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (item in snapshot.children) {

                        var userInfo = item.getValue(UserInfo::class.java)
                        if (userInfo != null)
                            tempList.add(userInfo)
                    }
                    val adapter=UserInfoAdapter(this@GetDataActivity,tempList)
                    binding.recyclerView.adapter=adapter
                    Log.i("MyDataList", "onDataChange:${tempList.size}")
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@GetDataActivity,error.message,Toast.LENGTH_SHORT).show()
                }

            })


    }
}