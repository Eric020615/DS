package solutions;
import java.util.*;
import java.io.*;
public class Payment {
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
    
    public static class MyQueue<E>  {
        private java.util.LinkedList<E> list = new java.util.LinkedList<>();

        public MyQueue(ArrayList e){
            this.list=new LinkedList<E>(e);
        }


        public MyQueue(E[] e){
            this.list=new LinkedList<E>(Arrays.asList(e));
        }

        public MyQueue(){
            this.list=new LinkedList<E>();
        }

        public void enqueue(E e){
            list.addLast(e);
        }

        public void enqueue(ArrayList e){
            list.addLast((E) e);
        }


        public E dequeue(){
            return list.removeFirst();
        }

        public E getElement(int i){
            return list.get(i);
        }

        public E peek(){
            return list.peek();
        }

        public int size(){
            return list.size();
        }

        public E get(int i){
            return list.get(i);
        }

        public boolean contains(E e){
            return list.contains(e);
        }

        public boolean isEmpty(){
            return list.isEmpty();
        }

        public E poll(){
            E element = list.peek();
            list.removeFirst();
            return element;
        }

        public void set(int i, E element){
            list.set(i, element);
        }

        public void clear(){
            list.clear();
        }
        public void toPrintQueue(){
            for(int i=0;i< list.size();i++) {
                System.out.println((i+1)+" "+list.get(i));
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        long StartTime = new Date(). getTime(); //some tasks long lEndTime = new Date(). getTime(); long difference = lEndTime - lStartTime; System.
        LinkedList<String>queue = new LinkedList<>();
        ArrayList<Transaction>input = new ArrayList<>();
        File dir = new File("DS/solutions/payment/cases");
        File[]files=dir.listFiles();

        if (files[0].isFile()){
            Scanner sc=null;
            String word;

            try{
                sc=new Scanner(new FileInputStream(files[0]));
                word=sc.nextLine();
                String[] element = word.split(" ");
                long time = Long.parseLong(element[0]);
                Transaction t = new Transaction(time, element[1], element[2]);
                input.add(t);

                long startnum =(( time % 10000)/1000);

                while(sc.hasNextLine()){
                    word=sc.nextLine();
                    element=word.split(" ");
                    time =Long.parseLong(element[0]);
                    Transaction t1 = new Transaction(time,element[1],element[2]);
                    input.add(t1);

                    long number =(( time % 10000)/1000);
                    if(startnum!=number) {
                        System.out.println("Arrange based on tier system :");
                        setTimeBasedOnTier(input);
                        Collections.sort(input);
                        printTrans(input);
                        for (int i=0;i<input.size();i++){
                            queue.add(input.get(i).getId());
                        }
                        input.clear();
                        System.out.println("Output:");
                        for (int i=0;i<100;i++) {
                            System.out.println((i+1)+")"+queue.removeFirst());
                        }
                    }
                    startnum = number;
                }
            }catch(IOException e){
                System.out.println("File got some problems.");
            }finally {
                if (sc!=null){
                    sc.close();
                }
            }
        }
        long EndTime = new Date(). getTime();
        long difference = EndTime - StartTime;
        System.out.println("The execution time is " +difference+ "ms");

    }

    public static void printTrans(ArrayList list){
        // tem.out.println("The 100 transaction in the list :");
        for(Object e :list){
            System.out.println(e);
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
