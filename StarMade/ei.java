/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class ei extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public ei()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  27 */     (
/*  29 */       localObjectArrayList = new ObjectArrayList())
/*  29 */       .add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  30 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*     */ 
/*  32 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  33 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*     */ 
/*  35 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  36 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*     */ 
/*  39 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
/*     */   {
/*  44 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  45 */       short s2 = s1;
/*  46 */       int i = paramInt;
/*  47 */       boolean bool = false;
/*  48 */       switch (paramInt)
/*     */       {
/*     */       case 4:
/*  54 */         if (s2 == 1) {
/*  55 */           bool = true;
/*     */         }
/*  57 */         else if (s2 == 2) {
/*  58 */           bool = true;
/*     */         }
/*  60 */         else if (s2 == 0) {
/*  61 */           i = 5;
/*  62 */           s2 = 3;
/*     */         }
/*  64 */         else if (s2 == 3) {
/*  65 */           i = 5;
/*  66 */           s2 = 0; } break;
/*     */       case 1:
/*  72 */         break;
/*     */       case 0:
/*  75 */         s2 = 0;
/*  76 */         break;
/*     */       case 3:
/*  79 */         if (s2 == 1)
/*  80 */           s2 = 0; break;
/*     */       case 2:
/*  85 */         if (s2 == 3) {
/*  86 */           s2 = 2;
/*     */         }
/*     */         break;
/*     */       }
/*     */ 
/*  91 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  96 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  97 */       short s2 = s1;
/*  98 */       int i = paramInt1;
/*  99 */       boolean bool = false;
/*     */ 
/* 101 */       switch (paramInt1)
/*     */       {
/*     */       case 4:
/* 106 */         if (s2 == 1) {
/* 107 */           bool = true;
/*     */         }
/* 109 */         else if (s2 == 2) {
/* 110 */           bool = true;
/*     */         }
/* 112 */         else if (s2 == 0) {
/* 113 */           i = 5;
/* 114 */           s2 = 3;
/*     */         }
/* 116 */         else if (s2 == 3) {
/* 117 */           i = 5;
/* 118 */           s2 = 0; } break;
/*     */       case 1:
/* 124 */         break;
/*     */       case 0:
/* 127 */         s2 = 0;
/* 128 */         break;
/*     */       case 3:
/* 131 */         if (s2 == 1)
/* 132 */           s2 = 0; break;
/*     */       case 2:
/* 137 */         if (s2 == 3) {
/* 138 */           s2 = 2;
/*     */         }
/*     */         break;
/*     */       }
/*     */ 
/* 143 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 149 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ei
 * JD-Core Version:    0.6.2
 */