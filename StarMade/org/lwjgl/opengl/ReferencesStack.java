package org.lwjgl.opengl;

class ReferencesStack
{
  private References[] references_stack;
  private int stack_pos;
  
  public References getReferences()
  {
    return this.references_stack[this.stack_pos];
  }
  
  public void pushState()
  {
    int pos = ++this.stack_pos;
    if (pos == this.references_stack.length) {
      growStack();
    }
    this.references_stack[pos].copy(this.references_stack[(pos - 1)], -1);
  }
  
  public References popState(int mask)
  {
    References result = this.references_stack[(this.stack_pos--)];
    this.references_stack[this.stack_pos].copy(result, mask ^ 0xFFFFFFFF);
    result.clear();
    return result;
  }
  
  private void growStack()
  {
    References[] new_references_stack = new References[this.references_stack.length + 1];
    System.arraycopy(this.references_stack, 0, new_references_stack, 0, this.references_stack.length);
    this.references_stack = new_references_stack;
    this.references_stack[(this.references_stack.length - 1)] = new References(GLContext.getCapabilities());
  }
  
  ReferencesStack()
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    this.references_stack = new References[1];
    this.stack_pos = 0;
    for (int local_i = 0; local_i < this.references_stack.length; local_i++) {
      this.references_stack[local_i] = new References(caps);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.ReferencesStack
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */