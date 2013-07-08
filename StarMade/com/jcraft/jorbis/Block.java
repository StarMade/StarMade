/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*   3:    */import com.jcraft.jogg.Buffer;
/*   4:    */import com.jcraft.jogg.Packet;
/*   5:    */
/*  31:    */public class Block
/*  32:    */{
/*  33: 33 */  float[][] pcm = new float[0][];
/*  34: 34 */  Buffer opb = new Buffer();
/*  35:    */  
/*  36:    */  int lW;
/*  37:    */  
/*  38:    */  int W;
/*  39:    */  
/*  40:    */  int nW;
/*  41:    */  int pcmend;
/*  42:    */  int mode;
/*  43:    */  int eofflag;
/*  44:    */  long granulepos;
/*  45:    */  long sequence;
/*  46:    */  DspState vd;
/*  47:    */  int glue_bits;
/*  48:    */  int time_bits;
/*  49:    */  int floor_bits;
/*  50:    */  int res_bits;
/*  51:    */  
/*  52:    */  public Block(DspState vd)
/*  53:    */  {
/*  54: 54 */    this.vd = vd;
/*  55: 55 */    if (vd.analysisp != 0) {
/*  56: 56 */      this.opb.writeinit();
/*  57:    */    }
/*  58:    */  }
/*  59:    */  
/*  60:    */  public void init(DspState vd) {
/*  61: 61 */    this.vd = vd;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public int clear() {
/*  65: 65 */    if ((this.vd != null) && 
/*  66: 66 */      (this.vd.analysisp != 0)) {
/*  67: 67 */      this.opb.writeclear();
/*  68:    */    }
/*  69:    */    
/*  70: 70 */    return 0;
/*  71:    */  }
/*  72:    */  
/*  73:    */  public int synthesis(Packet op) {
/*  74: 74 */    Info vi = this.vd.vi;
/*  75:    */    
/*  77: 77 */    this.opb.readinit(op.packet_base, op.packet, op.bytes);
/*  78:    */    
/*  80: 80 */    if (this.opb.read(1) != 0)
/*  81:    */    {
/*  82: 82 */      return -1;
/*  83:    */    }
/*  84:    */    
/*  86: 86 */    int _mode = this.opb.read(this.vd.modebits);
/*  87: 87 */    if (_mode == -1) {
/*  88: 88 */      return -1;
/*  89:    */    }
/*  90: 90 */    this.mode = _mode;
/*  91: 91 */    this.W = vi.mode_param[this.mode].blockflag;
/*  92: 92 */    if (this.W != 0) {
/*  93: 93 */      this.lW = this.opb.read(1);
/*  94: 94 */      this.nW = this.opb.read(1);
/*  95: 95 */      if (this.nW == -1) {
/*  96: 96 */        return -1;
/*  97:    */      }
/*  98:    */    } else {
/*  99: 99 */      this.lW = 0;
/* 100:100 */      this.nW = 0;
/* 101:    */    }
/* 102:    */    
/* 104:104 */    this.granulepos = op.granulepos;
/* 105:105 */    this.sequence = (op.packetno - 3L);
/* 106:106 */    this.eofflag = op.e_o_s;
/* 107:    */    
/* 109:109 */    this.pcmend = vi.blocksizes[this.W];
/* 110:110 */    if (this.pcm.length < vi.channels) {
/* 111:111 */      this.pcm = new float[vi.channels][];
/* 112:    */    }
/* 113:113 */    for (int i = 0; i < vi.channels; i++) {
/* 114:114 */      if ((this.pcm[i] == null) || (this.pcm[i].length < this.pcmend)) {
/* 115:115 */        this.pcm[i] = new float[this.pcmend];
/* 116:    */      }
/* 117:    */      else {
/* 118:118 */        for (int j = 0; j < this.pcmend; j++) {
/* 119:119 */          this.pcm[i][j] = 0.0F;
/* 120:    */        }
/* 121:    */      }
/* 122:    */    }
/* 123:    */    
/* 125:125 */    int type = vi.map_type[vi.mode_param[this.mode].mapping];
/* 126:126 */    return FuncMapping.mapping_P[type].inverse(this, this.vd.mode[this.mode]);
/* 127:    */  }
/* 128:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Block
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */