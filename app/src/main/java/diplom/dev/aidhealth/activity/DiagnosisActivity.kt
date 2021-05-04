package diplom.dev.aidhealth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.DataRecyclerDiagnosis
import diplom.dev.aidhealth.DataRecyclerSymptoms
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.DbDiagnosisHandler
import diplom.dev.aidhealth.db.handler.DbDiagnosisSymptomHandler
import diplom.dev.aidhealth.db.handler.DbHandler
import diplom.dev.aidhealth.db.handler.DbSymptomHandler
import diplom.dev.aidhealth.db.model.Diagnosis
import diplom.dev.aidhealth.db.model.DiagnosisSymptom
import diplom.dev.aidhealth.db.model.Symptom
import diplom.dev.aidhealth.recycler.DiagnosisRecyclerAdapter
import diplom.dev.aidhealth.recycler.DoctorsRecyclerAdapter

class DiagnosisActivity : AppCompatActivity() {

    private lateinit var toAddDiagnosisBtn: ImageButton
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis)
        initialise()

        val recyclerView: RecyclerView = findViewById(R.id.diagnosysRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DiagnosisRecyclerAdapter(fillList())
        setListener()
    }

    fun fillList(): ArrayList<String> {
        var db = DbDiagnosisHandler(context = this)
        var dbSymptom = DbSymptomHandler(context = this)
        var dataDiagnosis = db.readDiagnosis()

        var dataRecycler = arrayListOf<String>()
          var diagnosisID = mutableListOf<Int>()

        for (i in 0..dataDiagnosis.size - 1) {
            //сохраняем один полученный айди
            diagnosisID.add(dataDiagnosis.get(i).id)
            //сохраняем этот айди в глобаьлную переменную
            DataRecyclerDiagnosis.diagnosisID = dataDiagnosis.get(i).id
            //запрашиваем список идентификаторов симптомов для диагноза
            var dataDiagnosisSymptom = DbDiagnosisSymptomHandler(context = this)
            var symptomsForDiagnosis = dataDiagnosisSymptom.readOneDiagnosisSymptom()
            var symptomIdArray = arrayListOf<Int>()
            var dataSymptomTitleForDiagnosis = mutableListOf<Symptom>()
            var symptomTitleForDiagnosis = mutableListOf<String>()

            // перекладываем все в обычный массив достаем по одному идентификатору, кладем в глобальную переменную
            for(j in 0..symptomsForDiagnosis.size-1){
                symptomIdArray.add(symptomsForDiagnosis.get(j).symptomID)
            }

            for(j in 0..symptomIdArray.size-1){
                DataRecyclerDiagnosis.symptomID = symptomsForDiagnosis.get(j).symptomID
                dataSymptomTitleForDiagnosis =  dbSymptom.getOneSymptom()
                symptomTitleForDiagnosis.add(dataSymptomTitleForDiagnosis.get(0).title)
            }
            dataRecycler.add(dataDiagnosis.get(i).diagnosisTitle+"\n "+symptomTitleForDiagnosis.joinToString())


        }

        return dataRecycler

    }

    fun initialise(){
        toAddDiagnosisBtn = findViewById(R.id.toAddDiagnosisBtn)
        textView = findViewById(R.id.textView3)
    }

   private fun setListener(){
        toAddDiagnosisBtn.setOnClickListener(){
            DataRecyclerSymptoms.chooseSymptomId.clear()
            DataRecyclerSymptoms.ID.clear()
            DataRecyclerSymptoms.symptomId.clear()

            val intent = Intent(this@DiagnosisActivity, AddDiagnosisActivity::class.java)
            startActivity(intent)
        }
    }
}