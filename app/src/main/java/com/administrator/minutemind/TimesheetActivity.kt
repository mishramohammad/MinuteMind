/***************************************************************************************
 *    Title: <Save Data in Firebase Realtime Database using Kotlin | Realtime Database | Kotlin | Android Studio>
 *    Author: <Foxandroid>
 *    Date Published: <8 April 2021>
 *    Date Retrieved: <01 June 2023>
 *    Code version: <1.0.0>
 *    Availability: <https://www.youtube.com/watch?v=MFcMw9jJA9o&t=557s>
 *
 ***************************************************************************************/

package com.administrator.minutemind

import android.net.Uri
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.administrator.minutemind.databinding.ActivityTimesheetBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TimesheetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimesheetBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimesheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener{
            writeData()
        }

        binding.btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val getUserData = findViewById<Button>(R.id.btn_Set_Goals)
        getUserData.setOnClickListener()
        {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun writeData(){
        val name = binding.etName.text.toString()
        val category = binding.etCategory.text.toString()
        val date = binding.etDate.text.toString()
        val endTime = binding.etEndTimes.text.toString()
        val startTimes = binding.etStartTimes.text.toString()
        val description = binding.etDescription.text.toString()
/*        val image1 = binding.imageUser*/

        if(name.isNotEmpty() && category.isNotEmpty()){
            database = FirebaseDatabase.getInstance().getReference("Timesheet Entries")
            val User = User(/*image1,*/category,description,date,startTimes,endTime)
            database.child(name).setValue(User).addOnSuccessListener {
                binding.etName.text.clear()
                binding.etCategory.text.clear()
                binding.etDescription.text.clear()
                binding.etDate.text.clear()
                binding.etStartTimes.text.clear()
                binding.etEndTimes.text.clear()
                binding.imageUser

                Toast.makeText(this, "Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Failure",Toast.LENGTH_SHORT).show()
            }
        }
    }

}