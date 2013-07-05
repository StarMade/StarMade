/*     */ package paulscode.sound;
/*     */ 
/*     */ public class ListenerData
/*     */ {
/*     */   public Vector3D position;
/*     */   public Vector3D lookAt;
/*     */   public Vector3D up;
/*     */   public Vector3D velocity;
/*  65 */   public float angle = 0.0F;
/*     */ 
/*     */   public ListenerData()
/*     */   {
/*  72 */     this.position = new Vector3D(0.0F, 0.0F, 0.0F);
/*  73 */     this.lookAt = new Vector3D(0.0F, 0.0F, -1.0F);
/*  74 */     this.up = new Vector3D(0.0F, 1.0F, 0.0F);
/*  75 */     this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/*  76 */     this.angle = 0.0F;
/*     */   }
/*     */ 
/*     */   public ListenerData(float pX, float pY, float pZ, float lX, float lY, float lZ, float uX, float uY, float uZ, float a)
/*     */   {
/*  96 */     this.position = new Vector3D(pX, pY, pZ);
/*  97 */     this.lookAt = new Vector3D(lX, lY, lZ);
/*  98 */     this.up = new Vector3D(uX, uY, uZ);
/*  99 */     this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/* 100 */     this.angle = a;
/*     */   }
/*     */ 
/*     */   public ListenerData(Vector3D p, Vector3D l, Vector3D u, float a)
/*     */   {
/* 113 */     this.position = p.clone();
/* 114 */     this.lookAt = l.clone();
/* 115 */     this.up = u.clone();
/* 116 */     this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/* 117 */     this.angle = a;
/*     */   }
/*     */ 
/*     */   public void setData(float pX, float pY, float pZ, float lX, float lY, float lZ, float uX, float uY, float uZ, float a)
/*     */   {
/* 137 */     this.position.x = pX;
/* 138 */     this.position.y = pY;
/* 139 */     this.position.z = pZ;
/* 140 */     this.lookAt.x = lX;
/* 141 */     this.lookAt.y = lY;
/* 142 */     this.lookAt.z = lZ;
/* 143 */     this.up.x = uX;
/* 144 */     this.up.y = uY;
/* 145 */     this.up.z = uZ;
/* 146 */     this.angle = a;
/*     */   }
/*     */ 
/*     */   public void setData(Vector3D p, Vector3D l, Vector3D u, float a)
/*     */   {
/* 159 */     this.position.x = p.x;
/* 160 */     this.position.y = p.y;
/* 161 */     this.position.z = p.z;
/* 162 */     this.lookAt.x = l.x;
/* 163 */     this.lookAt.y = l.y;
/* 164 */     this.lookAt.z = l.z;
/* 165 */     this.up.x = u.x;
/* 166 */     this.up.y = u.y;
/* 167 */     this.up.z = u.z;
/* 168 */     this.angle = a;
/*     */   }
/*     */ 
/*     */   public void setData(ListenerData l)
/*     */   {
/* 177 */     this.position.x = l.position.x;
/* 178 */     this.position.y = l.position.y;
/* 179 */     this.position.z = l.position.z;
/* 180 */     this.lookAt.x = l.lookAt.x;
/* 181 */     this.lookAt.y = l.lookAt.y;
/* 182 */     this.lookAt.z = l.lookAt.z;
/* 183 */     this.up.x = l.up.x;
/* 184 */     this.up.y = l.up.y;
/* 185 */     this.up.z = l.up.z;
/* 186 */     this.angle = l.angle;
/*     */   }
/*     */ 
/*     */   public void setPosition(float x, float y, float z)
/*     */   {
/* 197 */     this.position.x = x;
/* 198 */     this.position.y = y;
/* 199 */     this.position.z = z;
/*     */   }
/*     */ 
/*     */   public void setPosition(Vector3D p)
/*     */   {
/* 208 */     this.position.x = p.x;
/* 209 */     this.position.y = p.y;
/* 210 */     this.position.z = p.z;
/*     */   }
/*     */ 
/*     */   public void setOrientation(float lX, float lY, float lZ, float uX, float uY, float uZ)
/*     */   {
/* 225 */     this.lookAt.x = lX;
/* 226 */     this.lookAt.y = lY;
/* 227 */     this.lookAt.z = lZ;
/* 228 */     this.up.x = uX;
/* 229 */     this.up.y = uY;
/* 230 */     this.up.z = uZ;
/*     */   }
/*     */ 
/*     */   public void setOrientation(Vector3D l, Vector3D u)
/*     */   {
/* 240 */     this.lookAt.x = l.x;
/* 241 */     this.lookAt.y = l.y;
/* 242 */     this.lookAt.z = l.z;
/* 243 */     this.up.x = u.x;
/* 244 */     this.up.y = u.y;
/* 245 */     this.up.z = u.z;
/*     */   }
/*     */ 
/*     */   public void setVelocity(Vector3D v)
/*     */   {
/* 254 */     this.velocity.x = v.x;
/* 255 */     this.velocity.y = v.y;
/* 256 */     this.velocity.z = v.z;
/*     */   }
/*     */ 
/*     */   public void setVelocity(float x, float y, float z)
/*     */   {
/* 267 */     this.velocity.x = x;
/* 268 */     this.velocity.y = y;
/* 269 */     this.velocity.z = z;
/*     */   }
/*     */ 
/*     */   public void setAngle(float a)
/*     */   {
/* 278 */     this.angle = a;
/* 279 */     this.lookAt.x = (-1.0F * (float)Math.sin(this.angle));
/* 280 */     this.lookAt.z = (-1.0F * (float)Math.cos(this.angle));
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.ListenerData
 * JD-Core Version:    0.6.2
 */