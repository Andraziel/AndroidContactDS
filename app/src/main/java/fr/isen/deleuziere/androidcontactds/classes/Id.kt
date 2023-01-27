package fr.isen.deleuziere.androidcontactds.classes

import com.google.gson.annotations.SerializedName


data class Id (

  @SerializedName("name"  ) var name  : String? = null,
  @SerializedName("value" ) var value : String? = null

):java.io.Serializable