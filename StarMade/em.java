/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class em extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public em()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  26 */     (
/*  28 */       localObjectArrayList = new ObjectArrayList())
/*  28 */       .add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  29 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  30 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  31 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  32 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  33 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*     */ 
/*  35 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  39 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  40 */       short s2 = s1;
/*  41 */       int i = paramInt;
/*  42 */       boolean bool = false;
/*  43 */       switch (paramInt)
/*     */       {
/*     */       case 2:
/*  49 */         if (s2 == 1) {
/*  50 */           i = 3;
/*  51 */           s2 = 3;
/*  52 */           bool = true;
/*     */         }
/*  54 */         else if (s2 == 2) {
/*  55 */           i = 3;
/*  56 */           s2 = 2;
/*  57 */           bool = true; } break;
/*     */       case 0:
/*  63 */         break;
/*     */       case 1:
/*  66 */         s2 = 0;
/*  67 */         break;
/*     */       case 5:
/*  70 */         if (s2 == 2)
/*  71 */           s2 = 1; break;
/*     */       case 4:
/*  76 */         if (s2 == 1) {
/*  77 */           s2 = 2;
/*     */         }
/*     */         break;
/*     */       case 3:
/*     */       }
/*  82 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*  86 */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) { for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  87 */       short s2 = s1;
/*  88 */       int i = paramInt1;
/*  89 */       boolean bool = false;
/*  90 */       switch (paramInt1)
/*     */       {
/*     */       case 2:
/*  96 */         if (s2 == 1) {
/*  97 */           i = 3;
/*  98 */           s2 = 3;
/*  99 */           bool = true;
/*     */         }
/* 101 */         else if (s2 == 2) {
/* 102 */           i = 3;
/* 103 */           s2 = 2;
/* 104 */           bool = true; } break;
/*     */       case 0:
/* 110 */         break;
/*     */       case 1:
/* 113 */         s2 = 0;
/* 114 */         break;
/*     */       case 5:
/* 117 */         if (s2 == 2)
/* 118 */           s2 = 1; break;
/*     */       case 4:
/* 123 */         if (s2 == 1) {
/* 124 */           s2 = 2;
/*     */         }
/*     */         break;
/*     */       case 3:
/*     */       }
/* 129 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 137 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     em
 * JD-Core Version:    0.6.2
 */