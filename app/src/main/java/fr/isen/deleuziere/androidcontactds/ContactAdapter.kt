package fr.isen.deleuziere.androidcontactds

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.deleuziere.androidcontactds.classes.Results

class ContactAdapter(private var contacts: ArrayList<Results>, val OnClick: (name: Results) -> Unit) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val prenomText = view.findViewById<TextView>(R.id.prenomText)
        val nomText = view.findViewById<TextView>(R.id.nomText)
        val adresseText = view.findViewById<TextView>(R.id.adresseText)
        val emailText = view.findViewById<TextView>(R.id.emailText)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell, parent, false)

        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]

        holder.prenomText.text = contact.name?.first
        holder.nomText.text = contact.name?.last
        val streetNumber =contact.location?.street?.number.toString()
        val streetName = contact.location?.street?.name
        val city = contact.location?.city
        holder.adresseText.text =  streetNumber + " " + streetName + " " + city
        holder.emailText.text = contact.email

        val image = contact.picture?.large
        if (image != "") { Picasso.get().load(image).into(holder.imageView) }


        holder.itemView.setOnClickListener {
            OnClick(contact)
        }
    }

    override fun getItemCount(): Int = contacts.size

    fun refreshList(Contactshandled:ArrayList<Results>) {
        contacts = Contactshandled
        notifyDataSetChanged()
    }
}
