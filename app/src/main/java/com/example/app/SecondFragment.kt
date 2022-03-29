package com.example.app

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.net.Uri
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.util.*
import android.app.DatePickerDialog
import android.widget.DatePicker
import java.text.SimpleDateFormat
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null

    var button_date: Button? = null
    var textview_date: TextView? = null
    var cal = Calendar.getInstance()

    var button_da: Button? = null
    var textview_da: TextView? = null
    var ca = Calendar.getInstance()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date!!.text = sdf.format(cal.getTime())
    }

    private fun updateDate() {
        val myFor = "MM/dd/yyyy" // mention the format you need
        val sd = SimpleDateFormat(myFor, Locale.US)
        textview_da!!.text = sd.format(ca.getTime())
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setContentView(R.layout.fragment_second)

        imageView = this.ImageView
        button = this.ButtonLoadPicture
        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }



        // get the references from layout file
        textview_date = this.text_view_date_1
        button_date = this.button_date_1

        textview_da = this.text_view_date_2
        button_da = this.button_date_2


        textview_date!!.text = "--/--/----"
        textview_da!!.text = "--/--/----"



        // create an OnDateSetListener
        val dateSetList = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                ca.set(Calendar.YEAR, year)
                ca.set(Calendar.MONTH, monthOfYear)
                ca.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        button_da!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(requireContext(),
                    dateSetList,
                    // set DatePickerDialog to point to today's date when it loads up
                    ca.get(Calendar.YEAR),
                    ca.get(Calendar.MONTH),
                    ca.get(Calendar.DAY_OF_MONTH)).show()
            }

        })



        view.findViewById<Button>(R.id.button_submit).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun setContentView(fragmentSecond: Int) {

    }
}