/*  1:   */package com.apple.eawt;
/*  2:   */
/*  3:   */import java.util.EventObject;
/*  4:   */
/*  5:   */public class ApplicationEvent extends EventObject
/*  6:   */{
/*  7:   */  ApplicationEvent(Object paramObject) {
/*  8: 8 */    super(paramObject);
/*  9:   */  }
/* 10:   */  
/* 11:   */  ApplicationEvent(Object paramObject, String paramString) {
/* 12:12 */    super(paramObject);
/* 13:   */  }
/* 14:   */  
/* 15:   */  public boolean isHandled() {
/* 16:16 */    return false;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public void setHandled(boolean paramBoolean) {}
/* 20:   */  
/* 21:   */  public String getFilename() {
/* 22:22 */    return null;
/* 23:   */  }
/* 24:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.apple.eawt.ApplicationEvent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */