package com.example.monee.home.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class CategoriesViewModel : ViewModel() {

    private val col = Firebase.firestore.collection("categories")
    private val cate = MutableLiveData<List<Categories>>()

    init{
        col.addSnapshotListener { snap, _ -> cate.value = snap?.toObjects() }
    }


    suspend fun get(id: String): Categories? {
        return col.document(id).get().await().toObject<Categories>()
    }

    fun getAll() = cate

    fun delete(id: String) {
        col.document(id).delete()

    }

    fun deleteAll() {
        //col.get().addOnSuccessListener { snap -> snap.documents.forEach { doc -> delete(doc.id) } }
        //cate.value?.forEach { f -> delete(f.id.toString()) }

        cate.value?.forEach { f -> delete(f.amount.toString()) }

    }

    fun set(f: Categories) {
        col.document(f.id.toString()).set(f)

    }

    //----------------------------------------------------------------------------------------------

    private  suspend fun idExists(id: String): Boolean {
        return col.document(id).get().await().exists()


        return false
    }


    suspend fun validate(f: Categories, insert: Boolean = true): String {
        var validation = ""

        if (insert) {
            validation += if (f.id == 0) "- Id is required.\n"
            else if (idExists(f.id.toString())) "- Id is duplicated.\n"
            else ""

        }
        validation += if (f.category == "") "- Category is required.\n"
        else ""

        validation += if (f.amount == 0.0) "- Amount is required.\n"
        else if (f.amount < 0) "- Invalid.\n"
        else ""

        validation += if (f.date == "") "- Date is required.\n"
        else ""


        return validation
    }


}