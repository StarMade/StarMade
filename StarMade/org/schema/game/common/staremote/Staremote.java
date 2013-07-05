/*     */ package org.schema.game.common.staremote;
/*     */ 
/*     */ import ct;
/*     */ import java.awt.EventQueue;
/*     */ import java.io.IOException;
/*     */ import jb;
/*     */ import org.schema.game.common.Starter;
/*     */ import org.schema.game.network.StarMadeServerStats;
/*     */ import pI;
/*     */ import pK;
/*     */ import pM;
/*     */ import qe;
/*     */ import qy;
/*     */ import rh;
/*     */ import sG;
/*     */ import x;
/*     */ import xu;
/*     */ 
/*     */ public class Staremote
/*     */ {
/*     */   private ct jdField_a_of_type_Ct;
/*     */   private pM jdField_a_of_type_PM;
/*     */   private rh jdField_a_of_type_Rh;
/*     */   public static qy a;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  34 */     sG.a();
/*     */ 
/*  37 */     xu.a();
/*     */     try
/*     */     {
/*  40 */       Starter.d();
/*     */     }
/*     */     catch (SecurityException localSecurityException)
/*     */     {
/*  45 */       localSecurityException.printStackTrace();
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*  45 */       localIOException.printStackTrace();
/*     */     }
/*     */ 
/*  48 */     new Staremote()
/*  49 */       .a();
/*     */   }
/*     */ 
/*     */   public final void a() {
/*  53 */     EventQueue.invokeLater(new pI(this));
/*     */   }
/*     */ 
/*     */   public final void a(ct paramct)
/*     */   {
/*  86 */     if (jdField_a_of_type_Qy != null) {
/*  87 */       jdField_a_of_type_Qy.b();
/*     */     }
/*     */     try
/*     */     {
/*  91 */       StarMadeServerStats localStarMadeServerStats = paramct.a().a();
/*  92 */       this.jdField_a_of_type_PM.a(localStarMadeServerStats);
/*     */ 
/*  94 */       if (this.jdField_a_of_type_Rh != null) {
/*  95 */         paramct = paramct.a().a();
/*  96 */         this.jdField_a_of_type_Rh.a(paramct);
/*  97 */         this.jdField_a_of_type_Rh = null;
/*     */       }return; } catch (IOException localIOException) {
/* 103 */       localIOException.printStackTrace();
/*     */       return;
/*     */     } catch (InterruptedException localInterruptedException) {
/* 103 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(qe paramqe)
/*     */   {
/* 107 */     paramqe = new jb(paramqe.b, paramqe.jdField_a_of_type_Int, paramqe.jdField_a_of_type_JavaLangString);
/* 108 */     new Thread(new pK(this, paramqe))
/* 224 */       .start();
/*     */   }
/*     */ 
/*     */   public static String a()
/*     */   {
/* 234 */     return "./.starmotecon";
/*     */   }
/*     */ 
/*     */   public final void b() {
/* 238 */     Staremote localStaremote = this;
/*     */     try { localStaremote.jdField_a_of_type_Ct.disconnect(); } catch (Exception localException) { localException.printStackTrace(); }
/*     */ 
/* 240 */     System.exit(0);
/*     */   }
/*     */   public final void a(rh paramrh) {
/* 243 */     this.jdField_a_of_type_Rh = paramrh;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.staremote.Staremote
 * JD-Core Version:    0.6.2
 */