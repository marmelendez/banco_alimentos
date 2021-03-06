package mx.iv.bancodealimentos_project


import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.paypal.android.sdk.payments.*
import mx.iv.bancodealimentos_project.databinding.ActivityDonationsBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment
import org.json.JSONException
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class DonationsActivity : AppCompatActivity(),  MenuFragment.CallbackMenu  {

    // PAYPAL
    private val PAYPAL_CLIENT_ID = "AR-QkFQ7N-tBR04ywjZuZwDpQ3j4gmzulTrjd6hGopUiiIhyTsUuuItV60ddErecEK22ELbu2L-Ceu5B"
    private val PAYPAL_REQUEST_CODE : Int = 123
    private val config: PayPalConfiguration = PayPalConfiguration()
        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
        .clientId(PAYPAL_CLIENT_ID)

    private lateinit var binding: ActivityDonationsBinding
    private lateinit var amount: String
    private val db = FirebaseFirestore.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //PAYPAL SERVICE
        val intent = Intent(this, PayPalService::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(intent)


        // Boton de donaciones
        binding.donationsBtnDonate.setOnClickListener {
            amount = binding.donationsEtAmount.text.toString()
            // Validar que la cantidad ingresada sea correcta
            if (amount.isNotBlank() && amount.toDouble() > 0){

                // Datos a guardar de la donaci??n
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                val formatted = current.format(formatter)

                var user = "generalUser"

                if (Firebase.auth.currentUser != null) {
                    user = Firebase.auth.currentUser!!.email.toString()
                }
                // Guardar datos de donaci??n en Firestore
                db.collection("donations").document().set(
                    hashMapOf("email" to user,
                                "amount" to amount,
                                "date" to formatted)
                )

                // Mostrar p??gina para realizar una donaci??n del Banco de Alimentos
                val url = "https://bdalimentos.org/make-a-donation/?cause_id=8492"
                val uri: Uri = Uri.parse(url)
                val inte = Intent(Intent.ACTION_VIEW, uri)
                startActivity(inte)

                //throw RuntimeException("Test Crash")
                //processPayment()
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
}