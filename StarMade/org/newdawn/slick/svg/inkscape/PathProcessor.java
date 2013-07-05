/*     */ package org.newdawn.slick.svg.inkscape;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.StringTokenizer;
/*     */ import org.newdawn.slick.geom.Path;
/*     */ import org.newdawn.slick.geom.Shape;
/*     */ import org.newdawn.slick.geom.Transform;
/*     */ import org.newdawn.slick.svg.Diagram;
/*     */ import org.newdawn.slick.svg.Figure;
/*     */ import org.newdawn.slick.svg.Loader;
/*     */ import org.newdawn.slick.svg.NonGeometricData;
/*     */ import org.newdawn.slick.svg.ParsingException;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ public class PathProcessor
/*     */   implements ElementProcessor
/*     */ {
/*     */   private static Path processPoly(Element element, StringTokenizer tokens)
/*     */     throws ParsingException
/*     */   {
/*  32 */     int count = 0;
/*     */ 
/*  34 */     ArrayList pts = new ArrayList();
/*  35 */     boolean moved = false;
/*  36 */     boolean reasonToBePath = false;
/*  37 */     Path path = null;
/*     */ 
/*  39 */     while (tokens.hasMoreTokens()) {
/*     */       try {
/*  41 */         String nextToken = tokens.nextToken();
/*  42 */         if (nextToken.equals("L")) {
/*  43 */           float x = Float.parseFloat(tokens.nextToken());
/*  44 */           float y = Float.parseFloat(tokens.nextToken());
/*  45 */           path.lineTo(x, y);
/*     */         }
/*  48 */         else if (nextToken.equals("z")) {
/*  49 */           path.close();
/*     */         }
/*  52 */         else if (nextToken.equals("M")) {
/*  53 */           if (!moved) {
/*  54 */             moved = true;
/*  55 */             float x = Float.parseFloat(tokens.nextToken());
/*  56 */             float y = Float.parseFloat(tokens.nextToken());
/*  57 */             path = new Path(x, y);
/*     */           }
/*     */           else
/*     */           {
/*  61 */             reasonToBePath = true;
/*  62 */             float x = Float.parseFloat(tokens.nextToken());
/*  63 */             float y = Float.parseFloat(tokens.nextToken());
/*  64 */             path.startHole(x, y);
/*     */           }
/*     */ 
/*     */         }
/*  68 */         else if (nextToken.equals("C")) {
/*  69 */           reasonToBePath = true;
/*  70 */           float cx1 = Float.parseFloat(tokens.nextToken());
/*  71 */           float cy1 = Float.parseFloat(tokens.nextToken());
/*  72 */           float cx2 = Float.parseFloat(tokens.nextToken());
/*  73 */           float cy2 = Float.parseFloat(tokens.nextToken());
/*  74 */           float x = Float.parseFloat(tokens.nextToken());
/*  75 */           float y = Float.parseFloat(tokens.nextToken());
/*  76 */           path.curveTo(x, y, cx1, cy1, cx2, cy2);
/*     */         }
/*     */       }
/*     */       catch (NumberFormatException e) {
/*  80 */         throw new ParsingException(element.getAttribute("id"), "Invalid token in points list", e);
/*     */       }
/*     */     }
/*     */ 
/*  84 */     if (!reasonToBePath) {
/*  85 */       return null;
/*     */     }
/*     */ 
/*  88 */     return path;
/*     */   }
/*     */ 
/*     */   public void process(Loader loader, Element element, Diagram diagram, Transform t)
/*     */     throws ParsingException
/*     */   {
/*  96 */     Transform transform = Util.getTransform(element);
/*  97 */     transform = new Transform(t, transform);
/*     */ 
/*  99 */     String points = element.getAttribute("points");
/* 100 */     if (element.getNodeName().equals("path")) {
/* 101 */       points = element.getAttribute("d");
/*     */     }
/*     */ 
/* 104 */     StringTokenizer tokens = new StringTokenizer(points, ", ");
/* 105 */     Path path = processPoly(element, tokens);
/* 106 */     NonGeometricData data = Util.getNonGeometricData(element);
/* 107 */     if (path != null) {
/* 108 */       Shape shape = path.transform(transform);
/*     */ 
/* 110 */       diagram.addFigure(new Figure(4, shape, data, transform));
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean handles(Element element)
/*     */   {
/* 118 */     if ((element.getNodeName().equals("path")) && 
/* 119 */       (!"arc".equals(element.getAttributeNS("http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd", "type")))) {
/* 120 */       return true;
/*     */     }
/*     */ 
/* 124 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.PathProcessor
 * JD-Core Version:    0.6.2
 */