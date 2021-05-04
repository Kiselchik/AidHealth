package diplom.dev.aidhealth.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.*
import diplom.dev.aidhealth.db.model.Course
import diplom.dev.aidhealth.db.model.CourseHistory
import diplom.dev.aidhealth.db.model.Doctor
import diplom.dev.aidhealth.db.model.StatusCourse
import java.text.SimpleDateFormat
import java.util.*

class AddCourseActivity : AppCompatActivity() {

    private lateinit var pillsSpinner: Spinner
    private lateinit var proceduresSpinner: Spinner
    private lateinit var doctorsSpinner: Spinner
    private lateinit var foodsSpinner: Spinner
    private lateinit var addCourseButton: Button
    private lateinit var courseNameEdTxt: EditText
    private lateinit var timeChSmptmChBox: CheckBox
    private lateinit var timeHealthChBox: CheckBox
    private lateinit var courseNtfChBox: CheckBox
    private lateinit var descrEdTxt: EditText

    var chooseProcedure: Int = 0
    var choosePill: Int = 0
    var chooseDoctor: Int =0
    var chooseFood: String = ""
    var chooseTimeChSmptmChBox = "0"
    var chooseTimeHealthChBox = "0"
    var chooseCourseNtfChBox = "0"
    var chooseDiagnosis = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        initialize()
    //    checkRow()
        checkBox()
        spinnerPills()
        spinnerDoctors()
        spinnerFoods()
        spinnerProcedures()
        spinnerDiagnosis()
        setListener()
    }

    fun initialize(){
        pillsSpinner = findViewById(R.id.pillsSpinner)
        proceduresSpinner = findViewById(R.id.procedursSpinner)
        doctorsSpinner = findViewById(R.id.doctorsSpinner)
        foodsSpinner = findViewById(R.id.foodsSpinner)
        addCourseButton = findViewById(R.id.addCourseButton)
        courseNameEdTxt = findViewById(R.id.courseNameEdTxt)
        timeChSmptmChBox = findViewById(R.id.timeChSmptmChBox)
        timeHealthChBox = findViewById(R.id.timeHealthChBox)
        courseNtfChBox = findViewById(R.id.courseNtfChBox)
        descrEdTxt = findViewById(R.id.descrEdTxt)


    }


    fun checkRow(){
        var courseName =courseNameEdTxt.text.toString()

        if(courseName == ""){
            addCourseButton.isEnabled = false
            addCourseButton.isClickable = false
        }
        else{
            addCourseButton.isEnabled = true
            addCourseButton.isClickable = true
        }
    }

    fun setListener(){

        addCourseButton.setOnClickListener(){
            val title = courseNameEdTxt.text.toString()
            if(title!=""){
            val shPref = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val savedEmail = shPref.getString("EMAIL_KEY", "").toString()
            var db = DbCourseHandler(context = this)
                var dbStatusCourse = DbStatusCourseHandler(context = this)
                var dbCourseHistory = DbCourseHistoryHandler(context = this)
                var statusCourse = dbStatusCourse.readStatusCourse()
                if(statusCourse.size==0){
                    var stCourse = StatusCourse("Архивный")
                   dbStatusCourse.insertStatusCourse(stCourse)
                    stCourse = StatusCourse("Текущий")
                    dbStatusCourse.insertStatusCourse(stCourse)
                    stCourse = StatusCourse("Завершенный")
                    dbStatusCourse.insertStatusCourse(stCourse)
                    stCourse = StatusCourse("Прерванный")
                    dbStatusCourse.insertStatusCourse(stCourse)

                }
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())
            val descr = descrEdTxt.text.toString()


                //записываем курс
            var course = Course( savedEmail, chooseProcedure, choosePill, chooseDoctor, chooseFood, title,
                chooseTimeChSmptmChBox, chooseTimeHealthChBox, chooseCourseNtfChBox, currentDate, descr, chooseDiagnosis  )
            db.insertCourse(course)
                var selectMaxCourseID = db.selectMaxId()
                var selectIDStatus= dbStatusCourse.setStatusArchive()
                var stCourse: Int = 0
                var courseID: Int = 0
                for(i in 0..selectMaxCourseID.size-1){
                    courseID = selectMaxCourseID.get(i).id
                }
                for(i in 0..selectIDStatus.size-1){
                    stCourse = selectIDStatus.get(i).id
                }
                var courseHistory = CourseHistory(courseID, stCourse, currentDate )
                dbCourseHistory.insertCourseHistory(courseHistory)

            val intent = Intent(this@AddCourseActivity, TuningCourseActivity::class.java)
            startActivity(intent)
        }else{
                Toast.makeText(this, "Введите название курса", Toast.LENGTH_SHORT)
            }
        }
        }
    fun checkBox(){
        timeChSmptmChBox.setOnCheckedChangeListener(){buttonView, isChecked ->
        if(isChecked){
            chooseTimeChSmptmChBox = "1"
        }

        }
        timeHealthChBox.setOnCheckedChangeListener(){buttonView, isChecked ->
            if(isChecked){
                chooseTimeHealthChBox = "1"
            }

        }
        courseNtfChBox.setOnCheckedChangeListener(){buttonView, isChecked ->
            if(isChecked){
                chooseCourseNtfChBox = "1"
            }

        }
    }





    fun spinnerPills() {

        var db = DbPillsHandler(context = this)
        var data = db.readPill()
        var dataSpinner = arrayListOf<String>()
        var dataSpinnerID = arrayListOf<Int>()

        for (i in 0..data.size-1) {
            dataSpinner.add(data.get(i).title)
            dataSpinnerID.add(data.get(i).id)
        }

        pillsSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataSpinner)
        pillsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                choosePill = dataSpinnerID.get(position)
                /*for(i in 0..data.size-1){
                    if(data[i].equals("${dataSpinner.get(position)}")){
                        choosePill=i
                    }
                }*/
             //   Toast.makeText(applicationContext, "${dataSpinner.get(position)}", Toast.LENGTH_SHORT).show()

              //  measurement = units.get(position)
            }
        }

    }
    fun spinnerProcedures() {

        var db = DbProcedureHandler(context = this)
        var data = db.readProcedure()
        var dataSpinner = arrayListOf<String>()
        for (i in 0..data.size-1) {
            dataSpinner.add(data.get(i).title)
        }

        proceduresSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataSpinner)
        proceduresSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                for(i in 0..data.size-1){
                    if(data[i].equals("${dataSpinner.get(position)}")){
                        chooseProcedure=i
                    }
                }


              //  Toast.makeText(applicationContext, "${dataSpinner.get(position)}", Toast.LENGTH_SHORT).show()

                //  measurement = units.get(position)
            }
        }

    }
    fun spinnerDoctors() {

        var db = DbHandler(context = this)
        var data = db.readDoctor()
        var dataSpinner = arrayListOf<String>()
        var dataSpinnerID = arrayListOf<Int>()
        for (i in 0..data.size-1) {
            dataSpinner.add(data.get(i).firstName +" "+data.get(i).lastName+" ("+data.get(i).position+")")
            dataSpinnerID.add(data.get(i).id)

        }

        doctorsSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataSpinner)
        doctorsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                chooseDoctor = dataSpinnerID.get(position)

                /*   for(i in 0..data.size-1){
                       if(data[i].equals("${dataSpinner.get(position)}")){
                           chooseDoctor=i
                       }
                   }*/
              //  Toast.makeText(applicationContext, "${dataSpinner.get(position)}", Toast.LENGTH_SHORT).show()

                //  measurement = units.get(position)
            }
        }

    }
    fun spinnerFoods() {

      //  var db = DbHandler(context = this)
        var dataSpinner = arrayOf<String>("до еды", "после еды", "во время еды", "нет зависимости от еды")

        foodsSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataSpinner)
        foodsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chooseFood = dataSpinner.get(position)
             //   Toast.makeText(applicationContext, "${dataSpinner.get(position)}", Toast.LENGTH_SHORT).show()

                //  measurement = units.get(position)
            }
        }

    }


    fun spinnerDiagnosis() {

        var db = DbDiagnosisHandler(context = this)
        var data = db.readDiagnosis()
        var dataSpinner = arrayListOf<String>()
        var dataSpinnerID = arrayListOf<Int>()

        for (i in 0..data.size-1) {
            dataSpinner.add(data.get(i).diagnosisTitle)
            dataSpinnerID.add(data.get(i).id)
        }

        pillsSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataSpinner)
        pillsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                chooseDiagnosis = dataSpinnerID.get(position)
                /*for(i in 0..data.size-1){
                    if(data[i].equals("${dataSpinner.get(position)}")){
                        choosePill=i
                    }
                }*/
                //   Toast.makeText(applicationContext, "${dataSpinner.get(position)}", Toast.LENGTH_SHORT).show()

                //  measurement = units.get(position)
            }
        }

    }
}