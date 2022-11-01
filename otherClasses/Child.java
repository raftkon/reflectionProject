class Child extends Person{
    boolean hasPoop;
    Child(String first,String last,int age){
        super(first, last, age);
        hasPoop = false;
    }

    void wannaShit(){
        System.out.println("AAAAAAAAAAAAAAAA I WANNA SHIT, I HAVE SHIT IN MY DIAPER!");
    } 
}