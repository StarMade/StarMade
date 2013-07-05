/*     */ package org.jasypt.web.pbeconfig;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.jasypt.encryption.pbe.config.WebPBEConfig;
/*     */ import org.jasypt.exceptions.EncryptionInitializationException;
/*     */ 
/*     */ final class WebPBEConfigHtmlUtils
/*     */ {
/*     */   public static final String PASSWORD_SETTING_FLAG = "jasyptPwSetting";
/*     */   public static final String VALIDATION_PREFIX = "jasyptVa";
/*     */   public static final String PASSWORD_PREFIX = "jasyptPw";
/*     */   public static final String PASSWORD_RETYPED_PREFIX = "jasyptRPw";
/*     */   private static final String HTTPS_SCHEME = "https";
/*     */ 
/*     */   public static String createConfigurationDoneHtml()
/*     */   {
/*  59 */     StringBuffer strBuff = new StringBuffer();
/*  60 */     addHeader(strBuff);
/*  61 */     strBuff.append("   <h2>All Configuration Done</h2>\n");
/*  62 */     addFoot(strBuff);
/*  63 */     return strBuff.toString();
/*     */   }
/*     */ 
/*     */   public static String createInputFormHtml(HttpServletRequest request, boolean inputError)
/*     */   {
/*  74 */     WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
/*  75 */     List configs = registry.getConfigs();
/*     */ 
/*  77 */     StringBuffer strBuff = new StringBuffer();
/*  78 */     addHeader(strBuff);
/*     */ 
/*  80 */     strBuff.append("   <h2>Please enter the PBE configuration parameters</h2>\n");
/*     */ 
/*  82 */     if (!"https".equals(request.getScheme().toLowerCase())) {
/*  83 */       strBuff.append("   <div class=\"warning\">WARNING: NOT IN SECURE MODE (HTTPS)</div>\n");
/*     */     }
/*     */ 
/*  86 */     if (inputError) {
/*  87 */       strBuff.append("   <div class=\"warning\">Validation error!</div>\n");
/*     */     }
/*     */ 
/*  90 */     strBuff.append("   <form action=\"" + request.getRequestURI() + "\" method=\"POST\">\n");
/*  91 */     strBuff.append("    <div>\n");
/*     */ 
/*  93 */     Iterator configsIter = configs.iterator();
/*  94 */     int i = 0;
/*  95 */     while (configsIter.hasNext())
/*     */     {
/*  97 */       WebPBEConfig config = (WebPBEConfig)configsIter.next();
/*     */ 
/* 100 */       if (!config.isComplete()) {
/* 101 */         throw new EncryptionInitializationException("Incomplete WebPBEConfig object: all configs must specify both a name and a validation word");
/*     */       }
/*     */ 
/* 106 */       strBuff.append("     <fieldset>\n");
/* 107 */       strBuff.append("      <legend>" + config.getName() + "</legend>\n");
/* 108 */       strBuff.append("      <label for=\"jasyptVa" + i + "\">Validation word</label>: <input type=\"password\" name=\"" + "jasyptVa" + i + "\" />\n");
/* 109 */       strBuff.append("      <br /><br />\n");
/* 110 */       strBuff.append("      <label for=\"jasyptPw" + i + "\">Password</label>: <input type=\"password\" name=\"" + "jasyptPw" + i + "\" />\n");
/* 111 */       strBuff.append("      <br /><br />\n");
/* 112 */       strBuff.append("      <label for=\"jasyptRPw" + i + "\">Retype password</label>: <input type=\"password\" name=\"" + "jasyptRPw" + i + "\" />\n");
/* 113 */       strBuff.append("     </fieldset>\n");
/* 114 */       i++;
/*     */     }
/*     */ 
/* 118 */     strBuff.append("    </div>\n");
/* 119 */     strBuff.append("    <div id=\"button\">\n");
/* 120 */     strBuff.append("     <input type=\"hidden\" name=\"jasyptPwSetting\" value=\"true\" />\n");
/* 121 */     strBuff.append("     <input type=\"submit\" value=\"Submit\" />\n");
/* 122 */     strBuff.append("    </div>\n");
/* 123 */     strBuff.append("   </form>\n");
/*     */ 
/* 125 */     addFoot(strBuff);
/* 126 */     return strBuff.toString();
/*     */   }
/*     */ 
/*     */   public static String createNotInitializedHtml()
/*     */   {
/* 132 */     StringBuffer strBuff = new StringBuffer();
/* 133 */     strBuff.append("<html>\n");
/* 134 */     strBuff.append(" <head>\n");
/* 135 */     strBuff.append("  <title>Forbidden</title>\n");
/* 136 */     strBuff.append(" </head>\n");
/* 137 */     strBuff.append(" <body>\n");
/* 138 */     strBuff.append("   <h1>Access Forbidden</h1>\n");
/* 139 */     strBuff.append(" </body>\n");
/* 140 */     strBuff.append("</html>\n");
/* 141 */     return strBuff.toString();
/*     */   }
/*     */ 
/*     */   private static void addHeader(StringBuffer strBuff)
/*     */   {
/* 148 */     strBuff.append("<html>\n");
/* 149 */     strBuff.append(" <head>\n");
/* 150 */     strBuff.append("  <title>Web Password Based Encryption Configuration</title>\n");
/* 151 */     strBuff.append("  <style type=\"text/css\">");
/* 152 */     strBuff.append("   html { background-color: #ccc; text-align: center; margin: 0px; padding: 0px;} body {text-align:center;} #page { width: 700px; background-color: white; margin-top: 10px; margin-left: auto; margin-right: auto; padding: 10px; border: 1px solid #000; text-align: left;} h1 { text-weight: bold;} #button { text-align: center; margin-top: 20px; } fieldset { margin-bottom: 20px; } label { font-style: italic; } legend { font-weight: bold; } div.warning { border: 1px dotted #000; margin: 15px; padding: 5px; background-color: eee; font-weight: bold; }");
/* 153 */     strBuff.append("  </style>\n");
/* 154 */     strBuff.append(" </head>\n");
/* 155 */     strBuff.append(" <body>\n");
/* 156 */     strBuff.append("  <div id=\"page\">\n");
/* 157 */     strBuff.append("   <h1>Web PBE Configuration</h1>\n");
/*     */   }
/*     */ 
/*     */   private static void addFoot(StringBuffer strBuff) {
/* 161 */     strBuff.append("  </div>\n");
/* 162 */     strBuff.append(" </body>\n");
/* 163 */     strBuff.append("</html>\n");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigHtmlUtils
 * JD-Core Version:    0.6.2
 */