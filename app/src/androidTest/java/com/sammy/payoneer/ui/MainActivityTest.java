package com.sammy.payoneer.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.sammy.payoneer.R;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import okhttp3.OkHttpClient;

@HiltAndroidTest
public class MainActivityTest{

    @Rule
    public HiltAndroidRule rule = new HiltAndroidRule(this);
    @Rule
    public ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(MainActivity.class);

    @Inject
    public OkHttpClient client;

    @Before
    public void setUp(){
        rule.inject();
        IdlingRegistry.getInstance().register(
                OkHttp3IdlingResource.create(
                        "okhttp",
                        client
                )
        );
    }

    @After
    public void tearDown() {
        activityScenarioRule.getScenario().close();
    }


    @Test
    public void paymentMethodsAreDisplayed() {
        onView(ViewMatchers.withId(R.id.payments_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,
                        click()));
    }

}