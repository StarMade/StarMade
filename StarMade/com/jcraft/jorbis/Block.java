/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ import com.jcraft.jogg.Buffer;
/*     */ import com.jcraft.jogg.Packet;
/*     */ 
/*     */ public class Block
/*     */ {
/*  33 */   float[][] pcm = new float[0][];
/*  34 */   Buffer opb = new Buffer();
/*     */   int lW;
/*     */   int W;
/*     */   int nW;
/*     */   int pcmend;
/*     */   int mode;
/*     */   int eofflag;
/*     */   long granulepos;
/*     */   long sequence;
/*     */   DspState vd;
/*     */   int glue_bits;
/*     */   int time_bits;
/*     */   int floor_bits;
/*     */   int res_bits;
/*     */ 
/*     */   public Block(DspState vd)
/*     */   {
/*  54 */     this.vd = vd;
/*  55 */     if (vd.analysisp != 0)
/*  56 */       this.opb.writeinit();
/*     */   }
/*     */ 
/*     */   public void init(DspState vd)
/*     */   {
/*  61 */     this.vd = vd;
/*     */   }
/*     */ 
/*     */   public int clear() {
/*  65 */     if ((this.vd != null) && 
/*  66 */       (this.vd.analysisp != 0)) {
/*  67 */       this.opb.writeclear();
/*     */     }
/*     */ 
/*  70 */     return 0;
/*     */   }
/*     */ 
/*     */   public int synthesis(Packet op) {
/*  74 */     Info vi = this.vd.vi;
/*     */ 
/*  77 */     this.opb.readinit(op.packet_base, op.packet, op.bytes);
/*     */ 
/*  80 */     if (this.opb.read(1) != 0)
/*     */     {
/*  82 */       return -1;
/*     */     }
/*     */ 
/*  86 */     int _mode = this.opb.read(this.vd.modebits);
/*  87 */     if (_mode == -1) {
/*  88 */       return -1;
/*     */     }
/*  90 */     this.mode = _mode;
/*  91 */     this.W = vi.mode_param[this.mode].blockflag;
/*  92 */     if (this.W != 0) {
/*  93 */       this.lW = this.opb.read(1);
/*  94 */       this.nW = this.opb.read(1);
/*  95 */       if (this.nW == -1)
/*  96 */         return -1;
/*     */     }
/*     */     else {
/*  99 */       this.lW = 0;
/* 100 */       this.nW = 0;
/*     */     }
/*     */ 
/* 104 */     this.granulepos = op.granulepos;
/* 105 */     this.sequence = (op.packetno - 3L);
/* 106 */     this.eofflag = op.e_o_s;
/*     */ 
/* 109 */     this.pcmend = vi.blocksizes[this.W];
/* 110 */     if (this.pcm.length < vi.channels) {
/* 111 */       this.pcm = new float[vi.channels][];
/*     */     }
/* 113 */     for (int i = 0; i < vi.channels; i++) {
/* 114 */       if ((this.pcm[i] == null) || (this.pcm[i].length < this.pcmend)) {
/* 115 */         this.pcm[i] = new float[this.pcmend];
/*     */       }
/*     */       else {
/* 118 */         for (int j = 0; j < this.pcmend; j++) {
/* 119 */           this.pcm[i][j] = 0.0F;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 125 */     int type = vi.map_type[vi.mode_param[this.mode].mapping];
/* 126 */     return FuncMapping.mapping_P[type].inverse(this, this.vd.mode[this.mode]);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Block
 * JD-Core Version:    0.6.2
 */