package diplom.dev.aidhealth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.DataRecyclerCourse
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.DbCourseHandler
import diplom.dev.aidhealth.db.handler.DbHandler
import diplom.dev.aidhealth.recycler.CourseRecyclerAdapter
import diplom.dev.aidhealth.recycler.DoctorsRecyclerAdapter

class CourseActivity : AppCompatActivity() {

    private lateinit var addNewCourseButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCourse)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CourseRecyclerAdapter(fillList())
        initialize()
        setListener()
    }

    fun fillList(): ArrayList<String> {
        var db = DbCourseHandler(context = this)
        var data = db.readCourse()
        var datarecycler = arrayListOf<String>()
        var dataRecyclerId = arrayListOf<Int>()

        //textDoc.text =""
        for (i in 0..data.size-1) {
            datarecycler.add(data.get(i).title)
            dataRecyclerId.add(data.get(i).id)
        }
        DataRecyclerCourse.dataRecycler = dataRecyclerId
        return datarecycler

    }


    fun initialize(){
        addNewCourseButton = findViewById(R.id.addNewCourseButton)
    }

    fun setListener(){
        addNewCourseButton.setOnClickListener(){
            val intent = Intent(this@CourseActivity, AddCourseActivity::class.java)
            startActivity(intent)

        }
    }
}