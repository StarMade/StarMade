/*     */ package org.lwjgl.openal;
/*     */ 
/*     */ public final class EFXUtil
/*     */ {
/*     */   private static final int EFFECT = 1111;
/*     */   private static final int FILTER = 2222;
/*     */ 
/*     */   public static boolean isEfxSupported()
/*     */   {
/*  65 */     if (!AL.isCreated()) {
/*  66 */       throw new OpenALException("OpenAL has not been created.");
/*     */     }
/*  68 */     return ALC10.alcIsExtensionPresent(AL.getDevice(), "ALC_EXT_EFX");
/*     */   }
/*     */ 
/*     */   public static boolean isEffectSupported(int effectType)
/*     */   {
/*  83 */     switch (effectType) {
/*     */     case 0:
/*     */     case 1:
/*     */     case 2:
/*     */     case 3:
/*     */     case 4:
/*     */     case 5:
/*     */     case 6:
/*     */     case 7:
/*     */     case 8:
/*     */     case 9:
/*     */     case 10:
/*     */     case 11:
/*     */     case 12:
/*     */     case 32768:
/*  98 */       break;
/*     */     default:
/* 100 */       throw new IllegalArgumentException("Unknown or invalid effect type: " + effectType);
/*     */     }
/*     */ 
/* 103 */     return testSupportGeneric(1111, effectType);
/*     */   }
/*     */ 
/*     */   public static boolean isFilterSupported(int filterType)
/*     */   {
/* 118 */     switch (filterType) {
/*     */     case 0:
/*     */     case 1:
/*     */     case 2:
/*     */     case 3:
/* 123 */       break;
/*     */     default:
/* 125 */       throw new IllegalArgumentException("Unknown or invalid filter type: " + filterType);
/*     */     }
/*     */ 
/* 128 */     return testSupportGeneric(2222, filterType);
/*     */   }
/*     */ 
/*     */   private static boolean testSupportGeneric(int objectType, int typeValue)
/*     */   {
/* 142 */     switch (objectType) {
/*     */     case 1111:
/*     */     case 2222:
/* 145 */       break;
/*     */     default:
/* 147 */       throw new IllegalArgumentException("Invalid objectType: " + objectType);
/*     */     }
/*     */ 
/* 150 */     boolean supported = false;
/* 151 */     if (isEfxSupported()) {
/* 154 */       AL10.alGetError();
/*     */ 
/* 156 */       int testObject = 0;
/*     */       int genError;
/*     */       try { switch (objectType) {
/*     */         case 1111:
/* 160 */           testObject = EFX10.alGenEffects();
/* 161 */           break;
/*     */         case 2222:
/* 163 */           testObject = EFX10.alGenFilters();
/* 164 */           break;
/*     */         default:
/* 166 */           throw new IllegalArgumentException("Invalid objectType: " + objectType);
/*     */         }
/* 168 */         genError = AL10.alGetError();
/*     */       }
/*     */       catch (OpenALException debugBuildException)
/*     */       {
/*     */         int genError;
/* 172 */         if (debugBuildException.getMessage().contains("AL_OUT_OF_MEMORY"))
/* 173 */           genError = 40965;
/*     */         else {
/* 175 */           genError = 40964;
/*     */         }
/*     */       }
/*     */ 
/* 179 */       if (genError == 0) {
/* 181 */         AL10.alGetError();
/*     */         int setError;
/*     */         try {
/* 184 */           switch (objectType) {
/*     */           case 1111:
/* 186 */             EFX10.alEffecti(testObject, 32769, typeValue);
/* 187 */             break;
/*     */           case 2222:
/* 189 */             EFX10.alFilteri(testObject, 32769, typeValue);
/* 190 */             break;
/*     */           default:
/* 192 */             throw new IllegalArgumentException("Invalid objectType: " + objectType);
/*     */           }
/* 194 */           setError = AL10.alGetError();
/*     */         }
/*     */         catch (OpenALException debugBuildException)
/*     */         {
/* 198 */           setError = 40963;
/*     */         }
/*     */ 
/* 201 */         if (setError == 0) {
/* 202 */           supported = true;
/*     */         }
/*     */ 
/*     */         try
/*     */         {
/* 207 */           switch (objectType) {
/*     */           case 1111:
/* 209 */             EFX10.alDeleteEffects(testObject);
/* 210 */             break;
/*     */           case 2222:
/* 212 */             EFX10.alDeleteFilters(testObject);
/* 213 */             break;
/*     */           default:
/* 215 */             throw new IllegalArgumentException("Invalid objectType: " + objectType);
/*     */           }
/*     */         }
/*     */         catch (OpenALException debugBuildException) {
/*     */         }
/*     */       }
/* 221 */       else if (genError == 40965) {
/* 222 */         throw new OpenALException(genError);
/*     */       }
/*     */     }
/*     */ 
/* 226 */     return supported;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.EFXUtil
 * JD-Core Version:    0.6.2
 */