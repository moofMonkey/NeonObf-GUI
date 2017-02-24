package com.neonObf.gui.music;

import java.io.InputStream;

import javazoom.jl.player.Player;

public class MusPlay {
	private Player player;
	private InputStream is;

	// constructor that takes the name of an MP3 file
	public MusPlay(String filename) {
            play(filename);
	}

	public void close() {
            if (player != null)
		player.close();
	}

	// play the MP3 file to the sound card
	public void play(String filename) {
		try {
                    is = MusPlay.class.getResourceAsStream(filename);
                    player = new Player(is);
		} catch (Exception e) {
                    System.out.println("Problem playing file " + filename);
                    System.out.println(e);
		}

		// run in new thread to play in background
		new Thread() {
                    @Override
                    public void run() {
                    	try {
                            player.play();
                            while (true) {
                                if (player.isComplete()) {
                                    player.close();
                                    is = getClass().getResourceAsStream(filename);
                                    player = new Player(is);
                                    player.play();
                                }
                                Thread.sleep(player.getPosition() * 6);
                            }
                        } catch (Throwable t) {
                            System.out.println(t);
                        }
                    }
		}.start();
	}
}
