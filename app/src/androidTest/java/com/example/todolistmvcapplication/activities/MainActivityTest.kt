package com.example.todolistmvcapplication.activities

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.toPackage
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.runner.AndroidJUnit4
import com.example.todolistmvcapplication.R
import com.example.todolistmvcapplication.utils.MyApplication
import org.hamcrest.Matchers.not
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val TAG = MainActivityTest::class.java.simpleName

    @get:Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkIfNoDataTextDisplayed() {
        if (MyApplication.getInstance().eventDB!!.eventDao().getEvents().isEmpty()) {
            Espresso.onView(ViewMatchers.withId(R.id.txt_no_data)).check(matches(isDisplayed()))
        } else {
            Espresso.onView(ViewMatchers.withId(R.id.txt_no_data)).check(matches(not(isDisplayed())))
        }
    }

    @Test
    fun checkIfEventResultIsReturned() {
        Intents.init()
        intending(toPackage("com.example.todolistmvcapplication.activities.AddEventActivity"))
        Espresso.onView(ViewMatchers.withId(R.id.btn_add)).perform(ViewActions.click())
    }

}