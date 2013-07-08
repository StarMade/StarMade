/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import org.lwjgl.BufferUtils;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */import org.newdawn.slick.AngelCodeFont;
/*   7:    */import org.newdawn.slick.Animation;
/*   8:    */import org.newdawn.slick.AppGameContainer;
/*   9:    */import org.newdawn.slick.BasicGame;
/*  10:    */import org.newdawn.slick.GameContainer;
/*  11:    */import org.newdawn.slick.Graphics;
/*  12:    */import org.newdawn.slick.Image;
/*  13:    */import org.newdawn.slick.SlickException;
/*  14:    */import org.newdawn.slick.SpriteSheet;
/*  15:    */import org.newdawn.slick.opengl.SlickCallable;
/*  16:    */
/*  28:    */public class SlickCallableTest
/*  29:    */  extends BasicGame
/*  30:    */{
/*  31:    */  private Image image;
/*  32:    */  private Image back;
/*  33:    */  private float rot;
/*  34:    */  private AngelCodeFont font;
/*  35:    */  private Animation homer;
/*  36:    */  
/*  37:    */  public SlickCallableTest()
/*  38:    */  {
/*  39: 39 */    super("Slick Callable Test");
/*  40:    */  }
/*  41:    */  
/*  43:    */  public void init(GameContainer container)
/*  44:    */    throws SlickException
/*  45:    */  {
/*  46: 46 */    this.image = new Image("testdata/rocket.png");
/*  47: 47 */    this.back = new Image("testdata/sky.jpg");
/*  48: 48 */    this.font = new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
/*  49: 49 */    SpriteSheet sheet = new SpriteSheet("testdata/homeranim.png", 36, 65);
/*  50: 50 */    this.homer = new Animation(sheet, 0, 0, 7, 0, true, 150, true);
/*  51:    */  }
/*  52:    */  
/*  54:    */  public void render(GameContainer container, Graphics g)
/*  55:    */    throws SlickException
/*  56:    */  {
/*  57: 57 */    g.scale(2.0F, 2.0F);
/*  58: 58 */    g.fillRect(0.0F, 0.0F, 800.0F, 600.0F, this.back, 0.0F, 0.0F);
/*  59: 59 */    g.resetTransform();
/*  60:    */    
/*  61: 61 */    g.drawImage(this.image, 100.0F, 100.0F);
/*  62: 62 */    this.image.draw(100.0F, 200.0F, 80.0F, 200.0F);
/*  63:    */    
/*  64: 64 */    this.font.drawString(100.0F, 200.0F, "Text Drawn before the callable");
/*  65:    */    
/*  66: 66 */    SlickCallable callable = new SlickCallable() {
/*  67:    */      protected void performGLOperations() throws SlickException {
/*  68: 68 */        SlickCallableTest.this.renderGL();
/*  69:    */      }
/*  70: 70 */    };
/*  71: 71 */    callable.call();
/*  72:    */    
/*  73: 73 */    this.homer.draw(450.0F, 250.0F, 80.0F, 200.0F);
/*  74: 74 */    this.font.drawString(150.0F, 300.0F, "Text Drawn after the callable");
/*  75:    */  }
/*  76:    */  
/*  81:    */  public void renderGL()
/*  82:    */  {
/*  83: 83 */    FloatBuffer pos = BufferUtils.createFloatBuffer(4);
/*  84: 84 */    pos.put(new float[] { 5.0F, 5.0F, 10.0F, 0.0F }).flip();
/*  85: 85 */    FloatBuffer red = BufferUtils.createFloatBuffer(4);
/*  86: 86 */    red.put(new float[] { 0.8F, 0.1F, 0.0F, 1.0F }).flip();
/*  87:    */    
/*  88: 88 */    GL11.glLight(16384, 4611, pos);
/*  89: 89 */    GL11.glEnable(16384);
/*  90:    */    
/*  91: 91 */    GL11.glEnable(2884);
/*  92: 92 */    GL11.glEnable(2929);
/*  93: 93 */    GL11.glEnable(2896);
/*  94:    */    
/*  95: 95 */    GL11.glMatrixMode(5889);
/*  96: 96 */    GL11.glLoadIdentity();
/*  97: 97 */    float h = 0.75F;
/*  98: 98 */    GL11.glFrustum(-1.0D, 1.0D, -h, h, 5.0D, 60.0D);
/*  99: 99 */    GL11.glMatrixMode(5888);
/* 100:100 */    GL11.glLoadIdentity();
/* 101:101 */    GL11.glTranslatef(0.0F, 0.0F, -40.0F);
/* 102:102 */    GL11.glRotatef(this.rot, 0.0F, 1.0F, 1.0F);
/* 103:    */    
/* 104:104 */    GL11.glMaterial(1028, 5634, red);
/* 105:105 */    gear(0.5F, 2.0F, 2.0F, 10, 0.7F);
/* 106:    */  }
/* 107:    */  
/* 121:    */  private void gear(float inner_radius, float outer_radius, float width, int teeth, float tooth_depth)
/* 122:    */  {
/* 123:123 */    float r0 = inner_radius;
/* 124:124 */    float r1 = outer_radius - tooth_depth / 2.0F;
/* 125:125 */    float r2 = outer_radius + tooth_depth / 2.0F;
/* 126:    */    
/* 127:127 */    float da = 6.283186F / teeth / 4.0F;
/* 128:    */    
/* 129:129 */    GL11.glShadeModel(7424);
/* 130:    */    
/* 131:131 */    GL11.glNormal3f(0.0F, 0.0F, 1.0F);
/* 132:    */    
/* 134:134 */    GL11.glBegin(8);
/* 135:135 */    for (int i = 0; i <= teeth; i++) {
/* 136:136 */      float angle = i * 2.0F * 3.141593F / teeth;
/* 137:137 */      GL11.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), width * 0.5F);
/* 138:138 */      GL11.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), width * 0.5F);
/* 139:139 */      if (i < teeth) {
/* 140:140 */        GL11.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), width * 0.5F);
/* 141:141 */        GL11.glVertex3f(r1 * (float)Math.cos(angle + 3.0F * da), r1 * (float)Math.sin(angle + 3.0F * da), width * 0.5F);
/* 142:    */      }
/* 143:    */    }
/* 144:    */    
/* 145:145 */    GL11.glEnd();
/* 146:    */    
/* 148:148 */    GL11.glBegin(7);
/* 149:149 */    for (i = 0; i < teeth; i++) {
/* 150:150 */      float angle = i * 2.0F * 3.141593F / teeth;
/* 151:151 */      GL11.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), width * 0.5F);
/* 152:152 */      GL11.glVertex3f(r2 * (float)Math.cos(angle + da), r2 * (float)Math.sin(angle + da), width * 0.5F);
/* 153:153 */      GL11.glVertex3f(r2 * (float)Math.cos(angle + 2.0F * da), r2 * (float)Math.sin(angle + 2.0F * da), width * 0.5F);
/* 154:154 */      GL11.glVertex3f(r1 * (float)Math.cos(angle + 3.0F * da), r1 * (float)Math.sin(angle + 3.0F * da), width * 0.5F);
/* 155:    */    }
/* 156:156 */    GL11.glEnd();
/* 157:    */    
/* 159:159 */    GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/* 160:160 */    GL11.glBegin(8);
/* 161:161 */    for (i = 0; i <= teeth; i++) {
/* 162:162 */      float angle = i * 2.0F * 3.141593F / teeth;
/* 163:163 */      GL11.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), -width * 0.5F);
/* 164:164 */      GL11.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), -width * 0.5F);
/* 165:165 */      GL11.glVertex3f(r1 * (float)Math.cos(angle + 3.0F * da), r1 * (float)Math.sin(angle + 3.0F * da), -width * 0.5F);
/* 166:166 */      GL11.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), -width * 0.5F);
/* 167:    */    }
/* 168:168 */    GL11.glEnd();
/* 169:    */    
/* 171:171 */    GL11.glBegin(7);
/* 172:172 */    for (i = 0; i < teeth; i++) {
/* 173:173 */      float angle = i * 2.0F * 3.141593F / teeth;
/* 174:174 */      GL11.glVertex3f(r1 * (float)Math.cos(angle + 3.0F * da), r1 * (float)Math.sin(angle + 3.0F * da), -width * 0.5F);
/* 175:175 */      GL11.glVertex3f(r2 * (float)Math.cos(angle + 2.0F * da), r2 * (float)Math.sin(angle + 2.0F * da), -width * 0.5F);
/* 176:176 */      GL11.glVertex3f(r2 * (float)Math.cos(angle + da), r2 * (float)Math.sin(angle + da), -width * 0.5F);
/* 177:177 */      GL11.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), -width * 0.5F);
/* 178:    */    }
/* 179:179 */    GL11.glEnd();
/* 180:180 */    GL11.glNormal3f(0.0F, 0.0F, 1.0F);
/* 181:    */    
/* 183:183 */    GL11.glBegin(8);
/* 184:184 */    for (i = 0; i < teeth; i++) {
/* 185:185 */      float angle = i * 2.0F * 3.141593F / teeth;
/* 186:186 */      GL11.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), width * 0.5F);
/* 187:187 */      GL11.glVertex3f(r1 * (float)Math.cos(angle), r1 * (float)Math.sin(angle), -width * 0.5F);
/* 188:188 */      float u = r2 * (float)Math.cos(angle + da) - r1 * (float)Math.cos(angle);
/* 189:189 */      float v = r2 * (float)Math.sin(angle + da) - r1 * (float)Math.sin(angle);
/* 190:190 */      float len = (float)Math.sqrt(u * u + v * v);
/* 191:191 */      u /= len;
/* 192:192 */      v /= len;
/* 193:193 */      GL11.glNormal3f(v, -u, 0.0F);
/* 194:194 */      GL11.glVertex3f(r2 * (float)Math.cos(angle + da), r2 * (float)Math.sin(angle + da), width * 0.5F);
/* 195:195 */      GL11.glVertex3f(r2 * (float)Math.cos(angle + da), r2 * (float)Math.sin(angle + da), -width * 0.5F);
/* 196:196 */      GL11.glNormal3f((float)Math.cos(angle), (float)Math.sin(angle), 0.0F);
/* 197:197 */      GL11.glVertex3f(r2 * (float)Math.cos(angle + 2.0F * da), r2 * (float)Math.sin(angle + 2.0F * da), width * 0.5F);
/* 198:198 */      GL11.glVertex3f(r2 * (float)Math.cos(angle + 2.0F * da), r2 * (float)Math.sin(angle + 2.0F * da), -width * 0.5F);
/* 199:199 */      u = r1 * (float)Math.cos(angle + 3.0F * da) - r2 * (float)Math.cos(angle + 2.0F * da);
/* 200:200 */      v = r1 * (float)Math.sin(angle + 3.0F * da) - r2 * (float)Math.sin(angle + 2.0F * da);
/* 201:201 */      GL11.glNormal3f(v, -u, 0.0F);
/* 202:202 */      GL11.glVertex3f(r1 * (float)Math.cos(angle + 3.0F * da), r1 * (float)Math.sin(angle + 3.0F * da), width * 0.5F);
/* 203:203 */      GL11.glVertex3f(r1 * (float)Math.cos(angle + 3.0F * da), r1 * (float)Math.sin(angle + 3.0F * da), -width * 0.5F);
/* 204:204 */      GL11.glNormal3f((float)Math.cos(angle), (float)Math.sin(angle), 0.0F);
/* 205:    */    }
/* 206:206 */    GL11.glVertex3f(r1 * (float)Math.cos(0.0D), r1 * (float)Math.sin(0.0D), width * 0.5F);
/* 207:207 */    GL11.glVertex3f(r1 * (float)Math.cos(0.0D), r1 * (float)Math.sin(0.0D), -width * 0.5F);
/* 208:208 */    GL11.glEnd();
/* 209:    */    
/* 210:210 */    GL11.glShadeModel(7425);
/* 211:    */    
/* 213:213 */    GL11.glBegin(8);
/* 214:214 */    for (i = 0; i <= teeth; i++) {
/* 215:215 */      float angle = i * 2.0F * 3.141593F / teeth;
/* 216:216 */      GL11.glNormal3f(-(float)Math.cos(angle), -(float)Math.sin(angle), 0.0F);
/* 217:217 */      GL11.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), -width * 0.5F);
/* 218:218 */      GL11.glVertex3f(r0 * (float)Math.cos(angle), r0 * (float)Math.sin(angle), width * 0.5F);
/* 219:    */    }
/* 220:220 */    GL11.glEnd();
/* 221:    */  }
/* 222:    */  
/* 225:    */  public void update(GameContainer container, int delta)
/* 226:    */  {
/* 227:227 */    this.rot += delta * 0.1F;
/* 228:    */  }
/* 229:    */  
/* 233:    */  public static void main(String[] argv)
/* 234:    */  {
/* 235:    */    try
/* 236:    */    {
/* 237:237 */      AppGameContainer container = new AppGameContainer(new SlickCallableTest());
/* 238:238 */      container.setDisplayMode(800, 600, false);
/* 239:239 */      container.start();
/* 240:    */    } catch (SlickException e) {
/* 241:241 */      e.printStackTrace();
/* 242:    */    }
/* 243:    */  }
/* 244:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.SlickCallableTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */