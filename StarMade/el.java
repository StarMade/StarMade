/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class el extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public el()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  22 */     (
/*  24 */       localObjectArrayList = new ObjectArrayList())
/*  24 */       .add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  25 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  26 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  28 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  29 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*     */ 
/*  33 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  37 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  38 */       short s2 = s1;
/*  39 */       int i = paramInt;
/*  40 */       boolean bool = false;
/*  41 */       switch (paramInt)
/*     */       {
/*     */       case 2:
/*  47 */         if (s2 == 3) {
/*  48 */           i = 3;
/*  49 */           s2 = 1;
/*  50 */           bool = true;
/*     */         }
/*  52 */         else if (s2 == 0) {
/*  53 */           i = 3;
/*  54 */           s2 = 0;
/*  55 */           bool = true; } break;
/*     */       case 1:
/*  61 */         break;
/*     */       case 0:
/*  64 */         s2 = 0;
/*  65 */         break;
/*     */       case 5:
/*  68 */         if (s2 == 3)
/*  69 */           s2 = 0; break;
/*     */       case 4:
/*  74 */         if (s2 == 0) {
/*  75 */           s2 = 3;
/*     */         }
/*     */         break;
/*     */       case 3:
/*     */       }
/*  80 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  85 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  86 */       short s2 = s1;
/*  87 */       int i = paramInt1;
/*  88 */       boolean bool = false;
/*  89 */       switch (paramInt1)
/*     */       {
/*     */       case 2:
/*  95 */         if (s2 == 3) {
/*  96 */           i = 3;
/*  97 */           s2 = 1;
/*  98 */           bool = true;
/*     */         }
/* 100 */         else if (s2 == 0) {
/* 101 */           i = 3;
/* 102 */           s2 = 0;
/* 103 */           bool = true; } break;
/*     */       case 1:
/* 109 */         break;
/*     */       case 0:
/* 112 */         s2 = 0;
/* 113 */         break;
/*     */       case 5:
/* 116 */         if (s2 == 3)
/* 117 */           s2 = 0; break;
/*     */       case 4:
/* 122 */         if (s2 == 0) {
/* 123 */           s2 = 3;
/*     */         }
/*     */         break;
/*     */       case 3:
/*     */       }
/* 128 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 134 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     el
 * JD-Core Version:    0.6.2
 */