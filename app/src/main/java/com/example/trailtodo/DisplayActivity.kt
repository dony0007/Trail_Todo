package com.example.trailtodo

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trailtodo.Adapter.NotesAdapter
import com.example.trailtodo.Models.NoteItemModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


class DisplayActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val arrayList = ArrayList<NoteItemModel>()

        var recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        auth = Firebase.auth
        var currentUser = auth.currentUser?.uid.toString()

        val db = Firebase.firestore

        db.collection("UserData").document(currentUser.toString()).collection("Notes")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    arrayList.add(document.toObject(NoteItemModel::class.java))
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    val adapter = NotesAdapter(arrayList)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }
}