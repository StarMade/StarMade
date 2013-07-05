/*     */ import it.unimi.dsi.fastutil.shorts.Short2IntArrayMap;
/*     */ import java.io.DataInputStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.game.common.data.element.ControlElementMap;
/*     */ import org.schema.game.common.data.element.ControlElementMapper;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.server.data.blueprintnw.BlueprintEntry;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ 
/*     */ public class vn
/*     */   implements vc
/*     */ {
/*     */   public xO a;
/*     */   public String a;
/*     */   public int a;
/*     */   private ControlElementMapper jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
/*     */   private int b;
/*     */   public Short2IntArrayMap a;
/*     */   private int c;
/*     */   private ArrayList jdField_a_of_type_JavaUtilArrayList;
/*     */ 
/*     */   public static vD a(StateInterface paramStateInterface, BlueprintEntry paramBlueprintEntry, String paramString1, String paramString2, float[] paramArrayOfFloat, int paramInt, q paramq1, q paramq2, String paramString3)
/*     */   {
/*  80 */     return paramBlueprintEntry.a().jdField_a_of_type_Vt.a(paramStateInterface, paramBlueprintEntry, paramString1, paramString2, paramArrayOfFloat, paramInt, paramq1, paramq2, paramString3);
/*     */   }
/*     */ 
/*     */   public vn(DataInputStream paramDataInputStream)
/*     */   {
/*  50 */     this.jdField_a_of_type_Int = -1;
/*     */ 
/*  54 */     vo.j.a();
/*     */ 
/*  56 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*     */     Object localObject;
/*  91 */     if (!(
/*  91 */       localObject = paramDataInputStream.readUTF())
/*  91 */       .contains(":")) {
/*  92 */       this.jdField_a_of_type_JavaLangString = ((String)localObject);
/*  93 */       this.c = 0;
/*     */     } else {
/*  95 */       localObject = ((String)localObject).split(":");
/*  96 */       this.c = Integer.parseInt(localObject[1]);
/*  97 */       this.jdField_a_of_type_JavaLangString = localObject[0];
/*     */     }
/*  99 */     if (this.c < 3) {
/* 100 */       this.jdField_a_of_type_JavaUtilArrayList.add("v0.061 to v0.062");
/*     */     }
/* 102 */     if (this.c < 4) {
/* 103 */       this.jdField_a_of_type_JavaUtilArrayList.add("v0.078 to v0.079");
/*     */     }
/* 105 */     if (this.c < 6) {
/* 106 */       this.jdField_a_of_type_JavaUtilArrayList.add("v0.0897 to v0.0898");
/*     */     }
/* 108 */     if (this.c > 4) {
/* 109 */       paramDataInputStream.readBoolean();
/* 110 */       paramDataInputStream.readBoolean();
/* 111 */       paramDataInputStream.readUTF();
/* 112 */       paramDataInputStream.readInt();
/* 113 */       this.jdField_a_of_type_Int = paramDataInputStream.readInt(); } else {
/* 114 */       if (this.c > 1) {
/* 115 */         paramDataInputStream.readBoolean();
/* 116 */         paramDataInputStream.readBoolean();
/* 117 */         paramDataInputStream.readUTF();
/* 118 */         paramDataInputStream.readInt();
/*     */       }
/* 120 */       else if (this.c > 0) {
/* 121 */         paramDataInputStream.readBoolean();
/* 122 */         paramDataInputStream.readBoolean();
/* 123 */       }this.jdField_a_of_type_Int = vK.jdField_a_of_type_VK.ordinal();
/*     */     }
/*     */ 
/* 133 */     this.jdField_a_of_type_XO = new xO(new Vector3f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat()), new Vector3f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat()));
/*     */ 
/* 136 */     int i = paramDataInputStream.readInt();
/* 137 */     this.b = 0;
/* 138 */     this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap = new Short2IntArrayMap();
/*     */ 
/* 140 */     for (int j = 0; j < i; j++) {
/* 141 */       short s = (short)Math.abs(paramDataInputStream.readShort());
/* 142 */       int k = paramDataInputStream.readInt();
/* 143 */       if (ElementKeyMap.exists(s))
/*     */       {
/* 145 */         if (Integer.valueOf(this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.get(s)) == null)
/*     */         {
/* 146 */           this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.put(s, 0);
/*     */         }
/* 148 */         this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.put(s, this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.get(s) + k);
/* 149 */         this.b = ((int)(this.b + ElementKeyMap.getInfo(s).getPrice() * k));
/*     */       }
/*     */     }
/*     */ 
/* 153 */     Ad localAd = Ad.a(paramDataInputStream, false);
/* 154 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper = new ControlElementMapper();
/* 155 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper = ControlElementMap.mapFromTag(localAd, this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 169 */     return ((vn)paramObject).jdField_a_of_type_JavaLangString.equals(this.jdField_a_of_type_JavaLangString);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 176 */     return this.jdField_a_of_type_JavaLangString.hashCode();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 183 */     return this.jdField_a_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final ControlElementMapper a()
/*     */   {
/* 271 */     return this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
/*     */   }
/*     */ 
/*     */   public final int a() {
/* 275 */     return this.b;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  41 */     vn.class.desiredAssertionStatus();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vn
 * JD-Core Version:    0.6.2
 */