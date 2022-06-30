package com.example.homework6

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var userInfo = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addUser()
        removeUser()
        updateUser()
        userInfo = intent.getStringArrayListExtra("UpdatedUsers")?: mutableListOf()
        var onlaineUser = intent.extras?.getString("OnlineUser")?: "0"
        var deleteUser = intent.extras?.getString("DeleteUser")?: "0"
        binding.tvOnlineUser.text = onlaineUser
        binding.tvDeleteUser.text = deleteUser
        Log.d("saba", "$userInfo")
    }


    private fun addUser(){
        binding.btnAddUser.setOnClickListener {
            val firstName = binding.edFirstName.text.toString().trim()
            val lastName = binding.edLastName.text.toString().trim()
            val age = binding.edAge.text.toString().trim()
            val email = binding.edEmail.text.toString().trim()
            val onlineUser = binding.tvOnlineUser.text
            val user = "$firstName, $lastName, $age, $email"
            if (firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || email.isEmpty()){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(userInfo.isNotEmpty()) {
                var response = ""
                for (record in userInfo){
                    if (email in record){
                        response = "1"
                        binding.tvSuccessError.text = "User already exists"
                        binding.tvSuccessError.setTextColor(Color.parseColor("#6E0000"))
                        binding.tvSuccessError.alpha = 1F
                        binding.tvSuccessError.postDelayed({
                            binding.tvSuccessError.alpha = 0F
                        }, 1000)
                        return@setOnClickListener
                    }
                }
                if (response != "1"){
                    val onlineUserIncrement = onlineUser.toString().toInt()+1
                    binding.tvOnlineUser.text = onlineUserIncrement.toString()
                    userInfo.add(user)
                    binding.tvSuccessError.text = "User added successfully"
                    binding.tvSuccessError.setTextColor(Color.parseColor("#055302"))
                    binding.tvSuccessError.alpha = 1F
                    binding.tvSuccessError.postDelayed({
                        binding.tvSuccessError.alpha = 0F
                    }, 1000)
                    binding.edFirstName.text!!.clear()
                    binding.edLastName.text!!.clear()
                    binding.edAge.text!!.clear()
                    binding.edEmail.text!!.clear()
                    return@setOnClickListener
                }
            }else{
                val onlineUserIncrement = onlineUser.toString().toInt()+1
                binding.tvOnlineUser.text = onlineUserIncrement.toString()
                userInfo.add(user)
                binding.tvSuccessError.text = "User added successfully"
                binding.tvSuccessError.setTextColor(Color.parseColor("#055302"))
                binding.tvSuccessError.alpha = 1F
                binding.tvSuccessError.postDelayed({
                    binding.tvSuccessError.alpha = 0F
                }, 1000)
                binding.edFirstName.text!!.clear()
                binding.edLastName.text!!.clear()
                binding.edAge.text!!.clear()
                binding.edEmail.text!!.clear()
                return@setOnClickListener
            }
        }
    }

    private fun removeUser(){
        binding.btnRemoveUser.setOnClickListener {
            val firstName = binding.edFirstName.text.toString().trim()
            val lastName = binding.edLastName.text.toString().trim()
            val age = binding.edAge.text.toString().trim()
            val email = binding.edEmail.text.toString().trim()
            val user = "$firstName, $lastName, $age, $email"
            val onlineUser = binding.tvOnlineUser.text
            val deleteUser = binding.tvDeleteUser.text
            if (user in userInfo){
                userInfo.remove(user)
                binding.tvOnlineUser.text = (onlineUser.toString().toInt()-1).toString()
                binding.tvDeleteUser.text = (deleteUser.toString().toInt()+1).toString()
                binding.tvSuccessError.text = "User deleted successfully"
                binding.tvSuccessError.setTextColor(Color.parseColor("#055302"))
                binding.tvSuccessError.alpha = 1F
                binding.tvSuccessError.postDelayed({
                    binding.tvSuccessError.alpha = 0F
                }, 1000)
                binding.edFirstName.text!!.clear()
                binding.edLastName.text!!.clear()
                binding.edAge.text!!.clear()
                binding.edEmail.text!!.clear()
                return@setOnClickListener
            }else{
                binding.tvSuccessError.text = "User does not exits"
                binding.tvSuccessError.setTextColor(Color.parseColor("#6E0000"))
                binding.tvSuccessError.alpha = 1F
                binding.tvSuccessError.postDelayed({
                    binding.tvSuccessError.alpha = 0F
                }, 1000)
                return@setOnClickListener
            }

        }
    }

    private fun updateUser(){
        binding.btnUpdateUser.setOnClickListener {
            val firstName = binding.edFirstName.text.toString().trim()
            val lastName = binding.edLastName.text.toString().trim()
            val age = binding.edAge.text.toString().trim()
            val email = binding.edEmail.text.toString().trim()
            val onlineUser = binding.tvOnlineUser.text
            val deleteUser = binding.tvDeleteUser.text
            val user = "$firstName, $lastName, $age, $email"
            if (user in userInfo){
                val intent = Intent(this, UpdateActivity::class.java)
                intent.putExtra("FIRSTNAME", firstName)
                intent.putExtra("LASTNAME", lastName)
                intent.putExtra("AGE", age)
                intent.putExtra("EMAIL", email)
                intent.putExtra("OnlineUser",onlineUser)
                intent.putExtra("DeleteUser",deleteUser)

                val mutableList: MutableList<String> = mutableListOf()
                mutableList.add(userInfo.toString())
                intent.putExtra("USER", ArrayList(mutableList))
                startActivity(intent)
            }else{
                binding.tvSuccessError.text = "User does not exits"
                binding.tvSuccessError.setTextColor(Color.parseColor("#6E0000"))
                binding.tvSuccessError.alpha = 1F
                binding.tvSuccessError.postDelayed({
                    binding.tvSuccessError.alpha = 0F
                }, 1000)
                return@setOnClickListener
            }
        }
    }

}