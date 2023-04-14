package com.example.morningfirebasedbapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var edtName:EditText
    lateinit var edtEmail:EditText
    lateinit var edtIdNumber:EditText
    lateinit var btnSave: Button
    lateinit var btnView:Button
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtName=findViewById(R.id.edtName)
        edtEmail=findViewById(R.id.edtEmail)
        edtIdNumber=findViewById(R.id.edtidNumber)
        btnSave=findViewById(R.id.mbtnSave)
        btnView=findViewById(R.id.mbtnView)
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Saving")
        progressDialog.setMessage("Please wait")
        btnSave.setOnClickListener {
            var name = edtName.text.toString().trim()
            var email = edtName.text.toString().trim()
            var idNumber = edtName.text.toString().trim()
            var id = System.currentTimeMillis().toString()
            if (name.isEmpty()){
                edtName.setError("Please fill this field")
                edtName.requestFocus()
            }else if (email.isEmpty()){
                edtEmail.setError("Please fill this field")
                edtEmail.requestFocus()
            }else if (email.isEmpty()){
                edtIdNumber.setError("Please fill this field")
                edtIdNumber.requestFocus()
            }else{
                //Proceed to save
                //prepare the user to be saved
                var user = User(name, email,idNumber,id)
                //Create a reference in the firebase database
                var ref =FirebaseDatabase.getInstance().
                        getReference().child("Users/"+id)
                //Show the progress to start saving
                progressDialog.show()
                ref.setValue(user).addOnCompleteListener{
                    //dismiss the progress and see if 5the task is successful
                    task->
                    progressDialog.dismiss()
                    if(task.isSuccessful) {
                        Toast.makeText(
                            this, "User saved successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }else{
                        Toast.makeText(this,"user saving failed",
                            Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
        btnView.setOnClickListener {

        }
    }
}