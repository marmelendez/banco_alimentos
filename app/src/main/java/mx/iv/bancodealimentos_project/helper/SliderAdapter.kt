package mx.iv.bancodealimentos_project.helper

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.smarteist.autoimageslider.SliderViewAdapter
import mx.iv.bancodealimentos_project.R
import java.util.ArrayList

class SliderAdapter(sliderDataArrayList: ArrayList<AboutUsData>) : SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    private val listItems: List<AboutUsData>

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, null)
        return SliderAdapterViewHolder(inflate)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val sliderItem: AboutUsData = listItems[position]

        viewHolder.title.text = sliderItem.title
        viewHolder.description.text = sliderItem.description
        viewHolder.icon.setImageResource(sliderItem.img)
        viewHolder.layout.background = viewHolder.view.resources.getDrawable(sliderItem.bg)
    }

    override fun getCount(): Int {
        return listItems.size
    }

    class SliderAdapterViewHolder(view: View) : ViewHolder(view) {
        var view: View = view
        var icon: ImageView = view.findViewById(R.id.sliderItemIvIcon)
        var title: TextView = view.findViewById(R.id.sliderItemTvTitle)
        var description: TextView = view.findViewById(R.id.sliderItemTvDescription)
        var layout: LinearLayout = view.findViewById(R.id.sliderLLlayout)
    }

    init {
        listItems = sliderDataArrayList
    }
}