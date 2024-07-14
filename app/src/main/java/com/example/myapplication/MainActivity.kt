package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.network.ApiConfig
import com.example.myapplication.network.SuperHero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var ivSuperHero: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvFullName: TextView
    private lateinit var tvAlterEgos: TextView
    private lateinit var tvPlaceOfBirth: TextView
    private lateinit var tvFirstAppearance: TextView
    private lateinit var tvPublisher: TextView
    private lateinit var tvAlignment: TextView
    private lateinit var tvIntelligence: TextView
    private lateinit var tvStrength: TextView
    private lateinit var tvSpeed: TextView
    private lateinit var tvDurability: TextView
    private lateinit var tvPower: TextView
    private lateinit var tvCombat: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvRace: TextView
    private lateinit var tvHeight: TextView
    private lateinit var tvWeight: TextView
    private lateinit var tvEyeColor: TextView
    private lateinit var tvHairColor: TextView
    private lateinit var tvOccupation: TextView
    private lateinit var tvBase: TextView
    private lateinit var tvGroupAffiliation: TextView
    private lateinit var tvRelatives: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivSuperHero = findViewById(R.id.ivSuperHero)
        tvName = findViewById(R.id.tvName)
        tvFullName = findViewById(R.id.tvFullName)
        tvAlterEgos = findViewById(R.id.tvAlterEgos)
        tvPlaceOfBirth = findViewById(R.id.tvPlaceOfBirth)
        tvFirstAppearance = findViewById(R.id.tvFirstAppearance)
        tvPublisher = findViewById(R.id.tvPublisher)
        tvAlignment = findViewById(R.id.tvAlignment)
        tvIntelligence = findViewById(R.id.tvIntelligence)
        tvStrength = findViewById(R.id.tvStrength)
        tvSpeed = findViewById(R.id.tvSpeed)
        tvDurability = findViewById(R.id.tvDurability)
        tvPower = findViewById(R.id.tvPower)
        tvCombat = findViewById(R.id.tvCombat)
        tvGender = findViewById(R.id.tvGender)
        tvRace = findViewById(R.id.tvRace)
        tvHeight = findViewById(R.id.tvHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvEyeColor = findViewById(R.id.tvEyeColor)
        tvHairColor = findViewById(R.id.tvHairColor)
        tvOccupation = findViewById(R.id.tvOccupation)
        tvBase = findViewById(R.id.tvBase)
        tvGroupAffiliation = findViewById(R.id.tvGroupAffiliation)
        tvRelatives = findViewById(R.id.tvRelatives)

        getSuperHeroData("50")
    }

    private fun getSuperHeroData(id: String) {
        val call = ApiConfig.getApiService().getSuperHero(id = id)
        call.enqueue(object : Callback<SuperHero> {
            override fun onResponse(call: Call<SuperHero>, response: Response<SuperHero>) {
                if (response.isSuccessful) {
                    val superHero = response.body()
                    superHero?.let {
                        tvName.text = it.name
                        tvFullName.text = "Full Name: ${it.biography?.fullName ?: "N/A"}"
                        tvAlterEgos.text = "Alter Egos: ${it.biography?.alterEgos ?: "N/A"}"
                        tvPlaceOfBirth.text = "Place of Birth: ${it.biography?.placeOfBirth ?: "N/A"}"
                        tvFirstAppearance.text = "First Appearance: ${it.biography?.firstAppearance ?: "N/A"}"
                        tvPublisher.text = "Publisher: ${it.biography?.publisher ?: "N/A"}"
                        tvAlignment.text = "Alignment: ${it.biography?.alignment ?: "N/A"}"
                        tvIntelligence.text = "Intelligence: ${it.powerstats?.intelligence ?: "N/A"}"
                        tvStrength.text = "Strength: ${it.powerstats?.strength ?: "N/A"}"
                        tvSpeed.text = "Speed: ${it.powerstats?.speed ?: "N/A"}"
                        tvDurability.text = "Durability: ${it.powerstats?.durability ?: "N/A"}"
                        tvPower.text = "Power: ${it.powerstats?.power ?: "N/A"}"
                        tvCombat.text = "Combat: ${it.powerstats?.combat ?: "N/A"}"
                        tvGender.text = "Gender: ${it.appearance?.gender ?: "N/A"}"
                        tvRace.text = "Race: ${it.appearance?.race ?: "N/A"}"
                        tvHeight.text = "Height: ${it.appearance?.height?.get(1) ?: "N/A"}"
                        tvWeight.text = "Weight: ${it.appearance?.weight?.get(1) ?: "N/A"}"
                        tvEyeColor.text = "Eye Color: ${it.appearance?.eyeColor ?: "N/A"}"
                        tvHairColor.text = "Hair Color: ${it.appearance?.hairColor ?: "N/A"}"
                        tvOccupation.text = "Occupation: ${it.work?.occupation ?: "N/A"}"
                        tvBase.text = "Base: ${it.work?.base ?: "N/A"}"
                        tvGroupAffiliation.text = "Group Affiliation: ${it.connections?.groupAffiliation ?: "N/A"}"
                        tvRelatives.text = "Relatives: ${it.connections?.relatives ?: "N/A"}"
                        Glide.with(this@MainActivity).load(it.image?.url ?: "").into(ivSuperHero)
                    }
                } else {
                    Log.e("API Error", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SuperHero>, t: Throwable) {
                Log.e("API Error", "API call failed: ${t.message}")
            }
        })
    }
}
