package com.streamflixreborn.streamflix.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.streamflixreborn.streamflix.R
import com.streamflixreborn.streamflix.models.Profile

class ProfileAdapter(private val profiles: List<Profile>, private val onProfileClicked: (Profile) -> Unit) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profiles[position]
        holder.bind(profile)
        holder.itemView.setOnClickListener { onProfileClicked(profile) }
    }

    override fun getItemCount(): Int = profiles.size

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImageView: ImageView = itemView.findViewById(R.id.profile_avatar)
        private val nameTextView: TextView = itemView.findViewById(R.id.profile_name)

        fun bind(profile: Profile) {
            nameTextView.text = profile.name
            // TODO: Load avatar image using a library like Glide or Picasso
        }
    }
}
