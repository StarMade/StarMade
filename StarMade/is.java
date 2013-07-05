/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.game.common.data.element.BlockFactory;
/*     */ import org.schema.game.common.data.element.ElementCategory;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.element.ElementParser;
/*     */ import org.schema.game.common.data.element.FactoryResource;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public class is extends yr
/*     */   implements Observer
/*     */ {
/*     */   private final yA jdField_a_of_type_YA;
/*     */   private HashMap jdField_a_of_type_JavaUtilHashMap;
/*     */   private boolean jdField_a_of_type_Boolean;
/*     */ 
/*     */   public is(ClientState paramClientState, HashMap paramHashMap)
/*     */   {
/*  33 */     super(paramClientState);
/*  34 */     this.jdField_a_of_type_JavaUtilHashMap = paramHashMap;
/*  35 */     this.jdField_a_of_type_YA = new yA(paramClientState);
/*     */   }
/*     */ 
/*     */   private void a(ElementCategory paramElementCategory, yA paramyA, int paramInt)
/*     */   {
/*  41 */     paramyA.a(a());
/*     */     Object localObject;
/*  44 */     ElementInformation localElementInformation = null;
/*     */ 
/*  43 */     (
/*  44 */       localObject = new yP(176, 30, d.h(), a())).b = 
/*  44 */       new ArrayList();
/*  45 */     ((yP)localObject).b.add("+ " + ElementParser.getStringFromType(paramElementCategory.getCategory()));
/*     */     yP localyP;
/*  47 */     (
/*  48 */       localyP = new yP(176, 30, d.h(), a())).b = 
/*  48 */       new ArrayList();
/*  49 */     localyP.b.add("- " + ElementParser.getStringFromType(paramElementCategory.getCategory()));
/*     */ 
/*  51 */     (
/*  52 */       localObject = new yC(a(), (yz)localObject, localyP))
/*  52 */       .a().x = (paramInt * 5);
/*  53 */     int i = 1;
/*     */ 
/*  56 */     if (paramElementCategory.hasChildren()) {
/*  57 */       for (j = 0; j < paramElementCategory.getChildren().size(); j++) {
/*  58 */         a((ElementCategory)paramElementCategory.getChildren().get(j), ((yC)localObject).a(), paramInt + 1);
/*  59 */         i = 0;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  64 */     ((yC)localObject).a().a(a());
/*  65 */     int j = 0;
/*  66 */     for (paramInt = 0; paramInt < paramElementCategory.getInfoElements().size(); paramInt++)
/*     */     {
/*  68 */       if ((
/*  68 */         localElementInformation = (ElementInformation)paramElementCategory.getInfoElements().get(paramInt))
/*  68 */         .isShoppable())
/*     */       {
/*  70 */         a(((yC)localObject).a(), localElementInformation, j);
/*  71 */         j++;
/*  72 */         i = 0;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  78 */     if (i == 0) {
/*  79 */       ((yC)localObject).addObserver(this);
/*  80 */       ((yz)localObject).a = "CATEGORY";
/*  81 */       ((yC)localObject).c();
/*  82 */       ((yz)localObject).g = true;
/*     */ 
/*  85 */       paramInt = new yD((yz)localObject, (yz)localObject, a());
/*  86 */       ((yC)localObject).b(this);
/*  87 */       paramyA.a(paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(yA paramyA, ElementInformation paramElementInformation, int paramInt)
/*     */   {
/*  93 */     (
/*  94 */       paramInt = new ip(a(), paramElementInformation, paramInt))
/*  94 */       .c();
/*     */ 
/*  96 */     yy localyy = new yy(paramInt.a(), paramInt.b(), paramInt.a(), new Vector4f(1.0F, 1.0F, 1.0F, 0.2F), paramInt); paramyA = null; paramyA.a(new yD(paramInt, localyy, paramInt.a()));
/*     */ 
/*  99 */     if (paramElementInformation.getId() >= 0) {
/* 100 */       (
/* 101 */         paramyA = new iu(paramElementInformation, a(), d.n())).a = 
/* 101 */         new ArrayList();
/*     */ 
/* 103 */       if ((!b) && (paramElementInformation.getDescription() == null)) throw new AssertionError(paramElementInformation.getId() + "; " + paramElementInformation.getName());
/*     */ 
/* 106 */       paramInt = paramElementInformation.getDescription().split("\\n");
/* 107 */       for (int i = 0; i < paramInt.length; i++) {
/* 108 */         paramInt[i] = paramInt[i].replace("$ACTIVATE", cv.w.b());
/*     */ 
/* 110 */         if (paramInt[i].contains("$RESOURCES")) {
/* 111 */           paramInt[i] = paramInt[i].replace("$RESOURCES", a(paramElementInformation));
/*     */         }
/* 113 */         if (paramInt[i].contains("$ARMOUR")) {
/* 114 */           paramInt[i] = paramInt[i].replace("$ARMOUR", String.valueOf(paramElementInformation.getAmour()));
/*     */         }
/* 116 */         if (paramInt[i].contains("$HP")) {
/* 117 */           paramInt[i] = paramInt[i].replace("$HP", String.valueOf(paramElementInformation.getMaxHitPoints()));
/*     */         }
/* 119 */         paramyA.a.add(paramInt[i]);
/*     */       }
/*     */ 
/* 122 */       this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf(paramElementInformation.getId()), paramyA);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static CharSequence a(ElementInformation paramElementInformation)
/*     */   {
/* 129 */     if (paramElementInformation.getFactory() == null)
/* 130 */       return "CANNOT DISPLAY RESOURCES: NOT A FACTORY";
/*     */     StringBuffer localStringBuffer;
/* 132 */     (
/* 133 */       localStringBuffer = new StringBuffer())
/* 133 */       .append("----------Factory Production--------------\n\n");
/*     */ 
/* 135 */     for (int i = 0; i < paramElementInformation.getFactory().input.length; i++) {
/* 136 */       localStringBuffer.append("----------Product-<" + (i + 1) + ">--------------\n");
/* 137 */       localStringBuffer.append("--- Required Resources:\n");
/*     */       FactoryResource localFactoryResource;
/* 139 */       for (localFactoryResource : paramElementInformation.getFactory().input[i]) {
/* 140 */         localStringBuffer.append(localFactoryResource.count + "x " + ElementKeyMap.getInfo(localFactoryResource.type).getName() + "\n");
/*     */       }
/* 142 */       localStringBuffer.append("\n\n--- Produces Resources:\n");
/* 143 */       for (localFactoryResource : paramElementInformation.getFactory().output[i]) {
/* 144 */         localStringBuffer.append(localFactoryResource.count + "x " + ElementKeyMap.getInfo(localFactoryResource.type).getName() + "\n");
/*     */       }
/* 146 */       localStringBuffer.append("\n");
/*     */     }
/* 148 */     localStringBuffer.append("\n---------------------------------------------\n\n");
/* 149 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 159 */     if (!this.jdField_a_of_type_Boolean) {
/* 160 */       c();
/*     */     }
/* 162 */     super.b();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 167 */     return this.jdField_a_of_type_YA.a();
/*     */   }
/*     */ 
/*     */   private bz a() {
/* 171 */     return ((ct)a()).a().a.a.a;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 176 */     return this.jdField_a_of_type_YA.b();
/*     */   }
/*     */ 
/*     */   public final boolean a_()
/*     */   {
/* 181 */     if (a() != null) {
/* 182 */       return (((yz)a()).a_()) && (super.a_());
/*     */     }
/* 184 */     return super.a_();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 195 */     super.c();
/*     */     ElementCategory localElementCategory;
/* 201 */     if ((
/* 201 */       localElementCategory = ElementKeyMap.getCategoryHirarchy())
/* 201 */       .hasChildren())
/*     */     {
/* 206 */       for (i = 0; i < localElementCategory.getChildren().size(); i++) {
/* 207 */         a((ElementCategory)localElementCategory.getChildren().get(i), this.jdField_a_of_type_YA, 0);
/*     */       }
/*     */     }
/* 210 */     int i = 0;
/* 211 */     for (int j = 0; j < localElementCategory.getInfoElements().size(); j++)
/*     */     {
/*     */       ElementInformation localElementInformation;
/* 213 */       if ((
/* 213 */         localElementInformation = (ElementInformation)localElementCategory.getInfoElements().get(j))
/* 213 */         .isShoppable()) {
/* 214 */         a(this.jdField_a_of_type_YA, localElementInformation, i);
/* 215 */         i++;
/*     */       }
/*     */     }
/*     */ 
/* 219 */     a(this.jdField_a_of_type_YA);
/* 220 */     this.jdField_a_of_type_YA.c();
/* 221 */     this.jdField_a_of_type_YA.g = true;
/* 222 */     this.g = true;
/*     */ 
/* 224 */     this.jdField_a_of_type_Boolean = true;
/*     */   }
/*     */ 
/*     */   public void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 230 */     this.jdField_a_of_type_YA.f();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     is
 * JD-Core Version:    0.6.2
 */