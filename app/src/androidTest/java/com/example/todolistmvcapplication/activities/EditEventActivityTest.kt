package com.example.todolistmvcapplication.activities

import android.app.Activity
import android.content.Intent
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.ActivityResultMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.View
import android.widget.TextView
import com.example.todolistmvcapplication.utils.DateFormatter
import com.example.todolistmvcapplication.utils.MyApplication
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import java.util.*
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.Intents
import com.example.todolistmvcapplication.R


class EditEventActivityTest {

    private val TAG = EditEventActivityTest::class.java.simpleName

    @get:Rule
    val mActivityTestRule = object : ActivityTestRule<EditEventActivity>(EditEventActivity::class.java, true, true) {
        override fun getActivityIntent(): Intent {
            Intents.init()
            val eventInDB = MyApplication.getInstance().eventDB?.eventDao()!!.getFirstEvent()
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val intent = Intent(targetContext, EditEventActivity::class.java)
            intent.putExtra(EditEventActivity.EVENT_OBJ, eventInDB)
            return intent
        }
    }

  /*  @get:Rule
    val mActivityTestRule = ActivityTestRule<EditEventActivity>(EditEventActivity::class.java, true, true)
*/
    private fun toastIsDisplayed(@StringRes stringId: Int) {
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withText(stringId))
            .inRoot(RootMatchers.withDecorView(Matchers.not(Matchers.`is`(mActivityTestRule.activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun whenEventNameEmptyToast() {
        Espresso.onView(ViewMatchers.withId(R.id.action_done)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_event_name)
    }

    @Test
    fun whenEventTimeNotFormattedToast() {
        Espresso.onView(ViewMatchers.withId(R.id.edt_event_name))
            .perform(ViewActions.typeText("Mast Event"))
        Espresso.onView(ViewMatchers.withId(R.id.action_done)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_select_proper_event_time)
    }

    private fun setTextInTextView(value: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return Matchers.allOf(ViewMatchers.isDisplayed(), ViewMatchers.isAssignableFrom(TextView::class.java))
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
        Espresso.onView(ViewMatchers.withId(R.id.edt_event_name))
            .perform(ViewActions.typeText("Mast Event"))
        Espresso.onView(ViewMatchers.withId(R.id.txt_event_time))
            .perform(setTextInTextView(DateFormatter.getStringFromDate(Date(), DateFormatter.dd_MM_yyyy_HH_mm)))
        Espresso.onView(ViewMatchers.withId(R.id.action_done)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_event_description)
    }

    @Test
    fun checkEventEditingisSuccessfullAndSentBack() {
        Espresso.onView(ViewMatchers.withId(R.id.edt_event_name))
            .perform(ViewActions.typeText("Mast Event"))
        Espresso.onView(ViewMatchers.withId(R.id.txt_event_time))
            .perform(setTextInTextView(DateFormatter.getStringFromDate(Date(), DateFormatter.dd_MM_yyyy_HH_mm)))
        Espresso.onView(ViewMatchers.withId(R.id.edt_event_description))
            .perform(ViewActions.typeText("Mast Event description sample text for long descripition is here."))
        Espresso.onView(ViewMatchers.withId(R.id.action_done)).perform(ViewActions.click())
        ViewMatchers.assertThat(
            mActivityTestRule.activityResult,
            ActivityResultMatchers.hasResultCode(Activity.RESULT_OK)
        )
        ViewMatchers.assertThat(
            mActivityTestRule.activityResult,
            ActivityResultMatchers.hasResultData(IntentMatchers.hasExtraWithKey(AddEventActivity.EVENT_OBJ))
        )

    }

}