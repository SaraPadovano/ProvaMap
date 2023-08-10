/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.parser;

/**
 *
 * @author 39388
 */
public class GameTimer implements Runnable {
    private static boolean isRunning = true;
    private long startTime;
    private static long totalGameTime = 0;
    private static long currentTime = 0;

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        while (isRunning) {
            currentTime = System.currentTimeMillis();
            totalGameTime = currentTime - startTime;

            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                System.out.println("Errore durante l'esecuzione.\n");
            }
        }
    }

    public static void stopTimer() {
        isRunning = false;
    }

    public static long getTotalGameTime() {
        return totalGameTime;
    }
}
