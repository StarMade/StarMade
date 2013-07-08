/*   1:    */package paulscode.sound;
/*   2:    */
/*  15:    */public class ListenerData
/*  16:    */{
/*  17:    */  public Vector3D position;
/*  18:    */  
/*  29:    */  public Vector3D lookAt;
/*  30:    */  
/*  41:    */  public Vector3D up;
/*  42:    */  
/*  53:    */  public Vector3D velocity;
/*  54:    */  
/*  65: 65 */  public float angle = 0.0F;
/*  66:    */  
/*  70:    */  public ListenerData()
/*  71:    */  {
/*  72: 72 */    this.position = new Vector3D(0.0F, 0.0F, 0.0F);
/*  73: 73 */    this.lookAt = new Vector3D(0.0F, 0.0F, -1.0F);
/*  74: 74 */    this.up = new Vector3D(0.0F, 1.0F, 0.0F);
/*  75: 75 */    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/*  76: 76 */    this.angle = 0.0F;
/*  77:    */  }
/*  78:    */  
/*  94:    */  public ListenerData(float pX, float pY, float pZ, float lX, float lY, float lZ, float uX, float uY, float uZ, float a)
/*  95:    */  {
/*  96: 96 */    this.position = new Vector3D(pX, pY, pZ);
/*  97: 97 */    this.lookAt = new Vector3D(lX, lY, lZ);
/*  98: 98 */    this.up = new Vector3D(uX, uY, uZ);
/*  99: 99 */    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/* 100:100 */    this.angle = a;
/* 101:    */  }
/* 102:    */  
/* 111:    */  public ListenerData(Vector3D p, Vector3D l, Vector3D u, float a)
/* 112:    */  {
/* 113:113 */    this.position = p.clone();
/* 114:114 */    this.lookAt = l.clone();
/* 115:115 */    this.up = u.clone();
/* 116:116 */    this.velocity = new Vector3D(0.0F, 0.0F, 0.0F);
/* 117:117 */    this.angle = a;
/* 118:    */  }
/* 119:    */  
/* 135:    */  public void setData(float pX, float pY, float pZ, float lX, float lY, float lZ, float uX, float uY, float uZ, float a)
/* 136:    */  {
/* 137:137 */    this.position.x = pX;
/* 138:138 */    this.position.y = pY;
/* 139:139 */    this.position.z = pZ;
/* 140:140 */    this.lookAt.x = lX;
/* 141:141 */    this.lookAt.y = lY;
/* 142:142 */    this.lookAt.z = lZ;
/* 143:143 */    this.up.x = uX;
/* 144:144 */    this.up.y = uY;
/* 145:145 */    this.up.z = uZ;
/* 146:146 */    this.angle = a;
/* 147:    */  }
/* 148:    */  
/* 157:    */  public void setData(Vector3D p, Vector3D l, Vector3D u, float a)
/* 158:    */  {
/* 159:159 */    this.position.x = p.x;
/* 160:160 */    this.position.y = p.y;
/* 161:161 */    this.position.z = p.z;
/* 162:162 */    this.lookAt.x = l.x;
/* 163:163 */    this.lookAt.y = l.y;
/* 164:164 */    this.lookAt.z = l.z;
/* 165:165 */    this.up.x = u.x;
/* 166:166 */    this.up.y = u.y;
/* 167:167 */    this.up.z = u.z;
/* 168:168 */    this.angle = a;
/* 169:    */  }
/* 170:    */  
/* 175:    */  public void setData(ListenerData l)
/* 176:    */  {
/* 177:177 */    this.position.x = l.position.x;
/* 178:178 */    this.position.y = l.position.y;
/* 179:179 */    this.position.z = l.position.z;
/* 180:180 */    this.lookAt.x = l.lookAt.x;
/* 181:181 */    this.lookAt.y = l.lookAt.y;
/* 182:182 */    this.lookAt.z = l.lookAt.z;
/* 183:183 */    this.up.x = l.up.x;
/* 184:184 */    this.up.y = l.up.y;
/* 185:185 */    this.up.z = l.up.z;
/* 186:186 */    this.angle = l.angle;
/* 187:    */  }
/* 188:    */  
/* 195:    */  public void setPosition(float x, float y, float z)
/* 196:    */  {
/* 197:197 */    this.position.x = x;
/* 198:198 */    this.position.y = y;
/* 199:199 */    this.position.z = z;
/* 200:    */  }
/* 201:    */  
/* 206:    */  public void setPosition(Vector3D p)
/* 207:    */  {
/* 208:208 */    this.position.x = p.x;
/* 209:209 */    this.position.y = p.y;
/* 210:210 */    this.position.z = p.z;
/* 211:    */  }
/* 212:    */  
/* 223:    */  public void setOrientation(float lX, float lY, float lZ, float uX, float uY, float uZ)
/* 224:    */  {
/* 225:225 */    this.lookAt.x = lX;
/* 226:226 */    this.lookAt.y = lY;
/* 227:227 */    this.lookAt.z = lZ;
/* 228:228 */    this.up.x = uX;
/* 229:229 */    this.up.y = uY;
/* 230:230 */    this.up.z = uZ;
/* 231:    */  }
/* 232:    */  
/* 238:    */  public void setOrientation(Vector3D l, Vector3D u)
/* 239:    */  {
/* 240:240 */    this.lookAt.x = l.x;
/* 241:241 */    this.lookAt.y = l.y;
/* 242:242 */    this.lookAt.z = l.z;
/* 243:243 */    this.up.x = u.x;
/* 244:244 */    this.up.y = u.y;
/* 245:245 */    this.up.z = u.z;
/* 246:    */  }
/* 247:    */  
/* 252:    */  public void setVelocity(Vector3D v)
/* 253:    */  {
/* 254:254 */    this.velocity.x = v.x;
/* 255:255 */    this.velocity.y = v.y;
/* 256:256 */    this.velocity.z = v.z;
/* 257:    */  }
/* 258:    */  
/* 265:    */  public void setVelocity(float x, float y, float z)
/* 266:    */  {
/* 267:267 */    this.velocity.x = x;
/* 268:268 */    this.velocity.y = y;
/* 269:269 */    this.velocity.z = z;
/* 270:    */  }
/* 271:    */  
/* 276:    */  public void setAngle(float a)
/* 277:    */  {
/* 278:278 */    this.angle = a;
/* 279:279 */    this.lookAt.x = (-1.0F * (float)Math.sin(this.angle));
/* 280:280 */    this.lookAt.z = (-1.0F * (float)Math.cos(this.angle));
/* 281:    */  }
/* 282:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.ListenerData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */