/*   1:    */package org.lwjgl.openal;
/*   2:    */
/*   3:    */import java.nio.FloatBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferChecks;
/*   6:    */import org.lwjgl.LWJGLException;
/*   7:    */import org.lwjgl.MemoryUtil;
/*   8:    */
/*  43:    */public final class EFX10
/*  44:    */{
/*  45:    */  public static final String ALC_EXT_EFX_NAME = "ALC_EXT_EFX";
/*  46:    */  public static final int ALC_EFX_MAJOR_VERSION = 131073;
/*  47:    */  public static final int ALC_EFX_MINOR_VERSION = 131074;
/*  48:    */  public static final int ALC_MAX_AUXILIARY_SENDS = 131075;
/*  49:    */  public static final int AL_METERS_PER_UNIT = 131076;
/*  50:    */  public static final int AL_DIRECT_FILTER = 131077;
/*  51:    */  public static final int AL_AUXILIARY_SEND_FILTER = 131078;
/*  52:    */  public static final int AL_AIR_ABSORPTION_FACTOR = 131079;
/*  53:    */  public static final int AL_ROOM_ROLLOFF_FACTOR = 131080;
/*  54:    */  public static final int AL_CONE_OUTER_GAINHF = 131081;
/*  55:    */  public static final int AL_DIRECT_FILTER_GAINHF_AUTO = 131082;
/*  56:    */  public static final int AL_AUXILIARY_SEND_FILTER_GAIN_AUTO = 131083;
/*  57:    */  public static final int AL_AUXILIARY_SEND_FILTER_GAINHF_AUTO = 131084;
/*  58:    */  public static final int AL_EFFECTSLOT_EFFECT = 1;
/*  59:    */  public static final int AL_EFFECTSLOT_GAIN = 2;
/*  60:    */  public static final int AL_EFFECTSLOT_AUXILIARY_SEND_AUTO = 3;
/*  61:    */  public static final int AL_EFFECTSLOT_NULL = 0;
/*  62:    */  public static final int AL_REVERB_DENSITY = 1;
/*  63:    */  public static final int AL_REVERB_DIFFUSION = 2;
/*  64:    */  public static final int AL_REVERB_GAIN = 3;
/*  65:    */  public static final int AL_REVERB_GAINHF = 4;
/*  66:    */  public static final int AL_REVERB_DECAY_TIME = 5;
/*  67:    */  public static final int AL_REVERB_DECAY_HFRATIO = 6;
/*  68:    */  public static final int AL_REVERB_REFLECTIONS_GAIN = 7;
/*  69:    */  public static final int AL_REVERB_REFLECTIONS_DELAY = 8;
/*  70:    */  public static final int AL_REVERB_LATE_REVERB_GAIN = 9;
/*  71:    */  public static final int AL_REVERB_LATE_REVERB_DELAY = 10;
/*  72:    */  public static final int AL_REVERB_AIR_ABSORPTION_GAINHF = 11;
/*  73:    */  public static final int AL_REVERB_ROOM_ROLLOFF_FACTOR = 12;
/*  74:    */  public static final int AL_REVERB_DECAY_HFLIMIT = 13;
/*  75:    */  public static final int AL_EAXREVERB_DENSITY = 1;
/*  76:    */  public static final int AL_EAXREVERB_DIFFUSION = 2;
/*  77:    */  public static final int AL_EAXREVERB_GAIN = 3;
/*  78:    */  public static final int AL_EAXREVERB_GAINHF = 4;
/*  79:    */  public static final int AL_EAXREVERB_GAINLF = 5;
/*  80:    */  public static final int AL_EAXREVERB_DECAY_TIME = 6;
/*  81:    */  public static final int AL_EAXREVERB_DECAY_HFRATIO = 7;
/*  82:    */  public static final int AL_EAXREVERB_DECAY_LFRATIO = 8;
/*  83:    */  public static final int AL_EAXREVERB_REFLECTIONS_GAIN = 9;
/*  84:    */  public static final int AL_EAXREVERB_REFLECTIONS_DELAY = 10;
/*  85:    */  public static final int AL_EAXREVERB_REFLECTIONS_PAN = 11;
/*  86:    */  public static final int AL_EAXREVERB_LATE_REVERB_GAIN = 12;
/*  87:    */  public static final int AL_EAXREVERB_LATE_REVERB_DELAY = 13;
/*  88:    */  public static final int AL_EAXREVERB_LATE_REVERB_PAN = 14;
/*  89:    */  public static final int AL_EAXREVERB_ECHO_TIME = 15;
/*  90:    */  public static final int AL_EAXREVERB_ECHO_DEPTH = 16;
/*  91:    */  public static final int AL_EAXREVERB_MODULATION_TIME = 17;
/*  92:    */  public static final int AL_EAXREVERB_MODULATION_DEPTH = 18;
/*  93:    */  public static final int AL_EAXREVERB_AIR_ABSORPTION_GAINHF = 19;
/*  94:    */  public static final int AL_EAXREVERB_HFREFERENCE = 20;
/*  95:    */  public static final int AL_EAXREVERB_LFREFERENCE = 21;
/*  96:    */  public static final int AL_EAXREVERB_ROOM_ROLLOFF_FACTOR = 22;
/*  97:    */  public static final int AL_EAXREVERB_DECAY_HFLIMIT = 23;
/*  98:    */  public static final int AL_CHORUS_WAVEFORM = 1;
/*  99:    */  public static final int AL_CHORUS_PHASE = 2;
/* 100:    */  public static final int AL_CHORUS_RATE = 3;
/* 101:    */  public static final int AL_CHORUS_DEPTH = 4;
/* 102:    */  public static final int AL_CHORUS_FEEDBACK = 5;
/* 103:    */  public static final int AL_CHORUS_DELAY = 6;
/* 104:    */  public static final int AL_DISTORTION_EDGE = 1;
/* 105:    */  public static final int AL_DISTORTION_GAIN = 2;
/* 106:    */  public static final int AL_DISTORTION_LOWPASS_CUTOFF = 3;
/* 107:    */  public static final int AL_DISTORTION_EQCENTER = 4;
/* 108:    */  public static final int AL_DISTORTION_EQBANDWIDTH = 5;
/* 109:    */  public static final int AL_ECHO_DELAY = 1;
/* 110:    */  public static final int AL_ECHO_LRDELAY = 2;
/* 111:    */  public static final int AL_ECHO_DAMPING = 3;
/* 112:    */  public static final int AL_ECHO_FEEDBACK = 4;
/* 113:    */  public static final int AL_ECHO_SPREAD = 5;
/* 114:    */  public static final int AL_FLANGER_WAVEFORM = 1;
/* 115:    */  public static final int AL_FLANGER_PHASE = 2;
/* 116:    */  public static final int AL_FLANGER_RATE = 3;
/* 117:    */  public static final int AL_FLANGER_DEPTH = 4;
/* 118:    */  public static final int AL_FLANGER_FEEDBACK = 5;
/* 119:    */  public static final int AL_FLANGER_DELAY = 6;
/* 120:    */  public static final int AL_FREQUENCY_SHIFTER_FREQUENCY = 1;
/* 121:    */  public static final int AL_FREQUENCY_SHIFTER_LEFT_DIRECTION = 2;
/* 122:    */  public static final int AL_FREQUENCY_SHIFTER_RIGHT_DIRECTION = 3;
/* 123:    */  public static final int AL_VOCAL_MORPHER_PHONEMEA = 1;
/* 124:    */  public static final int AL_VOCAL_MORPHER_PHONEMEA_COARSE_TUNING = 2;
/* 125:    */  public static final int AL_VOCAL_MORPHER_PHONEMEB = 3;
/* 126:    */  public static final int AL_VOCAL_MORPHER_PHONEMEB_COARSE_TUNING = 4;
/* 127:    */  public static final int AL_VOCAL_MORPHER_WAVEFORM = 5;
/* 128:    */  public static final int AL_VOCAL_MORPHER_RATE = 6;
/* 129:    */  public static final int AL_PITCH_SHIFTER_COARSE_TUNE = 1;
/* 130:    */  public static final int AL_PITCH_SHIFTER_FINE_TUNE = 2;
/* 131:    */  public static final int AL_RING_MODULATOR_FREQUENCY = 1;
/* 132:    */  public static final int AL_RING_MODULATOR_HIGHPASS_CUTOFF = 2;
/* 133:    */  public static final int AL_RING_MODULATOR_WAVEFORM = 3;
/* 134:    */  public static final int AL_AUTOWAH_ATTACK_TIME = 1;
/* 135:    */  public static final int AL_AUTOWAH_RELEASE_TIME = 2;
/* 136:    */  public static final int AL_AUTOWAH_RESONANCE = 3;
/* 137:    */  public static final int AL_AUTOWAH_PEAK_GAIN = 4;
/* 138:    */  public static final int AL_COMPRESSOR_ONOFF = 1;
/* 139:    */  public static final int AL_EQUALIZER_LOW_GAIN = 1;
/* 140:    */  public static final int AL_EQUALIZER_LOW_CUTOFF = 2;
/* 141:    */  public static final int AL_EQUALIZER_MID1_GAIN = 3;
/* 142:    */  public static final int AL_EQUALIZER_MID1_CENTER = 4;
/* 143:    */  public static final int AL_EQUALIZER_MID1_WIDTH = 5;
/* 144:    */  public static final int AL_EQUALIZER_MID2_GAIN = 6;
/* 145:    */  public static final int AL_EQUALIZER_MID2_CENTER = 7;
/* 146:    */  public static final int AL_EQUALIZER_MID2_WIDTH = 8;
/* 147:    */  public static final int AL_EQUALIZER_HIGH_GAIN = 9;
/* 148:    */  public static final int AL_EQUALIZER_HIGH_CUTOFF = 10;
/* 149:    */  public static final int AL_EFFECT_FIRST_PARAMETER = 0;
/* 150:    */  public static final int AL_EFFECT_LAST_PARAMETER = 32768;
/* 151:    */  public static final int AL_EFFECT_TYPE = 32769;
/* 152:    */  public static final int AL_EFFECT_NULL = 0;
/* 153:    */  public static final int AL_EFFECT_REVERB = 1;
/* 154:    */  public static final int AL_EFFECT_CHORUS = 2;
/* 155:    */  public static final int AL_EFFECT_DISTORTION = 3;
/* 156:    */  public static final int AL_EFFECT_ECHO = 4;
/* 157:    */  public static final int AL_EFFECT_FLANGER = 5;
/* 158:    */  public static final int AL_EFFECT_FREQUENCY_SHIFTER = 6;
/* 159:    */  public static final int AL_EFFECT_VOCAL_MORPHER = 7;
/* 160:    */  public static final int AL_EFFECT_PITCH_SHIFTER = 8;
/* 161:    */  public static final int AL_EFFECT_RING_MODULATOR = 9;
/* 162:    */  public static final int AL_EFFECT_AUTOWAH = 10;
/* 163:    */  public static final int AL_EFFECT_COMPRESSOR = 11;
/* 164:    */  public static final int AL_EFFECT_EQUALIZER = 12;
/* 165:    */  public static final int AL_EFFECT_EAXREVERB = 32768;
/* 166:    */  public static final int AL_LOWPASS_GAIN = 1;
/* 167:    */  public static final int AL_LOWPASS_GAINHF = 2;
/* 168:    */  public static final int AL_HIGHPASS_GAIN = 1;
/* 169:    */  public static final int AL_HIGHPASS_GAINLF = 2;
/* 170:    */  public static final int AL_BANDPASS_GAIN = 1;
/* 171:    */  public static final int AL_BANDPASS_GAINLF = 2;
/* 172:    */  public static final int AL_BANDPASS_GAINHF = 3;
/* 173:    */  public static final int AL_FILTER_FIRST_PARAMETER = 0;
/* 174:    */  public static final int AL_FILTER_LAST_PARAMETER = 32768;
/* 175:    */  public static final int AL_FILTER_TYPE = 32769;
/* 176:    */  public static final int AL_FILTER_NULL = 0;
/* 177:    */  public static final int AL_FILTER_LOWPASS = 1;
/* 178:    */  public static final int AL_FILTER_HIGHPASS = 2;
/* 179:    */  public static final int AL_FILTER_BANDPASS = 3;
/* 180:    */  public static final float AL_MIN_AIR_ABSORPTION_FACTOR = 0.0F;
/* 181:    */  public static final float AL_MAX_AIR_ABSORPTION_FACTOR = 10.0F;
/* 182:    */  public static final float AL_DEFAULT_AIR_ABSORPTION_FACTOR = 0.0F;
/* 183:    */  public static final float AL_MIN_ROOM_ROLLOFF_FACTOR = 0.0F;
/* 184:    */  public static final float AL_MAX_ROOM_ROLLOFF_FACTOR = 10.0F;
/* 185:    */  public static final float AL_DEFAULT_ROOM_ROLLOFF_FACTOR = 0.0F;
/* 186:    */  public static final float AL_MIN_CONE_OUTER_GAINHF = 0.0F;
/* 187:    */  public static final float AL_MAX_CONE_OUTER_GAINHF = 1.0F;
/* 188:    */  public static final float AL_DEFAULT_CONE_OUTER_GAINHF = 1.0F;
/* 189:    */  public static final int AL_MIN_DIRECT_FILTER_GAINHF_AUTO = 0;
/* 190:    */  public static final int AL_MAX_DIRECT_FILTER_GAINHF_AUTO = 1;
/* 191:    */  public static final int AL_DEFAULT_DIRECT_FILTER_GAINHF_AUTO = 1;
/* 192:    */  public static final int AL_MIN_AUXILIARY_SEND_FILTER_GAIN_AUTO = 0;
/* 193:    */  public static final int AL_MAX_AUXILIARY_SEND_FILTER_GAIN_AUTO = 1;
/* 194:    */  public static final int AL_DEFAULT_AUXILIARY_SEND_FILTER_GAIN_AUTO = 1;
/* 195:    */  public static final int AL_MIN_AUXILIARY_SEND_FILTER_GAINHF_AUTO = 0;
/* 196:    */  public static final int AL_MAX_AUXILIARY_SEND_FILTER_GAINHF_AUTO = 1;
/* 197:    */  public static final int AL_DEFAULT_AUXILIARY_SEND_FILTER_GAINHF_AUTO = 1;
/* 198:    */  public static final float AL_MIN_METERS_PER_UNIT = 1.4E-45F;
/* 199:    */  public static final float AL_MAX_METERS_PER_UNIT = 3.4028235E+38F;
/* 200:    */  public static final float AL_DEFAULT_METERS_PER_UNIT = 1.0F;
/* 201:    */  public static final float AL_REVERB_MIN_DENSITY = 0.0F;
/* 202:    */  public static final float AL_REVERB_MAX_DENSITY = 1.0F;
/* 203:    */  public static final float AL_REVERB_DEFAULT_DENSITY = 1.0F;
/* 204:    */  public static final float AL_REVERB_MIN_DIFFUSION = 0.0F;
/* 205:    */  public static final float AL_REVERB_MAX_DIFFUSION = 1.0F;
/* 206:    */  public static final float AL_REVERB_DEFAULT_DIFFUSION = 1.0F;
/* 207:    */  public static final float AL_REVERB_MIN_GAIN = 0.0F;
/* 208:    */  public static final float AL_REVERB_MAX_GAIN = 1.0F;
/* 209:    */  public static final float AL_REVERB_DEFAULT_GAIN = 0.32F;
/* 210:    */  public static final float AL_REVERB_MIN_GAINHF = 0.0F;
/* 211:    */  public static final float AL_REVERB_MAX_GAINHF = 1.0F;
/* 212:    */  public static final float AL_REVERB_DEFAULT_GAINHF = 0.89F;
/* 213:    */  public static final float AL_REVERB_MIN_DECAY_TIME = 0.1F;
/* 214:    */  public static final float AL_REVERB_MAX_DECAY_TIME = 20.0F;
/* 215:    */  public static final float AL_REVERB_DEFAULT_DECAY_TIME = 1.49F;
/* 216:    */  public static final float AL_REVERB_MIN_DECAY_HFRATIO = 0.1F;
/* 217:    */  public static final float AL_REVERB_MAX_DECAY_HFRATIO = 2.0F;
/* 218:    */  public static final float AL_REVERB_DEFAULT_DECAY_HFRATIO = 0.83F;
/* 219:    */  public static final float AL_REVERB_MIN_REFLECTIONS_GAIN = 0.0F;
/* 220:    */  public static final float AL_REVERB_MAX_REFLECTIONS_GAIN = 3.16F;
/* 221:    */  public static final float AL_REVERB_DEFAULT_REFLECTIONS_GAIN = 0.05F;
/* 222:    */  public static final float AL_REVERB_MIN_REFLECTIONS_DELAY = 0.0F;
/* 223:    */  public static final float AL_REVERB_MAX_REFLECTIONS_DELAY = 0.3F;
/* 224:    */  public static final float AL_REVERB_DEFAULT_REFLECTIONS_DELAY = 0.007F;
/* 225:    */  public static final float AL_REVERB_MIN_LATE_REVERB_GAIN = 0.0F;
/* 226:    */  public static final float AL_REVERB_MAX_LATE_REVERB_GAIN = 10.0F;
/* 227:    */  public static final float AL_REVERB_DEFAULT_LATE_REVERB_GAIN = 1.26F;
/* 228:    */  public static final float AL_REVERB_MIN_LATE_REVERB_DELAY = 0.0F;
/* 229:    */  public static final float AL_REVERB_MAX_LATE_REVERB_DELAY = 0.1F;
/* 230:    */  public static final float AL_REVERB_DEFAULT_LATE_REVERB_DELAY = 0.011F;
/* 231:    */  public static final float AL_REVERB_MIN_AIR_ABSORPTION_GAINHF = 0.892F;
/* 232:    */  public static final float AL_REVERB_MAX_AIR_ABSORPTION_GAINHF = 1.0F;
/* 233:    */  public static final float AL_REVERB_DEFAULT_AIR_ABSORPTION_GAINHF = 0.994F;
/* 234:    */  public static final float AL_REVERB_MIN_ROOM_ROLLOFF_FACTOR = 0.0F;
/* 235:    */  public static final float AL_REVERB_MAX_ROOM_ROLLOFF_FACTOR = 10.0F;
/* 236:    */  public static final float AL_REVERB_DEFAULT_ROOM_ROLLOFF_FACTOR = 0.0F;
/* 237:    */  public static final int AL_REVERB_MIN_DECAY_HFLIMIT = 0;
/* 238:    */  public static final int AL_REVERB_MAX_DECAY_HFLIMIT = 1;
/* 239:    */  public static final int AL_REVERB_DEFAULT_DECAY_HFLIMIT = 1;
/* 240:    */  public static final float AL_EAXREVERB_MIN_DENSITY = 0.0F;
/* 241:    */  public static final float AL_EAXREVERB_MAX_DENSITY = 1.0F;
/* 242:    */  public static final float AL_EAXREVERB_DEFAULT_DENSITY = 1.0F;
/* 243:    */  public static final float AL_EAXREVERB_MIN_DIFFUSION = 0.0F;
/* 244:    */  public static final float AL_EAXREVERB_MAX_DIFFUSION = 1.0F;
/* 245:    */  public static final float AL_EAXREVERB_DEFAULT_DIFFUSION = 1.0F;
/* 246:    */  public static final float AL_EAXREVERB_MIN_GAIN = 0.0F;
/* 247:    */  public static final float AL_EAXREVERB_MAX_GAIN = 1.0F;
/* 248:    */  public static final float AL_EAXREVERB_DEFAULT_GAIN = 0.32F;
/* 249:    */  public static final float AL_EAXREVERB_MIN_GAINHF = 0.0F;
/* 250:    */  public static final float AL_EAXREVERB_MAX_GAINHF = 1.0F;
/* 251:    */  public static final float AL_EAXREVERB_DEFAULT_GAINHF = 0.89F;
/* 252:    */  public static final float AL_EAXREVERB_MIN_GAINLF = 0.0F;
/* 253:    */  public static final float AL_EAXREVERB_MAX_GAINLF = 1.0F;
/* 254:    */  public static final float AL_EAXREVERB_DEFAULT_GAINLF = 1.0F;
/* 255:    */  public static final float AL_EAXREVERB_MIN_DECAY_TIME = 0.1F;
/* 256:    */  public static final float AL_EAXREVERB_MAX_DECAY_TIME = 20.0F;
/* 257:    */  public static final float AL_EAXREVERB_DEFAULT_DECAY_TIME = 1.49F;
/* 258:    */  public static final float AL_EAXREVERB_MIN_DECAY_HFRATIO = 0.1F;
/* 259:    */  public static final float AL_EAXREVERB_MAX_DECAY_HFRATIO = 2.0F;
/* 260:    */  public static final float AL_EAXREVERB_DEFAULT_DECAY_HFRATIO = 0.83F;
/* 261:    */  public static final float AL_EAXREVERB_MIN_DECAY_LFRATIO = 0.1F;
/* 262:    */  public static final float AL_EAXREVERB_MAX_DECAY_LFRATIO = 2.0F;
/* 263:    */  public static final float AL_EAXREVERB_DEFAULT_DECAY_LFRATIO = 1.0F;
/* 264:    */  public static final float AL_EAXREVERB_MIN_REFLECTIONS_GAIN = 0.0F;
/* 265:    */  public static final float AL_EAXREVERB_MAX_REFLECTIONS_GAIN = 3.16F;
/* 266:    */  public static final float AL_EAXREVERB_DEFAULT_REFLECTIONS_GAIN = 0.05F;
/* 267:    */  public static final float AL_EAXREVERB_MIN_REFLECTIONS_DELAY = 0.0F;
/* 268:    */  public static final float AL_EAXREVERB_MAX_REFLECTIONS_DELAY = 0.3F;
/* 269:    */  public static final float AL_EAXREVERB_DEFAULT_REFLECTIONS_DELAY = 0.007F;
/* 270:    */  public static final float AL_EAXREVERB_DEFAULT_REFLECTIONS_PAN_XYZ = 0.0F;
/* 271:    */  public static final float AL_EAXREVERB_MIN_LATE_REVERB_GAIN = 0.0F;
/* 272:    */  public static final float AL_EAXREVERB_MAX_LATE_REVERB_GAIN = 10.0F;
/* 273:    */  public static final float AL_EAXREVERB_DEFAULT_LATE_REVERB_GAIN = 1.26F;
/* 274:    */  public static final float AL_EAXREVERB_MIN_LATE_REVERB_DELAY = 0.0F;
/* 275:    */  public static final float AL_EAXREVERB_MAX_LATE_REVERB_DELAY = 0.1F;
/* 276:    */  public static final float AL_EAXREVERB_DEFAULT_LATE_REVERB_DELAY = 0.011F;
/* 277:    */  public static final float AL_EAXREVERB_DEFAULT_LATE_REVERB_PAN_XYZ = 0.0F;
/* 278:    */  public static final float AL_EAXREVERB_MIN_ECHO_TIME = 0.075F;
/* 279:    */  public static final float AL_EAXREVERB_MAX_ECHO_TIME = 0.25F;
/* 280:    */  public static final float AL_EAXREVERB_DEFAULT_ECHO_TIME = 0.25F;
/* 281:    */  public static final float AL_EAXREVERB_MIN_ECHO_DEPTH = 0.0F;
/* 282:    */  public static final float AL_EAXREVERB_MAX_ECHO_DEPTH = 1.0F;
/* 283:    */  public static final float AL_EAXREVERB_DEFAULT_ECHO_DEPTH = 0.0F;
/* 284:    */  public static final float AL_EAXREVERB_MIN_MODULATION_TIME = 0.04F;
/* 285:    */  public static final float AL_EAXREVERB_MAX_MODULATION_TIME = 4.0F;
/* 286:    */  public static final float AL_EAXREVERB_DEFAULT_MODULATION_TIME = 0.25F;
/* 287:    */  public static final float AL_EAXREVERB_MIN_MODULATION_DEPTH = 0.0F;
/* 288:    */  public static final float AL_EAXREVERB_MAX_MODULATION_DEPTH = 1.0F;
/* 289:    */  public static final float AL_EAXREVERB_DEFAULT_MODULATION_DEPTH = 0.0F;
/* 290:    */  public static final float AL_EAXREVERB_MIN_AIR_ABSORPTION_GAINHF = 0.892F;
/* 291:    */  public static final float AL_EAXREVERB_MAX_AIR_ABSORPTION_GAINHF = 1.0F;
/* 292:    */  public static final float AL_EAXREVERB_DEFAULT_AIR_ABSORPTION_GAINHF = 0.994F;
/* 293:    */  public static final float AL_EAXREVERB_MIN_HFREFERENCE = 1000.0F;
/* 294:    */  public static final float AL_EAXREVERB_MAX_HFREFERENCE = 20000.0F;
/* 295:    */  public static final float AL_EAXREVERB_DEFAULT_HFREFERENCE = 5000.0F;
/* 296:    */  public static final float AL_EAXREVERB_MIN_LFREFERENCE = 20.0F;
/* 297:    */  public static final float AL_EAXREVERB_MAX_LFREFERENCE = 1000.0F;
/* 298:    */  public static final float AL_EAXREVERB_DEFAULT_LFREFERENCE = 250.0F;
/* 299:    */  public static final float AL_EAXREVERB_MIN_ROOM_ROLLOFF_FACTOR = 0.0F;
/* 300:    */  public static final float AL_EAXREVERB_MAX_ROOM_ROLLOFF_FACTOR = 10.0F;
/* 301:    */  public static final float AL_EAXREVERB_DEFAULT_ROOM_ROLLOFF_FACTOR = 0.0F;
/* 302:    */  public static final int AL_EAXREVERB_MIN_DECAY_HFLIMIT = 0;
/* 303:    */  public static final int AL_EAXREVERB_MAX_DECAY_HFLIMIT = 1;
/* 304:    */  public static final int AL_EAXREVERB_DEFAULT_DECAY_HFLIMIT = 1;
/* 305:    */  public static final int AL_CHORUS_WAVEFORM_SINUSOID = 0;
/* 306:    */  public static final int AL_CHORUS_WAVEFORM_TRIANGLE = 1;
/* 307:    */  public static final int AL_CHORUS_MIN_WAVEFORM = 0;
/* 308:    */  public static final int AL_CHORUS_MAX_WAVEFORM = 1;
/* 309:    */  public static final int AL_CHORUS_DEFAULT_WAVEFORM = 1;
/* 310:    */  public static final int AL_CHORUS_MIN_PHASE = -180;
/* 311:    */  public static final int AL_CHORUS_MAX_PHASE = 180;
/* 312:    */  public static final int AL_CHORUS_DEFAULT_PHASE = 90;
/* 313:    */  public static final float AL_CHORUS_MIN_RATE = 0.0F;
/* 314:    */  public static final float AL_CHORUS_MAX_RATE = 10.0F;
/* 315:    */  public static final float AL_CHORUS_DEFAULT_RATE = 1.1F;
/* 316:    */  public static final float AL_CHORUS_MIN_DEPTH = 0.0F;
/* 317:    */  public static final float AL_CHORUS_MAX_DEPTH = 1.0F;
/* 318:    */  public static final float AL_CHORUS_DEFAULT_DEPTH = 0.1F;
/* 319:    */  public static final float AL_CHORUS_MIN_FEEDBACK = -1.0F;
/* 320:    */  public static final float AL_CHORUS_MAX_FEEDBACK = 1.0F;
/* 321:    */  public static final float AL_CHORUS_DEFAULT_FEEDBACK = 0.25F;
/* 322:    */  public static final float AL_CHORUS_MIN_DELAY = 0.0F;
/* 323:    */  public static final float AL_CHORUS_MAX_DELAY = 0.016F;
/* 324:    */  public static final float AL_CHORUS_DEFAULT_DELAY = 0.016F;
/* 325:    */  public static final float AL_DISTORTION_MIN_EDGE = 0.0F;
/* 326:    */  public static final float AL_DISTORTION_MAX_EDGE = 1.0F;
/* 327:    */  public static final float AL_DISTORTION_DEFAULT_EDGE = 0.2F;
/* 328:    */  public static final float AL_DISTORTION_MIN_GAIN = 0.01F;
/* 329:    */  public static final float AL_DISTORTION_MAX_GAIN = 1.0F;
/* 330:    */  public static final float AL_DISTORTION_DEFAULT_GAIN = 0.05F;
/* 331:    */  public static final float AL_DISTORTION_MIN_LOWPASS_CUTOFF = 80.0F;
/* 332:    */  public static final float AL_DISTORTION_MAX_LOWPASS_CUTOFF = 24000.0F;
/* 333:    */  public static final float AL_DISTORTION_DEFAULT_LOWPASS_CUTOFF = 8000.0F;
/* 334:    */  public static final float AL_DISTORTION_MIN_EQCENTER = 80.0F;
/* 335:    */  public static final float AL_DISTORTION_MAX_EQCENTER = 24000.0F;
/* 336:    */  public static final float AL_DISTORTION_DEFAULT_EQCENTER = 3600.0F;
/* 337:    */  public static final float AL_DISTORTION_MIN_EQBANDWIDTH = 80.0F;
/* 338:    */  public static final float AL_DISTORTION_MAX_EQBANDWIDTH = 24000.0F;
/* 339:    */  public static final float AL_DISTORTION_DEFAULT_EQBANDWIDTH = 3600.0F;
/* 340:    */  public static final float AL_ECHO_MIN_DELAY = 0.0F;
/* 341:    */  public static final float AL_ECHO_MAX_DELAY = 0.207F;
/* 342:    */  public static final float AL_ECHO_DEFAULT_DELAY = 0.1F;
/* 343:    */  public static final float AL_ECHO_MIN_LRDELAY = 0.0F;
/* 344:    */  public static final float AL_ECHO_MAX_LRDELAY = 0.404F;
/* 345:    */  public static final float AL_ECHO_DEFAULT_LRDELAY = 0.1F;
/* 346:    */  public static final float AL_ECHO_MIN_DAMPING = 0.0F;
/* 347:    */  public static final float AL_ECHO_MAX_DAMPING = 0.99F;
/* 348:    */  public static final float AL_ECHO_DEFAULT_DAMPING = 0.5F;
/* 349:    */  public static final float AL_ECHO_MIN_FEEDBACK = 0.0F;
/* 350:    */  public static final float AL_ECHO_MAX_FEEDBACK = 1.0F;
/* 351:    */  public static final float AL_ECHO_DEFAULT_FEEDBACK = 0.5F;
/* 352:    */  public static final float AL_ECHO_MIN_SPREAD = -1.0F;
/* 353:    */  public static final float AL_ECHO_MAX_SPREAD = 1.0F;
/* 354:    */  public static final float AL_ECHO_DEFAULT_SPREAD = -1.0F;
/* 355:    */  public static final int AL_FLANGER_WAVEFORM_SINUSOID = 0;
/* 356:    */  public static final int AL_FLANGER_WAVEFORM_TRIANGLE = 1;
/* 357:    */  public static final int AL_FLANGER_MIN_WAVEFORM = 0;
/* 358:    */  public static final int AL_FLANGER_MAX_WAVEFORM = 1;
/* 359:    */  public static final int AL_FLANGER_DEFAULT_WAVEFORM = 1;
/* 360:    */  public static final int AL_FLANGER_MIN_PHASE = -180;
/* 361:    */  public static final int AL_FLANGER_MAX_PHASE = 180;
/* 362:    */  public static final int AL_FLANGER_DEFAULT_PHASE = 0;
/* 363:    */  public static final float AL_FLANGER_MIN_RATE = 0.0F;
/* 364:    */  public static final float AL_FLANGER_MAX_RATE = 10.0F;
/* 365:    */  public static final float AL_FLANGER_DEFAULT_RATE = 0.27F;
/* 366:    */  public static final float AL_FLANGER_MIN_DEPTH = 0.0F;
/* 367:    */  public static final float AL_FLANGER_MAX_DEPTH = 1.0F;
/* 368:    */  public static final float AL_FLANGER_DEFAULT_DEPTH = 1.0F;
/* 369:    */  public static final float AL_FLANGER_MIN_FEEDBACK = -1.0F;
/* 370:    */  public static final float AL_FLANGER_MAX_FEEDBACK = 1.0F;
/* 371:    */  public static final float AL_FLANGER_DEFAULT_FEEDBACK = -0.5F;
/* 372:    */  public static final float AL_FLANGER_MIN_DELAY = 0.0F;
/* 373:    */  public static final float AL_FLANGER_MAX_DELAY = 0.004F;
/* 374:    */  public static final float AL_FLANGER_DEFAULT_DELAY = 0.002F;
/* 375:    */  public static final float AL_FREQUENCY_SHIFTER_MIN_FREQUENCY = 0.0F;
/* 376:    */  public static final float AL_FREQUENCY_SHIFTER_MAX_FREQUENCY = 24000.0F;
/* 377:    */  public static final float AL_FREQUENCY_SHIFTER_DEFAULT_FREQUENCY = 0.0F;
/* 378:    */  public static final int AL_FREQUENCY_SHIFTER_MIN_LEFT_DIRECTION = 0;
/* 379:    */  public static final int AL_FREQUENCY_SHIFTER_MAX_LEFT_DIRECTION = 2;
/* 380:    */  public static final int AL_FREQUENCY_SHIFTER_DEFAULT_LEFT_DIRECTION = 0;
/* 381:    */  public static final int AL_FREQUENCY_SHIFTER_DIRECTION_DOWN = 0;
/* 382:    */  public static final int AL_FREQUENCY_SHIFTER_DIRECTION_UP = 1;
/* 383:    */  public static final int AL_FREQUENCY_SHIFTER_DIRECTION_OFF = 2;
/* 384:    */  public static final int AL_FREQUENCY_SHIFTER_MIN_RIGHT_DIRECTION = 0;
/* 385:    */  public static final int AL_FREQUENCY_SHIFTER_MAX_RIGHT_DIRECTION = 2;
/* 386:    */  public static final int AL_FREQUENCY_SHIFTER_DEFAULT_RIGHT_DIRECTION = 0;
/* 387:    */  public static final int AL_VOCAL_MORPHER_MIN_PHONEMEA = 0;
/* 388:    */  public static final int AL_VOCAL_MORPHER_MAX_PHONEMEA = 29;
/* 389:    */  public static final int AL_VOCAL_MORPHER_DEFAULT_PHONEMEA = 0;
/* 390:    */  public static final int AL_VOCAL_MORPHER_MIN_PHONEMEA_COARSE_TUNING = -24;
/* 391:    */  public static final int AL_VOCAL_MORPHER_MAX_PHONEMEA_COARSE_TUNING = 24;
/* 392:    */  public static final int AL_VOCAL_MORPHER_DEFAULT_PHONEMEA_COARSE_TUNING = 0;
/* 393:    */  public static final int AL_VOCAL_MORPHER_MIN_PHONEMEB = 0;
/* 394:    */  public static final int AL_VOCAL_MORPHER_MAX_PHONEMEB = 29;
/* 395:    */  public static final int AL_VOCAL_MORPHER_DEFAULT_PHONEMEB = 10;
/* 396:    */  public static final int AL_VOCAL_MORPHER_MIN_PHONEMEB_COARSE_TUNING = -24;
/* 397:    */  public static final int AL_VOCAL_MORPHER_MAX_PHONEMEB_COARSE_TUNING = 24;
/* 398:    */  public static final int AL_VOCAL_MORPHER_DEFAULT_PHONEMEB_COARSE_TUNING = 0;
/* 399:    */  public static final int AL_VOCAL_MORPHER_PHONEME_A = 0;
/* 400:    */  public static final int AL_VOCAL_MORPHER_PHONEME_E = 1;
/* 401:    */  public static final int AL_VOCAL_MORPHER_PHONEME_I = 2;
/* 402:    */  public static final int AL_VOCAL_MORPHER_PHONEME_O = 3;
/* 403:    */  public static final int AL_VOCAL_MORPHER_PHONEME_U = 4;
/* 404:    */  public static final int AL_VOCAL_MORPHER_PHONEME_AA = 5;
/* 405:    */  public static final int AL_VOCAL_MORPHER_PHONEME_AE = 6;
/* 406:    */  public static final int AL_VOCAL_MORPHER_PHONEME_AH = 7;
/* 407:    */  public static final int AL_VOCAL_MORPHER_PHONEME_AO = 8;
/* 408:    */  public static final int AL_VOCAL_MORPHER_PHONEME_EH = 9;
/* 409:    */  public static final int AL_VOCAL_MORPHER_PHONEME_ER = 10;
/* 410:    */  public static final int AL_VOCAL_MORPHER_PHONEME_IH = 11;
/* 411:    */  public static final int AL_VOCAL_MORPHER_PHONEME_IY = 12;
/* 412:    */  public static final int AL_VOCAL_MORPHER_PHONEME_UH = 13;
/* 413:    */  public static final int AL_VOCAL_MORPHER_PHONEME_UW = 14;
/* 414:    */  public static final int AL_VOCAL_MORPHER_PHONEME_B = 15;
/* 415:    */  public static final int AL_VOCAL_MORPHER_PHONEME_D = 16;
/* 416:    */  public static final int AL_VOCAL_MORPHER_PHONEME_F = 17;
/* 417:    */  public static final int AL_VOCAL_MORPHER_PHONEME_G = 18;
/* 418:    */  public static final int AL_VOCAL_MORPHER_PHONEME_J = 19;
/* 419:    */  public static final int AL_VOCAL_MORPHER_PHONEME_K = 20;
/* 420:    */  public static final int AL_VOCAL_MORPHER_PHONEME_L = 21;
/* 421:    */  public static final int AL_VOCAL_MORPHER_PHONEME_M = 22;
/* 422:    */  public static final int AL_VOCAL_MORPHER_PHONEME_N = 23;
/* 423:    */  public static final int AL_VOCAL_MORPHER_PHONEME_P = 24;
/* 424:    */  public static final int AL_VOCAL_MORPHER_PHONEME_R = 25;
/* 425:    */  public static final int AL_VOCAL_MORPHER_PHONEME_S = 26;
/* 426:    */  public static final int AL_VOCAL_MORPHER_PHONEME_T = 27;
/* 427:    */  public static final int AL_VOCAL_MORPHER_PHONEME_V = 28;
/* 428:    */  public static final int AL_VOCAL_MORPHER_PHONEME_Z = 29;
/* 429:    */  public static final int AL_VOCAL_MORPHER_WAVEFORM_SINUSOID = 0;
/* 430:    */  public static final int AL_VOCAL_MORPHER_WAVEFORM_TRIANGLE = 1;
/* 431:    */  public static final int AL_VOCAL_MORPHER_WAVEFORM_SAWTOOTH = 2;
/* 432:    */  public static final int AL_VOCAL_MORPHER_MIN_WAVEFORM = 0;
/* 433:    */  public static final int AL_VOCAL_MORPHER_MAX_WAVEFORM = 2;
/* 434:    */  public static final int AL_VOCAL_MORPHER_DEFAULT_WAVEFORM = 0;
/* 435:    */  public static final float AL_VOCAL_MORPHER_MIN_RATE = 0.0F;
/* 436:    */  public static final float AL_VOCAL_MORPHER_MAX_RATE = 10.0F;
/* 437:    */  public static final float AL_VOCAL_MORPHER_DEFAULT_RATE = 1.41F;
/* 438:    */  public static final int AL_PITCH_SHIFTER_MIN_COARSE_TUNE = -12;
/* 439:    */  public static final int AL_PITCH_SHIFTER_MAX_COARSE_TUNE = 12;
/* 440:    */  public static final int AL_PITCH_SHIFTER_DEFAULT_COARSE_TUNE = 12;
/* 441:    */  public static final int AL_PITCH_SHIFTER_MIN_FINE_TUNE = -50;
/* 442:    */  public static final int AL_PITCH_SHIFTER_MAX_FINE_TUNE = 50;
/* 443:    */  public static final int AL_PITCH_SHIFTER_DEFAULT_FINE_TUNE = 0;
/* 444:    */  public static final float AL_RING_MODULATOR_MIN_FREQUENCY = 0.0F;
/* 445:    */  public static final float AL_RING_MODULATOR_MAX_FREQUENCY = 8000.0F;
/* 446:    */  public static final float AL_RING_MODULATOR_DEFAULT_FREQUENCY = 440.0F;
/* 447:    */  public static final float AL_RING_MODULATOR_MIN_HIGHPASS_CUTOFF = 0.0F;
/* 448:    */  public static final float AL_RING_MODULATOR_MAX_HIGHPASS_CUTOFF = 24000.0F;
/* 449:    */  public static final float AL_RING_MODULATOR_DEFAULT_HIGHPASS_CUTOFF = 800.0F;
/* 450:    */  public static final int AL_RING_MODULATOR_SINUSOID = 0;
/* 451:    */  public static final int AL_RING_MODULATOR_SAWTOOTH = 1;
/* 452:    */  public static final int AL_RING_MODULATOR_SQUARE = 2;
/* 453:    */  public static final int AL_RING_MODULATOR_MIN_WAVEFORM = 0;
/* 454:    */  public static final int AL_RING_MODULATOR_MAX_WAVEFORM = 2;
/* 455:    */  public static final int AL_RING_MODULATOR_DEFAULT_WAVEFORM = 0;
/* 456:    */  public static final float AL_AUTOWAH_MIN_ATTACK_TIME = 1.0E-004F;
/* 457:    */  public static final float AL_AUTOWAH_MAX_ATTACK_TIME = 1.0F;
/* 458:    */  public static final float AL_AUTOWAH_DEFAULT_ATTACK_TIME = 0.06F;
/* 459:    */  public static final float AL_AUTOWAH_MIN_RELEASE_TIME = 1.0E-004F;
/* 460:    */  public static final float AL_AUTOWAH_MAX_RELEASE_TIME = 1.0F;
/* 461:    */  public static final float AL_AUTOWAH_DEFAULT_RELEASE_TIME = 0.06F;
/* 462:    */  public static final float AL_AUTOWAH_MIN_RESONANCE = 2.0F;
/* 463:    */  public static final float AL_AUTOWAH_MAX_RESONANCE = 1000.0F;
/* 464:    */  public static final float AL_AUTOWAH_DEFAULT_RESONANCE = 1000.0F;
/* 465:    */  public static final float AL_AUTOWAH_MIN_PEAK_GAIN = 3.0E-005F;
/* 466:    */  public static final float AL_AUTOWAH_MAX_PEAK_GAIN = 31621.0F;
/* 467:    */  public static final float AL_AUTOWAH_DEFAULT_PEAK_GAIN = 11.22F;
/* 468:    */  public static final int AL_COMPRESSOR_MIN_ONOFF = 0;
/* 469:    */  public static final int AL_COMPRESSOR_MAX_ONOFF = 1;
/* 470:    */  public static final int AL_COMPRESSOR_DEFAULT_ONOFF = 1;
/* 471:    */  public static final float AL_EQUALIZER_MIN_LOW_GAIN = 0.126F;
/* 472:    */  public static final float AL_EQUALIZER_MAX_LOW_GAIN = 7.943F;
/* 473:    */  public static final float AL_EQUALIZER_DEFAULT_LOW_GAIN = 1.0F;
/* 474:    */  public static final float AL_EQUALIZER_MIN_LOW_CUTOFF = 50.0F;
/* 475:    */  public static final float AL_EQUALIZER_MAX_LOW_CUTOFF = 800.0F;
/* 476:    */  public static final float AL_EQUALIZER_DEFAULT_LOW_CUTOFF = 200.0F;
/* 477:    */  public static final float AL_EQUALIZER_MIN_MID1_GAIN = 0.126F;
/* 478:    */  public static final float AL_EQUALIZER_MAX_MID1_GAIN = 7.943F;
/* 479:    */  public static final float AL_EQUALIZER_DEFAULT_MID1_GAIN = 1.0F;
/* 480:    */  public static final float AL_EQUALIZER_MIN_MID1_CENTER = 200.0F;
/* 481:    */  public static final float AL_EQUALIZER_MAX_MID1_CENTER = 3000.0F;
/* 482:    */  public static final float AL_EQUALIZER_DEFAULT_MID1_CENTER = 500.0F;
/* 483:    */  public static final float AL_EQUALIZER_MIN_MID1_WIDTH = 0.01F;
/* 484:    */  public static final float AL_EQUALIZER_MAX_MID1_WIDTH = 1.0F;
/* 485:    */  public static final float AL_EQUALIZER_DEFAULT_MID1_WIDTH = 1.0F;
/* 486:    */  public static final float AL_EQUALIZER_MIN_MID2_GAIN = 0.126F;
/* 487:    */  public static final float AL_EQUALIZER_MAX_MID2_GAIN = 7.943F;
/* 488:    */  public static final float AL_EQUALIZER_DEFAULT_MID2_GAIN = 1.0F;
/* 489:    */  public static final float AL_EQUALIZER_MIN_MID2_CENTER = 1000.0F;
/* 490:    */  public static final float AL_EQUALIZER_MAX_MID2_CENTER = 8000.0F;
/* 491:    */  public static final float AL_EQUALIZER_DEFAULT_MID2_CENTER = 3000.0F;
/* 492:    */  public static final float AL_EQUALIZER_MIN_MID2_WIDTH = 0.01F;
/* 493:    */  public static final float AL_EQUALIZER_MAX_MID2_WIDTH = 1.0F;
/* 494:    */  public static final float AL_EQUALIZER_DEFAULT_MID2_WIDTH = 1.0F;
/* 495:    */  public static final float AL_EQUALIZER_MIN_HIGH_GAIN = 0.126F;
/* 496:    */  public static final float AL_EQUALIZER_MAX_HIGH_GAIN = 7.943F;
/* 497:    */  public static final float AL_EQUALIZER_DEFAULT_HIGH_GAIN = 1.0F;
/* 498:    */  public static final float AL_EQUALIZER_MIN_HIGH_CUTOFF = 4000.0F;
/* 499:    */  public static final float AL_EQUALIZER_MAX_HIGH_CUTOFF = 16000.0F;
/* 500:    */  public static final float AL_EQUALIZER_DEFAULT_HIGH_CUTOFF = 6000.0F;
/* 501:    */  public static final float LOWPASS_MIN_GAIN = 0.0F;
/* 502:    */  public static final float LOWPASS_MAX_GAIN = 1.0F;
/* 503:    */  public static final float LOWPASS_DEFAULT_GAIN = 1.0F;
/* 504:    */  public static final float LOWPASS_MIN_GAINHF = 0.0F;
/* 505:    */  public static final float LOWPASS_MAX_GAINHF = 1.0F;
/* 506:    */  public static final float LOWPASS_DEFAULT_GAINHF = 1.0F;
/* 507:    */  public static final float HIGHPASS_MIN_GAIN = 0.0F;
/* 508:    */  public static final float HIGHPASS_MAX_GAIN = 1.0F;
/* 509:    */  public static final float HIGHPASS_DEFAULT_GAIN = 1.0F;
/* 510:    */  public static final float HIGHPASS_MIN_GAINLF = 0.0F;
/* 511:    */  public static final float HIGHPASS_MAX_GAINLF = 1.0F;
/* 512:    */  public static final float HIGHPASS_DEFAULT_GAINLF = 1.0F;
/* 513:    */  public static final float BANDPASS_MIN_GAIN = 0.0F;
/* 514:    */  public static final float BANDPASS_MAX_GAIN = 1.0F;
/* 515:    */  public static final float BANDPASS_DEFAULT_GAIN = 1.0F;
/* 516:    */  public static final float BANDPASS_MIN_GAINHF = 0.0F;
/* 517:    */  public static final float BANDPASS_MAX_GAINHF = 1.0F;
/* 518:    */  public static final float BANDPASS_DEFAULT_GAINHF = 1.0F;
/* 519:    */  public static final float BANDPASS_MIN_GAINLF = 0.0F;
/* 520:    */  public static final float BANDPASS_MAX_GAINLF = 1.0F;
/* 521:    */  public static final float BANDPASS_DEFAULT_GAINLF = 1.0F;
/* 522:    */  
/* 523:    */  static native void initNativeStubs()
/* 524:    */    throws LWJGLException;
/* 525:    */  
/* 526:    */  public static void alGenAuxiliaryEffectSlots(IntBuffer auxiliaryeffectslots)
/* 527:    */  {
/* 528:528 */    BufferChecks.checkDirect(auxiliaryeffectslots);
/* 529:529 */    nalGenAuxiliaryEffectSlots(auxiliaryeffectslots.remaining(), MemoryUtil.getAddress(auxiliaryeffectslots));
/* 530:    */  }
/* 531:    */  
/* 532:    */  static native void nalGenAuxiliaryEffectSlots(int paramInt, long paramLong);
/* 533:    */  
/* 534:    */  public static int alGenAuxiliaryEffectSlots() {
/* 535:535 */    int __result = nalGenAuxiliaryEffectSlots2(1);
/* 536:536 */    return __result;
/* 537:    */  }
/* 538:    */  
/* 539:    */  static native int nalGenAuxiliaryEffectSlots2(int paramInt);
/* 540:    */  
/* 541:541 */  public static void alDeleteAuxiliaryEffectSlots(IntBuffer auxiliaryeffectslots) { BufferChecks.checkDirect(auxiliaryeffectslots);
/* 542:542 */    nalDeleteAuxiliaryEffectSlots(auxiliaryeffectslots.remaining(), MemoryUtil.getAddress(auxiliaryeffectslots));
/* 543:    */  }
/* 544:    */  
/* 546:    */  static native void nalDeleteAuxiliaryEffectSlots(int paramInt, long paramLong);
/* 547:    */  
/* 548:548 */  public static void alDeleteAuxiliaryEffectSlots(int auxiliaryeffectslot) { nalDeleteAuxiliaryEffectSlots2(1, auxiliaryeffectslot); }
/* 549:    */  
/* 550:    */  static native void nalDeleteAuxiliaryEffectSlots2(int paramInt1, int paramInt2);
/* 551:    */  
/* 552:    */  public static boolean alIsAuxiliaryEffectSlot(int auxiliaryeffectslot) {
/* 553:553 */    boolean __result = nalIsAuxiliaryEffectSlot(auxiliaryeffectslot);
/* 554:554 */    return __result;
/* 555:    */  }
/* 556:    */  
/* 557:    */  static native boolean nalIsAuxiliaryEffectSlot(int paramInt);
/* 558:    */  
/* 559:559 */  public static void alAuxiliaryEffectSloti(int auxiliaryeffectslot, int param, int value) { nalAuxiliaryEffectSloti(auxiliaryeffectslot, param, value); }
/* 560:    */  
/* 561:    */  static native void nalAuxiliaryEffectSloti(int paramInt1, int paramInt2, int paramInt3);
/* 562:    */  
/* 563:    */  public static void alAuxiliaryEffectSlot(int auxiliaryeffectslot, int param, IntBuffer values) {
/* 564:564 */    BufferChecks.checkBuffer(values, 1);
/* 565:565 */    nalAuxiliaryEffectSlotiv(auxiliaryeffectslot, param, MemoryUtil.getAddress(values));
/* 566:    */  }
/* 567:    */  
/* 568:    */  static native void nalAuxiliaryEffectSlotiv(int paramInt1, int paramInt2, long paramLong);
/* 569:    */  
/* 570:570 */  public static void alAuxiliaryEffectSlotf(int auxiliaryeffectslot, int param, float value) { nalAuxiliaryEffectSlotf(auxiliaryeffectslot, param, value); }
/* 571:    */  
/* 572:    */  static native void nalAuxiliaryEffectSlotf(int paramInt1, int paramInt2, float paramFloat);
/* 573:    */  
/* 574:    */  public static void alAuxiliaryEffectSlot(int auxiliaryeffectslot, int param, FloatBuffer values) {
/* 575:575 */    BufferChecks.checkBuffer(values, 1);
/* 576:576 */    nalAuxiliaryEffectSlotfv(auxiliaryeffectslot, param, MemoryUtil.getAddress(values));
/* 577:    */  }
/* 578:    */  
/* 579:    */  static native void nalAuxiliaryEffectSlotfv(int paramInt1, int paramInt2, long paramLong);
/* 580:    */  
/* 581:581 */  public static int alGetAuxiliaryEffectSloti(int auxiliaryeffectslot, int param) { int __result = nalGetAuxiliaryEffectSloti(auxiliaryeffectslot, param);
/* 582:582 */    return __result;
/* 583:    */  }
/* 584:    */  
/* 585:    */  static native int nalGetAuxiliaryEffectSloti(int paramInt1, int paramInt2);
/* 586:    */  
/* 587:587 */  public static void alGetAuxiliaryEffectSlot(int auxiliaryeffectslot, int param, IntBuffer intdata) { BufferChecks.checkBuffer(intdata, 1);
/* 588:588 */    nalGetAuxiliaryEffectSlotiv(auxiliaryeffectslot, param, MemoryUtil.getAddress(intdata));
/* 589:    */  }
/* 590:    */  
/* 591:    */  static native void nalGetAuxiliaryEffectSlotiv(int paramInt1, int paramInt2, long paramLong);
/* 592:    */  
/* 593:593 */  public static float alGetAuxiliaryEffectSlotf(int auxiliaryeffectslot, int param) { float __result = nalGetAuxiliaryEffectSlotf(auxiliaryeffectslot, param);
/* 594:594 */    return __result;
/* 595:    */  }
/* 596:    */  
/* 597:    */  static native float nalGetAuxiliaryEffectSlotf(int paramInt1, int paramInt2);
/* 598:    */  
/* 599:599 */  public static void alGetAuxiliaryEffectSlot(int auxiliaryeffectslot, int param, FloatBuffer floatdata) { BufferChecks.checkBuffer(floatdata, 1);
/* 600:600 */    nalGetAuxiliaryEffectSlotfv(auxiliaryeffectslot, param, MemoryUtil.getAddress(floatdata));
/* 601:    */  }
/* 602:    */  
/* 603:    */  static native void nalGetAuxiliaryEffectSlotfv(int paramInt1, int paramInt2, long paramLong);
/* 604:    */  
/* 605:605 */  public static void alGenEffects(IntBuffer effects) { BufferChecks.checkDirect(effects);
/* 606:606 */    nalGenEffects(effects.remaining(), MemoryUtil.getAddress(effects));
/* 607:    */  }
/* 608:    */  
/* 609:    */  static native void nalGenEffects(int paramInt, long paramLong);
/* 610:    */  
/* 611:    */  public static int alGenEffects() {
/* 612:612 */    int __result = nalGenEffects2(1);
/* 613:613 */    return __result;
/* 614:    */  }
/* 615:    */  
/* 616:    */  static native int nalGenEffects2(int paramInt);
/* 617:    */  
/* 618:618 */  public static void alDeleteEffects(IntBuffer effects) { BufferChecks.checkDirect(effects);
/* 619:619 */    nalDeleteEffects(effects.remaining(), MemoryUtil.getAddress(effects));
/* 620:    */  }
/* 621:    */  
/* 623:    */  static native void nalDeleteEffects(int paramInt, long paramLong);
/* 624:    */  
/* 625:625 */  public static void alDeleteEffects(int effect) { nalDeleteEffects2(1, effect); }
/* 626:    */  
/* 627:    */  static native void nalDeleteEffects2(int paramInt1, int paramInt2);
/* 628:    */  
/* 629:    */  public static boolean alIsEffect(int effect) {
/* 630:630 */    boolean __result = nalIsEffect(effect);
/* 631:631 */    return __result;
/* 632:    */  }
/* 633:    */  
/* 634:    */  static native boolean nalIsEffect(int paramInt);
/* 635:    */  
/* 636:636 */  public static void alEffecti(int effect, int param, int value) { nalEffecti(effect, param, value); }
/* 637:    */  
/* 638:    */  static native void nalEffecti(int paramInt1, int paramInt2, int paramInt3);
/* 639:    */  
/* 640:    */  public static void alEffect(int effect, int param, IntBuffer values) {
/* 641:641 */    BufferChecks.checkBuffer(values, 1);
/* 642:642 */    nalEffectiv(effect, param, MemoryUtil.getAddress(values));
/* 643:    */  }
/* 644:    */  
/* 645:    */  static native void nalEffectiv(int paramInt1, int paramInt2, long paramLong);
/* 646:    */  
/* 647:647 */  public static void alEffectf(int effect, int param, float value) { nalEffectf(effect, param, value); }
/* 648:    */  
/* 649:    */  static native void nalEffectf(int paramInt1, int paramInt2, float paramFloat);
/* 650:    */  
/* 651:    */  public static void alEffect(int effect, int param, FloatBuffer values) {
/* 652:652 */    BufferChecks.checkBuffer(values, 1);
/* 653:653 */    nalEffectfv(effect, param, MemoryUtil.getAddress(values));
/* 654:    */  }
/* 655:    */  
/* 656:    */  static native void nalEffectfv(int paramInt1, int paramInt2, long paramLong);
/* 657:    */  
/* 658:658 */  public static int alGetEffecti(int effect, int param) { int __result = nalGetEffecti(effect, param);
/* 659:659 */    return __result;
/* 660:    */  }
/* 661:    */  
/* 662:    */  static native int nalGetEffecti(int paramInt1, int paramInt2);
/* 663:    */  
/* 664:664 */  public static void alGetEffect(int effect, int param, IntBuffer intdata) { BufferChecks.checkBuffer(intdata, 1);
/* 665:665 */    nalGetEffectiv(effect, param, MemoryUtil.getAddress(intdata));
/* 666:    */  }
/* 667:    */  
/* 668:    */  static native void nalGetEffectiv(int paramInt1, int paramInt2, long paramLong);
/* 669:    */  
/* 670:670 */  public static float alGetEffectf(int effect, int param) { float __result = nalGetEffectf(effect, param);
/* 671:671 */    return __result;
/* 672:    */  }
/* 673:    */  
/* 674:    */  static native float nalGetEffectf(int paramInt1, int paramInt2);
/* 675:    */  
/* 676:676 */  public static void alGetEffect(int effect, int param, FloatBuffer floatdata) { BufferChecks.checkBuffer(floatdata, 1);
/* 677:677 */    nalGetEffectfv(effect, param, MemoryUtil.getAddress(floatdata));
/* 678:    */  }
/* 679:    */  
/* 680:    */  static native void nalGetEffectfv(int paramInt1, int paramInt2, long paramLong);
/* 681:    */  
/* 682:682 */  public static void alGenFilters(IntBuffer filters) { BufferChecks.checkDirect(filters);
/* 683:683 */    nalGenFilters(filters.remaining(), MemoryUtil.getAddress(filters));
/* 684:    */  }
/* 685:    */  
/* 686:    */  static native void nalGenFilters(int paramInt, long paramLong);
/* 687:    */  
/* 688:    */  public static int alGenFilters() {
/* 689:689 */    int __result = nalGenFilters2(1);
/* 690:690 */    return __result;
/* 691:    */  }
/* 692:    */  
/* 693:    */  static native int nalGenFilters2(int paramInt);
/* 694:    */  
/* 695:695 */  public static void alDeleteFilters(IntBuffer filters) { BufferChecks.checkDirect(filters);
/* 696:696 */    nalDeleteFilters(filters.remaining(), MemoryUtil.getAddress(filters));
/* 697:    */  }
/* 698:    */  
/* 700:    */  static native void nalDeleteFilters(int paramInt, long paramLong);
/* 701:    */  
/* 702:702 */  public static void alDeleteFilters(int filter) { nalDeleteFilters2(1, filter); }
/* 703:    */  
/* 704:    */  static native void nalDeleteFilters2(int paramInt1, int paramInt2);
/* 705:    */  
/* 706:    */  public static boolean alIsFilter(int filter) {
/* 707:707 */    boolean __result = nalIsFilter(filter);
/* 708:708 */    return __result;
/* 709:    */  }
/* 710:    */  
/* 711:    */  static native boolean nalIsFilter(int paramInt);
/* 712:    */  
/* 713:713 */  public static void alFilteri(int filter, int param, int value) { nalFilteri(filter, param, value); }
/* 714:    */  
/* 715:    */  static native void nalFilteri(int paramInt1, int paramInt2, int paramInt3);
/* 716:    */  
/* 717:    */  public static void alFilter(int filter, int param, IntBuffer values) {
/* 718:718 */    BufferChecks.checkBuffer(values, 1);
/* 719:719 */    nalFilteriv(filter, param, MemoryUtil.getAddress(values));
/* 720:    */  }
/* 721:    */  
/* 722:    */  static native void nalFilteriv(int paramInt1, int paramInt2, long paramLong);
/* 723:    */  
/* 724:724 */  public static void alFilterf(int filter, int param, float value) { nalFilterf(filter, param, value); }
/* 725:    */  
/* 726:    */  static native void nalFilterf(int paramInt1, int paramInt2, float paramFloat);
/* 727:    */  
/* 728:    */  public static void alFilter(int filter, int param, FloatBuffer values) {
/* 729:729 */    BufferChecks.checkBuffer(values, 1);
/* 730:730 */    nalFilterfv(filter, param, MemoryUtil.getAddress(values));
/* 731:    */  }
/* 732:    */  
/* 733:    */  static native void nalFilterfv(int paramInt1, int paramInt2, long paramLong);
/* 734:    */  
/* 735:735 */  public static int alGetFilteri(int filter, int param) { int __result = nalGetFilteri(filter, param);
/* 736:736 */    return __result;
/* 737:    */  }
/* 738:    */  
/* 739:    */  static native int nalGetFilteri(int paramInt1, int paramInt2);
/* 740:    */  
/* 741:741 */  public static void alGetFilter(int filter, int param, IntBuffer intdata) { BufferChecks.checkBuffer(intdata, 1);
/* 742:742 */    nalGetFilteriv(filter, param, MemoryUtil.getAddress(intdata));
/* 743:    */  }
/* 744:    */  
/* 745:    */  static native void nalGetFilteriv(int paramInt1, int paramInt2, long paramLong);
/* 746:    */  
/* 747:747 */  public static float alGetFilterf(int filter, int param) { float __result = nalGetFilterf(filter, param);
/* 748:748 */    return __result;
/* 749:    */  }
/* 750:    */  
/* 751:    */  static native float nalGetFilterf(int paramInt1, int paramInt2);
/* 752:    */  
/* 753:753 */  public static void alGetFilter(int filter, int param, FloatBuffer floatdata) { BufferChecks.checkBuffer(floatdata, 1);
/* 754:754 */    nalGetFilterfv(filter, param, MemoryUtil.getAddress(floatdata));
/* 755:    */  }
/* 756:    */  
/* 757:    */  static native void nalGetFilterfv(int paramInt1, int paramInt2, long paramLong);
/* 758:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.EFX10
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */