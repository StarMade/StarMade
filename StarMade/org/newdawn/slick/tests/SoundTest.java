package org.newdawn.slick.tests;

import java.io.IOException;
import java.io.PrintStream;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class SoundTest
  extends BasicGame
{
  private GameContainer myContainer;
  private Sound sound;
  private Sound charlie;
  private Sound burp;
  private Music music;
  private Music musica;
  private Music musicb;
  private Audio engine;
  private int volume = 10;
  private int[] engines = new int[3];
  
  public SoundTest()
  {
    super("Sound And Music Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    SoundStore.get().setMaxSources(32);
    this.myContainer = container;
    this.sound = new Sound("testdata/restart.ogg");
    this.charlie = new Sound("testdata/cbrown01.wav");
    try
    {
      this.engine = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("testdata/engine.wav"));
    }
    catch (IOException local_e)
    {
      throw new SlickException("Failed to load engine", local_e);
    }
    this.music = (this.musica = new Music("testdata/SMB-X.XM"));
    this.musicb = new Music("testdata/kirby.ogg", true);
    this.burp = new Sound("testdata/burp.aif");
    this.music.play();
  }
  
  public void render(GameContainer container, Graphics local_g)
  {
    local_g.setColor(Color.white);
    local_g.drawString("The OGG loop is now streaming from the file, woot.", 100.0F, 60.0F);
    local_g.drawString("Press space for sound effect (OGG)", 100.0F, 100.0F);
    local_g.drawString("Press P to pause/resume music (XM)", 100.0F, 130.0F);
    local_g.drawString("Press E to pause/resume engine sound (WAV)", 100.0F, 190.0F);
    local_g.drawString("Press enter for charlie (WAV)", 100.0F, 160.0F);
    local_g.drawString("Press C to change music", 100.0F, 210.0F);
    local_g.drawString("Press B to burp (AIF)", 100.0F, 240.0F);
    local_g.drawString("Press + or - to change global volume of music", 100.0F, 270.0F);
    local_g.drawString("Press Y or X to change individual volume of music", 100.0F, 300.0F);
    local_g.drawString("Press N or M to change global volume of sound fx", 100.0F, 330.0F);
    local_g.setColor(Color.blue);
    local_g.drawString("Global Sound Volume Level: " + container.getSoundVolume(), 150.0F, 390.0F);
    local_g.drawString("Global Music Volume Level: " + container.getMusicVolume(), 150.0F, 420.0F);
    local_g.drawString("Current Music Volume Level: " + this.music.getVolume(), 150.0F, 450.0F);
  }
  
  public void update(GameContainer container, int delta) {}
  
  public void keyPressed(int key, char local_c)
  {
    if (key == 1) {
      System.exit(0);
    }
    if (key == 57) {
      this.sound.play();
    }
    if (key == 48) {
      this.burp.play();
    }
    if (key == 30) {
      this.sound.playAt(-1.0F, 0.0F, 0.0F);
    }
    if (key == 38) {
      this.sound.playAt(1.0F, 0.0F, 0.0F);
    }
    if (key == 28) {
      this.charlie.play(1.0F, 1.0F);
    }
    if (key == 25) {
      if (this.music.playing()) {
        this.music.pause();
      } else {
        this.music.resume();
      }
    }
    if (key == 46)
    {
      this.music.stop();
      if (this.music == this.musica) {
        this.music = this.musicb;
      } else {
        this.music = this.musica;
      }
      this.music.loop();
    }
    for (int local_i = 0; local_i < 3; local_i++) {
      if (key == 2 + local_i) {
        if (this.engines[local_i] != 0)
        {
          System.out.println("Stop " + local_i);
          SoundStore.get().stopSoundEffect(this.engines[local_i]);
          this.engines[local_i] = 0;
        }
        else
        {
          System.out.println("Start " + local_i);
          this.engines[local_i] = this.engine.playAsSoundEffect(1.0F, 1.0F, true);
        }
      }
    }
    if (local_c == '+')
    {
      this.volume += 1;
      setVolume();
    }
    if (local_c == '-')
    {
      this.volume -= 1;
      setVolume();
    }
    if (key == 21)
    {
      int local_i = (int)(this.music.getVolume() * 10.0F);
      local_i--;
      if (local_i < 0) {
        local_i = 0;
      }
      this.music.setVolume(local_i / 10.0F);
    }
    if (key == 45)
    {
      int local_i = (int)(this.music.getVolume() * 10.0F);
      local_i++;
      if (local_i > 10) {
        local_i = 10;
      }
      this.music.setVolume(local_i / 10.0F);
    }
    if (key == 49)
    {
      int local_i = (int)(this.myContainer.getSoundVolume() * 10.0F);
      local_i--;
      if (local_i < 0) {
        local_i = 0;
      }
      this.myContainer.setSoundVolume(local_i / 10.0F);
    }
    if (key == 50)
    {
      int local_i = (int)(this.myContainer.getSoundVolume() * 10.0F);
      local_i++;
      if (local_i > 10) {
        local_i = 10;
      }
      this.myContainer.setSoundVolume(local_i / 10.0F);
    }
  }
  
  private void setVolume()
  {
    if (this.volume > 10) {
      this.volume = 10;
    } else if (this.volume < 0) {
      this.volume = 0;
    }
    this.myContainer.setMusicVolume(this.volume / 10.0F);
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new SoundTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.SoundTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */