/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */
/*  73:    */public class Sphere
/*  74:    */  extends Quadric
/*  75:    */{
/*  76:    */  public void draw(float radius, int slices, int stacks)
/*  77:    */  {
/*  78: 78 */    boolean normals = this.normals != 100002;
/*  79:    */    float nsign;
/*  80: 80 */    float nsign; if (this.orientation == 100021) {
/*  81: 81 */      nsign = -1.0F;
/*  82:    */    } else {
/*  83: 83 */      nsign = 1.0F;
/*  84:    */    }
/*  85:    */    
/*  86: 86 */    float drho = 3.141593F / stacks;
/*  87: 87 */    float dtheta = 6.283186F / slices;
/*  88:    */    int i;
/*  89: 89 */    int j; if (this.drawStyle == 100012) {
/*  90: 90 */      if (!this.textureFlag)
/*  91:    */      {
/*  92: 92 */        GL11.glBegin(6);
/*  93: 93 */        GL11.glNormal3f(0.0F, 0.0F, 1.0F);
/*  94: 94 */        GL11.glVertex3f(0.0F, 0.0F, nsign * radius);
/*  95: 95 */        for (int j = 0; j <= slices; j++) {
/*  96: 96 */          float theta = j == slices ? 0.0F : j * dtheta;
/*  97: 97 */          float x = -sin(theta) * sin(drho);
/*  98: 98 */          float y = cos(theta) * sin(drho);
/*  99: 99 */          float z = nsign * cos(drho);
/* 100:100 */          if (normals) {
/* 101:101 */            GL11.glNormal3f(x * nsign, y * nsign, z * nsign);
/* 102:    */          }
/* 103:103 */          GL11.glVertex3f(x * radius, y * radius, z * radius);
/* 104:    */        }
/* 105:105 */        GL11.glEnd();
/* 106:    */      }
/* 107:    */      
/* 108:108 */      float ds = 1.0F / slices;
/* 109:109 */      float dt = 1.0F / stacks;
/* 110:110 */      float t = 1.0F;
/* 111:111 */      int imax; int imin; int imax; if (this.textureFlag) {
/* 112:112 */        int imin = 0;
/* 113:113 */        imax = stacks;
/* 114:    */      } else {
/* 115:115 */        imin = 1;
/* 116:116 */        imax = stacks - 1;
/* 117:    */      }
/* 118:    */      
/* 120:120 */      for (int i = imin; i < imax; i++) {
/* 121:121 */        float rho = i * drho;
/* 122:122 */        GL11.glBegin(8);
/* 123:123 */        float s = 0.0F;
/* 124:124 */        for (int j = 0; j <= slices; j++) {
/* 125:125 */          float theta = j == slices ? 0.0F : j * dtheta;
/* 126:126 */          float x = -sin(theta) * sin(rho);
/* 127:127 */          float y = cos(theta) * sin(rho);
/* 128:128 */          float z = nsign * cos(rho);
/* 129:129 */          if (normals) {
/* 130:130 */            GL11.glNormal3f(x * nsign, y * nsign, z * nsign);
/* 131:    */          }
/* 132:132 */          TXTR_COORD(s, t);
/* 133:133 */          GL11.glVertex3f(x * radius, y * radius, z * radius);
/* 134:134 */          x = -sin(theta) * sin(rho + drho);
/* 135:135 */          y = cos(theta) * sin(rho + drho);
/* 136:136 */          z = nsign * cos(rho + drho);
/* 137:137 */          if (normals) {
/* 138:138 */            GL11.glNormal3f(x * nsign, y * nsign, z * nsign);
/* 139:    */          }
/* 140:140 */          TXTR_COORD(s, t - dt);
/* 141:141 */          s += ds;
/* 142:142 */          GL11.glVertex3f(x * radius, y * radius, z * radius);
/* 143:    */        }
/* 144:144 */        GL11.glEnd();
/* 145:145 */        t -= dt;
/* 146:    */      }
/* 147:    */      
/* 148:148 */      if (!this.textureFlag)
/* 149:    */      {
/* 150:150 */        GL11.glBegin(6);
/* 151:151 */        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/* 152:152 */        GL11.glVertex3f(0.0F, 0.0F, -radius * nsign);
/* 153:153 */        float rho = 3.141593F - drho;
/* 154:154 */        float s = 1.0F;
/* 155:155 */        for (int j = slices; j >= 0; j--) {
/* 156:156 */          float theta = j == slices ? 0.0F : j * dtheta;
/* 157:157 */          float x = -sin(theta) * sin(rho);
/* 158:158 */          float y = cos(theta) * sin(rho);
/* 159:159 */          float z = nsign * cos(rho);
/* 160:160 */          if (normals)
/* 161:161 */            GL11.glNormal3f(x * nsign, y * nsign, z * nsign);
/* 162:162 */          s -= ds;
/* 163:163 */          GL11.glVertex3f(x * radius, y * radius, z * radius);
/* 164:    */        }
/* 165:165 */        GL11.glEnd();
/* 166:    */      }
/* 167:167 */    } else if ((this.drawStyle == 100011) || (this.drawStyle == 100013))
/* 168:    */    {
/* 171:171 */      for (i = 1; 
/* 172:172 */          i < stacks; 
/* 173:173 */          i++) {
/* 174:174 */        float rho = i * drho;
/* 175:175 */        GL11.glBegin(2);
/* 176:176 */        for (int j = 0; j < slices; j++) {
/* 177:177 */          float theta = j * dtheta;
/* 178:178 */          float x = cos(theta) * sin(rho);
/* 179:179 */          float y = sin(theta) * sin(rho);
/* 180:180 */          float z = cos(rho);
/* 181:181 */          if (normals)
/* 182:182 */            GL11.glNormal3f(x * nsign, y * nsign, z * nsign);
/* 183:183 */          GL11.glVertex3f(x * radius, y * radius, z * radius);
/* 184:    */        }
/* 185:185 */        GL11.glEnd();
/* 186:    */      }
/* 187:    */      
/* 188:188 */      for (j = 0; j < slices;) {
/* 189:189 */        float theta = j * dtheta;
/* 190:190 */        GL11.glBegin(3);
/* 191:191 */        for (i = 0; i <= stacks; i++) {
/* 192:192 */          float rho = i * drho;
/* 193:193 */          float x = cos(theta) * sin(rho);
/* 194:194 */          float y = sin(theta) * sin(rho);
/* 195:195 */          float z = cos(rho);
/* 196:196 */          if (normals)
/* 197:197 */            GL11.glNormal3f(x * nsign, y * nsign, z * nsign);
/* 198:198 */          GL11.glVertex3f(x * radius, y * radius, z * radius);
/* 199:    */        }
/* 200:200 */        GL11.glEnd();j++;continue;
/* 201:    */        
/* 214:202 */        if (this.drawStyle == 100010)
/* 215:    */        {
/* 216:204 */          GL11.glBegin(0);
/* 217:205 */          if (normals)
/* 218:206 */            GL11.glNormal3f(0.0F, 0.0F, nsign);
/* 219:207 */          GL11.glVertex3f(0.0F, 0.0F, radius);
/* 220:208 */          if (normals)
/* 221:209 */            GL11.glNormal3f(0.0F, 0.0F, -nsign);
/* 222:210 */          GL11.glVertex3f(0.0F, 0.0F, -radius);
/* 223:    */          
/* 225:213 */          for (int i = 1; i < stacks - 1; i++) {
/* 226:214 */            float rho = i * drho;
/* 227:215 */            for (int j = 0; j < slices; j++) {
/* 228:216 */              float theta = j * dtheta;
/* 229:217 */              float x = cos(theta) * sin(rho);
/* 230:218 */              float y = sin(theta) * sin(rho);
/* 231:219 */              float z = cos(rho);
/* 232:220 */              if (normals)
/* 233:221 */                GL11.glNormal3f(x * nsign, y * nsign, z * nsign);
/* 234:222 */              GL11.glVertex3f(x * radius, y * radius, z * radius);
/* 235:    */            }
/* 236:    */          }
/* 237:225 */          GL11.glEnd();
/* 238:    */        }
/* 239:    */      }
/* 240:    */    }
/* 241:    */  }
/* 242:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Sphere
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */