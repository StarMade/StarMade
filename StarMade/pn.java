/*   1:    */import java.awt.Component;
/*   2:    */import java.awt.GridBagConstraints;
/*   3:    */import java.awt.GridBagLayout;
/*   4:    */import java.awt.Insets;
/*   5:    */import java.awt.LayoutManager;
/*   6:    */import javax.swing.JLabel;
/*   7:    */import javax.swing.JPanel;
/*   8:    */
/*  18:    */public final class pn
/*  19:    */  extends JPanel
/*  20:    */{
/*  21:    */  private static final long serialVersionUID = -7527303857041337040L;
/*  22:    */  
/*  23:    */  public pn()
/*  24:    */  {
/*  25: 25 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
/*  26: 26 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*  27: 27 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/*  28: 28 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  29: 29 */    setLayout((LayoutManager)localObject1);
/*  30:    */    
/*  31: 31 */    Object localObject1 = new JLabel(" Resolution: ");
/*  32:    */    
/*  33: 33 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/*  34: 34 */    ((GridBagConstraints)localObject2).anchor = 17;
/*  35: 35 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  36: 36 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  37: 37 */    add((Component)localObject1, localObject2);
/*  38:    */    
/*  39: 39 */    Object localObject2 = new pH(xu.b);
/*  40: 40 */    ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*  41:    */    
/*  42: 42 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  43: 43 */    ((GridBagConstraints)localObject1).fill = 2;
/*  44: 44 */    ((GridBagConstraints)localObject1).gridx = 1;
/*  45: 45 */    ((GridBagConstraints)localObject1).gridy = 0;
/*  46: 46 */    add((Component)localObject2, localObject1);
/*  47:    */    
/*  48: 48 */    localObject1 = new JLabel(" AntiAliasing samples: ");
/*  49:    */    
/*  50: 50 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/*  51: 51 */    ((GridBagConstraints)localObject2).anchor = 17;
/*  52: 52 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  53: 53 */    ((GridBagConstraints)localObject2).gridy = 1;
/*  54: 54 */    add((Component)localObject1, localObject2);
/*  55:    */    
/*  56: 56 */    localObject2 = new pH(xu.T);
/*  57: 57 */    ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*  58:    */    
/*  59: 59 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  60: 60 */    ((GridBagConstraints)localObject1).fill = 2;
/*  61: 61 */    ((GridBagConstraints)localObject1).gridx = 1;
/*  62: 62 */    ((GridBagConstraints)localObject1).gridy = 1;
/*  63: 63 */    add((Component)localObject2, localObject1);
/*  64:    */    
/*  65: 65 */    localObject1 = new JLabel(" Bloom: ");
/*  66:    */    
/*  67: 67 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/*  68: 68 */    ((GridBagConstraints)localObject2).anchor = 17;
/*  69: 69 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  70: 70 */    ((GridBagConstraints)localObject2).gridy = 2;
/*  71: 71 */    add((Component)localObject1, localObject2);
/*  72:    */    
/*  73: 73 */    localObject2 = new pH(xu.V);
/*  74: 74 */    ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*  75:    */    
/*  76: 76 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  77: 77 */    ((GridBagConstraints)localObject1).fill = 2;
/*  78: 78 */    ((GridBagConstraints)localObject1).gridx = 1;
/*  79: 79 */    ((GridBagConstraints)localObject1).gridy = 2;
/*  80: 80 */    add((Component)localObject2, localObject1);
/*  81:    */    
/*  82: 82 */    localObject1 = new JLabel(" View Distance: ");
/*  83:    */    
/*  84: 84 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/*  85: 85 */    ((GridBagConstraints)localObject2).anchor = 17;
/*  86: 86 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  87: 87 */    ((GridBagConstraints)localObject2).gridy = 3;
/*  88: 88 */    add((Component)localObject1, localObject2);
/*  89:    */    
/*  90: 90 */    localObject2 = new pH(xu.e);
/*  91: 91 */    ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*  92:    */    
/*  93: 93 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  94: 94 */    ((GridBagConstraints)localObject1).fill = 2;
/*  95: 95 */    ((GridBagConstraints)localObject1).gridx = 1;
/*  96: 96 */    ((GridBagConstraints)localObject1).gridy = 3;
/*  97: 97 */    add((Component)localObject2, localObject1);
/*  98:    */    
/*  99: 99 */    localObject1 = new JLabel(" Vertical Synch: ");
/* 100:    */    
/* 101:101 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/* 102:102 */    ((GridBagConstraints)localObject2).anchor = 17;
/* 103:103 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 104:104 */    ((GridBagConstraints)localObject2).gridy = 4;
/* 105:105 */    add((Component)localObject1, localObject2);
/* 106:    */    
/* 107:107 */    localObject2 = new pH(xu.d);
/* 108:108 */    ((JLabel)localObject1).setLabelFor((Component)localObject2);
/* 109:    */    
/* 110:110 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 111:111 */    ((GridBagConstraints)localObject1).fill = 2;
/* 112:112 */    ((GridBagConstraints)localObject1).gridx = 1;
/* 113:113 */    ((GridBagConstraints)localObject1).gridy = 4;
/* 114:114 */    add((Component)localObject2, localObject1);
/* 115:    */    
/* 116:116 */    localObject1 = new JLabel(" Fullscreen: ");
/* 117:    */    
/* 118:118 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 119:119 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 120:120 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 121:121 */    ((GridBagConstraints)localObject2).gridy = 5;
/* 122:122 */    add((Component)localObject1, localObject2);
/* 123:    */    
/* 124:124 */    localObject2 = new pH(xu.c);
/* 125:125 */    ((JLabel)localObject1).setLabelFor((Component)localObject2);
/* 126:    */    
/* 127:127 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 128:128 */    ((GridBagConstraints)localObject1).fill = 2;
/* 129:129 */    ((GridBagConstraints)localObject1).gridx = 1;
/* 130:130 */    ((GridBagConstraints)localObject1).gridy = 5;
/* 131:131 */    add((Component)localObject2, localObject1);
/* 132:    */    
/* 133:133 */    localObject1 = new JLabel(" Fixed Framerate: ");
/* 134:    */    
/* 135:135 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 136:136 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 137:137 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 138:138 */    ((GridBagConstraints)localObject2).gridy = 6;
/* 139:139 */    add((Component)localObject1, localObject2);
/* 140:    */    
/* 141:141 */    localObject2 = new pH(xu.v);
/* 142:142 */    ((JLabel)localObject1).setLabelFor((Component)localObject2);
/* 143:    */    
/* 144:144 */    (localObject1 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 145:145 */    ((GridBagConstraints)localObject1).fill = 2;
/* 146:146 */    ((GridBagConstraints)localObject1).gridx = 1;
/* 147:147 */    ((GridBagConstraints)localObject1).gridy = 6;
/* 148:148 */    add((Component)localObject2, localObject1);
/* 149:    */    
/* 150:150 */    localObject1 = new JLabel(" Max Segments in Graphics Memory: ");
/* 151:    */    
/* 152:152 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 153:153 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 154:154 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 155:155 */    ((GridBagConstraints)localObject2).gridy = 7;
/* 156:156 */    add((Component)localObject1, localObject2);
/* 157:    */    
/* 158:158 */    localObject1 = new pH(xu.ad);
/* 159:    */    
/* 160:160 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 161:161 */    ((GridBagConstraints)localObject2).fill = 2;
/* 162:162 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 163:163 */    ((GridBagConstraints)localObject2).gridy = 7;
/* 164:164 */    add((Component)localObject1, localObject2);
/* 165:    */    
/* 166:166 */    localObject1 = new JLabel(" HiRes Background:");
/* 167:    */    
/* 168:168 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/* 169:169 */    ((GridBagConstraints)localObject2).anchor = 17;
/* 170:170 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 171:171 */    ((GridBagConstraints)localObject2).gridy = 8;
/* 172:172 */    add((Component)localObject1, localObject2);
/* 173:    */    
/* 174:174 */    localObject1 = new pH(xu.ab);
/* 175:    */    
/* 176:176 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 177:177 */    ((GridBagConstraints)localObject2).fill = 2;
/* 178:178 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 179:179 */    ((GridBagConstraints)localObject2).gridy = 8;
/* 180:180 */    add((Component)localObject1, localObject2);
/* 181:    */    
/* 182:182 */    localObject1 = new JLabel(" Tutorial:");
/* 183:    */    
/* 184:184 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 185:185 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 186:186 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 187:187 */    ((GridBagConstraints)localObject2).gridy = 9;
/* 188:188 */    add((Component)localObject1, localObject2);
/* 189:    */    
/* 190:190 */    localObject1 = new pH(xu.O);
/* 191:    */    
/* 192:192 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 193:193 */    ((GridBagConstraints)localObject2).fill = 2;
/* 194:194 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 195:195 */    ((GridBagConstraints)localObject2).gridy = 9;
/* 196:196 */    add((Component)localObject1, localObject2);
/* 197:    */    
/* 198:198 */    localObject1 = new JLabel(" Invert Ship Mouse:");
/* 199:    */    
/* 200:200 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 201:201 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 202:202 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 203:203 */    ((GridBagConstraints)localObject2).gridy = 10;
/* 204:204 */    add((Component)localObject1, localObject2);
/* 205:    */    
/* 206:206 */    localObject1 = new pH(xu.Y);
/* 207:    */    
/* 208:208 */    (localObject2 = new GridBagConstraints()).fill = 2;
/* 209:209 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 210:210 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 211:211 */    ((GridBagConstraints)localObject2).gridy = 10;
/* 212:212 */    add((Component)localObject1, localObject2);
/* 213:    */    
/* 214:214 */    localObject1 = new JLabel(" Invert Mouse for everything:");
/* 215:    */    
/* 216:216 */    (localObject2 = new GridBagConstraints()).anchor = 13;
/* 217:217 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 218:218 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 219:219 */    ((GridBagConstraints)localObject2).gridy = 11;
/* 220:220 */    add((Component)localObject1, localObject2);
/* 221:    */    
/* 222:222 */    localObject1 = new pH(xu.Z);
/* 223:    */    
/* 224:224 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 225:225 */    ((GridBagConstraints)localObject2).fill = 2;
/* 226:226 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 227:227 */    ((GridBagConstraints)localObject2).gridy = 11;
/* 228:228 */    add((Component)localObject1, localObject2);
/* 229:    */    
/* 230:230 */    localObject1 = new JLabel(" Window Pos:");
/* 231:    */    
/* 232:232 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 233:233 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 234:234 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 235:235 */    ((GridBagConstraints)localObject2).gridy = 12;
/* 236:236 */    add((Component)localObject1, localObject2);
/* 237:    */    
/* 238:238 */    localObject1 = new pH(xu.I);
/* 239:    */    
/* 240:240 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 241:241 */    ((GridBagConstraints)localObject2).fill = 2;
/* 242:242 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 243:243 */    ((GridBagConstraints)localObject2).gridy = 12;
/* 244:244 */    add((Component)localObject1, localObject2);
/* 245:    */    
/* 246:246 */    localObject1 = new JLabel(" Enable Background: ");
/* 247:    */    
/* 248:248 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 249:249 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 250:250 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 251:251 */    ((GridBagConstraints)localObject2).gridy = 13;
/* 252:252 */    add((Component)localObject1, localObject2);
/* 253:    */    
/* 254:254 */    localObject1 = new pH(xu.t);
/* 255:    */    
/* 256:256 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 257:257 */    ((GridBagConstraints)localObject2).fill = 2;
/* 258:258 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 259:259 */    ((GridBagConstraints)localObject2).gridy = 13;
/* 260:260 */    add((Component)localObject1, localObject2);
/* 261:    */    
/* 262:262 */    localObject1 = new JLabel(" Enable Shieldhit graphics: ");
/* 263:    */    
/* 264:264 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/* 265:265 */    ((GridBagConstraints)localObject2).anchor = 17;
/* 266:266 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 267:267 */    ((GridBagConstraints)localObject2).gridy = 14;
/* 268:268 */    add((Component)localObject1, localObject2);
/* 269:    */    
/* 270:270 */    localObject1 = new pH(xu.s);
/* 271:    */    
/* 272:272 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 273:273 */    ((GridBagConstraints)localObject2).fill = 2;
/* 274:274 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 275:275 */    ((GridBagConstraints)localObject2).gridy = 14;
/* 276:276 */    add((Component)localObject1, localObject2);
/* 277:    */    
/* 278:278 */    localObject1 = new JLabel(" Enable Sound: ");
/* 279:    */    
/* 280:280 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 281:281 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 282:282 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 283:283 */    ((GridBagConstraints)localObject2).gridy = 15;
/* 284:284 */    add((Component)localObject1, localObject2);
/* 285:    */    
/* 286:286 */    localObject1 = new pH(xu.g);
/* 287:    */    
/* 288:288 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 289:289 */    ((GridBagConstraints)localObject2).fill = 2;
/* 290:290 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 291:291 */    ((GridBagConstraints)localObject2).gridy = 15;
/* 292:292 */    add((Component)localObject1, localObject2);
/* 293:    */    
/* 294:294 */    localObject1 = new JLabel(" Sound Volume: ");
/* 295:    */    
/* 296:296 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 297:297 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 298:298 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 299:299 */    ((GridBagConstraints)localObject2).gridy = 16;
/* 300:300 */    add((Component)localObject1, localObject2);
/* 301:    */    
/* 302:302 */    localObject1 = new pH(xu.i);
/* 303:    */    
/* 304:304 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 305:305 */    ((GridBagConstraints)localObject2).fill = 2;
/* 306:306 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 307:307 */    ((GridBagConstraints)localObject2).gridy = 16;
/* 308:308 */    add((Component)localObject1, localObject2);
/* 309:    */    
/* 310:310 */    localObject1 = new JLabel(" Field of View (FOV): ");
/* 311:    */    
/* 312:312 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 313:313 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 314:314 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 315:315 */    ((GridBagConstraints)localObject2).gridy = 17;
/* 316:316 */    add((Component)localObject1, localObject2);
/* 317:    */    
/* 318:318 */    localObject1 = new pH(xu.f);
/* 319:    */    
/* 320:320 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 321:321 */    ((GridBagConstraints)localObject2).fill = 2;
/* 322:322 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 323:323 */    ((GridBagConstraints)localObject2).gridy = 17;
/* 324:324 */    add((Component)localObject1, localObject2);
/* 325:    */    
/* 326:326 */    localObject1 = new JLabel(" Use Frame Buffer: ");
/* 327:    */    
/* 328:328 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 329:329 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 330:330 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 331:331 */    ((GridBagConstraints)localObject2).gridy = 18;
/* 332:332 */    add((Component)localObject1, localObject2);
/* 333:    */    
/* 334:334 */    localObject1 = new pH(xu.x);
/* 335:    */    
/* 336:336 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 337:337 */    ((GridBagConstraints)localObject2).fill = 2;
/* 338:338 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 339:339 */    ((GridBagConstraints)localObject2).gridy = 18;
/* 340:340 */    add((Component)localObject1, localObject2);
/* 341:    */    
/* 342:342 */    localObject1 = new JLabel(" Atmosphere Shader: ");
/* 343:    */    
/* 344:344 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/* 345:345 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 0, 5);
/* 346:346 */    ((GridBagConstraints)localObject2).gridx = 0;
/* 347:347 */    ((GridBagConstraints)localObject2).gridy = 19;
/* 348:348 */    add((Component)localObject1, localObject2);
/* 349:    */    
/* 350:350 */    localObject1 = new pH(xu.r);
/* 351:    */    
/* 352:352 */    (localObject2 = new GridBagConstraints()).fill = 2;
/* 353:353 */    ((GridBagConstraints)localObject2).gridx = 1;
/* 354:354 */    ((GridBagConstraints)localObject2).gridy = 19;
/* 355:355 */    add((Component)localObject1, localObject2);
/* 356:    */  }
/* 357:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */