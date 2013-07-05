/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class eh extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public eh()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  24 */     (
/*  26 */       localObjectArrayList = new ObjectArrayList())
/*  26 */       .add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*     */ 
/*  29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*     */ 
/*  32 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  33 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*     */ 
/*  35 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  39 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  40 */       short s2 = s1;
/*  41 */       int i = paramInt;
/*  42 */       boolean bool = false;
/*     */ 
/*  44 */       switch (paramInt)
/*     */       {
/*     */       case 4:
/*  49 */         if (s2 == 0) {
/*  50 */           bool = true;
/*     */         }
/*  52 */         else if (s2 == 3) {
/*  53 */           bool = true;
/*     */         }
/*  55 */         else if (s2 == 1) {
/*  56 */           i = 5;
/*  57 */           s2 = 2;
/*     */         }
/*  59 */         else if (s2 == 2) {
/*  60 */           i = 5;
/*  61 */           s2 = 1; } break;
/*     */       case 1:
/*  66 */         s2 = 0;
/*  67 */         break;
/*     */       case 0:
/*  70 */         break;
/*     */       case 3:
/*  73 */         if (s2 == 2)
/*  74 */           s2 = 1; break;
/*     */       case 2:
/*  79 */         if (s2 == 2) {
/*  80 */           s2 = 1;
/*     */         }
/*     */         break;
/*     */       }
/*     */ 
/*  85 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*  89 */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) { for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  90 */       short s2 = s1;
/*  91 */       int i = paramInt1;
/*  92 */       boolean bool = false;
/*     */ 
/*  94 */       switch (paramInt1)
/*     */       {
/*     */       case 4:
/*  99 */         if (s2 == 0) {
/* 100 */           bool = true;
/*     */         }
/* 102 */         else if (s2 == 3) {
/* 103 */           bool = true;
/*     */         }
/* 105 */         else if (s2 == 1) {
/* 106 */           i = 5;
/* 107 */           s2 = 2;
/*     */         }
/* 109 */         else if (s2 == 2) {
/* 110 */           i = 5;
/* 111 */           s2 = 1; } break;
/*     */       case 1:
/* 116 */         s2 = 0;
/* 117 */         break;
/*     */       case 0:
/* 120 */         break;
/*     */       case 3:
/* 123 */         if (s2 == 2)
/* 124 */           s2 = 1; break;
/*     */       case 2:
/* 129 */         if (s2 == 2) {
/* 130 */           s2 = 1;
/*     */         }
/*     */         break;
/*     */       }
/*     */ 
/* 135 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 141 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     eh
 * JD-Core Version:    0.6.2
 */