/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class ec extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public ec()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  24 */     (
/*  26 */       localObjectArrayList = new ObjectArrayList())
/*  26 */       .add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  28 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  31 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*     */ 
/*  33 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  37 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  38 */       short s2 = s1;
/*  39 */       int i = paramInt;
/*  40 */       boolean bool = false;
/*     */ 
/*  42 */       switch (paramInt)
/*     */       {
/*     */       case 3:
/*  47 */         if (s2 == 0) {
/*  48 */           bool = true;
/*     */         }
/*  50 */         else if (s2 == 1) {
/*  51 */           bool = true;
/*     */         }
/*  53 */         else if (s2 == 2) {
/*  54 */           i = 2;
/*  55 */           s2 = 2;
/*     */         }
/*  57 */         else if (s2 == 3) {
/*  58 */           i = 2;
/*  59 */           s2 = 1; } break;
/*     */       case 1:
/*  64 */         s2 = 0;
/*  65 */         break;
/*     */       case 0:
/*  68 */         break;
/*     */       case 5:
/*  71 */         if (s2 == 1)
/*  72 */           s2 = 0; break;
/*     */       case 4:
/*  77 */         if (s2 == 2) {
/*  78 */           s2 = 1;
/*     */         }
/*     */         break;
/*     */       case 2:
/*     */       }
/*  83 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*  87 */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) { for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  88 */       short s2 = s1;
/*  89 */       int i = paramInt1;
/*  90 */       boolean bool = false;
/*     */ 
/*  92 */       switch (paramInt1)
/*     */       {
/*     */       case 3:
/*  97 */         if (s2 == 0) {
/*  98 */           bool = true;
/*     */         }
/* 100 */         else if (s2 == 1) {
/* 101 */           bool = true;
/*     */         }
/* 103 */         else if (s2 == 2) {
/* 104 */           i = 2;
/* 105 */           s2 = 2;
/*     */         }
/* 107 */         else if (s2 == 3) {
/* 108 */           i = 2;
/* 109 */           s2 = 1; } break;
/*     */       case 1:
/* 114 */         s2 = 0;
/* 115 */         break;
/*     */       case 0:
/* 118 */         break;
/*     */       case 5:
/* 121 */         if (s2 == 1)
/* 122 */           s2 = 0; break;
/*     */       case 4:
/* 127 */         if (s2 == 2) {
/* 128 */           s2 = 1;
/*     */         }
/*     */         break;
/*     */       case 2:
/*     */       }
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
 * Qualified Name:     ec
 * JD-Core Version:    0.6.2
 */