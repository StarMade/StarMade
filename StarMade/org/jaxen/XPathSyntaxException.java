/*     */ package org.jaxen;
/*     */ 
/*     */ public class XPathSyntaxException extends JaxenException
/*     */ {
/*     */   private static final long serialVersionUID = 1980601567207604059L;
/*     */   private String xpath;
/*     */   private int position;
/*     */ 
/*     */   public XPathSyntaxException(org.jaxen.saxpath.XPathSyntaxException e)
/*     */   {
/*  77 */     super(e);
/*     */ 
/*  79 */     this.xpath = e.getXPath();
/*  80 */     this.position = e.getPosition();
/*     */   }
/*     */ 
/*     */   public XPathSyntaxException(String xpath, int position, String message)
/*     */   {
/*  93 */     super(message);
/*     */ 
/*  95 */     this.xpath = xpath;
/*  96 */     this.position = position;
/*     */   }
/*     */ 
/*     */   public int getPosition()
/*     */   {
/* 105 */     return this.position;
/*     */   }
/*     */ 
/*     */   public String getXPath()
/*     */   {
/* 114 */     return this.xpath;
/*     */   }
/*     */ 
/*     */   public String getPositionMarker()
/*     */   {
/* 131 */     StringBuffer buf = new StringBuffer();
/*     */ 
/* 133 */     int pos = getPosition();
/*     */ 
/* 135 */     for (int i = 0; i < pos; i++)
/*     */     {
/* 137 */       buf.append(" ");
/*     */     }
/*     */ 
/* 140 */     buf.append("^");
/*     */ 
/* 142 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public String getMultilineMessage()
/*     */   {
/* 159 */     StringBuffer buf = new StringBuffer(getMessage());
/* 160 */     buf.append("\n");
/* 161 */     buf.append(getXPath());
/* 162 */     buf.append("\n");
/*     */ 
/* 164 */     buf.append(getPositionMarker());
/*     */ 
/* 166 */     return buf.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.XPathSyntaxException
 * JD-Core Version:    0.6.2
 */