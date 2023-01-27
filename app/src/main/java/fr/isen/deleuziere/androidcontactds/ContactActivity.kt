package fr.isen.deleuziere.androidcontactds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.deleuziere.androidcontactds.classes.Results
import fr.isen.deleuziere.androidcontactds.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent.getSerializableExtra("contact") as Results

        val actionBar = supportActionBar
        actionBar?.title = contact.name?.first + " " + contact.name?.last

        val image = contact.picture?.large
        if (image != "") { Picasso.get().load(image).into(binding.imageDetail) }

        binding.nomDetail.text = contact.name?.last
        binding.prenomDetail.text = contact.name?.first

        val streetNumber = contact.location?.street?.number.toString()
        val streetName = contact.location?.street?.name
        val city = contact.location?.city

        binding.adresseDetail.text = streetNumber + " " + streetName + " " + city

        binding.emailDetail.text = contact.email
        binding.numeroDetail.text = contact.cell

        binding.naissanceDetail.text = contact.dob?.date

    }
}