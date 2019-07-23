package com.example.todolistmvcapplication.activities

import android.support.annotation.StringRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.example.todolistmvcapplication.utils.DateFormatter
import org.junit.Rule
import org.junit.Test
import java.util.*
import android.widget.TextView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import android.app.Activity
import android.support.test.espresso.contrib.ActivityResultMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers
import com.example.todolistmvcapplication.R


class AddEventActivityTest {

    private val TAG = AddEventActivityTest::class.java.simpleName

    @get:Rule
    val mActivityTestRule = ActivityTestRule(AddEventActivity::class.java)

    private fun toastIsDisplayed(@StringRes stringId: Int) {
        Thread.sleep(1000)
        onView(withText(stringId)).inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenEventNameEmptyToast() {
        Espresso.onView(withId(R.id.action_done)).perform(click())
        toastIsDisplayed(R.string.plz_enter_event_name)
    }

    @Test
    fun whenEventTimeNotFormattedToast() {
        Espresso.onView(withId(R.id.edt_event_name))
            .perform(ViewActions.typeText("Mast Event"))
        Espresso.onView(withId(R.id.action_done)).perform(click())
        toastIsDisplayed(R.string.plz_select_proper_event_time)
    }

    private fun setTextInTextView(value: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(TextView::class.java))
            }

            override fun perform(uiController: UiController, view: View) {
                (view as TextView).text = value
            }

            override fun getDescription(): String {
                return "replace text"
            }
        }
    }

    @Test
    fun whenEventDescriptionEmptyToast() {
        Espresso.onView(withId(R.id.edt_event_name))
            .perform(ViewActions.typeText("Mast Event"))
        Espresso.onView(withId(R.id.txt_event_time))
            .perform(setTextInTextView(DateFormatter.getStringFromDate(Date(), DateFormatter.dd_MM_yyyy_HH_mm)))
        Espresso.onView(withId(R.id.action_done)).perform(click())
        toastIsDisplayed(R.string.plz_enter_event_description)
    }

    @Test
    fun checkEventCreationisSuccessfullAndSentBack() {
        Espresso.onView(withId(R.id.edt_event_name))
            .perform(ViewActions.typeText("Mast Event"))
        Espresso.onView(withId(R.id.txt_event_time))
            .perform(setTextInTextView(DateFormatter.getStringFromDate(Date(), DateFormatter.dd_MM_yyyy_HH_mm)))
        Espresso.onView(withId(R.id.edt_event_description))
            .perform(ViewActions.typeText("Mast Event description sample text for long descripition is here."))
        Espresso.onView(withId(R.id.action_done)).perform(click())
        assertThat(mActivityTestRule.activityResult,
            ActivityResultMatchers.hasResultCode(Activity.RESULT_OK)
        )
        assertThat(mActivityTestRule.activityResult,
            ActivityResultMatchers.hasResultData(IntentMatchers.hasExtraWithKey(AddEventActivity.EVENT_OBJ))
        )

    }


}