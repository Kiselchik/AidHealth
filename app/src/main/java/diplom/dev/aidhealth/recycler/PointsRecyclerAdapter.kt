package diplom.dev.aidhealth.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.R

class PointsRecyclerAdapter (private var dates: MutableList<String>) :
    RecyclerView.Adapter<PointsRecyclerAdapter.MyViewHolderPoint>() {


    class MyViewHolderPoint(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView: TextView? = null
        var choise = false
        init {
            textView = itemView.findViewById(R.id.timePointText)
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
    ): PointsRecyclerAdapter.MyViewHolderPoint {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_point_course_layout, parent, false)
        // notifyDataSetChanged()

        return PointsRecyclerAdapter.MyViewHolderPoint(itemView)
    }

    override fun getItemCount() =  dates.size

    override fun onBindViewHolder(holder: PointsRecyclerAdapter.MyViewHolderPoint, position: Int) {
        holder.textView?.text = dates[position]
        var date = dates
        holder.itemView.setOnLongClickListener(){

            return@setOnLongClickListener true
        }

    }

}