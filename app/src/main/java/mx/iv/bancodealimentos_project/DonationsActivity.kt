package mx.iv.bancodealimentos_project


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.paypal.android.sdk.payments.*
import mx.iv.bancodealimentos_project.databinding.ActivityDonationsBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment
import org.json.JSONException
import java.math.BigDecimal

class DonationsActivity : AppCompatActivity(),  MenuFragment.CallbackMenu  {

    // PAYPAL
    private val PAYPAL_CLIENT_ID = "AR-QkFQ7N-tBR04ywjZuZwDpQ3j4gmzulTrjd6hGopUiiIhyTsUuuItV60ddErecEK22ELbu2L-Ceu5B"
    private val PAYPAL_REQUEST_CODE : Int = 123
    private val config: PayPalConfiguration = PayPalConfiguration()
        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
        .clientId(PAYPAL_CLIENT_ID)

    private lateinit var binding: ActivityDonationsBinding
    private lateinit var amount: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FRAGMENTO DE MENU
        // Muestra el fragmento de menu desde el inicio
        val menuFragment = MenuFragment()
        val transactionMenu = supportFragmentManager.beginTransaction()
        transactionMenu.add(R.id.donationsMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transactionMenu.commit()

        //PAYPAL SERVICE
        val intent = Intent(this, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(intent)


        binding.donationsBtnDonate.setOnClickListener {
            amount = binding.donationsEtAmount.text.toString()
            if (amount.isNotBlank() && amount.toDouble() > 0){
                //throw RuntimeException("Test Crash")
                processPayment()
            } else {
                Toast.makeText(this, "Porfavor introduce una cantidad", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun processPayment() {
        val payment = PayPalPayment(
            BigDecimal(amount),
                "MXN",
                "Donacion al banco de alimentos",
                PayPalPayment.PAYMENT_INTENT_SALE)

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
        startActivityForResult(intent, PAYPAL_REQUEST_CODE)
    }

    override fun returnAct() {
        finish()
    }

    // DETENER EL SERVICIO DE PAYPAL
    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val confirmation = data?.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirmation != null) {
                    try {
                        val paymentDetails = confirmation.toJSONObject().toString(4)
                        val intent = Intent(this, PaymentDetailsActivity::class.java)
                        intent.putExtra("PaymentDetails", paymentDetails)
                        intent.putExtra("PaymentAmount", amount)
                        startActivity(intent)
                    } catch (e : JSONException) {
                        e.printStackTrace()
                    }
                }
            } else {
                Toast.makeText(this, "Pago cancelado", Toast.LENGTH_SHORT).show()
            }
        } else if(resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "No valido", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}