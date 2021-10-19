package mx.iv.bancodealimentos_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import mx.iv.bancodealimentos_project.HelpActivity
import mx.iv.bancodealimentos_project.R

/**
 * Fragmento para agradecer cuando una persona solicita
 * Ser Voluntario o Ser Beneficiario
 */
class FormResponseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form_response, container, false)

        val btnReturnHome = view.findViewById<Button>(R.id.fragFormResponseBtnReturn)

        btnReturnHome.setOnClickListener {
            val intent = Intent(it.context, HelpActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}