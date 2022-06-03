public class Transaction implements Comparable<Transaction> {
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
