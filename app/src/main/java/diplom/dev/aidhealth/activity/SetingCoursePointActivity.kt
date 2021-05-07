package diplom.dev.aidhealth.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import diplom.dev.aidhealth.*
import diplom.dev.aidhealth.db.handler.DbCoursePointHandler
import diplom.dev.aidhealth.db.handler.DbPillsHandler
import diplom.dev.aidhealth.db.handler.DbStatusPointHandler
import diplom.dev.aidhealth.db.model.CoursePoint
import diplom.dev.aidhealth.db.model.StatusPoint
import kotlinx.android.synthetic.main.activity_add_pills.*
import org.w3c.dom.Text

class SetingCoursePointActivity : AppCompatActivity() {

    private lateinit var measuringSpinner: Spinner
    private lateinit var dosePillEdTxt: EditText
    private lateinit var numInDayEdTxt: EditText
    private lateinit var setTimePicker: TimePicker
    private lateinit var timeFromText: TextView
    private lateinit var timeToText:TextView
    private lateinit var saveCoursePointButton: Button
    private lateinit var testInterval : TextView
    var measurement = ""
    var hourTo = 0
    var hourFrom = 0
    var minuteTo = 0
    var minuteFrom = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seting_course_point)

        initialize()
        spinnerMeasuring()
        setListener()
    }

    fun initialize(){
        measuringSpinner = findViewById(R.id.measuringDoseSpinner)
        dosePillEdTxt = findViewById(R.id.dosePillEdTxt)
        numInDayEdTxt = findViewById(R.id.numInDayEdTxt)
        setTimePicker = findViewById(R.id.setTimePicker)
        timeFromText = findViewById(R.id.timeFromText)
        timeToText = findViewById(R.id.timeToText)
        saveCoursePointButton = findViewById(R.id.saveCoursePointButton)
        testInterval = findViewById(R.id.testInterval)
        testInterval.text = DataRecyclerCourse.courseID.toString()
        setTimePicker.setIs24HourView(true)

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

                  measurement = dataSpinner.get(position)
            }
        }

    }



   private fun setListener(){
        timeFromText.setOnClickListener(){

            if (Build.VERSION.SDK_INT >= 23 ){
            hourFrom = setTimePicker.hour
             minuteFrom = setTimePicker.minute
        }else{
                hourFrom   = setTimePicker.currentHour
                minuteFrom = setTimePicker.currentMinute
            }

            timeFromText.text = "C $hourFrom : ${minuteFrom}"
        }
       timeToText.setOnClickListener(){

           if (Build.VERSION.SDK_INT >= 23 ){
               hourTo = setTimePicker.hour
               minuteTo = setTimePicker.minute
           }else{
               hourTo = setTimePicker.currentHour
               minuteTo = setTimePicker.currentMinute
           }

           timeToText.text = "До $hourTo : ${minuteTo}"
       }




        saveCoursePointButton.setOnClickListener(){
           // var Array = intent.getStringArrayListExtra("DATES")
            Items.numInDay = numInDayEdTxt.text.toString().toInt()
          //Items.timeFrom = fromTimeEdTxt.text.toString()
          //  Items.timeTo = toTimeEdTxt.text.toString()
            Items.dose = dosePillEdTxt.text.toString()
            Items.measuring = measurement

            var allMinutesTo = ((hourTo-1)*60 + minuteTo).toFloat()
            var allMinutesFrom = ((hourFrom+1)*60 + minuteFrom).toFloat()
            var difference = allMinutesTo-allMinutesFrom //общее время для принятия
            var interval = difference/(Items.numInDay)  //Интервал между приемами
           // var firstPill = allMinutesFrom

            var doseTime = arrayListOf<String>()
            for(i in 0..Items.numInDay-1){
                    var point = ((allMinutesFrom + interval*i) / 60)
                    var pointHour = point.toInt() //целая часть
                    var pointMinute =  ((point - pointHour) * 60).toInt() //дробная часть переведенная в минуты
                    doseTime.add("$pointHour:${pointMinute}")

            }

            var dbStatusPoint = DbStatusPointHandler(context = this)
            var dataStatusPoint = dbStatusPoint.readStatusPoint()
            if(dataStatusPoint.size==0){
                var statusPoint = StatusPoint("Ожидание")
                dbStatusPoint.insertStatusPoint(statusPoint)
                statusPoint = StatusPoint("Отложен")
                dbStatusPoint.insertStatusPoint(statusPoint)
                statusPoint = StatusPoint("Отклонен")
                dbStatusPoint.insertStatusPoint(statusPoint)
                statusPoint = StatusPoint("Подтвержден")
                dbStatusPoint.insertStatusPoint(statusPoint)
            }

            var statusWaitId = dbStatusPoint.getIdStatusPointWait().get(0).id
            //course ID???
            for(i in 0..Items.chooseItem.size-1){
                var dbCoursePoint = DbCoursePointHandler(context = this)
                //TODO: insert values to CoursePoint
                //TODO: проверять все ли выбранные даты насторены!!!
                     for(j in 0..doseTime.size-1){

                         //было PointCourse.courseID

                         var coursePoint = CoursePoint(DataRecyclerCourse.courseID, Items.chooseItem[i], doseTime[j], statusWaitId )
                            dbCoursePoint.insertCoursePoint(coursePoint)
                }

            }



           /// testInterval.text = doseTime.joinToString()



        }



            //val intent = Intent(this, SetTimeCourseActivity::class.java)

                //    startActivity(intent)
                }

        }

