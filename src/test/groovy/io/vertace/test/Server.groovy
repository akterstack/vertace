package io.vertace.test

import io.vertx.ext.unit.TestContext
import io.vertx.ext.unit.junit.VertxUnitRunner
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(VertxUnitRunner)
class Server  {

    @Test
    void shouldPass(TestContext context) {
        context.assertFalse(true)
    }

}
