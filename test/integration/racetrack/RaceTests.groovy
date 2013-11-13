package racetrack

import static org.junit.Assert.*
import org.junit.*

class RaceTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testRaceDatesBeforeToday() {
        def lastWeek = new Date() - 7
        def race = new Race(startDate:lastWeek)
        assertFalse "Validation should not succeed",
        race.validate()
        assertTrue "There should be errors",
        race.hasErrors()
    }
}
