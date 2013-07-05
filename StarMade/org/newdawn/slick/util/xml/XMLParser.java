/*    */ package org.newdawn.slick.util.xml;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import javax.xml.parsers.DocumentBuilder;
/*    */ import javax.xml.parsers.DocumentBuilderFactory;
/*    */ import org.newdawn.slick.SlickException;
/*    */ import org.newdawn.slick.util.ResourceLoader;
/*    */ import org.w3c.dom.Document;
/*    */ 
/*    */ public class XMLParser
/*    */ {
/*    */   private static DocumentBuilderFactory factory;
/*    */ 
/*    */   public XMLElement parse(String ref)
/*    */     throws SlickException
/*    */   {
/* 38 */     return parse(ref, ResourceLoader.getResourceAsStream(ref));
/*    */   }
/*    */ 
/*    */   public XMLElement parse(String name, InputStream in)
/*    */     throws SlickXMLException
/*    */   {
/*    */     try
/*    */     {
/* 52 */       if (factory == null) {
/* 53 */         factory = DocumentBuilderFactory.newInstance();
/*    */       }
/* 55 */       DocumentBuilder builder = factory.newDocumentBuilder();
/* 56 */       Document doc = builder.parse(in);
/*    */ 
/* 58 */       return new XMLElement(doc.getDocumentElement());
/*    */     } catch (Exception e) {
/* 60 */       throw new SlickXMLException("Failed to parse document: " + name, e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.xml.XMLParser
 * JD-Core Version:    0.6.2
 */