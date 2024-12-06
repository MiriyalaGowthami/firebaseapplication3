package com.example.firebaseapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseapplication.databinding.ActivitySendDataBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SendDataActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySendDataBinding.inflate(layoutInflater)
    }

    companion object {
        fun launch(activity: Activity) {
            activity.startActivity(Intent(activity, SendDataActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnUpload.setOnClickListener {
            if (TextUtils.isEmpty(binding.txtTitle.text.toString().trim())) {
                binding.txtTitle.error = "Title can't be empty"
                binding.txtTitle.requestFocus()
            } else if (TextUtils.isEmpty(binding.textDesc.text.toString().trim())) {
                binding.textDesc.error = "Description can't be empty"
                binding.textDesc.requestFocus()
            } else {
                val userInfo = UserInfo(
                    title = binding.txtTitle.text.toString().trim(),
                    description = binding.textDesc.text.toString().trim()
                )

                upload(userInfo)
            }
        }
    }

    private fun upload(userInfo: com.example.firebaseapplication.UserInfo) {
        Firebase.database.getReference("REPO")
            .child(System.currentTimeMillis().toString())
            .setValue(userInfo)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    binding.txtTitle.setText("")
                    binding.textDesc.setText("")
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
    }
}
