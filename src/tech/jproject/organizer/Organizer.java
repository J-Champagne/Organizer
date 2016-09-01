package tech.jproject.organizer;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by jackchampagne on 8/31/16.
 * V Alpha 0.0.1
 *
 * Application will
 * + organize projects and daily homework
 * + show school day
 * + show classes for the current day
 *
 * STRETCH GOALS:
 * + Local and CLoud saving -- Note:
    must get entire file inputstream before out:
    must flush buffered output stream

 * + Server to hold cloud saves.
 */

public class Organizer extends JFrame implements Runnable {

    public static int width = 900;
    public static int height = width / 12 * 9;

    public JFrame organizerFrame;
    public PrintWriter localSave;

    private static String filename = "class-organizer.organi";
    private boolean running = false;
    private Thread thread;

    private Organizer () {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);

        organizerFrame = new JFrame();


        try {
            localSave = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
        } catch (IOException e) {
            System.out.println("Failed to create output file");
            e.printStackTrace();
        }
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Organizer");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Error in main stop();");
            e.printStackTrace();
        }
    }

    public void run() {

    }

    public static void main(String[] args) {

        Organizer organizer = new Organizer();
        organizer
        organizer.start();

        //organizer.localSave.close();
        organizer.stop();
    }
}
