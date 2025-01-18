package com.lcaohoanq.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioHandler {
    private static AudioInputStream audioInputStream;
    private static Clip clip;

    public void playAudio() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/sound.wav");
            if (inputStream == null) {
                throw new Error("Audio file is missing");
            }
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            byte[] audioData = buffer.toByteArray();

            // Use ByteArrayInputStream to create an AudioInputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0); // Reset the clip position to the start
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void playAudio(String path) {
        try {
            InputStream inputStream = AudioHandler.class.getResourceAsStream(path);
            if (inputStream == null) {
                throw new Error("Audio file is missing");
            }
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            byte[] audioData = buffer.toByteArray();

            // Use ByteArrayInputStream to create an AudioInputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0); // Reset the clip position to the start
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}