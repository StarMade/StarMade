/*     */ package org.newdawn.slick.tests;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.newdawn.slick.AppGameContainer;
/*     */ import org.newdawn.slick.BasicGame;
/*     */ import org.newdawn.slick.Color;
/*     */ import org.newdawn.slick.GameContainer;
/*     */ import org.newdawn.slick.Graphics;
/*     */ import org.newdawn.slick.Input;
/*     */ import org.newdawn.slick.SlickException;
/*     */ import org.newdawn.slick.geom.Circle;
/*     */ import org.newdawn.slick.geom.GeomUtil;
/*     */ import org.newdawn.slick.geom.GeomUtilListener;
/*     */ import org.newdawn.slick.geom.Polygon;
/*     */ import org.newdawn.slick.geom.Rectangle;
/*     */ import org.newdawn.slick.geom.Shape;
/*     */ import org.newdawn.slick.geom.Transform;
/*     */ import org.newdawn.slick.geom.Vector2f;
/*     */ 
/*     */ public class GeomUtilTest extends BasicGame
/*     */   implements GeomUtilListener
/*     */ {
/*     */   private Shape source;
/*     */   private Shape cut;
/*     */   private Shape[] result;
/*  35 */   private ArrayList points = new ArrayList();
/*     */ 
/*  37 */   private ArrayList marks = new ArrayList();
/*     */ 
/*  39 */   private ArrayList exclude = new ArrayList();
/*     */   private boolean dynamic;
/*  44 */   private GeomUtil util = new GeomUtil();
/*     */   private int xp;
/*     */   private int yp;
/*     */   private Circle circle;
/*     */   private Shape rect;
/*     */   private Polygon star;
/*     */   private boolean union;
/*     */ 
/*     */   public GeomUtilTest()
/*     */   {
/*  63 */     super("GeomUtilTest");
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  70 */     Polygon source = new Polygon();
/*  71 */     source.addPoint(100.0F, 100.0F);
/*  72 */     source.addPoint(150.0F, 80.0F);
/*  73 */     source.addPoint(210.0F, 120.0F);
/*  74 */     source.addPoint(340.0F, 150.0F);
/*  75 */     source.addPoint(150.0F, 200.0F);
/*  76 */     source.addPoint(120.0F, 250.0F);
/*  77 */     this.source = source;
/*     */ 
/*  79 */     this.circle = new Circle(0.0F, 0.0F, 50.0F);
/*  80 */     this.rect = new Rectangle(-100.0F, -40.0F, 200.0F, 80.0F);
/*  81 */     this.star = new Polygon();
/*     */ 
/*  83 */     float dis = 40.0F;
/*  84 */     for (int i = 0; i < 360; i += 30) {
/*  85 */       dis = dis == 40.0F ? 60.0F : 40.0F;
/*  86 */       double x = Math.cos(Math.toRadians(i)) * dis;
/*  87 */       double y = Math.sin(Math.toRadians(i)) * dis;
/*  88 */       this.star.addPoint((float)x, (float)y);
/*     */     }
/*     */ 
/*  91 */     this.cut = this.circle;
/*  92 */     this.cut.setLocation(203.0F, 78.0F);
/*  93 */     this.xp = ((int)this.cut.getCenterX());
/*  94 */     this.yp = ((int)this.cut.getCenterY());
/*  95 */     makeBoolean();
/*     */   }
/*     */ 
/*     */   public void init(GameContainer container)
/*     */     throws SlickException
/*     */   {
/* 102 */     this.util.setListener(this);
/* 103 */     init();
/* 104 */     container.setVSync(true);
/*     */   }
/*     */ 
/*     */   public void update(GameContainer container, int delta)
/*     */     throws SlickException
/*     */   {
/* 112 */     if (container.getInput().isKeyPressed(57)) {
/* 113 */       this.dynamic = (!this.dynamic);
/*     */     }
/* 115 */     if (container.getInput().isKeyPressed(28)) {
/* 116 */       this.union = (!this.union);
/* 117 */       makeBoolean();
/*     */     }
/* 119 */     if (container.getInput().isKeyPressed(2)) {
/* 120 */       this.cut = this.circle;
/* 121 */       this.circle.setCenterX(this.xp);
/* 122 */       this.circle.setCenterY(this.yp);
/* 123 */       makeBoolean();
/*     */     }
/* 125 */     if (container.getInput().isKeyPressed(3)) {
/* 126 */       this.cut = this.rect;
/* 127 */       this.rect.setCenterX(this.xp);
/* 128 */       this.rect.setCenterY(this.yp);
/* 129 */       makeBoolean();
/*     */     }
/* 131 */     if (container.getInput().isKeyPressed(4)) {
/* 132 */       this.cut = this.star;
/* 133 */       this.star.setCenterX(this.xp);
/* 134 */       this.star.setCenterY(this.yp);
/* 135 */       makeBoolean();
/*     */     }
/*     */ 
/* 138 */     if (this.dynamic) {
/* 139 */       this.xp = container.getInput().getMouseX();
/* 140 */       this.yp = container.getInput().getMouseY();
/* 141 */       makeBoolean();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void makeBoolean()
/*     */   {
/* 149 */     this.marks.clear();
/* 150 */     this.points.clear();
/* 151 */     this.exclude.clear();
/* 152 */     this.cut.setCenterX(this.xp);
/* 153 */     this.cut.setCenterY(this.yp);
/* 154 */     if (this.union)
/* 155 */       this.result = this.util.union(this.source, this.cut);
/*     */     else
/* 157 */       this.result = this.util.subtract(this.source, this.cut);
/*     */   }
/*     */ 
/*     */   public void render(GameContainer container, Graphics g)
/*     */     throws SlickException
/*     */   {
/* 166 */     g.drawString("Space - toggle movement of cutting shape", 530.0F, 10.0F);
/* 167 */     g.drawString("1,2,3 - select cutting shape", 530.0F, 30.0F);
/* 168 */     g.drawString("Mouse wheel - rotate shape", 530.0F, 50.0F);
/* 169 */     g.drawString("Enter - toggle union/subtract", 530.0F, 70.0F);
/* 170 */     g.drawString(new StringBuilder().append("MODE: ").append(this.union ? "Union" : "Cut").toString(), 530.0F, 200.0F);
/*     */ 
/* 172 */     g.setColor(Color.green);
/* 173 */     g.draw(this.source);
/* 174 */     g.setColor(Color.red);
/* 175 */     g.draw(this.cut);
/*     */ 
/* 177 */     g.setColor(Color.white);
/* 178 */     for (int i = 0; i < this.exclude.size(); i++) {
/* 179 */       Vector2f pt = (Vector2f)this.exclude.get(i);
/* 180 */       g.drawOval(pt.x - 3.0F, pt.y - 3.0F, 7.0F, 7.0F);
/*     */     }
/* 182 */     g.setColor(Color.yellow);
/* 183 */     for (int i = 0; i < this.points.size(); i++) {
/* 184 */       Vector2f pt = (Vector2f)this.points.get(i);
/* 185 */       g.fillOval(pt.x - 1.0F, pt.y - 1.0F, 3.0F, 3.0F);
/*     */     }
/* 187 */     g.setColor(Color.white);
/* 188 */     for (int i = 0; i < this.marks.size(); i++) {
/* 189 */       Vector2f pt = (Vector2f)this.marks.get(i);
/* 190 */       g.fillOval(pt.x - 1.0F, pt.y - 1.0F, 3.0F, 3.0F);
/*     */     }
/*     */ 
/* 193 */     g.translate(0.0F, 300.0F);
/* 194 */     g.setColor(Color.white);
/* 195 */     if (this.result != null) {
/* 196 */       for (int i = 0; i < this.result.length; i++) {
/* 197 */         g.draw(this.result[i]);
/*     */       }
/*     */ 
/* 200 */       g.drawString(new StringBuilder().append("Polys:").append(this.result.length).toString(), 10.0F, 100.0F);
/* 201 */       g.drawString(new StringBuilder().append("X:").append(this.xp).toString(), 10.0F, 120.0F);
/* 202 */       g.drawString(new StringBuilder().append("Y:").append(this.yp).toString(), 10.0F, 130.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] argv)
/*     */   {
/*     */     try
/*     */     {
/* 214 */       AppGameContainer container = new AppGameContainer(new GeomUtilTest());
/* 215 */       container.setDisplayMode(800, 600, false);
/* 216 */       container.start();
/*     */     } catch (SlickException e) {
/* 218 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void pointExcluded(float x, float y) {
/* 223 */     this.exclude.add(new Vector2f(x, y));
/*     */   }
/*     */ 
/*     */   public void pointIntersected(float x, float y) {
/* 227 */     this.marks.add(new Vector2f(x, y));
/*     */   }
/*     */ 
/*     */   public void pointUsed(float x, float y) {
/* 231 */     this.points.add(new Vector2f(x, y));
/*     */   }
/*     */ 
/*     */   public void mouseWheelMoved(int change) {
/* 235 */     if (this.dynamic)
/* 236 */       if (change < 0)
/* 237 */         this.cut = this.cut.transform(Transform.createRotateTransform((float)Math.toRadians(10.0D), this.cut.getCenterX(), this.cut.getCenterY()));
/*     */       else
/* 239 */         this.cut = this.cut.transform(Transform.createRotateTransform((float)Math.toRadians(-10.0D), this.cut.getCenterX(), this.cut.getCenterY()));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.GeomUtilTest
 * JD-Core Version:    0.6.2
 */