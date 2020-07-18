package hello

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HelloTest {

    @Test
    fun whenAdding1and3_thenAnswerIs4() {
        Assertions.assertEquals(4, 1 + 3)
    }
}