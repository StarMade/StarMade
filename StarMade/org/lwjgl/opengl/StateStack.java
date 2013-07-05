/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ class StateStack
/*    */ {
/*    */   private int[] state_stack;
/*    */   private int stack_pos;
/*    */ 
/*    */   public int getState()
/*    */   {
/* 39 */     return this.state_stack[this.stack_pos];
/*    */   }
/*    */ 
/*    */   public void pushState(int new_state) {
/* 43 */     int pos = ++this.stack_pos;
/* 44 */     if (pos == this.state_stack.length) {
/* 45 */       growState();
/*    */     }
/* 47 */     this.state_stack[pos] = new_state;
/*    */   }
/*    */ 
/*    */   public int popState() {
/* 51 */     return this.state_stack[(this.stack_pos--)];
/*    */   }
/*    */ 
/*    */   public void growState() {
/* 55 */     int[] new_state_stack = new int[this.state_stack.length + 1];
/* 56 */     System.arraycopy(this.state_stack, 0, new_state_stack, 0, this.state_stack.length);
/* 57 */     this.state_stack = new_state_stack;
/*    */   }
/*    */ 
/*    */   StateStack(int initial_value) {
/* 61 */     this.state_stack = new int[1];
/* 62 */     this.stack_pos = 0;
/* 63 */     this.state_stack[this.stack_pos] = initial_value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.StateStack
 * JD-Core Version:    0.6.2
 */