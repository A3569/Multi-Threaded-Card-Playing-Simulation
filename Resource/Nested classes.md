# Nested classes
- Nested classes are classes defined within another class.
- Nested classes are primarily used to logically group classes that will only be used in one place, improve encapsulation, and make the code more readable and maintainable.
- **Encapsulation**: Nested classes can be used to hide the details of their implementation.

## Static vs Non-static:
- A **static** nested class doesn't have access to instance variables/methods of the outer class unless explicitly passed.
- A **non-static** inner class always has a reference to its enclosing class instance

### There are three types of nested classes in Java:

#### 1. Static Nested Class
- Declared with the static keyword.
- It can access the static members (fields and methods) of the enclosing class but cannot directly access non-static members.
- It doesn't require an instance of the enclosing class to be instantiated.

~~~
class OuterClass {
    static int staticVar = 10;

    static class StaticNestedClass {
        void display() {
            System.out.println("Static variable: " + staticVar);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
        nested.display();
    }
}
~~~

#### 2. Inner Class (Non-static Nested Class)
- An inner class is associated with an instance of its enclosing class.
- It can directly access both static and non-static members of the outer class.
- To create an instance, you need an instance of the enclosing class.

~~~
class OuterClass {
    int outerVar = 20;

    class InnerClass {
        void display() {
            System.out.println("Outer variable: " + outerVar);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.display();
    }
}
~~~

#### 3. Anonymous Inner Class
- A class without a name, typically used for implementing interfaces or extending classes inline.
- Commonly used when you need to provide an implementation of a class or interface for one-time use.
- These are especially useful for event handling or callbacks

~~~
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello, World!");
            }
        };
        greeting.sayHello();
    }
}
~~~
