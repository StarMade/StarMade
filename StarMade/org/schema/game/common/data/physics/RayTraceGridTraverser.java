/*   1:    */package org.schema.game.common.data.physics;
/*   2:    */
/*   3:    */import jL;
/*   4:    */import jM;
/*   5:    */import java.io.PrintStream;
/*   6:    */import javax.vecmath.Vector3f;
/*   7:    */import org.schema.common.FastMath;
/*   8:    */import org.schema.game.common.controller.SegmentController;
/*   9:    */import org.schema.game.common.data.world.Segment;
/*  10:    */import q;
/*  11:    */
/*  16:    */public class RayTraceGridTraverser
/*  17:    */{
/*  18: 18 */  private q cell = new q();
/*  19: 19 */  private Vector3f tMax = new Vector3f();
/*  20: 20 */  private Vector3f tDelta = new Vector3f();
/*  21: 21 */  private Vector3f cellBoundary = new Vector3f();
/*  22:    */  private int stepX;
/*  23:    */  private int stepY;
/*  24:    */  private int stepZ;
/*  25:    */  private Ray ray;
/*  26:    */  
/*  27:    */  public static void main(String[] paramArrayOfString)
/*  28:    */  {
/*  29: 29 */    paramArrayOfString = new RayTraceGridTraverser();
/*  30:    */    Ray localRay;
/*  31: 31 */    (localRay = new Ray()).position = new Vector3f(16.638065F, 5.568517F, 18.922298F);
/*  32:    */    
/*  33: 33 */    localRay.direction = new Vector3f(0.0F, -6.0F, 0.0F);
/*  34: 34 */    paramArrayOfString.getCellsOnRay(localRay, 6, null, null);
/*  35:    */  }
/*  36:    */  
/*  37:    */  public void getCellsOnRay(Ray paramRay, int paramInt, jM paramjM, SegmentController paramSegmentController)
/*  38:    */  {
/*  39: 39 */    this.ray = paramRay;
/*  40:    */    
/*  48:    */    q localq;
/*  49:    */    
/*  56: 56 */    int j = (localq = getCellAt(paramRay.position, this.cell)).a;
/*  57: 57 */    int k = localq.b;
/*  58: 58 */    int i = localq.c;
/*  59:    */    
/*  62: 62 */    this.stepX = ((int)Math.signum(paramRay.direction.x));
/*  63: 63 */    this.stepY = ((int)Math.signum(paramRay.direction.y));
/*  64: 64 */    this.stepZ = ((int)Math.signum(paramRay.direction.z));
/*  65:    */    
/*  69: 69 */    this.cellBoundary.set(j + (this.stepX > 0 ? 1 : 0), k + (this.stepY > 0 ? 1 : 0), i + (this.stepZ > 0 ? 1 : 0));
/*  70:    */    
/*  79: 79 */    this.tMax.set((this.cellBoundary.x - paramRay.position.x) / paramRay.direction.x, (this.cellBoundary.y - paramRay.position.y) / paramRay.direction.y, (this.cellBoundary.z - paramRay.position.z) / paramRay.direction.z);
/*  80:    */    
/*  83: 83 */    if ((Float.isNaN(this.tMax.x)) || (Float.isInfinite(this.tMax.x))) this.tMax.x = (1.0F / 1.0F);
/*  84: 84 */    if ((Float.isNaN(this.tMax.y)) || (Float.isInfinite(this.tMax.y))) this.tMax.y = (1.0F / 1.0F);
/*  85: 85 */    if ((Float.isNaN(this.tMax.z)) || (Float.isInfinite(this.tMax.z))) { this.tMax.z = (1.0F / 1.0F);
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    this.tDelta.set(this.stepX / paramRay.direction.x, this.stepY / paramRay.direction.y, this.stepZ / paramRay.direction.z);
/*  89:    */    
/*  92: 92 */    if (Float.isNaN(this.tDelta.x)) this.tDelta.x = (1.0F / 1.0F);
/*  93: 93 */    if (Float.isNaN(this.tDelta.y)) this.tDelta.y = (1.0F / 1.0F);
/*  94: 94 */    if (Float.isNaN(this.tDelta.z)) { this.tDelta.z = (1.0F / 1.0F);
/*  95:    */    }
/*  96:    */    
/* 100:100 */    for (paramRay = 0; paramRay < paramInt; paramRay++)
/* 101:    */    {
/* 105:105 */      if (!handle(j, k, i, paramjM, paramSegmentController)) {
/* 106:106 */        return;
/* 107:    */      }
/* 108:    */      
/* 129:129 */      if (this.tMax.x < this.tMax.y) {
/* 130:130 */        if (this.tMax.x < this.tMax.z) {
/* 131:131 */          j += this.stepX;
/* 132:132 */          this.tMax.x += this.tDelta.x;
/* 133:    */        } else {
/* 134:134 */          i += this.stepZ;
/* 135:135 */          this.tMax.z += this.tDelta.z;
/* 136:    */        }
/* 137:    */      }
/* 138:138 */      else if (this.tMax.y < this.tMax.z) {
/* 139:139 */        k += this.stepY;
/* 140:140 */        this.tMax.y += this.tDelta.y;
/* 141:    */      } else {
/* 142:142 */        i += this.stepZ;
/* 143:143 */        this.tMax.z += this.tDelta.z;
/* 144:    */      }
/* 145:    */    }
/* 146:    */    
/* 147:147 */    if (SubsimplexRayCubesCovexCast.debug) {
/* 148:148 */      System.err.println("NO SEGMENT FOUND ON PATH: " + paramInt);
/* 149:    */    }
/* 150:    */  }
/* 151:    */  
/* 152:    */  private boolean handleDebug(int paramInt1, int paramInt2, int paramInt3) {
/* 153:153 */    System.err.println("TESTING: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + "; " + this.stepX + ", " + this.stepY + ", " + this.stepZ + "; MAX " + this.tMax + "; DELTA " + this.tDelta + "; " + this.ray.position + " -> " + this.ray.direction);
/* 154:154 */    return false;
/* 155:    */  }
/* 156:    */  
/* 158:    */  private boolean handle(int paramInt1, int paramInt2, int paramInt3, jM paramjM, SegmentController paramSegmentController)
/* 159:    */  {
/* 160:160 */    paramInt1 <<= 4;
/* 161:161 */    paramInt2 <<= 4;
/* 162:162 */    paramInt3 <<= 4;
/* 163:    */    
/* 182:182 */    if (((paramInt1 = paramSegmentController.getSegmentBuffer().a(paramInt1, paramInt2, paramInt3)) != null) && (!paramInt1.g())) {
/* 183:183 */      if (SubsimplexRayCubesCovexCast.debug) {
/* 184:184 */        System.err.println("TRAVERSED TO " + paramInt1.a);
/* 185:    */      }
/* 186:186 */      return paramjM.handle(paramInt1);
/* 187:    */    }
/* 188:188 */    return true;
/* 189:    */  }
/* 190:    */  
/* 191:    */  private q getCellAt(Vector3f paramVector3f, q paramq) {
/* 192:192 */    paramq.b(FastMath.a(paramVector3f.x), FastMath.a(paramVector3f.y), FastMath.a(paramVector3f.z));
/* 193:    */    
/* 196:196 */    return paramq;
/* 197:    */  }
/* 198:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.RayTraceGridTraverser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */