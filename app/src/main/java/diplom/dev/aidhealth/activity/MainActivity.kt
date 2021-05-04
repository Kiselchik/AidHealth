package diplom.dev.aidhealth.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.*

class MainActivity : AppCompatActivity() {
    private lateinit var userName: TextView
    private lateinit var pills_button: ImageButton
    private lateinit var doctor_button: ImageButton
    private lateinit var dynamic_button: ImageButton
    private lateinit var course_button: ImageButton
    private lateinit var symptom_button: ImageButton
    private lateinit var toDiagnosisBtn: Button
    private lateinit var clearCourseBtn: Button
    private lateinit var clearPillBtn: Button
    private lateinit var clearDoctorBtn: Button
    private lateinit var clearDiagnosisBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        setUserNameTxt()
        setListener()
        val shPref = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = shPref.edit()
        editor.apply{

        }.apply()

        //TODO: добавить кнопку выхода - удлить sharedpreferences
    }

    private fun initializeView(){
        userName = findViewById(R.id.mainFirstLastNameTxt)
        pills_button = findViewById(R.id.ib_pills)
        doctor_button = findViewById(R.id.ib_doctors)
        dynamic_button = findViewById(R.id.ib_dynamic)
        course_button = findViewById(R.id.ib_course)
        symptom_button = findViewById(R.id.ib_symptom)
        toDiagnosisBtn = findViewById(R.id.toDiagnosysBtn)
        clearCourseBtn = findViewById(R.id.clearCourseBtn)
        clearPillBtn = findViewById(R.id.clearPillBtn)
        clearDoctorBtn = findViewById(R.id.clearDoctorBtn)
        clearDiagnosisBtn = findViewById(R.id.clearDiagnosisBtn)


    }

    fun setUserNameTxt(){
        val shPref = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedFirstName = shPref.getString("FIRSTNAME_KEY", "")
        val savedLastName = shPref.getString("LASTNAME_KEY", null)
        userName.text = savedFirstName+" "+savedLastName

    }


    private fun setListener(){
        pills_button.setOnClickListener(){
            val intent = Intent(this@MainActivity, PillsActivity::class.java)
            startActivity(intent)
        }

        doctor_button.setOnClickListener(){
            val intent = Intent(this@MainActivity, DoctorActivity::class.java)
            startActivity(intent)
        }
        course_button.setOnClickListener(){
            val intent = Intent(this@MainActivity, CourseActivity::class.java)
            startActivity(intent)
        }

        toDiagnosisBtn.setOnClickListener(){
            val intent = Intent(this@MainActivity, DiagnosisActivity::class.java)
            startActivity(intent)
        }
        clearCourseBtn.setOnClickListener(){
            var dbCourse = DbCourseHandler(context = this)
            var dbCourseHistory = DbCourseHistoryHandler(context = this)
            dbCourse.clearTableCourse()
            dbCourseHistory.clearTableCourseHistory()
        }

        clearPillBtn.setOnClickListener(){
            var dbPill = DbPillsHandler(context = this)
            dbPill.clearTablePill()
        }
        clearDoctorBtn.setOnClickListener(){
            var dbDoctor = DbHandler(context = this)
            dbDoctor.clearTableDoctor()
        }

        symptom_button.setOnClickListener(){
            val intent = Intent(this@MainActivity, SymptomActivity::class.java)
            startActivity(intent)
        }
        clearDiagnosisBtn.setOnClickListener(){
            var dbDiagnosis = DbDiagnosisHandler(context = this)
            var dbDiagnosisSymptom = DbDiagnosisSymptomHandler(context = this)

            dbDiagnosis.clearTableDiagnosis()
            dbDiagnosisSymptom.clearTableDiagnosisSymptom()
        }

    }

}