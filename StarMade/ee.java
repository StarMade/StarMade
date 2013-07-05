/*    */ import com.bulletphysics.collision.shapes.ConvexHullShape;
/*    */ import com.bulletphysics.collision.shapes.ConvexShape;
/*    */ import com.bulletphysics.util.ObjectArrayList;
/*    */ import java.nio.FloatBuffer;
/*    */ import javax.vecmath.Vector3f;
/*    */ import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*    */ 
/*    */ public final class ee extends dO
/*    */   implements dP
/*    */ {
/*    */   private ConvexHullShape a;
/*    */ 
/*    */   public ee()
/*    */   {
/*    */     ObjectArrayList localObjectArrayList;
/* 22 */     (
/* 24 */       localObjectArrayList = new ObjectArrayList())
/* 24 */       .add(new Vector3f(-0.5F, -0.5F, -0.5F));
/* 25 */     localObjectArrayList.add(new Vector3f(-0.5F, -0.5F, 0.5F));
/* 26 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, 0.5F));
/* 27 */     localObjectArrayList.add(new Vector3f(0.5F, -0.5F, -0.5F));
/* 28 */     localObjectArrayList.add(new Vector3f(-0.5F, 0.5F, 0.5F));
/* 29 */     localObjectArrayList.add(new Vector3f(0.5F, 0.5F, 0.5F));
/*    */ 
/* 31 */     this.a = new ConvexHullShape(localObjectArrayList);
/*    */   }
/*    */   public final void a(int paramInt, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, FloatBuffer paramFloatBuffer) {
/* 34 */     for (short s1 = 0; s1 < 4; s1 = (short)(s1 + 1)) {
/* 35 */       short s2 = s1;
/* 36 */       int i = paramInt;
/* 37 */       boolean bool = false;
/* 38 */       switch (paramInt)
/*    */       {
/*    */       case 2:
/* 44 */         if (s2 == 0) {
/* 45 */           i = 3;
/* 46 */           s2 = 0;
/* 47 */           bool = true;
/*    */         }
/* 49 */         else if (s2 == 1) {
/* 50 */           i = 3;
/* 51 */           s2 = 3;
/* 52 */           bool = true; } else if (s2 == 2);
/*    */         break;
/*    */       case 1:
/* 55 */         if ((s2 != 3) || (goto 134) || 
/* 61 */           (s2 != 1));
/* 62 */         break;
/*    */       case 0:
/* 67 */         if (s2 == 2)
/* 68 */           s2 = 1; break;
/*    */       case 5:
/* 73 */         s2 = 0;
/*    */       case 3:
/*    */       case 4:
/*    */       }
/* 77 */       a(i, s2, paramByte1, paramShort, paramByte2, paramByte3, bool, false, s1, 2184, paramByte4, paramByte5, paramByte6, paramFloatBuffer);
/*    */     }
/*    */   }
/*    */ 
/*    */   public final void a(int paramInt1, byte paramByte1, short paramShort, byte paramByte2, byte paramByte3, int paramInt2, int paramInt3, int paramInt4, CubeMeshBufferContainer paramCubeMeshBufferContainer)
/*    */   {
/*    */   }
/*    */ 
/*    */   protected final ConvexShape a()
/*    */   {
/* 87 */     return this.a;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ee
 * JD-Core Version:    0.6.2
 */