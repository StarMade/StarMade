/*   1:    */package org.newdawn.slick.geom;
/*   2:    */
/*   3:    */import org.newdawn.slick.util.FastTrig;
/*   4:    */
/*  19:    */public class Transform
/*  20:    */{
/*  21:    */  private float[] matrixPosition;
/*  22:    */  
/*  23:    */  public Transform()
/*  24:    */  {
/*  25: 25 */    this.matrixPosition = new float[] { 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F };
/*  26:    */  }
/*  27:    */  
/*  32:    */  public Transform(Transform other)
/*  33:    */  {
/*  34: 34 */    this.matrixPosition = new float[9];
/*  35: 35 */    for (int i = 0; i < 9; i++) {
/*  36: 36 */      this.matrixPosition[i] = other.matrixPosition[i];
/*  37:    */    }
/*  38:    */  }
/*  39:    */  
/*  45:    */  public Transform(Transform t1, Transform t2)
/*  46:    */  {
/*  47: 47 */    this(t1);
/*  48: 48 */    concatenate(t2);
/*  49:    */  }
/*  50:    */  
/*  56:    */  public Transform(float[] matrixPosition)
/*  57:    */  {
/*  58: 58 */    if (matrixPosition.length != 6) {
/*  59: 59 */      throw new RuntimeException("The parameter must be a float array of length 6.");
/*  60:    */    }
/*  61: 61 */    this.matrixPosition = new float[] { matrixPosition[0], matrixPosition[1], matrixPosition[2], matrixPosition[3], matrixPosition[4], matrixPosition[5], 0.0F, 0.0F, 1.0F };
/*  62:    */  }
/*  63:    */  
/*  75:    */  public Transform(float point00, float point01, float point02, float point10, float point11, float point12)
/*  76:    */  {
/*  77: 77 */    this.matrixPosition = new float[] { point00, point01, point02, point10, point11, point12, 0.0F, 0.0F, 1.0F };
/*  78:    */  }
/*  79:    */  
/*  92:    */  public void transform(float[] source, int sourceOffset, float[] destination, int destOffset, int numberOfPoints)
/*  93:    */  {
/*  94: 94 */    float[] result = source == destination ? new float[numberOfPoints * 2] : destination;
/*  95:    */    
/*  96: 96 */    for (int i = 0; i < numberOfPoints * 2; i += 2) {
/*  97: 97 */      for (int j = 0; j < 6; j += 3) {
/*  98: 98 */        result[(i + j / 3)] = (source[(i + sourceOffset)] * this.matrixPosition[j] + source[(i + sourceOffset + 1)] * this.matrixPosition[(j + 1)] + 1.0F * this.matrixPosition[(j + 2)]);
/*  99:    */      }
/* 100:    */    }
/* 101:    */    
/* 102:102 */    if (source == destination)
/* 103:    */    {
/* 104:104 */      for (int i = 0; i < numberOfPoints * 2; i += 2) {
/* 105:105 */        destination[(i + destOffset)] = result[i];
/* 106:106 */        destination[(i + destOffset + 1)] = result[(i + 1)];
/* 107:    */      }
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 116:    */  public Transform concatenate(Transform tx)
/* 117:    */  {
/* 118:118 */    float[] mp = new float[9];
/* 119:119 */    float n00 = this.matrixPosition[0] * tx.matrixPosition[0] + this.matrixPosition[1] * tx.matrixPosition[3];
/* 120:120 */    float n01 = this.matrixPosition[0] * tx.matrixPosition[1] + this.matrixPosition[1] * tx.matrixPosition[4];
/* 121:121 */    float n02 = this.matrixPosition[0] * tx.matrixPosition[2] + this.matrixPosition[1] * tx.matrixPosition[5] + this.matrixPosition[2];
/* 122:122 */    float n10 = this.matrixPosition[3] * tx.matrixPosition[0] + this.matrixPosition[4] * tx.matrixPosition[3];
/* 123:123 */    float n11 = this.matrixPosition[3] * tx.matrixPosition[1] + this.matrixPosition[4] * tx.matrixPosition[4];
/* 124:124 */    float n12 = this.matrixPosition[3] * tx.matrixPosition[2] + this.matrixPosition[4] * tx.matrixPosition[5] + this.matrixPosition[5];
/* 125:125 */    mp[0] = n00;
/* 126:126 */    mp[1] = n01;
/* 127:127 */    mp[2] = n02;
/* 128:128 */    mp[3] = n10;
/* 129:129 */    mp[4] = n11;
/* 130:130 */    mp[5] = n12;
/* 131:    */    
/* 139:139 */    this.matrixPosition = mp;
/* 140:140 */    return this;
/* 141:    */  }
/* 142:    */  
/* 148:    */  public String toString()
/* 149:    */  {
/* 150:150 */    String result = "Transform[[" + this.matrixPosition[0] + "," + this.matrixPosition[1] + "," + this.matrixPosition[2] + "][" + this.matrixPosition[3] + "," + this.matrixPosition[4] + "," + this.matrixPosition[5] + "][" + this.matrixPosition[6] + "," + this.matrixPosition[7] + "," + this.matrixPosition[8] + "]]";
/* 151:    */    
/* 154:154 */    return result.toString();
/* 155:    */  }
/* 156:    */  
/* 161:    */  public float[] getMatrixPosition()
/* 162:    */  {
/* 163:163 */    return this.matrixPosition;
/* 164:    */  }
/* 165:    */  
/* 171:    */  public static Transform createRotateTransform(float angle)
/* 172:    */  {
/* 173:173 */    return new Transform((float)FastTrig.cos(angle), -(float)FastTrig.sin(angle), 0.0F, (float)FastTrig.sin(angle), (float)FastTrig.cos(angle), 0.0F);
/* 174:    */  }
/* 175:    */  
/* 183:    */  public static Transform createRotateTransform(float angle, float x, float y)
/* 184:    */  {
/* 185:185 */    Transform temp = createRotateTransform(angle);
/* 186:186 */    float sinAngle = temp.matrixPosition[3];
/* 187:187 */    float oneMinusCosAngle = 1.0F - temp.matrixPosition[4];
/* 188:188 */    temp.matrixPosition[2] = (x * oneMinusCosAngle + y * sinAngle);
/* 189:189 */    temp.matrixPosition[5] = (y * oneMinusCosAngle - x * sinAngle);
/* 190:    */    
/* 191:191 */    return temp;
/* 192:    */  }
/* 193:    */  
/* 200:    */  public static Transform createTranslateTransform(float xOffset, float yOffset)
/* 201:    */  {
/* 202:202 */    return new Transform(1.0F, 0.0F, xOffset, 0.0F, 1.0F, yOffset);
/* 203:    */  }
/* 204:    */  
/* 211:    */  public static Transform createScaleTransform(float xScale, float yScale)
/* 212:    */  {
/* 213:213 */    return new Transform(xScale, 0.0F, 0.0F, 0.0F, yScale, 0.0F);
/* 214:    */  }
/* 215:    */  
/* 221:    */  public Vector2f transform(Vector2f pt)
/* 222:    */  {
/* 223:223 */    float[] in = { pt.x, pt.y };
/* 224:224 */    float[] out = new float[2];
/* 225:    */    
/* 226:226 */    transform(in, 0, out, 0, 1);
/* 227:    */    
/* 228:228 */    return new Vector2f(out[0], out[1]);
/* 229:    */  }
/* 230:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.geom.Transform
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */