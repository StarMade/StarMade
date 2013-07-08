/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import java.util.Arrays;
/*   3:    */import javax.vecmath.Matrix3f;
/*   4:    */import javax.vecmath.Tuple3f;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */import org.schema.common.util.ByteUtil;
/*   7:    */import org.schema.game.common.controller.SegmentController;
/*   8:    */import org.schema.game.common.data.element.Element;
/*   9:    */import org.schema.game.common.data.element.ElementInformation;
/*  10:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  11:    */import org.schema.game.common.data.world.Segment;
/*  12:    */import org.schema.game.common.data.world.SegmentData;
/*  13:    */
/*  18:    */public class le
/*  19:    */{
/*  20:    */  private byte[] jdField_a_of_type_ArrayOfByte;
/*  21:    */  private Segment jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment;
/*  22: 22 */  private int jdField_a_of_type_Int = -1;
/*  23:    */  public byte a;
/*  24:    */  
/*  25:    */  static {
/*  26: 26 */    new q();
/*  27: 27 */    new q();
/*  28: 28 */    new Vector3f();
/*  29: 29 */    new Vector3f();
/*  30:    */  }
/*  31:    */  
/*  37:    */  public byte b;
/*  38:    */  
/*  43:    */  public byte c;
/*  44:    */  
/*  49:    */  public le()
/*  50:    */  {
/*  51: 51 */    this.jdField_a_of_type_ArrayOfByte = new byte[3];
/*  52:    */  }
/*  53:    */  
/*  54:    */  public final void a(Segment paramSegment, o paramo)
/*  55:    */  {
/*  56: 56 */    this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
/*  57: 57 */    a(paramo);
/*  58:    */    
/*  60: 60 */    if (paramSegment.g())
/*  61:    */    {
/*  62: 62 */      if (this.jdField_a_of_type_ArrayOfByte == null) {
/*  63: 63 */        this.jdField_a_of_type_ArrayOfByte = new byte[3];return;
/*  64:    */      }
/*  65: 65 */      Arrays.fill(this.jdField_a_of_type_ArrayOfByte, (byte)0);return;
/*  66:    */    }
/*  67:    */    
/*  68: 68 */    this.jdField_a_of_type_ArrayOfByte = paramSegment.a().getSegmentPieceData(SegmentData.getInfoIndex(paramo), this.jdField_a_of_type_ArrayOfByte == null ? new byte[3] : this.jdField_a_of_type_ArrayOfByte);
/*  69:    */  }
/*  70:    */  
/*  72:    */  public le(Segment paramSegment, o paramo)
/*  73:    */  {
/*  74: 74 */    a(paramSegment, paramo);
/*  75:    */  }
/*  76:    */  
/*  80:    */  public le(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3)
/*  81:    */  {
/*  82: 82 */    this.jdField_a_of_type_Byte = paramByte1;
/*  83: 83 */    this.b = paramByte2;
/*  84: 84 */    this.c = paramByte3;
/*  85: 85 */    this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
/*  86: 86 */    if (paramSegment.g())
/*  87:    */    {
/*  88: 88 */      this.jdField_a_of_type_ArrayOfByte = new byte[3];return;
/*  89:    */    }
/*  90: 90 */    this.jdField_a_of_type_ArrayOfByte = paramSegment.a().getSegmentPieceData(SegmentData.getInfoIndex(paramByte1, paramByte2, paramByte3), new byte[3]);
/*  91:    */  }
/*  92:    */  
/*  99:    */  public boolean equals(Object paramObject)
/* 100:    */  {
/* 101:101 */    if ((paramObject != null) && ((paramObject instanceof le)))
/* 102:    */    {
/* 103:103 */      return ((paramObject = (le)paramObject).jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a() == this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a()) && (paramObject.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a.equals(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a)) && (paramObject.jdField_a_of_type_Byte == this.jdField_a_of_type_Byte) && (paramObject.b == this.b) && (paramObject.c == this.c);
/* 104:    */    }
/* 105:105 */    return false;
/* 106:    */  }
/* 107:    */  
/* 108:    */  public final boolean a(q paramq) {
/* 109:109 */    return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a(paramq, this.jdField_a_of_type_Byte, this.b, this.c);
/* 110:    */  }
/* 111:    */  
/* 113:113 */  public final q a(q paramq) { return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a(this.jdField_a_of_type_Byte, this.b, this.c, paramq); }
/* 114:    */  
/* 115:    */  public final byte a() {
/* 116:116 */    byte b1 = 0;
/* 117:117 */    if (this.jdField_a_of_type_Byte == 0) {
/* 118:118 */      b1 = 2;
/* 119:    */    }
/* 120:120 */    if (this.jdField_a_of_type_Byte == 15) {
/* 121:121 */      b1 = (byte)(b1 + 1);
/* 122:    */    }
/* 123:123 */    if (this.b == 0) {
/* 124:124 */      b1 = (byte)(b1 + 8);
/* 125:    */    }
/* 126:126 */    if (this.b == 15) {
/* 127:127 */      b1 = (byte)(b1 + 4);
/* 128:    */    }
/* 129:129 */    if (this.c == 0) {
/* 130:130 */      b1 = (byte)(b1 + 32);
/* 131:    */    }
/* 132:132 */    if (this.c == 15) {
/* 133:133 */      b1 = (byte)(b1 + 16);
/* 134:    */    }
/* 135:135 */    return b1;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public final byte[] a() {
/* 139:139 */    return this.jdField_a_of_type_ArrayOfByte;
/* 140:    */  }
/* 141:    */  
/* 142:142 */  public final int a() { return (short)ByteUtil.a(ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, 0), 11, 20); }
/* 143:    */  
/* 145:    */  public final int b()
/* 146:    */  {
/* 147:147 */    return this.jdField_a_of_type_Int;
/* 148:    */  }
/* 149:    */  
/* 151:151 */  public final byte b() { return (byte)ByteUtil.a(ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, 0), 21, 24); }
/* 152:    */  
/* 153:    */  public final o a(o paramo) {
/* 154:154 */    paramo.b(this.jdField_a_of_type_Byte, this.b, this.c);
/* 155:155 */    return paramo;
/* 156:    */  }
/* 157:    */  
/* 158:158 */  public final Segment a() { return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment; }
/* 159:    */  
/* 160:    */  public final void a(Transform paramTransform) {
/* 161:161 */    paramTransform.set(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a().getWorldTransform());
/* 162:162 */    Object localObject = a(new q());
/* 163:163 */    localObject = new Vector3f(((q)localObject).jdField_a_of_type_Int - 8, ((q)localObject).b - 8, ((q)localObject).c - 8);
/* 164:164 */    paramTransform.basis.transform((Tuple3f)localObject);
/* 165:165 */    paramTransform.origin.add((Tuple3f)localObject);
/* 166:    */  }
/* 167:    */  
/* 168:168 */  public final short a() { return (short)ByteUtil.a(ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, 0), 0, 11); }
/* 169:    */  
/* 173:    */  public int hashCode()
/* 174:    */  {
/* 175:175 */    le localle = this;long l = 7L + localle.jdField_a_of_type_Byte;l = 7L * l + localle.b; long tmp45_44 = (7L * l + localle.c);return this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment.a.hashCode() + (byte)(int)(tmp45_44 ^ tmp45_44 >> 8);
/* 176:    */  }
/* 177:    */  
/* 181:    */  public final boolean a()
/* 182:    */  {
/* 183:183 */    return ByteUtil.a(ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, 0), 20, 21) == 0;
/* 184:    */  }
/* 185:    */  
/* 186:186 */  public final boolean b() { return (this.jdField_a_of_type_Byte == 0) || (this.b == 0) || (this.c == 0) || (this.jdField_a_of_type_Byte == 15) || (this.b == 15) || (this.c == 15); }
/* 187:    */  
/* 188:    */  public final void a(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3) {
/* 189:189 */    this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
/* 190:190 */    le localle = this;this.jdField_a_of_type_Byte = paramByte1;localle.b = paramByte2;localle.c = paramByte3;
/* 191:191 */    if ((paramSegment.g()) || (paramSegment.a() == null))
/* 192:    */    {
/* 193:193 */      Arrays.fill(this.jdField_a_of_type_ArrayOfByte, (byte)0);return;
/* 194:    */    }
/* 195:195 */    this.jdField_a_of_type_ArrayOfByte = paramSegment.a().getSegmentPieceData(SegmentData.getInfoIndex(paramByte1, paramByte2, paramByte3), this.jdField_a_of_type_ArrayOfByte);
/* 196:    */  }
/* 197:    */  
/* 209:    */  public final void a()
/* 210:    */  {
/* 211:211 */    a(this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment, this.jdField_a_of_type_Byte, this.b, this.c);
/* 212:    */  }
/* 213:    */  
/* 217:    */  public final void a(boolean paramBoolean)
/* 218:    */  {
/* 219:219 */    ByteUtil.a(this.jdField_a_of_type_ArrayOfByte, paramBoolean ? 0 : 1, 20, 21, 0);
/* 220:    */  }
/* 221:    */  
/* 223:    */  public final void a(byte[] paramArrayOfByte)
/* 224:    */  {
/* 225:225 */    this.jdField_a_of_type_ArrayOfByte = paramArrayOfByte;
/* 226:    */  }
/* 227:    */  
/* 228:228 */  public final void a(int paramInt) { this.jdField_a_of_type_Int = paramInt; }
/* 229:    */  
/* 238:    */  public final void a(o paramo)
/* 239:    */  {
/* 240:240 */    this.jdField_a_of_type_Byte = paramo.jdField_a_of_type_Byte;
/* 241:241 */    this.b = paramo.b;
/* 242:242 */    this.c = paramo.c;
/* 243:    */  }
/* 244:    */  
/* 246:    */  public final void a(Segment paramSegment)
/* 247:    */  {
/* 248:248 */    this.jdField_a_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
/* 249:    */  }
/* 250:    */  
/* 253:    */  public String toString()
/* 254:    */  {
/* 255:255 */    return a(new q()).toString() + "[" + (a() != 0 ? ElementKeyMap.getInfo(a()).getName() : "NONE") + "]o[" + Element.getSideString(Element.orientationBackMapping[b()]) + "]";
/* 256:    */  }
/* 257:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     le
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */