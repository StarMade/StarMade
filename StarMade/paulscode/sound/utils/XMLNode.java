/*     */ package paulscode.sound.utils;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.SoundSystemLogger;
/*     */ 
/*     */ public class XMLNode
/*     */ {
/*     */   private static SoundSystemLogger logger;
/*  56 */   private static boolean verbose = false;
/*     */ 
/*  61 */   protected String name = "";
/*     */ 
/*  65 */   protected String contents = "";
/*     */ 
/*  69 */   protected HashMap<String, String> parameters = null;
/*     */ 
/*  73 */   protected XMLNode next = null;
/*     */ 
/*     */   public XMLNode(String XMLText)
/*     */   {
/*  85 */     int lBracket = XMLText.indexOf("<");
/*  86 */     int rBracket = XMLText.indexOf(">");
/*     */ 
/*  89 */     if ((lBracket == -1) || (rBracket == -1)) {
/*  90 */       return;
/*     */     }
/*     */ 
/*  93 */     if (rBracket <= lBracket)
/*     */     {
/*  95 */       errorMessage("Invalid XML syntax: '>' before '<'");
/*  96 */       return;
/*     */     }
/*     */ 
/* 100 */     this.parameters = new HashMap();
/*     */ 
/* 103 */     this.contents = XMLText.substring(lBracket + 1, rBracket);
/* 104 */     processTagContents(this.contents);
/*     */ 
/* 106 */     if (verbose) {
/* 107 */       displayParameters();
/*     */     }
/*     */ 
/* 110 */     if (rBracket + 1 >= XMLText.length())
/* 111 */       return;
/* 112 */     String XMLRemainder = XMLText.substring(rBracket + 1);
/*     */ 
/* 115 */     lBracket = XMLRemainder.indexOf("<");
/* 116 */     if (lBracket == -1) {
/* 117 */       return;
/*     */     }
/*     */ 
/* 120 */     this.next = new XMLNode(XMLRemainder.substring(lBracket));
/*     */   }
/*     */ 
/*     */   public String name()
/*     */   {
/* 129 */     if (this.name == null) {
/* 130 */       return "";
/*     */     }
/* 132 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String contents()
/*     */   {
/* 141 */     if (this.contents == null) {
/* 142 */       return "";
/*     */     }
/* 144 */     return this.contents;
/*     */   }
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/* 153 */     return this.next != null;
/*     */   }
/*     */ 
/*     */   public XMLNode next()
/*     */   {
/* 162 */     return this.next;
/*     */   }
/*     */ 
/*     */   public String get(String parameter)
/*     */   {
/* 171 */     if (this.parameters == null) {
/* 172 */       return "";
/*     */     }
/* 174 */     return (String)this.parameters.get(parameter.toUpperCase());
/*     */   }
/*     */ 
/*     */   public HashMap<String, String> parameters()
/*     */   {
/* 183 */     return this.parameters;
/*     */   }
/*     */ 
/*     */   public void displayParameters()
/*     */   {
/* 192 */     System.out.println("Parameters for " + this.name + ":");
/*     */ 
/* 195 */     Set keys = this.parameters.keySet();
/* 196 */     Iterator iter = keys.iterator();
/*     */ 
/* 201 */     if (!iter.hasNext()) {
/* 202 */       System.out.println("    (none)");
/*     */     }
/*     */ 
/* 205 */     while (iter.hasNext())
/*     */     {
/* 207 */       String par = (String)iter.next();
/* 208 */       String val = (String)this.parameters.get(par);
/* 209 */       System.out.println("    " + par + " = " + val);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void processTagContents(String tagContents)
/*     */   {
/* 220 */     String[] splitTag = XMLParser.seperateWords(tagContents);
/*     */     int x;
/* 221 */     if (splitTag.length > 0)
/*     */     {
/* 224 */       this.name = splitTag[0];
/*     */ 
/* 227 */       if ((this.name.length() >= 3) && (this.name.substring(0, 3).equals("!--"))) {
/* 228 */         return;
/*     */       }
/*     */ 
/* 231 */       for (x = 1; x < splitTag.length; )
/*     */       {
/* 233 */         String paramText = splitTag[x];
/* 234 */         if (paramText.equals("/"))
/*     */           break;
/* 236 */         if (paramText.contains("="))
/*     */         {
/* 238 */           String[] pair = paramText.split("=");
/* 239 */           if ((pair == null) || (pair.length == 0))
/*     */           {
/* 241 */             errorMessage("Invalid XML syntax: paramater null");
/* 242 */             return;
/*     */           }
/* 244 */           if (pair.length == 1)
/*     */           {
/* 246 */             if (x + 1 >= splitTag.length)
/*     */             {
/* 248 */               warningMessage("Value not specified for parameter '" + pair[0] + "'");
/*     */ 
/* 250 */               this.parameters.put(pair[0].toUpperCase(), "");
/* 251 */               x++;
/*     */             }
/*     */             else
/*     */             {
/* 255 */               this.parameters.put(pair[0].toUpperCase(), splitTag[(x + 1)]);
/*     */ 
/* 257 */               x += 2;
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 262 */             this.parameters.put(pair[0].toUpperCase(), pair[1]);
/* 263 */             x++;
/*     */           }
/*     */ 
/*     */         }
/* 268 */         else if (x + 1 >= splitTag.length)
/*     */         {
/* 270 */           warningMessage("Value not specified for parameter '" + splitTag[x] + "'");
/*     */ 
/* 272 */           this.parameters.put(splitTag[x].toUpperCase(), "");
/* 273 */           x++;
/*     */         }
/* 277 */         else if (splitTag[(x + 1)].equals("="))
/*     */         {
/* 279 */           if (x + 2 >= splitTag.length)
/*     */           {
/* 281 */             warningMessage("Value not specified for parameter '" + splitTag[x] + "'");
/*     */ 
/* 284 */             this.parameters.put(splitTag[x].toUpperCase(), "");
/* 285 */             x += 2;
/*     */           }
/*     */           else
/*     */           {
/* 289 */             this.parameters.put(splitTag[x].toUpperCase(), splitTag[(x + 2)]);
/*     */ 
/* 291 */             x += 3;
/*     */           }
/*     */         }
/* 294 */         else if (splitTag[(x + 1)].contains("="))
/*     */         {
/* 296 */           String[] eq = splitTag[(x + 1)].split("=");
/* 297 */           if ((eq == null) || (eq.length < 1))
/*     */           {
/* 299 */             warningMessage("Value not specified for parameter '" + splitTag[x] + "'");
/*     */ 
/* 302 */             this.parameters.put(splitTag[x].toUpperCase(), "");
/* 303 */             x += 2;
/*     */           }
/*     */           else
/*     */           {
/* 307 */             this.parameters.put(splitTag[x].toUpperCase(), eq[1]);
/*     */ 
/* 309 */             x += 2;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 314 */           warningMessage("Value not specified for parameter '" + splitTag[x] + "'");
/*     */ 
/* 316 */           this.parameters.put(splitTag[x].toUpperCase(), "");
/* 317 */           x++;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setVerbose(boolean val)
/*     */   {
/* 331 */     verbose = val;
/*     */   }
/*     */ 
/*     */   protected static void errorMessage(String message)
/*     */   {
/* 341 */     if (logger == null) {
/* 342 */       logger = SoundSystemConfig.getLogger();
/*     */     }
/* 344 */     if (logger == null)
/*     */     {
/* 346 */       logger = new SoundSystemLogger();
/* 347 */       SoundSystemConfig.setLogger(logger);
/*     */     }
/* 349 */     logger.errorMessage("XMLNode", message, 0);
/*     */   }
/*     */ 
/*     */   protected static void warningMessage(String message)
/*     */   {
/* 359 */     if (logger == null) {
/* 360 */       logger = SoundSystemConfig.getLogger();
/*     */     }
/* 362 */     if (logger == null)
/*     */     {
/* 364 */       logger = new SoundSystemLogger();
/* 365 */       SoundSystemConfig.setLogger(logger);
/*     */     }
/* 367 */     logger.importantMessage("Warning in class 'XMLNode': " + message, 0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.utils.XMLNode
 * JD-Core Version:    0.6.2
 */