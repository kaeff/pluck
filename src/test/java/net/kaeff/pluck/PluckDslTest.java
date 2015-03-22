package net.kaeff.pluck;

import net.kaeff.pluck.subjects.Person;
import org.junit.Test;

import java.util.function.Function;

import static net.kaeff.pluck.PluckDsl.from;
import static net.kaeff.pluck.PluckDsl.pluck;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PluckDslTest {

    private final String name = "John Smith";
    private final Person person = new Person(name);

    @Test
    public void shouldPluckParameterlessMethodBySampleInvocation() {
        Function<Person,String> nameFromPerson = pluck(from(Person.class).getName());

        String plucked = nameFromPerson.apply(person);

        assertThat(plucked, is(name));
    }

}
