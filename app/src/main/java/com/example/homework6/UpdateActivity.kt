package com.example.homework6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.homework6.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    var oldFirstName = ""
    var oldLastName = ""
    var oldAge = ""
    var oldEmail  = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUser()
        oldFirstName = intent.extras?.getString("FIRSTNAME")!!
        oldLastName = intent.extras?.getString("LASTNAME")!!
        oldAge = intent.extras?.getString("AGE")!!
        oldEmail = intent.extras?.getString("EMAIL")!!
        binding.edFirstName.setText(oldFirstName)
        binding.edLastName.setText(oldLastName)
        binding.edAge.setText(oldAge)
        binding.edEmail.setText(oldEmail)
    }

    private fun updateUser(){
        binding.btnUpdateUser.setOnClickListener {
            val mutableList = intent.extras?.getStringArrayList("USER")
            val onlaineUser = intent.extras?.getString("OnlineUser")
            val deleteUser = intent.extras?.getString("DeleteUser")
            Log.d("saba", "$mutableList")
            val oldUser = "$oldFirstName, $oldLastName, $oldAge, $oldEmail"
            Log.d("saba", "oldUser:$oldUser")
            if (oldUser in mutableList!!){
                mutableList.remove(oldUser)
                Log.d("saba", "remove:$mutableList")
            }
            Log.d("saba", "$mutableList")
            val firstName = binding.edFirstName.text.toString()
            val lastName = binding.edLastName.text.toString()
            val age = binding.edAge.text.toString()
            val email = binding.edEmail.text.toString()
            val user = "$firstName, $lastName, $age, $email"
            mutableList.add(user)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("UpdatedUsers", ArrayList(mutableList))
            intent.putExtra("DeleteUser", deleteUser)
            intent.putExtra("OnlineUser", onlaineUser)
            startActivity(intent)

        }
    }
}