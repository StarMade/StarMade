/*   1:    */package org.lwjgl.openal;
/*   2:    */
/*  22:    */public final class EFXUtil
/*  23:    */{
/*  24:    */  private static final int EFFECT = 1111;
/*  25:    */  
/*  43:    */  private static final int FILTER = 2222;
/*  44:    */  
/*  63:    */  public static boolean isEfxSupported()
/*  64:    */  {
/*  65: 65 */    if (!AL.isCreated()) {
/*  66: 66 */      throw new OpenALException("OpenAL has not been created.");
/*  67:    */    }
/*  68: 68 */    return ALC10.alcIsExtensionPresent(AL.getDevice(), "ALC_EXT_EFX");
/*  69:    */  }
/*  70:    */  
/*  81:    */  public static boolean isEffectSupported(int effectType)
/*  82:    */  {
/*  83: 83 */    switch (effectType) {
/*  84:    */    case 0: 
/*  85:    */    case 1: 
/*  86:    */    case 2: 
/*  87:    */    case 3: 
/*  88:    */    case 4: 
/*  89:    */    case 5: 
/*  90:    */    case 6: 
/*  91:    */    case 7: 
/*  92:    */    case 8: 
/*  93:    */    case 9: 
/*  94:    */    case 10: 
/*  95:    */    case 11: 
/*  96:    */    case 12: 
/*  97:    */    case 32768: 
/*  98: 98 */      break;
/*  99:    */    default: 
/* 100:100 */      throw new IllegalArgumentException("Unknown or invalid effect type: " + effectType);
/* 101:    */    }
/* 102:    */    
/* 103:103 */    return testSupportGeneric(1111, effectType);
/* 104:    */  }
/* 105:    */  
/* 116:    */  public static boolean isFilterSupported(int filterType)
/* 117:    */  {
/* 118:118 */    switch (filterType) {
/* 119:    */    case 0: 
/* 120:    */    case 1: 
/* 121:    */    case 2: 
/* 122:    */    case 3: 
/* 123:123 */      break;
/* 124:    */    default: 
/* 125:125 */      throw new IllegalArgumentException("Unknown or invalid filter type: " + filterType);
/* 126:    */    }
/* 127:    */    
/* 128:128 */    return testSupportGeneric(2222, filterType);
/* 129:    */  }
/* 130:    */  
/* 140:    */  private static boolean testSupportGeneric(int objectType, int typeValue)
/* 141:    */  {
/* 142:142 */    switch (objectType) {
/* 143:    */    case 1111: 
/* 144:    */    case 2222: 
/* 145:145 */      break;
/* 146:    */    default: 
/* 147:147 */      throw new IllegalArgumentException("Invalid objectType: " + objectType);
/* 148:    */    }
/* 149:    */    
/* 150:150 */    boolean supported = false;
/* 151:151 */    if (isEfxSupported())
/* 152:    */    {
/* 154:154 */      AL10.alGetError();
/* 155:    */      
/* 156:156 */      int testObject = 0;
/* 157:    */      int genError;
/* 158:158 */      try { switch (objectType) {
/* 159:    */        case 1111: 
/* 160:160 */          testObject = EFX10.alGenEffects();
/* 161:161 */          break;
/* 162:    */        case 2222: 
/* 163:163 */          testObject = EFX10.alGenFilters();
/* 164:164 */          break;
/* 165:    */        default: 
/* 166:166 */          throw new IllegalArgumentException("Invalid objectType: " + objectType);
/* 167:    */        }
/* 168:168 */        genError = AL10.alGetError();
/* 169:    */      }
/* 170:    */      catch (OpenALException debugBuildException) {
/* 171:    */        int genError;
/* 172:172 */        if (debugBuildException.getMessage().contains("AL_OUT_OF_MEMORY")) {
/* 173:173 */          genError = 40965;
/* 174:    */        } else {
/* 175:175 */          genError = 40964;
/* 176:    */        }
/* 177:    */      }
/* 178:    */      
/* 179:179 */      if (genError == 0)
/* 180:    */      {
/* 181:181 */        AL10.alGetError();
/* 182:    */        int setError;
/* 183:    */        try {
/* 184:184 */          switch (objectType) {
/* 185:    */          case 1111: 
/* 186:186 */            EFX10.alEffecti(testObject, 32769, typeValue);
/* 187:187 */            break;
/* 188:    */          case 2222: 
/* 189:189 */            EFX10.alFilteri(testObject, 32769, typeValue);
/* 190:190 */            break;
/* 191:    */          default: 
/* 192:192 */            throw new IllegalArgumentException("Invalid objectType: " + objectType);
/* 193:    */          }
/* 194:194 */          setError = AL10.alGetError();
/* 195:    */        }
/* 196:    */        catch (OpenALException debugBuildException)
/* 197:    */        {
/* 198:198 */          setError = 40963;
/* 199:    */        }
/* 200:    */        
/* 201:201 */        if (setError == 0) {
/* 202:202 */          supported = true;
/* 203:    */        }
/* 204:    */        
/* 205:    */        try
/* 206:    */        {
/* 207:207 */          switch (objectType) {
/* 208:    */          case 1111: 
/* 209:209 */            EFX10.alDeleteEffects(testObject);
/* 210:210 */            break;
/* 211:    */          case 2222: 
/* 212:212 */            EFX10.alDeleteFilters(testObject);
/* 213:213 */            break;
/* 214:    */          default: 
/* 215:215 */            throw new IllegalArgumentException("Invalid objectType: " + objectType);
/* 216:    */          }
/* 217:    */          
/* 218:    */        }
/* 219:    */        catch (OpenALException debugBuildException) {}
/* 220:    */      }
/* 221:221 */      else if (genError == 40965) {
/* 222:222 */        throw new OpenALException(genError);
/* 223:    */      }
/* 224:    */    }
/* 225:    */    
/* 226:226 */    return supported;
/* 227:    */  }
/* 228:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.EFXUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */