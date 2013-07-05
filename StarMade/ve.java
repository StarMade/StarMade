/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ import org.schema.game.server.controller.EntityAlreadyExistsException;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.client.ClientStateInterface;
/*     */ 
/*     */ public final class ve
/*     */ {
/*     */   private Object[] a;
/*     */   public final Class a;
/*     */   public final int a;
/*     */ 
/*     */   public static String a(String paramString)
/*     */   {
/*  26 */     return "ENTITY_SHIP_" + paramString;
/*     */   }
/*     */   public static String b(String paramString) {
/*  29 */     return "ENTITY_SPACESTATION_" + paramString;
/*     */   }
/*     */   public static String c(String paramString) {
/*  32 */     return "ENTITY_FLOATINGROCK_" + paramString;
/*     */   }
/*     */   public static String d(String paramString) {
/*  35 */     return "ENTITY_SHOP_" + paramString;
/*     */   }
/*     */ 
/*     */   public static boolean a(StateInterface paramStateInterface, String paramString)
/*     */   {
/*  42 */     File localFile = new File(vg.a + paramString + ".ent");
/*  43 */     if (((paramStateInterface instanceof vg)) && 
/*  44 */       (((vg)paramStateInterface).c().containsKey(paramString))) {
/*  45 */       throw new EntityAlreadyExistsException(paramString);
/*     */     }
/*     */ 
/*  48 */     if (localFile.exists()) {
/*  49 */       throw new EntityAlreadyExistsException(paramString);
/*     */     }
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   public static ki a(StateInterface paramStateInterface, String paramString1, int paramInt1, String paramString2, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*     */   {
/*     */     ki localki;
/*  56 */     (
/*  57 */       localki = new ki(paramStateInterface))
/*  57 */       .setUniqueIdentifier(paramString1);
/*  58 */     localki.getMinPos().b(new q(paramInt2, paramInt3, paramInt4));
/*  59 */     localki.getMaxPos().b(new q(paramInt5, paramInt6, paramInt7));
/*  60 */     localki.setCreatorId(kk.b.ordinal());
/*  61 */     localki.setId(paramStateInterface.getNextFreeObjectId());
/*  62 */     localki.setSectorId(paramInt1);
/*  63 */     localki.setRealName(paramString2);
/*  64 */     localki.initialize();
/*  65 */     localki.getInitialTransform().setFromOpenGLMatrix(paramArrayOfFloat);
/*  66 */     return localki;
/*     */   }
/*     */ 
/*     */   public static kd a(StateInterface paramStateInterface, String paramString1, int paramInt1, String paramString2, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, String paramString3)
/*     */   {
/*     */     kd localkd;
/*  69 */     (
/*  70 */       localkd = new kd(paramStateInterface))
/*  70 */       .setUniqueIdentifier(paramString1);
/*  71 */     localkd.getMinPos().b(new q(paramInt2, paramInt3, paramInt4));
/*  72 */     localkd.getMaxPos().b(new q(paramInt5, paramInt6, paramInt7));
/*  73 */     localkd.setId(paramStateInterface.getNextFreeObjectId());
/*  74 */     localkd.setSectorId(paramInt1);
/*  75 */     localkd.setRealName(paramString2);
/*  76 */     localkd.initialize();
/*  77 */     localkd.getInitialTransform().setFromOpenGLMatrix(paramArrayOfFloat);
/*     */ 
/*  80 */     localkd.setSpawner(paramString3);
/*  81 */     return localkd;
/*     */   }
/*     */ 
/*     */   public static boolean a(String paramString)
/*     */   {
/* 123 */     return (paramString.length() > 0) && (paramString.matches("[a-zA-Z0-9_-]+"));
/*     */   }
/*     */ 
/*     */   public ve(int paramInt, Object[] paramArrayOfObject, Class paramClass)
/*     */   {
/* 131 */     this.jdField_a_of_type_Int = paramInt;
/* 132 */     this.jdField_a_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
/* 133 */     this.jdField_a_of_type_JavaLangClass = paramClass;
/*     */   }
/*     */ 
/*     */   public final kd a(vg paramvg)
/*     */   {
/* 146 */     Object localObject1 = new float[16];
/* 147 */     int i = 0;
/* 148 */     for (int j = 0; j < 16; j++) {
/* 149 */       localObject1[j] = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
/*     */     }
/* 151 */     j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 152 */     int k = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 153 */     int m = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 155 */     int n = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 156 */     int i1 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 157 */     int i2 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 159 */     int i3 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 161 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)];
/* 162 */     Object localObject2 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[i];
/*     */ 
/* 164 */     lE locallE = (lE)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(i3);
/*     */ 
/* 166 */     a(paramvg, str);
/*     */ 
/* 169 */     localObject1 = a(paramvg, str, locallE.c(), (String)localObject2, (float[])localObject1, j, k, m, n, i1, i2, locallE.getUniqueIdentifier());
/*     */ 
/* 172 */     (
/* 173 */       localObject2 = new mw((SegmentController)localObject1))
/* 173 */       .a(new SegmentData(paramvg instanceof ClientStateInterface));
/* 174 */     ((mw)localObject2).a().setSegment((Segment)localObject2);
/* 175 */     ((mw)localObject2).a().setInfoElement((byte)8, (byte)8, (byte)8, (short)1, true);
/* 176 */     ((mw)localObject2).a(System.currentTimeMillis());
/* 177 */     ((kd)localObject1).getSegmentBuffer().a((Segment)localObject2);
/* 178 */     ((kd)localObject1).getSegmentBuffer().b((Segment)localObject2);
/*     */ 
/* 180 */     return localObject1;
/*     */   }
/*     */   public final kn a(vg paramvg) {
/* 183 */     Object localObject1 = new float[16];
/* 184 */     int i = 0;
/* 185 */     for (lE locallE1 = 0; locallE1 < 16; locallE1++) {
/* 186 */       localObject1[locallE1] = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
/*     */     }
/* 188 */     locallE1 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 189 */     String str1 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 190 */     int j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 192 */     lE locallE2 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 193 */     String str2 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 194 */     int k = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 196 */     int m = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 198 */     String str3 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)];
/* 199 */     Object localObject2 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[i];
/*     */ 
/* 202 */     a(paramvg, str3);
/* 203 */     lE locallE3 = (lE)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(m);
/*     */ 
/* 205 */     String str4 = locallE3.getUniqueIdentifier(); int n = k; str3 = str2; locallE3 = locallE2; k = j; str2 = str1; locallE2 = locallE1; Object localObject4 = localObject1; Object localObject3 = localObject2; locallE1 = locallE3.c(); localObject2 = str3; localObject1 = paramvg;
/*     */     kn localkn;
/* 205 */     (localkn = new kn((StateInterface)localObject1)).setUniqueIdentifier((String)localObject2); localkn.getMinPos().b(new q(locallE2, str2, k)); localkn.getMaxPos().b(new q(locallE3, str3, n)); localkn.setId(((StateInterface)localObject1).getNextFreeObjectId()); localkn.setSectorId(locallE1); localkn.setRealName(localObject3); localkn.initialize(); localkn.getInitialTransform().setFromOpenGLMatrix(localObject4); localkn.setSpawner(str4); localObject1 = localkn;
/*     */ 
/* 208 */     (
/* 209 */       localObject2 = new mw((SegmentController)localObject1))
/* 209 */       .a(new SegmentData(paramvg instanceof ClientStateInterface));
/* 210 */     ((mw)localObject2).a().setSegment((Segment)localObject2);
/* 211 */     ((mw)localObject2).a().setInfoElement((byte)8, (byte)8, (byte)8, (short)1, true);
/* 212 */     ((mw)localObject2).a(System.currentTimeMillis());
/* 213 */     ((kn)localObject1).getSegmentBuffer().a((Segment)localObject2);
/*     */ 
/* 215 */     return localObject1;
/*     */   }
/*     */   public final ki a(vg paramvg) {
/* 218 */     Object localObject1 = new float[16];
/* 219 */     int i = 0;
/* 220 */     for (int j = 0; j < 16; j++) {
/* 221 */       localObject1[j] = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
/*     */     }
/* 223 */     j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 224 */     int k = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 225 */     int m = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 227 */     int n = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 228 */     int i1 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 229 */     int i2 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 231 */     int i3 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/*     */ 
/* 233 */     String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)];
/* 234 */     Object localObject2 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[i];
/*     */ 
/* 236 */     a(paramvg, str);
/* 237 */     lE locallE = (lE)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(i3);
/*     */ 
/* 239 */     localObject1 = a(paramvg, str, locallE.c(), (String)localObject2, (float[])localObject1, j, k, m, n, i1, i2);
/*     */ 
/* 242 */     (
/* 243 */       localObject2 = new mw((SegmentController)localObject1))
/* 243 */       .a(new SegmentData(paramvg instanceof ClientStateInterface));
/* 244 */     ((mw)localObject2).a().setSegment((Segment)localObject2);
/* 245 */     ((mw)localObject2).a().setInfoElement((byte)8, (byte)8, (byte)8, (short)5, true);
/* 246 */     ((mw)localObject2).a(System.currentTimeMillis());
/* 247 */     ((ki)localObject1).getSegmentBuffer().a((Segment)localObject2);
/*     */ 
/* 249 */     return localObject1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ve
 * JD-Core Version:    0.6.2
 */