package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.databinding.ActivityPaymentDetailsBinding
import org.json.JSONException
import org.json.JSONObject

class PaymentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        try {
            val jsonObj = JSONObject(intent.getStringExtra("PaymentDetails"))
            showDetails(jsonObj.getJSONObject("response"), intent.getStringExtra("PaymentAmount"))
        } catch (e : JSONException) {
            e.printStackTrace()
        }
    }

    private fun showDetails(response: JSONObject, paymentAmount: String?) {
        try {
            binding.payDetailTvID.text = response.getString("id")
            binding.payDetailTvAmount.text = response.getString(" $ $paymentAmount")
            binding.payDetailTvStatus.text = response.getString("state")
        } catch(e : JSONException) {
            e.printStackTrace()
        }
    }
}