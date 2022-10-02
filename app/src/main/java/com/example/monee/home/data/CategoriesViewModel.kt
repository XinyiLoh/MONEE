package com.example.monee.home.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class CategoriesViewModel : ViewModel() {
    // TODO: Initialization
    private val col = Firebase.firestore.collection("categories")
    private val cate = MutableLiveData<List<Categories>>()

    init{
        col.addSnapshotListener { snap, _ -> cate.value = snap?.toObjects() }
    }


    fun get(id: String): Categories? {
        // TODO
        return null
    }

    fun getAll() = cate

    fun delete(id: String) {
        col.document(id).delete()

    }

    fun deleteAll() {
        //col.get().addOnSuccessListener { snap -> snap.documents.forEach { doc -> delete(doc.id) } }
        cate.value?.forEach { f -> delete(f.id.toString()) }

    }

    fun set(f: Categories) {
        col.document(f.id.toString()).set(f)

    }

    //----------------------------------------------------------------------------------------------

    /*fun idExists(id: String): Boolean {
        // TODO: Duplicated id?
        return false
    }

    fun nameExists(name: String): Boolean {
        // TODO: Duplicated name?
        return false
    }

    fun validate(f: Categories, insert: Boolean = true): String {
        val regexId = Regex("""^[0-9A-Z]{4}$""")
        var e = ""

        if (insert) {
            e += if (f.id == "") "- Id is required.\n"
            else if (!f.id.matches(regexId)) "- Id format is invalid.\n"
            else if (idExists(f.id)) "- Id is duplicated.\n"
            else ""
        }

        e += if (f.name == "") "- Name is required.\n"
        else if (f.name.length < 3) "- Name is too short.\n"
        else if (nameExists(f.name)) "- Name is duplicated.\n"
        else ""

        e += if (f.age == 0) "- Age is required.\n"
        else if (f.age < 18) "- Underage.\n"
        else ""

        /*
        e += if (f.photo.toBytes().isEmpty()) "- Photo is required.\n"
        else ""
        */

        return e
    }*/
}