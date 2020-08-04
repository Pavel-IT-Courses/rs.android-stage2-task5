package com.example.ted_mvp.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.ted_mvp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}
