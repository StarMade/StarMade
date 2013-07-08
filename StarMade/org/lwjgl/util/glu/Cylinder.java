/*   1:    */package org.lwjgl.util.glu;
/*   2:    */
/*   3:    */import org.lwjgl.opengl.GL11;
/*   4:    */
/*  77:    */public class Cylinder
/*  78:    */  extends Quadric
/*  79:    */{
/*  80:    */  public void draw(float baseRadius, float topRadius, float height, int slices, int stacks)
/*  81:    */  {
/*  82:    */    float nsign;
/*  83:    */    float nsign;
/*  84: 84 */    if (this.orientation == 100021) {
/*  85: 85 */      nsign = -1.0F;
/*  86:    */    } else {
/*  87: 87 */      nsign = 1.0F;
/*  88:    */    }
/*  89:    */    
/*  90: 90 */    float da = 6.283186F / slices;
/*  91: 91 */    float dr = (topRadius - baseRadius) / stacks;
/*  92: 92 */    float dz = height / stacks;
/*  93: 93 */    float nz = (baseRadius - topRadius) / height;
/*  94:    */    
/*  96: 96 */    if (this.drawStyle == 100010) {
/*  97: 97 */      GL11.glBegin(0);
/*  98: 98 */      for (int i = 0; i < slices; i++) {
/*  99: 99 */        float x = cos(i * da);
/* 100:100 */        float y = sin(i * da);
/* 101:101 */        normal3f(x * nsign, y * nsign, nz * nsign);
/* 102:    */        
/* 103:103 */        float z = 0.0F;
/* 104:104 */        float r = baseRadius;
/* 105:105 */        for (int j = 0; j <= stacks; j++) {
/* 106:106 */          GL11.glVertex3f(x * r, y * r, z);
/* 107:107 */          z += dz;
/* 108:108 */          r += dr;
/* 109:    */        }
/* 110:    */      }
/* 111:111 */      GL11.glEnd();
/* 112:112 */    } else if ((this.drawStyle == 100011) || (this.drawStyle == 100013))
/* 113:    */    {
/* 114:114 */      if (this.drawStyle == 100011) {
/* 115:115 */        float z = 0.0F;
/* 116:116 */        float r = baseRadius;
/* 117:117 */        for (int j = 0; j <= stacks; j++) {
/* 118:118 */          GL11.glBegin(2);
/* 119:119 */          for (int i = 0; i < slices; i++) {
/* 120:120 */            float x = cos(i * da);
/* 121:121 */            float y = sin(i * da);
/* 122:122 */            normal3f(x * nsign, y * nsign, nz * nsign);
/* 123:123 */            GL11.glVertex3f(x * r, y * r, z);
/* 124:    */          }
/* 125:125 */          GL11.glEnd();
/* 126:126 */          z += dz;
/* 127:127 */          r += dr;
/* 128:    */        }
/* 129:    */      }
/* 130:    */      
/* 131:131 */      if (baseRadius != 0.0D) {
/* 132:132 */        GL11.glBegin(2);
/* 133:133 */        for (int i = 0; i < slices; i++) {
/* 134:134 */          float x = cos(i * da);
/* 135:135 */          float y = sin(i * da);
/* 136:136 */          normal3f(x * nsign, y * nsign, nz * nsign);
/* 137:137 */          GL11.glVertex3f(x * baseRadius, y * baseRadius, 0.0F);
/* 138:    */        }
/* 139:139 */        GL11.glEnd();
/* 140:140 */        GL11.glBegin(2);
/* 141:141 */        for (i = 0; i < slices; i++) {
/* 142:142 */          float x = cos(i * da);
/* 143:143 */          float y = sin(i * da);
/* 144:144 */          normal3f(x * nsign, y * nsign, nz * nsign);
/* 145:145 */          GL11.glVertex3f(x * topRadius, y * topRadius, height);
/* 146:    */        }
/* 147:147 */        GL11.glEnd();
/* 148:    */      }
/* 149:    */      
/* 151:151 */      GL11.glBegin(1);
/* 152:152 */      for (int i = 0; i < slices; i++) {
/* 153:153 */        float x = cos(i * da);
/* 154:154 */        float y = sin(i * da);
/* 155:155 */        normal3f(x * nsign, y * nsign, nz * nsign);
/* 156:156 */        GL11.glVertex3f(x * baseRadius, y * baseRadius, 0.0F);
/* 157:157 */        GL11.glVertex3f(x * topRadius, y * topRadius, height);
/* 158:    */      }
/* 159:159 */      GL11.glEnd();
/* 160:160 */    } else if (this.drawStyle == 100012) {
/* 161:161 */      float ds = 1.0F / slices;
/* 162:162 */      float dt = 1.0F / stacks;
/* 163:163 */      float t = 0.0F;
/* 164:164 */      float z = 0.0F;
/* 165:165 */      float r = baseRadius;
/* 166:166 */      for (int j = 0; j < stacks; j++) {
/* 167:167 */        float s = 0.0F;
/* 168:168 */        GL11.glBegin(8);
/* 169:169 */        for (int i = 0; i <= slices; i++) { float y;
/* 170:170 */          float x; float y; if (i == slices) {
/* 171:171 */            float x = sin(0.0F);
/* 172:172 */            y = cos(0.0F);
/* 173:    */          } else {
/* 174:174 */            x = sin(i * da);
/* 175:175 */            y = cos(i * da);
/* 176:    */          }
/* 177:177 */          if (nsign == 1.0F) {
/* 178:178 */            normal3f(x * nsign, y * nsign, nz * nsign);
/* 179:179 */            TXTR_COORD(s, t);
/* 180:180 */            GL11.glVertex3f(x * r, y * r, z);
/* 181:181 */            normal3f(x * nsign, y * nsign, nz * nsign);
/* 182:182 */            TXTR_COORD(s, t + dt);
/* 183:183 */            GL11.glVertex3f(x * (r + dr), y * (r + dr), z + dz);
/* 184:    */          } else {
/* 185:185 */            normal3f(x * nsign, y * nsign, nz * nsign);
/* 186:186 */            TXTR_COORD(s, t);
/* 187:187 */            GL11.glVertex3f(x * r, y * r, z);
/* 188:188 */            normal3f(x * nsign, y * nsign, nz * nsign);
/* 189:189 */            TXTR_COORD(s, t + dt);
/* 190:190 */            GL11.glVertex3f(x * (r + dr), y * (r + dr), z + dz);
/* 191:    */          }
/* 192:192 */          s += ds;
/* 193:    */        }
/* 194:194 */        GL11.glEnd();
/* 195:195 */        r += dr;
/* 196:196 */        t += dt;
/* 197:197 */        z += dz;
/* 198:    */      }
/* 199:    */    }
/* 200:    */  }
/* 201:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.Cylinder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */