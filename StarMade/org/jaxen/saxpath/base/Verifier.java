/*   1:    */package org.jaxen.saxpath.base;
/*   2:    */
/*  63:    */final class Verifier
/*  64:    */{
/*  65:    */  static boolean isXMLNCNameCharacter(char c)
/*  66:    */  {
/*  67: 67 */    return (isXMLLetter(c)) || (isXMLDigit(c)) || (c == '.') || (c == '-') || (c == '_') || (isXMLCombiningChar(c)) || (isXMLExtender(c));
/*  68:    */  }
/*  69:    */  
/*  82:    */  static boolean isXMLNCNameStartCharacter(char c)
/*  83:    */  {
/*  84: 84 */    return (isXMLLetter(c)) || (c == '_');
/*  85:    */  }
/*  86:    */  
/*  99:    */  static boolean isXMLLetter(char c)
/* 100:    */  {
/* 101:101 */    if (c < 'A') return false; if (c <= 'Z') return true;
/* 102:102 */    if (c < 'a') return false; if (c <= 'z') return true;
/* 103:103 */    if (c < 'À') return false; if (c <= 'Ö') return true;
/* 104:104 */    if (c < 'Ø') return false; if (c <= 'ö') return true;
/* 105:105 */    if (c < 'ø') return false; if (c <= 'ÿ') return true;
/* 106:106 */    if (c < 'Ā') return false; if (c <= 'ı') return true;
/* 107:107 */    if (c < 'Ĵ') return false; if (c <= 'ľ') return true;
/* 108:108 */    if (c < 'Ł') return false; if (c <= 'ň') return true;
/* 109:109 */    if (c < 'Ŋ') return false; if (c <= 'ž') return true;
/* 110:110 */    if (c < 'ƀ') return false; if (c <= 'ǃ') return true;
/* 111:111 */    if (c < 'Ǎ') return false; if (c <= 'ǰ') return true;
/* 112:112 */    if (c < 'Ǵ') return false; if (c <= 'ǵ') return true;
/* 113:113 */    if (c < 'Ǻ') return false; if (c <= 'ȗ') return true;
/* 114:114 */    if (c < 'ɐ') return false; if (c <= 'ʨ') return true;
/* 115:115 */    if (c < 'ʻ') return false; if (c <= 'ˁ') return true;
/* 116:116 */    if (c == 'Ά') return true;
/* 117:117 */    if (c < 'Έ') return false; if (c <= 'Ί') return true;
/* 118:118 */    if (c == 'Ό') return true;
/* 119:119 */    if (c < 'Ύ') return false; if (c <= 'Ρ') return true;
/* 120:120 */    if (c < 'Σ') return false; if (c <= 'ώ') return true;
/* 121:121 */    if (c < 'ϐ') return false; if (c <= 'ϖ') return true;
/* 122:122 */    if (c == 'Ϛ') return true;
/* 123:123 */    if (c == 'Ϝ') return true;
/* 124:124 */    if (c == 'Ϟ') return true;
/* 125:125 */    if (c == 'Ϡ') return true;
/* 126:126 */    if (c < 'Ϣ') return false; if (c <= 'ϳ') return true;
/* 127:127 */    if (c < 'Ё') return false; if (c <= 'Ќ') return true;
/* 128:128 */    if (c < 'Ў') return false; if (c <= 'я') return true;
/* 129:129 */    if (c < 'ё') return false; if (c <= 'ќ') return true;
/* 130:130 */    if (c < 'ў') return false; if (c <= 'ҁ') return true;
/* 131:131 */    if (c < 'Ґ') return false; if (c <= 'ӄ') return true;
/* 132:132 */    if (c < 'Ӈ') return false; if (c <= 'ӈ') return true;
/* 133:133 */    if (c < 'Ӌ') return false; if (c <= 'ӌ') return true;
/* 134:134 */    if (c < 'Ӑ') return false; if (c <= 'ӫ') return true;
/* 135:135 */    if (c < 'Ӯ') return false; if (c <= 'ӵ') return true;
/* 136:136 */    if (c < 'Ӹ') return false; if (c <= 'ӹ') return true;
/* 137:137 */    if (c < 'Ա') return false; if (c <= 'Ֆ') return true;
/* 138:138 */    if (c == 'ՙ') return true;
/* 139:139 */    if (c < 'ա') return false; if (c <= 'ֆ') return true;
/* 140:140 */    if (c < 'א') return false; if (c <= 'ת') return true;
/* 141:141 */    if (c < 'װ') return false; if (c <= 'ײ') return true;
/* 142:142 */    if (c < 'ء') return false; if (c <= 'غ') return true;
/* 143:143 */    if (c < 'ف') return false; if (c <= 'ي') return true;
/* 144:144 */    if (c < 'ٱ') return false; if (c <= 'ڷ') return true;
/* 145:145 */    if (c < 'ں') return false; if (c <= 'ھ') return true;
/* 146:146 */    if (c < 'ۀ') return false; if (c <= 'ێ') return true;
/* 147:147 */    if (c < 'ې') return false; if (c <= 'ۓ') return true;
/* 148:148 */    if (c == 'ە') return true;
/* 149:149 */    if (c < 'ۥ') return false; if (c <= 'ۦ') return true;
/* 150:150 */    if (c < 'अ') return false; if (c <= 'ह') return true;
/* 151:151 */    if (c == 'ऽ') return true;
/* 152:152 */    if (c < 'क़') return false; if (c <= 'ॡ') return true;
/* 153:153 */    if (c < 'অ') return false; if (c <= 'ঌ') return true;
/* 154:154 */    if (c < 'এ') return false; if (c <= 'ঐ') return true;
/* 155:155 */    if (c < 'ও') return false; if (c <= 'ন') return true;
/* 156:156 */    if (c < 'প') return false; if (c <= 'র') return true;
/* 157:157 */    if (c == 'ল') return true;
/* 158:158 */    if (c < 'শ') return false; if (c <= 'হ') return true;
/* 159:159 */    if (c < 'ড়') return false; if (c <= 'ঢ়') return true;
/* 160:160 */    if (c < 'য়') return false; if (c <= 'ৡ') return true;
/* 161:161 */    if (c < 'ৰ') return false; if (c <= 'ৱ') return true;
/* 162:162 */    if (c < 'ਅ') return false; if (c <= 'ਊ') return true;
/* 163:163 */    if (c < 'ਏ') return false; if (c <= 'ਐ') return true;
/* 164:164 */    if (c < 'ਓ') return false; if (c <= 'ਨ') return true;
/* 165:165 */    if (c < 'ਪ') return false; if (c <= 'ਰ') return true;
/* 166:166 */    if (c < 'ਲ') return false; if (c <= 'ਲ਼') return true;
/* 167:167 */    if (c < 'ਵ') return false; if (c <= 'ਸ਼') return true;
/* 168:168 */    if (c < 'ਸ') return false; if (c <= 'ਹ') return true;
/* 169:169 */    if (c < 'ਖ਼') return false; if (c <= 'ੜ') return true;
/* 170:170 */    if (c == 'ਫ਼') return true;
/* 171:171 */    if (c < 'ੲ') return false; if (c <= 'ੴ') return true;
/* 172:172 */    if (c < 'અ') return false; if (c <= 'ઋ') return true;
/* 173:173 */    if (c == 'ઍ') return true;
/* 174:174 */    if (c < 'એ') return false; if (c <= 'ઑ') return true;
/* 175:175 */    if (c < 'ઓ') return false; if (c <= 'ન') return true;
/* 176:176 */    if (c < 'પ') return false; if (c <= 'ર') return true;
/* 177:177 */    if (c < 'લ') return false; if (c <= 'ળ') return true;
/* 178:178 */    if (c < 'વ') return false; if (c <= 'હ') return true;
/* 179:179 */    if (c == 'ઽ') return true;
/* 180:180 */    if (c == 'ૠ') return true;
/* 181:181 */    if (c < 'ଅ') return false; if (c <= 'ଌ') return true;
/* 182:182 */    if (c < 'ଏ') return false; if (c <= 'ଐ') return true;
/* 183:183 */    if (c < 'ଓ') return false; if (c <= 'ନ') return true;
/* 184:184 */    if (c < 'ପ') return false; if (c <= 'ର') return true;
/* 185:185 */    if (c < 'ଲ') return false; if (c <= 'ଳ') return true;
/* 186:186 */    if (c < 'ଶ') return false; if (c <= 'ହ') return true;
/* 187:187 */    if (c == 'ଽ') return true;
/* 188:188 */    if (c < 'ଡ଼') return false; if (c <= 'ଢ଼') return true;
/* 189:189 */    if (c < 'ୟ') return false; if (c <= 'ୡ') return true;
/* 190:190 */    if (c < 'அ') return false; if (c <= 'ஊ') return true;
/* 191:191 */    if (c < 'எ') return false; if (c <= 'ஐ') return true;
/* 192:192 */    if (c < 'ஒ') return false; if (c <= 'க') return true;
/* 193:193 */    if (c < 'ங') return false; if (c <= 'ச') return true;
/* 194:194 */    if (c == 'ஜ') return true;
/* 195:195 */    if (c < 'ஞ') return false; if (c <= 'ட') return true;
/* 196:196 */    if (c < 'ண') return false; if (c <= 'த') return true;
/* 197:197 */    if (c < 'ந') return false; if (c <= 'ப') return true;
/* 198:198 */    if (c < 'ம') return false; if (c <= 'வ') return true;
/* 199:199 */    if (c < 'ஷ') return false; if (c <= 'ஹ') return true;
/* 200:200 */    if (c < 'అ') return false; if (c <= 'ఌ') return true;
/* 201:201 */    if (c < 'ఎ') return false; if (c <= 'ఐ') return true;
/* 202:202 */    if (c < 'ఒ') return false; if (c <= 'న') return true;
/* 203:203 */    if (c < 'ప') return false; if (c <= 'ళ') return true;
/* 204:204 */    if (c < 'వ') return false; if (c <= 'హ') return true;
/* 205:205 */    if (c < 'ౠ') return false; if (c <= 'ౡ') return true;
/* 206:206 */    if (c < 'ಅ') return false; if (c <= 'ಌ') return true;
/* 207:207 */    if (c < 'ಎ') return false; if (c <= 'ಐ') return true;
/* 208:208 */    if (c < 'ಒ') return false; if (c <= 'ನ') return true;
/* 209:209 */    if (c < 'ಪ') return false; if (c <= 'ಳ') return true;
/* 210:210 */    if (c < 'ವ') return false; if (c <= 'ಹ') return true;
/* 211:211 */    if (c == 'ೞ') return true;
/* 212:212 */    if (c < 'ೠ') return false; if (c <= 'ೡ') return true;
/* 213:213 */    if (c < 'അ') return false; if (c <= 'ഌ') return true;
/* 214:214 */    if (c < 'എ') return false; if (c <= 'ഐ') return true;
/* 215:215 */    if (c < 'ഒ') return false; if (c <= 'ന') return true;
/* 216:216 */    if (c < 'പ') return false; if (c <= 'ഹ') return true;
/* 217:217 */    if (c < 'ൠ') return false; if (c <= 'ൡ') return true;
/* 218:218 */    if (c < 'ก') return false; if (c <= 'ฮ') return true;
/* 219:219 */    if (c == 'ะ') return true;
/* 220:220 */    if (c < 'า') return false; if (c <= 'ำ') return true;
/* 221:221 */    if (c < 'เ') return false; if (c <= 'ๅ') return true;
/* 222:222 */    if (c < 'ກ') return false; if (c <= 'ຂ') return true;
/* 223:223 */    if (c == 'ຄ') return true;
/* 224:224 */    if (c < 'ງ') return false; if (c <= 'ຈ') return true;
/* 225:225 */    if (c == 'ຊ') return true;
/* 226:226 */    if (c == 'ຍ') return true;
/* 227:227 */    if (c < 'ດ') return false; if (c <= 'ທ') return true;
/* 228:228 */    if (c < 'ນ') return false; if (c <= 'ຟ') return true;
/* 229:229 */    if (c < 'ມ') return false; if (c <= 'ຣ') return true;
/* 230:230 */    if (c == 'ລ') return true;
/* 231:231 */    if (c == 'ວ') return true;
/* 232:232 */    if (c < 'ສ') return false; if (c <= 'ຫ') return true;
/* 233:233 */    if (c < 'ອ') return false; if (c <= 'ຮ') return true;
/* 234:234 */    if (c == 'ະ') return true;
/* 235:235 */    if (c < 'າ') return false; if (c <= 'ຳ') return true;
/* 236:236 */    if (c == 'ຽ') return true;
/* 237:237 */    if (c < 'ເ') return false; if (c <= 'ໄ') return true;
/* 238:238 */    if (c < 'ཀ') return false; if (c <= 'ཇ') return true;
/* 239:239 */    if (c < 'ཉ') return false; if (c <= 'ཀྵ') return true;
/* 240:240 */    if (c < 'Ⴀ') return false; if (c <= 'Ⴥ') return true;
/* 241:241 */    if (c < 'ა') return false; if (c <= 'ჶ') return true;
/* 242:242 */    if (c == 'ᄀ') return true;
/* 243:243 */    if (c < 'ᄂ') return false; if (c <= 'ᄃ') return true;
/* 244:244 */    if (c < 'ᄅ') return false; if (c <= 'ᄇ') return true;
/* 245:245 */    if (c == 'ᄉ') return true;
/* 246:246 */    if (c < 'ᄋ') return false; if (c <= 'ᄌ') return true;
/* 247:247 */    if (c < 'ᄎ') return false; if (c <= 'ᄒ') return true;
/* 248:248 */    if (c == 'ᄼ') return true;
/* 249:249 */    if (c == 'ᄾ') return true;
/* 250:250 */    if (c == 'ᅀ') return true;
/* 251:251 */    if (c == 'ᅌ') return true;
/* 252:252 */    if (c == 'ᅎ') return true;
/* 253:253 */    if (c == 'ᅐ') return true;
/* 254:254 */    if (c < 'ᅔ') return false; if (c <= 'ᅕ') return true;
/* 255:255 */    if (c == 'ᅙ') return true;
/* 256:256 */    if (c < 'ᅟ') return false; if (c <= 'ᅡ') return true;
/* 257:257 */    if (c == 'ᅣ') return true;
/* 258:258 */    if (c == 'ᅥ') return true;
/* 259:259 */    if (c == 'ᅧ') return true;
/* 260:260 */    if (c == 'ᅩ') return true;
/* 261:261 */    if (c < 'ᅭ') return false; if (c <= 'ᅮ') return true;
/* 262:262 */    if (c < 'ᅲ') return false; if (c <= 'ᅳ') return true;
/* 263:263 */    if (c == 'ᅵ') return true;
/* 264:264 */    if (c == 'ᆞ') return true;
/* 265:265 */    if (c == 'ᆨ') return true;
/* 266:266 */    if (c == 'ᆫ') return true;
/* 267:267 */    if (c < 'ᆮ') return false; if (c <= 'ᆯ') return true;
/* 268:268 */    if (c < 'ᆷ') return false; if (c <= 'ᆸ') return true;
/* 269:269 */    if (c == 'ᆺ') return true;
/* 270:270 */    if (c < 'ᆼ') return false; if (c <= 'ᇂ') return true;
/* 271:271 */    if (c == 'ᇫ') return true;
/* 272:272 */    if (c == 'ᇰ') return true;
/* 273:273 */    if (c == 'ᇹ') return true;
/* 274:274 */    if (c < 'Ḁ') return false; if (c <= 'ẛ') return true;
/* 275:275 */    if (c < 'Ạ') return false; if (c <= 'ỹ') return true;
/* 276:276 */    if (c < 'ἀ') return false; if (c <= 'ἕ') return true;
/* 277:277 */    if (c < 'Ἐ') return false; if (c <= 'Ἕ') return true;
/* 278:278 */    if (c < 'ἠ') return false; if (c <= 'ὅ') return true;
/* 279:279 */    if (c < 'Ὀ') return false; if (c <= 'Ὅ') return true;
/* 280:280 */    if (c < 'ὐ') return false; if (c <= 'ὗ') return true;
/* 281:281 */    if (c == 'Ὑ') return true;
/* 282:282 */    if (c == 'Ὓ') return true;
/* 283:283 */    if (c == 'Ὕ') return true;
/* 284:284 */    if (c < 'Ὗ') return false; if (c <= 'ώ') return true;
/* 285:285 */    if (c < 'ᾀ') return false; if (c <= 'ᾴ') return true;
/* 286:286 */    if (c < 'ᾶ') return false; if (c <= 'ᾼ') return true;
/* 287:287 */    if (c == 'ι') return true;
/* 288:288 */    if (c < 'ῂ') return false; if (c <= 'ῄ') return true;
/* 289:289 */    if (c < 'ῆ') return false; if (c <= 'ῌ') return true;
/* 290:290 */    if (c < 'ῐ') return false; if (c <= 'ΐ') return true;
/* 291:291 */    if (c < 'ῖ') return false; if (c <= 'Ί') return true;
/* 292:292 */    if (c < 'ῠ') return false; if (c <= 'Ῥ') return true;
/* 293:293 */    if (c < 'ῲ') return false; if (c <= 'ῴ') return true;
/* 294:294 */    if (c < 'ῶ') return false; if (c <= 'ῼ') return true;
/* 295:295 */    if (c == 'Ω') return true;
/* 296:296 */    if (c < 'K') return false; if (c <= 'Å') return true;
/* 297:297 */    if (c == '℮') return true;
/* 298:298 */    if (c < 'ↀ') return false; if (c <= 'ↂ') return true;
/* 299:299 */    if (c == '〇') return true;
/* 300:300 */    if (c < '〡') return false; if (c <= '〩') return true;
/* 301:301 */    if (c < 'ぁ') return false; if (c <= 'ゔ') return true;
/* 302:302 */    if (c < 'ァ') return false; if (c <= 'ヺ') return true;
/* 303:303 */    if (c < 'ㄅ') return false; if (c <= 'ㄬ') return true;
/* 304:304 */    if (c < '一') return false; if (c <= 40869) return true;
/* 305:305 */    if (c < 44032) return false; if (c <= 55203) { return true;
/* 306:    */    }
/* 307:307 */    return false;
/* 308:    */  }
/* 309:    */  
/* 320:    */  static boolean isXMLCombiningChar(char c)
/* 321:    */  {
/* 322:322 */    if (c < '̀') return false; if (c <= 'ͅ') return true;
/* 323:323 */    if (c < '͠') return false; if (c <= '͡') return true;
/* 324:324 */    if (c < '҃') return false; if (c <= '҆') return true;
/* 325:325 */    if (c < '֑') return false; if (c <= '֡') { return true;
/* 326:    */    }
/* 327:327 */    if (c < '֣') return false; if (c <= 'ֹ') return true;
/* 328:328 */    if (c < 'ֻ') return false; if (c <= 'ֽ') return true;
/* 329:329 */    if (c == 'ֿ') return true;
/* 330:330 */    if (c < 'ׁ') return false; if (c <= 'ׂ') { return true;
/* 331:    */    }
/* 332:332 */    if (c == 'ׄ') return true;
/* 333:333 */    if (c < 'ً') return false; if (c <= 'ْ') return true;
/* 334:334 */    if (c == 'ٰ') return true;
/* 335:335 */    if (c < 'ۖ') return false; if (c <= 'ۜ') { return true;
/* 336:    */    }
/* 337:337 */    if (c < '۝') return false; if (c <= '۟') return true;
/* 338:338 */    if (c < '۠') return false; if (c <= 'ۤ') return true;
/* 339:339 */    if (c < 'ۧ') return false; if (c <= 'ۨ') { return true;
/* 340:    */    }
/* 341:341 */    if (c < '۪') return false; if (c <= 'ۭ') return true;
/* 342:342 */    if (c < 'ँ') return false; if (c <= 'ः') return true;
/* 343:343 */    if (c == '़') return true;
/* 344:344 */    if (c < 'ा') return false; if (c <= 'ौ') { return true;
/* 345:    */    }
/* 346:346 */    if (c == '्') return true;
/* 347:347 */    if (c < '॑') return false; if (c <= '॔') return true;
/* 348:348 */    if (c < 'ॢ') return false; if (c <= 'ॣ') return true;
/* 349:349 */    if (c < 'ঁ') return false; if (c <= 'ঃ') { return true;
/* 350:    */    }
/* 351:351 */    if (c == '়') return true;
/* 352:352 */    if (c == 'া') return true;
/* 353:353 */    if (c == 'ি') return true;
/* 354:354 */    if (c < 'ী') return false; if (c <= 'ৄ') return true;
/* 355:355 */    if (c < 'ে') return false; if (c <= 'ৈ') { return true;
/* 356:    */    }
/* 357:357 */    if (c < 'ো') return false; if (c <= '্') return true;
/* 358:358 */    if (c == 'ৗ') return true;
/* 359:359 */    if (c < 'ৢ') return false; if (c <= 'ৣ') return true;
/* 360:360 */    if (c == 'ਂ') return true;
/* 361:361 */    if (c == '਼') { return true;
/* 362:    */    }
/* 363:363 */    if (c == 'ਾ') return true;
/* 364:364 */    if (c == 'ਿ') return true;
/* 365:365 */    if (c < 'ੀ') return false; if (c <= 'ੂ') return true;
/* 366:366 */    if (c < 'ੇ') return false; if (c <= 'ੈ') { return true;
/* 367:    */    }
/* 368:368 */    if (c < 'ੋ') return false; if (c <= '੍') return true;
/* 369:369 */    if (c < 'ੰ') return false; if (c <= 'ੱ') return true;
/* 370:370 */    if (c < 'ઁ') return false; if (c <= 'ઃ') return true;
/* 371:371 */    if (c == '઼') { return true;
/* 372:    */    }
/* 373:373 */    if (c < 'ા') return false; if (c <= 'ૅ') return true;
/* 374:374 */    if (c < 'ે') return false; if (c <= 'ૉ') return true;
/* 375:375 */    if (c < 'ો') return false; if (c <= '્') { return true;
/* 376:    */    }
/* 377:377 */    if (c < 'ଁ') return false; if (c <= 'ଃ') return true;
/* 378:378 */    if (c == '଼') return true;
/* 379:379 */    if (c < 'ା') return false; if (c <= 'ୃ') return true;
/* 380:380 */    if (c < 'େ') return false; if (c <= 'ୈ') { return true;
/* 381:    */    }
/* 382:382 */    if (c < 'ୋ') return false; if (c <= '୍') return true;
/* 383:383 */    if (c < 'ୖ') return false; if (c <= 'ୗ') return true;
/* 384:384 */    if (c < 'ஂ') return false; if (c <= 'ஃ') { return true;
/* 385:    */    }
/* 386:386 */    if (c < 'ா') return false; if (c <= 'ூ') return true;
/* 387:387 */    if (c < 'ெ') return false; if (c <= 'ை') return true;
/* 388:388 */    if (c < 'ொ') return false; if (c <= '்') return true;
/* 389:389 */    if (c == 'ௗ') { return true;
/* 390:    */    }
/* 391:391 */    if (c < 'ఁ') return false; if (c <= 'ః') return true;
/* 392:392 */    if (c < 'ా') return false; if (c <= 'ౄ') return true;
/* 393:393 */    if (c < 'ె') return false; if (c <= 'ై') { return true;
/* 394:    */    }
/* 395:395 */    if (c < 'ొ') return false; if (c <= '్') return true;
/* 396:396 */    if (c < 'ౕ') return false; if (c <= 'ౖ') return true;
/* 397:397 */    if (c < 'ಂ') return false; if (c <= 'ಃ') { return true;
/* 398:    */    }
/* 399:399 */    if (c < 'ಾ') return false; if (c <= 'ೄ') return true;
/* 400:400 */    if (c < 'ೆ') return false; if (c <= 'ೈ') return true;
/* 401:401 */    if (c < 'ೊ') return false; if (c <= '್') { return true;
/* 402:    */    }
/* 403:403 */    if (c < 'ೕ') return false; if (c <= 'ೖ') return true;
/* 404:404 */    if (c < 'ം') return false; if (c <= 'ഃ') return true;
/* 405:405 */    if (c < 'ാ') return false; if (c <= 'ൃ') { return true;
/* 406:    */    }
/* 407:407 */    if (c < 'െ') return false; if (c <= 'ൈ') return true;
/* 408:408 */    if (c < 'ൊ') return false; if (c <= '്') return true;
/* 409:409 */    if (c == 'ൗ') return true;
/* 410:410 */    if (c == 'ั') { return true;
/* 411:    */    }
/* 412:412 */    if (c < 'ิ') return false; if (c <= 'ฺ') return true;
/* 413:413 */    if (c < '็') return false; if (c <= '๎') return true;
/* 414:414 */    if (c == 'ັ') return true;
/* 415:415 */    if (c < 'ິ') return false; if (c <= 'ູ') { return true;
/* 416:    */    }
/* 417:417 */    if (c < 'ົ') return false; if (c <= 'ຼ') return true;
/* 418:418 */    if (c < '່') return false; if (c <= 'ໍ') return true;
/* 419:419 */    if (c < '༘') return false; if (c <= '༙') return true;
/* 420:420 */    if (c == '༵') { return true;
/* 421:    */    }
/* 422:422 */    if (c == '༷') return true;
/* 423:423 */    if (c == '༹') return true;
/* 424:424 */    if (c == '༾') return true;
/* 425:425 */    if (c == '༿') return true;
/* 426:426 */    if (c < 'ཱ') return false; if (c <= '྄') { return true;
/* 427:    */    }
/* 428:428 */    if (c < '྆') return false; if (c <= 'ྋ') return true;
/* 429:429 */    if (c < 'ྐ') return false; if (c <= 'ྕ') return true;
/* 430:430 */    if (c == 'ྗ') return true;
/* 431:431 */    if (c < 'ྙ') return false; if (c <= 'ྭ') { return true;
/* 432:    */    }
/* 433:433 */    if (c < 'ྱ') return false; if (c <= 'ྷ') return true;
/* 434:434 */    if (c == 'ྐྵ') return true;
/* 435:435 */    if (c < '⃐') return false; if (c <= '⃜') return true;
/* 436:436 */    if (c == '⃡') { return true;
/* 437:    */    }
/* 438:438 */    if (c < '〪') return false; if (c <= '〯') return true;
/* 439:439 */    if (c == '゙') return true;
/* 440:440 */    if (c == '゚') { return true;
/* 441:    */    }
/* 442:442 */    return false;
/* 443:    */  }
/* 444:    */  
/* 454:    */  static boolean isXMLExtender(char c)
/* 455:    */  {
/* 456:456 */    if (c < '¶') { return false;
/* 457:    */    }
/* 458:    */    
/* 459:459 */    if (c == '·') return true;
/* 460:460 */    if (c == 'ː') return true;
/* 461:461 */    if (c == 'ˑ') return true;
/* 462:462 */    if (c == '·') return true;
/* 463:463 */    if (c == 'ـ') return true;
/* 464:464 */    if (c == 'ๆ') return true;
/* 465:465 */    if (c == 'ໆ') return true;
/* 466:466 */    if (c == '々') { return true;
/* 467:    */    }
/* 468:468 */    if (c < '〱') return false; if (c <= '〵') return true;
/* 469:469 */    if (c < 'ゝ') return false; if (c <= 'ゞ') return true;
/* 470:470 */    if (c < 'ー') return false; if (c <= 'ヾ') { return true;
/* 471:    */    }
/* 472:472 */    return false;
/* 473:    */  }
/* 474:    */  
/* 483:    */  static boolean isXMLDigit(char c)
/* 484:    */  {
/* 485:485 */    if (c < '0') return false; if (c <= '9') return true;
/* 486:486 */    if (c < '٠') return false; if (c <= '٩') return true;
/* 487:487 */    if (c < '۰') return false; if (c <= '۹') return true;
/* 488:488 */    if (c < '०') return false; if (c <= '९') { return true;
/* 489:    */    }
/* 490:490 */    if (c < '০') return false; if (c <= '৯') return true;
/* 491:491 */    if (c < '੦') return false; if (c <= '੯') return true;
/* 492:492 */    if (c < '૦') return false; if (c <= '૯') { return true;
/* 493:    */    }
/* 494:494 */    if (c < '୦') return false; if (c <= '୯') return true;
/* 495:495 */    if (c < '௧') return false; if (c <= '௯') return true;
/* 496:496 */    if (c < '౦') return false; if (c <= '౯') { return true;
/* 497:    */    }
/* 498:498 */    if (c < '೦') return false; if (c <= '೯') return true;
/* 499:499 */    if (c < '൦') return false; if (c <= '൯') return true;
/* 500:500 */    if (c < '๐') return false; if (c <= '๙') { return true;
/* 501:    */    }
/* 502:502 */    if (c < '໐') return false; if (c <= '໙') return true;
/* 503:503 */    if (c < '༠') return false; if (c <= '༩') { return true;
/* 504:    */    }
/* 505:505 */    return false;
/* 506:    */  }
/* 507:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.saxpath.base.Verifier
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */