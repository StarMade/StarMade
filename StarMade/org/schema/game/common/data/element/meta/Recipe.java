/*   1:    */package org.schema.game.common.data.element.meta;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import Aj;
/*   5:    */import java.io.DataInputStream;
/*   6:    */import java.io.DataOutputStream;
/*   7:    */
/*  11:    */public class Recipe
/*  12:    */  extends MetaObject
/*  13:    */{
/*  14:    */  short[] input;
/*  15:    */  short[] inputCount;
/*  16:    */  short[] output;
/*  17:    */  short[] outputCount;
/*  18:    */  
/*  19:    */  public void serialize(DataOutputStream paramDataOutputStream)
/*  20:    */  {
/*  21: 21 */    paramDataOutputStream.writeShort(this.input.length);
/*  22: 22 */    for (int i = 0; i < this.input.length; i++) {
/*  23: 23 */      paramDataOutputStream.writeShort(this.input[i]);
/*  24:    */    }
/*  25: 25 */    paramDataOutputStream.writeShort(this.output.length);
/*  26: 26 */    for (i = 0; i < this.output.length; i++) {
/*  27: 27 */      paramDataOutputStream.writeShort(this.output[i]);
/*  28:    */    }
/*  29:    */    
/*  30: 30 */    paramDataOutputStream.writeShort(this.inputCount.length);
/*  31: 31 */    for (i = 0; i < this.inputCount.length; i++) {
/*  32: 32 */      paramDataOutputStream.writeShort(this.inputCount[i]);
/*  33:    */    }
/*  34: 34 */    paramDataOutputStream.writeShort(this.outputCount.length);
/*  35: 35 */    for (i = 0; i < this.outputCount.length; i++) {
/*  36: 36 */      paramDataOutputStream.writeShort(this.outputCount[i]);
/*  37:    */    }
/*  38:    */  }
/*  39:    */  
/*  41:    */  public void deserialize(DataInputStream paramDataInputStream)
/*  42:    */  {
/*  43: 43 */    this.input = new short[paramDataInputStream.readShort()];
/*  44: 44 */    for (int i = 0; i < this.input.length; i++) {
/*  45: 45 */      this.input[i] = paramDataInputStream.readShort();
/*  46:    */    }
/*  47:    */    
/*  49: 49 */    this.output = new short[paramDataInputStream.readShort()];
/*  50: 50 */    for (i = 0; i < this.output.length; i++) {
/*  51: 51 */      this.output[i] = paramDataInputStream.readShort();
/*  52:    */    }
/*  53:    */    
/*  54: 54 */    this.inputCount = new short[paramDataInputStream.readShort()];
/*  55: 55 */    for (i = 0; i < this.inputCount.length; i++) {
/*  56: 56 */      this.inputCount[i] = paramDataInputStream.readShort();
/*  57:    */    }
/*  58:    */    
/*  60: 60 */    this.outputCount = new short[paramDataInputStream.readShort()];
/*  61: 61 */    for (i = 0; i < this.outputCount.length; i++) {
/*  62: 62 */      this.outputCount[i] = paramDataInputStream.readShort();
/*  63:    */    }
/*  64:    */  }
/*  65:    */  
/*  66:    */  public short getObjectBlockID()
/*  67:    */  {
/*  68: 68 */    return -10;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public Ah getBytesTag()
/*  72:    */  {
/*  73: 73 */    Ah[] arrayOfAh1 = new Ah[this.input.length + 1];
/*  74: 74 */    Ah[] arrayOfAh2 = new Ah[this.output.length + 1];
/*  75:    */    
/*  76: 76 */    arrayOfAh1[this.input.length] = new Ah(Aj.a, null, null);
/*  77: 77 */    for (int i = 0; i < this.input.length; i++) {
/*  78: 78 */      arrayOfAh1[i] = new Ah(Aj.c, null, Short.valueOf(this.input[i]));
/*  79:    */    }
/*  80:    */    
/*  82: 82 */    arrayOfAh2[this.output.length] = new Ah(Aj.a, null, null);
/*  83: 83 */    for (i = 0; i < this.output.length; i++) {
/*  84: 84 */      arrayOfAh2[i] = new Ah(Aj.c, null, Short.valueOf(this.output[i]));
/*  85:    */    }
/*  86:    */    
/*  88: 88 */    Ah[] arrayOfAh3 = new Ah[this.inputCount.length + 1];
/*  89: 89 */    Ah[] arrayOfAh4 = new Ah[this.outputCount.length + 1];
/*  90:    */    
/*  91: 91 */    arrayOfAh3[this.inputCount.length] = new Ah(Aj.a, null, null);
/*  92: 92 */    for (int j = 0; j < this.inputCount.length; j++) {
/*  93: 93 */      arrayOfAh3[j] = new Ah(Aj.c, null, Short.valueOf(this.inputCount[j]));
/*  94:    */    }
/*  95:    */    
/*  97: 97 */    arrayOfAh4[this.outputCount.length] = new Ah(Aj.a, null, null);
/*  98: 98 */    for (j = 0; j < this.outputCount.length; j++) {
/*  99: 99 */      arrayOfAh4[j] = new Ah(Aj.c, null, Short.valueOf(this.outputCount[j]));
/* 100:    */    }
/* 101:101 */    return new Ah(Aj.n, null, new Ah[] { new Ah(Aj.n, null, arrayOfAh1), new Ah(Aj.n, null, arrayOfAh2), new Ah(Aj.n, null, arrayOfAh3), new Ah(Aj.n, null, arrayOfAh4), new Ah(Aj.a, null, null) });
/* 102:    */  }
/* 103:    */  
/* 110:    */  public void fromTag(Ah paramAh)
/* 111:    */  {
/* 112:112 */    Ah[] arrayOfAh1 = (Ah[])((Ah[])paramAh.a())[0].a();
/* 113:113 */    Ah[] arrayOfAh2 = (Ah[])((Ah[])paramAh.a())[1].a();
/* 114:    */    
/* 115:115 */    this.input = new short[arrayOfAh1.length - 1];
/* 116:116 */    for (int j = 0; j < arrayOfAh1.length - 1; j++) {
/* 117:117 */      this.input[j] = ((Short)arrayOfAh1[j].a()).shortValue();
/* 118:    */    }
/* 119:    */    
/* 120:120 */    this.output = new short[arrayOfAh2.length - 1];
/* 121:121 */    for (j = 0; j < arrayOfAh2.length - 1; j++) {
/* 122:122 */      this.output[j] = ((Short)arrayOfAh2[j].a()).shortValue();
/* 123:    */    }
/* 124:    */    
/* 125:125 */    Ah[] arrayOfAh3 = (Ah[])((Ah[])paramAh.a())[2].a();
/* 126:126 */    paramAh = (Ah[])((Ah[])paramAh.a())[3].a();
/* 127:    */    
/* 128:128 */    this.inputCount = new short[arrayOfAh3.length - 1];
/* 129:129 */    for (int i = 0; i < arrayOfAh3.length - 1; i++) {
/* 130:130 */      this.inputCount[i] = ((Short)arrayOfAh3[i].a()).shortValue();
/* 131:    */    }
/* 132:    */    
/* 133:133 */    this.outputCount = new short[paramAh.length - 1];
/* 134:134 */    for (i = 0; i < paramAh.length - 1; i++) {
/* 135:135 */      this.outputCount[i] = ((Short)paramAh[i].a()).shortValue();
/* 136:    */    }
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.meta.Recipe
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */