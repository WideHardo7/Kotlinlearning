package com.example.kotlinlearning


import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import com.example.kotlinlearning.view.fragment.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kotlinlearning.databinding.HomeBinding
import com.example.kotlinlearning.view.activity.MainActivity
import com.example.kotlinlearning.view.activity.MainActivity2

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard as viewActionsCloseSoftKeyboard

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)

class MainButtonsTest {
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun testBottoneVar() {
        onView(withId(R.id.bottone_variabili)).perform(click())
        onView(withId(R.id.quiz_nome_argomento)).check(matches(isDisplayed()))
        onView(withId(R.id.b_quiz)).perform(click())
        for(i in 0..3){
            onView(withId(R.id.b_risp1)).perform(click())
        }
        onView(withId(R.id.risposta_da_tastiera)).perform(typeText("var"), viewActionsCloseSoftKeyboard())
        onView(withId(R.id.b_conferma)).perform(click())
        onView(withId(R.id.immagine_punteggio)).check(matches(isDisplayed()))
        onView(withId(R.id.b_home)).perform(click())
        onView(withId(R.id.bottone_variabili)).check(matches(isDisplayed()))
    }
   /*@Test
    fun testBottoneFun(){
        onView(withId(R.id.bottone_funzioni)).check(matches(isEnabled()))

    }*/
}

class DrawerTest{
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testDrawer(){
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        onView(withId(R.id.navView)).perform(NavigationViewActions.navigateTo(R.id.glossaryFragment))
        Espresso.pressBack()
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        onView(withId(R.id.navView)).perform(NavigationViewActions.navigateTo(R.id.achievementFragment))
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