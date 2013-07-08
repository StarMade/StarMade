package org.schema.game.common.data.element.meta;

import class_69;
import class_79;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Recipe
  extends MetaObject
{
  short[] input;
  short[] inputCount;
  short[] output;
  short[] outputCount;
  
  public void serialize(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeShort(this.input.length);
    for (int i = 0; i < this.input.length; i++) {
      paramDataOutputStream.writeShort(this.input[i]);
    }
    paramDataOutputStream.writeShort(this.output.length);
    for (i = 0; i < this.output.length; i++) {
      paramDataOutputStream.writeShort(this.output[i]);
    }
    paramDataOutputStream.writeShort(this.inputCount.length);
    for (i = 0; i < this.inputCount.length; i++) {
      paramDataOutputStream.writeShort(this.inputCount[i]);
    }
    paramDataOutputStream.writeShort(this.outputCount.length);
    for (i = 0; i < this.outputCount.length; i++) {
      paramDataOutputStream.writeShort(this.outputCount[i]);
    }
  }
  
  public void deserialize(DataInputStream paramDataInputStream)
  {
    this.input = new short[paramDataInputStream.readShort()];
    for (int i = 0; i < this.input.length; i++) {
      this.input[i] = paramDataInputStream.readShort();
    }
    this.output = new short[paramDataInputStream.readShort()];
    for (i = 0; i < this.output.length; i++) {
      this.output[i] = paramDataInputStream.readShort();
    }
    this.inputCount = new short[paramDataInputStream.readShort()];
    for (i = 0; i < this.inputCount.length; i++) {
      this.inputCount[i] = paramDataInputStream.readShort();
    }
    this.outputCount = new short[paramDataInputStream.readShort()];
    for (i = 0; i < this.outputCount.length; i++) {
      this.outputCount[i] = paramDataInputStream.readShort();
    }
  }
  
  public short getObjectBlockID()
  {
    return -10;
  }
  
  public class_69 getBytesTag()
  {
    class_69[] arrayOfclass_691 = new class_69[this.input.length + 1];
    class_69[] arrayOfclass_692 = new class_69[this.output.length + 1];
    arrayOfclass_691[this.input.length] = new class_69(class_79.field_548, null, null);
    for (int i = 0; i < this.input.length; i++) {
      arrayOfclass_691[i] = new class_69(class_79.field_550, null, Short.valueOf(this.input[i]));
    }
    arrayOfclass_692[this.output.length] = new class_69(class_79.field_548, null, null);
    for (i = 0; i < this.output.length; i++) {
      arrayOfclass_692[i] = new class_69(class_79.field_550, null, Short.valueOf(this.output[i]));
    }
    class_69[] arrayOfclass_693 = new class_69[this.inputCount.length + 1];
    class_69[] arrayOfclass_694 = new class_69[this.outputCount.length + 1];
    arrayOfclass_693[this.inputCount.length] = new class_69(class_79.field_548, null, null);
    for (int j = 0; j < this.inputCount.length; j++) {
      arrayOfclass_693[j] = new class_69(class_79.field_550, null, Short.valueOf(this.inputCount[j]));
    }
    arrayOfclass_694[this.outputCount.length] = new class_69(class_79.field_548, null, null);
    for (j = 0; j < this.outputCount.length; j++) {
      arrayOfclass_694[j] = new class_69(class_79.field_550, null, Short.valueOf(this.outputCount[j]));
    }
    return new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_561, null, arrayOfclass_691), new class_69(class_79.field_561, null, arrayOfclass_692), new class_69(class_79.field_561, null, arrayOfclass_693), new class_69(class_79.field_561, null, arrayOfclass_694), new class_69(class_79.field_548, null, null) });
  }
  
  public void fromTag(class_69 paramclass_69)
  {
    class_69[] arrayOfclass_691 = (class_69[])((class_69[])paramclass_69.a4())[0].a4();
    class_69[] arrayOfclass_692 = (class_69[])((class_69[])paramclass_69.a4())[1].a4();
    this.input = new short[arrayOfclass_691.length - 1];
    for (int j = 0; j < arrayOfclass_691.length - 1; j++) {
      this.input[j] = ((Short)arrayOfclass_691[j].a4()).shortValue();
    }
    this.output = new short[arrayOfclass_692.length - 1];
    for (j = 0; j < arrayOfclass_692.length - 1; j++) {
      this.output[j] = ((Short)arrayOfclass_692[j].a4()).shortValue();
    }
    class_69[] arrayOfclass_693 = (class_69[])((class_69[])paramclass_69.a4())[2].a4();
    paramclass_69 = (class_69[])((class_69[])paramclass_69.a4())[3].a4();
    this.inputCount = new short[arrayOfclass_693.length - 1];
    for (int i = 0; i < arrayOfclass_693.length - 1; i++) {
      this.inputCount[i] = ((Short)arrayOfclass_693[i].a4()).shortValue();
    }
    this.outputCount = new short[paramclass_69.length - 1];
    for (i = 0; i < paramclass_69.length - 1; i++) {
      this.outputCount[i] = ((Short)paramclass_69[i].a4()).shortValue();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.meta.Recipe
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */