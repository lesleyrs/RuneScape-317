// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.awt.*;

public class GameFrame extends Frame {

    public final GameShell shell;

    public GameFrame(GameShell shell) {
        this.shell = shell;
        setTitle("Jagex");
        setResizable(false);
        if (Game.fullscreen) {
            setBackground(Color.BLACK);
            setUndecorated(true);
            setLayout(new GridBagLayout());
            add(shell, new GridBagConstraints());
            pack();
            setExtendedState(Frame.MAXIMIZED_BOTH);
        } else {
            setLayout(new BorderLayout());
            add(shell, BorderLayout.CENTER);
            pack();
        }
        setLocationRelativeTo(null);
        setVisible(true);
        transferFocus();
    }
}
