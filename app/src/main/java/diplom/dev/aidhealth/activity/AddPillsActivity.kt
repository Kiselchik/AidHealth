package diplom.dev.aidhealth.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.DbPillsHandler
import diplom.dev.aidhealth.db.model.Pill


class AddPillsActivity : AppCompatActivity() {
    private lateinit var pillName: EditText
    private lateinit var pillSizePackEdText: EditText
    private lateinit var pillNumPackEdText: EditText
    private lateinit var textView: TextView
    private lateinit var unitSpinner: Spinner
    private lateinit var measurement : String
    private lateinit var insertPillButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pills)
        initializeView()
        listener()
        spinner()
    }

    private fun initializeView() {
        pillName = findViewById(R.id.pillNameEdText)
        pillSizePackEdText = findViewById(R.id.pillSizePackEdText)
        pillNumPackEdText = findViewById(R.id.pillNumPackEdText)
        textView = findViewById(R.id.textView)
        unitSpinner = findViewById(R.id.spinner)
        insertPillButton = findViewById(R.id.insert_pill_button)
    }

    fun spinner() {

        var units = arrayOf<String>("мл", "мг")
        /*  var adapter =
        ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Вызываем адаптер
        unitSpinner.setAdapter(adapter)*/

        unitSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, units)
        unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext,
                    "${units.get(position)}",
                    Toast.LENGTH_SHORT
                ).show()

                measurement = units.get(position)
            }
        }

    }

    fun listener() {
        insertPillButton.setOnClickListener() {


            var db =
                DbPillsHandler(context = this)
            var pill = Pill(
                pillName.text.toString(), Integer.parseInt(pillSizePackEdText.text.toString()),
                Integer.parseInt(pillNumPackEdText.text.toString()).toFloat(), measurement
            )
            //  var db = DbHandler(context = this)
            db.insertPill(pill)
            /*    textView.text = ""
        myDbManager.openDb()
        myDbManager.insertToDb(pillName.text.toString())
        val dataList = myDbManager.readDbData()
        for (item in dataList){
            textView.append(item)
            textView.append("\n")
        }*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        /*    super.onDestroy()
      myDbManager.closeDb()*/
    }

    fun onClickDelete(view: View) {
        /*myDbManager.openDb()
        myDbManager.allPillsDelete()
        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            textView.append(item)
            textView.append("\n")
        }

    }
    */
    }
}
