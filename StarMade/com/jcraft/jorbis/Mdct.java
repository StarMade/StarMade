/*     */ package com.jcraft.jorbis;
/*     */ 
/*     */ class Mdct
/*     */ {
/*     */   int n;
/*     */   int log2n;
/*     */   float[] trig;
/*     */   int[] bitrev;
/*     */   float scale;
/*  86 */   float[] _x = new float[1024];
/*  87 */   float[] _w = new float[1024];
/*     */ 
/*     */   void init(int n)
/*     */   {
/*  40 */     this.bitrev = new int[n / 4];
/*  41 */     this.trig = new float[n + n / 4];
/*     */ 
/*  43 */     this.log2n = ((int)Math.rint(Math.log(n) / Math.log(2.0D)));
/*  44 */     this.n = n;
/*     */ 
/*  46 */     int AE = 0;
/*  47 */     int AO = 1;
/*  48 */     int BE = AE + n / 2;
/*  49 */     int BO = BE + 1;
/*  50 */     int CE = BE + n / 2;
/*  51 */     int CO = CE + 1;
/*     */ 
/*  53 */     for (int i = 0; i < n / 4; i++) {
/*  54 */       this.trig[(AE + i * 2)] = ((float)Math.cos(3.141592653589793D / n * (4 * i)));
/*  55 */       this.trig[(AO + i * 2)] = ((float)-Math.sin(3.141592653589793D / n * (4 * i)));
/*  56 */       this.trig[(BE + i * 2)] = ((float)Math.cos(3.141592653589793D / (2 * n) * (2 * i + 1)));
/*  57 */       this.trig[(BO + i * 2)] = ((float)Math.sin(3.141592653589793D / (2 * n) * (2 * i + 1)));
/*     */     }
/*  59 */     for (int i = 0; i < n / 8; i++) {
/*  60 */       this.trig[(CE + i * 2)] = ((float)Math.cos(3.141592653589793D / n * (4 * i + 2)));
/*  61 */       this.trig[(CO + i * 2)] = ((float)-Math.sin(3.141592653589793D / n * (4 * i + 2)));
/*     */     }
/*     */ 
/*  65 */     int mask = (1 << this.log2n - 1) - 1;
/*  66 */     int msb = 1 << this.log2n - 2;
/*  67 */     for (int i = 0; i < n / 8; i++) {
/*  68 */       int acc = 0;
/*  69 */       for (int j = 0; msb >>> j != 0; j++)
/*  70 */         if ((msb >>> j & i) != 0)
/*  71 */           acc |= 1 << j;
/*  72 */       this.bitrev[(i * 2)] = ((acc ^ 0xFFFFFFFF) & mask);
/*     */ 
/*  74 */       this.bitrev[(i * 2 + 1)] = acc;
/*     */     }
/*     */ 
/*  77 */     this.scale = (4.0F / n);
/*     */   }
/*     */ 
/*     */   void clear()
/*     */   {
/*     */   }
/*     */ 
/*     */   void forward(float[] in, float[] out)
/*     */   {
/*     */   }
/*     */ 
/*     */   synchronized void backward(float[] in, float[] out)
/*     */   {
/*  90 */     if (this._x.length < this.n / 2) {
/*  91 */       this._x = new float[this.n / 2];
/*     */     }
/*  93 */     if (this._w.length < this.n / 2) {
/*  94 */       this._w = new float[this.n / 2];
/*     */     }
/*  96 */     float[] x = this._x;
/*  97 */     float[] w = this._w;
/*  98 */     int n2 = this.n >>> 1;
/*  99 */     int n4 = this.n >>> 2;
/* 100 */     int n8 = this.n >>> 3;
/*     */ 
/* 104 */     int inO = 1;
/* 105 */     int xO = 0;
/* 106 */     int A = n2;
/*     */ 
/* 109 */     for (int i = 0; i < n8; i++) {
/* 110 */       A -= 2;
/* 111 */       x[(xO++)] = (-in[(inO + 2)] * this.trig[(A + 1)] - in[inO] * this.trig[A]);
/* 112 */       x[(xO++)] = (in[inO] * this.trig[(A + 1)] - in[(inO + 2)] * this.trig[A]);
/* 113 */       inO += 4;
/*     */     }
/*     */ 
/* 116 */     inO = n2 - 4;
/*     */ 
/* 118 */     for (i = 0; i < n8; i++) {
/* 119 */       A -= 2;
/* 120 */       x[(xO++)] = (in[inO] * this.trig[(A + 1)] + in[(inO + 2)] * this.trig[A]);
/* 121 */       x[(xO++)] = (in[inO] * this.trig[A] - in[(inO + 2)] * this.trig[(A + 1)]);
/* 122 */       inO -= 4;
/*     */     }
/*     */ 
/* 126 */     float[] xxx = mdct_kernel(x, w, this.n, n2, n4, n8);
/* 127 */     int xx = 0;
/*     */ 
/* 132 */     int B = n2;
/* 133 */     int o1 = n4; int o2 = o1 - 1;
/* 134 */     int o3 = n4 + n2; int o4 = o3 - 1;
/*     */ 
/* 136 */     for (int i = 0; i < n4; i++) {
/* 137 */       float temp1 = xxx[xx] * this.trig[(B + 1)] - xxx[(xx + 1)] * this.trig[B];
/* 138 */       float temp2 = -(xxx[xx] * this.trig[B] + xxx[(xx + 1)] * this.trig[(B + 1)]);
/*     */ 
/* 140 */       out[o1] = (-temp1);
/* 141 */       out[o2] = temp1;
/* 142 */       out[o3] = temp2;
/* 143 */       out[o4] = temp2;
/*     */ 
/* 145 */       o1++;
/* 146 */       o2--;
/* 147 */       o3++;
/* 148 */       o4--;
/* 149 */       xx += 2;
/* 150 */       B += 2;
/*     */     }
/*     */   }
/*     */ 
/*     */   private float[] mdct_kernel(float[] x, float[] w, int n, int n2, int n4, int n8)
/*     */   {
/* 159 */     int xA = n4;
/* 160 */     int xB = 0;
/* 161 */     int w2 = n4;
/* 162 */     int A = n2;
/*     */ 
/* 164 */     for (int i = 0; i < n4; ) {
/* 165 */       float x0 = x[xA] - x[xB];
/*     */ 
/* 167 */       w[(w2 + i)] = (x[(xA++)] + x[(xB++)]);
/*     */ 
/* 169 */       float x1 = x[xA] - x[xB];
/* 170 */       A -= 4;
/*     */ 
/* 172 */       w[(i++)] = (x0 * this.trig[A] + x1 * this.trig[(A + 1)]);
/* 173 */       w[i] = (x1 * this.trig[A] - x0 * this.trig[(A + 1)]);
/*     */ 
/* 175 */       w[(w2 + i)] = (x[(xA++)] + x[(xB++)]);
/* 176 */       i++;
/*     */     }
/*     */ 
/* 182 */     for (int i = 0; i < this.log2n - 3; i++) {
/* 183 */       int k0 = n >>> i + 2;
/* 184 */       int k1 = 1 << i + 3;
/* 185 */       int wbase = n2 - 2;
/*     */ 
/* 187 */       A = 0;
/*     */ 
/* 190 */       for (int r = 0; r < k0 >>> 2; r++) {
/* 191 */         int w1 = wbase;
/* 192 */         w2 = w1 - (k0 >> 1);
/* 193 */         float AEv = this.trig[A];
/* 194 */         float AOv = this.trig[(A + 1)];
/* 195 */         wbase -= 2;
/*     */ 
/* 197 */         k0++;
/* 198 */         for (int s = 0; s < 2 << i; s++) {
/* 199 */           float wB = w[w1] - w[w2];
/* 200 */           w[w1] += w[w2];
/*     */ 
/* 202 */           float wA = w[(++w1)] - w[(++w2)];
/* 203 */           w[w1] += w[w2];
/*     */ 
/* 205 */           x[w2] = (wA * AEv - wB * AOv);
/* 206 */           x[(w2 - 1)] = (wB * AEv + wA * AOv);
/*     */ 
/* 208 */           w1 -= k0;
/* 209 */           w2 -= k0;
/*     */         }
/* 211 */         k0--;
/* 212 */         A += k1;
/*     */       }
/*     */ 
/* 215 */       float[] temp = w;
/* 216 */       w = x;
/* 217 */       x = temp;
/*     */     }
/*     */ 
/* 223 */     int C = n;
/* 224 */     int bit = 0;
/* 225 */     int x1 = 0;
/* 226 */     int x2 = n2 - 1;
/*     */ 
/* 228 */     for (int i = 0; i < n8; i++) {
/* 229 */       int t1 = this.bitrev[(bit++)];
/* 230 */       int t2 = this.bitrev[(bit++)];
/*     */ 
/* 232 */       float wA = w[t1] - w[(t2 + 1)];
/* 233 */       float wB = w[(t1 - 1)] + w[t2];
/* 234 */       float wC = w[t1] + w[(t2 + 1)];
/* 235 */       float wD = w[(t1 - 1)] - w[t2];
/*     */ 
/* 237 */       float wACE = wA * this.trig[C];
/* 238 */       float wBCE = wB * this.trig[(C++)];
/* 239 */       float wACO = wA * this.trig[C];
/* 240 */       float wBCO = wB * this.trig[(C++)];
/*     */ 
/* 242 */       x[(x1++)] = ((wC + wACO + wBCE) * 0.5F);
/* 243 */       x[(x2--)] = ((-wD + wBCO - wACE) * 0.5F);
/* 244 */       x[(x1++)] = ((wD + wBCO - wACE) * 0.5F);
/* 245 */       x[(x2--)] = ((wC - wACO - wBCE) * 0.5F);
/*     */     }
/*     */ 
/* 248 */     return x;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Mdct
 * JD-Core Version:    0.6.2
 */