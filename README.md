# pluck

[![License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](http://www.opensource.org/licenses/MIT)
[![Build Status](http://img.shields.io/travis/kaeff/pluck.svg?style=flat&branch=master)](https://travis-ci.org/kaeff/pluck)
[![Coverage Status](https://img.shields.io/coveralls/kaeff/pluck.svg?style=flat)](https://coveralls.io/r/kaeff/pluck?branch=master)

A microlibrary for stressless pluck functions in Java.

**WARNING**: This is merely a [breakable toy](http://redsquirrel.com/dave/work/a2j/patterns/BreakableToys.html) right now. Don't use it.

### Setup

Releases are published to [bintray jcenter](https://bintray.com/bintray/jcenter) (package appear immediately after release)
and then to maven central (require few days after release to be published).

<!---
[![Download](https://api.bintray.com/packages/kaeff/pluck/pluck/images/download.svg) ](https://bintray.com/kaeff/pluck/pluck/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.kaeff/pluck/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/net.kaeff/pluck)
-->

Maven:

```xml
<dependency>
  <groupId>net.kaeff</groupId>
  <artifactId>pluck</artifactId>
  <version>0.0.1</version>
</dependency>
```

Gradle:

```groovy
compile 'net.kaeff:pluck:0.0.1'
```

### Usage

Plucking is an idiom known from other languages. Pluck is a utility used to invoke a method on an object within functional composition.

Use the static `pluck()` function to create a `Function` that represents a method call on an object.

Probably, the whole purpose of this lib is defied by using [method references](http://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html).

### Plucking a query method

A query method (here) is a side effect-free, parameterless method. You'll often find yourself using these when mapping or transforming an object as part of a functional pipeline.

Say we have an entity like this:

```java
class BusinessObject {
    private String name;
    public BusinessObject(String name) { this.name = name; }
    public String getName() { return name; }
}
```

In order use `getName()` as a Function, an anonymous class or lambda function is required:

```java
Function<Person,String> nameFromPerson = new Function<Person, String>() {
            @Override
            public String apply(Person businessObject) {
                return businessObject.getName();
            }
        };
```

Eliminate the need for an anonymous function subclass using `pluck()`:

```java
String name = Optional.of(new BusinesObject()))
.transform(pluck(BusinessObject.class, "getName"))
.orNull();
```