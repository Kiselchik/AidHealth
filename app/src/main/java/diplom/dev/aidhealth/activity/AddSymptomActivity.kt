package diplom.dev.aidhealth.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.DbCourseHandler
import diplom.dev.aidhealth.db.handler.DbSymptomHandler
import diplom.dev.aidhealth.db.model.Course
import diplom.dev.aidhealth.db.model.Symptom

class AddSymptomActivity : AppCompatActivity() {
    private lateinit var newSymptomEdTxt: EditText
    private lateinit var evaluationSymptomChBox: CheckBox
    private lateinit var addNewSymptomBtn: Button
    var chooseEvaluation: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_symptom)
        initialise()
        setListener()
    }

    fun initialise() {
        newSymptomEdTxt = findViewById(R.id.newSymptomEdTxt)
        evaluationSymptomChBox = findViewById(R.id.evaluationSymptomChBox)
        addNewSymptomBtn = findViewById(R.id.addNewSymptomBtn)
    }

    private fun setListener() {
        evaluationSymptomChBox.setOnCheckedChangeListener() { buttonView, isChecked ->
            if (isChecked) {
                chooseEvaluation = 1
            }
        }
        addNewSymptomBtn.setOnClickListener(){
            val shPref = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val savedEmail = shPref.getString("EMAIL_KEY", "").toString()
            val nameSymptom = newSymptomEdTxt.text.toString()
            var db = DbSymptomHandler(context = this)
            var units = 0
            var ball = 0
            if(chooseEvaluation==0){
                units = 1
            }else{
                ball = 1
            }
            var symptom = Symptom(savedEmail, nameSymptom, units, ball )
            db.insertSymptom(symptom)
        }
    }
}
