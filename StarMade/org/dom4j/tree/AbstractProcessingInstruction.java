/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.ProcessingInstruction;
/*     */ import org.dom4j.Visitor;
/*     */ 
/*     */ public abstract class AbstractProcessingInstruction extends AbstractNode
/*     */   implements ProcessingInstruction
/*     */ {
/*     */   public short getNodeType()
/*     */   {
/*  36 */     return 7;
/*     */   }
/*     */ 
/*     */   public String getPath(Element context) {
/*  40 */     Element parent = getParent();
/*     */ 
/*  42 */     return (parent != null) && (parent != context) ? parent.getPath(context) + "/processing-instruction()" : "processing-instruction()";
/*     */   }
/*     */ 
/*     */   public String getUniquePath(Element context)
/*     */   {
/*  48 */     Element parent = getParent();
/*     */ 
/*  50 */     return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/processing-instruction()" : "processing-instruction()";
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*  56 */     return super.toString() + " [ProcessingInstruction: &" + getName() + ";]";
/*     */   }
/*     */ 
/*     */   public String asXML()
/*     */   {
/*  61 */     return "<?" + getName() + " " + getText() + "?>";
/*     */   }
/*     */ 
/*     */   public void write(Writer writer) throws IOException {
/*  65 */     writer.write("<?");
/*  66 */     writer.write(getName());
/*  67 */     writer.write(" ");
/*  68 */     writer.write(getText());
/*  69 */     writer.write("?>");
/*     */   }
/*     */ 
/*     */   public void accept(Visitor visitor) {
/*  73 */     visitor.visit(this);
/*     */   }
/*     */ 
/*     */   public void setValue(String name, String value) {
/*  77 */     throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
/*     */   }
/*     */ 
/*     */   public void setValues(Map data)
/*     */   {
/*  82 */     throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  87 */     return getTarget();
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  91 */     setTarget(name);
/*     */   }
/*     */ 
/*     */   public boolean removeValue(String name) {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   protected String toString(Map values)
/*     */   {
/* 111 */     StringBuffer buffer = new StringBuffer();
/*     */ 
/* 113 */     for (Iterator iter = values.entrySet().iterator(); iter.hasNext(); ) {
/* 114 */       Map.Entry entry = (Map.Entry)iter.next();
/* 115 */       String name = (String)entry.getKey();
/* 116 */       String value = (String)entry.getValue();
/*     */ 
/* 118 */       buffer.append(name);
/* 119 */       buffer.append("=\"");
/* 120 */       buffer.append(value);
/* 121 */       buffer.append("\" ");
/*     */     }
/*     */ 
/* 125 */     buffer.setLength(buffer.length() - 1);
/*     */ 
/* 127 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   protected Map parseValues(String text)
/*     */   {
/* 141 */     Map data = new HashMap();
/*     */ 
/* 143 */     StringTokenizer s = new StringTokenizer(text, " ='\"", true);
/*     */ 
/* 145 */     while (s.hasMoreTokens()) {
/* 146 */       String name = getName(s);
/*     */ 
/* 148 */       if (s.hasMoreTokens()) {
/* 149 */         String value = getValue(s);
/* 150 */         data.put(name, value);
/*     */       }
/*     */     }
/*     */ 
/* 154 */     return data;
/*     */   }
/*     */ 
/*     */   private String getName(StringTokenizer tokenizer) {
/* 158 */     String token = tokenizer.nextToken();
/* 159 */     StringBuffer name = new StringBuffer(token);
/*     */ 
/* 161 */     while (tokenizer.hasMoreTokens()) {
/* 162 */       token = tokenizer.nextToken();
/*     */ 
/* 164 */       if (token.equals("=")) break;
/* 165 */       name.append(token);
/*     */     }
/*     */ 
/* 171 */     return name.toString().trim();
/*     */   }
/*     */ 
/*     */   private String getValue(StringTokenizer tokenizer) {
/* 175 */     String token = tokenizer.nextToken();
/* 176 */     StringBuffer value = new StringBuffer();
/*     */ 
/* 180 */     while ((tokenizer.hasMoreTokens()) && (!token.equals("'")) && (!token.equals("\""))) {
/* 181 */       token = tokenizer.nextToken();
/*     */     }
/*     */ 
/* 184 */     String quote = token;
/*     */ 
/* 186 */     while (tokenizer.hasMoreTokens()) {
/* 187 */       token = tokenizer.nextToken();
/*     */ 
/* 189 */       if (quote.equals(token)) break;
/* 190 */       value.append(token);
/*     */     }
/*     */ 
/* 196 */     return value.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractProcessingInstruction
 * JD-Core Version:    0.6.2
 */