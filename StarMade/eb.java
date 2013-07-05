/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class eb extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public eb()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  24 */     (
/*  26 */       localObjectArrayList = new ObjectArrayList())
/*  26 */       .add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  28 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  31 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
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
/*  48 */         if (s2 == 0) {
/*  49 */           i = 2;
/*     */         }
/*  52 */         else if (s2 == 1) {
/*  53 */           bool = true;
/*     */         }
/*  55 */         else if (s2 == 2) {
/*  56 */           bool = true;
/*     */         }
/*  58 */         else if (s2 == 3) {
/*  59 */           i = 2;
/*  60 */           s2 = 1; } break;
/*     */       case 1:
/*  67 */         if (s2 == 2)
/*  68 */           s2 = 1; break;
/*     */       case 0:
/*  73 */         if (s2 != 1);
/*     */         break;
/*     */       case 5:
/*  74 */         s2 = 0;
/*     */       case 2:
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
/*     */       case 3:
/*  98 */         if (s2 == 0) {
/*  99 */           i = 2;
/*     */         }
/* 102 */         else if (s2 == 1) {
/* 103 */           bool = true;
/*     */         }
/* 105 */         else if (s2 == 2) {
/* 106 */           bool = true;
/*     */         }
/* 108 */         else if (s2 == 3) {
/* 109 */           i = 2;
/* 110 */           s2 = 1; } break;
/*     */       case 1:
/* 117 */         if (s2 == 2)
/* 118 */           s2 = 1; break;
/*     */       case 0:
/* 123 */         if (s2 != 1);
/*     */         break;
/*     */       case 5:
/* 124 */         s2 = 0;
/*     */       case 2:
/*     */       case 4:
/*     */       }
/*     */ 
/* 133 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 139 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     eb
 * JD-Core Version:    0.6.2
 */