/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.io.File;
/*   4:    */import java.io.FileInputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.PrintStream;
/*   7:    */import org.lwjgl.LWJGLException;
/*   8:    */import org.lwjgl.input.Keyboard;
/*   9:    */import org.lwjgl.opengl.Display;
/*  10:    */import org.lwjgl.opengl.DisplayMode;
/*  11:    */import org.lwjgl.opengl.GL11;
/*  12:    */import org.newdawn.slick.Color;
/*  13:    */import org.newdawn.slick.TrueTypeFont;
/*  14:    */import org.newdawn.slick.openal.Audio;
/*  15:    */import org.newdawn.slick.openal.AudioLoader;
/*  16:    */import org.newdawn.slick.openal.SoundStore;
/*  17:    */import org.newdawn.slick.opengl.Texture;
/*  18:    */import org.newdawn.slick.opengl.TextureLoader;
/*  19:    */import org.newdawn.slick.util.Log;
/*  20:    */
/*  36:    */public class TestUtils
/*  37:    */{
/*  38:    */  private Texture texture;
/*  39:    */  private Audio oggEffect;
/*  40:    */  private Audio wavEffect;
/*  41:    */  private Audio aifEffect;
/*  42:    */  private Audio oggStream;
/*  43:    */  private Audio modStream;
/*  44:    */  private org.newdawn.slick.Font font;
/*  45:    */  
/*  46:    */  public void start()
/*  47:    */  {
/*  48: 48 */    initGL(800, 600);
/*  49: 49 */    init();
/*  50:    */    for (;;)
/*  51:    */    {
/*  52: 52 */      update();
/*  53: 53 */      GL11.glClear(16384);
/*  54: 54 */      render();
/*  55:    */      
/*  56: 56 */      Display.update();
/*  57: 57 */      Display.sync(100);
/*  58:    */      
/*  59: 59 */      if (Display.isCloseRequested()) {
/*  60: 60 */        System.exit(0);
/*  61:    */      }
/*  62:    */    }
/*  63:    */  }
/*  64:    */  
/*  69:    */  private void initGL(int width, int height)
/*  70:    */  {
/*  71:    */    try
/*  72:    */    {
/*  73: 73 */      Display.setDisplayMode(new DisplayMode(width, height));
/*  74: 74 */      Display.create();
/*  75: 75 */      Display.setVSyncEnabled(true);
/*  76:    */    } catch (LWJGLException e) {
/*  77: 77 */      e.printStackTrace();
/*  78: 78 */      System.exit(0);
/*  79:    */    }
/*  80:    */    
/*  81: 81 */    GL11.glEnable(3553);
/*  82: 82 */    GL11.glShadeModel(7425);
/*  83: 83 */    GL11.glDisable(2929);
/*  84: 84 */    GL11.glDisable(2896);
/*  85:    */    
/*  86: 86 */    GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/*  87: 87 */    GL11.glClearDepth(1.0D);
/*  88:    */    
/*  89: 89 */    GL11.glEnable(3042);
/*  90: 90 */    GL11.glBlendFunc(770, 771);
/*  91:    */    
/*  92: 92 */    GL11.glViewport(0, 0, width, height);
/*  93: 93 */    GL11.glMatrixMode(5888);
/*  94:    */    
/*  95: 95 */    GL11.glMatrixMode(5889);
/*  96: 96 */    GL11.glLoadIdentity();
/*  97: 97 */    GL11.glOrtho(0.0D, width, height, 0.0D, 1.0D, -1.0D);
/*  98: 98 */    GL11.glMatrixMode(5888);
/*  99:    */  }
/* 100:    */  
/* 104:    */  public void init()
/* 105:    */  {
/* 106:106 */    Log.setVerbose(false);
/* 107:    */    
/* 108:108 */    java.awt.Font awtFont = new java.awt.Font("Times New Roman", 1, 16);
/* 109:109 */    this.font = new TrueTypeFont(awtFont, false);
/* 110:    */    
/* 113:    */    try
/* 114:    */    {
/* 115:115 */      this.texture = TextureLoader.getTexture("PNG", new FileInputStream("testdata/rocks.png"));
/* 116:    */      
/* 117:117 */      System.out.println("Texture loaded: " + this.texture);
/* 118:118 */      System.out.println(">> Image width: " + this.texture.getImageWidth());
/* 119:119 */      System.out.println(">> Image height: " + this.texture.getImageWidth());
/* 120:120 */      System.out.println(">> Texture width: " + this.texture.getTextureWidth());
/* 121:121 */      System.out.println(">> Texture height: " + this.texture.getTextureHeight());
/* 122:122 */      System.out.println(">> Texture ID: " + this.texture.getTextureID());
/* 123:    */    } catch (IOException e) {
/* 124:124 */      e.printStackTrace();
/* 125:    */    }
/* 126:    */    
/* 128:    */    try
/* 129:    */    {
/* 130:130 */      this.oggEffect = AudioLoader.getAudio("OGG", new FileInputStream("testdata/restart.ogg"));
/* 131:    */      
/* 135:135 */      this.oggStream = AudioLoader.getStreamingAudio("OGG", new File("testdata/bongos.ogg").toURL());
/* 136:    */      
/* 139:139 */      this.modStream = AudioLoader.getStreamingAudio("MOD", new File("testdata/SMB-X.XM").toURL());
/* 140:    */      
/* 143:143 */      this.modStream.playAsMusic(1.0F, 1.0F, true);
/* 144:    */      
/* 147:147 */      this.aifEffect = AudioLoader.getAudio("AIF", new FileInputStream("testdata/burp.aif"));
/* 148:    */      
/* 151:151 */      this.wavEffect = AudioLoader.getAudio("WAV", new FileInputStream("testdata/cbrown01.wav"));
/* 152:    */    } catch (IOException e) {
/* 153:153 */      e.printStackTrace();
/* 154:    */    }
/* 155:    */  }
/* 156:    */  
/* 159:    */  public void update()
/* 160:    */  {
/* 161:161 */    while (Keyboard.next()) {
/* 162:162 */      if (Keyboard.getEventKeyState()) {
/* 163:163 */        if (Keyboard.getEventKey() == 16)
/* 164:    */        {
/* 165:165 */          this.oggEffect.playAsSoundEffect(1.0F, 1.0F, false);
/* 166:    */        }
/* 167:167 */        if (Keyboard.getEventKey() == 17)
/* 168:    */        {
/* 170:170 */          this.oggStream.playAsMusic(1.0F, 1.0F, true);
/* 171:    */        }
/* 172:172 */        if (Keyboard.getEventKey() == 18)
/* 173:    */        {
/* 175:175 */          this.modStream.playAsMusic(1.0F, 1.0F, true);
/* 176:    */        }
/* 177:177 */        if (Keyboard.getEventKey() == 19)
/* 178:    */        {
/* 179:179 */          this.aifEffect.playAsSoundEffect(1.0F, 1.0F, false);
/* 180:    */        }
/* 181:181 */        if (Keyboard.getEventKey() == 20)
/* 182:    */        {
/* 183:183 */          this.wavEffect.playAsSoundEffect(1.0F, 1.0F, false);
/* 184:    */        }
/* 185:    */      }
/* 186:    */    }
/* 187:    */    
/* 190:190 */    SoundStore.get().poll(0);
/* 191:    */  }
/* 192:    */  
/* 195:    */  public void render()
/* 196:    */  {
/* 197:197 */    Color.white.bind();
/* 198:198 */    this.texture.bind();
/* 199:    */    
/* 200:200 */    GL11.glBegin(7);
/* 201:201 */    GL11.glTexCoord2f(0.0F, 0.0F);
/* 202:202 */    GL11.glVertex2f(100.0F, 100.0F);
/* 203:203 */    GL11.glTexCoord2f(1.0F, 0.0F);
/* 204:204 */    GL11.glVertex2f(100 + this.texture.getTextureWidth(), 100.0F);
/* 205:205 */    GL11.glTexCoord2f(1.0F, 1.0F);
/* 206:206 */    GL11.glVertex2f(100 + this.texture.getTextureWidth(), 100 + this.texture.getTextureHeight());
/* 207:207 */    GL11.glTexCoord2f(0.0F, 1.0F);
/* 208:208 */    GL11.glVertex2f(100.0F, 100 + this.texture.getTextureHeight());
/* 209:209 */    GL11.glEnd();
/* 210:    */    
/* 211:211 */    this.font.drawString(150.0F, 300.0F, "HELLO LWJGL WORLD", Color.yellow);
/* 212:    */  }
/* 213:    */  
/* 218:    */  public static void main(String[] argv)
/* 219:    */  {
/* 220:220 */    TestUtils utils = new TestUtils();
/* 221:221 */    utils.start();
/* 222:    */  }
/* 223:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.TestUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */