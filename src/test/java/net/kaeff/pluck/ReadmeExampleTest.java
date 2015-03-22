package net.kaeff.pluck;

import net.kaeff.pluck.subjects.Person;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReadmeExampleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final String name = "John Smith";
    private final Person person = new Person(name);

    @Test
    public void exampleWithoutPluckUsingAnonymousClass() {
        @SuppressWarnings("all") Function<Person,String> nameFromPersion = new Function<Person, String>() {
            @Override
            public String apply(Person businessObject) {
                return businessObject.getName();
            }
        };

        String plucked = nameFromPersion.apply(person);

        assertThat(plucked, is(name));
    }

    @Test
    public void exampleWithoutPluckUsingLambda() {
        @SuppressWarnings("all") Function<Person,String> nameFromPerson = it -> it.getName();

        String plucked = nameFromPerson.apply(person);

        assertThat(plucked, is(name));
    }

    @Test
    public void shouldPluckParameterlessMethodByName() {
        Function<Person,String> nameFromPerson = Pluck.pluck(Person.class, "getName");

        String plucked = nameFromPerson.apply(person);

        assertThat(plucked, is(name));
    }
}
