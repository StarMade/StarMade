/*     */ import java.io.PrintStream;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.swing.text.MutableAttributeSet;
/*     */ import javax.swing.text.html.HTML.Tag;
/*     */ import javax.swing.text.html.HTMLEditorKit.ParserCallback;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Node;
/*     */ import org.schema.game.common.api.ApiController;
/*     */ import org.w3c.tidy.Tidy;
/*     */ 
/*     */ public final class sq extends HTMLEditorKit.ParserCallback
/*     */ {
/*     */   private HTML.Tag a;
/*     */ 
/*     */   public static ArrayList a()
/*     */   {
/*  57 */     ApiController.a(localObject1 = new jp());
/*     */ 
/*  58 */     System.err.println("Session: " + localObject1 + "\n");
/*  59 */     (
/*  62 */       localObject1 = ApiController.a((jp)localObject1))
/*  62 */       .getRootElement();
/*     */ 
/*  65 */     Object localObject1 = ((Document)localObject1).selectNodes("//result/item");
/*     */ 
/*  68 */     ArrayList localArrayList = new ArrayList();
/*  69 */     for (localObject1 = ((List)localObject1).iterator(); ((Iterator)localObject1).hasNext(); ) {
/*  70 */       Object localObject3 = (
/*  70 */         localObject2 = (Node)((Iterator)localObject1).next())
/*  70 */         .selectSingleNode("Body");
/*  71 */       Object localObject2 = ((Node)localObject2).selectSingleNode("node_title");
/*     */ 
/*  73 */       Tidy localTidy = new Tidy();
/*  74 */       localObject3 = new StringReader(((Node)localObject3).getText().replaceAll("Â", ""));
/*     */ 
/*  76 */       StringWriter localStringWriter = new StringWriter();
/*     */ 
/*  78 */       localTidy.parse((Reader)localObject3, localStringWriter);
/*     */ 
/*  86 */       localObject2 = localStringWriter.getBuffer().toString().replaceFirst("<body>", "<body>\n<h1>" + ((Node)localObject2).getText() + "</h1>");
/*     */ 
/* 108 */       localArrayList.add(localObject2);
/*     */     }
/*     */ 
/* 113 */     return localArrayList;
/*     */   }
/*     */ 
/*     */   public final void handleEndOfLineString(String paramString)
/*     */   {
/* 190 */     super.handleEndOfLineString(paramString);
/* 191 */     null.delete(0, null.indexOf("\n") + 3);
/* 192 */     paramString = "Log in or register to post comments";
/* 193 */     int i = 0;
/* 194 */     while ((i = null.indexOf(paramString)) >= 0)
/* 195 */       null.delete(i, i + paramString.length());
/*     */   }
/*     */ 
/*     */   public final void handleEndTag(HTML.Tag paramTag, int paramInt)
/*     */   {
/* 205 */     if (this.a == paramTag) {
/* 206 */       null.append("\n\n");
/* 207 */       this.a = null;
/*     */     }
/*     */ 
/* 210 */     super.handleEndTag(paramTag, paramInt);
/*     */   }
/*     */ 
/*     */   public final void handleStartTag(HTML.Tag paramTag, MutableAttributeSet paramMutableAttributeSet, int paramInt)
/*     */   {
/*     */     String str;
/* 225 */     if ((
/* 225 */       str = paramMutableAttributeSet.toString())
/* 225 */       .contains("class=meta submitted")) {
/* 226 */       this.a = paramTag;
/* 227 */       null.append("\n");
/*     */     }
/* 229 */     if (str.contains("node node-news-entry")) {
/* 230 */       null.append("\n\n\n");
/* 231 */       this.a = paramTag;
/*     */     }
/* 233 */     super.handleStartTag(paramTag, paramMutableAttributeSet, paramInt);
/*     */   }
/*     */ 
/*     */   public final void handleText(char[] paramArrayOfChar, int paramInt)
/*     */   {
/* 238 */     null.append(paramArrayOfChar);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  51 */     new SimpleDateFormat("M/d/yyyy - h:mm a");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sq
 * JD-Core Version:    0.6.2
 */