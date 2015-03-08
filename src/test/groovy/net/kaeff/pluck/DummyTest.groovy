package net.kaeff.pluck;

/**
 * Dummy test.
 *
 * @author Klaus Fl
 * @since 8.03.2015
 */
class DummyTest extends AbstractTest {

    def "Check something important"() {

        when: "do something"
        Integer checkAssignment = 1
        then: "check result"
        checkAssignment == 1
    }
}
