/*   1:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   2:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*   4:    */import java.nio.ShortBuffer;
/*   5:    */import java.util.Collection;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Iterator;
/*   8:    */import java.util.Map;
/*   9:    */import javax.vecmath.Vector3f;
/*  10:    */import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
/*  11:    */import org.schema.game.common.controller.SegmentController;
/*  12:    */import org.schema.game.common.data.world.Segment;
/*  13:    */import org.schema.game.common.data.world.SegmentData;
/*  14:    */
/*  32:    */public final class eu
/*  33:    */{
/*  34: 34 */  private static ObjectArrayList jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
/*  35:    */  
/*  51: 51 */  private final Map jdField_a_of_type_JavaUtilMap = new HashMap();
/*  52:    */  
/*  54: 54 */  private final et[] jdField_a_of_type_ArrayOfEt = new et[64];
/*  55:    */  
/*  56:    */  private int jdField_a_of_type_Int;
/*  57:    */  private boolean jdField_a_of_type_Boolean;
/*  58:    */  
/*  59:    */  public final void a(ld paramld)
/*  60:    */  {
/*  61: 61 */    et localet = null;paramld = paramld;(localet = (et)jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(0)).jdField_a_of_type_Ld = paramld;paramld = jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.isEmpty() ? new et(paramld) : localet;
/*  62: 62 */    this.jdField_a_of_type_JavaUtilMap.put(paramld.jdField_a_of_type_Ld, paramld);
/*  63:    */  }
/*  64:    */  
/*  65:    */  public final void a(Int2ObjectOpenHashMap paramInt2ObjectOpenHashMap) {
/*  66: 66 */    Iterator localIterator1 = this.jdField_a_of_type_JavaUtilMap.values().iterator();
/*  67: 67 */    while (localIterator1.hasNext()) {
/*  68: 68 */      localObject = (et)localIterator1.next();
/*  69: 69 */      if (!paramInt2ObjectOpenHashMap.containsKey(((et)localObject).jdField_a_of_type_Ld.a().getId()))
/*  70:    */      {
/*  71: 71 */        (localObject = localObject).jdField_a_of_type_Ld = null;jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(localObject);
/*  72: 72 */        localIterator1.remove();
/*  73:    */      }
/*  74:    */    }
/*  75:    */    
/*  76: 76 */    for (Object localObject = paramInt2ObjectOpenHashMap.values().iterator(); ((Iterator)localObject).hasNext();) {
/*  77: 77 */      if ((((paramInt2ObjectOpenHashMap = (mF)((Iterator)localObject).next()) instanceof ld)) && 
/*  78: 78 */        (!this.jdField_a_of_type_JavaUtilMap.containsKey(paramInt2ObjectOpenHashMap))) {
/*  79: 79 */        a((ld)paramInt2ObjectOpenHashMap);
/*  80:    */      }
/*  81:    */    }
/*  82:    */    
/*  83: 83 */    for (int i = 0; i < this.jdField_a_of_type_Int; i++) {
/*  84: 84 */      this.jdField_a_of_type_ArrayOfEt[i] = null;
/*  85:    */    }
/*  86: 86 */    this.jdField_a_of_type_Int = 0;
/*  87: 87 */    for (Iterator localIterator2 = this.jdField_a_of_type_JavaUtilMap.values().iterator(); localIterator2.hasNext();)
/*  88:    */    {
/*  90: 90 */      if ((paramInt2ObjectOpenHashMap = (et)localIterator2.next()).a()) {
/*  91: 91 */        a(paramInt2ObjectOpenHashMap, true);
/*  92:    */      }
/*  93:    */    }
/*  94:    */  }
/*  95:    */  
/* 102:    */  public final void a()
/* 103:    */  {
/* 104:104 */    if (this.jdField_a_of_type_Boolean) {
/* 105:105 */      for (int i = 0; i < this.jdField_a_of_type_Int; i++) {
/* 106:106 */        this.jdField_a_of_type_ArrayOfEt[i].b();
/* 107:    */      }
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 119:    */  public static void b() {}
/* 120:    */  
/* 128:    */  private void a(et paramet, boolean paramBoolean)
/* 129:    */  {
/* 130:130 */    if (paramBoolean)
/* 131:    */    {
/* 132:132 */      if (this.jdField_a_of_type_Int < this.jdField_a_of_type_ArrayOfEt.length) {
/* 133:133 */        this.jdField_a_of_type_ArrayOfEt[this.jdField_a_of_type_Int] = paramet;
/* 134:134 */        this.jdField_a_of_type_Int += 1;
/* 135:    */      }
/* 136:    */      
/* 137:    */    }
/* 138:138 */    else if (this.jdField_a_of_type_Int < this.jdField_a_of_type_ArrayOfEt.length) {
/* 139:139 */      for (paramBoolean = false; paramBoolean < this.jdField_a_of_type_ArrayOfEt.length; paramBoolean++) {
/* 140:140 */        if (this.jdField_a_of_type_ArrayOfEt[paramBoolean] == paramet) {
/* 141:141 */          this.jdField_a_of_type_ArrayOfEt[paramBoolean] = this.jdField_a_of_type_ArrayOfEt[(this.jdField_a_of_type_Int - 1)];
/* 142:142 */          this.jdField_a_of_type_Int -= 1;
/* 143:143 */          break;
/* 144:    */        }
/* 145:    */      }
/* 146:    */    }
/* 147:    */    
/* 149:149 */    this.jdField_a_of_type_Boolean = (this.jdField_a_of_type_Int > 0);
/* 150:    */  }
/* 151:    */  
/* 152:    */  public final void a(mr parammr)
/* 153:    */  {
/* 154:    */    ShortBuffer localShortBuffer;
/* 155:155 */    int i = (localShortBuffer = parammr.b().a).position();
/* 156:    */    
/* 160:160 */    localShortBuffer.rewind();
/* 161:161 */    o localo = new o();
/* 162:162 */    new q();
/* 163:163 */    le localle = new le();
/* 164:    */    
/* 165:    */    et localet;
/* 166:166 */    if ((localet = (et)this.jdField_a_of_type_JavaUtilMap.get(parammr.a())) != null) {
/* 167:167 */      boolean bool1 = localet.a();
/* 168:168 */      Object localObject2 = parammr;Object localObject1 = localet; for (int m = 0; m < ((et)localObject1).jdField_a_of_type_EK.b(); m++) { ((et)localObject1).jdField_a_of_type_EK.a.a(m, ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f); if ((((Segment)localObject2).jdField_a_of_type_Q.jdField_a_of_type_Int <= ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.x + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.b <= ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.y + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.c <= ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.z + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.jdField_a_of_type_Int + 16 > ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.x + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.b + 16 > ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.y + 8.0F) && (((Segment)localObject2).jdField_a_of_type_Q.c + 16 > ((et)localObject1).jdField_a_of_type_JavaxVecmathVector3f.z + 8.0F)) { ((et)localObject1).jdField_a_of_type_EK.b(m);m--;
/* 169:    */        } }
/* 170:170 */      for (int j = 0; j < i; j++) {
/* 171:171 */        SegmentData.getPositionFromIndex(localShortBuffer.get(), localo);
/* 172:    */        
/* 173:173 */        localle.a(parammr, localo);
/* 174:174 */        localObject2 = localle;(localObject1 = localet).jdField_a_of_type_EK.b();localObject1 = ((le)localObject2).a(((et)localObject1).jdField_a_of_type_Q); eK localeK; (localeK = ((et)localObject1).jdField_a_of_type_EK).jdField_a_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);int k = localeK.a(localeK.jdField_a_of_type_JavaxVecmathVector3f, localeK.jdField_a_of_type_JavaxVecmathVector3f);localeK.a.a(k, ((q)localObject1).jdField_a_of_type_Int - kd.jdField_a_of_type_Q.jdField_a_of_type_Int, ((q)localObject1).b - kd.jdField_a_of_type_Q.b, ((q)localObject1).c - kd.jdField_a_of_type_Q.c);localeK.a.b(k, ((q)localObject1).jdField_a_of_type_Int, ((q)localObject1).b, ((q)localObject1).c);
/* 175:    */      }
/* 176:176 */      boolean bool2 = localet.a();
/* 177:177 */      if ((bool1) && (!bool2)) {
/* 178:178 */        a(localet, false);
/* 179:179 */      } else if ((!bool1) && (bool2)) {
/* 180:180 */        a(localet, true);
/* 181:    */      }
/* 182:    */    }
/* 183:    */    
/* 186:186 */    localShortBuffer.rewind();
/* 187:    */  }
/* 188:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     eu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */