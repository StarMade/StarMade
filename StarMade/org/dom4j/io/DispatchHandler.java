/*   1:    */package org.dom4j.io;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.HashMap;
/*   5:    */import org.dom4j.Element;
/*   6:    */import org.dom4j.ElementHandler;
/*   7:    */import org.dom4j.ElementPath;
/*   8:    */
/*  46:    */class DispatchHandler
/*  47:    */  implements ElementHandler
/*  48:    */{
/*  49:    */  private boolean atRoot;
/*  50:    */  private String path;
/*  51:    */  private ArrayList pathStack;
/*  52:    */  private ArrayList handlerStack;
/*  53:    */  private HashMap handlers;
/*  54:    */  private ElementHandler defaultHandler;
/*  55:    */  
/*  56:    */  public DispatchHandler()
/*  57:    */  {
/*  58: 58 */    this.atRoot = true;
/*  59: 59 */    this.path = "/";
/*  60: 60 */    this.pathStack = new ArrayList();
/*  61: 61 */    this.handlerStack = new ArrayList();
/*  62: 62 */    this.handlers = new HashMap();
/*  63:    */  }
/*  64:    */  
/*  74:    */  public void addHandler(String handlerPath, ElementHandler handler)
/*  75:    */  {
/*  76: 76 */    this.handlers.put(handlerPath, handler);
/*  77:    */  }
/*  78:    */  
/*  87:    */  public ElementHandler removeHandler(String handlerPath)
/*  88:    */  {
/*  89: 89 */    return (ElementHandler)this.handlers.remove(handlerPath);
/*  90:    */  }
/*  91:    */  
/* 100:    */  public boolean containsHandler(String handlerPath)
/* 101:    */  {
/* 102:102 */    return this.handlers.containsKey(handlerPath);
/* 103:    */  }
/* 104:    */  
/* 112:    */  public ElementHandler getHandler(String handlerPath)
/* 113:    */  {
/* 114:114 */    return (ElementHandler)this.handlers.get(handlerPath);
/* 115:    */  }
/* 116:    */  
/* 122:    */  public int getActiveHandlerCount()
/* 123:    */  {
/* 124:124 */    return this.handlerStack.size();
/* 125:    */  }
/* 126:    */  
/* 135:    */  public void setDefaultHandler(ElementHandler handler)
/* 136:    */  {
/* 137:137 */    this.defaultHandler = handler;
/* 138:    */  }
/* 139:    */  
/* 143:    */  public void resetHandlers()
/* 144:    */  {
/* 145:145 */    this.atRoot = true;
/* 146:146 */    this.path = "/";
/* 147:147 */    this.pathStack.clear();
/* 148:148 */    this.handlerStack.clear();
/* 149:149 */    this.handlers.clear();
/* 150:150 */    this.defaultHandler = null;
/* 151:    */  }
/* 152:    */  
/* 157:    */  public String getPath()
/* 158:    */  {
/* 159:159 */    return this.path;
/* 160:    */  }
/* 161:    */  
/* 162:    */  public void onStart(ElementPath elementPath)
/* 163:    */  {
/* 164:164 */    Element element = elementPath.getCurrent();
/* 165:    */    
/* 167:167 */    this.pathStack.add(this.path);
/* 168:    */    
/* 170:170 */    if (this.atRoot) {
/* 171:171 */      this.path += element.getName();
/* 172:172 */      this.atRoot = false;
/* 173:    */    } else {
/* 174:174 */      this.path = (this.path + "/" + element.getName());
/* 175:    */    }
/* 176:    */    
/* 177:177 */    if ((this.handlers != null) && (this.handlers.containsKey(this.path)))
/* 178:    */    {
/* 180:180 */      ElementHandler handler = (ElementHandler)this.handlers.get(this.path);
/* 181:181 */      this.handlerStack.add(handler);
/* 182:    */      
/* 184:184 */      handler.onStart(elementPath);
/* 187:    */    }
/* 188:188 */    else if ((this.handlerStack.isEmpty()) && (this.defaultHandler != null)) {
/* 189:189 */      this.defaultHandler.onStart(elementPath);
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 193:    */  public void onEnd(ElementPath elementPath)
/* 194:    */  {
/* 195:195 */    if ((this.handlers != null) && (this.handlers.containsKey(this.path)))
/* 196:    */    {
/* 198:198 */      ElementHandler handler = (ElementHandler)this.handlers.get(this.path);
/* 199:199 */      this.handlerStack.remove(this.handlerStack.size() - 1);
/* 200:    */      
/* 202:202 */      handler.onEnd(elementPath);
/* 205:    */    }
/* 206:206 */    else if ((this.handlerStack.isEmpty()) && (this.defaultHandler != null)) {
/* 207:207 */      this.defaultHandler.onEnd(elementPath);
/* 208:    */    }
/* 209:    */    
/* 212:212 */    this.path = ((String)this.pathStack.remove(this.pathStack.size() - 1));
/* 213:    */    
/* 214:214 */    if (this.pathStack.size() == 0) {
/* 215:215 */      this.atRoot = true;
/* 216:    */    }
/* 217:    */  }
/* 218:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.DispatchHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */