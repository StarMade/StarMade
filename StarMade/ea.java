/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class ea extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public ea()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  24 */     (
/*  26 */       localObjectArrayList = new ObjectArrayList())
/*  26 */       .add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  28 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  31 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*     */ 
/*  33 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
/*     */   {
/*  38 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  39 */       short s2 = s1;
/*  40 */       int i = paramInt;
/*  41 */       boolean bool = false;
/*  42 */       switch (paramInt)
/*     */       {
/*     */       case 3:
/*  48 */         if (s2 == 0)
/*     */         {
/*  50 */           bool = true;
/*  51 */         } else if (s2 == 1) {
/*  52 */           i = 2;
/*  53 */           s2 = 3;
/*     */         }
/*  55 */         else if (s2 == 2) {
/*  56 */           i = 2;
/*  57 */           s2 = 2;
/*     */         }
/*  59 */         else if (s2 == 3) {
/*  60 */           bool = true; } break;
/*     */       case 1:
/*  65 */         if (s2 == 3)
/*  66 */           s2 = 2; break;
/*     */       case 0:
/*  71 */         if (s2 == 0)
/*  72 */           s2 = 1; break;
/*     */       case 4:
/*  77 */         s2 = 0;
/*     */       case 2:
/*     */       }
/*     */ 
/*  81 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  86 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  87 */       short s2 = s1;
/*  88 */       int i = paramInt1;
/*  89 */       boolean bool = false;
/*  90 */       switch (paramInt1)
/*     */       {
/*     */       case 3:
/*  96 */         if (s2 == 0)
/*     */         {
/*  98 */           bool = true;
/*  99 */         } else if (s2 == 1) {
/* 100 */           i = 2;
/* 101 */           s2 = 3;
/*     */         }
/* 103 */         else if (s2 == 2) {
/* 104 */           i = 2;
/* 105 */           s2 = 2;
/*     */         }
/* 107 */         else if (s2 == 3) {
/* 108 */           bool = true; } break;
/*     */       case 1:
/* 113 */         if (s2 == 3)
/* 114 */           s2 = 2; break;
/*     */       case 0:
/* 119 */         if (s2 == 0)
/* 120 */           s2 = 1; break;
/*     */       case 4:
/* 125 */         s2 = 0;
/*     */       case 2:
/*     */       }
/*     */ 
/* 129 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 135 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ea
 * JD-Core Version:    0.6.2
 */