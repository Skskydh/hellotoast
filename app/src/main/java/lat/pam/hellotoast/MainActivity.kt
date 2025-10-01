package lat.pam.hellotoast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var mCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switchpage)
        val buttonBrowser = findViewById<Button>(R.id.button_browser)
        val buttonContact = findViewById<Button>(R.id.button_contact)
        val buttonMap = findViewById<Button>(R.id.button_map)
        val buttonEmail = findViewById<Button>(R.id.button_email)
        val buttonMessage = findViewById<Button>(R.id.button_mesagge)

        buttonCountUp.setOnClickListener {
            mCount++
            mShowCount.text = mCount.toString()
        }

        buttonToast.setOnClickListener {
            val tulisan = mShowCount.text.toString()
            Toast.makeText(this, "Angka yang dimunculkan: $tulisan", Toast.LENGTH_LONG).show()
        }

        buttonSwitchPage.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        buttonBrowser.setOnClickListener(View.OnClickListener {
            val intentbrowse = Intent(Intent.ACTION_VIEW)
            intentbrowse.setData(Uri.parse("https://www.google.com/"))
            startActivity(intentbrowse)
        })

        buttonContact.setOnClickListener {
            val intentContact = Intent(Intent.ACTION_PICK, android.provider.ContactsContract.Contacts.CONTENT_URI)
            startActivity(intentContact)
        }

        buttonMap.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=Universitas+Islam+Negeri+SGD+Bandung")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        buttonEmail.setOnClickListener {
            val intentEmail = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("contoh@email.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Tes Email")
                putExtra(Intent.EXTRA_TEXT, "Halo ini pesan dari aplikasi Android")
            }
            startActivity(intentEmail)
        }

        buttonMessage.setOnClickListener {
            val intentMessage = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"  // konten berupa teks biasa
                putExtra(Intent.EXTRA_TEXT, "Halo, ini pesan dari aplikasi HelloToast")
            }
            startActivity(Intent.createChooser(intentMessage, "Kirim pesan dengan:"))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
}
