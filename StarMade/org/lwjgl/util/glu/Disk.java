/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */
/*  71:    */public class Disk
/*  72:    */  extends Quadric
/*  73:    */{
/*  74:    */  public void draw(float innerRadius, float outerRadius, int slices, int loops)
/*  75:    */  {
/*  76: 76 */    if (this.normals != 100002) {
/*  77: 77 */      if (this.orientation == 100020) {
/*  78: 78 */        GL11.glNormal3f(0.0F, 0.0F, 1.0F);
/*  79:    */      }
/*  80:    */      else {
/*  81: 81 */        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/*  82:    */      }
/*  83:    */    }
/*  84:    */    
/*  85: 85 */    float da = 6.283186F / slices;
/*  86: 86 */    float dr = (outerRadius - innerRadius) / loops;
/*  87:    */    float dtc;
/*  88: 88 */    float r1; int l; switch (this.drawStyle)
/*  89:    */    {
/*  94:    */    case 100012: 
/*  95: 95 */      dtc = 2.0F * outerRadius;
/*  96:    */      
/*  97: 97 */      r1 = innerRadius;
/*  98:    */      
/*  99: 99 */      for (l = 0; l < loops;) {
/* 100:100 */        float r2 = r1 + dr;
/* 101:101 */        if (this.orientation == 100020)
/* 102:    */        {
/* 103:103 */          GL11.glBegin(8);
/* 104:104 */          for (int s = 0; s <= slices; s++) { float a;
/* 105:    */            float a;
/* 106:106 */            if (s == slices) {
/* 107:107 */              a = 0.0F;
/* 108:    */            } else
/* 109:109 */              a = s * da;
/* 110:110 */            float sa = sin(a);
/* 111:111 */            float ca = cos(a);
/* 112:112 */            TXTR_COORD(0.5F + sa * r2 / dtc, 0.5F + ca * r2 / dtc);
/* 113:113 */            GL11.glVertex2f(r2 * sa, r2 * ca);
/* 114:114 */            TXTR_COORD(0.5F + sa * r1 / dtc, 0.5F + ca * r1 / dtc);
/* 115:115 */            GL11.glVertex2f(r1 * sa, r1 * ca);
/* 116:    */          }
/* 117:117 */          GL11.glEnd();
/* 118:    */        }
/* 119:    */        else
/* 120:    */        {
/* 121:121 */          GL11.glBegin(8);
/* 122:122 */          for (int s = slices; s >= 0; s--) { float a;
/* 123:    */            float a;
/* 124:124 */            if (s == slices) {
/* 125:125 */              a = 0.0F;
/* 126:    */            } else
/* 127:127 */              a = s * da;
/* 128:128 */            float sa = sin(a);
/* 129:129 */            float ca = cos(a);
/* 130:130 */            TXTR_COORD(0.5F - sa * r2 / dtc, 0.5F + ca * r2 / dtc);
/* 131:131 */            GL11.glVertex2f(r2 * sa, r2 * ca);
/* 132:132 */            TXTR_COORD(0.5F - sa * r1 / dtc, 0.5F + ca * r1 / dtc);
/* 133:133 */            GL11.glVertex2f(r1 * sa, r1 * ca);
/* 134:    */          }
/* 135:135 */          GL11.glEnd();
/* 136:    */        }
/* 137:137 */        r1 = r2;l++;continue;
/* 138:    */        
/* 183:145 */        for (l = 0; l <= loops; l++) {
/* 184:146 */          float r = innerRadius + l * dr;
/* 185:147 */          GL11.glBegin(2);
/* 186:148 */          for (int s = 0; s < slices; s++) {
/* 187:149 */            float a = s * da;
/* 188:150 */            GL11.glVertex2f(r * sin(a), r * cos(a));
/* 189:    */          }
/* 190:152 */          GL11.glEnd();
/* 191:    */        }
/* 192:    */        
/* 193:155 */        for (s = 0; s < slices;) {
/* 194:156 */          float a = s * da;
/* 195:157 */          float x = sin(a);
/* 196:158 */          float y = cos(a);
/* 197:159 */          GL11.glBegin(3);
/* 198:160 */          for (l = 0; l <= loops; l++) {
/* 199:161 */            float r = innerRadius + l * dr;
/* 200:162 */            GL11.glVertex2f(r * x, r * y);
/* 201:    */          }
/* 202:164 */          GL11.glEnd();s++;continue;
/* 203:    */          
/* 209:171 */          GL11.glBegin(0);
/* 210:172 */          for (int s = 0; s < slices; s++) {
/* 211:173 */            float a = s * da;
/* 212:174 */            float x = sin(a);
/* 213:175 */            float y = cos(a);
/* 214:    */            
/* 215:177 */            for (int l = 0; l <= loops; l++) {
/* 216:178 */              float r = innerRadius * l * dr;
/* 217:179 */              GL11.glVertex2f(r * x, r * y);
/* 218:    */            }
/* 219:    */          }
/* 220:182 */          GL11.glEnd();
/* 221:183 */          break;
/* 222:    */          
/* 225:187 */          if (innerRadius != 0.0D)
/* 226:    */          {
/* 227:189 */            GL11.glBegin(2);
/* 228:190 */            for (float a = 0.0F; a < 6.283185482025147D; a += da) {
/* 229:191 */              float x = innerRadius * sin(a);
/* 230:192 */              float y = innerRadius * cos(a);
/* 231:193 */              GL11.glVertex2f(x, y);
/* 232:    */            }
/* 233:195 */            GL11.glEnd();
/* 234:    */          }
/* 235:    */          
/* 237:199 */          GL11.glBegin(2);
/* 238:200 */          for (float a = 0.0F; a < 6.283186F; a += da) {
/* 239:201 */            float x = outerRadius * sin(a);
/* 240:202 */            float y = outerRadius * cos(a);
/* 241:203 */            GL11.glVertex2f(x, y);
/* 242:    */          }
/* 243:205 */          GL11.glEnd();
/* 244:    */          
/* 245:207 */          break; return;
/* 246:    */        }
/* 247:    */      }
/* 248:    */    }
/* 249:    */    
/* 250:    */    int l;
/* 251:    */    int s;
/* 252:    */  }
/* 253:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Disk
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */