package com.end.testingUtils.rule

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.*

class LocaleTestRule : TestWatcher() {

    private lateinit var oldLocale: Locale

    override fun starting(description: Description?) {
        super.starting(description)

        oldLocale = Locale.getDefault()
        Locale.setDefault(Locale.ENGLISH)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Locale.setDefault(oldLocale)
    }
}
