/*   1:    */package org.lwjgl.util.glu.tessellation;
/*   2:    */
/* 119:    */class TessMono
/* 120:    */{
/* 121:    */  static boolean __gl_meshTessellateMonoRegion(GLUface face)
/* 122:    */  {
/* 123:123 */    GLUhalfEdge up = face.anEdge;
/* 124:124 */    assert ((up.Lnext != up) && (up.Lnext.Lnext != up));
/* 125:    */    
/* 126:126 */    while (Geom.VertLeq(up.Sym.Org, up.Org)) { up = up.Onext.Sym;
/* 127:    */    }
/* 128:128 */    while (Geom.VertLeq(up.Org, up.Sym.Org)) { up = up.Lnext;
/* 129:    */    }
/* 130:130 */    GLUhalfEdge lo = up.Onext.Sym;
/* 131:    */    
/* 132:132 */    while (up.Lnext != lo) {
/* 133:133 */      if (Geom.VertLeq(up.Sym.Org, lo.Org))
/* 134:    */      {
/* 138:138 */        while ((lo.Lnext != up) && ((Geom.EdgeGoesLeft(lo.Lnext)) || (Geom.EdgeSign(lo.Org, lo.Sym.Org, lo.Lnext.Sym.Org) <= 0.0D)))
/* 139:    */        {
/* 140:140 */          GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(lo.Lnext, lo);
/* 141:141 */          if (tempHalfEdge == null) return false;
/* 142:142 */          lo = tempHalfEdge.Sym;
/* 143:    */        }
/* 144:144 */        lo = lo.Onext.Sym;
/* 145:    */      }
/* 146:    */      else {
/* 147:147 */        while ((lo.Lnext != up) && ((Geom.EdgeGoesRight(up.Onext.Sym)) || (Geom.EdgeSign(up.Sym.Org, up.Org, up.Onext.Sym.Org) >= 0.0D)))
/* 148:    */        {
/* 149:149 */          GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(up, up.Onext.Sym);
/* 150:150 */          if (tempHalfEdge == null) return false;
/* 151:151 */          up = tempHalfEdge.Sym;
/* 152:    */        }
/* 153:153 */        up = up.Lnext;
/* 154:    */      }
/* 155:    */    }
/* 156:    */    
/* 160:160 */    assert (lo.Lnext != up);
/* 161:161 */    while (lo.Lnext.Lnext != up) {
/* 162:162 */      GLUhalfEdge tempHalfEdge = Mesh.__gl_meshConnect(lo.Lnext, lo);
/* 163:163 */      if (tempHalfEdge == null) return false;
/* 164:164 */      lo = tempHalfEdge.Sym;
/* 165:    */    }
/* 166:    */    
/* 167:167 */    return true;
/* 168:    */  }
/* 169:    */  
/* 173:    */  public static boolean __gl_meshTessellateInterior(GLUmesh mesh)
/* 174:    */  {
/* 175:    */    GLUface next;
/* 176:    */    
/* 179:179 */    for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = next)
/* 180:    */    {
/* 181:181 */      next = f.next;
/* 182:182 */      if ((f.inside) && 
/* 183:183 */        (!__gl_meshTessellateMonoRegion(f))) { return false;
/* 184:    */      }
/* 185:    */    }
/* 186:    */    
/* 187:187 */    return true;
/* 188:    */  }
/* 189:    */  
/* 194:    */  public static void __gl_meshDiscardExterior(GLUmesh mesh)
/* 195:    */  {
/* 196:    */    GLUface next;
/* 197:    */    
/* 200:200 */    for (GLUface f = mesh.fHead.next; f != mesh.fHead; f = next)
/* 201:    */    {
/* 202:202 */      next = f.next;
/* 203:203 */      if (!f.inside) {
/* 204:204 */        Mesh.__gl_meshZapFace(f);
/* 205:    */      }
/* 206:    */    }
/* 207:    */  }
/* 208:    */  
/* 214:    */  public static boolean __gl_meshSetWindingNumber(GLUmesh mesh, int value, boolean keepOnlyBoundary)
/* 215:    */  {
/* 216:    */    GLUhalfEdge eNext;
/* 217:    */    
/* 222:222 */    for (GLUhalfEdge e = mesh.eHead.next; e != mesh.eHead; e = eNext) {
/* 223:223 */      eNext = e.next;
/* 224:224 */      if (e.Sym.Lface.inside != e.Lface.inside)
/* 225:    */      {
/* 227:227 */        e.winding = (e.Lface.inside ? value : -value);
/* 230:    */      }
/* 231:231 */      else if (!keepOnlyBoundary) {
/* 232:232 */        e.winding = 0;
/* 233:    */      }
/* 234:234 */      else if (!Mesh.__gl_meshDelete(e)) { return false;
/* 235:    */      }
/* 236:    */    }
/* 237:    */    
/* 238:238 */    return true;
/* 239:    */  }
/* 240:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.tessellation.TessMono
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */