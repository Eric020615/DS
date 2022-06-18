package solutions;
import java.util.*;
public class payment_user_input {
    
    public static class Transaction implements Comparable<Transaction> {
        private long time;
        private String id;
        private String tier;

        public Transaction(long time,String id,String tier){
            this.time=time;
            this.id=id;
            this.tier=tier;
        }

        public long getTime() {
            return time;
        }

        public String getId() {
            return id;
        }

        public String getTier() {
            return tier;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTier(String tier) {
            this.tier = tier;
        }

        @Override
        public String toString() {
            return time+" "+id+" "+tier;
        }

        @Override
        public int compareTo(Transaction o) {
            if(this.getTime()>o.getTime())
                return -1;
            else if(this.getTime()<o.getTime())
                return 1;
            else
                return 0;
        }

    }
    
    public static void main(String[] args) {
        LinkedList<String>queue = new LinkedList<>();
        ArrayList<Transaction>input = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        while((word.equalsIgnoreCase("END"))||(word.equalsIgnoreCase("CLEAR"))) {
            if (word.equalsIgnoreCase("END")) {
                System.exit(0);
            }else if (word.equalsIgnoreCase("CLEAR")) {
                word = sc.nextLine();
            }
        }
        String[] element = word.split(" ");
        long time = Long.parseLong(element[0]);
        Transaction t = new Transaction(time, element[1], element[2]);
        input.add(t);
        long startnum = ((time % 10000) / 1000);
        while(true) {
            if (word.equalsIgnoreCase("END")) {
                System.exit(0);
            }
            else{
                word = sc.nextLine();
                if (word.equalsIgnoreCase("CLEAR")) {
                    queue.clear();
                    continue;
                }
                element = word.split(" ");
                if (word.equalsIgnoreCase("END")) {
                    System.exit(0);
                }
                time = Long.parseLong(element[0]);
                Transaction t1 = new Transaction(time, element[1], element[2]);
                input.add(t1);

                long number = ((time % 10000) / 1000);
                if(startnum!=number) {
                    System.out.println("Arrange based on tier system :");
                    setTimeBasedOnTier(input);
                    Collections.sort(input);
                    printTrans(input);
                    for (int i=0;i<input.size();i++){
                        queue.add(input.get(i).getId());
                    }
                    input.clear();
                    System.out.print("Output:");
                    for (int i=0;i<100;i++) {
                        System.out.println(queue.removeFirst());
                    }
                }
                startnum = number;
            }
        }
    }


    public static void printTrans(ArrayList list){
        for(Object e :list){
            System.out.println(e);
        }
    }

    public static void printID(ArrayList list){
        int i=0;
        for(Object e :list){
            if(i%10==0)
                System.out.println("");
            System.out.print(e+" ");
            i++;
        }
    }

    public static void setTimeBasedOnTier(ArrayList list){
        for(int i=0;i< list.size();i++){
            Transaction temp = (Transaction) list.get(i);
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
}
