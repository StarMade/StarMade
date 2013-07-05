/*    */ package com.google.code.tempusfugit;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CompositeFactory<T>
/*    */   implements Factory<T>
/*    */ {
/*    */   private final List<Factory<T>> factories;
/*    */ 
/*    */   public CompositeFactory(Factory<T>[] factories)
/*    */   {
/* 28 */     this.factories = Arrays.asList(factories);
/*    */   }
/*    */ 
/*    */   public T create() throws FactoryException
/*    */   {
/* 33 */     for (Factory factory : this.factories)
/*    */       try {
/* 35 */         return factory.create();
/*    */       }
/*    */       catch (FactoryException e)
/*    */       {
/*    */       }
/* 40 */     throw new FactoryException("unable to create any objects from factories");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.google.code.tempusfugit.CompositeFactory
 * JD-Core Version:    0.6.2
 */