//import some libraries needed
import java.util.*;
//Payment class
public class Payment {
    //Main method
    public static void main(String[] args) {
        //Create a LinkedList called queue with String
        LinkedList<String>queue = new LinkedList<>();
        //Create a ArrayList called input with Transaction Instance Type
        ArrayList<Payment.Transaction>input = new ArrayList<>();
        //Create an instance of Scanner
        Scanner sc = new Scanner(System.in);
        //Get the number of test case
        int num = Integer.valueOf(sc.nextLine());
        //Test case looping
        for(int count=0;count<num;count++){
            //Get the transaction
            String word = sc.nextLine();
            //If the word equals to END and CLEAR
            while((word.equalsIgnoreCase("END"))||(word.equalsIgnoreCase("CLEAR"))) {
                if (word.equalsIgnoreCase("END")) {
                    word = sc.nextLine();
                    continue;
                }else if (word.equalsIgnoreCase("CLEAR")) {
                    sc.nextLine();
                    word = sc.nextLine();
                }
            }
            //Split the String
            String[]element = word.split(" ");
            //Assign to the time variable
            long time = Long.parseLong(element[0]);
            //Pass argument and create an object of Transaction
            Payment.Transaction t = new Payment.Transaction(time, element[1], element[2]);
            //input elements into ArrayList
            input.add(t);
            //Calculate the starting number
            long startnum = ((time % 10000) / 1000);
            //Loop
            while(true) {
                //If equals to END
                if (word.equalsIgnoreCase("END")) {
                    word = sc.nextLine();
                    continue;
                }
                else{
                    //Get the String
                    word = sc.nextLine();
                    //If equals to CLEAR
                    if (word.equalsIgnoreCase("CLEAR")) {
                        queue.clear();
                        sc.nextLine();
                        word = sc.nextLine();
                        continue;
                    }
                    //Split the String
                    element = word.split(" ");
                    if (word.equalsIgnoreCase("END")) {
                        word = sc.nextLine();
                    }
                    //Assign element[0] to variable Time
                    time = Long.parseLong(element[0]);
                    //Pass argument and create an object of Transaction
                    Payment.Transaction t1 = new Payment.Transaction(time, element[1], element[2]);
                    //input elements into ArrayList
                    input.add(t1);
                    //Calculate the starting number
                    long number = ((time % 10000) / 1000);\
                    //determine if there is the change of the time
                    if(startnum!=number) {
                        //Call for method
                        setTimeBasedOnTier(input);
                        //Call for method
                        Collections.sort(input);
                        //Call for method
                        printTrans(input);
                        //Looping to get data
                        for (int i=0;i<input.size();i++){
                            queue.add(input.get(i).getId());
                        }
                        //Clear it
                        input.clear();
                        //Print it and remove from queue within 100 elements
                        for (int i=0;i<100;i++) {
                            System.out.println(queue.removeFirst());
                        }
                    }
                    startnum = number;
                }
            }
        }
    }
    //Method to Print the elements inside ArrayList
    public static void printTrans(ArrayList list){
        for(Object e :list){
            System.out.println(e);
        }
    }
    //Method to Print the elements inside ArrayList
    public static void printID(ArrayList list){
        int i=0;
        for(Object e :list){
            if(i%10==0)
                System.out.println("");
            System.out.print(e+" ");
            i++;
        }
        System.out.println();
    }
    //Method to set time time based on Tier by receiving ArrayList
    public static void setTimeBasedOnTier(ArrayList list){
        for(int i=0;i< list.size();i++){
            Payment.Transaction temp = (Payment.Transaction) list.get(i);
            if(temp.getTier().equalsIgnoreCase("BRONZE")){
                long change =temp.getTime()+0;
                temp.setTime(change);
            }else if(temp.getTier().equalsIgnoreCase("SILVER")){
                long change =temp.getTime()+0000000001000;
                temp.setTime(change);
            }else if(temp.getTier().equalsIgnoreCase("GOLD")){
                long change =temp.getTime()+0000000002000;
                temp.setTime(change);
            }else{
                long change =temp.getTime()+0000000003000;
                temp.setTime(change);
            }

        }
    }
    //Transaction Class
    public static class Transaction implements Comparable<Transaction> {
        //Create instance variable
        private long time;
        private String id;
        private String tier;
        //Create a constructor
        public Transaction(long time,String id,String tier){
            this.time=time;
            this.id=id;
            this.tier=tier;
        }
        //Getter method for time
        public long getTime() {
            return time;
        }
        //Getter method for id
        public String getId() {
            return id;
        }
        //Getter method for tier
        public String getTier() {
            return tier;
        }
        //Mutator method for time
        public void setTime(long time) {
            this.time = time;
        }
        //Mutator method for id
        public void setId(String id) {
            this.id = id;
        }
        //Mutator method for tier
        public void setTier(String tier) {
            this.tier = tier;
        }
        //toString method        
        @Override
        public String toString() {
            return time+" "+id+" "+tier;
        }
        //compareTo method
        @Override
        public int compareTo(Payment.Transaction o) {
            if(this.getTime()>o.getTime())
                return -1;
            else if(this.getTime()<o.getTime())
                return 1;
            else
                return 0;
        }
    }
}
