package diplom.dev.aidhealth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import diplom.dev.aidhealth.DataRecyclerCourse
import diplom.dev.aidhealth.PointCourse
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.*

class CourseHistoryActivity : AppCompatActivity() {
    private lateinit var textViewNameCourse: TextView
    private lateinit var textViewStatusCourse: TextView
    private lateinit var textViewPillProcedureCourse: TextView
    private lateinit var textViewFoodCourse: TextView
    private lateinit var textViewDoctorCourse: TextView
    private lateinit var textViewDateCourse: TextView
    private lateinit var chBoxNotification: CheckBox
    private lateinit var chBoxSymptom: CheckBox
    private lateinit var chBoxHealth: CheckBox
    private lateinit var toSetingCourse: Button
    private lateinit var showPointBtn: Button
    var toActivity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_history)


        PointCourse.courseID = 0
        initialize()
        setText()
        setListener()
    }

    fun initialize(){
        textViewNameCourse = findViewById(R.id.textViewNameCourse)
        textViewStatusCourse = findViewById(R.id.textViewStatusCourse)
        textViewPillProcedureCourse = findViewById(R.id.textViewPillProcedureCourse)
        textViewFoodCourse = findViewById(R.id.textViewFoodCourse)
        textViewDoctorCourse = findViewById(R.id.textViewDoctorCourse)
        textViewDateCourse = findViewById(R.id.textViewDateCourse)
        chBoxNotification = findViewById(R.id.chBoxNotification)
        chBoxSymptom = findViewById(R.id.chBoxSymptom)
        chBoxHealth = findViewById(R.id.chBoxHealth)
        toSetingCourse = findViewById(R.id.toSetingCourse)
        showPointBtn = findViewById(R.id.showPoints)
    }



    fun setText() {

        var chooseCourseId = intent.getIntExtra("chooseCourseId", 0)

        textViewNameCourse.text = "$chooseCourseId"
        DataRecyclerCourse.courseID = chooseCourseId

        //получаем историю курса
        val dbCourseHistory = DbCourseHistoryHandler(context = this)
        var data = dbCourseHistory.getChooseCourse()

        //получаем расшифровку. Вся инфа о курсе
        val dbCourse = DbCourseHandler(context = this)
        var dataCourse = dbCourse.getOneCourse()
        PointCourse.courseID = dataCourse.get(0).id

        //получаем расшифровку статуса
        DataRecyclerCourse.statusIDCourse = data.get(0).statusID
        val dbStatusCourse = DbStatusCourseHandler(context = this)
        var dataStatusCourse = dbStatusCourse.getOneStatus()

        //получаем расшифровку врача
        val dbDoctor = DbHandler(context = this)
        DataRecyclerCourse.doctorID = dataCourse.get(0).doctor
        var dataDoctor = dbDoctor.getOneDoctor()

        //получаем расшифровку лекарства
        val dbPill = DbPillsHandler(context = this)
        DataRecyclerCourse.pillID = dataCourse.get(0).medicament
        var dataPill = dbPill.getOnePill()

        //TODO: получаем расшифровку процедур

        textViewNameCourse.text = "${dataCourse.get(0).title}"
        textViewStatusCourse.text = "${dataStatusCourse.get(0).title}"
       textViewPillProcedureCourse.text = "${dataPill.get(0).title}"
        textViewFoodCourse.text = "${dataCourse.get(0).food}"
     textViewDoctorCourse.text = "${dataDoctor.get(0).firstName} ${dataDoctor.get(0).lastName}"
        textViewDateCourse.text = "${dataCourse.get(0).date}"

        if(dataCourse.get(0).notification == "1")
        { chBoxNotification.toggle()}
        if(dataCourse.get(0).timeCheckSymptom == "1")
        { chBoxSymptom.toggle()}
        if(dataCourse.get(0).timeHealthNotification == "1")
        { chBoxHealth.toggle()}

        var dbCoursePoint= DbCoursePointHandler(context = this)
       if( dbCoursePoint.getOneCourse().size ==0 ){
           toSetingCourse.text = "Настроить уведомления"

       }else{
           toSetingCourse.text = "Показать уведомления"
           toActivity = true
       }

        //выяснить если ли записи в таблиуе для этого курса. Если да - "показать уведомления"

//TODO: если этого курс нет в coursePoint и нажимаете напомнить о курсе, то перевести в SetTimeCoursePoint




        Toast.makeText(this, "${chooseCourseId}", Toast.LENGTH_SHORT)
    }

    private fun setListener(){
        toSetingCourse.setOnClickListener(){
            if(toActivity){
                val intent = Intent(this@CourseHistoryActivity, PointCourseActivity::class.java)
                startActivity(intent)

            }else{
                val intent = Intent(this@CourseHistoryActivity, TuningCourseActivity::class.java)
                startActivity(intent)
            }
          //  startActivity(intent)
        }
        showPointBtn.setOnClickListener(){
            val intent = Intent(this@CourseHistoryActivity, PointCourseActivity::class.java)

            startActivity(intent)
        }
    }
}