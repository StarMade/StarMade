/*   1:    */package org.newdawn.slick;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.util.HashMap;
/*   5:    */import javax.jnlp.ServiceManager;
/*   6:    */import org.newdawn.slick.muffin.FileMuffin;
/*   7:    */import org.newdawn.slick.muffin.Muffin;
/*   8:    */import org.newdawn.slick.muffin.WebstartMuffin;
/*   9:    */import org.newdawn.slick.util.Log;
/*  10:    */
/*  22:    */public class SavedState
/*  23:    */{
/*  24:    */  private String fileName;
/*  25:    */  private Muffin muffin;
/*  26: 26 */  private HashMap numericData = new HashMap();
/*  27:    */  
/*  28: 28 */  private HashMap stringData = new HashMap();
/*  29:    */  
/*  36:    */  public SavedState(String fileName)
/*  37:    */    throws SlickException
/*  38:    */  {
/*  39: 39 */    this.fileName = fileName;
/*  40:    */    
/*  41: 41 */    if (isWebstartAvailable()) {
/*  42: 42 */      this.muffin = new WebstartMuffin();
/*  43:    */    }
/*  44:    */    else {
/*  45: 45 */      this.muffin = new FileMuffin();
/*  46:    */    }
/*  47:    */    try
/*  48:    */    {
/*  49: 49 */      load();
/*  50:    */    } catch (IOException e) {
/*  51: 51 */      throw new SlickException("Failed to load state on startup", e);
/*  52:    */    }
/*  53:    */  }
/*  54:    */  
/*  60:    */  public double getNumber(String nameOfField)
/*  61:    */  {
/*  62: 62 */    return getNumber(nameOfField, 0.0D);
/*  63:    */  }
/*  64:    */  
/*  71:    */  public double getNumber(String nameOfField, double defaultValue)
/*  72:    */  {
/*  73: 73 */    Double value = (Double)this.numericData.get(nameOfField);
/*  74:    */    
/*  75: 75 */    if (value == null) {
/*  76: 76 */      return defaultValue;
/*  77:    */    }
/*  78:    */    
/*  79: 79 */    return value.doubleValue();
/*  80:    */  }
/*  81:    */  
/*  88:    */  public void setNumber(String nameOfField, double value)
/*  89:    */  {
/*  90: 90 */    this.numericData.put(nameOfField, new Double(value));
/*  91:    */  }
/*  92:    */  
/*  98:    */  public String getString(String nameOfField)
/*  99:    */  {
/* 100:100 */    return getString(nameOfField, null);
/* 101:    */  }
/* 102:    */  
/* 109:    */  public String getString(String nameOfField, String defaultValue)
/* 110:    */  {
/* 111:111 */    String value = (String)this.stringData.get(nameOfField);
/* 112:    */    
/* 113:113 */    if (value == null) {
/* 114:114 */      return defaultValue;
/* 115:    */    }
/* 116:    */    
/* 117:117 */    return value;
/* 118:    */  }
/* 119:    */  
/* 126:    */  public void setString(String nameOfField, String value)
/* 127:    */  {
/* 128:128 */    this.stringData.put(nameOfField, value);
/* 129:    */  }
/* 130:    */  
/* 134:    */  public void save()
/* 135:    */    throws IOException
/* 136:    */  {
/* 137:137 */    this.muffin.saveFile(this.numericData, this.fileName + "_Number");
/* 138:138 */    this.muffin.saveFile(this.stringData, this.fileName + "_String");
/* 139:    */  }
/* 140:    */  
/* 144:    */  public void load()
/* 145:    */    throws IOException
/* 146:    */  {
/* 147:147 */    this.numericData = this.muffin.loadFile(this.fileName + "_Number");
/* 148:148 */    this.stringData = this.muffin.loadFile(this.fileName + "_String");
/* 149:    */  }
/* 150:    */  
/* 153:    */  public void clear()
/* 154:    */  {
/* 155:155 */    this.numericData.clear();
/* 156:156 */    this.stringData.clear();
/* 157:    */  }
/* 158:    */  
/* 162:    */  private boolean isWebstartAvailable()
/* 163:    */  {
/* 164:    */    try
/* 165:    */    {
/* 166:166 */      Class.forName("javax.jnlp.ServiceManager");
/* 167:    */      
/* 168:168 */      ServiceManager.lookup("javax.jnlp.PersistenceService");
/* 169:169 */      Log.info("Webstart detected using Muffins");
/* 170:    */    } catch (Exception e) {
/* 171:171 */      Log.info("Using Local File System");
/* 172:172 */      return false;
/* 173:    */    }
/* 174:174 */    return true;
/* 175:    */  }
/* 176:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.SavedState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */