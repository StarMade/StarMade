/*   1:    */package com.jcraft.jogg;
/*   2:    */
/*   9:    */public class SyncState
/*  10:    */{
/*  11:    */  public byte[] data;
/*  12:    */  
/*  18:    */  int storage;
/*  19:    */  
/*  24:    */  int fill;
/*  25:    */  
/*  30:    */  int returned;
/*  31:    */  
/*  36:    */  int unsynced;
/*  37:    */  
/*  42:    */  int headerbytes;
/*  43:    */  
/*  48:    */  int bodybytes;
/*  49:    */  
/*  55:    */  public int clear()
/*  56:    */  {
/*  57: 57 */    this.data = null;
/*  58: 58 */    return 0;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public int buffer(int size)
/*  62:    */  {
/*  63: 63 */    if (this.returned != 0) {
/*  64: 64 */      this.fill -= this.returned;
/*  65: 65 */      if (this.fill > 0) {
/*  66: 66 */        System.arraycopy(this.data, this.returned, this.data, 0, this.fill);
/*  67:    */      }
/*  68: 68 */      this.returned = 0;
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    if (size > this.storage - this.fill)
/*  72:    */    {
/*  73: 73 */      int newsize = size + this.fill + 4096;
/*  74: 74 */      if (this.data != null) {
/*  75: 75 */        byte[] foo = new byte[newsize];
/*  76: 76 */        System.arraycopy(this.data, 0, foo, 0, this.data.length);
/*  77: 77 */        this.data = foo;
/*  78:    */      }
/*  79:    */      else {
/*  80: 80 */        this.data = new byte[newsize];
/*  81:    */      }
/*  82: 82 */      this.storage = newsize;
/*  83:    */    }
/*  84:    */    
/*  85: 85 */    return this.fill;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public int wrote(int bytes) {
/*  89: 89 */    if (this.fill + bytes > this.storage)
/*  90: 90 */      return -1;
/*  91: 91 */    this.fill += bytes;
/*  92: 92 */    return 0;
/*  93:    */  }
/*  94:    */  
/* 102:102 */  private Page pageseek = new Page();
/* 103:103 */  private byte[] chksum = new byte[4];
/* 104:    */  
/* 105:    */  public int pageseek(Page og) {
/* 106:106 */    int page = this.returned;
/* 107:    */    
/* 108:108 */    int bytes = this.fill - this.returned;
/* 109:    */    
/* 110:110 */    if (this.headerbytes == 0)
/* 111:    */    {
/* 112:112 */      if (bytes < 27) {
/* 113:113 */        return 0;
/* 114:    */      }
/* 115:    */      
/* 116:116 */      if ((this.data[page] != 79) || (this.data[(page + 1)] != 103) || (this.data[(page + 2)] != 103) || (this.data[(page + 3)] != 83))
/* 117:    */      {
/* 118:118 */        this.headerbytes = 0;
/* 119:119 */        this.bodybytes = 0;
/* 120:    */        
/* 122:122 */        int next = 0;
/* 123:123 */        for (int ii = 0; ii < bytes - 1; ii++) {
/* 124:124 */          if (this.data[(page + 1 + ii)] == 79) {
/* 125:125 */            next = page + 1 + ii;
/* 126:126 */            break;
/* 127:    */          }
/* 128:    */        }
/* 129:    */        
/* 130:130 */        if (next == 0) {
/* 131:131 */          next = this.fill;
/* 132:    */        }
/* 133:133 */        this.returned = next;
/* 134:134 */        return -(next - page);
/* 135:    */      }
/* 136:136 */      int _headerbytes = (this.data[(page + 26)] & 0xFF) + 27;
/* 137:137 */      if (bytes < _headerbytes) {
/* 138:138 */        return 0;
/* 139:    */      }
/* 140:    */      
/* 142:142 */      for (int i = 0; i < (this.data[(page + 26)] & 0xFF); i++) {
/* 143:143 */        this.bodybytes += (this.data[(page + 27 + i)] & 0xFF);
/* 144:    */      }
/* 145:145 */      this.headerbytes = _headerbytes;
/* 146:    */    }
/* 147:    */    
/* 148:148 */    if (this.bodybytes + this.headerbytes > bytes) {
/* 149:149 */      return 0;
/* 150:    */    }
/* 151:    */    
/* 152:152 */    synchronized (this.chksum)
/* 153:    */    {
/* 155:155 */      System.arraycopy(this.data, page + 22, this.chksum, 0, 4);
/* 156:156 */      this.data[(page + 22)] = 0;
/* 157:157 */      this.data[(page + 23)] = 0;
/* 158:158 */      this.data[(page + 24)] = 0;
/* 159:159 */      this.data[(page + 25)] = 0;
/* 160:    */      
/* 162:162 */      Page log = this.pageseek;
/* 163:163 */      log.header_base = this.data;
/* 164:164 */      log.header = page;
/* 165:165 */      log.header_len = this.headerbytes;
/* 166:    */      
/* 167:167 */      log.body_base = this.data;
/* 168:168 */      log.body = (page + this.headerbytes);
/* 169:169 */      log.body_len = this.bodybytes;
/* 170:170 */      log.checksum();
/* 171:    */      
/* 173:173 */      if ((this.chksum[0] != this.data[(page + 22)]) || (this.chksum[1] != this.data[(page + 23)]) || (this.chksum[2] != this.data[(page + 24)]) || (this.chksum[3] != this.data[(page + 25)]))
/* 174:    */      {
/* 177:177 */        System.arraycopy(this.chksum, 0, this.data, page + 22, 4);
/* 178:    */        
/* 180:180 */        this.headerbytes = 0;
/* 181:181 */        this.bodybytes = 0;
/* 182:    */        
/* 183:183 */        int next = 0;
/* 184:184 */        for (int ii = 0; ii < bytes - 1; ii++) {
/* 185:185 */          if (this.data[(page + 1 + ii)] == 79) {
/* 186:186 */            next = page + 1 + ii;
/* 187:187 */            break;
/* 188:    */          }
/* 189:    */        }
/* 190:    */        
/* 191:191 */        if (next == 0)
/* 192:192 */          next = this.fill;
/* 193:193 */        this.returned = next;
/* 194:194 */        return -(next - page);
/* 195:    */      }
/* 196:    */    }
/* 197:    */    
/* 200:200 */    page = this.returned;
/* 201:    */    
/* 202:202 */    if (og != null) {
/* 203:203 */      og.header_base = this.data;
/* 204:204 */      og.header = page;
/* 205:205 */      og.header_len = this.headerbytes;
/* 206:206 */      og.body_base = this.data;
/* 207:207 */      og.body = (page + this.headerbytes);
/* 208:208 */      og.body_len = this.bodybytes;
/* 209:    */    }
/* 210:    */    
/* 211:211 */    this.unsynced = 0;
/* 212:212 */    this.returned += (bytes = this.headerbytes + this.bodybytes);
/* 213:213 */    this.headerbytes = 0;
/* 214:214 */    this.bodybytes = 0;
/* 215:215 */    return bytes;
/* 216:    */  }
/* 217:    */  
/* 232:    */  public int pageout(Page og)
/* 233:    */  {
/* 234:    */    for (;;)
/* 235:    */    {
/* 236:236 */      int ret = pageseek(og);
/* 237:237 */      if (ret > 0)
/* 238:    */      {
/* 239:239 */        return 1;
/* 240:    */      }
/* 241:241 */      if (ret == 0)
/* 242:    */      {
/* 243:243 */        return 0;
/* 244:    */      }
/* 245:    */      
/* 247:247 */      if (this.unsynced == 0) {
/* 248:248 */        this.unsynced = 1;
/* 249:249 */        return -1;
/* 250:    */      }
/* 251:    */    }
/* 252:    */  }
/* 253:    */  
/* 255:    */  public int reset()
/* 256:    */  {
/* 257:257 */    this.fill = 0;
/* 258:258 */    this.returned = 0;
/* 259:259 */    this.unsynced = 0;
/* 260:260 */    this.headerbytes = 0;
/* 261:261 */    this.bodybytes = 0;
/* 262:262 */    return 0;
/* 263:    */  }
/* 264:    */  
/* 265:    */  public void init() {}
/* 266:    */  
/* 267:    */  public int getDataOffset()
/* 268:    */  {
/* 269:269 */    return this.returned;
/* 270:    */  }
/* 271:    */  
/* 272:    */  public int getBufferOffset() {
/* 273:273 */    return this.fill;
/* 274:    */  }
/* 275:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jogg.SyncState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */