package com.neonObf.gui;

import com.neonObf.gui.music.MusPlay;

public class Main {
    public static String[] names = new String[] {
            "Crutch_and_bike.mp3",
            "This_is_Java.mp3",
    };
    public static MusPlay mus;
    
    public static void main(final String[] args) throws Throwable {
        new GUI().init();
        
        mus = new MusPlay(names[new java.util.Random().nextInt(names.length)]);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                mus.close();
            }
        });
    }
}
