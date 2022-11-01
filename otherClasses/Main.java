class Main {
    public static void main(String[] args) {
        Person p = new Person("Gerasimos", "Alysandratos", 29);
        Child c = new Child("Panagiotis", "Aggelis", 3);
        Adult a = new Adult("Petros", "Petropoulos", 40);

        Adult.cryForSex();
        c.wannaShit();
    }
}