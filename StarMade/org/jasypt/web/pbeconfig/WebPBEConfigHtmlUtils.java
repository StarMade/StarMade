/*   1:    */package org.jasypt.web.pbeconfig;
/*   2:    */
/*   3:    */import java.util.Iterator;
/*   4:    */import java.util.List;
/*   5:    */import javax.servlet.http.HttpServletRequest;
/*   6:    */import org.jasypt.encryption.pbe.config.WebPBEConfig;
/*   7:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   8:    */
/*  49:    */final class WebPBEConfigHtmlUtils
/*  50:    */{
/*  51:    */  public static final String PASSWORD_SETTING_FLAG = "jasyptPwSetting";
/*  52:    */  public static final String VALIDATION_PREFIX = "jasyptVa";
/*  53:    */  public static final String PASSWORD_PREFIX = "jasyptPw";
/*  54:    */  public static final String PASSWORD_RETYPED_PREFIX = "jasyptRPw";
/*  55:    */  private static final String HTTPS_SCHEME = "https";
/*  56:    */  
/*  57:    */  public static String createConfigurationDoneHtml()
/*  58:    */  {
/*  59: 59 */    StringBuffer strBuff = new StringBuffer();
/*  60: 60 */    addHeader(strBuff);
/*  61: 61 */    strBuff.append("   <h2>All Configuration Done</h2>\n");
/*  62: 62 */    addFoot(strBuff);
/*  63: 63 */    return strBuff.toString();
/*  64:    */  }
/*  65:    */  
/*  72:    */  public static String createInputFormHtml(HttpServletRequest request, boolean inputError)
/*  73:    */  {
/*  74: 74 */    WebPBEConfigRegistry registry = WebPBEConfigRegistry.getInstance();
/*  75: 75 */    List configs = registry.getConfigs();
/*  76:    */    
/*  77: 77 */    StringBuffer strBuff = new StringBuffer();
/*  78: 78 */    addHeader(strBuff);
/*  79:    */    
/*  80: 80 */    strBuff.append("   <h2>Please enter the PBE configuration parameters</h2>\n");
/*  81:    */    
/*  82: 82 */    if (!"https".equals(request.getScheme().toLowerCase())) {
/*  83: 83 */      strBuff.append("   <div class=\"warning\">WARNING: NOT IN SECURE MODE (HTTPS)</div>\n");
/*  84:    */    }
/*  85:    */    
/*  86: 86 */    if (inputError) {
/*  87: 87 */      strBuff.append("   <div class=\"warning\">Validation error!</div>\n");
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    strBuff.append("   <form action=\"" + request.getRequestURI() + "\" method=\"POST\">\n");
/*  91: 91 */    strBuff.append("    <div>\n");
/*  92:    */    
/*  93: 93 */    Iterator configsIter = configs.iterator();
/*  94: 94 */    int i = 0;
/*  95: 95 */    while (configsIter.hasNext())
/*  96:    */    {
/*  97: 97 */      WebPBEConfig config = (WebPBEConfig)configsIter.next();
/*  98:    */      
/* 100:100 */      if (!config.isComplete()) {
/* 101:101 */        throw new EncryptionInitializationException("Incomplete WebPBEConfig object: all configs must specify both a name and a validation word");
/* 102:    */      }
/* 103:    */      
/* 106:106 */      strBuff.append("     <fieldset>\n");
/* 107:107 */      strBuff.append("      <legend>" + config.getName() + "</legend>\n");
/* 108:108 */      strBuff.append("      <label for=\"jasyptVa" + i + "\">Validation word</label>: <input type=\"password\" name=\"" + "jasyptVa" + i + "\" />\n");
/* 109:109 */      strBuff.append("      <br /><br />\n");
/* 110:110 */      strBuff.append("      <label for=\"jasyptPw" + i + "\">Password</label>: <input type=\"password\" name=\"" + "jasyptPw" + i + "\" />\n");
/* 111:111 */      strBuff.append("      <br /><br />\n");
/* 112:112 */      strBuff.append("      <label for=\"jasyptRPw" + i + "\">Retype password</label>: <input type=\"password\" name=\"" + "jasyptRPw" + i + "\" />\n");
/* 113:113 */      strBuff.append("     </fieldset>\n");
/* 114:114 */      i++;
/* 115:    */    }
/* 116:    */    
/* 118:118 */    strBuff.append("    </div>\n");
/* 119:119 */    strBuff.append("    <div id=\"button\">\n");
/* 120:120 */    strBuff.append("     <input type=\"hidden\" name=\"jasyptPwSetting\" value=\"true\" />\n");
/* 121:121 */    strBuff.append("     <input type=\"submit\" value=\"Submit\" />\n");
/* 122:122 */    strBuff.append("    </div>\n");
/* 123:123 */    strBuff.append("   </form>\n");
/* 124:    */    
/* 125:125 */    addFoot(strBuff);
/* 126:126 */    return strBuff.toString();
/* 127:    */  }
/* 128:    */  
/* 130:    */  public static String createNotInitializedHtml()
/* 131:    */  {
/* 132:132 */    StringBuffer strBuff = new StringBuffer();
/* 133:133 */    strBuff.append("<html>\n");
/* 134:134 */    strBuff.append(" <head>\n");
/* 135:135 */    strBuff.append("  <title>Forbidden</title>\n");
/* 136:136 */    strBuff.append(" </head>\n");
/* 137:137 */    strBuff.append(" <body>\n");
/* 138:138 */    strBuff.append("   <h1>Access Forbidden</h1>\n");
/* 139:139 */    strBuff.append(" </body>\n");
/* 140:140 */    strBuff.append("</html>\n");
/* 141:141 */    return strBuff.toString();
/* 142:    */  }
/* 143:    */  
/* 146:    */  private static void addHeader(StringBuffer strBuff)
/* 147:    */  {
/* 148:148 */    strBuff.append("<html>\n");
/* 149:149 */    strBuff.append(" <head>\n");
/* 150:150 */    strBuff.append("  <title>Web Password Based Encryption Configuration</title>\n");
/* 151:151 */    strBuff.append("  <style type=\"text/css\">");
/* 152:152 */    strBuff.append("   html { background-color: #ccc; text-align: center; margin: 0px; padding: 0px;} body {text-align:center;} #page { width: 700px; background-color: white; margin-top: 10px; margin-left: auto; margin-right: auto; padding: 10px; border: 1px solid #000; text-align: left;} h1 { text-weight: bold;} #button { text-align: center; margin-top: 20px; } fieldset { margin-bottom: 20px; } label { font-style: italic; } legend { font-weight: bold; } div.warning { border: 1px dotted #000; margin: 15px; padding: 5px; background-color: eee; font-weight: bold; }");
/* 153:153 */    strBuff.append("  </style>\n");
/* 154:154 */    strBuff.append(" </head>\n");
/* 155:155 */    strBuff.append(" <body>\n");
/* 156:156 */    strBuff.append("  <div id=\"page\">\n");
/* 157:157 */    strBuff.append("   <h1>Web PBE Configuration</h1>\n");
/* 158:    */  }
/* 159:    */  
/* 160:    */  private static void addFoot(StringBuffer strBuff) {
/* 161:161 */    strBuff.append("  </div>\n");
/* 162:162 */    strBuff.append(" </body>\n");
/* 163:163 */    strBuff.append("</html>\n");
/* 164:    */  }
/* 165:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.web.pbeconfig.WebPBEConfigHtmlUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */