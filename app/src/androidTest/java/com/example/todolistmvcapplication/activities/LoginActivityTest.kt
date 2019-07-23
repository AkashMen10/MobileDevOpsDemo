package com.example.todolistmvcapplication.activities

import android.support.annotation.StringRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.example.todolistmvcapplication.R
import com.example.todolistmvcapplication.utils.Utils
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import android.widget.TextView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not


class LoginActivityTest {

    @get:Rule
    val mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    private fun toastIsDisplayed(@StringRes stringId: Int) {
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withText(stringId))
            .inRoot(RootMatchers.withDecorView(not(Matchers.`is`(mActivityTestRule.activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun toastIsNotDisplayed(@StringRes stringId: Int) {
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withText(stringId))
            .inRoot(RootMatchers.withDecorView(not(Matchers.`is`(mActivityTestRule.activity.window.decorView))))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun whenEmailEmptyToast() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_email)
    }

    fun getText(matcher: Matcher<View>): String {
        val stringHolder = ArrayList<String>()
        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView //Save, because of check in getConstraints()
                stringHolder[0] = tv.text.toString()
            }
        })
        return stringHolder[0]
    }

    @Test
    fun whenEmailInvalidToast() {
        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.typeText("Invalid.email"), closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_valid_email)

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.clearText())

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.typeText("invalid.email@"), closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_valid_email)

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.clearText())

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.typeText("invalid@email."), closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_valid_email)

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.clearText())

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.typeText("invalid@email@"), closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_valid_email)
    }

    @Test
    fun whenEmailValidTest() {
        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.clearText())

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.typeText("valid@email.com"), closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_password)
    }


    @Test
    fun whenPasswordEmptyToast() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        toastIsDisplayed(R.string.plz_enter_password)
    }

    @Test
    fun whenPasswordLengthInvalidToast() {
        Espresso.onView(ViewMatchers.withId(R.id.edt_password))
            .perform(ViewActions.typeText("123"), closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.edt_password))
            .perform(ViewActions.clearText())

        Espresso.onView(ViewMatchers.withId(R.id.edt_password))
            .perform(ViewActions.typeText("12345"), closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())

        toastIsDisplayed(R.string.password_length_should_be_atleast_6)
    }

    @Test
    fun whenPasswordLengthValidToast() {

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.clearText())

        Espresso.onView(ViewMatchers.withId(R.id.edt_email))
            .perform(ViewActions.typeText("valid@email.com"), closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.edt_password))
            .perform(ViewActions.typeText("123456"), closeSoftKeyboard())

        //check if MainActivity starts
        Intents.init()
        Intents.intending(IntentMatchers.toPackage("com.example.todolistmvcapplication.activities.MainActivity"))
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())

    }

}