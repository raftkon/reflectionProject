/**
 * Person
 */
public class Person {
    String firstName;
    String lastName;
    int age;

    Person(String firstName,String lastName,int age){
        this.age=age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static void shout() {
        System.out.println("WTF IS GOING ONNNNNN!");
    }

    void introduce(){
        System.out.println("Hello friend, my name is "+ firstName+" " +lastName +" and I'm "+age+" years old.");
    }
    

}