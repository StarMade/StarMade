/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.PrintStream;
/*   5:    */import org.newdawn.slick.AppGameContainer;
/*   6:    */import org.newdawn.slick.BasicGame;
/*   7:    */import org.newdawn.slick.Color;
/*   8:    */import org.newdawn.slick.GameContainer;
/*   9:    */import org.newdawn.slick.Graphics;
/*  10:    */import org.newdawn.slick.Music;
/*  11:    */import org.newdawn.slick.SlickException;
/*  12:    */import org.newdawn.slick.Sound;
/*  13:    */import org.newdawn.slick.openal.Audio;
/*  14:    */import org.newdawn.slick.openal.AudioLoader;
/*  15:    */import org.newdawn.slick.openal.SoundStore;
/*  16:    */import org.newdawn.slick.util.ResourceLoader;
/*  17:    */
/*  32:    */public class SoundTest
/*  33:    */  extends BasicGame
/*  34:    */{
/*  35:    */  private GameContainer myContainer;
/*  36:    */  private Sound sound;
/*  37:    */  private Sound charlie;
/*  38:    */  private Sound burp;
/*  39:    */  private Music music;
/*  40:    */  private Music musica;
/*  41:    */  private Music musicb;
/*  42:    */  private Audio engine;
/*  43: 43 */  private int volume = 10;
/*  44:    */  
/*  46: 46 */  private int[] engines = new int[3];
/*  47:    */  
/*  50:    */  public SoundTest()
/*  51:    */  {
/*  52: 52 */    super("Sound And Music Test");
/*  53:    */  }
/*  54:    */  
/*  56:    */  public void init(GameContainer container)
/*  57:    */    throws SlickException
/*  58:    */  {
/*  59: 59 */    SoundStore.get().setMaxSources(32);
/*  60:    */    
/*  61: 61 */    this.myContainer = container;
/*  62: 62 */    this.sound = new Sound("testdata/restart.ogg");
/*  63: 63 */    this.charlie = new Sound("testdata/cbrown01.wav");
/*  64:    */    try {
/*  65: 65 */      this.engine = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("testdata/engine.wav"));
/*  66:    */    } catch (IOException e) {
/*  67: 67 */      throw new SlickException("Failed to load engine", e);
/*  68:    */    }
/*  69: 69 */    this.music = (this.musica = new Music("testdata/SMB-X.XM"));
/*  70:    */    
/*  71: 71 */    this.musicb = new Music("testdata/kirby.ogg", true);
/*  72: 72 */    this.burp = new Sound("testdata/burp.aif");
/*  73:    */    
/*  74: 74 */    this.music.play();
/*  75:    */  }
/*  76:    */  
/*  79:    */  public void render(GameContainer container, Graphics g)
/*  80:    */  {
/*  81: 81 */    g.setColor(Color.white);
/*  82: 82 */    g.drawString("The OGG loop is now streaming from the file, woot.", 100.0F, 60.0F);
/*  83: 83 */    g.drawString("Press space for sound effect (OGG)", 100.0F, 100.0F);
/*  84: 84 */    g.drawString("Press P to pause/resume music (XM)", 100.0F, 130.0F);
/*  85: 85 */    g.drawString("Press E to pause/resume engine sound (WAV)", 100.0F, 190.0F);
/*  86: 86 */    g.drawString("Press enter for charlie (WAV)", 100.0F, 160.0F);
/*  87: 87 */    g.drawString("Press C to change music", 100.0F, 210.0F);
/*  88: 88 */    g.drawString("Press B to burp (AIF)", 100.0F, 240.0F);
/*  89: 89 */    g.drawString("Press + or - to change global volume of music", 100.0F, 270.0F);
/*  90: 90 */    g.drawString("Press Y or X to change individual volume of music", 100.0F, 300.0F);
/*  91: 91 */    g.drawString("Press N or M to change global volume of sound fx", 100.0F, 330.0F);
/*  92: 92 */    g.setColor(Color.blue);
/*  93: 93 */    g.drawString("Global Sound Volume Level: " + container.getSoundVolume(), 150.0F, 390.0F);
/*  94: 94 */    g.drawString("Global Music Volume Level: " + container.getMusicVolume(), 150.0F, 420.0F);
/*  95: 95 */    g.drawString("Current Music Volume Level: " + this.music.getVolume(), 150.0F, 450.0F);
/*  96:    */  }
/*  97:    */  
/* 101:    */  public void update(GameContainer container, int delta) {}
/* 102:    */  
/* 106:    */  public void keyPressed(int key, char c)
/* 107:    */  {
/* 108:108 */    if (key == 1) {
/* 109:109 */      System.exit(0);
/* 110:    */    }
/* 111:111 */    if (key == 57) {
/* 112:112 */      this.sound.play();
/* 113:    */    }
/* 114:114 */    if (key == 48) {
/* 115:115 */      this.burp.play();
/* 116:    */    }
/* 117:117 */    if (key == 30) {
/* 118:118 */      this.sound.playAt(-1.0F, 0.0F, 0.0F);
/* 119:    */    }
/* 120:120 */    if (key == 38) {
/* 121:121 */      this.sound.playAt(1.0F, 0.0F, 0.0F);
/* 122:    */    }
/* 123:123 */    if (key == 28) {
/* 124:124 */      this.charlie.play(1.0F, 1.0F);
/* 125:    */    }
/* 126:126 */    if (key == 25) {
/* 127:127 */      if (this.music.playing()) {
/* 128:128 */        this.music.pause();
/* 129:    */      } else {
/* 130:130 */        this.music.resume();
/* 131:    */      }
/* 132:    */    }
/* 133:133 */    if (key == 46) {
/* 134:134 */      this.music.stop();
/* 135:135 */      if (this.music == this.musica) {
/* 136:136 */        this.music = this.musicb;
/* 137:    */      } else {
/* 138:138 */        this.music = this.musica;
/* 139:    */      }
/* 140:    */      
/* 141:141 */      this.music.loop();
/* 142:    */    }
/* 143:143 */    for (int i = 0; i < 3; i++) {
/* 144:144 */      if (key == 2 + i) {
/* 145:145 */        if (this.engines[i] != 0) {
/* 146:146 */          System.out.println("Stop " + i);
/* 147:147 */          SoundStore.get().stopSoundEffect(this.engines[i]);
/* 148:148 */          this.engines[i] = 0;
/* 149:    */        } else {
/* 150:150 */          System.out.println("Start " + i);
/* 151:151 */          this.engines[i] = this.engine.playAsSoundEffect(1.0F, 1.0F, true);
/* 152:    */        }
/* 153:    */      }
/* 154:    */    }
/* 155:    */    
/* 156:156 */    if (c == '+') {
/* 157:157 */      this.volume += 1;
/* 158:158 */      setVolume();
/* 159:    */    }
/* 160:    */    
/* 161:161 */    if (c == '-') {
/* 162:162 */      this.volume -= 1;
/* 163:163 */      setVolume();
/* 164:    */    }
/* 165:    */    
/* 166:166 */    if (key == 21) {
/* 167:167 */      int vol = (int)(this.music.getVolume() * 10.0F);
/* 168:168 */      vol--;
/* 169:169 */      if (vol < 0) { vol = 0;
/* 170:    */      }
/* 171:171 */      this.music.setVolume(vol / 10.0F);
/* 172:    */    }
/* 173:173 */    if (key == 45) {
/* 174:174 */      int vol = (int)(this.music.getVolume() * 10.0F);
/* 175:175 */      vol++;
/* 176:176 */      if (vol > 10) { vol = 10;
/* 177:    */      }
/* 178:178 */      this.music.setVolume(vol / 10.0F);
/* 179:    */    }
/* 180:180 */    if (key == 49) {
/* 181:181 */      int vol = (int)(this.myContainer.getSoundVolume() * 10.0F);
/* 182:182 */      vol--;
/* 183:183 */      if (vol < 0) { vol = 0;
/* 184:    */      }
/* 185:185 */      this.myContainer.setSoundVolume(vol / 10.0F);
/* 186:    */    }
/* 187:187 */    if (key == 50) {
/* 188:188 */      int vol = (int)(this.myContainer.getSoundVolume() * 10.0F);
/* 189:189 */      vol++;
/* 190:190 */      if (vol > 10) { vol = 10;
/* 191:    */      }
/* 192:192 */      this.myContainer.setSoundVolume(vol / 10.0F);
/* 193:    */    }
/* 194:    */  }
/* 195:    */  
/* 200:    */  private void setVolume()
/* 201:    */  {
/* 202:202 */    if (this.volume > 10) {
/* 203:203 */      this.volume = 10;
/* 204:204 */    } else if (this.volume < 0) {
/* 205:205 */      this.volume = 0;
/* 206:    */    }
/* 207:    */    
/* 208:208 */    this.myContainer.setMusicVolume(this.volume / 10.0F);
/* 209:    */  }
/* 210:    */  
/* 214:    */  public static void main(String[] argv)
/* 215:    */  {
/* 216:    */    try
/* 217:    */    {
/* 218:218 */      AppGameContainer container = new AppGameContainer(new SoundTest());
/* 219:219 */      container.setDisplayMode(800, 600, false);
/* 220:220 */      container.start();
/* 221:    */    } catch (SlickException e) {
/* 222:222 */      e.printStackTrace();
/* 223:    */    }
/* 224:    */  }
/* 225:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.SoundTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */