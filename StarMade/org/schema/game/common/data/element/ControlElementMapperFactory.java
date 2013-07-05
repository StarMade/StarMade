/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ import Ac;
/*    */ import java.io.DataInputStream;
/*    */ 
/*    */ public class ControlElementMapperFactory
/*    */   implements Ac
/*    */ {
/*    */   private static final long serialVersionUID = 8953098951065383692L;
/*    */ 
/*    */   public Object create(DataInputStream paramDataInputStream)
/*    */   {
/* 24 */     ControlElementMapper localControlElementMapper = new ControlElementMapper();
/* 25 */     ControlElementMap.deserialize(paramDataInputStream, localControlElementMapper);
/*    */ 
/* 27 */     return localControlElementMapper;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ControlElementMapperFactory
 * JD-Core Version:    0.6.2
 */