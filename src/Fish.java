import java.util.Date;

public class Fish extends Thread{
    private Integer x;
    private Integer y;
    private Integer z;
    private final Integer currentTime;
    private final Integer ageTime;
    private final Boolean sex;
    private Boolean isDead;
    public Fish(Integer x, Integer y, Integer z, Integer currentTime, Integer ageTime, Boolean sex, Boolean isDead){
        this.x = x;
        this.y = y;
        this.z = z;
        this.currentTime = currentTime;
        this.ageTime = ageTime;
        this.sex = sex;
        this.isDead = isDead;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public Boolean getSex() {
        return sex;
    }

    @Override
    public void run() {
        while(true){
            Date date = new Date();
            int current = date.getMinutes() * 60 + date.getSeconds();
            if(currentTime + ageTime < current){
                isDead = true;
                break;
            }else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
