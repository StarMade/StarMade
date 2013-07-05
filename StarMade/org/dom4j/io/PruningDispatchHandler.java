/*    */ package org.dom4j.io;
/*    */ 
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.ElementPath;
/*    */ 
/*    */ class PruningDispatchHandler extends DispatchHandler
/*    */ {
/*    */   public void onEnd(ElementPath elementPath)
/*    */   {
/* 21 */     super.onEnd(elementPath);
/*    */ 
/* 23 */     if (getActiveHandlerCount() == 0)
/* 24 */       elementPath.getCurrent().detach();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.PruningDispatchHandler
 * JD-Core Version:    0.6.2
 */