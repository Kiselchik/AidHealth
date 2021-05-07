package diplom.dev.aidhealth.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.R

class ProcedureRecyclerAdapter(private var dates: MutableList<String>) :
    RecyclerView.Adapter<ProcedureRecyclerAdapter.MyViewHolderProcedure>() {


    class MyViewHolderProcedure(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView: TextView? = null
        var choise = false
        init {
            textView = itemView.findViewById(R.id.textCourseDate)
            itemView.setOnClickListener(){
                val pos: Int = adapterPosition
                val text: String = (textView)?.getText().toString()
                itemView.setBackgroundColor(Color.RED)
                //  Toast.makeText(itemView.context, "Нажали $pos, ${text}", Toast.LENGTH_SHORT).show()
            }


        }



    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProcedureRecyclerAdapter.MyViewHolderProcedure {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_course_date_layout, parent, false)
        // notifyDataSetChanged()

        return ProcedureRecyclerAdapter.MyViewHolderProcedure(itemView)
    }

    override fun getItemCount() =  dates.size

    override fun onBindViewHolder(holder: ProcedureRecyclerAdapter.MyViewHolderProcedure, position: Int) {
        holder.textView?.text = dates[position]
        var date = dates
        holder.itemView.setOnLongClickListener(){

            return@setOnLongClickListener true
        }

    }
}