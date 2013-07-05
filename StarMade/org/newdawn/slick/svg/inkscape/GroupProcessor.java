/*    */ package org.newdawn.slick.svg.inkscape;
/*    */ 
/*    */ import org.newdawn.slick.geom.Transform;
/*    */ import org.newdawn.slick.svg.Diagram;
/*    */ import org.newdawn.slick.svg.Loader;
/*    */ import org.newdawn.slick.svg.ParsingException;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ public class GroupProcessor
/*    */   implements ElementProcessor
/*    */ {
/*    */   public boolean handles(Element element)
/*    */   {
/* 20 */     if (element.getNodeName().equals("g")) {
/* 21 */       return true;
/*    */     }
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   public void process(Loader loader, Element element, Diagram diagram, Transform t)
/*    */     throws ParsingException
/*    */   {
/* 30 */     Transform transform = Util.getTransform(element);
/* 31 */     transform = new Transform(t, transform);
/*    */ 
/* 33 */     loader.loadChildren(element, transform);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.GroupProcessor
 * JD-Core Version:    0.6.2
 */