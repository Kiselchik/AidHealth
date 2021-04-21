package diplom.dev.aidhealth.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diplom.dev.aidhealth.R

class DoctorsRecyclerAdapter( private val names: ArrayList<String>) :
    RecyclerView.Adapter<DoctorsRecyclerAdapter.MyViewHolderDoctor>() {

    class MyViewHolderDoctor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var textView: TextView? = null
        init {
            textView = itemView.findViewById(R.id.textViewDoctors)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderDoctor {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_doctor_layout, parent, false)
        return MyViewHolderDoctor(itemView)
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: MyViewHolderDoctor, position: Int) {
        holder.textView?.text = names[position]

    }
}