/*     */ import java.awt.Component;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public final class pn extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -7527303857041337040L;
/*     */ 
/*     */   public pn()
/*     */   {
/*  24 */     (
/*  25 */       localObject1 = new GridBagLayout()).columnWidths = 
/*  25 */       new int[] { 0, 0, 0 };
/*  26 */     ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*  27 */     ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/*  28 */     ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  29 */     setLayout((LayoutManager)localObject1);
/*     */ 
/*  31 */     Object localObject1 = new JLabel(" Resolution: ");
/*  32 */     (
/*  33 */       localObject2 = new GridBagConstraints()).insets = 
/*  33 */       new Insets(0, 5, 5, 5);
/*  34 */     ((GridBagConstraints)localObject2).anchor = 17;
/*  35 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  36 */     ((GridBagConstraints)localObject2).gridy = 0;
/*  37 */     add((Component)localObject1, localObject2);
/*     */ 
/*  39 */     Object localObject2 = new pH(xu.b);
/*  40 */     ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*  41 */     (
/*  42 */       localObject1 = new GridBagConstraints()).insets = 
/*  42 */       new Insets(0, 0, 5, 0);
/*  43 */     ((GridBagConstraints)localObject1).fill = 2;
/*  44 */     ((GridBagConstraints)localObject1).gridx = 1;
/*  45 */     ((GridBagConstraints)localObject1).gridy = 0;
/*  46 */     add((Component)localObject2, localObject1);
/*     */ 
/*  48 */     localObject1 = new JLabel(" AntiAliasing samples: ");
/*  49 */     (
/*  50 */       localObject2 = new GridBagConstraints()).insets = 
/*  50 */       new Insets(0, 5, 5, 5);
/*  51 */     ((GridBagConstraints)localObject2).anchor = 17;
/*  52 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  53 */     ((GridBagConstraints)localObject2).gridy = 1;
/*  54 */     add((Component)localObject1, localObject2);
/*     */ 
/*  56 */     localObject2 = new pH(xu.S);
/*  57 */     ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*  58 */     (
/*  59 */       localObject1 = new GridBagConstraints()).insets = 
/*  59 */       new Insets(0, 0, 5, 0);
/*  60 */     ((GridBagConstraints)localObject1).fill = 2;
/*  61 */     ((GridBagConstraints)localObject1).gridx = 1;
/*  62 */     ((GridBagConstraints)localObject1).gridy = 1;
/*  63 */     add((Component)localObject2, localObject1);
/*     */ 
/*  65 */     localObject1 = new JLabel(" Bloom: ");
/*  66 */     (
/*  67 */       localObject2 = new GridBagConstraints()).insets = 
/*  67 */       new Insets(0, 5, 5, 5);
/*  68 */     ((GridBagConstraints)localObject2).anchor = 17;
/*  69 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  70 */     ((GridBagConstraints)localObject2).gridy = 2;
/*  71 */     add((Component)localObject1, localObject2);
/*     */ 
/*  73 */     localObject2 = new pH(xu.U);
/*  74 */     ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*  75 */     (
/*  76 */       localObject1 = new GridBagConstraints()).insets = 
/*  76 */       new Insets(0, 0, 5, 0);
/*  77 */     ((GridBagConstraints)localObject1).fill = 2;
/*  78 */     ((GridBagConstraints)localObject1).gridx = 1;
/*  79 */     ((GridBagConstraints)localObject1).gridy = 2;
/*  80 */     add((Component)localObject2, localObject1);
/*     */ 
/*  82 */     localObject1 = new JLabel(" View Distance: ");
/*  83 */     (
/*  84 */       localObject2 = new GridBagConstraints()).insets = 
/*  84 */       new Insets(0, 5, 5, 5);
/*  85 */     ((GridBagConstraints)localObject2).anchor = 17;
/*  86 */     ((GridBagConstraints)localObject2).gridx = 0;
/*  87 */     ((GridBagConstraints)localObject2).gridy = 3;
/*  88 */     add((Component)localObject1, localObject2);
/*     */ 
/*  90 */     localObject2 = new pH(xu.e);
/*  91 */     ((JLabel)localObject1).setLabelFor((Component)localObject2);
/*  92 */     (
/*  93 */       localObject1 = new GridBagConstraints()).insets = 
/*  93 */       new Insets(0, 0, 5, 0);
/*  94 */     ((GridBagConstraints)localObject1).fill = 2;
/*  95 */     ((GridBagConstraints)localObject1).gridx = 1;
/*  96 */     ((GridBagConstraints)localObject1).gridy = 3;
/*  97 */     add((Component)localObject2, localObject1);
/*     */ 
/*  99 */     localObject1 = new JLabel(" Vertical Synch: ");
/* 100 */     (
/* 101 */       localObject2 = new GridBagConstraints()).insets = 
/* 101 */       new Insets(0, 5, 5, 5);
/* 102 */     ((GridBagConstraints)localObject2).anchor = 17;
/* 103 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 104 */     ((GridBagConstraints)localObject2).gridy = 4;
/* 105 */     add((Component)localObject1, localObject2);
/*     */ 
/* 107 */     localObject2 = new pH(xu.d);
/* 108 */     ((JLabel)localObject1).setLabelFor((Component)localObject2);
/* 109 */     (
/* 110 */       localObject1 = new GridBagConstraints()).insets = 
/* 110 */       new Insets(0, 0, 5, 0);
/* 111 */     ((GridBagConstraints)localObject1).fill = 2;
/* 112 */     ((GridBagConstraints)localObject1).gridx = 1;
/* 113 */     ((GridBagConstraints)localObject1).gridy = 4;
/* 114 */     add((Component)localObject2, localObject1);
/*     */ 
/* 116 */     localObject1 = new JLabel(" Fullscreen: ");
/* 117 */     (
/* 118 */       localObject2 = new GridBagConstraints()).anchor = 
/* 118 */       17;
/* 119 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 120 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 121 */     ((GridBagConstraints)localObject2).gridy = 5;
/* 122 */     add((Component)localObject1, localObject2);
/*     */ 
/* 124 */     localObject2 = new pH(xu.c);
/* 125 */     ((JLabel)localObject1).setLabelFor((Component)localObject2);
/* 126 */     (
/* 127 */       localObject1 = new GridBagConstraints()).insets = 
/* 127 */       new Insets(0, 0, 5, 0);
/* 128 */     ((GridBagConstraints)localObject1).fill = 2;
/* 129 */     ((GridBagConstraints)localObject1).gridx = 1;
/* 130 */     ((GridBagConstraints)localObject1).gridy = 5;
/* 131 */     add((Component)localObject2, localObject1);
/*     */ 
/* 133 */     localObject1 = new JLabel(" Fixed Framerate: ");
/* 134 */     (
/* 135 */       localObject2 = new GridBagConstraints()).anchor = 
/* 135 */       17;
/* 136 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 137 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 138 */     ((GridBagConstraints)localObject2).gridy = 6;
/* 139 */     add((Component)localObject1, localObject2);
/*     */ 
/* 141 */     localObject2 = new pH(xu.v);
/* 142 */     ((JLabel)localObject1).setLabelFor((Component)localObject2);
/* 143 */     (
/* 144 */       localObject1 = new GridBagConstraints()).insets = 
/* 144 */       new Insets(0, 0, 5, 0);
/* 145 */     ((GridBagConstraints)localObject1).fill = 2;
/* 146 */     ((GridBagConstraints)localObject1).gridx = 1;
/* 147 */     ((GridBagConstraints)localObject1).gridy = 6;
/* 148 */     add((Component)localObject2, localObject1);
/*     */ 
/* 150 */     localObject1 = new JLabel(" Max Segments in Graphics Memory: ");
/* 151 */     (
/* 152 */       localObject2 = new GridBagConstraints()).anchor = 
/* 152 */       17;
/* 153 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 154 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 155 */     ((GridBagConstraints)localObject2).gridy = 7;
/* 156 */     add((Component)localObject1, localObject2);
/*     */ 
/* 158 */     localObject1 = new pH(xu.ac);
/* 159 */     (
/* 160 */       localObject2 = new GridBagConstraints()).insets = 
/* 160 */       new Insets(0, 0, 5, 0);
/* 161 */     ((GridBagConstraints)localObject2).fill = 2;
/* 162 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 163 */     ((GridBagConstraints)localObject2).gridy = 7;
/* 164 */     add((Component)localObject1, localObject2);
/*     */ 
/* 166 */     localObject1 = new JLabel(" HiRes Background:");
/* 167 */     (
/* 168 */       localObject2 = new GridBagConstraints()).insets = 
/* 168 */       new Insets(0, 5, 5, 5);
/* 169 */     ((GridBagConstraints)localObject2).anchor = 17;
/* 170 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 171 */     ((GridBagConstraints)localObject2).gridy = 8;
/* 172 */     add((Component)localObject1, localObject2);
/*     */ 
/* 174 */     localObject1 = new pH(xu.aa);
/* 175 */     (
/* 176 */       localObject2 = new GridBagConstraints()).insets = 
/* 176 */       new Insets(0, 0, 5, 0);
/* 177 */     ((GridBagConstraints)localObject2).fill = 2;
/* 178 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 179 */     ((GridBagConstraints)localObject2).gridy = 8;
/* 180 */     add((Component)localObject1, localObject2);
/*     */ 
/* 182 */     localObject1 = new JLabel(" Tutorial:");
/* 183 */     (
/* 184 */       localObject2 = new GridBagConstraints()).anchor = 
/* 184 */       17;
/* 185 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 186 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 187 */     ((GridBagConstraints)localObject2).gridy = 9;
/* 188 */     add((Component)localObject1, localObject2);
/*     */ 
/* 190 */     localObject1 = new pH(xu.O);
/* 191 */     (
/* 192 */       localObject2 = new GridBagConstraints()).insets = 
/* 192 */       new Insets(0, 0, 5, 0);
/* 193 */     ((GridBagConstraints)localObject2).fill = 2;
/* 194 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 195 */     ((GridBagConstraints)localObject2).gridy = 9;
/* 196 */     add((Component)localObject1, localObject2);
/*     */ 
/* 198 */     localObject1 = new JLabel(" Invert Ship Mouse:");
/* 199 */     (
/* 200 */       localObject2 = new GridBagConstraints()).anchor = 
/* 200 */       17;
/* 201 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 202 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 203 */     ((GridBagConstraints)localObject2).gridy = 10;
/* 204 */     add((Component)localObject1, localObject2);
/*     */ 
/* 206 */     localObject1 = new pH(xu.X);
/* 207 */     (
/* 208 */       localObject2 = new GridBagConstraints()).fill = 
/* 208 */       2;
/* 209 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 0, 5, 0);
/* 210 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 211 */     ((GridBagConstraints)localObject2).gridy = 10;
/* 212 */     add((Component)localObject1, localObject2);
/*     */ 
/* 214 */     localObject1 = new JLabel(" Invert Mouse for everything:");
/* 215 */     (
/* 216 */       localObject2 = new GridBagConstraints()).anchor = 
/* 216 */       13;
/* 217 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 218 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 219 */     ((GridBagConstraints)localObject2).gridy = 11;
/* 220 */     add((Component)localObject1, localObject2);
/*     */ 
/* 222 */     localObject1 = new pH(xu.Y);
/* 223 */     (
/* 224 */       localObject2 = new GridBagConstraints()).insets = 
/* 224 */       new Insets(0, 0, 5, 0);
/* 225 */     ((GridBagConstraints)localObject2).fill = 2;
/* 226 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 227 */     ((GridBagConstraints)localObject2).gridy = 11;
/* 228 */     add((Component)localObject1, localObject2);
/*     */ 
/* 230 */     localObject1 = new JLabel(" Window Pos:");
/* 231 */     (
/* 232 */       localObject2 = new GridBagConstraints()).anchor = 
/* 232 */       17;
/* 233 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 234 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 235 */     ((GridBagConstraints)localObject2).gridy = 12;
/* 236 */     add((Component)localObject1, localObject2);
/*     */ 
/* 238 */     localObject1 = new pH(xu.I);
/* 239 */     (
/* 240 */       localObject2 = new GridBagConstraints()).insets = 
/* 240 */       new Insets(0, 0, 5, 0);
/* 241 */     ((GridBagConstraints)localObject2).fill = 2;
/* 242 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 243 */     ((GridBagConstraints)localObject2).gridy = 12;
/* 244 */     add((Component)localObject1, localObject2);
/*     */ 
/* 246 */     localObject1 = new JLabel(" Enable Background: ");
/* 247 */     (
/* 248 */       localObject2 = new GridBagConstraints()).anchor = 
/* 248 */       17;
/* 249 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 250 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 251 */     ((GridBagConstraints)localObject2).gridy = 13;
/* 252 */     add((Component)localObject1, localObject2);
/*     */ 
/* 254 */     localObject1 = new pH(xu.t);
/* 255 */     (
/* 256 */       localObject2 = new GridBagConstraints()).insets = 
/* 256 */       new Insets(0, 0, 5, 0);
/* 257 */     ((GridBagConstraints)localObject2).fill = 2;
/* 258 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 259 */     ((GridBagConstraints)localObject2).gridy = 13;
/* 260 */     add((Component)localObject1, localObject2);
/*     */ 
/* 262 */     localObject1 = new JLabel(" Enable Shieldhit graphics: ");
/* 263 */     (
/* 264 */       localObject2 = new GridBagConstraints()).insets = 
/* 264 */       new Insets(0, 5, 5, 5);
/* 265 */     ((GridBagConstraints)localObject2).anchor = 17;
/* 266 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 267 */     ((GridBagConstraints)localObject2).gridy = 14;
/* 268 */     add((Component)localObject1, localObject2);
/*     */ 
/* 270 */     localObject1 = new pH(xu.s);
/* 271 */     (
/* 272 */       localObject2 = new GridBagConstraints()).insets = 
/* 272 */       new Insets(0, 0, 5, 0);
/* 273 */     ((GridBagConstraints)localObject2).fill = 2;
/* 274 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 275 */     ((GridBagConstraints)localObject2).gridy = 14;
/* 276 */     add((Component)localObject1, localObject2);
/*     */ 
/* 278 */     localObject1 = new JLabel(" Enable Sound: ");
/* 279 */     (
/* 280 */       localObject2 = new GridBagConstraints()).anchor = 
/* 280 */       17;
/* 281 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 282 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 283 */     ((GridBagConstraints)localObject2).gridy = 15;
/* 284 */     add((Component)localObject1, localObject2);
/*     */ 
/* 286 */     localObject1 = new pH(xu.g);
/* 287 */     (
/* 288 */       localObject2 = new GridBagConstraints()).insets = 
/* 288 */       new Insets(0, 0, 5, 0);
/* 289 */     ((GridBagConstraints)localObject2).fill = 2;
/* 290 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 291 */     ((GridBagConstraints)localObject2).gridy = 15;
/* 292 */     add((Component)localObject1, localObject2);
/*     */ 
/* 294 */     localObject1 = new JLabel(" Sound Volume: ");
/* 295 */     (
/* 296 */       localObject2 = new GridBagConstraints()).anchor = 
/* 296 */       17;
/* 297 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 298 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 299 */     ((GridBagConstraints)localObject2).gridy = 16;
/* 300 */     add((Component)localObject1, localObject2);
/*     */ 
/* 302 */     localObject1 = new pH(xu.i);
/* 303 */     (
/* 304 */       localObject2 = new GridBagConstraints()).insets = 
/* 304 */       new Insets(0, 0, 5, 0);
/* 305 */     ((GridBagConstraints)localObject2).fill = 2;
/* 306 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 307 */     ((GridBagConstraints)localObject2).gridy = 16;
/* 308 */     add((Component)localObject1, localObject2);
/*     */ 
/* 310 */     localObject1 = new JLabel(" Field of View (FOV): ");
/* 311 */     (
/* 312 */       localObject2 = new GridBagConstraints()).anchor = 
/* 312 */       17;
/* 313 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 314 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 315 */     ((GridBagConstraints)localObject2).gridy = 17;
/* 316 */     add((Component)localObject1, localObject2);
/*     */ 
/* 318 */     localObject1 = new pH(xu.f);
/* 319 */     (
/* 320 */       localObject2 = new GridBagConstraints()).insets = 
/* 320 */       new Insets(0, 0, 5, 0);
/* 321 */     ((GridBagConstraints)localObject2).fill = 2;
/* 322 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 323 */     ((GridBagConstraints)localObject2).gridy = 17;
/* 324 */     add((Component)localObject1, localObject2);
/*     */ 
/* 326 */     localObject1 = new JLabel(" Use Frame Buffer: ");
/* 327 */     (
/* 328 */       localObject2 = new GridBagConstraints()).anchor = 
/* 328 */       17;
/* 329 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/* 330 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 331 */     ((GridBagConstraints)localObject2).gridy = 18;
/* 332 */     add((Component)localObject1, localObject2);
/*     */ 
/* 334 */     localObject1 = new pH(xu.x);
/* 335 */     (
/* 336 */       localObject2 = new GridBagConstraints()).insets = 
/* 336 */       new Insets(0, 0, 5, 0);
/* 337 */     ((GridBagConstraints)localObject2).fill = 2;
/* 338 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 339 */     ((GridBagConstraints)localObject2).gridy = 18;
/* 340 */     add((Component)localObject1, localObject2);
/*     */ 
/* 342 */     localObject1 = new JLabel(" Atmosphere Shader: ");
/* 343 */     (
/* 344 */       localObject2 = new GridBagConstraints()).anchor = 
/* 344 */       17;
/* 345 */     ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 0, 5);
/* 346 */     ((GridBagConstraints)localObject2).gridx = 0;
/* 347 */     ((GridBagConstraints)localObject2).gridy = 19;
/* 348 */     add((Component)localObject1, localObject2);
/*     */ 
/* 350 */     localObject1 = new pH(xu.r);
/* 351 */     (
/* 352 */       localObject2 = new GridBagConstraints()).fill = 
/* 352 */       2;
/* 353 */     ((GridBagConstraints)localObject2).gridx = 1;
/* 354 */     ((GridBagConstraints)localObject2).gridy = 19;
/* 355 */     add((Component)localObject1, localObject2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pn
 * JD-Core Version:    0.6.2
 */