/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class ej extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public ej()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  22 */     (
/*  24 */       localObjectArrayList = new ObjectArrayList())
/*  24 */       .add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  25 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  26 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  28 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, -0.5F));
/*  29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, -0.5F));
/*     */ 
/*  31 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer)
/*     */   {
/*  36 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  37 */       short s2 = s1;
/*  38 */       int i = paramInt;
/*  39 */       boolean bool = false;
/*  40 */       switch (paramInt)
/*     */       {
/*     */       case 2:
/*  46 */         if (s2 == 2) {
/*  47 */           i = 3;
/*  48 */           s2 = 2;
/*  49 */           bool = true;
/*     */         }
/*  51 */         else if (s2 == 3) {
/*  52 */           i = 3;
/*  53 */           s2 = 1;
/*  54 */           bool = true; } break;
/*     */       case 1:
/*  59 */         if (s2 == 0)
/*  60 */           s2 = 3; break;
/*     */       case 0:
/*  65 */         if (s2 == 3)
/*  66 */           s2 = 2; break;
/*     */       case 4:
/*  71 */         s2 = 0;
/*     */       case 3:
/*     */       }
/*     */ 
/*  75 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer)
/*     */   {
/*  81 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  82 */       short s2 = s1;
/*  83 */       int i = paramInt1;
/*  84 */       boolean bool = false;
/*  85 */       switch (paramInt1)
/*     */       {
/*     */       case 2:
/*  91 */         if (s2 == 2) {
/*  92 */           i = 3;
/*  93 */           s2 = 2;
/*  94 */           bool = true;
/*     */         }
/*  96 */         else if (s2 == 3) {
/*  97 */           i = 3;
/*  98 */           s2 = 1;
/*  99 */           bool = true; } break;
/*     */       case 1:
/* 104 */         if (s2 == 0)
/* 105 */           s2 = 3; break;
/*     */       case 0:
/* 110 */         if (s2 == 3)
/* 111 */           s2 = 2; break;
/*     */       case 4:
/* 116 */         s2 = 0;
/*     */       case 3:
/*     */       }
/*     */ 
/* 120 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 126 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ej
 * JD-Core Version:    0.6.2
 */