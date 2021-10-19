package mx.iv.bancodealimentos_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderView
import mx.iv.bancodealimentos_project.R
import mx.iv.bancodealimentos_project.helper.AboutUsData
import mx.iv.bancodealimentos_project.helper.SliderAdapter

/**
 * Fragmento para mostrar información de la sección Sobre Nosotros
 */
class AboutUsDataFragment(private val idAboutUs: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_about_us_data, container, false)

        // Lista de elementos a mostrar
        val sliderDataArrayList: ArrayList<AboutUsData> = ArrayList()
        // View de slider para mostrar los elementos
        val sliderView = view.findViewById<SliderView>(R.id.aboutUsInfoSvSlider)

        // Identifica si el fragmento sera info (mision, vision, etc) o datos
        if (idAboutUs == "info") {
            sliderDataArrayList.add(AboutUsData(getString(R.string.aboutus),getString(R.string.paragraph), R.drawable.ic_white_info, R.drawable.info1))
            sliderDataArrayList.add(AboutUsData(getString(R.string.mision),getString(R.string.paragraph), R.drawable.ic_white_mision, R.drawable.info2))
            sliderDataArrayList.add(AboutUsData(getString(R.string.vision),getString(R.string.paragraph), R.drawable.ic_white_vision, R.drawable.info3))
        } else {
            sliderDataArrayList.add(AboutUsData(getString(R.string.number),getString(R.string.paragraph), R.drawable.ic_white_fam, R.drawable.data1))
            sliderDataArrayList.add(AboutUsData(getString(R.string.number),getString(R.string.paragraph), R.drawable.ic_white_people, R.drawable.data2))
            sliderDataArrayList.add(AboutUsData(getString(R.string.number),getString(R.string.paragraph), R.drawable.ic_white_store, R.drawable.data3))
            sliderDataArrayList.add(AboutUsData(getString(R.string.number),getString(R.string.paragraph), R.drawable.ic_white_data, R.drawable.data4))
        }

        // Muestra lista en slider con Adapter
        val adapter = SliderAdapter(sliderDataArrayList)

        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        sliderView.setSliderAdapter(adapter)
        sliderView.scrollTimeInSec = 7
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()

        return view
    }
}