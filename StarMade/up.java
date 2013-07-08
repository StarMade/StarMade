/*   1:    */import org.schema.game.common.data.element.ControlElementMap;
/*   2:    */import org.schema.game.common.data.element.ControlledElementContainer;
/*   3:    */import org.schema.game.common.data.element.ElementCollection;
/*   4:    */
/*  21:    */public final class up
/*  22:    */  extends uC
/*  23:    */{
/*  24: 24 */  private int jdField_b_of_type_Int = 0;
/*  25:    */  
/*  27:    */  private final int jdField_c_of_type_Int;
/*  28:    */  
/*  30:    */  public up(q paramq1, uu[] paramArrayOfuu, q paramq2, q paramq3, byte paramByte)
/*  31:    */  {
/*  32: 32 */    super(paramq1, paramArrayOfuu, paramq2, paramq3, 8, paramByte);
/*  33:    */    
/*  35: 35 */    this.jdField_c_of_type_Int = ((paramq3.a - paramq2.a) / 2 + (paramq3.jdField_b_of_type_Int - paramq2.jdField_b_of_type_Int) / 2 + (paramq3.jdField_c_of_type_Int - paramq2.jdField_c_of_type_Int) / 2 - 4);
/*  36:    */  }
/*  37:    */  
/*  41: 41 */  private q d = new q();
/*  42:    */  
/*  43:    */  private boolean a;
/*  44:    */  
/*  45:    */  private boolean jdField_b_of_type_Boolean;
/*  46:    */  private boolean jdField_c_of_type_Boolean;
/*  47:    */  
/*  48:    */  protected final short a(q paramq)
/*  49:    */  {
/*  50: 50 */    if (paramq.equals(this.jdField_c_of_type_Q)) {
/*  51: 51 */      this.a = true;
/*  52: 52 */      this.jdField_b_of_type_Int += 1;
/*  53: 53 */      this.jdField_b_of_type_Boolean = ((!this.jdField_c_of_type_Boolean) && (this.jdField_b_of_type_Int >= this.jdField_c_of_type_Int) && (this.a));
/*  54: 54 */      return 7;
/*  55:    */    }
/*  56:    */    
/*  57: 57 */    this.d.b(this.jdField_c_of_type_Q);
/*  58: 58 */    q localq = org.schema.game.common.data.element.Element.DIRECTIONSi[a(this.jdField_c_of_type_Q)];
/*  59: 59 */    this.d.a(localq);
/*  60:    */    
/*  61: 61 */    if (((localq.a > 0) && (paramq.a > this.jdField_c_of_type_Q.a)) || ((localq.a < 0) && (paramq.a < this.jdField_c_of_type_Q.a)) || ((localq.jdField_b_of_type_Int > 0) && (paramq.jdField_b_of_type_Int > this.jdField_c_of_type_Q.jdField_b_of_type_Int)) || ((localq.jdField_b_of_type_Int < 0) && (paramq.jdField_b_of_type_Int < this.jdField_c_of_type_Q.jdField_b_of_type_Int)) || ((localq.jdField_c_of_type_Int > 0) && (paramq.jdField_c_of_type_Int > this.jdField_c_of_type_Q.jdField_c_of_type_Int)) || ((localq.jdField_c_of_type_Int < 0) && (paramq.jdField_c_of_type_Int < this.jdField_c_of_type_Q.jdField_c_of_type_Int)))
/*  62:    */    {
/*  66: 66 */      return 0;
/*  67:    */    }
/*  68: 68 */    this.d.b(this.jdField_c_of_type_Q);
/*  69: 69 */    this.d.c(localq);
/*  70: 70 */    if ((paramq.equals(this.d)) || ((paramq.a == this.d.a) && (paramq.jdField_b_of_type_Int == this.d.jdField_b_of_type_Int) && (paramq.jdField_c_of_type_Int != this.d.jdField_c_of_type_Int)) || ((paramq.a == this.d.a) && (paramq.jdField_c_of_type_Int == this.d.jdField_c_of_type_Int) && (paramq.jdField_b_of_type_Int != this.d.jdField_b_of_type_Int)) || ((paramq.jdField_c_of_type_Int == this.d.jdField_c_of_type_Int) && (paramq.jdField_b_of_type_Int == this.d.jdField_b_of_type_Int) && (paramq.a != this.d.a)))
/*  71:    */    {
/*  77: 77 */      this.a.addDelayed(new ControlledElementContainer(ElementCollection.getIndex(this.jdField_c_of_type_Q), new q(paramq), (short)88, true, true));
/*  78:    */      
/*  79: 79 */      this.jdField_b_of_type_Int += 1;
/*  80: 80 */      this.jdField_b_of_type_Boolean = ((!this.jdField_c_of_type_Boolean) && (this.jdField_b_of_type_Int >= this.jdField_c_of_type_Int) && (this.a));
/*  81:    */      
/*  85: 85 */      return 88;
/*  86:    */    }
/*  87:    */    
/*  89: 89 */    return 32767;
/*  90:    */  }
/*  91:    */  
/* 101:    */  public final boolean a()
/* 102:    */  {
/* 103:103 */    return this.jdField_b_of_type_Boolean;
/* 104:    */  }
/* 105:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     up
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */