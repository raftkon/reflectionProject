
/**
 * Animal
 */
public class Animal {
    String name;
    int age;
    boolean hasEaten;

    Animal(String name,int age){
        this.name=name;
        this.age = age;
        hasEaten = false;
    }
    
    static void askWalk(){
        System.out.println("I wanna go for a walk man!");
    }
} 