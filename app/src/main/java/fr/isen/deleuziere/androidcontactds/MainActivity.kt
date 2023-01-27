package fr.isen.deleuziere.androidcontactds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.deleuziere.androidcontactds.classes.Data
import fr.isen.deleuziere.androidcontactds.classes.Results
import fr.isen.deleuziere.androidcontactds.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleText.text = getString(R.string.Title)

        val actionBar = supportActionBar
        actionBar?.title = binding.titleText.text

        val value = arrayListOf<Results>()

        binding.contactList.layoutManager = LinearLayoutManager(this)

        binding.contactList.adapter = ContactAdapter(value) {
            Log.w("adapter", "value : $it")
        }
        loadContactsFromAPI()
    }

    private fun loadContactsFromAPI() {

        val url = "https://randomuser.me/api/?results=10&nat=fr"
        val jsonObject = JSONObject()
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, jsonObject, {
                Log.w("Debug", "reponse : $it")
                manageData(it.toString())
            }, {
                Log.w("Debug", "erreur : $it")
            }
        )
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun manageData(data: String) {
        var contactsResult = Gson().fromJson(data, Data::class.java)
        /*val dishCategoryFiltered = contactsResult.results//.firstOrNull { it.nameFr == category }
*/
        val adapter = binding.contactList.adapter as ContactAdapter

        adapter.refreshList(contactsResult?.results?.map { it } as ArrayList<Results>)
    }
}