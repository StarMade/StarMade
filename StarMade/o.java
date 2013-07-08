/*   1:    */public class o {
/*   2:    */  public byte a;
/*   3:    */  public byte b;
/*   4:    */  public byte c;
/*   5:    */  
/*   6:    */  public o() {}
/*   7:    */  
/*   8:    */  public o(byte paramByte1, byte paramByte2, byte paramByte3) {
/*   9:  9 */    this.a = paramByte1;this.b = paramByte2;this.c = paramByte3;
/*  10:    */  }
/*  11:    */  
/*  12: 12 */  public o(float paramFloat1, float paramFloat2, float paramFloat3) { this.a = ((byte)(int)paramFloat1);this.b = ((byte)(int)paramFloat2);this.c = ((byte)(int)paramFloat3);
/*  13:    */  }
/*  14:    */  
/*  15: 15 */  public o(o paramo) { this.a = paramo.a;
/*  16: 16 */    this.b = paramo.b;
/*  17: 17 */    this.c = paramo.c;
/*  18:    */  }
/*  19:    */  
/*  20: 20 */  public final void a(byte paramByte1, byte paramByte2, byte paramByte3) { this.a = ((byte)(this.a + paramByte1));
/*  21: 21 */    this.b = ((byte)(this.b + paramByte2));
/*  22: 22 */    this.c = ((byte)(this.c + paramByte3));
/*  23:    */  }
/*  24:    */  
/*  25:    */  public final void a(o paramo) {
/*  26: 26 */    this.a = ((byte)(this.a + paramo.a));
/*  27: 27 */    this.b = ((byte)(this.b + paramo.b));
/*  28: 28 */    this.c = ((byte)(this.c + paramo.c));
/*  29:    */  }
/*  30:    */  
/*  31: 31 */  public final void a() { this.a = ((byte)(this.a / 2));
/*  32: 32 */    this.b = ((byte)(this.b / 2));
/*  33: 33 */    this.c = ((byte)(this.c / 2));
/*  34:    */  }
/*  35:    */  
/*  43:    */  public boolean equals(Object paramObject)
/*  44:    */  {
/*  45:    */    try
/*  46:    */    {
/*  47: 47 */      paramObject = (o)paramObject;
/*  48: 48 */      return (this.a == paramObject.a) && (this.b == paramObject.b) && (this.c == paramObject.c);
/*  49:    */    } catch (NullPointerException localNullPointerException) {
/*  50: 50 */      return false; } catch (ClassCastException localClassCastException) {}
/*  51: 51 */    return false;
/*  52:    */  }
/*  53:    */  
/*  61:    */  public int hashCode()
/*  62:    */  {
/*  63: 63 */    long l = 7L + this.a;
/*  64:    */    
/*  65: 65 */    l = 7L * l + this.b; long 
/*  66: 66 */      tmp33_32 = (7L * l + this.c);
/*  67: 67 */    return (byte)(int)(tmp33_32 ^ tmp33_32 >> 8);
/*  68:    */  }
/*  69:    */  
/*  82:    */  public final void b(byte paramByte1, byte paramByte2, byte paramByte3)
/*  83:    */  {
/*  84: 84 */    this.a = paramByte1;this.b = paramByte2;this.c = paramByte3;
/*  85:    */  }
/*  86:    */  
/*  87: 87 */  public final void b(o paramo) { b(paramo.a, paramo.b, paramo.c); }
/*  88:    */  
/*  94:    */  public final void c(o paramo)
/*  95:    */  {
/*  96: 96 */    this.a = ((byte)(this.a - paramo.a));
/*  97: 97 */    this.b = ((byte)(this.b - paramo.b));
/*  98: 98 */    this.c = ((byte)(this.c - paramo.c));
/*  99:    */  }
/* 100:    */  
/* 101:    */  public String toString()
/* 102:    */  {
/* 103:103 */    return "(" + this.a + ", " + this.b + ", " + this.c + ")";
/* 104:    */  }
/* 105:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     o
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */