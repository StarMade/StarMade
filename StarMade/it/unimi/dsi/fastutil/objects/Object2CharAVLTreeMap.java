package it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.chars.AbstractCharCollection;
import it.unimi.dsi.fastutil.chars.CharCollection;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.chars.CharListIterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.SortedMap;

public class Object2CharAVLTreeMap<K>
  extends AbstractObject2CharSortedMap<K>
  implements Serializable, Cloneable
{
  protected transient Entry<K> tree;
  protected int count;
  protected transient Entry<K> firstEntry;
  protected transient Entry<K> lastEntry;
  protected volatile transient ObjectSortedSet<Object2CharMap.Entry<K>> entries;
  protected volatile transient ObjectSortedSet<K> keys;
  protected volatile transient CharCollection values;
  protected transient boolean modified;
  protected Comparator<? super K> storedComparator;
  protected transient Comparator<? super K> actualComparator;
  public static final long serialVersionUID = -7046029254386353129L;
  private static final boolean ASSERTS = false;
  private transient boolean[] dirPath;
  
  public Object2CharAVLTreeMap()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    this.actualComparator = this.storedComparator;
  }
  
  public Object2CharAVLTreeMap(Comparator<? super K> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public Object2CharAVLTreeMap(Map<? extends K, ? extends Character> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Object2CharAVLTreeMap(SortedMap<K, Character> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Object2CharAVLTreeMap(Object2CharMap<? extends K> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Object2CharAVLTreeMap(Object2CharSortedMap<K> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Object2CharAVLTreeMap(K[] local_k, char[] local_v, Comparator<? super K> local_c)
  {
    this(local_c);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Object2CharAVLTreeMap(K[] local_k, char[] local_v)
  {
    this(local_k, local_v, null);
  }
  
  final int compare(K local_k1, K local_k2)
  {
    return this.actualComparator == null ? ((Comparable)local_k1).compareTo(local_k2) : this.actualComparator.compare(local_k1, local_k2);
  }
  
  final Entry<K> findKey(K local_k)
  {
    int cmp;
    for (Entry<K> local_e = this.tree; (local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0); local_e = cmp < 0 ? local_e.left() : local_e.right()) {}
    return local_e;
  }
  
  final Entry<K> locateKey(K local_k)
  {
    Entry<K> local_e = this.tree;
    Entry<K> last = this.tree;
    int cmp = 0;
    while ((local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0))
    {
      last = local_e;
      local_e = cmp < 0 ? local_e.left() : local_e.right();
    }
    return cmp == 0 ? local_e : last;
  }
  
  private void allocatePaths()
  {
    this.dirPath = new boolean[48];
  }
  
  public char put(K local_k, char local_v)
  {
    this.modified = false;
    if (this.tree == null)
    {
      this.count += 1;
      this.tree = (this.lastEntry = this.firstEntry = new Entry(local_k, local_v));
      this.modified = true;
    }
    else
    {
      Entry<K> local_p = this.tree;
      Entry<K> local_q = null;
      Entry<K> local_y = this.tree;
      Entry<K> local_z = null;
      Entry<K> local_e = null;
      Entry<K> local_w = null;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0)
        {
          char oldValue = local_p.value;
          local_p.value = local_v;
          return oldValue;
        }
        if (local_p.balance() != 0)
        {
          local_i = 0;
          local_z = local_q;
          local_y = local_p;
        }
        if ((this.dirPath[(local_i++)] = cmp > 0 ? 1 : 0) != 0)
        {
          if (local_p.succ())
          {
            this.count += 1;
            local_e = new Entry(local_k, local_v);
            this.modified = true;
            if (local_p.right == null) {
              this.lastEntry = local_e;
            }
            local_e.left = local_p;
            local_e.right = local_p.right;
            local_p.right(local_e);
            break;
          }
          local_q = local_p;
          local_p = local_p.right;
        }
        else
        {
          if (local_p.pred())
          {
            this.count += 1;
            local_e = new Entry(local_k, local_v);
            this.modified = true;
            if (local_p.left == null) {
              this.firstEntry = local_e;
            }
            local_e.right = local_p;
            local_e.left = local_p.left;
            local_p.left(local_e);
            break;
          }
          local_q = local_p;
          local_p = local_p.left;
        }
      }
      local_p = local_y;
      local_i = 0;
      while (local_p != local_e)
      {
        if (this.dirPath[local_i] != 0) {
          local_p.incBalance();
        } else {
          local_p.decBalance();
        }
        local_p = this.dirPath[(local_i++)] != 0 ? local_p.right : local_p.left;
      }
      if (local_y.balance() == -2)
      {
        Entry<K> oldValue = local_y.left;
        if (oldValue.balance() == -1)
        {
          local_w = oldValue;
          if (oldValue.succ())
          {
            oldValue.succ(false);
            local_y.pred(oldValue);
          }
          else
          {
            local_y.left = oldValue.right;
          }
          oldValue.right = local_y;
          oldValue.balance(0);
          local_y.balance(0);
        }
        else
        {
          local_w = oldValue.right;
          oldValue.right = local_w.left;
          local_w.left = oldValue;
          local_y.left = local_w.right;
          local_w.right = local_y;
          if (local_w.balance() == -1)
          {
            oldValue.balance(0);
            local_y.balance(1);
          }
          else if (local_w.balance() == 0)
          {
            oldValue.balance(0);
            local_y.balance(0);
          }
          else
          {
            oldValue.balance(-1);
            local_y.balance(0);
          }
          local_w.balance(0);
          if (local_w.pred())
          {
            oldValue.succ(local_w);
            local_w.pred(false);
          }
          if (local_w.succ())
          {
            local_y.pred(local_w);
            local_w.succ(false);
          }
        }
      }
      else if (local_y.balance() == 2)
      {
        Entry<K> oldValue = local_y.right;
        if (oldValue.balance() == 1)
        {
          local_w = oldValue;
          if (oldValue.pred())
          {
            oldValue.pred(false);
            local_y.succ(oldValue);
          }
          else
          {
            local_y.right = oldValue.left;
          }
          oldValue.left = local_y;
          oldValue.balance(0);
          local_y.balance(0);
        }
        else
        {
          local_w = oldValue.left;
          oldValue.left = local_w.right;
          local_w.right = oldValue;
          local_y.right = local_w.left;
          local_w.left = local_y;
          if (local_w.balance() == 1)
          {
            oldValue.balance(0);
            local_y.balance(-1);
          }
          else if (local_w.balance() == 0)
          {
            oldValue.balance(0);
            local_y.balance(0);
          }
          else
          {
            oldValue.balance(1);
            local_y.balance(0);
          }
          local_w.balance(0);
          if (local_w.pred())
          {
            local_y.succ(local_w);
            local_w.pred(false);
          }
          if (local_w.succ())
          {
            oldValue.pred(local_w);
            local_w.succ(false);
          }
        }
      }
      else
      {
        return this.defRetValue;
      }
      if (local_z == null) {
        this.tree = local_w;
      } else if (local_z.left == local_y) {
        local_z.left = local_w;
      } else {
        local_z.right = local_w;
      }
    }
    return this.defRetValue;
  }
  
  private Entry<K> parent(Entry<K> local_e)
  {
    if (local_e == this.tree) {
      return null;
    }
    Entry<K> local_y;
    Entry<K> local_x = local_y = local_e;
    for (;;)
    {
      if (local_y.succ())
      {
        Entry<K> local_p = local_y.right;
        if ((local_p == null) || (local_p.left != local_e))
        {
          while (!local_x.pred()) {
            local_x = local_x.left;
          }
          local_p = local_x.left;
        }
        return local_p;
      }
      if (local_x.pred())
      {
        Entry<K> local_p = local_x.left;
        if ((local_p == null) || (local_p.right != local_e))
        {
          while (!local_y.succ()) {
            local_y = local_y.right;
          }
          local_p = local_y.right;
        }
        return local_p;
      }
      local_x = local_x.left;
      local_y = local_y.right;
    }
  }
  
  public char removeChar(Object local_k)
  {
    this.modified = false;
    if (this.tree == null) {
      return this.defRetValue;
    }
    Entry<K> local_p = this.tree;
    Entry<K> local_q = null;
    boolean dir = false;
    K local_kk = local_k;
    int cmp;
    while ((cmp = compare(local_kk, local_p.key)) != 0) {
      if ((dir = cmp > 0 ? 1 : 0) != 0)
      {
        local_q = local_p;
        if ((local_p = local_p.right()) == null) {
          return this.defRetValue;
        }
      }
      else
      {
        local_q = local_p;
        if ((local_p = local_p.left()) == null) {
          return this.defRetValue;
        }
      }
    }
    if (local_p.left == null) {
      this.firstEntry = local_p.next();
    }
    if (local_p.right == null) {
      this.lastEntry = local_p.prev();
    }
    if (local_p.succ())
    {
      if (local_p.pred())
      {
        if (local_q != null)
        {
          if (dir) {
            local_q.succ(local_p.right);
          } else {
            local_q.pred(local_p.left);
          }
        }
        else {
          this.tree = (dir ? local_p.right : local_p.left);
        }
      }
      else
      {
        local_p.prev().right = local_p.right;
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_p.left;
          } else {
            local_q.left = local_p.left;
          }
        }
        else {
          this.tree = local_p.left;
        }
      }
    }
    else
    {
      Entry<K> local_r = local_p.right;
      if (local_r.pred())
      {
        local_r.left = local_p.left;
        local_r.pred(local_p.pred());
        if (!local_r.pred()) {
          local_r.prev().right = local_r;
        }
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_r;
          } else {
            local_q.left = local_r;
          }
        }
        else {
          this.tree = local_r;
        }
        local_r.balance(local_p.balance());
        local_q = local_r;
        dir = true;
      }
      else
      {
        Entry<K> local_s;
        for (;;)
        {
          local_s = local_r.left;
          if (local_s.pred()) {
            break;
          }
          local_r = local_s;
        }
        if (local_s.succ()) {
          local_r.pred(local_s);
        } else {
          local_r.left = local_s.right;
        }
        local_s.left = local_p.left;
        if (!local_p.pred())
        {
          local_p.prev().right = local_s;
          local_s.pred(false);
        }
        local_s.right = local_p.right;
        local_s.succ(false);
        if (local_q != null)
        {
          if (dir) {
            local_q.right = local_s;
          } else {
            local_q.left = local_s;
          }
        }
        else {
          this.tree = local_s;
        }
        local_s.balance(local_p.balance());
        local_q = local_r;
        dir = false;
      }
    }
    while (local_q != null)
    {
      Entry<K> local_r = local_q;
      local_q = parent(local_r);
      if (!dir)
      {
        dir = (local_q != null) && (local_q.left != local_r);
        local_r.incBalance();
        if (local_r.balance() == 1) {
          break;
        }
        if (local_r.balance() == 2)
        {
          Entry<K> local_s = local_r.right;
          if (local_s.balance() == -1)
          {
            Entry<K> local_w = local_s.left;
            local_s.left = local_w.right;
            local_w.right = local_s;
            local_r.right = local_w.left;
            local_w.left = local_r;
            if (local_w.balance() == 1)
            {
              local_s.balance(0);
              local_r.balance(-1);
            }
            else if (local_w.balance() == 0)
            {
              local_s.balance(0);
              local_r.balance(0);
            }
            else
            {
              local_s.balance(1);
              local_r.balance(0);
            }
            local_w.balance(0);
            if (local_w.pred())
            {
              local_r.succ(local_w);
              local_w.pred(false);
            }
            if (local_w.succ())
            {
              local_s.pred(local_w);
              local_w.succ(false);
            }
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_w;
              } else {
                local_q.left = local_w;
              }
            }
            else {
              this.tree = local_w;
            }
          }
          else
          {
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_s;
              } else {
                local_q.left = local_s;
              }
            }
            else {
              this.tree = local_s;
            }
            if (local_s.balance() == 0)
            {
              local_r.right = local_s.left;
              local_s.left = local_r;
              local_s.balance(-1);
              local_r.balance(1);
              break;
            }
            if (local_s.pred())
            {
              local_r.succ(true);
              local_s.pred(false);
            }
            else
            {
              local_r.right = local_s.left;
            }
            local_s.left = local_r;
            local_r.balance(0);
            local_s.balance(0);
          }
        }
      }
      else
      {
        dir = (local_q != null) && (local_q.left != local_r);
        local_r.decBalance();
        if (local_r.balance() == -1) {
          break;
        }
        if (local_r.balance() == -2)
        {
          Entry<K> local_s = local_r.left;
          if (local_s.balance() == 1)
          {
            Entry<K> local_w = local_s.right;
            local_s.right = local_w.left;
            local_w.left = local_s;
            local_r.left = local_w.right;
            local_w.right = local_r;
            if (local_w.balance() == -1)
            {
              local_s.balance(0);
              local_r.balance(1);
            }
            else if (local_w.balance() == 0)
            {
              local_s.balance(0);
              local_r.balance(0);
            }
            else
            {
              local_s.balance(-1);
              local_r.balance(0);
            }
            local_w.balance(0);
            if (local_w.pred())
            {
              local_s.succ(local_w);
              local_w.pred(false);
            }
            if (local_w.succ())
            {
              local_r.pred(local_w);
              local_w.succ(false);
            }
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_w;
              } else {
                local_q.left = local_w;
              }
            }
            else {
              this.tree = local_w;
            }
          }
          else
          {
            if (local_q != null)
            {
              if (dir) {
                local_q.right = local_s;
              } else {
                local_q.left = local_s;
              }
            }
            else {
              this.tree = local_s;
            }
            if (local_s.balance() == 0)
            {
              local_r.left = local_s.right;
              local_s.right = local_r;
              local_s.balance(1);
              local_r.balance(-1);
              break;
            }
            if (local_s.succ())
            {
              local_r.pred(true);
              local_s.succ(false);
            }
            else
            {
              local_r.left = local_s.right;
            }
            local_s.right = local_r;
            local_r.balance(0);
            local_s.balance(0);
          }
        }
      }
    }
    this.modified = true;
    this.count -= 1;
    return local_p.value;
  }
  
  public Character put(K local_ok, Character local_ov)
  {
    char oldValue = put(local_ok, local_ov.charValue());
    return this.modified ? null : Character.valueOf(oldValue);
  }
  
  public Character remove(Object local_ok)
  {
    char oldValue = removeChar(local_ok);
    return this.modified ? Character.valueOf(oldValue) : null;
  }
  
  public boolean containsValue(char local_v)
  {
    Object2CharAVLTreeMap<K>.ValueIterator local_i = new ValueIterator(null);
    int local_j = this.count;
    while (local_j-- != 0)
    {
      char local_ev = local_i.nextChar();
      if (local_ev == local_v) {
        return true;
      }
    }
    return false;
  }
  
  public void clear()
  {
    this.count = 0;
    this.tree = null;
    this.entries = null;
    this.values = null;
    this.keys = null;
    this.firstEntry = (this.lastEntry = null);
  }
  
  public boolean containsKey(Object local_k)
  {
    return findKey(local_k) != null;
  }
  
  public int size()
  {
    return this.count;
  }
  
  public boolean isEmpty()
  {
    return this.count == 0;
  }
  
  public char getChar(Object local_k)
  {
    Entry<K> local_e = findKey(local_k);
    return local_e == null ? this.defRetValue : local_e.value;
  }
  
  public Character get(Object local_ok)
  {
    Entry<K> local_e = findKey(local_ok);
    return local_e == null ? null : local_e.getValue();
  }
  
  public K firstKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.firstEntry.key;
  }
  
  public K lastKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.lastEntry.key;
  }
  
  public ObjectSortedSet<Object2CharMap.Entry<K>> object2CharEntrySet()
  {
    if (this.entries == null) {
      this.entries = new AbstractObjectSortedSet()
      {
        final Comparator<? super Object2CharMap.Entry<K>> comparator = new Comparator()
        {
          public int compare(Object2CharMap.Entry<K> local_x, Object2CharMap.Entry<K> local_y)
          {
            return Object2CharAVLTreeMap.this.storedComparator.compare(local_x.getKey(), local_y.getKey());
          }
        };
        
        public Comparator<? super Object2CharMap.Entry<K>> comparator()
        {
          return this.comparator;
        }
        
        public ObjectBidirectionalIterator<Object2CharMap.Entry<K>> iterator()
        {
          return new Object2CharAVLTreeMap.EntryIterator(Object2CharAVLTreeMap.this);
        }
        
        public ObjectBidirectionalIterator<Object2CharMap.Entry<K>> iterator(Object2CharMap.Entry<K> from)
        {
          return new Object2CharAVLTreeMap.EntryIterator(Object2CharAVLTreeMap.this, from.getKey());
        }
        
        public boolean contains(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<K, Character> local_e = (Map.Entry)local_o;
          Object2CharAVLTreeMap.Entry<K> local_f = Object2CharAVLTreeMap.this.findKey(local_e.getKey());
          return local_e.equals(local_f);
        }
        
        public boolean remove(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<K, Character> local_e = (Map.Entry)local_o;
          Object2CharAVLTreeMap.Entry<K> local_f = Object2CharAVLTreeMap.this.findKey(local_e.getKey());
          if (local_f != null) {
            Object2CharAVLTreeMap.this.removeChar(local_f.key);
          }
          return local_f != null;
        }
        
        public int size()
        {
          return Object2CharAVLTreeMap.this.count;
        }
        
        public void clear()
        {
          Object2CharAVLTreeMap.this.clear();
        }
        
        public Object2CharMap.Entry<K> first()
        {
          return Object2CharAVLTreeMap.this.firstEntry;
        }
        
        public Object2CharMap.Entry<K> last()
        {
          return Object2CharAVLTreeMap.this.lastEntry;
        }
        
        public ObjectSortedSet<Object2CharMap.Entry<K>> subSet(Object2CharMap.Entry<K> from, Object2CharMap.Entry<K> local_to)
        {
          return Object2CharAVLTreeMap.this.subMap(from.getKey(), local_to.getKey()).object2CharEntrySet();
        }
        
        public ObjectSortedSet<Object2CharMap.Entry<K>> headSet(Object2CharMap.Entry<K> local_to)
        {
          return Object2CharAVLTreeMap.this.headMap(local_to.getKey()).object2CharEntrySet();
        }
        
        public ObjectSortedSet<Object2CharMap.Entry<K>> tailSet(Object2CharMap.Entry<K> from)
        {
          return Object2CharAVLTreeMap.this.tailMap(from.getKey()).object2CharEntrySet();
        }
      };
    }
    return this.entries;
  }
  
  public ObjectSortedSet<K> keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public CharCollection values()
  {
    if (this.values == null) {
      this.values = new AbstractCharCollection()
      {
        public CharIterator iterator()
        {
          return new Object2CharAVLTreeMap.ValueIterator(Object2CharAVLTreeMap.this, null);
        }
        
        public boolean contains(char local_k)
        {
          return Object2CharAVLTreeMap.this.containsValue(local_k);
        }
        
        public int size()
        {
          return Object2CharAVLTreeMap.this.count;
        }
        
        public void clear()
        {
          Object2CharAVLTreeMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  public Comparator<? super K> comparator()
  {
    return this.actualComparator;
  }
  
  public Object2CharSortedMap<K> headMap(K local_to)
  {
    return new Submap(null, true, local_to, false);
  }
  
  public Object2CharSortedMap<K> tailMap(K from)
  {
    return new Submap(from, false, null, true);
  }
  
  public Object2CharSortedMap<K> subMap(K from, K local_to)
  {
    return new Submap(from, false, local_to, false);
  }
  
  public Object2CharAVLTreeMap<K> clone()
  {
    Object2CharAVLTreeMap<K> local_c;
    try
    {
      local_c = (Object2CharAVLTreeMap)super.clone();
    }
    catch (CloneNotSupportedException cantHappen)
    {
      throw new InternalError();
    }
    local_c.keys = null;
    local_c.values = null;
    local_c.entries = null;
    local_c.allocatePaths();
    if (this.count != 0)
    {
      Entry<K> local_rp = new Entry();
      Entry<K> local_rq = new Entry();
      Entry<K> local_p = local_rp;
      local_rp.left(this.tree);
      Entry<K> local_q = local_rq;
      local_rq.pred(null);
      for (;;)
      {
        if (!local_p.pred())
        {
          Entry<K> cantHappen = local_p.left.clone();
          cantHappen.pred(local_q.left);
          cantHappen.succ(local_q);
          local_q.left(cantHappen);
          local_p = local_p.left;
          local_q = local_q.left;
        }
        else
        {
          while (local_p.succ())
          {
            local_p = local_p.right;
            if (local_p == null)
            {
              local_q.right = null;
              local_c.tree = local_rq.left;
              for (local_c.firstEntry = local_c.tree; local_c.firstEntry.left != null; local_c.firstEntry = local_c.firstEntry.left) {}
              for (local_c.lastEntry = local_c.tree; local_c.lastEntry.right != null; local_c.lastEntry = local_c.lastEntry.right) {}
              return local_c;
            }
            local_q = local_q.right;
          }
          local_p = local_p.right;
          local_q = local_q.right;
        }
        if (!local_p.succ())
        {
          Entry<K> cantHappen = local_p.right.clone();
          cantHappen.succ(local_q.right);
          cantHappen.pred(local_q);
          local_q.right(cantHappen);
        }
      }
    }
    return local_c;
  }
  
  private void writeObject(ObjectOutputStream local_s)
    throws IOException
  {
    int local_n = this.count;
    Object2CharAVLTreeMap<K>.EntryIterator local_i = new EntryIterator();
    local_s.defaultWriteObject();
    while (local_n-- != 0)
    {
      Entry<K> local_e = local_i.nextEntry();
      local_s.writeObject(local_e.key);
      local_s.writeChar(local_e.value);
    }
  }
  
  private Entry<K> readTree(ObjectInputStream local_s, int local_n, Entry<K> pred, Entry<K> succ)
    throws IOException, ClassNotFoundException
  {
    if (local_n == 1)
    {
      Entry<K> top = new Entry(local_s.readObject(), local_s.readChar());
      top.pred(pred);
      top.succ(succ);
      return top;
    }
    if (local_n == 2)
    {
      Entry<K> top = new Entry(local_s.readObject(), local_s.readChar());
      top.right(new Entry(local_s.readObject(), local_s.readChar()));
      top.right.pred(top);
      top.balance(1);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry<K> top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readObject();
    top.value = local_s.readChar();
    top.right(readTree(local_s, top, top, succ));
    if (local_n == (local_n & -local_n)) {
      top.balance(1);
    }
    return top;
  }
  
  private void readObject(ObjectInputStream local_s)
    throws IOException, ClassNotFoundException
  {
    local_s.defaultReadObject();
    setActualComparator();
    allocatePaths();
    if (this.count != 0)
    {
      this.tree = readTree(local_s, this.count, null, null);
      for (Entry<K> local_e = this.tree; local_e.left() != null; local_e = local_e.left()) {}
      this.firstEntry = local_e;
      for (local_e = this.tree; local_e.right() != null; local_e = local_e.right()) {}
      this.lastEntry = local_e;
    }
  }
  
  private static int checkTree(Entry local_e)
  {
    return 0;
  }
  
  private final class Submap
    extends AbstractObject2CharSortedMap<K>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    K from;
    K field_47;
    boolean bottom;
    boolean top;
    protected volatile transient ObjectSortedSet<Object2CharMap.Entry<K>> entries;
    protected volatile transient ObjectSortedSet<K> keys;
    protected volatile transient CharCollection values;
    
    public Submap(boolean from, K bottom, boolean local_to)
    {
      if ((!bottom) && (!top) && (Object2CharAVLTreeMap.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start key (" + from + ") is larger than end key (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_47 = local_to;
      this.top = top;
      this.defRetValue = Object2CharAVLTreeMap.this.defRetValue;
    }
    
    public void clear()
    {
      Object2CharAVLTreeMap<K>.Submap.SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        local_i.nextEntry();
        local_i.remove();
      }
    }
    
    final boolean in5(K local_k)
    {
      return ((this.bottom) || (Object2CharAVLTreeMap.this.compare(local_k, this.from) >= 0)) && ((this.top) || (Object2CharAVLTreeMap.this.compare(local_k, this.field_47) < 0));
    }
    
    public ObjectSortedSet<Object2CharMap.Entry<K>> object2CharEntrySet()
    {
      if (this.entries == null) {
        this.entries = new AbstractObjectSortedSet()
        {
          public ObjectBidirectionalIterator<Object2CharMap.Entry<K>> iterator()
          {
            return new Object2CharAVLTreeMap.Submap.SubmapEntryIterator(Object2CharAVLTreeMap.Submap.this);
          }
          
          public ObjectBidirectionalIterator<Object2CharMap.Entry<K>> iterator(Object2CharMap.Entry<K> from)
          {
            return new Object2CharAVLTreeMap.Submap.SubmapEntryIterator(Object2CharAVLTreeMap.Submap.this, from.getKey());
          }
          
          public Comparator<? super Object2CharMap.Entry<K>> comparator()
          {
            return Object2CharAVLTreeMap.this.entrySet().comparator();
          }
          
          public boolean contains(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<K, Character> local_e = (Map.Entry)local_o;
            Object2CharAVLTreeMap.Entry<K> local_f = Object2CharAVLTreeMap.this.findKey(local_e.getKey());
            return (local_f != null) && (Object2CharAVLTreeMap.Submap.this.in5(local_f.key)) && (local_e.equals(local_f));
          }
          
          public boolean remove(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<K, Character> local_e = (Map.Entry)local_o;
            Object2CharAVLTreeMap.Entry<K> local_f = Object2CharAVLTreeMap.this.findKey(local_e.getKey());
            if ((local_f != null) && (Object2CharAVLTreeMap.Submap.this.in5(local_f.key))) {
              Object2CharAVLTreeMap.Submap.this.removeChar(local_f.key);
            }
            return local_f != null;
          }
          
          public int size()
          {
            int local_c = 0;
            Iterator<?> local_i = iterator();
            while (local_i.hasNext())
            {
              local_c++;
              local_i.next();
            }
            return local_c;
          }
          
          public boolean isEmpty()
          {
            return !new Object2CharAVLTreeMap.Submap.SubmapIterator(Object2CharAVLTreeMap.Submap.this).hasNext();
          }
          
          public void clear()
          {
            Object2CharAVLTreeMap.Submap.this.clear();
          }
          
          public Object2CharMap.Entry<K> first()
          {
            return Object2CharAVLTreeMap.Submap.this.firstEntry();
          }
          
          public Object2CharMap.Entry<K> last()
          {
            return Object2CharAVLTreeMap.Submap.this.lastEntry();
          }
          
          public ObjectSortedSet<Object2CharMap.Entry<K>> subSet(Object2CharMap.Entry<K> from, Object2CharMap.Entry<K> local_to)
          {
            return Object2CharAVLTreeMap.Submap.this.subMap(from.getKey(), local_to.getKey()).object2CharEntrySet();
          }
          
          public ObjectSortedSet<Object2CharMap.Entry<K>> headSet(Object2CharMap.Entry<K> local_to)
          {
            return Object2CharAVLTreeMap.Submap.this.headMap(local_to.getKey()).object2CharEntrySet();
          }
          
          public ObjectSortedSet<Object2CharMap.Entry<K>> tailSet(Object2CharMap.Entry<K> from)
          {
            return Object2CharAVLTreeMap.Submap.this.tailMap(from.getKey()).object2CharEntrySet();
          }
        };
      }
      return this.entries;
    }
    
    public ObjectSortedSet<K> keySet()
    {
      if (this.keys == null) {
        this.keys = new KeySet(null);
      }
      return this.keys;
    }
    
    public CharCollection values()
    {
      if (this.values == null) {
        this.values = new AbstractCharCollection()
        {
          public CharIterator iterator()
          {
            return new Object2CharAVLTreeMap.Submap.SubmapValueIterator(Object2CharAVLTreeMap.Submap.this, null);
          }
          
          public boolean contains(char local_k)
          {
            return Object2CharAVLTreeMap.Submap.this.containsValue(local_k);
          }
          
          public int size()
          {
            return Object2CharAVLTreeMap.Submap.this.size();
          }
          
          public void clear()
          {
            Object2CharAVLTreeMap.Submap.this.clear();
          }
        };
      }
      return this.values;
    }
    
    public boolean containsKey(Object local_k)
    {
      return (in5(local_k)) && (Object2CharAVLTreeMap.this.containsKey(local_k));
    }
    
    public boolean containsValue(char local_v)
    {
      Object2CharAVLTreeMap<K>.Submap.SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        char local_ev = local_i.nextEntry().value;
        if (local_ev == local_v) {
          return true;
        }
      }
      return false;
    }
    
    public char getChar(Object local_k)
    {
      K local_kk = local_k;
      Object2CharAVLTreeMap.Entry<K> local_e;
      return (in5(local_kk)) && ((local_e = Object2CharAVLTreeMap.this.findKey(local_kk)) != null) ? local_e.value : this.defRetValue;
    }
    
    public Character get(Object local_ok)
    {
      K local_kk = local_ok;
      Object2CharAVLTreeMap.Entry<K> local_e;
      return (in5(local_kk)) && ((local_e = Object2CharAVLTreeMap.this.findKey(local_kk)) != null) ? local_e.getValue() : null;
    }
    
    public char put(K local_k, char local_v)
    {
      Object2CharAVLTreeMap.this.modified = false;
      if (!in5(local_k)) {
        throw new IllegalArgumentException("Key (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_47)) + ")");
      }
      char oldValue = Object2CharAVLTreeMap.this.put(local_k, local_v);
      return Object2CharAVLTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public Character put(K local_ok, Character local_ov)
    {
      char oldValue = put(local_ok, local_ov.charValue());
      return Object2CharAVLTreeMap.this.modified ? null : Character.valueOf(oldValue);
    }
    
    public char removeChar(Object local_k)
    {
      Object2CharAVLTreeMap.this.modified = false;
      if (!in5(local_k)) {
        return this.defRetValue;
      }
      char oldValue = Object2CharAVLTreeMap.this.removeChar(local_k);
      return Object2CharAVLTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public Character remove(Object local_ok)
    {
      char oldValue = removeChar(local_ok);
      return Object2CharAVLTreeMap.this.modified ? Character.valueOf(oldValue) : null;
    }
    
    public int size()
    {
      Object2CharAVLTreeMap<K>.Submap.SubmapIterator local_i = new SubmapIterator();
      int local_n = 0;
      while (local_i.hasNext())
      {
        local_n++;
        local_i.nextEntry();
      }
      return local_n;
    }
    
    public boolean isEmpty()
    {
      return !new SubmapIterator().hasNext();
    }
    
    public Comparator<? super K> comparator()
    {
      return Object2CharAVLTreeMap.this.actualComparator;
    }
    
    public Object2CharSortedMap<K> headMap(K local_to)
    {
      if (this.top) {
        return new Submap(Object2CharAVLTreeMap.this, this.from, this.bottom, local_to, false);
      }
      return Object2CharAVLTreeMap.this.compare(local_to, this.field_47) < 0 ? new Submap(Object2CharAVLTreeMap.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public Object2CharSortedMap<K> tailMap(K from)
    {
      if (this.bottom) {
        return new Submap(Object2CharAVLTreeMap.this, from, false, this.field_47, this.top);
      }
      return Object2CharAVLTreeMap.this.compare(from, this.from) > 0 ? new Submap(Object2CharAVLTreeMap.this, from, false, this.field_47, this.top) : this;
    }
    
    public Object2CharSortedMap<K> subMap(K from, K local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Submap(Object2CharAVLTreeMap.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = Object2CharAVLTreeMap.this.compare(local_to, this.field_47) < 0 ? local_to : this.field_47;
      }
      if (!this.bottom) {
        from = Object2CharAVLTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_47)) {
        return this;
      }
      return new Submap(Object2CharAVLTreeMap.this, from, false, local_to, false);
    }
    
    public Object2CharAVLTreeMap.Entry<K> firstEntry()
    {
      if (Object2CharAVLTreeMap.this.tree == null) {
        return null;
      }
      Object2CharAVLTreeMap.Entry<K> local_e;
      Object2CharAVLTreeMap.Entry<K> local_e;
      if (this.bottom)
      {
        local_e = Object2CharAVLTreeMap.this.firstEntry;
      }
      else
      {
        local_e = Object2CharAVLTreeMap.this.locateKey(this.from);
        if (Object2CharAVLTreeMap.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (Object2CharAVLTreeMap.this.compare(local_e.key, this.field_47) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public Object2CharAVLTreeMap.Entry<K> lastEntry()
    {
      if (Object2CharAVLTreeMap.this.tree == null) {
        return null;
      }
      Object2CharAVLTreeMap.Entry<K> local_e;
      Object2CharAVLTreeMap.Entry<K> local_e;
      if (this.top)
      {
        local_e = Object2CharAVLTreeMap.this.lastEntry;
      }
      else
      {
        local_e = Object2CharAVLTreeMap.this.locateKey(this.field_47);
        if (Object2CharAVLTreeMap.this.compare(local_e.key, this.field_47) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (Object2CharAVLTreeMap.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public K firstKey()
    {
      Object2CharAVLTreeMap.Entry<K> local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public K lastKey()
    {
      Object2CharAVLTreeMap.Entry<K> local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    private final class SubmapValueIterator
      extends Object2CharAVLTreeMap.Submap.SubmapIterator
      implements CharListIterator
    {
      private SubmapValueIterator()
      {
        super();
      }
      
      public char nextChar()
      {
        return nextEntry().value;
      }
      
      public char previousChar()
      {
        return previousEntry().value;
      }
      
      public void set(char local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(char local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public Character next()
      {
        return Character.valueOf(nextEntry().value);
      }
      
      public Character previous()
      {
        return Character.valueOf(previousEntry().value);
      }
      
      public void set(Character local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Character local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private final class SubmapKeyIterator
      extends Object2CharAVLTreeMap<K>.Submap.SubmapIterator
      implements ObjectListIterator<K>
    {
      public SubmapKeyIterator()
      {
        super();
      }
      
      public SubmapKeyIterator()
      {
        super(from);
      }
      
      public K next()
      {
        return nextEntry().key;
      }
      
      public K previous()
      {
        return previousEntry().key;
      }
      
      public void set(K local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(K local_k)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapEntryIterator
      extends Object2CharAVLTreeMap<K>.Submap.SubmapIterator
      implements ObjectListIterator<Object2CharMap.Entry<K>>
    {
      SubmapEntryIterator()
      {
        super();
      }
      
      SubmapEntryIterator()
      {
        super(local_k);
      }
      
      public Object2CharMap.Entry<K> next()
      {
        return nextEntry();
      }
      
      public Object2CharMap.Entry<K> previous()
      {
        return previousEntry();
      }
      
      public void set(Object2CharMap.Entry<K> local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Object2CharMap.Entry<K> local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapIterator
      extends Object2CharAVLTreeMap.TreeIterator
    {
      SubmapIterator()
      {
        super();
        this.next = Object2CharAVLTreeMap.Submap.this.firstEntry();
      }
      
      SubmapIterator()
      {
        this();
        if (this.next != null) {
          if ((!Object2CharAVLTreeMap.Submap.this.bottom) && (Object2CharAVLTreeMap.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!Object2CharAVLTreeMap.Submap.this.top) && (Object2CharAVLTreeMap.this.compare(local_k, (this.prev = Object2CharAVLTreeMap.Submap.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = Object2CharAVLTreeMap.this.locateKey(local_k);
            if (Object2CharAVLTreeMap.this.compare(this.next.key, local_k) <= 0)
            {
              this.prev = this.next;
              this.next = this.next.next();
            }
            else
            {
              this.prev = this.next.prev();
            }
          }
        }
      }
      
      void updatePrevious()
      {
        this.prev = this.prev.prev();
        if ((!Object2CharAVLTreeMap.Submap.this.bottom) && (this.prev != null) && (Object2CharAVLTreeMap.this.compare(this.prev.key, Object2CharAVLTreeMap.Submap.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!Object2CharAVLTreeMap.Submap.this.top) && (this.next != null) && (Object2CharAVLTreeMap.this.compare(this.next.key, Object2CharAVLTreeMap.Submap.this.field_47) >= 0)) {
          this.next = null;
        }
      }
    }
    
    private class KeySet
      extends AbstractObject2CharSortedMap.KeySet
    {
      private KeySet()
      {
        super();
      }
      
      public ObjectBidirectionalIterator<K> iterator()
      {
        return new Object2CharAVLTreeMap.Submap.SubmapKeyIterator(Object2CharAVLTreeMap.Submap.this);
      }
      
      public ObjectBidirectionalIterator<K> iterator(K from)
      {
        return new Object2CharAVLTreeMap.Submap.SubmapKeyIterator(Object2CharAVLTreeMap.Submap.this, from);
      }
    }
  }
  
  private final class ValueIterator
    extends Object2CharAVLTreeMap.TreeIterator
    implements CharListIterator
  {
    private ValueIterator()
    {
      super();
    }
    
    public char nextChar()
    {
      return nextEntry().value;
    }
    
    public char previousChar()
    {
      return previousEntry().value;
    }
    
    public void set(char local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(char local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public Character next()
    {
      return Character.valueOf(nextEntry().value);
    }
    
    public Character previous()
    {
      return Character.valueOf(previousEntry().value);
    }
    
    public void set(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Character local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class KeySet
    extends AbstractObject2CharSortedMap.KeySet
  {
    private KeySet()
    {
      super();
    }
    
    public ObjectBidirectionalIterator<K> iterator()
    {
      return new Object2CharAVLTreeMap.KeyIterator(Object2CharAVLTreeMap.this);
    }
    
    public ObjectBidirectionalIterator<K> iterator(K from)
    {
      return new Object2CharAVLTreeMap.KeyIterator(Object2CharAVLTreeMap.this, from);
    }
  }
  
  private final class KeyIterator
    extends Object2CharAVLTreeMap<K>.TreeIterator
    implements ObjectListIterator<K>
  {
    public KeyIterator()
    {
      super();
    }
    
    public KeyIterator()
    {
      super(local_k);
    }
    
    public K next()
    {
      return nextEntry().key;
    }
    
    public K previous()
    {
      return previousEntry().key;
    }
    
    public void set(K local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(K local_k)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Object2CharAVLTreeMap<K>.TreeIterator
    implements ObjectListIterator<Object2CharMap.Entry<K>>
  {
    EntryIterator()
    {
      super();
    }
    
    EntryIterator()
    {
      super(local_k);
    }
    
    public Object2CharMap.Entry<K> next()
    {
      return nextEntry();
    }
    
    public Object2CharMap.Entry<K> previous()
    {
      return previousEntry();
    }
    
    public void set(Object2CharMap.Entry<K> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Object2CharMap.Entry<K> local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class TreeIterator
  {
    Object2CharAVLTreeMap.Entry<K> prev;
    Object2CharAVLTreeMap.Entry<K> next;
    Object2CharAVLTreeMap.Entry<K> curr;
    int index = 0;
    
    TreeIterator()
    {
      this.next = Object2CharAVLTreeMap.this.firstEntry;
    }
    
    TreeIterator()
    {
      if ((this.next = Object2CharAVLTreeMap.this.locateKey(local_k)) != null) {
        if (Object2CharAVLTreeMap.this.compare(this.next.key, local_k) <= 0)
        {
          this.prev = this.next;
          this.next = this.next.next();
        }
        else
        {
          this.prev = this.next.prev();
        }
      }
    }
    
    public boolean hasNext()
    {
      return this.next != null;
    }
    
    public boolean hasPrevious()
    {
      return this.prev != null;
    }
    
    void updateNext()
    {
      this.next = this.next.next();
    }
    
    Object2CharAVLTreeMap.Entry<K> nextEntry()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      this.curr = (this.prev = this.next);
      this.index += 1;
      updateNext();
      return this.curr;
    }
    
    void updatePrevious()
    {
      this.prev = this.prev.prev();
    }
    
    Object2CharAVLTreeMap.Entry<K> previousEntry()
    {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      this.curr = (this.next = this.prev);
      this.index -= 1;
      updatePrevious();
      return this.curr;
    }
    
    public int nextIndex()
    {
      return this.index;
    }
    
    public int previousIndex()
    {
      return this.index - 1;
    }
    
    public void remove()
    {
      if (this.curr == null) {
        throw new IllegalStateException();
      }
      if (this.curr == this.prev) {
        this.index -= 1;
      }
      this.next = (this.prev = this.curr);
      updatePrevious();
      updateNext();
      Object2CharAVLTreeMap.this.removeChar(this.curr.key);
      this.curr = null;
    }
    
    public int skip(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasNext())) {
        nextEntry();
      }
      return local_n - local_i - 1;
    }
    
    public int back(int local_n)
    {
      int local_i = local_n;
      while ((local_i-- != 0) && (hasPrevious())) {
        previousEntry();
      }
      return local_n - local_i - 1;
    }
  }
  
  private static final class Entry<K>
    implements Cloneable, Object2CharMap.Entry<K>
  {
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
    private static final int BALANCE_MASK = 255;
    K key;
    char value;
    Entry<K> left;
    Entry<K> right;
    int info;
    
    Entry() {}
    
    Entry(K local_k, char local_v)
    {
      this.key = local_k;
      this.value = local_v;
      this.info = -1073741824;
    }
    
    Entry<K> left()
    {
      return (this.info & 0x40000000) != 0 ? null : this.left;
    }
    
    Entry<K> right()
    {
      return (this.info & 0x80000000) != 0 ? null : this.right;
    }
    
    boolean pred()
    {
      return (this.info & 0x40000000) != 0;
    }
    
    boolean succ()
    {
      return (this.info & 0x80000000) != 0;
    }
    
    void pred(boolean pred)
    {
      if (pred) {
        this.info |= 1073741824;
      } else {
        this.info &= -1073741825;
      }
    }
    
    void succ(boolean succ)
    {
      if (succ) {
        this.info |= -2147483648;
      } else {
        this.info &= 2147483647;
      }
    }
    
    void pred(Entry<K> pred)
    {
      this.info |= 1073741824;
      this.left = pred;
    }
    
    void succ(Entry<K> succ)
    {
      this.info |= -2147483648;
      this.right = succ;
    }
    
    void left(Entry<K> left)
    {
      this.info &= -1073741825;
      this.left = left;
    }
    
    void right(Entry<K> right)
    {
      this.info &= 2147483647;
      this.right = right;
    }
    
    int balance()
    {
      return (byte)this.info;
    }
    
    void balance(int level)
    {
      this.info &= -256;
      this.info |= level & 0xFF;
    }
    
    void incBalance()
    {
      this.info = (this.info & 0xFFFFFF00 | (byte)this.info + 1 & 0xFF);
    }
    
    protected void decBalance()
    {
      this.info = (this.info & 0xFFFFFF00 | (byte)this.info - 1 & 0xFF);
    }
    
    Entry<K> next()
    {
      Entry<K> next = this.right;
      if ((this.info & 0x80000000) == 0) {
        while ((next.info & 0x40000000) == 0) {
          next = next.left;
        }
      }
      return next;
    }
    
    Entry<K> prev()
    {
      Entry<K> prev = this.left;
      if ((this.info & 0x40000000) == 0) {
        while ((prev.info & 0x80000000) == 0) {
          prev = prev.right;
        }
      }
      return prev;
    }
    
    public K getKey()
    {
      return this.key;
    }
    
    public Character getValue()
    {
      return Character.valueOf(this.value);
    }
    
    public char getCharValue()
    {
      return this.value;
    }
    
    public char setValue(char value)
    {
      char oldValue = this.value;
      this.value = value;
      return oldValue;
    }
    
    public Character setValue(Character value)
    {
      return Character.valueOf(setValue(value.charValue()));
    }
    
    public Entry<K> clone()
    {
      Entry<K> local_c;
      try
      {
        local_c = (Entry)super.clone();
      }
      catch (CloneNotSupportedException cantHappen)
      {
        throw new InternalError();
      }
      local_c.key = this.key;
      local_c.value = this.value;
      local_c.info = this.info;
      return local_c;
    }
    
    public boolean equals(Object local_o)
    {
      if (!(local_o instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, Character> local_e = (Map.Entry)local_o;
      return (this.key == null ? local_e.getKey() == null : this.key.equals(local_e.getKey())) && (this.value == ((Character)local_e.getValue()).charValue());
    }
    
    public int hashCode()
    {
      return (this.key == null ? 0 : this.key.hashCode()) ^ this.value;
    }
    
    public String toString()
    {
      return this.key + "=>" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.objects.Object2CharAVLTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */