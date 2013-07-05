/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.schema.game.common.data.world.SectorNotFoundException;
/*     */ import org.schema.game.common.data.world.Universe;
/*     */ import org.schema.game.network.objects.NetworkClientChannel;
/*     */ import org.schema.game.network.objects.remote.RemoteMissileUpdate;
/*     */ import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.RegisteredClientOnServer;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteShort;
/*     */ 
/*     */ public class lo
/*     */ {
/*  22 */   private final Short2ObjectOpenHashMap jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap = new Short2ObjectOpenHashMap();
/*     */   private final vg jdField_a_of_type_Vg;
/*     */ 
/*     */   public lo(vg paramvg)
/*     */   {
/*  26 */     this.jdField_a_of_type_Vg = paramvg;
/*     */   }
/*     */ 
/*     */   public final void a(ln paramln) {
/*  30 */     paramln.initPhysics();
/*  31 */     this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.put(paramln.a(), paramln);
/*  32 */     paramln.b.add(a(paramln));
/*  33 */     System.err.println("[SERVER] MISSILE ADDED " + paramln);
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq) {
/*  37 */     ObjectIterator localObjectIterator = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.values().iterator();
/*  38 */     while (localObjectIterator.hasNext())
/*     */     {
/*     */       ln localln;
/*     */       Iterator localIterator;
/*  41 */       if ((
/*  41 */         localln = (ln)localObjectIterator.next())
/*  41 */         .a()) {
/*  42 */         localln.a(paramxq);
/*     */ 
/*  44 */         localln.a().clear();
/*  45 */         for (localIterator = this.jdField_a_of_type_Vg.b().values().iterator(); localIterator.hasNext(); )
/*     */         {
/*     */           lE locallE;
/*  46 */           if ((
/*  46 */             locallE = (lE)localIterator.next())
/*  46 */             .c() == localln.b()) {
/*  47 */             localln.a().add(locallE);
/*     */           }
/*     */           else
/*     */           {
/*     */             mx localmx;
/*  51 */             if ((
/*  51 */               localmx = this.jdField_a_of_type_Vg.a().getSector(localln.b())) != null)
/*     */             {
/*  53 */               if ((Math.abs(localmx.a.a - locallE.a().a) < 2) && (Math.abs(localmx.a.b - locallE.a().b) < 2) && (Math.abs(localmx.a.c - locallE.a().c) < 2))
/*     */               {
/*  58 */                 localln.a().add(locallE);
/*     */               }
/*     */             }
/*     */             else
/*  62 */               localln.d();
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/*     */         try {
/*  68 */           localln.c();
/*     */         } catch (SectorNotFoundException localSectorNotFoundException) {
/*  70 */           System.err.println("[SERVER][MISSILEMAN] WARNING: Physics for missile " + localln + " has not been removed: Sector not loaded: " + localSectorNotFoundException.getMessage());
/*     */         }
/*  72 */         localObjectIterator.remove();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  77 */     a();
/*     */   }
/*     */ 
/*     */   private void a() {
/*  81 */     for (Iterator localIterator1 = this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.values().iterator(); localIterator1.hasNext(); )
/*     */     {
/*     */       ln localln;
/*     */       Iterator localIterator2;
/*  83 */       if ((
/*  83 */         localln = (ln)localIterator1.next())
/*  83 */         .c()) {
/*  84 */         System.err.println("[SERVER] BROADCAST MISSILE UPDATE " + localln + "; Missile Count: " + this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.size() + "; " + localln.b);
/*  85 */         for (localIterator2 = this.jdField_a_of_type_Vg.b().values().iterator(); localIterator2.hasNext(); ) { localObject1 = (lE)localIterator2.next();
/*     */ 
/*  88 */           if ((
/*  88 */             localObject2 = (RegisteredClientOnServer)this.jdField_a_of_type_Vg.getClients().get(Integer.valueOf(((lE)localObject1).a()))) != null)
/*     */           {
/*  89 */             ((lE)localObject1).a();
/*     */ 
/*  93 */             if (((
/*  93 */               localObject2 = (Sendable)((RegisteredClientOnServer)localObject2).getLocalAndRemoteObjectContainer().getLocalObjects().get(0)) != null) && 
/*  93 */               ((localObject2 instanceof t))) {
/*  94 */               localObject1 = (t)localObject2;
/*  95 */               localln.b((t)localObject1);
/*     */             } else {
/*  97 */               System.err.println("[SERVER] BROADCAST MISSILE UPDATE FAILED FOR " + localObject1 + ": NO CLIENT CHANNEL");
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 102 */             System.err.println("[SEVRER][MISSILEMAN] client for player not found: " + localObject1);
/*     */           }
/*     */         }
/*     */       }
/*     */       Object localObject1;
/*     */       Object localObject2;
/* 107 */       if ((localln.b()) && (localln.a())) {
/* 108 */         for (localIterator2 = localln.a().iterator(); localIterator2.hasNext(); ) { localObject1 = (lE)localIterator2.next();
/*     */ 
/* 111 */           if ((
/* 111 */             localObject2 = (RegisteredClientOnServer)this.jdField_a_of_type_Vg.getClients().get(Integer.valueOf(((lE)localObject1).a()))) != null)
/*     */           {
/* 112 */             ((lE)localObject1).a();
/*     */ 
/* 116 */             if (((
/* 116 */               localObject2 = (Sendable)((RegisteredClientOnServer)localObject2).getLocalAndRemoteObjectContainer().getLocalObjects().get(0)) != null) && 
/* 116 */               ((localObject2 instanceof t))) {
/* 117 */               localObject1 = (t)localObject2;
/* 118 */               localln.a((t)localObject1);
/*     */             }
/*     */           } else {
/* 121 */             System.err.println("[SEVRER][MISSILEMAN] broadcast client for player not found: " + localObject1);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 126 */       localln.e();
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(NetworkClientChannel paramNetworkClientChannel)
/*     */   {
/* 138 */     for (int i = 0; i < paramNetworkClientChannel.missileMissingRequestBuffer.getReceiveBuffer().size(); 
/* 139 */       i++) {
/* 140 */       short s = ((Short)((RemoteShort)paramNetworkClientChannel.missileMissingRequestBuffer.getReceiveBuffer().get(i)).get()).shortValue();
/*     */       ln localln;
/* 143 */       if ((
/* 143 */         localln = (ln)this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.get(s)) != null)
/*     */       {
/* 144 */         System.err.println("[SERVER] Adding Requested Spawn " + localln);
/* 145 */         paramNetworkClientChannel.missileUpdateBuffer.add(new RemoteMissileUpdate(a(localln), paramNetworkClientChannel));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static lu a(ln paramln)
/*     */   {
/* 153 */     int i = -1;
/* 154 */     if (((paramln instanceof lp)) && (((lp)paramln).a() != null))
/*     */     {
/* 156 */       i = ((lp)paramln).a().getId();
/*     */     }
/*     */ 
/* 159 */     lu locallu = new lu(paramln.a(), paramln.a().origin, paramln.a(), i, paramln.a(), paramln.b());
/*     */ 
/* 163 */     if ((!jdField_a_of_type_Boolean) && (locallu.a != paramln.a())) throw new AssertionError();
/*     */ 
/* 165 */     return locallu;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lo
 * JD-Core Version:    0.6.2
 */