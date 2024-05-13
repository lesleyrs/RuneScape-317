// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   signlink.java

import java.io.*;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

public class Signlink implements Runnable {

    public static final RandomAccessFile[] cache_idx = new RandomAccessFile[5];
    public static final int clientversion = 317;
    public static int uid;
    public static int storeid = 32;
    public static RandomAccessFile cache_dat = null;
    public static boolean active;
    public static int threadliveid;
    public static String dnsreq = null;
    public static String dns = null;
    public static int savelen;
    public static String savereq = null;
    public static byte[] savebuf = null;
    public static boolean midiplay;
    public static int midipos;
    public static String midi = null;
    public static int midivol = 127;
    public static boolean midifade;
    public static boolean waveplay;
    public static int wavepos;
    public static String wave = null;
    public static int wavevol = 100;
    public static boolean reporterror = true;
    public static Sequencer music = null;
    public static Sequence sequence = null;
    public static Synthesizer synthesizer = null;

    public static void startpriv() {
        threadliveid = (int) (Math.random() * 99999999D);

        if (active) {
            try {
                Thread.sleep(500L);
            } catch (Exception ignored) {
            }
            active = false;
        }

        dnsreq = null;
        savereq = null;
        Thread thread = new Thread(new Signlink());
        thread.setDaemon(true);
        thread.start();

        while (!active) {
            try {
                Thread.sleep(50L);
            } catch (Exception ignored) {
            }
        }
    }

    public static String findcachedir() {
        String[] as = {"c:/windows/", "c:/winnt/", "d:/windows/", "d:/winnt/", "e:/windows/", "e:/winnt/", "f:/windows/", "f:/winnt/", "c:/", System.getProperty("user.home") + "/", "/tmp/", "", "c:/rscache", "/rscache"};
        if ((storeid < 32) || (storeid > 34)) {
            storeid = 32;
        }
        String s = ".file_store_" + storeid;
        for (String a : as) {
            try {
                if (a.length() > 0) {
                    File file = new File(a);
                    if (!file.exists()) {
                        continue;
                    }
                }
                File file1 = new File(a + s);
                if (file1.exists() || file1.mkdir()) {
                    return a + s + "/";
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    public static int getuid(String s) {
        Path path = Paths.get(s + "uid.dat");

        try {
            File file = new File(s + "uid.dat");

            if (!file.exists() || (file.length() < 4L)) {
                DataOutputStream out = new DataOutputStream(Files.newOutputStream(path));
                out.writeInt((int) (Math.random() * 99999999D));
                out.close();
            }
        } catch (Exception ignored) {
        }

        try {
            DataInputStream in = new DataInputStream(Files.newInputStream(path));
            int uid = in.readInt();
            in.close();
            return uid + 1;
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static synchronized void dnslookup(String dns) {
        Signlink.dns = dns;
        dnsreq = dns;
    }

    public static synchronized boolean wavesave(byte[] src, int len) {
        if (len > 2000000) {
            return false;
        }
        if (savereq != null) {
            return false;
        } else {
            wavepos = (wavepos + 1) % 5;
            savelen = len;
            savebuf = src;
            waveplay = true;
            savereq = "sound" + wavepos + ".wav";
            return true;
        }
    }

    public static synchronized boolean wavereplay() {
        if (savereq != null) {
            return false;
        } else {
            savebuf = null;
            waveplay = true;
            savereq = "sound" + wavepos + ".wav";
            return true;
        }
    }

    public static synchronized void midisave(byte[] data, int len) {
        if (len > 0x1e8480) {
            return;
        }
        if (savereq == null) {
            midipos = (midipos + 1) % 5;
            savelen = len;
            savebuf = data;
            midiplay = true;
            savereq = "jingle" + midipos + ".mid";
        }
    }

    private void midiplay(String location) { // TODO: fade out
        music = null;
        synthesizer = null;
        sequence = null;
        File midiFile = new File(location);
        try {
            sequence = MidiSystem.getSequence(midiFile);
            music = MidiSystem.getSequencer();
            music.open();
            music.setSequence(sequence);
        } catch (Exception e) {
            System.err.println("Problem loading MIDI file.");
            e.printStackTrace();
            return;
        }
        if (music instanceof Synthesizer) {
            synthesizer = (Synthesizer) music;
        } else {
            try {
                synthesizer = MidiSystem.getSynthesizer();
                synthesizer.open();
                if (synthesizer.getDefaultSoundbank() == null) {
                    music.getTransmitter().setReceiver(MidiSystem.getReceiver());
                } else {
                    music.getTransmitter().setReceiver(synthesizer.getReceiver());
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        music.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        updateVolume(); // TODO: this doesn't work why? initial music value doesn't seem to be 127
        music.start();
    }

    public static void updateVolume() {
        if (synthesizer.getDefaultSoundbank() == null) {
            try {
                ShortMessage volumeMessage = new ShortMessage();
                for (int i = 0; i < 16; i++) {
                    volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, midivol);
                    volumeMessage.setMessage(ShortMessage.CONTROL_CHANGE, i, 39, midivol);
                    MidiSystem.getReceiver().send(volumeMessage, -1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            MidiChannel[] channels = synthesizer.getChannels();
            for (int c = 0; channels != null && c < channels.length; c++) {
                channels[c].controlChange(7, midivol);
                channels[c].controlChange(39, midivol);
            }
        }
    }

    public static void reporterror(String s) {
        if (!reporterror) {
            return;
        }
        if (!active) {
            return;
        }
        System.out.println("Error: " + s);
    }

    public Signlink() {
    }

    @Override
    public void run() {
        active = true;

        String cachedir = findcachedir();
        System.out.println(cachedir);
        uid = getuid(cachedir);

        try {
            cache_dat = new RandomAccessFile(cachedir + "main_file_cache.dat", "rw");
            for (int j = 0; j < 5; j++) {
                cache_idx[j] = new RandomAccessFile(cachedir + "main_file_cache.idx" + j, "rw");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = threadliveid; threadliveid == i; ) {
            if (dnsreq != null) {
                try {
                    dns = InetAddress.getByName(dnsreq).getHostName();
                } catch (Exception _ex) {
                    dns = "unknown";
                }
                dnsreq = null;
            } else if (savereq != null) {
                if (savebuf != null) {
                    try {
                        FileOutputStream fileoutputstream = new FileOutputStream(cachedir + savereq);
                        fileoutputstream.write(savebuf, 0, savelen);
                        fileoutputstream.close();
                    } catch (Exception ignored) {
                    }
                }
                if (waveplay) {
                    wave = cachedir + savereq;
                    waveplay = false;
                }
                if (midiplay) {
                    midi = cachedir + savereq;
                    midiplay = false;

                    if (music != null) {
                        music.stop();
                        music.close();
                    }
                    midiplay(midi);
                }
                savereq = null;
            }

            try {
                Thread.sleep(50L);
            } catch (Exception ignored) {
            }
        }
    }

}
