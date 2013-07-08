/*   1:    */import com.bulletphysics.collision.shapes.TriangleCallback;
/*   2:    */import javax.vecmath.Vector3f;
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */
/* 162:    */final class xl
/* 163:    */  extends TriangleCallback
/* 164:    */{
/* 165:165 */  public boolean a = false;
/* 166:    */  
/* 178:    */  public final void processTriangle(Vector3f[] paramArrayOfVector3f, int paramInt1, int paramInt2)
/* 179:    */  {
/* 180:180 */    GL11.glDisable(2896);
/* 181:181 */    GL11.glDisable(3553);
/* 182:182 */    GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 183:183 */    if (this.a) {
/* 184:184 */      GL11.glBegin(1);
/* 185:185 */      GL11.glColor3f(1.0F, 0.0F, 0.0F);
/* 186:186 */      GL11.glVertex3f(paramArrayOfVector3f[0].x, paramArrayOfVector3f[0].y, paramArrayOfVector3f[0].z);
/* 187:187 */      GL11.glVertex3f(paramArrayOfVector3f[1].x, paramArrayOfVector3f[1].y, paramArrayOfVector3f[1].z);
/* 188:188 */      GL11.glColor3f(0.0F, 1.0F, 0.0F);
/* 189:189 */      GL11.glVertex3f(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z);
/* 190:190 */      GL11.glVertex3f(paramArrayOfVector3f[1].x, paramArrayOfVector3f[1].y, paramArrayOfVector3f[1].z);
/* 191:191 */      GL11.glColor3f(0.0F, 0.0F, 1.0F);
/* 192:192 */      GL11.glVertex3f(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z);
/* 193:193 */      GL11.glVertex3f(paramArrayOfVector3f[0].x, paramArrayOfVector3f[0].y, paramArrayOfVector3f[0].z);
/* 194:194 */      GL11.glEnd();
/* 195:    */    }
/* 196:    */    else {
/* 197:197 */      GL11.glBegin(4);
/* 198:198 */      GL11.glColor3f(1.0F, 0.0F, 0.0F);
/* 199:199 */      GL11.glVertex3f(paramArrayOfVector3f[0].x, paramArrayOfVector3f[0].y, paramArrayOfVector3f[0].z);
/* 200:200 */      GL11.glColor3f(0.0F, 1.0F, 0.0F);
/* 201:201 */      GL11.glVertex3f(paramArrayOfVector3f[1].x, paramArrayOfVector3f[1].y, paramArrayOfVector3f[1].z);
/* 202:202 */      GL11.glColor3f(0.0F, 0.0F, 1.0F);
/* 203:203 */      GL11.glVertex3f(paramArrayOfVector3f[2].x, paramArrayOfVector3f[2].y, paramArrayOfVector3f[2].z);
/* 204:204 */      GL11.glEnd();
/* 205:    */    }
/* 206:206 */    GL11.glEnable(2896);
/* 207:    */  }
/* 208:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */