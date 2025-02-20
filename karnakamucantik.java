import java.util.ArrayList;
import java.util.List;

class LyricSinger implements Runnable {
    private final String lyric;
    private final long delay;
    private final long speed;
    
    public LyricSinger(String lyric, long delay, long speed) {
        this.lyric = lyric;
        this.delay = delay;
        this.speed = speed;
    }
    
    private void animateText(String text, long speed) {
        synchronized (System.out) {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println();
        }
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            animateText(lyric, speed);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class karnakamucantik {
    public static void main(String[] args) {
        String[][] lyrics = {
            {"\nKarna kamu cantik", "90"},
            {"Kan kuberi segalanya apa yang kupunya", "90"},
            {"Dan hatimu baik", "100"},
            {"Sempurnalah duniaku saat kau di sisiku\n", "100"},
            {"Bukan karna make up di wajahmu", "90"},
            {"Atau lipstik merah itu", "90"},
            {"Lembut hati tutur kata", "80"},
            {"Terciptalah cinta yang kupuja\n", "100"}
        };
        
        long[] delays = {300, 3400, 7400, 10500, 14500, 18000, 21900, 24400};
        
        List<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < lyrics.length; i++) {
            String lyric = lyrics[i][0];
            long speed = Long.parseLong(lyrics[i][1]);
            Thread t = new Thread(new LyricSinger(lyric, delays[i], speed));
            threads.add(t);
            t.start();
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}