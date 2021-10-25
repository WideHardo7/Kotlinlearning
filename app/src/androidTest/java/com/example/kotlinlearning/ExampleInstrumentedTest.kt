package com.example.kotlinlearning


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kotlinlearning.view.activity.MainActivity
import com.example.kotlinlearning.view.activity.MainActivity2

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.kotlinlearning", appContext.packageName)
    }
}

class MainButtonsTest {
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun testBottoneVar() {
        onView(withId(R.id.bottone_variabili)).perform(click())
    }
    @Test
    fun testBottoneFun(){
        onView(withId(R.id.bottone_funzioni)).perform(click())
    }
}

class TestCon {
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity2> = ActivityScenarioRule(MainActivity2::class.java)
    @Test
    fun testBottoneSalta(){
        onView(withId(R.id.b_Salta)).perform(click())
    }
    @Test
    fun testBottonePro(){
        onView(withId(R.id.b_Procedi)).perform(click())
    }

}