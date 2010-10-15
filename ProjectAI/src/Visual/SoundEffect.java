/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visual;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public enum SoundEffect {
    LASERSHOOT("QuakeRailGun.wav"),
    EXPLOSION("BombExplosion.wav"),
    TURNING("TurningSound2.wav");

    static boolean Mute;
    static CGame Game;
    private float DefaultSampleRate = 1;
    // Nested class for specifying volume
    private FloatControl fc;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;
    static  void SetDeltaSampleRate(float delta)
    {
        for (int i = 0; i < values().length;++i) {
            values()[i].fc.shift(values()[i].DefaultSampleRate, values()[i].DefaultSampleRate*delta, 0);
        }
    }
    // Constructor to construct each element of the enum with its own sound file.
    SoundEffect(String soundFileName) {
        try {
            // Use URL (instead of File) to read from disk and JAR.
            URL url = this.getClass().getResource("/Resources/"+soundFileName);
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
            fc = (FloatControl) clip.getControl(FloatControl.Type.SAMPLE_RATE);
            DefaultSampleRate = fc.getValue();
            
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void stop() {
        clip.stop();
    }
    public void play() {

        if ((clip.getMicrosecondPosition() > 200000)||(!clip.isRunning())&&(!Mute))
        {
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }


    // Optional static method to pre-load all the sound files.
    static void init() {
      values(); // calls the constructor for all the elements
    }
    }
