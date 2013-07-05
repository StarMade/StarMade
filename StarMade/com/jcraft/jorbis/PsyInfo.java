/*    */ package com.jcraft.jorbis;
/*    */ 
/*    */ class PsyInfo
/*    */ {
/*    */   int athp;
/*    */   int decayp;
/*    */   int smoothp;
/*    */   int noisefitp;
/*    */   int noisefit_subblock;
/*    */   float noisefit_threshdB;
/*    */   float ath_att;
/*    */   int tonemaskp;
/* 41 */   float[] toneatt_125Hz = new float[5];
/* 42 */   float[] toneatt_250Hz = new float[5];
/* 43 */   float[] toneatt_500Hz = new float[5];
/* 44 */   float[] toneatt_1000Hz = new float[5];
/* 45 */   float[] toneatt_2000Hz = new float[5];
/* 46 */   float[] toneatt_4000Hz = new float[5];
/* 47 */   float[] toneatt_8000Hz = new float[5];
/*    */   int peakattp;
/* 50 */   float[] peakatt_125Hz = new float[5];
/* 51 */   float[] peakatt_250Hz = new float[5];
/* 52 */   float[] peakatt_500Hz = new float[5];
/* 53 */   float[] peakatt_1000Hz = new float[5];
/* 54 */   float[] peakatt_2000Hz = new float[5];
/* 55 */   float[] peakatt_4000Hz = new float[5];
/* 56 */   float[] peakatt_8000Hz = new float[5];
/*    */   int noisemaskp;
/* 59 */   float[] noiseatt_125Hz = new float[5];
/* 60 */   float[] noiseatt_250Hz = new float[5];
/* 61 */   float[] noiseatt_500Hz = new float[5];
/* 62 */   float[] noiseatt_1000Hz = new float[5];
/* 63 */   float[] noiseatt_2000Hz = new float[5];
/* 64 */   float[] noiseatt_4000Hz = new float[5];
/* 65 */   float[] noiseatt_8000Hz = new float[5];
/*    */   float max_curve_dB;
/*    */   float attack_coeff;
/*    */   float decay_coeff;
/*    */ 
/*    */   void free()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.PsyInfo
 * JD-Core Version:    0.6.2
 */