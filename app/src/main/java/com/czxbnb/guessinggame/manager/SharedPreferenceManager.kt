package com.czxbnb.guessinggame.manager

import android.content.Context
import android.content.SharedPreferences
import com.czxbnb.guessinggame.base.BaseApp
import com.google.gson.Gson


class SharedPreferenceManager {
    private val mPrefs: SharedPreferences

    init {
        mPrefs =
            BaseApp.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        private const val PREF_NAME = "pref_guessing_game"
        private const val KEY_QUESTION_VERSION = "key_question_version"
        private const val KEY_CURRENT_PROGRESS = "key_current_progress"
        private const val KEY_CURRENT_SCORE = "key_current_score"

        private var instance: SharedPreferenceManager? = null

        fun getInstance(): SharedPreferenceManager? {
            if (instance == null) {
                synchronized(SharedPreferenceManager::class.java) {
                    if (instance == null) {
                        instance = SharedPreferenceManager()
                    }
                }
            }
            return instance
        }
    }
    var questionVersion: Int
        get() = mPrefs.getInt(KEY_QUESTION_VERSION, 0)
        set(questionVersion: Int) = mPrefs.edit().putInt(KEY_QUESTION_VERSION, questionVersion).apply()

    var currentProgress: Int
        get() = mPrefs.getInt(KEY_CURRENT_PROGRESS, 0)
        set(questionVersion: Int) = mPrefs.edit().putInt(KEY_CURRENT_PROGRESS, questionVersion).apply()

    var currentScore: Int
        get() = mPrefs.getInt(KEY_CURRENT_SCORE, 0)
        set(questionVersion: Int) = mPrefs.edit().putInt(KEY_CURRENT_SCORE, questionVersion).apply()
}