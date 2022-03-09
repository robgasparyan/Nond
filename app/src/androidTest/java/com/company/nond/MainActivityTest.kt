package com.company.nond


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun testActivity_inView() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun ensureTextChangesWork() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            it.hideLoading()
        }
        onView(withId(R.id.progress)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }

}