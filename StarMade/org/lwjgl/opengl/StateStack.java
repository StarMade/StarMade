/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/* 13:   */class StateStack
/* 14:   */{
/* 15:   */  private int[] state_stack;
/* 16:   */  
/* 26:   */  private int stack_pos;
/* 27:   */  
/* 37:   */  public int getState()
/* 38:   */  {
/* 39:39 */    return this.state_stack[this.stack_pos];
/* 40:   */  }
/* 41:   */  
/* 42:   */  public void pushState(int new_state) {
/* 43:43 */    int pos = ++this.stack_pos;
/* 44:44 */    if (pos == this.state_stack.length) {
/* 45:45 */      growState();
/* 46:   */    }
/* 47:47 */    this.state_stack[pos] = new_state;
/* 48:   */  }
/* 49:   */  
/* 50:   */  public int popState() {
/* 51:51 */    return this.state_stack[(this.stack_pos--)];
/* 52:   */  }
/* 53:   */  
/* 54:   */  public void growState() {
/* 55:55 */    int[] new_state_stack = new int[this.state_stack.length + 1];
/* 56:56 */    System.arraycopy(this.state_stack, 0, new_state_stack, 0, this.state_stack.length);
/* 57:57 */    this.state_stack = new_state_stack;
/* 58:   */  }
/* 59:   */  
/* 60:   */  StateStack(int initial_value) {
/* 61:61 */    this.state_stack = new int[1];
/* 62:62 */    this.stack_pos = 0;
/* 63:63 */    this.state_stack[this.stack_pos] = initial_value;
/* 64:   */  }
/* 65:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.StateStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */