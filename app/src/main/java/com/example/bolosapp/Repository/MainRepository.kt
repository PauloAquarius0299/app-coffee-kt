package com.example.bolosapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bolosapp.Model.Items.Model
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase=FirebaseDatabase.getInstance()

    fun loadFilted(id:Int):LiveData<MutableList<Model>>{
        val ListData=MutableLiveData<MutableList<Model>>()
        val ref=firebaseDatabase.getReference("Items")
        val query:Query=ref.orderByChild("categoryId").equalTo(id.toString())

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<Model>()
                for(childSnashot in snapshot.children){
                    val item=childSnashot.getValue(Model::class.java)
                    item?.let {lists.add(it)}
                }
                ListData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return ListData
    }
}