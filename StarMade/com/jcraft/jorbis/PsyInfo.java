/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/*  7:   */class PsyInfo
/*  8:   */{
/*  9:   */  int athp;
/* 10:   */  
/* 13:   */  int decayp;
/* 14:   */  
/* 17:   */  int smoothp;
/* 18:   */  
/* 21:   */  int noisefitp;
/* 22:   */  
/* 25:   */  int noisefit_subblock;
/* 26:   */  
/* 29:   */  float noisefit_threshdB;
/* 30:   */  
/* 33:   */  float ath_att;
/* 34:   */  
/* 37:   */  int tonemaskp;
/* 38:   */  
/* 41:41 */  float[] toneatt_125Hz = new float[5];
/* 42:42 */  float[] toneatt_250Hz = new float[5];
/* 43:43 */  float[] toneatt_500Hz = new float[5];
/* 44:44 */  float[] toneatt_1000Hz = new float[5];
/* 45:45 */  float[] toneatt_2000Hz = new float[5];
/* 46:46 */  float[] toneatt_4000Hz = new float[5];
/* 47:47 */  float[] toneatt_8000Hz = new float[5];
/* 48:   */  
/* 49:   */  int peakattp;
/* 50:50 */  float[] peakatt_125Hz = new float[5];
/* 51:51 */  float[] peakatt_250Hz = new float[5];
/* 52:52 */  float[] peakatt_500Hz = new float[5];
/* 53:53 */  float[] peakatt_1000Hz = new float[5];
/* 54:54 */  float[] peakatt_2000Hz = new float[5];
/* 55:55 */  float[] peakatt_4000Hz = new float[5];
/* 56:56 */  float[] peakatt_8000Hz = new float[5];
/* 57:   */  
/* 58:   */  int noisemaskp;
/* 59:59 */  float[] noiseatt_125Hz = new float[5];
/* 60:60 */  float[] noiseatt_250Hz = new float[5];
/* 61:61 */  float[] noiseatt_500Hz = new float[5];
/* 62:62 */  float[] noiseatt_1000Hz = new float[5];
/* 63:63 */  float[] noiseatt_2000Hz = new float[5];
/* 64:64 */  float[] noiseatt_4000Hz = new float[5];
/* 65:65 */  float[] noiseatt_8000Hz = new float[5];
/* 66:   */  float max_curve_dB;
/* 67:   */  float attack_coeff;
/* 68:   */  float decay_coeff;
/* 69:   */  
/* 70:   */  void free() {}
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.PsyInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */