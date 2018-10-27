package app.novan.bolamani.com.bolic.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import app.novan.bolamani.com.bolic.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activiyRule=ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour(){
        onView(withId(navigation)).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withId(matchPrevRecyclerView)).check(matches(isDisplayed()))
        onView(withId(matchPrevRecyclerView)).perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(matchPrevRecyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(3,click()))
        Thread.sleep(5000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added To Favorites")).check(matches(isDisplayed()))
        pressBack()

        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        Thread.sleep(5000)
    }
}