package diplom.dev.aidhealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private lateinit var pills_button: ImageButton
    private lateinit var doctor_button: ImageButton
    private lateinit var dynamic_button: ImageButton
    private lateinit var course_button: ImageButton
    private lateinit var symptom_button: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        setListener()
    }

    private fun initializeView(){
        pills_button = findViewById(R.id.ib_pills)
        doctor_button = findViewById(R.id.ib_doctors)
        dynamic_button = findViewById(R.id.ib_dynamic)
        course_button = findViewById(R.id.ib_course)
        symptom_button = findViewById(R.id.ib_symptom)
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
    }

}