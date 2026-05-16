package com.streamflixreborn.streamflix.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.streamflixreborn.streamflix.BuildConfig
import com.streamflixreborn.streamflix.R
import com.streamflixreborn.streamflix.activities.main.MainMobileActivity
import com.streamflixreborn.streamflix.activities.main.MainTvActivity
import com.streamflixreborn.streamflix.database.AppDatabase
import com.streamflixreborn.streamflix.databinding.ActivityProfileSelectionBinding
import com.streamflixreborn.streamflix.models.Profile
import com.streamflixreborn.streamflix.utils.UserPreferences

class ProfileSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profiles = createDummyProfiles()
        val adapter = ProfileAdapter(profiles) { profile ->
            // Handle profile selection
            UserPreferences.currentProfile = profile
            AppDatabase.resetInstance()

            val intent = if (BuildConfig.APP_LAYOUT == "mobile") {
                Intent(this, MainMobileActivity::class.java)
            } else {
                Intent(this, MainTvActivity::class.java)
            }
            intent.putExtra("selected_profile", profile)
            startActivity(intent)
            finish()
        }

        val spanCount = if (BuildConfig.APP_LAYOUT == "mobile") 2 else 4
        binding.profilesRecyclerView.layoutManager = GridLayoutManager(this, spanCount)
        binding.profilesRecyclerView.adapter = adapter
    }

    private fun createDummyProfiles(): List<Profile> {
        return listOf(
            Profile("1", "User 1", "@drawable/avatar1"),
            Profile("2", "User 2", "@drawable/avatar2"),
            Profile("3", "User 3", "@drawable/avatar3"),
            Profile("4", "Add Profile", "@drawable/ic_add")
        )
    }
}
