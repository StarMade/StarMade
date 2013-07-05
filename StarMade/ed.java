/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class ed extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public ed()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  24 */     (
/*  26 */       localObjectArrayList = new ObjectArrayList())
/*  26 */       .add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  28 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*  29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*  30 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  31 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
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
/*  50 */           s2 = 0;
/*     */         }
/*  52 */         else if (s2 == 1) {
/*  53 */           i = 2;
/*  54 */           s2 = 3;
/*     */         }
/*  56 */         else if (s2 == 2) {
/*  57 */           bool = true;
/*     */         }
/*  59 */         else if (s2 == 3) {
/*  60 */           bool = true; } break;
/*     */       case 0:
/*  65 */         s2 = 0;
/*  66 */         break;
/*     */       case 1:
/*  70 */         break;
/*     */       case 5:
/*  73 */         if (s2 == 0)
/*  74 */           s2 = 3; break;
/*     */       case 4:
/*  79 */         if (s2 == 3) {
/*  80 */           s2 = 0;
/*     */         }
/*     */         break;
/*     */       case 2:
/*     */       }
/*  85 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  90 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  91 */       short s2 = s1;
/*  92 */       int i = paramInt1;
/*  93 */       boolean bool = false;
/*  94 */       switch (paramInt1)
/*     */       {
/*     */       case 3:
/* 100 */         if (s2 == 0) {
/* 101 */           i = 2;
/* 102 */           s2 = 0;
/*     */         }
/* 104 */         else if (s2 == 1) {
/* 105 */           i = 2;
/* 106 */           s2 = 3;
/*     */         }
/* 108 */         else if (s2 == 2) {
/* 109 */           bool = true;
/*     */         }
/* 111 */         else if (s2 == 3) {
/* 112 */           bool = true; } break;
/*     */       case 0:
/* 117 */         s2 = 0;
/* 118 */         break;
/*     */       case 1:
/* 122 */         break;
/*     */       case 5:
/* 125 */         if (s2 == 0)
/* 126 */           s2 = 3; break;
/*     */       case 4:
/* 131 */         if (s2 == 3) {
/* 132 */           s2 = 0;
/*     */         }
/*     */         break;
/*     */       case 2:
/*     */       }
/* 137 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 143 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ed
 * JD-Core Version:    0.6.2
 */