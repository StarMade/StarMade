/*     */ package org.jaxen.saxpath;
/*     */ 
/*     */ public class XPathSyntaxException extends SAXPathException
/*     */ {
/*     */   private static final long serialVersionUID = 3567675610742422397L;
/*     */   private String xpath;
/*     */   private int position;
/*  65 */   private static final String lineSeparator = System.getProperty("line.separator");
/*     */ 
/*     */   public XPathSyntaxException(String xpath, int position, String message)
/*     */   {
/*  78 */     super(message);
/*  79 */     this.position = position;
/*  80 */     this.xpath = xpath;
/*     */   }
/*     */ 
/*     */   public int getPosition()
/*     */   {
/*  94 */     return this.position;
/*     */   }
/*     */ 
/*     */   public String getXPath()
/*     */   {
/* 106 */     return this.xpath;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 111 */     return getClass() + ": " + getXPath() + ": " + getPosition() + ": " + getMessage();
/*     */   }
/*     */ 
/*     */   private String getPositionMarker()
/*     */   {
/* 125 */     int pos = getPosition();
/* 126 */     StringBuffer buf = new StringBuffer(pos + 1);
/* 127 */     for (int i = 0; i < pos; i++)
/*     */     {
/* 129 */       buf.append(" ");
/*     */     }
/*     */ 
/* 132 */     buf.append("^");
/*     */ 
/* 134 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String getMultilineMessage()
/*     */   {
/* 148 */     StringBuffer buf = new StringBuffer();
/*     */ 
/* 150 */     buf.append(getMessage());
/* 151 */     buf.append(lineSeparator);
/* 152 */     buf.append(getXPath());
/* 153 */     buf.append(lineSeparator);
/*     */ 
/* 155 */     buf.append(getPositionMarker());
/*     */ 
/* 157 */     return buf.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.XPathSyntaxException
 * JD-Core Version:    0.6.2
 */