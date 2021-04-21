package diplom.dev.aidhealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.db.DbHandler
import diplom.dev.aidhealth.db.MyDbManager
import diplom.dev.aidhealth.recycler.CustomRecyclerAdapter
import diplom.dev.aidhealth.recycler.DoctorsRecyclerAdapter

class DoctorActivity : AppCompatActivity() {
    private lateinit var addDoctorButton: ImageButton
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewDoctor)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DoctorsRecyclerAdapter(fillList())
        initialize()
    }

    fun initialize() {
        addDoctorButton = findViewById(R.id.addButton)
    }

    fun fillList(): ArrayList<String> {
        var db = DbHandler(context = this)
        var data = db.readDoctor()
        var datarecycler = arrayListOf<String>()

        //textDoc.text =""
        for (i in 0..data.size-1) {
            datarecycler.add(data.get(i).id.toString() + " " + data.get(i).firstName + data.get(i).lastName +
                        data.get(i).position + data.get(i).address
            )
        }
        return datarecycler

    }

    fun onClickAddDoctor(view: View) {

            val intent = Intent(this@DoctorActivity, AddDoctorActivity::class.java)
            startActivity(intent)


    }
}