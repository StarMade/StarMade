/*     */ import com.bulletphysics.collision.shapes.TriangleCallback;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ final class xl extends TriangleCallback
/*     */ {
/* 165 */   public boolean a = false;
/*     */ 
/*     */   public final void processTriangle(Vector3f[] paramArrayOfVector3f, int paramInt1, int paramInt2)
/*     */   {
/* 180 */     GL11.glDisable(2896);
/* 181 */     GL11.glDisable(3553);
/* 182 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 183 */     if (this.a) {
/* 184 */       GL11.glBegin(1);
/* 185 */       GL11.glColor3f(1.0F, 0.0F, 0.0F);
/* 186 */       GL11.glVertex3f(paramArrayOfVector3f[0].x, paramArrayOfVector3f[0].y, paramArrayOfVector3f[0].z);
/* 187 */       GL11.glVertex3f(paramArrayOfVector3f[1].x, paramArrayOfVector3f[1].y, paramArrayOfVector3f[1].z);
/* 188 */       GL11.glColor3f(0.0F, 1.0F, 0.0F);
/* 189 */       GL11.glVertex3f(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z);
/* 190 */       GL11.glVertex3f(paramArrayOfVector3f[1].x, paramArrayOfVector3f[1].y, paramArrayOfVector3f[1].z);
/* 191 */       GL11.glColor3f(0.0F, 0.0F, 1.0F);
/* 192 */       GL11.glVertex3f(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z);
/* 193 */       GL11.glVertex3f(paramArrayOfVector3f[0].x, paramArrayOfVector3f[0].y, paramArrayOfVector3f[0].z);
/* 194 */       GL11.glEnd();
/*     */     }
/*     */     else {
/* 197 */       GL11.glBegin(4);
/* 198 */       GL11.glColor3f(1.0F, 0.0F, 0.0F);
/* 199 */       GL11.glVertex3f(paramArrayOfVector3f[0].x, paramArrayOfVector3f[0].y, paramArrayOfVector3f[0].z);
/* 200 */       GL11.glColor3f(0.0F, 1.0F, 0.0F);
/* 201 */       GL11.glVertex3f(paramArrayOfVector3f[1].x, paramArrayOfVector3f[1].y, paramArrayOfVector3f[1].z);
/* 202 */       GL11.glColor3f(0.0F, 0.0F, 1.0F);
/* 203 */       GL11.glVertex3f(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z);
/* 204 */       GL11.glEnd();
/*     */     }
/* 206 */     GL11.glEnable(2896);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xl
 * JD-Core Version:    0.6.2
 */