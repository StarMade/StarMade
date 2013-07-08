/*   1:    */package com.eaio.uuid;
/*   2:    */
/*   3:    */import com.eaio.util.lang.Hex;
/*   4:    */import java.io.Externalizable;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.ObjectInput;
/*   7:    */import java.io.ObjectOutput;
/*   8:    */import org.omg.CORBA.portable.IDLEntity;
/*   9:    */
/*  74:    */public class UUID
/*  75:    */  implements Comparable<UUID>, Externalizable, Cloneable, IDLEntity
/*  76:    */{
/*  77:    */  static final long serialVersionUID = 7435962790062944603L;
/*  78:    */  public long time;
/*  79:    */  public long clockSeqAndNode;
/*  80:    */  
/*  81:    */  public UUID()
/*  82:    */  {
/*  83: 83 */    this(UUIDGen.newTime(), UUIDGen.getClockSeqAndNode());
/*  84:    */  }
/*  85:    */  
/*  91:    */  public UUID(long time, long clockSeqAndNode)
/*  92:    */  {
/*  93: 93 */    this.time = time;
/*  94: 94 */    this.clockSeqAndNode = clockSeqAndNode;
/*  95:    */  }
/*  96:    */  
/* 101:    */  public UUID(UUID u)
/* 102:    */  {
/* 103:103 */    this(u.time, u.clockSeqAndNode);
/* 104:    */  }
/* 105:    */  
/* 113:    */  public UUID(CharSequence s)
/* 114:    */  {
/* 115:115 */    this(Hex.parseLong(s.subSequence(0, 18)), Hex.parseLong(s.subSequence(19, 36)));
/* 116:    */  }
/* 117:    */  
/* 129:    */  public int compareTo(UUID t)
/* 130:    */  {
/* 131:131 */    if (this == t) {
/* 132:132 */      return 0;
/* 133:    */    }
/* 134:134 */    if (this.time > t.time) {
/* 135:135 */      return 1;
/* 136:    */    }
/* 137:137 */    if (this.time < t.time) {
/* 138:138 */      return -1;
/* 139:    */    }
/* 140:140 */    if (this.clockSeqAndNode > t.clockSeqAndNode) {
/* 141:141 */      return 1;
/* 142:    */    }
/* 143:143 */    if (this.clockSeqAndNode < t.clockSeqAndNode) {
/* 144:144 */      return -1;
/* 145:    */    }
/* 146:146 */    return 0;
/* 147:    */  }
/* 148:    */  
/* 150:    */  public void writeExternal(ObjectOutput out)
/* 151:    */    throws IOException
/* 152:    */  {
/* 153:153 */    out.writeLong(this.time);
/* 154:154 */    out.writeLong(this.clockSeqAndNode);
/* 155:    */  }
/* 156:    */  
/* 158:    */  public void readExternal(ObjectInput in)
/* 159:    */    throws IOException
/* 160:    */  {
/* 161:161 */    this.time = in.readLong();
/* 162:162 */    this.clockSeqAndNode = in.readLong();
/* 163:    */  }
/* 164:    */  
/* 172:    */  public final String toString()
/* 173:    */  {
/* 174:174 */    return toAppendable(null).toString();
/* 175:    */  }
/* 176:    */  
/* 184:    */  public StringBuffer toStringBuffer(StringBuffer in)
/* 185:    */  {
/* 186:186 */    StringBuffer out = in;
/* 187:187 */    if (out == null) {
/* 188:188 */      out = new StringBuffer(36);
/* 189:    */    }
/* 190:    */    else {
/* 191:191 */      out.ensureCapacity(out.length() + 36);
/* 192:    */    }
/* 193:193 */    return (StringBuffer)toAppendable(out);
/* 194:    */  }
/* 195:    */  
/* 206:    */  public Appendable toAppendable(Appendable a)
/* 207:    */  {
/* 208:208 */    Appendable out = a;
/* 209:209 */    if (out == null) {
/* 210:210 */      out = new StringBuilder(36);
/* 211:    */    }
/* 212:    */    try {
/* 213:213 */      Hex.append(out, (int)(this.time >> 32)).append('-');
/* 214:214 */      Hex.append(out, (short)(int)(this.time >> 16)).append('-');
/* 215:215 */      Hex.append(out, (short)(int)this.time).append('-');
/* 216:216 */      Hex.append(out, (short)(int)(this.clockSeqAndNode >> 48)).append('-');
/* 217:217 */      Hex.append(out, this.clockSeqAndNode, 12);
/* 218:    */    }
/* 219:    */    catch (IOException ex) {}
/* 220:    */    
/* 222:222 */    return out;
/* 223:    */  }
/* 224:    */  
/* 233:    */  public int hashCode()
/* 234:    */  {
/* 235:235 */    return (int)(this.time >> 32 ^ this.time ^ this.clockSeqAndNode >> 32 ^ this.clockSeqAndNode);
/* 236:    */  }
/* 237:    */  
/* 242:    */  public Object clone()
/* 243:    */  {
/* 244:    */    try
/* 245:    */    {
/* 246:246 */      return super.clone();
/* 247:    */    }
/* 248:    */    catch (CloneNotSupportedException ex) {}
/* 249:    */    
/* 250:250 */    return null;
/* 251:    */  }
/* 252:    */  
/* 258:    */  public final long getTime()
/* 259:    */  {
/* 260:260 */    return this.time;
/* 261:    */  }
/* 262:    */  
/* 267:    */  public final long getClockSeqAndNode()
/* 268:    */  {
/* 269:269 */    return this.clockSeqAndNode;
/* 270:    */  }
/* 271:    */  
/* 280:    */  public boolean equals(Object obj)
/* 281:    */  {
/* 282:282 */    if (!(obj instanceof UUID)) {
/* 283:283 */      return false;
/* 284:    */    }
/* 285:285 */    return compareTo((UUID)obj) == 0;
/* 286:    */  }
/* 287:    */  
/* 297:    */  public static UUID nilUUID()
/* 298:    */  {
/* 299:299 */    return new UUID(0L, 0L);
/* 300:    */  }
/* 301:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.UUID
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */