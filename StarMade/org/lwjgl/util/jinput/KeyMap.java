/*     */ package org.lwjgl.util.jinput;
/*     */ 
/*     */ import net.java.games.input.Component.Identifier.Key;
/*     */ 
/*     */ final class KeyMap
/*     */ {
/*     */   public static Component.Identifier.Key map(int lwjgl_key_code)
/*     */   {
/*  42 */     switch (lwjgl_key_code) {
/*     */     case 1:
/*  44 */       return Component.Identifier.Key.ESCAPE;
/*     */     case 2:
/*  46 */       return Component.Identifier.Key._1;
/*     */     case 3:
/*  48 */       return Component.Identifier.Key._2;
/*     */     case 4:
/*  50 */       return Component.Identifier.Key._3;
/*     */     case 5:
/*  52 */       return Component.Identifier.Key._4;
/*     */     case 6:
/*  54 */       return Component.Identifier.Key._5;
/*     */     case 7:
/*  56 */       return Component.Identifier.Key._6;
/*     */     case 8:
/*  58 */       return Component.Identifier.Key._7;
/*     */     case 9:
/*  60 */       return Component.Identifier.Key._8;
/*     */     case 10:
/*  62 */       return Component.Identifier.Key._9;
/*     */     case 11:
/*  64 */       return Component.Identifier.Key._0;
/*     */     case 12:
/*  66 */       return Component.Identifier.Key.MINUS;
/*     */     case 13:
/*  68 */       return Component.Identifier.Key.EQUALS;
/*     */     case 14:
/*  70 */       return Component.Identifier.Key.BACK;
/*     */     case 15:
/*  72 */       return Component.Identifier.Key.TAB;
/*     */     case 16:
/*  74 */       return Component.Identifier.Key.Q;
/*     */     case 17:
/*  76 */       return Component.Identifier.Key.W;
/*     */     case 18:
/*  78 */       return Component.Identifier.Key.E;
/*     */     case 19:
/*  80 */       return Component.Identifier.Key.R;
/*     */     case 20:
/*  82 */       return Component.Identifier.Key.T;
/*     */     case 21:
/*  84 */       return Component.Identifier.Key.Y;
/*     */     case 22:
/*  86 */       return Component.Identifier.Key.U;
/*     */     case 23:
/*  88 */       return Component.Identifier.Key.I;
/*     */     case 24:
/*  90 */       return Component.Identifier.Key.O;
/*     */     case 25:
/*  92 */       return Component.Identifier.Key.P;
/*     */     case 26:
/*  94 */       return Component.Identifier.Key.LBRACKET;
/*     */     case 27:
/*  96 */       return Component.Identifier.Key.RBRACKET;
/*     */     case 28:
/*  98 */       return Component.Identifier.Key.RETURN;
/*     */     case 29:
/* 100 */       return Component.Identifier.Key.LCONTROL;
/*     */     case 30:
/* 102 */       return Component.Identifier.Key.A;
/*     */     case 31:
/* 104 */       return Component.Identifier.Key.S;
/*     */     case 32:
/* 106 */       return Component.Identifier.Key.D;
/*     */     case 33:
/* 108 */       return Component.Identifier.Key.F;
/*     */     case 34:
/* 110 */       return Component.Identifier.Key.G;
/*     */     case 35:
/* 112 */       return Component.Identifier.Key.H;
/*     */     case 36:
/* 114 */       return Component.Identifier.Key.J;
/*     */     case 37:
/* 116 */       return Component.Identifier.Key.K;
/*     */     case 38:
/* 118 */       return Component.Identifier.Key.L;
/*     */     case 39:
/* 120 */       return Component.Identifier.Key.SEMICOLON;
/*     */     case 40:
/* 122 */       return Component.Identifier.Key.APOSTROPHE;
/*     */     case 41:
/* 124 */       return Component.Identifier.Key.GRAVE;
/*     */     case 42:
/* 126 */       return Component.Identifier.Key.LSHIFT;
/*     */     case 43:
/* 128 */       return Component.Identifier.Key.BACKSLASH;
/*     */     case 44:
/* 130 */       return Component.Identifier.Key.Z;
/*     */     case 45:
/* 132 */       return Component.Identifier.Key.X;
/*     */     case 46:
/* 134 */       return Component.Identifier.Key.C;
/*     */     case 47:
/* 136 */       return Component.Identifier.Key.V;
/*     */     case 48:
/* 138 */       return Component.Identifier.Key.B;
/*     */     case 49:
/* 140 */       return Component.Identifier.Key.N;
/*     */     case 50:
/* 142 */       return Component.Identifier.Key.M;
/*     */     case 51:
/* 144 */       return Component.Identifier.Key.COMMA;
/*     */     case 52:
/* 146 */       return Component.Identifier.Key.PERIOD;
/*     */     case 53:
/* 148 */       return Component.Identifier.Key.SLASH;
/*     */     case 54:
/* 150 */       return Component.Identifier.Key.RSHIFT;
/*     */     case 55:
/* 152 */       return Component.Identifier.Key.MULTIPLY;
/*     */     case 56:
/* 154 */       return Component.Identifier.Key.LALT;
/*     */     case 57:
/* 156 */       return Component.Identifier.Key.SPACE;
/*     */     case 58:
/* 158 */       return Component.Identifier.Key.CAPITAL;
/*     */     case 59:
/* 160 */       return Component.Identifier.Key.F1;
/*     */     case 60:
/* 162 */       return Component.Identifier.Key.F2;
/*     */     case 61:
/* 164 */       return Component.Identifier.Key.F3;
/*     */     case 62:
/* 166 */       return Component.Identifier.Key.F4;
/*     */     case 63:
/* 168 */       return Component.Identifier.Key.F5;
/*     */     case 64:
/* 170 */       return Component.Identifier.Key.F6;
/*     */     case 65:
/* 172 */       return Component.Identifier.Key.F7;
/*     */     case 66:
/* 174 */       return Component.Identifier.Key.F8;
/*     */     case 67:
/* 176 */       return Component.Identifier.Key.F9;
/*     */     case 68:
/* 178 */       return Component.Identifier.Key.F10;
/*     */     case 69:
/* 180 */       return Component.Identifier.Key.NUMLOCK;
/*     */     case 70:
/* 182 */       return Component.Identifier.Key.SCROLL;
/*     */     case 71:
/* 184 */       return Component.Identifier.Key.NUMPAD7;
/*     */     case 72:
/* 186 */       return Component.Identifier.Key.NUMPAD8;
/*     */     case 73:
/* 188 */       return Component.Identifier.Key.NUMPAD9;
/*     */     case 74:
/* 190 */       return Component.Identifier.Key.SUBTRACT;
/*     */     case 75:
/* 192 */       return Component.Identifier.Key.NUMPAD4;
/*     */     case 76:
/* 194 */       return Component.Identifier.Key.NUMPAD5;
/*     */     case 77:
/* 196 */       return Component.Identifier.Key.NUMPAD6;
/*     */     case 78:
/* 198 */       return Component.Identifier.Key.ADD;
/*     */     case 79:
/* 200 */       return Component.Identifier.Key.NUMPAD1;
/*     */     case 80:
/* 202 */       return Component.Identifier.Key.NUMPAD2;
/*     */     case 81:
/* 204 */       return Component.Identifier.Key.NUMPAD3;
/*     */     case 82:
/* 206 */       return Component.Identifier.Key.NUMPAD0;
/*     */     case 83:
/* 208 */       return Component.Identifier.Key.DECIMAL;
/*     */     case 87:
/* 210 */       return Component.Identifier.Key.F11;
/*     */     case 88:
/* 212 */       return Component.Identifier.Key.F12;
/*     */     case 100:
/* 214 */       return Component.Identifier.Key.F13;
/*     */     case 101:
/* 216 */       return Component.Identifier.Key.F14;
/*     */     case 102:
/* 218 */       return Component.Identifier.Key.F15;
/*     */     case 112:
/* 220 */       return Component.Identifier.Key.KANA;
/*     */     case 121:
/* 222 */       return Component.Identifier.Key.CONVERT;
/*     */     case 123:
/* 224 */       return Component.Identifier.Key.NOCONVERT;
/*     */     case 125:
/* 226 */       return Component.Identifier.Key.YEN;
/*     */     case 141:
/* 228 */       return Component.Identifier.Key.NUMPADEQUAL;
/*     */     case 144:
/* 230 */       return Component.Identifier.Key.CIRCUMFLEX;
/*     */     case 145:
/* 232 */       return Component.Identifier.Key.AT;
/*     */     case 146:
/* 234 */       return Component.Identifier.Key.COLON;
/*     */     case 147:
/* 236 */       return Component.Identifier.Key.UNDERLINE;
/*     */     case 148:
/* 238 */       return Component.Identifier.Key.KANJI;
/*     */     case 149:
/* 240 */       return Component.Identifier.Key.STOP;
/*     */     case 150:
/* 242 */       return Component.Identifier.Key.AX;
/*     */     case 151:
/* 244 */       return Component.Identifier.Key.UNLABELED;
/*     */     case 156:
/* 246 */       return Component.Identifier.Key.NUMPADENTER;
/*     */     case 157:
/* 248 */       return Component.Identifier.Key.RCONTROL;
/*     */     case 179:
/* 250 */       return Component.Identifier.Key.NUMPADCOMMA;
/*     */     case 181:
/* 252 */       return Component.Identifier.Key.DIVIDE;
/*     */     case 183:
/* 254 */       return Component.Identifier.Key.SYSRQ;
/*     */     case 184:
/* 256 */       return Component.Identifier.Key.RALT;
/*     */     case 197:
/* 258 */       return Component.Identifier.Key.PAUSE;
/*     */     case 199:
/* 260 */       return Component.Identifier.Key.HOME;
/*     */     case 200:
/* 262 */       return Component.Identifier.Key.UP;
/*     */     case 201:
/* 264 */       return Component.Identifier.Key.PAGEUP;
/*     */     case 203:
/* 266 */       return Component.Identifier.Key.LEFT;
/*     */     case 205:
/* 268 */       return Component.Identifier.Key.RIGHT;
/*     */     case 207:
/* 270 */       return Component.Identifier.Key.END;
/*     */     case 208:
/* 272 */       return Component.Identifier.Key.DOWN;
/*     */     case 209:
/* 274 */       return Component.Identifier.Key.PAGEDOWN;
/*     */     case 210:
/* 276 */       return Component.Identifier.Key.INSERT;
/*     */     case 211:
/* 278 */       return Component.Identifier.Key.DELETE;
/*     */     case 219:
/* 280 */       return Component.Identifier.Key.LWIN;
/*     */     case 220:
/* 282 */       return Component.Identifier.Key.RWIN;
/*     */     case 221:
/* 284 */       return Component.Identifier.Key.APPS;
/*     */     case 222:
/* 286 */       return Component.Identifier.Key.POWER;
/*     */     case 223:
/* 288 */       return Component.Identifier.Key.SLEEP;
/*     */     case 84:
/*     */     case 85:
/*     */     case 86:
/*     */     case 89:
/*     */     case 90:
/*     */     case 91:
/*     */     case 92:
/*     */     case 93:
/*     */     case 94:
/*     */     case 95:
/*     */     case 96:
/*     */     case 97:
/*     */     case 98:
/*     */     case 99:
/*     */     case 103:
/*     */     case 104:
/*     */     case 105:
/*     */     case 106:
/*     */     case 107:
/*     */     case 108:
/*     */     case 109:
/*     */     case 110:
/*     */     case 111:
/*     */     case 113:
/*     */     case 114:
/*     */     case 115:
/*     */     case 116:
/*     */     case 117:
/*     */     case 118:
/*     */     case 119:
/*     */     case 120:
/*     */     case 122:
/*     */     case 124:
/*     */     case 126:
/*     */     case 127:
/*     */     case 128:
/*     */     case 129:
/*     */     case 130:
/*     */     case 131:
/*     */     case 132:
/*     */     case 133:
/*     */     case 134:
/*     */     case 135:
/*     */     case 136:
/*     */     case 137:
/*     */     case 138:
/*     */     case 139:
/*     */     case 140:
/*     */     case 142:
/*     */     case 143:
/*     */     case 152:
/*     */     case 153:
/*     */     case 154:
/*     */     case 155:
/*     */     case 158:
/*     */     case 159:
/*     */     case 160:
/*     */     case 161:
/*     */     case 162:
/*     */     case 163:
/*     */     case 164:
/*     */     case 165:
/*     */     case 166:
/*     */     case 167:
/*     */     case 168:
/*     */     case 169:
/*     */     case 170:
/*     */     case 171:
/*     */     case 172:
/*     */     case 173:
/*     */     case 174:
/*     */     case 175:
/*     */     case 176:
/*     */     case 177:
/*     */     case 178:
/*     */     case 180:
/*     */     case 182:
/*     */     case 185:
/*     */     case 186:
/*     */     case 187:
/*     */     case 188:
/*     */     case 189:
/*     */     case 190:
/*     */     case 191:
/*     */     case 192:
/*     */     case 193:
/*     */     case 194:
/*     */     case 195:
/*     */     case 196:
/*     */     case 198:
/*     */     case 202:
/*     */     case 204:
/*     */     case 206:
/*     */     case 212:
/*     */     case 213:
/*     */     case 214:
/*     */     case 215:
/*     */     case 216:
/*     */     case 217:
/* 290 */     case 218: } return Component.Identifier.Key.UNKNOWN;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.jinput.KeyMap
 * JD-Core Version:    0.6.2
 */