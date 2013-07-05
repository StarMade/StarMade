/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.SQLException;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.server.controller.EntityNotFountException;
/*     */ 
/*     */ public final class tg extends ti
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private long a;
/*     */ 
/*     */   public tg(wk paramwk)
/*     */   {
/*  26 */     super(paramwk);
/*     */   }
/*     */ 
/*     */   public final boolean c()
/*     */   {
/*  32 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean b()
/*     */   {
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */   public final boolean d()
/*     */   {
/*     */     sL localsL;
/*  44 */     if ((
/*  44 */       localsL = (sL)a().a())
/*  44 */       .a() == null) {
/*  45 */       a(new tx());
/*  46 */       return true;
/*     */     }
/*     */ 
/*  51 */     q localq1 = new q();
/*  52 */     for (int i = 0; i < ((vY)a()).a().size(); i++) {
/*     */       try {
/*  54 */         ((vY)a()).a((String)((vY)a()).a().get(i), localq1);
/*     */ 
/*  56 */         if (localq1.equals(localsL.a())) {
/*  57 */           a(new tF());
/*  58 */           return true;
/*     */         }
/*     */ 
/*     */       }
/*     */       catch (EntityNotFountException localEntityNotFountException1)
/*     */       {
/*  65 */         localEntityNotFountException1.printStackTrace();
/*     */       }
/*     */       catch (SQLException localSQLException1)
/*     */       {
/*  65 */         localSQLException1.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  68 */     if (System.currentTimeMillis() - this.a > 1000L) {
/*  69 */       boolean bool2 = ((vY)a()).a().a().a(localsL.a());
/*  70 */       for (int j = 0; j < ((vY)a()).a().size(); j++)
/*     */       {
/*     */         boolean bool1;
/*  72 */         if (((vY)a()).a().a().isSectorLoaded(localsL.a()))
/*     */         {
/*  74 */           bool1 = ((vY)a()).a((String)((vY)a()).a().get(j), localsL.a());
/*     */         }
/*  76 */         else if (bool2)
/*     */         {
/*  78 */           bool1 = true;
/*  79 */           new q();
/*  80 */           q localq2 = org.schema.game.common.data.element.Element.DIRECTIONSi[Universe.getRandom().nextInt(org.schema.game.common.data.element.Element.DIRECTIONSi.length)];
/*     */           try
/*     */           {
/*     */             q localq3;
/*  82 */             (
/*  83 */               localq3 = ((vY)a()).a((String)((vY)a()).a().get(j), new q()))
/*  83 */               .a(localq2);
/*  84 */             System.err.println("[MOVING TO SECTOR] Position occupied for " + (String)((vY)a()).a().get(j));
/*  85 */             ((vY)a()).a((String)((vY)a()).a().get(j), localq3);
/*     */           }
/*     */           catch (EntityNotFountException localEntityNotFountException2)
/*     */           {
/*  92 */             localEntityNotFountException2.printStackTrace();
/*     */           }
/*     */           catch (SQLException localSQLException2)
/*     */           {
/*  92 */             localSQLException2.printStackTrace();
/*     */           }
/*     */           catch (Exception localException)
/*     */           {
/*  92 */             localException.printStackTrace();
/*     */           }
/*     */         }
/*     */         else {
/*  94 */           bool1 = ((vY)a()).a((String)((vY)a()).a().get(j), localsL.a());
/*     */         }
/*     */ 
/*  97 */         if (!bool1) {
/*  98 */           System.err.println("[SIMULATION] Exception while moving entity: REMOVING FROM MEMBERS: " + (String)((vY)a()).a().get(j));
/*  99 */           ((vY)a()).a().remove(j);
/* 100 */           j--;
/*     */         }
/* 102 */         this.a = System.currentTimeMillis();
/*     */       }
/*     */     }
/*     */ 
/* 106 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     tg
 * JD-Core Version:    0.6.2
 */