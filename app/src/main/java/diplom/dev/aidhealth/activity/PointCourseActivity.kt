package diplom.dev.aidhealth.activity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.DataRecyclerCourse
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.db.handler.DbCourseHandler
import diplom.dev.aidhealth.db.handler.DbCoursePointHandler
import diplom.dev.aidhealth.recycler.CourseRecyclerAdapter
import diplom.dev.aidhealth.recycler.PointsRecyclerAdapter

class PointCourseActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_course)

        textView = findViewById(R.id.testTextView)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerPointsForCourse)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PointsRecyclerAdapter(fillList())

    }


    fun fillList(): ArrayList<String> {
        var db = DbCoursePointHandler(context = this)
        var data = db.getOneCourse()
      //  Toast.makeText(this, "${data.size}")
        var datarecycler = arrayListOf<String>()
        var dataRecyclerId = arrayListOf<Int>()

        textView.text = "${DataRecyclerCourse.courseID}"

            for (i in 0..data.size - 1) {
                datarecycler.add(data.get(i).courseID.toString()+" "+data.get(i).day + " " + data.get(i).time)
                // dataRecyclerId.add(data.get(i).t)
            }
          //  DataRecyclerCourse.dataRecycler = dataRecyclerId

        return datarecycler
    }
}
