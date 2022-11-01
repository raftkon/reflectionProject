/**
 * Adult
 */
public class Adult extends Person {
    boolean isHorny;

    Adult(String first,String last, int age){
        super(first, last, age);
        isHorny = true;
    }
    

    static void cryForSex(){
        System.out.println("I just wanna have sex for fuck's sake!");
    }
}