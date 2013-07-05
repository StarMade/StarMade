/*     */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*     */ import com.bulletphysics.collision.shapes.ConvexShape;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.nio.FloatBuffer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*     */ 
/*     */ public final class ek extends dO
/*     */   implements dP
/*     */ {
/*     */   private ConvexHullShape a;
/*     */ 
/*     */   public ek()
/*     */   {
/*     */     ObjectArrayList localObjectArrayList;
/*  22 */     (
/*  24 */       localObjectArrayList = new ObjectArrayList())
/*  24 */       .add(new Vector3f(-0.5F, -0.5F, -0.5F));
/*  25 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/*  26 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/*  27 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/*  28 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/*  29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*     */ 
/*  31 */     this.a = new ConvexHullShape(localObjectArrayList);
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/*  35 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  36 */       short s2 = s1;
/*  37 */       int i = paramInt;
/*  38 */       boolean bool = false;
/*  39 */       switch (paramInt)
/*     */       {
/*     */       case 2:
/*  45 */         if (s2 == 0) {
/*  46 */           i = 3;
/*  47 */           s2 = 0;
/*  48 */           bool = true;
/*     */         }
/*  50 */         else if (s2 == 1) {
/*  51 */           i = 3;
/*  52 */           s2 = 3;
/*  53 */           bool = true; } else if (s2 == 2);
/*     */         break;
/*     */       case 1:
/*  56 */         if ((s2 != 3) || (goto 134) || 
/*  62 */           (s2 != 1));
/*  63 */         break;
/*     */       case 0:
/*  68 */         if (s2 == 2)
/*  69 */           s2 = 1; break;
/*     */       case 5:
/*  74 */         s2 = 0;
/*     */       case 3:
/*     */       case 4:
/*     */       }
/*  78 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer) {
/*  83 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/*  84 */       short s2 = s1;
/*  85 */       int i = paramInt1;
/*  86 */       boolean bool = false;
/*  87 */       switch (paramInt1)
/*     */       {
/*     */       case 2:
/*  93 */         if (s2 == 0) {
/*  94 */           i = 3;
/*  95 */           s2 = 0;
/*  96 */           bool = true;
/*     */         }
/*  98 */         else if (s2 == 1) {
/*  99 */           i = 3;
/* 100 */           s2 = 3;
/* 101 */           bool = true; } else if (s2 == 2);
/*     */         break;
/*     */       case 1:
/* 104 */         if ((s2 != 3) || (goto 134) || 
/* 110 */           (s2 != 1));
/* 111 */         break;
/*     */       case 0:
/* 116 */         if (s2 == 2)
/* 117 */           s2 = 1; break;
/*     */       case 5:
/* 122 */         s2 = 0;
/*     */       case 3:
/*     */       case 4:
/*     */       }
/* 126 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, paramInt2, paramInt3, s1, paramInt4, paramCubeMeshBufferContainer);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected final ConvexShape a()
/*     */   {
/* 132 */     return this.a;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ek
 * JD-Core Version:    0.6.2
 */