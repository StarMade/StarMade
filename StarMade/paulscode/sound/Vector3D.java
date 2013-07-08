/*   1:    */package paulscode.sound;
/*   2:    */
/*  16:    */public class Vector3D
/*  17:    */{
/*  18:    */  public float x;
/*  19:    */  
/*  32:    */  public float y;
/*  33:    */  
/*  46:    */  public float z;
/*  47:    */  
/*  60:    */  public Vector3D()
/*  61:    */  {
/*  62: 62 */    this.x = 0.0F;
/*  63: 63 */    this.y = 0.0F;
/*  64: 64 */    this.z = 0.0F;
/*  65:    */  }
/*  66:    */  
/*  73:    */  public Vector3D(float nx, float ny, float nz)
/*  74:    */  {
/*  75: 75 */    this.x = nx;
/*  76: 76 */    this.y = ny;
/*  77: 77 */    this.z = nz;
/*  78:    */  }
/*  79:    */  
/*  85:    */  public Vector3D clone()
/*  86:    */  {
/*  87: 87 */    return new Vector3D(this.x, this.y, this.z);
/*  88:    */  }
/*  89:    */  
/*  96:    */  public Vector3D cross(Vector3D A, Vector3D B)
/*  97:    */  {
/*  98: 98 */    return new Vector3D(A.y * B.z - B.y * A.z, A.z * B.x - B.z * A.x, A.x * B.y - B.x * A.y);
/*  99:    */  }
/* 100:    */  
/* 109:    */  public Vector3D cross(Vector3D B)
/* 110:    */  {
/* 111:111 */    return new Vector3D(this.y * B.z - B.y * this.z, this.z * B.x - B.z * this.x, this.x * B.y - B.x * this.y);
/* 112:    */  }
/* 113:    */  
/* 124:    */  public float dot(Vector3D A, Vector3D B)
/* 125:    */  {
/* 126:126 */    return A.x * B.x + A.y * B.y + A.z * B.z;
/* 127:    */  }
/* 128:    */  
/* 134:    */  public float dot(Vector3D B)
/* 135:    */  {
/* 136:136 */    return this.x * B.x + this.y * B.y + this.z * B.z;
/* 137:    */  }
/* 138:    */  
/* 145:    */  public Vector3D add(Vector3D A, Vector3D B)
/* 146:    */  {
/* 147:147 */    return new Vector3D(A.x + B.x, A.y + B.y, A.z + B.z);
/* 148:    */  }
/* 149:    */  
/* 155:    */  public Vector3D add(Vector3D B)
/* 156:    */  {
/* 157:157 */    return new Vector3D(this.x + B.x, this.y + B.y, this.z + B.z);
/* 158:    */  }
/* 159:    */  
/* 166:    */  public Vector3D subtract(Vector3D A, Vector3D B)
/* 167:    */  {
/* 168:168 */    return new Vector3D(A.x - B.x, A.y - B.y, A.z - B.z);
/* 169:    */  }
/* 170:    */  
/* 176:    */  public Vector3D subtract(Vector3D B)
/* 177:    */  {
/* 178:178 */    return new Vector3D(this.x - B.x, this.y - B.y, this.z - B.z);
/* 179:    */  }
/* 180:    */  
/* 185:    */  public float length()
/* 186:    */  {
/* 187:187 */    return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/* 188:    */  }
/* 189:    */  
/* 193:    */  public void normalize()
/* 194:    */  {
/* 195:195 */    double t = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/* 196:196 */    this.x = ((float)(this.x / t));
/* 197:197 */    this.y = ((float)(this.y / t));
/* 198:198 */    this.z = ((float)(this.z / t));
/* 199:    */  }
/* 200:    */  
/* 206:    */  public String toString()
/* 207:    */  {
/* 208:208 */    return "Vector3D (" + this.x + ", " + this.y + ", " + this.z + ")";
/* 209:    */  }
/* 210:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.Vector3D
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */