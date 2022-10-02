package com.example.monee.report

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.monee.databinding.FragmentSummaryBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.itextpdf.text.Chunk
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

data class Category2(
    val id: Int = 0,
    val date: String = "",
    val type: String = "",
    val category: String = "",
    val amount: Double = 0.00
)

class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding?= null
    private val binding get() = _binding!!

    private var STORAGE_CODE = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_summary, container, false)

        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readIncomeData()
        readExpensesData()

        binding.fabPrint.setOnClickListener{

           if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {

               if (checkPermissions()) {
                   val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                   requestPermissions(permission, STORAGE_CODE)
               } else {
                   generatePDF()
               }
           }else{
               generatePDF()
           }
        }
    }

    fun checkPermissions(): Boolean {

         var writeStoragePermission = ContextCompat.checkSelfPermission(
             requireActivity().applicationContext,
            WRITE_EXTERNAL_STORAGE
        )
        return writeStoragePermission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            STORAGE_CODE -> {
                if(grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    generatePDF()
                }else{
                    Toast.makeText(context,"Permission denied.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun generatePDF() {
        val mDoc = Document()
        val mFileName = SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault())
            .format(System.currentTimeMillis())

        val mFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + mFileName + ".pdf"

        try{

            PdfWriter.getInstance(mDoc,FileOutputStream(mFilePath))
            mDoc.open()
            mDoc.add(Chunk(""))

            val data = "MONEE Overall History Record \n" + readReport()
            
            //mDoc.newPage()
            mDoc.addAuthor("MONEE")

            mDoc.add(Paragraph(data))
            mDoc.close()
            Toast.makeText(context,"$mFileName.pdf is created to \n$mFilePath",Toast.LENGTH_SHORT).show()

        }catch (e:java.lang.Exception){
            Toast.makeText(context,""+e.toString(),Toast.LENGTH_SHORT).show()
        }

    }

    private fun readIncomeData(){

        Firebase.firestore
            .collection("categories")
            .whereEqualTo("type","Income")
            .get()
            .addOnSuccessListener { snap->

                val list = snap.toObjects<Category2>()
                val totalIncome: Double = list.sumOf { it.amount }

                binding.textViewTotalIncome.text = "RM" + totalIncome.toString()

            }.addOnFailureListener {
                Toast.makeText(context,"Failed to get data.",Toast.LENGTH_SHORT).show()
            }
    }

    private fun readExpensesData(){

        Firebase.firestore
            .collection("categories")
            .whereEqualTo("type","Outcome")
            .get()
            .addOnSuccessListener { snap->

                val list = snap.toObjects<Category2>()
                val totalOutcome: Double = list.sumOf { it.amount }

                binding.textViewTotalExpenses.text = "RM" + totalOutcome.toString()

            }.addOnFailureListener {
                Toast.makeText(context,"Failed to get data.",Toast.LENGTH_SHORT).show()
            }
    }

    private fun readReport(): String{
        var out = ""
        Firebase.firestore
            .collection("categories")
            .get()
            .addOnSuccessListener { snap->

                val reportList = snap.toObjects<Category2>()
                reportList.forEach{
                    var id = it.id.toString()
                    var date = it.id.toString()
                    var type = it.type
                    var cat = it.category
                    var amt = it.amount.toString()

                    out += "ID = $id | DATE = $date | TYPE = $type | CATEGORY = $cat | AMOUNT = $amt \n"

                    //out += "ID = ${it.id} | DATE = ${it.date} | TYPE = ${it.type} | CATEGORY = ${it.category} | AMOUNT = ${it.amount} \n"
                }
            }.addOnFailureListener {
                Toast.makeText(context,"Failed to get data.",Toast.LENGTH_SHORT).show()
            }

        return out
    }
}

