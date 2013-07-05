/*     */ package paulscode.sound;
/*     */ 
/*     */ public class Vector3D
/*     */ {
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*     */ 
/*     */   public Vector3D()
/*     */   {
/*  62 */     this.x = 0.0F;
/*  63 */     this.y = 0.0F;
/*  64 */     this.z = 0.0F;
/*     */   }
/*     */ 
/*     */   public Vector3D(float nx, float ny, float nz)
/*     */   {
/*  75 */     this.x = nx;
/*  76 */     this.y = ny;
/*  77 */     this.z = nz;
/*     */   }
/*     */ 
/*     */   public Vector3D clone()
/*     */   {
/*  87 */     return new Vector3D(this.x, this.y, this.z);
/*     */   }
/*     */ 
/*     */   public Vector3D cross(Vector3D A, Vector3D B)
/*     */   {
/*  98 */     return new Vector3D(A.y * B.z - B.y * A.z, A.z * B.x - B.z * A.x, A.x * B.y - B.x * A.y);
/*     */   }
/*     */ 
/*     */   public Vector3D cross(Vector3D B)
/*     */   {
/* 111 */     return new Vector3D(this.y * B.z - B.y * this.z, this.z * B.x - B.z * this.x, this.x * B.y - B.x * this.y);
/*     */   }
/*     */ 
/*     */   public float dot(Vector3D A, Vector3D B)
/*     */   {
/* 126 */     return A.x * B.x + A.y * B.y + A.z * B.z;
/*     */   }
/*     */ 
/*     */   public float dot(Vector3D B)
/*     */   {
/* 136 */     return this.x * B.x + this.y * B.y + this.z * B.z;
/*     */   }
/*     */ 
/*     */   public Vector3D add(Vector3D A, Vector3D B)
/*     */   {
/* 147 */     return new Vector3D(A.x + B.x, A.y + B.y, A.z + B.z);
/*     */   }
/*     */ 
/*     */   public Vector3D add(Vector3D B)
/*     */   {
/* 157 */     return new Vector3D(this.x + B.x, this.y + B.y, this.z + B.z);
/*     */   }
/*     */ 
/*     */   public Vector3D subtract(Vector3D A, Vector3D B)
/*     */   {
/* 168 */     return new Vector3D(A.x - B.x, A.y - B.y, A.z - B.z);
/*     */   }
/*     */ 
/*     */   public Vector3D subtract(Vector3D B)
/*     */   {
/* 178 */     return new Vector3D(this.x - B.x, this.y - B.y, this.z - B.z);
/*     */   }
/*     */ 
/*     */   public float length()
/*     */   {
/* 187 */     return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */ 
/*     */   public void normalize()
/*     */   {
/* 195 */     double t = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/* 196 */     this.x = ((float)(this.x / t));
/* 197 */     this.y = ((float)(this.y / t));
/* 198 */     this.z = ((float)(this.z / t));
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 208 */     return "Vector3D (" + this.x + ", " + this.y + ", " + this.z + ")";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.Vector3D
 * JD-Core Version:    0.6.2
 */