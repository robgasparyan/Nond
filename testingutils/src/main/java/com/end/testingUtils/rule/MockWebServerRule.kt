package com.end.testingUtils.rule

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule(val server: MockWebServer = MockWebServer()) : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        server.start()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        server.shutdown()
    }
}

