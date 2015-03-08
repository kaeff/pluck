package net.kaeff.pluck;

import org.junit.Test;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReadmeExampleTest {

    static class Person {
        private String name;
        public Person(String name) { this.name = name; }
        public String getName() { return name; }
    }

    private String name = "John Smith";
    private Person person = new Person(name);

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
