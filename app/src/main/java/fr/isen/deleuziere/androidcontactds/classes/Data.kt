package fr.isen.deleuziere.androidcontactds.classes

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf()

):java.io.Serializable