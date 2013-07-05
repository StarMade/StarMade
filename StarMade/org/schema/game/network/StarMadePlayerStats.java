/*     */ package org.schema.game.network;
/*     */ 
/*     */ import Ad;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import lE;
/*     */ import vg;
/*     */ 
/*     */ public class StarMadePlayerStats
/*     */ {
/*     */   public static final int ONLINE_ONLY = 1;
/*     */   public static final int INFO_PLAYER_DETAILS = 4;
/*     */   public static final int INFO_LAST_LOGIN = 8;
/*     */   public static final int INFO_LOGGED_IP = 16;
/*  26 */   private static int paramSize = 4;
/*     */   public ReceivedPlayer[] receivedPlayers;
/*     */ 
/*     */   public static StarMadePlayerStats decode(Object[] paramArrayOfObject, int paramInt)
/*     */   {
/*  32 */     int i = paramArrayOfObject.length / paramSize;
/*     */     StarMadePlayerStats localStarMadePlayerStats;
/*  34 */     (
/*  36 */       localStarMadePlayerStats = new StarMadePlayerStats()).receivedPlayers = 
/*  36 */       new ReceivedPlayer[i];
/*     */ 
/*  38 */     for (int j = 0; j < i; j++) {
/*  39 */       localStarMadePlayerStats.receivedPlayers[j] = new ReceivedPlayer();
/*  40 */       localStarMadePlayerStats.receivedPlayers[j].decode(paramArrayOfObject, j * paramSize, paramInt);
/*     */     }
/*     */ 
/*  44 */     return localStarMadePlayerStats;
/*     */   }
/*     */ 
/*     */   public static Object[] encode(vg paramvg, int paramInt)
/*     */   {
/*  51 */     paramInt = new File(vg.a)
/*  51 */       .listFiles(new StarMadePlayerStats.1());
/*     */ 
/*  57 */     ArrayList localArrayList1 = new ArrayList();
/*     */     Object localObject;
/*  58 */     for (int i = 0; i < paramInt.length; i++) {
/*     */       try {
/*  60 */         localObject = Ad.a(new BufferedInputStream(new FileInputStream(paramInt[i])), true);
/*     */         lE locallE;
/*  61 */         (
/*  62 */           locallE = new lE(paramvg))
/*  62 */           .initialize();
/*  63 */         locallE.fromTagStructure((Ad)localObject);
/*  64 */         localObject = paramInt[i].getName();
/*  65 */         locallE.a(((String)localObject).substring(19, ((String)localObject).lastIndexOf(".")));
/*  66 */         localArrayList1.add(locallE); } catch (FileNotFoundException localFileNotFoundException) {
/*  67 */         localObject = null;
/*     */ 
/*  71 */         localFileNotFoundException.printStackTrace();
/*     */       } catch (IOException localIOException) {
/*  69 */         localObject = null;
/*     */ 
/*  71 */         localIOException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  75 */     Object[] arrayOfObject = new Object[localArrayList1.size() * paramSize];
/*     */ 
/*  77 */     for (int j = 0; j < localArrayList1.size(); 
/*  78 */       j++)
/*     */     {
/*  80 */       localObject = (lE)localArrayList1.get(j);
/*  81 */       paramvg = j * paramSize;
/*     */ 
/*  83 */       paramInt = new StringBuilder();
/*     */       ArrayList localArrayList2;
/*  84 */       (
/*  85 */         localArrayList2 = new ArrayList())
/*  85 */         .addAll(((lE)localObject).a());
/*  86 */       for (int k = 0; k < localArrayList2.size(); k++) {
/*  87 */         paramInt.append((String)localArrayList2.get(k));
/*  88 */         if (k < ((lE)localObject).a().size() - 1) {
/*  89 */           paramInt.append(",");
/*     */         }
/*     */       }
/*     */ 
/*  93 */       arrayOfObject[paramvg] = ((lE)localObject).getName();
/*  94 */       arrayOfObject[(paramvg + 1)] = Long.valueOf(((lE)localObject).b());
/*  95 */       arrayOfObject[(paramvg + 2)] = Long.valueOf(((lE)localObject).c());
/*  96 */       arrayOfObject[(paramvg + 3)] = paramInt.toString();
/*     */     }
/*     */ 
/*  99 */     for (j = 0; j < arrayOfObject.length; j++) {
/* 100 */       assert (arrayOfObject[j] != null) : Arrays.toString(arrayOfObject);
/*     */     }
/* 102 */     return arrayOfObject;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.StarMadePlayerStats
 * JD-Core Version:    0.6.2
 */