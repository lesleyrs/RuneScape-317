import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class SoundPlayer implements Runnable {

	private AudioInputStream stream;
	private DataLine.Info info;
	private Clip sound;

	private InputStream soundStream;
	private Thread player;
	private int delay;

	/**
	 * Initializes the sound player.
	 * @param stream
	 * @param level
	 * @param delay
	 */
	public SoundPlayer(InputStream stream, int delay) {
		this.soundStream = stream;
		this.delay = delay;
		player = new Thread(this);
		player.start();
	}

	/**
	 * Plays the sound.
	 */
	@Override
	public void run() {
		try {
			stream = AudioSystem.getAudioInputStream(soundStream);
			info = new DataLine.Info(Clip.class, stream.getFormat());
			sound = (Clip) AudioSystem.getLine(info);
			sound.open(stream);
			FloatControl volumeControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
			volumeControl.setValue(20.0f * (float)Math.log10(Signlink.wavevol / 100.0));
			if (delay > 0) {
				Thread.sleep(delay);
			}
			sound.start();
			while (sound.isActive()) {
				Thread.sleep(250);
			}
			Thread.sleep(10000);
			sound.close();
			stream.close();
			player.interrupt();
		} catch (Exception e) {
			player.interrupt();
			e.printStackTrace();
		}
	}
}
