package diplom.dev.aidhealth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import diplom.dev.aidhealth.Dates
import diplom.dev.aidhealth.Items
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.DbPillsHandler

class SetingCoursePointActivity : AppCompatActivity() {

    private lateinit var measuringSpinner: Spinner
    private lateinit var numInDayEdTxt: EditText
    private lateinit var fromTimeEdTxt: EditText
    private lateinit var toTimeEdTxt: EditText
    private lateinit var dosePillEdTxt: EditText
    private lateinit var saveCoursePointButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seting_course_point)

        initialize()
        spinnerMeasuring()
        setListener()
    }

    fun initialize(){
        measuringSpinner = findViewById(R.id.measuringDoseSpinner)
        numInDayEdTxt = findViewById(R.id.numInDayEdTxt)
        fromTimeEdTxt = findViewById(R.id.fromTimeEdTxt)
        toTimeEdTxt = findViewById(R.id.toTimeEdTxt)
        dosePillEdTxt = findViewById(R.id.dosePillEdTxt)
        saveCoursePointButton = findViewById(R.id.saveCoursePointButton)

    }



    fun spinnerMeasuring() {

       // var db = DbPillsHandler(context = this)
        //var data = db.readPill()
        var dataSpinner = arrayListOf<String>("шт", "мл", "мг", "капель", "впрыскиваний")
       /* for (i in 0..data.size-1) {
            dataSpinner.add(data.get(i).title)
        }*/

        measuringSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataSpinner)
        measuringSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //   Toast.makeText(applicationContext, "${dataSpinner.get(position)}", Toast.LENGTH_SHORT).show()

                //  measurement = units.get(position)
            }
        }

    }



    fun setListener(){
        saveCoursePointButton.setOnClickListener(){
           // var Array = intent.getStringArrayListExtra("DATES")


            val intent = Intent(this, SetTimeCourseActivity::class.java)

                    startActivity(intent)
                }

        }
    }
