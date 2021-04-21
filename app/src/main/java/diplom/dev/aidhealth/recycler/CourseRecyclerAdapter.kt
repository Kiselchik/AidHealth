package diplom.dev.aidhealth.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.R

class CourseRecyclerAdapter ( private val names: ArrayList<String>) :
    RecyclerView.Adapter<CourseRecyclerAdapter.MyViewHolderCourse>() {

    class MyViewHolderCourse(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView: TextView? = null
        init {
            textView = itemView.findViewById(R.id.textViewCourse)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderCourse {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_course_layout, parent, false)
        return MyViewHolderCourse(itemView)
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: MyViewHolderCourse, position: Int) {
        holder.textView?.text = names[position]

    }
}