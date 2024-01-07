package audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Audio {

    private static Clip[] clips;
    private static Clip clip;
    private float volume = 1f;

    private static final Map<Integer, String> STATE_FILE_NAMES = new HashMap<>();

    static {
        STATE_FILE_NAMES.put(0, "playstate");
        STATE_FILE_NAMES.put(1, "menustate");
        STATE_FILE_NAMES.put(2, "gameover");
        STATE_FILE_NAMES.put(3, "winstate");
    }

    public Audio() {
        clips = new Clip[STATE_FILE_NAMES.size()];

        for (Map.Entry<Integer, String> entry : STATE_FILE_NAMES.entrySet()) {
            int state = entry.getKey();
            clips[state] = importSound(state);
        }
    }
    public Audio(String path){
        clip = importSound(path);
    }

    private Clip importSound(int state) {
        String filePath = "/audio/" + STATE_FILE_NAMES.get(state) + ".wav";;
        URL url = getClass().getResource(filePath);
        AudioInputStream audio;
        try {
            assert url != null;
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Clip importSound(String filePath) {
        URL url = getClass().getResource(filePath);
        AudioInputStream audio;
        try {
            assert url != null;
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void playSound(int state) {
        stopSound();
        updateVolume(state);
        clips[state].setMicrosecondPosition(0);
        clips[state].loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void playSound() {
        updateVolume();
        clip.setMicrosecondPosition(0);
        clip.start();
    }


    public void stopSound() {
        for (Clip clip : clips) {
            if (clip != null && clip.isActive()) {
                clip.stop();
            }
        }
    }

    private void updateVolume(int state) {
        FloatControl gainControl = (FloatControl) clips[state].getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
    private void updateVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }

    public void setVolume(float volume) {
        this.volume = volume;
        for (Clip clip : clips) {
            if (clip != null) {
                updateVolume(clip);
            }
        }
    }

    private void updateVolume(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}
