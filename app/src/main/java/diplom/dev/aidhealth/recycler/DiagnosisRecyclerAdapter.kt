package diplom.dev.aidhealth.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.DataRecyclerCourse
import diplom.dev.aidhealth.R
import diplom.dev.aidhealth.activity.AddDiagnosisActivity

class DiagnosisRecyclerAdapter(private val names: ArrayList<String>) :
    RecyclerView.Adapter<DiagnosisRecyclerAdapter.MyViewHolderDiagnosis>() {

    class MyViewHolderDiagnosis(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView: TextView? = null
        init {
            textView = itemView.findViewById(R.id.textViewDiagnosis)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDiagnosis {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_diagnosis_layout, parent, false)
        return MyViewHolderDiagnosis(itemView)
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: MyViewHolderDiagnosis, position: Int) {
        holder.textView?.text = names[position]
        holder.itemView.setOnClickListener(){
           // var chooseCourseId = DataRecyclerCourse.dataRecycler[position]
            //  Toast.makeText(holder.itemView.context, "${chooseCourseId}", Toast.LENGTH_SHORT)

            val intent = Intent(holder.itemView.context, AddDiagnosisActivity::class.java)
          //  intent.putExtra("chooseCourseId", chooseCourseId)
            holder.itemView.context.startActivity(intent)
        }

    }
}
