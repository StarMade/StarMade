/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Color;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.Music;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.Sound;
/*  11:    */import org.newdawn.slick.util.ResourceLoader;
/*  12:    */
/*  26:    */public class SoundURLTest
/*  27:    */  extends BasicGame
/*  28:    */{
/*  29:    */  private Sound sound;
/*  30:    */  private Sound charlie;
/*  31:    */  private Sound burp;
/*  32:    */  private Music music;
/*  33:    */  private Music musica;
/*  34:    */  private Music musicb;
/*  35:    */  private Sound engine;
/*  36: 36 */  private int volume = 1;
/*  37:    */  
/*  40:    */  public SoundURLTest()
/*  41:    */  {
/*  42: 42 */    super("Sound URL Test");
/*  43:    */  }
/*  44:    */  
/*  46:    */  public void init(GameContainer container)
/*  47:    */    throws SlickException
/*  48:    */  {
/*  49: 49 */    this.sound = new Sound(ResourceLoader.getResource("testdata/restart.ogg"));
/*  50: 50 */    this.charlie = new Sound(ResourceLoader.getResource("testdata/cbrown01.wav"));
/*  51: 51 */    this.engine = new Sound(ResourceLoader.getResource("testdata/engine.wav"));
/*  52:    */    
/*  53: 53 */    this.music = (this.musica = new Music(ResourceLoader.getResource("testdata/restart.ogg"), false));
/*  54: 54 */    this.musicb = new Music(ResourceLoader.getResource("testdata/kirby.ogg"), false);
/*  55: 55 */    this.burp = new Sound(ResourceLoader.getResource("testdata/burp.aif"));
/*  56:    */  }
/*  57:    */  
/*  60:    */  public void render(GameContainer container, Graphics g)
/*  61:    */  {
/*  62: 62 */    g.setColor(Color.white);
/*  63: 63 */    g.drawString("The OGG loop is now streaming from the file, woot.", 100.0F, 60.0F);
/*  64: 64 */    g.drawString("Press space for sound effect (OGG)", 100.0F, 100.0F);
/*  65: 65 */    g.drawString("Press P to pause/resume music (XM)", 100.0F, 130.0F);
/*  66: 66 */    g.drawString("Press E to pause/resume engine sound (WAV)", 100.0F, 190.0F);
/*  67: 67 */    g.drawString("Press enter for charlie (WAV)", 100.0F, 160.0F);
/*  68: 68 */    g.drawString("Press C to change music", 100.0F, 210.0F);
/*  69: 69 */    g.drawString("Press B to burp (AIF)", 100.0F, 240.0F);
/*  70: 70 */    g.drawString("Press + or - to change volume of music", 100.0F, 270.0F);
/*  71: 71 */    g.setColor(Color.blue);
/*  72: 72 */    g.drawString("Music Volume Level: " + this.volume / 10.0F, 150.0F, 300.0F);
/*  73:    */  }
/*  74:    */  
/*  78:    */  public void update(GameContainer container, int delta) {}
/*  79:    */  
/*  83:    */  public void keyPressed(int key, char c)
/*  84:    */  {
/*  85: 85 */    if (key == 1) {
/*  86: 86 */      System.exit(0);
/*  87:    */    }
/*  88: 88 */    if (key == 57) {
/*  89: 89 */      this.sound.play();
/*  90:    */    }
/*  91: 91 */    if (key == 48) {
/*  92: 92 */      this.burp.play();
/*  93:    */    }
/*  94: 94 */    if (key == 30) {
/*  95: 95 */      this.sound.playAt(-1.0F, 0.0F, 0.0F);
/*  96:    */    }
/*  97: 97 */    if (key == 38) {
/*  98: 98 */      this.sound.playAt(1.0F, 0.0F, 0.0F);
/*  99:    */    }
/* 100:100 */    if (key == 28) {
/* 101:101 */      this.charlie.play(1.0F, 1.0F);
/* 102:    */    }
/* 103:103 */    if (key == 25) {
/* 104:104 */      if (this.music.playing()) {
/* 105:105 */        this.music.pause();
/* 106:    */      } else {
/* 107:107 */        this.music.resume();
/* 108:    */      }
/* 109:    */    }
/* 110:110 */    if (key == 46) {
/* 111:111 */      this.music.stop();
/* 112:112 */      if (this.music == this.musica) {
/* 113:113 */        this.music = this.musicb;
/* 114:    */      } else {
/* 115:115 */        this.music = this.musica;
/* 116:    */      }
/* 117:    */      
/* 118:118 */      this.music.loop();
/* 119:    */    }
/* 120:120 */    if (key == 18) {
/* 121:121 */      if (this.engine.playing()) {
/* 122:122 */        this.engine.stop();
/* 123:    */      } else {
/* 124:124 */        this.engine.loop();
/* 125:    */      }
/* 126:    */    }
/* 127:    */    
/* 128:128 */    if (c == '+') {
/* 129:129 */      this.volume += 1;
/* 130:130 */      setVolume();
/* 131:    */    }
/* 132:    */    
/* 133:133 */    if (c == '-') {
/* 134:134 */      this.volume -= 1;
/* 135:135 */      setVolume();
/* 136:    */    }
/* 137:    */  }
/* 138:    */  
/* 143:    */  private void setVolume()
/* 144:    */  {
/* 145:145 */    if (this.volume > 10) {
/* 146:146 */      this.volume = 10;
/* 147:147 */    } else if (this.volume < 0) {
/* 148:148 */      this.volume = 0;
/* 149:    */    }
/* 150:    */    
/* 151:151 */    this.music.setVolume(this.volume / 10.0F);
/* 152:    */  }
/* 153:    */  
/* 157:    */  public static void main(String[] argv)
/* 158:    */  {
/* 159:    */    try
/* 160:    */    {
/* 161:161 */      AppGameContainer container = new AppGameContainer(new SoundURLTest());
/* 162:162 */      container.setDisplayMode(800, 600, false);
/* 163:163 */      container.start();
/* 164:    */    } catch (SlickException e) {
/* 165:165 */      e.printStackTrace();
/* 166:    */    }
/* 167:    */  }
/* 168:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.SoundURLTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */