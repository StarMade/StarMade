/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/* 14:   */class ReferencesStack
/* 15:   */{
/* 16:   */  private References[] references_stack;
/* 17:   */  
/* 27:   */  private int stack_pos;
/* 28:   */  
/* 39:   */  public References getReferences()
/* 40:   */  {
/* 41:41 */    return this.references_stack[this.stack_pos];
/* 42:   */  }
/* 43:   */  
/* 44:   */  public void pushState() {
/* 45:45 */    int pos = ++this.stack_pos;
/* 46:46 */    if (pos == this.references_stack.length) {
/* 47:47 */      growStack();
/* 48:   */    }
/* 49:49 */    this.references_stack[pos].copy(this.references_stack[(pos - 1)], -1);
/* 50:   */  }
/* 51:   */  
/* 52:   */  public References popState(int mask) {
/* 53:53 */    References result = this.references_stack[(this.stack_pos--)];
/* 54:   */    
/* 55:55 */    this.references_stack[this.stack_pos].copy(result, mask ^ 0xFFFFFFFF);
/* 56:56 */    result.clear();
/* 57:   */    
/* 58:58 */    return result;
/* 59:   */  }
/* 60:   */  
/* 61:   */  private void growStack() {
/* 62:62 */    References[] new_references_stack = new References[this.references_stack.length + 1];
/* 63:63 */    System.arraycopy(this.references_stack, 0, new_references_stack, 0, this.references_stack.length);
/* 64:64 */    this.references_stack = new_references_stack;
/* 65:65 */    this.references_stack[(this.references_stack.length - 1)] = new References(GLContext.getCapabilities());
/* 66:   */  }
/* 67:   */  
/* 68:   */  ReferencesStack() {
/* 69:69 */    ContextCapabilities caps = GLContext.getCapabilities();
/* 70:70 */    this.references_stack = new References[1];
/* 71:71 */    this.stack_pos = 0;
/* 72:72 */    for (int i = 0; i < this.references_stack.length; i++) {
/* 73:73 */      this.references_stack[i] = new References(caps);
/* 74:   */    }
/* 75:   */  }
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ReferencesStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */