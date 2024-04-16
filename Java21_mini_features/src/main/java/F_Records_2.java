import java.util.Objects;
// Records: more on constructor, custom method, variables, validation

public class F_Records_2 {
    record Person(String firstName, String lastName, Integer age) {

        // variable: Note that, instance field are final, but the variables are not forced to be final.
        // We can update defaultAge's value but not firstName
        private static Integer defaultAge = 0;

        // Custom method
        String getFullName(){
            // firstName = "test name"; - not allowed as the instance fields are final by default
            // defaultAge = 50; - allowed as it is not an instance field.
            return firstName + " " + lastName;
        }

        // PRE-CONSTRUCTOR: This is like a pre-processor of the default constructor
        // In this section, property are not also final as the object is not created yet.
        // So we can set values for instance fields!
        public Person {
            // VALIDATION
            validate(firstName, "First name");
            validate(lastName, "Last name");
            // DEFAULT (min, max too)
            if (Objects.isNull(age)) {
                // Setting value for instance field is allowed.
                age = defaultAge;
            }
        }

        // HELPER METHOD for validation
        private void validate(String value, String param){
            if (Objects.isNull(value) || value.isEmpty() || value.isBlank()) {
                throw new IllegalArgumentException(param + " must not be null or empty.");
            }
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("MD", "Feeham", 80);
        System.out.println(person1.getFullName()); // prints: MD Feeham

        Person person2 = new Person("MD", "Feeham", null);
        System.out.println(person2.toString()); // prints: Person[firstName=MD, lastName=Feeham, age=0]

        Person person3 = new Person(null, null, null); // Error
    }
}

