/*    */ package org.schema.game.common.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.schema.game.common.data.element.Element;
/*    */ 
/*    */ public final class ObjectSizer
/*    */ {
/* 11 */   private static int jdField_a_of_type_Int = 100;
/*    */ 
/* 14 */   private static long jdField_a_of_type_Long = 100L;
/*    */ 
/*    */   private static void a() { try { System.gc();
/* 19 */       Thread.currentThread();
/* 20 */       Thread.sleep(jdField_a_of_type_Long);
/* 21 */       System.runFinalization();
/* 22 */       Thread.currentThread();
/* 23 */       Thread.sleep(jdField_a_of_type_Long);
/*    */       return; } catch (InterruptedException localInterruptedException) { localInterruptedException.printStackTrace(); }
/*    */   }
/*    */ 
/*    */   private static long a()
/*    */   {
/* 30 */     a(); a();
/* 31 */     long l1 = Runtime.getRuntime().totalMemory();
/*    */ 
/* 33 */     a(); a();
/* 34 */     long l2 = Runtime.getRuntime().freeMemory();
/*    */ 
/* 36 */     return l1 - l2;
/*    */   }
/*    */ 
/*    */   private static long a(Class paramClass)
/*    */   {
/* 46 */     long l1 = 0L;
/*    */     try
/*    */     {
/* 51 */       paramClass.getConstructor(new Class[0]);
/*    */     }
/*    */     catch (NoSuchMethodException localNoSuchMethodException) {
/* 54 */       System.err.println(paramClass + " does not have a no-argument constructor.");
/* 55 */       return 0L;
/*    */     }
/*    */ 
/* 60 */     Object[] arrayOfObject = new Object[jdField_a_of_type_Int];
/*    */     try
/*    */     {
/* 66 */       long l2 = a();
/* 67 */       for (int i = 0; i < arrayOfObject.length; i++) {
/* 68 */         arrayOfObject[i] = paramClass.newInstance();
/*    */       }
/*    */ 
/* 73 */       l1 = Math.round((float)(a() - 
/* 72 */         l2) / 100.0F);
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 76 */       System.err.println("Cannot create object using " + paramClass);
/*    */     }
/* 78 */     return l1;
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 93 */     long l = a(paramArrayOfString = Element.class);
/*    */ 
/* 95 */     System.out.println("Approximate size of " + paramArrayOfString + " objects: " + l);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.util.ObjectSizer
 * JD-Core Version:    0.6.2
 */