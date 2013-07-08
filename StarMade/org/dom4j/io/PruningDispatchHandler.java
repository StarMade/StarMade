/*  1:   */package org.dom4j.io;
/*  2:   */
/*  3:   */import org.dom4j.Element;
/*  4:   */import org.dom4j.ElementPath;
/*  5:   */
/* 16:   */class PruningDispatchHandler
/* 17:   */  extends DispatchHandler
/* 18:   */{
/* 19:   */  public void onEnd(ElementPath elementPath)
/* 20:   */  {
/* 21:21 */    super.onEnd(elementPath);
/* 22:   */    
/* 23:23 */    if (getActiveHandlerCount() == 0) {
/* 24:24 */      elementPath.getCurrent().detach();
/* 25:   */    }
/* 26:   */  }
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.PruningDispatchHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */