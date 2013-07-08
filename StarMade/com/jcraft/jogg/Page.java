/*   1:    */package com.jcraft.jogg;
/*   2:    */
/*  28:    */public class Page
/*  29:    */{
/*  30: 30 */  private static int[] crc_lookup = new int[256];
/*  31:    */  
/*  32: 32 */  static { for (int i = 0; i < crc_lookup.length; i++) {
/*  33: 33 */      crc_lookup[i] = crc_entry(i);
/*  34:    */    }
/*  35:    */  }
/*  36:    */  
/*  37:    */  private static int crc_entry(int index) {
/*  38: 38 */    int r = index << 24;
/*  39: 39 */    for (int i = 0; i < 8; i++) {
/*  40: 40 */      if ((r & 0x80000000) != 0) {
/*  41: 41 */        r = r << 1 ^ 0x4C11DB7;
/*  43:    */      }
/*  44:    */      else
/*  45:    */      {
/*  47: 47 */        r <<= 1;
/*  48:    */      }
/*  49:    */    }
/*  50: 50 */    return r & 0xFFFFFFFF;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public byte[] header_base;
/*  54:    */  public int header;
/*  55:    */  public int header_len;
/*  56:    */  public byte[] body_base;
/*  57:    */  public int body;
/*  58:    */  public int body_len;
/*  59:    */  int version()
/*  60:    */  {
/*  61: 61 */    return this.header_base[(this.header + 4)] & 0xFF;
/*  62:    */  }
/*  63:    */  
/*  64:    */  int continued() {
/*  65: 65 */    return this.header_base[(this.header + 5)] & 0x1;
/*  66:    */  }
/*  67:    */  
/*  68:    */  public int bos() {
/*  69: 69 */    return this.header_base[(this.header + 5)] & 0x2;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public int eos() {
/*  73: 73 */    return this.header_base[(this.header + 5)] & 0x4;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public long granulepos() {
/*  77: 77 */    long foo = this.header_base[(this.header + 13)] & 0xFF;
/*  78: 78 */    foo = foo << 8 | this.header_base[(this.header + 12)] & 0xFF;
/*  79: 79 */    foo = foo << 8 | this.header_base[(this.header + 11)] & 0xFF;
/*  80: 80 */    foo = foo << 8 | this.header_base[(this.header + 10)] & 0xFF;
/*  81: 81 */    foo = foo << 8 | this.header_base[(this.header + 9)] & 0xFF;
/*  82: 82 */    foo = foo << 8 | this.header_base[(this.header + 8)] & 0xFF;
/*  83: 83 */    foo = foo << 8 | this.header_base[(this.header + 7)] & 0xFF;
/*  84: 84 */    foo = foo << 8 | this.header_base[(this.header + 6)] & 0xFF;
/*  85: 85 */    return foo;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public int serialno() {
/*  89: 89 */    return this.header_base[(this.header + 14)] & 0xFF | (this.header_base[(this.header + 15)] & 0xFF) << 8 | (this.header_base[(this.header + 16)] & 0xFF) << 16 | (this.header_base[(this.header + 17)] & 0xFF) << 24;
/*  90:    */  }
/*  91:    */  
/*  93:    */  int pageno()
/*  94:    */  {
/*  95: 95 */    return this.header_base[(this.header + 18)] & 0xFF | (this.header_base[(this.header + 19)] & 0xFF) << 8 | (this.header_base[(this.header + 20)] & 0xFF) << 16 | (this.header_base[(this.header + 21)] & 0xFF) << 24;
/*  96:    */  }
/*  97:    */  
/*  99:    */  void checksum()
/* 100:    */  {
/* 101:101 */    int crc_reg = 0;
/* 102:    */    
/* 103:103 */    for (int i = 0; i < this.header_len; i++) {
/* 104:104 */      crc_reg = crc_reg << 8 ^ crc_lookup[(crc_reg >>> 24 & 0xFF ^ this.header_base[(this.header + i)] & 0xFF)];
/* 105:    */    }
/* 106:    */    
/* 107:107 */    for (int i = 0; i < this.body_len; i++) {
/* 108:108 */      crc_reg = crc_reg << 8 ^ crc_lookup[(crc_reg >>> 24 & 0xFF ^ this.body_base[(this.body + i)] & 0xFF)];
/* 109:    */    }
/* 110:    */    
/* 111:111 */    this.header_base[(this.header + 22)] = ((byte)crc_reg);
/* 112:112 */    this.header_base[(this.header + 23)] = ((byte)(crc_reg >>> 8));
/* 113:113 */    this.header_base[(this.header + 24)] = ((byte)(crc_reg >>> 16));
/* 114:114 */    this.header_base[(this.header + 25)] = ((byte)(crc_reg >>> 24));
/* 115:    */  }
/* 116:    */  
/* 117:    */  public Page copy() {
/* 118:118 */    return copy(new Page());
/* 119:    */  }
/* 120:    */  
/* 121:    */  public Page copy(Page p) {
/* 122:122 */    byte[] tmp = new byte[this.header_len];
/* 123:123 */    System.arraycopy(this.header_base, this.header, tmp, 0, this.header_len);
/* 124:124 */    p.header_len = this.header_len;
/* 125:125 */    p.header_base = tmp;
/* 126:126 */    p.header = 0;
/* 127:127 */    tmp = new byte[this.body_len];
/* 128:128 */    System.arraycopy(this.body_base, this.body, tmp, 0, this.body_len);
/* 129:129 */    p.body_len = this.body_len;
/* 130:130 */    p.body_base = tmp;
/* 131:131 */    p.body = 0;
/* 132:132 */    return p;
/* 133:    */  }
/* 134:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jogg.Page
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */