/*   1:    */package org.lwjgl.util.jinput;
/*   2:    */
/*   3:    */import net.java.games.input.Component.Identifier.Key;
/*   4:    */
/*  38:    */final class KeyMap
/*  39:    */{
/*  40:    */  public static Component.Identifier.Key map(int lwjgl_key_code)
/*  41:    */  {
/*  42: 42 */    switch (lwjgl_key_code) {
/*  43:    */    case 1: 
/*  44: 44 */      return Component.Identifier.Key.ESCAPE;
/*  45:    */    case 2: 
/*  46: 46 */      return Component.Identifier.Key._1;
/*  47:    */    case 3: 
/*  48: 48 */      return Component.Identifier.Key._2;
/*  49:    */    case 4: 
/*  50: 50 */      return Component.Identifier.Key._3;
/*  51:    */    case 5: 
/*  52: 52 */      return Component.Identifier.Key._4;
/*  53:    */    case 6: 
/*  54: 54 */      return Component.Identifier.Key._5;
/*  55:    */    case 7: 
/*  56: 56 */      return Component.Identifier.Key._6;
/*  57:    */    case 8: 
/*  58: 58 */      return Component.Identifier.Key._7;
/*  59:    */    case 9: 
/*  60: 60 */      return Component.Identifier.Key._8;
/*  61:    */    case 10: 
/*  62: 62 */      return Component.Identifier.Key._9;
/*  63:    */    case 11: 
/*  64: 64 */      return Component.Identifier.Key._0;
/*  65:    */    case 12: 
/*  66: 66 */      return Component.Identifier.Key.MINUS;
/*  67:    */    case 13: 
/*  68: 68 */      return Component.Identifier.Key.EQUALS;
/*  69:    */    case 14: 
/*  70: 70 */      return Component.Identifier.Key.BACK;
/*  71:    */    case 15: 
/*  72: 72 */      return Component.Identifier.Key.TAB;
/*  73:    */    case 16: 
/*  74: 74 */      return Component.Identifier.Key.Q;
/*  75:    */    case 17: 
/*  76: 76 */      return Component.Identifier.Key.W;
/*  77:    */    case 18: 
/*  78: 78 */      return Component.Identifier.Key.E;
/*  79:    */    case 19: 
/*  80: 80 */      return Component.Identifier.Key.R;
/*  81:    */    case 20: 
/*  82: 82 */      return Component.Identifier.Key.T;
/*  83:    */    case 21: 
/*  84: 84 */      return Component.Identifier.Key.Y;
/*  85:    */    case 22: 
/*  86: 86 */      return Component.Identifier.Key.U;
/*  87:    */    case 23: 
/*  88: 88 */      return Component.Identifier.Key.I;
/*  89:    */    case 24: 
/*  90: 90 */      return Component.Identifier.Key.O;
/*  91:    */    case 25: 
/*  92: 92 */      return Component.Identifier.Key.P;
/*  93:    */    case 26: 
/*  94: 94 */      return Component.Identifier.Key.LBRACKET;
/*  95:    */    case 27: 
/*  96: 96 */      return Component.Identifier.Key.RBRACKET;
/*  97:    */    case 28: 
/*  98: 98 */      return Component.Identifier.Key.RETURN;
/*  99:    */    case 29: 
/* 100:100 */      return Component.Identifier.Key.LCONTROL;
/* 101:    */    case 30: 
/* 102:102 */      return Component.Identifier.Key.A;
/* 103:    */    case 31: 
/* 104:104 */      return Component.Identifier.Key.S;
/* 105:    */    case 32: 
/* 106:106 */      return Component.Identifier.Key.D;
/* 107:    */    case 33: 
/* 108:108 */      return Component.Identifier.Key.F;
/* 109:    */    case 34: 
/* 110:110 */      return Component.Identifier.Key.G;
/* 111:    */    case 35: 
/* 112:112 */      return Component.Identifier.Key.H;
/* 113:    */    case 36: 
/* 114:114 */      return Component.Identifier.Key.J;
/* 115:    */    case 37: 
/* 116:116 */      return Component.Identifier.Key.K;
/* 117:    */    case 38: 
/* 118:118 */      return Component.Identifier.Key.L;
/* 119:    */    case 39: 
/* 120:120 */      return Component.Identifier.Key.SEMICOLON;
/* 121:    */    case 40: 
/* 122:122 */      return Component.Identifier.Key.APOSTROPHE;
/* 123:    */    case 41: 
/* 124:124 */      return Component.Identifier.Key.GRAVE;
/* 125:    */    case 42: 
/* 126:126 */      return Component.Identifier.Key.LSHIFT;
/* 127:    */    case 43: 
/* 128:128 */      return Component.Identifier.Key.BACKSLASH;
/* 129:    */    case 44: 
/* 130:130 */      return Component.Identifier.Key.Z;
/* 131:    */    case 45: 
/* 132:132 */      return Component.Identifier.Key.X;
/* 133:    */    case 46: 
/* 134:134 */      return Component.Identifier.Key.C;
/* 135:    */    case 47: 
/* 136:136 */      return Component.Identifier.Key.V;
/* 137:    */    case 48: 
/* 138:138 */      return Component.Identifier.Key.B;
/* 139:    */    case 49: 
/* 140:140 */      return Component.Identifier.Key.N;
/* 141:    */    case 50: 
/* 142:142 */      return Component.Identifier.Key.M;
/* 143:    */    case 51: 
/* 144:144 */      return Component.Identifier.Key.COMMA;
/* 145:    */    case 52: 
/* 146:146 */      return Component.Identifier.Key.PERIOD;
/* 147:    */    case 53: 
/* 148:148 */      return Component.Identifier.Key.SLASH;
/* 149:    */    case 54: 
/* 150:150 */      return Component.Identifier.Key.RSHIFT;
/* 151:    */    case 55: 
/* 152:152 */      return Component.Identifier.Key.MULTIPLY;
/* 153:    */    case 56: 
/* 154:154 */      return Component.Identifier.Key.LALT;
/* 155:    */    case 57: 
/* 156:156 */      return Component.Identifier.Key.SPACE;
/* 157:    */    case 58: 
/* 158:158 */      return Component.Identifier.Key.CAPITAL;
/* 159:    */    case 59: 
/* 160:160 */      return Component.Identifier.Key.F1;
/* 161:    */    case 60: 
/* 162:162 */      return Component.Identifier.Key.F2;
/* 163:    */    case 61: 
/* 164:164 */      return Component.Identifier.Key.F3;
/* 165:    */    case 62: 
/* 166:166 */      return Component.Identifier.Key.F4;
/* 167:    */    case 63: 
/* 168:168 */      return Component.Identifier.Key.F5;
/* 169:    */    case 64: 
/* 170:170 */      return Component.Identifier.Key.F6;
/* 171:    */    case 65: 
/* 172:172 */      return Component.Identifier.Key.F7;
/* 173:    */    case 66: 
/* 174:174 */      return Component.Identifier.Key.F8;
/* 175:    */    case 67: 
/* 176:176 */      return Component.Identifier.Key.F9;
/* 177:    */    case 68: 
/* 178:178 */      return Component.Identifier.Key.F10;
/* 179:    */    case 69: 
/* 180:180 */      return Component.Identifier.Key.NUMLOCK;
/* 181:    */    case 70: 
/* 182:182 */      return Component.Identifier.Key.SCROLL;
/* 183:    */    case 71: 
/* 184:184 */      return Component.Identifier.Key.NUMPAD7;
/* 185:    */    case 72: 
/* 186:186 */      return Component.Identifier.Key.NUMPAD8;
/* 187:    */    case 73: 
/* 188:188 */      return Component.Identifier.Key.NUMPAD9;
/* 189:    */    case 74: 
/* 190:190 */      return Component.Identifier.Key.SUBTRACT;
/* 191:    */    case 75: 
/* 192:192 */      return Component.Identifier.Key.NUMPAD4;
/* 193:    */    case 76: 
/* 194:194 */      return Component.Identifier.Key.NUMPAD5;
/* 195:    */    case 77: 
/* 196:196 */      return Component.Identifier.Key.NUMPAD6;
/* 197:    */    case 78: 
/* 198:198 */      return Component.Identifier.Key.ADD;
/* 199:    */    case 79: 
/* 200:200 */      return Component.Identifier.Key.NUMPAD1;
/* 201:    */    case 80: 
/* 202:202 */      return Component.Identifier.Key.NUMPAD2;
/* 203:    */    case 81: 
/* 204:204 */      return Component.Identifier.Key.NUMPAD3;
/* 205:    */    case 82: 
/* 206:206 */      return Component.Identifier.Key.NUMPAD0;
/* 207:    */    case 83: 
/* 208:208 */      return Component.Identifier.Key.DECIMAL;
/* 209:    */    case 87: 
/* 210:210 */      return Component.Identifier.Key.F11;
/* 211:    */    case 88: 
/* 212:212 */      return Component.Identifier.Key.F12;
/* 213:    */    case 100: 
/* 214:214 */      return Component.Identifier.Key.F13;
/* 215:    */    case 101: 
/* 216:216 */      return Component.Identifier.Key.F14;
/* 217:    */    case 102: 
/* 218:218 */      return Component.Identifier.Key.F15;
/* 219:    */    case 112: 
/* 220:220 */      return Component.Identifier.Key.KANA;
/* 221:    */    case 121: 
/* 222:222 */      return Component.Identifier.Key.CONVERT;
/* 223:    */    case 123: 
/* 224:224 */      return Component.Identifier.Key.NOCONVERT;
/* 225:    */    case 125: 
/* 226:226 */      return Component.Identifier.Key.YEN;
/* 227:    */    case 141: 
/* 228:228 */      return Component.Identifier.Key.NUMPADEQUAL;
/* 229:    */    case 144: 
/* 230:230 */      return Component.Identifier.Key.CIRCUMFLEX;
/* 231:    */    case 145: 
/* 232:232 */      return Component.Identifier.Key.AT;
/* 233:    */    case 146: 
/* 234:234 */      return Component.Identifier.Key.COLON;
/* 235:    */    case 147: 
/* 236:236 */      return Component.Identifier.Key.UNDERLINE;
/* 237:    */    case 148: 
/* 238:238 */      return Component.Identifier.Key.KANJI;
/* 239:    */    case 149: 
/* 240:240 */      return Component.Identifier.Key.STOP;
/* 241:    */    case 150: 
/* 242:242 */      return Component.Identifier.Key.AX;
/* 243:    */    case 151: 
/* 244:244 */      return Component.Identifier.Key.UNLABELED;
/* 245:    */    case 156: 
/* 246:246 */      return Component.Identifier.Key.NUMPADENTER;
/* 247:    */    case 157: 
/* 248:248 */      return Component.Identifier.Key.RCONTROL;
/* 249:    */    case 179: 
/* 250:250 */      return Component.Identifier.Key.NUMPADCOMMA;
/* 251:    */    case 181: 
/* 252:252 */      return Component.Identifier.Key.DIVIDE;
/* 253:    */    case 183: 
/* 254:254 */      return Component.Identifier.Key.SYSRQ;
/* 255:    */    case 184: 
/* 256:256 */      return Component.Identifier.Key.RALT;
/* 257:    */    case 197: 
/* 258:258 */      return Component.Identifier.Key.PAUSE;
/* 259:    */    case 199: 
/* 260:260 */      return Component.Identifier.Key.HOME;
/* 261:    */    case 200: 
/* 262:262 */      return Component.Identifier.Key.UP;
/* 263:    */    case 201: 
/* 264:264 */      return Component.Identifier.Key.PAGEUP;
/* 265:    */    case 203: 
/* 266:266 */      return Component.Identifier.Key.LEFT;
/* 267:    */    case 205: 
/* 268:268 */      return Component.Identifier.Key.RIGHT;
/* 269:    */    case 207: 
/* 270:270 */      return Component.Identifier.Key.END;
/* 271:    */    case 208: 
/* 272:272 */      return Component.Identifier.Key.DOWN;
/* 273:    */    case 209: 
/* 274:274 */      return Component.Identifier.Key.PAGEDOWN;
/* 275:    */    case 210: 
/* 276:276 */      return Component.Identifier.Key.INSERT;
/* 277:    */    case 211: 
/* 278:278 */      return Component.Identifier.Key.DELETE;
/* 279:    */    case 219: 
/* 280:280 */      return Component.Identifier.Key.LWIN;
/* 281:    */    case 220: 
/* 282:282 */      return Component.Identifier.Key.RWIN;
/* 283:    */    case 221: 
/* 284:284 */      return Component.Identifier.Key.APPS;
/* 285:    */    case 222: 
/* 286:286 */      return Component.Identifier.Key.POWER;
/* 287:    */    case 223: 
/* 288:288 */      return Component.Identifier.Key.SLEEP;
/* 289:    */    }
/* 290:290 */    return Component.Identifier.Key.UNKNOWN;
/* 291:    */  }
/* 292:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.jinput.KeyMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */