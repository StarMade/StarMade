package jo.sm.ui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BegPanel extends JPanel
{
    private static final int TICK = 200;
    private static final int CHOP = 150;

    private static final String THE_RAIDERS_LAMENT = "http://podiobooks.com/title/the-raiders-lament";
    
    private int mMessageOffset;
    
    private JLabel  mStatus;
    private JButton mGoto;
    
    public BegPanel()
    {
        // instantiate
        mStatus = new JLabel(MESSAGE.substring(0, CHOP));
        setBackground(Color.cyan);
        mGoto = new JButton("Go");
        Dimension d = mGoto.getPreferredSize();
        System.out.println("Goto size="+d);
        mStatus.setPreferredSize(new Dimension(1024 - d.width, d.height));
        // layout
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add("Center", mStatus);
        add("East", mGoto);
        // link
        Thread t = new Thread("beg_ticker") { public void run() { doTicker(); } };
        t.setDaemon(true);
        t.start();
        mGoto.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                doGoto();
            }            
        });
    }
    
    private void doTicker()
    {
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
        }
        for (;;)
        {
            try
            {
                Thread.sleep(TICK);
            }
            catch (InterruptedException e)
            {
            }
            mMessageOffset++;
            if (mMessageOffset == MESSAGE.length())
                mMessageOffset = 0;
            String msg = MESSAGE.substring(mMessageOffset) + MESSAGE.substring(0, mMessageOffset);
            msg = msg.substring(0, CHOP);
            mStatus.setText(msg);
        }
    }
    
    private void doGoto()
    {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Action.BROWSE))
                try {
                    desktop.browse(URI.create(THE_RAIDERS_LAMENT));
                    return;
                } catch (IOException e) {
                    // handled below
                }
        }
    }

    private static final String MESSAGE = "This software is made freely available with no charge or limitation. "
            + "Even the source is included. "
            + "It is distributed as \"begware\", and here is the begging. "
            + "If you like and use this softawre, please go and download my audiobook \"The Raider's Lament\". "
            + "It's a light hearted Sci-Fi novel that's a fun waste of time. "
            + "You don't have to actually listen to it. "
            + "(The recording is not the best.) "
            + "Clocking up the downloads is appreciated. "
            + "If you *really* like this software (or the story) there is a \"Donate\" button on the site. "
            + "Proceeds will go towards buying my daughter the Minecraft Lego Kit! "
            + "                                                           ";
}
