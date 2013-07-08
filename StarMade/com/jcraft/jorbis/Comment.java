/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */import com.jcraft.jogg.Packet;
/*   5:    */
/*  32:    */public class Comment
/*  33:    */{
/*  34: 34 */  private static byte[] _vorbis = "vorbis".getBytes();
/*  35: 35 */  private static byte[] _vendor = "Xiphophorus libVorbis I 20000508".getBytes();
/*  36:    */  
/*  37:    */  private static final int OV_EIMPL = -130;
/*  38:    */  
/*  39:    */  public byte[][] user_comments;
/*  40:    */  public int[] comment_lengths;
/*  41:    */  public int comments;
/*  42:    */  public byte[] vendor;
/*  43:    */  
/*  44:    */  public void init()
/*  45:    */  {
/*  46: 46 */    this.user_comments = ((byte[][])null);
/*  47: 47 */    this.comments = 0;
/*  48: 48 */    this.vendor = null;
/*  49:    */  }
/*  50:    */  
/*  51:    */  public void add(String comment) {
/*  52: 52 */    add(comment.getBytes());
/*  53:    */  }
/*  54:    */  
/*  55:    */  private void add(byte[] comment) {
/*  56: 56 */    byte[][] foo = new byte[this.comments + 2][];
/*  57: 57 */    if (this.user_comments != null) {
/*  58: 58 */      System.arraycopy(this.user_comments, 0, foo, 0, this.comments);
/*  59:    */    }
/*  60: 60 */    this.user_comments = foo;
/*  61:    */    
/*  62: 62 */    int[] goo = new int[this.comments + 2];
/*  63: 63 */    if (this.comment_lengths != null) {
/*  64: 64 */      System.arraycopy(this.comment_lengths, 0, goo, 0, this.comments);
/*  65:    */    }
/*  66: 66 */    this.comment_lengths = goo;
/*  67:    */    
/*  68: 68 */    byte[] bar = new byte[comment.length + 1];
/*  69: 69 */    System.arraycopy(comment, 0, bar, 0, comment.length);
/*  70: 70 */    this.user_comments[this.comments] = bar;
/*  71: 71 */    this.comment_lengths[this.comments] = comment.length;
/*  72: 72 */    this.comments += 1;
/*  73: 73 */    this.user_comments[this.comments] = null;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void add_tag(String tag, String contents) {
/*  77: 77 */    if (contents == null)
/*  78: 78 */      contents = "";
/*  79: 79 */    add(tag + "=" + contents);
/*  80:    */  }
/*  81:    */  
/*  82:    */  static boolean tagcompare(byte[] s1, byte[] s2, int n) {
/*  83: 83 */    int c = 0;
/*  84:    */    
/*  85: 85 */    while (c < n) {
/*  86: 86 */      byte u1 = s1[c];
/*  87: 87 */      byte u2 = s2[c];
/*  88: 88 */      if ((90 >= u1) && (u1 >= 65))
/*  89: 89 */        u1 = (byte)(u1 - 65 + 97);
/*  90: 90 */      if ((90 >= u2) && (u2 >= 65))
/*  91: 91 */        u2 = (byte)(u2 - 65 + 97);
/*  92: 92 */      if (u1 != u2) {
/*  93: 93 */        return false;
/*  94:    */      }
/*  95: 95 */      c++;
/*  96:    */    }
/*  97: 97 */    return true;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public String query(String tag) {
/* 101:101 */    return query(tag, 0);
/* 102:    */  }
/* 103:    */  
/* 104:    */  public String query(String tag, int count) {
/* 105:105 */    int foo = query(tag.getBytes(), count);
/* 106:106 */    if (foo == -1)
/* 107:107 */      return null;
/* 108:108 */    byte[] comment = this.user_comments[foo];
/* 109:109 */    for (int i = 0; i < this.comment_lengths[foo]; i++) {
/* 110:110 */      if (comment[i] == 61) {
/* 111:111 */        return new String(comment, i + 1, this.comment_lengths[foo] - (i + 1));
/* 112:    */      }
/* 113:    */    }
/* 114:114 */    return null;
/* 115:    */  }
/* 116:    */  
/* 117:    */  private int query(byte[] tag, int count) {
/* 118:118 */    int i = 0;
/* 119:119 */    int found = 0;
/* 120:120 */    int fulltaglen = tag.length + 1;
/* 121:121 */    byte[] fulltag = new byte[fulltaglen];
/* 122:122 */    System.arraycopy(tag, 0, fulltag, 0, tag.length);
/* 123:123 */    fulltag[tag.length] = 61;
/* 124:    */    
/* 125:125 */    for (i = 0; i < this.comments; i++) {
/* 126:126 */      if (tagcompare(this.user_comments[i], fulltag, fulltaglen)) {
/* 127:127 */        if (count == found)
/* 128:    */        {
/* 130:130 */          return i;
/* 131:    */        }
/* 132:    */        
/* 133:133 */        found++;
/* 134:    */      }
/* 135:    */    }
/* 136:    */    
/* 137:137 */    return -1;
/* 138:    */  }
/* 139:    */  
/* 140:    */  int unpack(Buffer opb) {
/* 141:141 */    int vendorlen = opb.read(32);
/* 142:142 */    if (vendorlen < 0) {
/* 143:143 */      clear();
/* 144:144 */      return -1;
/* 145:    */    }
/* 146:146 */    this.vendor = new byte[vendorlen + 1];
/* 147:147 */    opb.read(this.vendor, vendorlen);
/* 148:148 */    this.comments = opb.read(32);
/* 149:149 */    if (this.comments < 0) {
/* 150:150 */      clear();
/* 151:151 */      return -1;
/* 152:    */    }
/* 153:153 */    this.user_comments = new byte[this.comments + 1][];
/* 154:154 */    this.comment_lengths = new int[this.comments + 1];
/* 155:    */    
/* 156:156 */    for (int i = 0; i < this.comments; i++) {
/* 157:157 */      int len = opb.read(32);
/* 158:158 */      if (len < 0) {
/* 159:159 */        clear();
/* 160:160 */        return -1;
/* 161:    */      }
/* 162:162 */      this.comment_lengths[i] = len;
/* 163:163 */      this.user_comments[i] = new byte[len + 1];
/* 164:164 */      opb.read(this.user_comments[i], len);
/* 165:    */    }
/* 166:166 */    if (opb.read(1) != 1) {
/* 167:167 */      clear();
/* 168:168 */      return -1;
/* 169:    */    }
/* 170:    */    
/* 171:171 */    return 0;
/* 172:    */  }
/* 173:    */  
/* 174:    */  int pack(Buffer opb)
/* 175:    */  {
/* 176:176 */    opb.write(3, 8);
/* 177:177 */    opb.write(_vorbis);
/* 178:    */    
/* 180:180 */    opb.write(_vendor.length, 32);
/* 181:181 */    opb.write(_vendor);
/* 182:    */    
/* 184:184 */    opb.write(this.comments, 32);
/* 185:185 */    if (this.comments != 0) {
/* 186:186 */      for (int i = 0; i < this.comments; i++) {
/* 187:187 */        if (this.user_comments[i] != null) {
/* 188:188 */          opb.write(this.comment_lengths[i], 32);
/* 189:189 */          opb.write(this.user_comments[i]);
/* 190:    */        }
/* 191:    */        else {
/* 192:192 */          opb.write(0, 32);
/* 193:    */        }
/* 194:    */      }
/* 195:    */    }
/* 196:196 */    opb.write(1, 1);
/* 197:197 */    return 0;
/* 198:    */  }
/* 199:    */  
/* 200:    */  public int header_out(Packet op) {
/* 201:201 */    Buffer opb = new Buffer();
/* 202:202 */    opb.writeinit();
/* 203:    */    
/* 204:204 */    if (pack(opb) != 0) {
/* 205:205 */      return -130;
/* 206:    */    }
/* 207:207 */    op.packet_base = new byte[opb.bytes()];
/* 208:208 */    op.packet = 0;
/* 209:209 */    op.bytes = opb.bytes();
/* 210:210 */    System.arraycopy(opb.buffer(), 0, op.packet_base, 0, op.bytes);
/* 211:211 */    op.b_o_s = 0;
/* 212:212 */    op.e_o_s = 0;
/* 213:213 */    op.granulepos = 0L;
/* 214:214 */    return 0;
/* 215:    */  }
/* 216:    */  
/* 217:    */  void clear() {
/* 218:218 */    for (int i = 0; i < this.comments; i++)
/* 219:219 */      this.user_comments[i] = null;
/* 220:220 */    this.user_comments = ((byte[][])null);
/* 221:221 */    this.vendor = null;
/* 222:    */  }
/* 223:    */  
/* 224:    */  public String getVendor() {
/* 225:225 */    return new String(this.vendor, 0, this.vendor.length - 1);
/* 226:    */  }
/* 227:    */  
/* 228:    */  public String getComment(int i) {
/* 229:229 */    if (this.comments <= i)
/* 230:230 */      return null;
/* 231:231 */    return new String(this.user_comments[i], 0, this.user_comments[i].length - 1);
/* 232:    */  }
/* 233:    */  
/* 234:    */  public String toString() {
/* 235:235 */    String foo = "Vendor: " + new String(this.vendor, 0, this.vendor.length - 1);
/* 236:236 */    for (int i = 0; i < this.comments; i++) {
/* 237:237 */      foo = foo + "\nComment: " + new String(this.user_comments[i], 0, this.user_comments[i].length - 1);
/* 238:    */    }
/* 239:    */    
/* 240:240 */    foo = foo + "\n";
/* 241:241 */    return foo;
/* 242:    */  }
/* 243:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Comment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */