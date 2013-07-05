/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class ef extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public ef()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  24 */     (
/*  26 */       localObjectArrayList = new ObjectArrayList())
/*  26 */       .add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*     */ 
/*  29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*     */ 
/*  32 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  33 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*     */ 
/*  35 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
/*     */   {
/*  40 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  41 */       short s2 = s1;
/*  42 */       int i = paramInt;
/*  43 */       boolean bool = false;
/*  44 */       switch (paramInt)
/*     */       {
/*     */       case 1:
/*  50 */         if (s2 == 0)
/*     */         {
/*  52 */           bool = true;
/*  53 */         } else if (s2 == 1) {
/*  54 */           i = 0;
/*  55 */           s2 = 2;
/*     */         }
/*  57 */         else if (s2 == 2) {
/*  58 */           i = 0;
/*  59 */           s2 = 1;
/*     */         }
/*  61 */         else if (s2 == 3) {
/*  62 */           bool = true; } break;
/*     */       case 3:
/*  67 */         if (s2 == 3)
/*  68 */           s2 = 2; break;
/*     */       case 2:
/*  73 */         if (s2 != 1) break; case 5:
/*  74 */         s2 = 0;
/*     */       case 4:
/*     */       }
/*     */ 
/*  83 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  88 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  89 */       short s2 = s1;
/*  90 */       int i = paramInt1;
/*  91 */       boolean bool = false;
/*  92 */       switch (paramInt1)
/*     */       {
/*     */       case 1:
/*  98 */         if (s2 == 0)
/*     */         {
/* 100 */           bool = true;
/* 101 */         } else if (s2 == 1) {
/* 102 */           i = 0;
/* 103 */           s2 = 2;
/*     */         }
/* 105 */         else if (s2 == 2) {
/* 106 */           i = 0;
/* 107 */           s2 = 1;
/*     */         }
/* 109 */         else if (s2 == 3) {
/* 110 */           bool = true; } break;
/*     */       case 3:
/* 115 */         if (s2 == 3)
/* 116 */           s2 = 2; break;
/*     */       case 2:
/* 121 */         if (s2 != 1) break; case 5:
/* 122 */         s2 = 0;
/*     */       case 4:
/*     */       }
/*     */ 
/* 131 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 137 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ef
 * JD-Core Version:    0.6.2
 */