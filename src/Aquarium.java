import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Aquarium extends Thread{
    private List<Fish> fish = new ArrayList<>();

    public synchronized List<Fish> getFish() {
        return fish;
    }

    public synchronized void setFish(List<Fish> fish) {
        this.fish = fish;
    }

    @Override
    public void run() {

         Random random = new Random();
        final Integer x = random.nextInt(1,100);
        final Integer y = random.nextInt(1,100);
        final Integer z = random.nextInt(1,100);
        for (int i = 0; i < 100; i++) {
            Date date = new Date();
            int currentTime = date.getMinutes() * 60 + date.getSeconds();
            Fish newFish = new Fish(random.nextInt(x), random.nextInt(y), random.nextInt(z), currentTime, random.nextInt(1,25), random.nextBoolean(), false);
            newFish.start();
            getFish().add(newFish);
        }
        System.out.println("Baliqlar soni:::::::::::::: " + getFish().size() + "\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        do {
            List<Fish> f = new ArrayList<>();
            for (int i = 0; i < getFish().size(); i++) {
                for (int j = i + 1; j < getFish().size(); j++) {
                    Fish a = getFish().get(i);
                    Fish b = getFish().get(j);
                    if (!(a.getSex().equals(b.getSex())) && a.getX().equals(b.getX()) && a.getY().equals(b.getY()) && a.getZ().equals(b.getZ())) {
                        for (int k = 0; k < random.nextInt(random.nextInt(2, 100)) * 2; k++) {
                            Date date = new Date();
                            int currentTime = date.getMinutes() * 60 + date.getSeconds();
                            Fish newFish = new Fish(random.nextInt(100), random.nextInt(100), random.nextInt(100), currentTime, random.nextInt(1,25), random.nextBoolean(), false);
                            newFish.start();
                            f.add(newFish);

                        }
                    }
                }
            }
            int deadCounter = 0;
            for (int i = 0; i < getFish().size(); i++) {
                if (getFish().get(i).getDead()) {
                    deadCounter++;
                    getFish().remove(i);
                    i--;
                    continue;
                }
                fish.get(i).setX(random.nextInt(10));
                fish.get(i).setY(random.nextInt(10));
                fish.get(i).setZ(random.nextInt(10));

            }
            System.out.println("Ulganlar soni : " + deadCounter);
            System.out.println("Tug'ilishdan oldingi soni: " + getFish().size());
            System.out.println("Tug'ilganlar soni: " + f.size() + "\n");
            getFish().addAll(f);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Baliqlar soni:::::::::::::: " + getFish().size() + "\n");
        } while (getFish().size() <= 20000);
    }
}
