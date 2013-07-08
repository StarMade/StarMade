/*  1:   */package org.schema.game.common.controller.elements.power;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementCollection;
/*  4:   */import q;
/*  5:   */
/*  9:   */public class PowerUnit
/* 10:   */  extends ElementCollection
/* 11:   */{
/* 12:12 */  private q significator = new q();
/* 13:   */  
/* 17:   */  private double recharge;
/* 18:   */  
/* 23:   */  public double getRecharge()
/* 24:   */  {
/* 25:25 */    return this.recharge;
/* 26:   */  }
/* 27:   */  
/* 28:   */  public q getSignificator()
/* 29:   */  {
/* 30:30 */    return this.significator;
/* 31:   */  }
/* 32:   */  
/* 38:   */  public void refreshPowerCapabilities()
/* 39:   */  {
/* 40:40 */    this.recharge = (Math.max(0.0F, getMax().c - getMin().c) + 1.0D);
/* 41:41 */    this.recharge += Math.max(0.0F, getMax().a - getMin().a) + 1.0D;
/* 42:42 */    this.recharge += Math.max(0.0F, getMax().b - getMin().b) + 1.0D;
/* 43:43 */    this.recharge = Math.max(1.0D, Math.pow(this.recharge / 3.0D, 1.7D));
/* 44:   */  }
/* 45:   */  
/* 46:46 */  private static double f(double paramDouble) { return Math.max(0.1D, 1.0D - 0.5D * Math.pow(2.0D * (1.0D - paramDouble), 2.5D)); }
/* 47:   */  
/* 48:   */  public static double integrate(double paramDouble1, double paramDouble2) {
/* 49:49 */    double d1 = (paramDouble2 - paramDouble1) / 9.0D;
/* 50:   */    
/* 53:53 */    double d2 = 0.3333333333333333D * (f(paramDouble1) + f(paramDouble2));
/* 54:   */    
/* 55:   */    double d3;
/* 56:56 */    for (paramDouble2 = 1; paramDouble2 < 9; paramDouble2 += 2) {
/* 57:57 */      d3 = paramDouble1 + d1 * paramDouble2;
/* 58:58 */      d2 += 1.333333333333333D * f(d3);
/* 59:   */    }
/* 60:   */    
/* 62:62 */    for (paramDouble2 = 2; paramDouble2 < 9; paramDouble2 += 2) {
/* 63:63 */      d3 = paramDouble1 + d1 * paramDouble2;
/* 64:64 */      d2 += 0.6666666666666666D * f(d3);
/* 65:   */    }
/* 66:   */    
/* 67:67 */    return d2 * d1;
/* 68:   */  }
/* 69:   */  
/* 78:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 79:   */  {
/* 80:80 */    this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 81:81 */    this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 82:82 */    this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/* 83:   */  }
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.power.PowerUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */