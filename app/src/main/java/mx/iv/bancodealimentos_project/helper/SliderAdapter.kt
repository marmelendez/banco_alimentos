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

class SliderAdapter(context: Context?, sliderDataArrayList: ArrayList<AboutUsData>) : SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    // list for storing urls of images.
    private val listItems: List<AboutUsData>

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, null)
        return SliderAdapterViewHolder(inflate)
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val sliderItem: AboutUsData = listItems[position]

        // Glide is use to load image
        // from url in your imageview.

        viewHolder.title.text = sliderItem.title
        viewHolder.description.text = sliderItem.description
        viewHolder.icon.setImageResource(sliderItem.img)
        viewHolder.layout.background = viewHolder.view.resources.getDrawable(sliderItem.bg)
    }

    // this method will return
    // the count of our list.
    override fun getCount(): Int {
        return listItems.size
    }

    class SliderAdapterViewHolder(view: View) : ViewHolder(view) {
        // Adapter class for initializing
        // the views of our slider view.
        var view: View
        var icon: ImageView
        var title: TextView
        var description: TextView
        var layout: LinearLayout

        init {
            this.view = view
            this.icon = view.findViewById(R.id.sliderItemIvIcon)
            this.title = view.findViewById(R.id.sliderItemTvTitle)
            this.description = view.findViewById(R.id.sliderItemTvDescription)
            this.layout = view.findViewById(R.id.sliderLLlayout)
        }
    }

    // Constructor
    init {
        listItems = sliderDataArrayList
    }
}