package Cafe.Customer.Actions;

import SettingsData.Data;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * This class is responsible for simulating playing the piano.
 * Implements the customerAction class.
 */
public class PlayPiano implements CustomerAction {
    private final Semaphore pianoPermits = new Semaphore(2, true);
    Random random = new Random();

    /**
     * Uses a semaphore to only allow two Customer threads to access the critical section.
     * If Customer is unable to gain access to the critical section, the thread will be placed into a queue.
     */
    @Override
    public void executeAction() {
        try {
            if (pianoPermits.availablePermits() == 0) {
                System.out.println(Thread.currentThread().getName() + " is waiting to play piano.");
            }
            pianoPermits.acquire();
            System.out.println(Thread.currentThread().getName() + " is playing piano.");
            Thread.sleep(random.nextInt(Data.getWaitingTime()));
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " has finished playing piano. \n");
            pianoPermits.release();
        }
    }
}
