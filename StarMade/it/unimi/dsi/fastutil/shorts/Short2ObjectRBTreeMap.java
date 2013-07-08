package it.unimi.dsi.fastutil.shorts;

import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
import it.unimi.dsi.fastutil.objects.AbstractObjectSortedSet;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
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

public class Short2ObjectRBTreeMap<V>
  extends AbstractShort2ObjectSortedMap<V>
  implements Serializable, Cloneable
{
  protected transient Entry<V> tree;
  protected int count;
  protected transient Entry<V> firstEntry;
  protected transient Entry<V> lastEntry;
  protected volatile transient ObjectSortedSet<Short2ObjectMap.Entry<V>> entries;
  protected volatile transient ShortSortedSet keys;
  protected volatile transient ObjectCollection<V> values;
  protected transient boolean modified;
  protected Comparator<? super Short> storedComparator;
  protected transient ShortComparator actualComparator;
  public static final long serialVersionUID = -7046029254386353129L;
  private static final boolean ASSERTS = false;
  private transient boolean[] dirPath;
  private transient Entry<V>[] nodePath;
  
  public Short2ObjectRBTreeMap()
  {
    allocatePaths();
    this.tree = null;
    this.count = 0;
  }
  
  private void setActualComparator()
  {
    if ((this.storedComparator == null) || ((this.storedComparator instanceof ShortComparator))) {
      this.actualComparator = ((ShortComparator)this.storedComparator);
    } else {
      this.actualComparator = new ShortComparator()
      {
        public int compare(short local_k1, short local_k2)
        {
          return Short2ObjectRBTreeMap.this.storedComparator.compare(Short.valueOf(local_k1), Short.valueOf(local_k2));
        }
        
        public int compare(Short ok1, Short ok2)
        {
          return Short2ObjectRBTreeMap.this.storedComparator.compare(ok1, ok2);
        }
      };
    }
  }
  
  public Short2ObjectRBTreeMap(Comparator<? super Short> local_c)
  {
    this();
    this.storedComparator = local_c;
    setActualComparator();
  }
  
  public Short2ObjectRBTreeMap(Map<? extends Short, ? extends V> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Short2ObjectRBTreeMap(SortedMap<Short, V> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Short2ObjectRBTreeMap(Short2ObjectMap<? extends V> local_m)
  {
    this();
    putAll(local_m);
  }
  
  public Short2ObjectRBTreeMap(Short2ObjectSortedMap<V> local_m)
  {
    this(local_m.comparator());
    putAll(local_m);
  }
  
  public Short2ObjectRBTreeMap(short[] local_k, V[] local_v, Comparator<? super Short> local_c)
  {
    this(local_c);
    if (local_k.length != local_v.length) {
      throw new IllegalArgumentException("The key array and the value array have different lengths (" + local_k.length + " and " + local_v.length + ")");
    }
    for (int local_i = 0; local_i < local_k.length; local_i++) {
      put(local_k[local_i], local_v[local_i]);
    }
  }
  
  public Short2ObjectRBTreeMap(short[] local_k, V[] local_v)
  {
    this(local_k, local_v, null);
  }
  
  final int compare(short local_k1, short local_k2)
  {
    return this.actualComparator == null ? 1 : local_k1 == local_k2 ? 0 : local_k1 < local_k2 ? -1 : this.actualComparator.compare(local_k1, local_k2);
  }
  
  final Entry<V> findKey(short local_k)
  {
    int cmp;
    for (Entry<V> local_e = this.tree; (local_e != null) && ((cmp = compare(local_k, local_e.key)) != 0); local_e = cmp < 0 ? local_e.left() : local_e.right()) {}
    return local_e;
  }
  
  final Entry<V> locateKey(short local_k)
  {
    Entry<V> local_e = this.tree;
    Entry<V> last = this.tree;
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
    this.dirPath = new boolean[64];
    this.nodePath = new Entry[64];
  }
  
  public V put(short local_k, V local_v)
  {
    this.modified = false;
    int maxDepth = 0;
    if (this.tree == null)
    {
      this.count += 1;
      this.tree = (this.lastEntry = this.firstEntry = new Entry(local_k, local_v));
    }
    else
    {
      Entry<V> local_p = this.tree;
      int local_i = 0;
      for (;;)
      {
        int cmp;
        if ((cmp = compare(local_k, local_p.key)) == 0)
        {
          V oldValue = local_p.value;
          local_p.value = local_v;
          while (local_i-- != 0) {
            this.nodePath[local_i] = null;
          }
          return oldValue;
        }
        this.nodePath[local_i] = local_p;
        if ((this.dirPath[(local_i++)] = cmp > 0 ? 1 : 0) != 0)
        {
          if (local_p.succ())
          {
            this.count += 1;
            Entry<V> local_e = new Entry(local_k, local_v);
            if (local_p.right == null) {
              this.lastEntry = local_e;
            }
            local_e.left = local_p;
            local_e.right = local_p.right;
            local_p.right(local_e);
            break;
          }
          local_p = local_p.right;
        }
        else
        {
          if (local_p.pred())
          {
            this.count += 1;
            Entry<V> local_e = new Entry(local_k, local_v);
            if (local_p.left == null) {
              this.firstEntry = local_e;
            }
            local_e.right = local_p;
            local_e.left = local_p.left;
            local_p.left(local_e);
            break;
          }
          local_p = local_p.left;
        }
      }
      Entry<V> local_e;
      this.modified = true;
      maxDepth = local_i--;
      while ((local_i > 0) && (!this.nodePath[local_i].black())) {
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry<V> oldValue = this.nodePath[(local_i - 1)].right;
          if ((!this.nodePath[(local_i - 1)].succ()) && (!oldValue.black()))
          {
            this.nodePath[local_i].black(true);
            oldValue.black(true);
            this.nodePath[(local_i - 1)].black(false);
            local_i -= 2;
          }
          else
          {
            if (this.dirPath[local_i] == 0)
            {
              oldValue = this.nodePath[local_i];
            }
            else
            {
              Entry<V> local_x = this.nodePath[local_i];
              oldValue = local_x.right;
              local_x.right = oldValue.left;
              oldValue.left = local_x;
              this.nodePath[(local_i - 1)].left = oldValue;
              if (oldValue.pred())
              {
                oldValue.pred(false);
                local_x.succ(oldValue);
              }
            }
            Entry<V> local_x = this.nodePath[(local_i - 1)];
            local_x.black(false);
            oldValue.black(true);
            local_x.left = oldValue.right;
            oldValue.right = local_x;
            if (local_i < 2) {
              this.tree = oldValue;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = oldValue;
            } else {
              this.nodePath[(local_i - 2)].left = oldValue;
            }
            if (!oldValue.succ()) {
              break;
            }
            oldValue.succ(false);
            local_x.pred(oldValue);
            break;
          }
        }
        else
        {
          Entry<V> oldValue = this.nodePath[(local_i - 1)].left;
          if ((!this.nodePath[(local_i - 1)].pred()) && (!oldValue.black()))
          {
            this.nodePath[local_i].black(true);
            oldValue.black(true);
            this.nodePath[(local_i - 1)].black(false);
            local_i -= 2;
          }
          else
          {
            if (this.dirPath[local_i] != 0)
            {
              oldValue = this.nodePath[local_i];
            }
            else
            {
              Entry<V> local_x = this.nodePath[local_i];
              oldValue = local_x.left;
              local_x.left = oldValue.right;
              oldValue.right = local_x;
              this.nodePath[(local_i - 1)].right = oldValue;
              if (oldValue.succ())
              {
                oldValue.succ(false);
                local_x.pred(oldValue);
              }
            }
            Entry<V> local_x = this.nodePath[(local_i - 1)];
            local_x.black(false);
            oldValue.black(true);
            local_x.right = oldValue.left;
            oldValue.left = local_x;
            if (local_i < 2) {
              this.tree = oldValue;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = oldValue;
            } else {
              this.nodePath[(local_i - 2)].left = oldValue;
            }
            if (!oldValue.pred()) {
              break;
            }
            oldValue.pred(false);
            local_x.succ(oldValue);
            break;
          }
        }
      }
    }
    this.tree.black(true);
    while (maxDepth-- != 0) {
      this.nodePath[maxDepth] = null;
    }
    return this.defRetValue;
  }
  
  public V remove(short local_k)
  {
    this.modified = false;
    if (this.tree == null) {
      return this.defRetValue;
    }
    Entry<V> local_p = this.tree;
    int local_i = 0;
    short local_kk = local_k;
    int cmp;
    while ((cmp = compare(local_kk, local_p.key)) != 0)
    {
      this.dirPath[local_i] = (cmp > 0 ? 1 : false);
      this.nodePath[local_i] = local_p;
      if (this.dirPath[(local_i++)] != 0)
      {
        if ((local_p = local_p.right()) == null)
        {
          while (local_i-- != 0) {
            this.nodePath[local_i] = null;
          }
          return this.defRetValue;
        }
      }
      else if ((local_p = local_p.left()) == null)
      {
        while (local_i-- != 0) {
          this.nodePath[local_i] = null;
        }
        return this.defRetValue;
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
        if (local_i == 0) {
          this.tree = local_p.left;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].succ(local_p.right);
        } else {
          this.nodePath[(local_i - 1)].pred(local_p.left);
        }
      }
      else
      {
        local_p.prev().right = local_p.right;
        if (local_i == 0) {
          this.tree = local_p.left;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].right = local_p.left;
        } else {
          this.nodePath[(local_i - 1)].left = local_p.left;
        }
      }
    }
    else
    {
      Entry<V> local_r = local_p.right;
      if (local_r.pred())
      {
        local_r.left = local_p.left;
        local_r.pred(local_p.pred());
        if (!local_r.pred()) {
          local_r.prev().right = local_r;
        }
        if (local_i == 0) {
          this.tree = local_r;
        } else if (this.dirPath[(local_i - 1)] != 0) {
          this.nodePath[(local_i - 1)].right = local_r;
        } else {
          this.nodePath[(local_i - 1)].left = local_r;
        }
        boolean color = local_r.black();
        local_r.black(local_p.black());
        local_p.black(color);
        this.dirPath[local_i] = true;
        this.nodePath[(local_i++)] = local_r;
      }
      else
      {
        int local_j = local_i++;
        Entry<V> local_s;
        for (;;)
        {
          this.dirPath[local_i] = false;
          this.nodePath[(local_i++)] = local_r;
          local_s = local_r.left;
          if (local_s.pred()) {
            break;
          }
          local_r = local_s;
        }
        this.dirPath[local_j] = true;
        this.nodePath[local_j] = local_s;
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
        local_s.right(local_p.right);
        boolean color = local_s.black();
        local_s.black(local_p.black());
        local_p.black(color);
        if (local_j == 0) {
          this.tree = local_s;
        } else if (this.dirPath[(local_j - 1)] != 0) {
          this.nodePath[(local_j - 1)].right = local_s;
        } else {
          this.nodePath[(local_j - 1)].left = local_s;
        }
      }
    }
    int color = local_i;
    if (local_p.black())
    {
      while (local_i > 0)
      {
        if (((this.dirPath[(local_i - 1)] != 0) && (!this.nodePath[(local_i - 1)].succ())) || ((this.dirPath[(local_i - 1)] == 0) && (!this.nodePath[(local_i - 1)].pred())))
        {
          Entry<V> local_r = this.dirPath[(local_i - 1)] != 0 ? this.nodePath[(local_i - 1)].right : this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            break;
          }
        }
        if (this.dirPath[(local_i - 1)] == 0)
        {
          Entry<V> local_r = this.nodePath[(local_i - 1)].right;
          if (!local_r.black())
          {
            local_r.black(true);
            this.nodePath[(local_i - 1)].black(false);
            this.nodePath[(local_i - 1)].right = local_r.left;
            local_r.left = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            this.nodePath[local_i] = this.nodePath[(local_i - 1)];
            this.dirPath[local_i] = false;
            this.nodePath[(local_i - 1)] = local_r;
            if (color == local_i++) {
              color++;
            }
            local_r = this.nodePath[(local_i - 1)].right;
          }
          if (((local_r.pred()) || (local_r.left.black())) && ((local_r.succ()) || (local_r.right.black())))
          {
            local_r.black(false);
          }
          else
          {
            if ((local_r.succ()) || (local_r.right.black()))
            {
              Entry<V> local_s = local_r.left;
              local_s.black(true);
              local_r.black(false);
              local_r.left = local_s.right;
              local_s.right = local_r;
              local_r = this.nodePath[(local_i - 1)].right = local_s;
              if (local_r.succ())
              {
                local_r.succ(false);
                local_r.right.pred(local_r);
              }
            }
            local_r.black(this.nodePath[(local_i - 1)].black());
            this.nodePath[(local_i - 1)].black(true);
            local_r.right.black(true);
            this.nodePath[(local_i - 1)].right = local_r.left;
            local_r.left = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            if (!local_r.pred()) {
              break;
            }
            local_r.pred(false);
            this.nodePath[(local_i - 1)].succ(local_r);
            break;
          }
        }
        else
        {
          Entry<V> local_r = this.nodePath[(local_i - 1)].left;
          if (!local_r.black())
          {
            local_r.black(true);
            this.nodePath[(local_i - 1)].black(false);
            this.nodePath[(local_i - 1)].left = local_r.right;
            local_r.right = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            this.nodePath[local_i] = this.nodePath[(local_i - 1)];
            this.dirPath[local_i] = true;
            this.nodePath[(local_i - 1)] = local_r;
            if (color == local_i++) {
              color++;
            }
            local_r = this.nodePath[(local_i - 1)].left;
          }
          if (((local_r.pred()) || (local_r.left.black())) && ((local_r.succ()) || (local_r.right.black())))
          {
            local_r.black(false);
          }
          else
          {
            if ((local_r.pred()) || (local_r.left.black()))
            {
              Entry<V> local_s = local_r.right;
              local_s.black(true);
              local_r.black(false);
              local_r.right = local_s.left;
              local_s.left = local_r;
              local_r = this.nodePath[(local_i - 1)].left = local_s;
              if (local_r.pred())
              {
                local_r.pred(false);
                local_r.left.succ(local_r);
              }
            }
            local_r.black(this.nodePath[(local_i - 1)].black());
            this.nodePath[(local_i - 1)].black(true);
            local_r.left.black(true);
            this.nodePath[(local_i - 1)].left = local_r.right;
            local_r.right = this.nodePath[(local_i - 1)];
            if (local_i < 2) {
              this.tree = local_r;
            } else if (this.dirPath[(local_i - 2)] != 0) {
              this.nodePath[(local_i - 2)].right = local_r;
            } else {
              this.nodePath[(local_i - 2)].left = local_r;
            }
            if (!local_r.succ()) {
              break;
            }
            local_r.succ(false);
            this.nodePath[(local_i - 1)].pred(local_r);
            break;
          }
        }
        local_i--;
      }
      if (this.tree != null) {
        this.tree.black(true);
      }
    }
    this.modified = true;
    this.count -= 1;
    while (color-- != 0) {
      this.nodePath[color] = null;
    }
    return local_p.value;
  }
  
  public V put(Short local_ok, V local_ov)
  {
    V oldValue = put(local_ok.shortValue(), local_ov);
    return this.modified ? this.defRetValue : oldValue;
  }
  
  public V remove(Object local_ok)
  {
    V oldValue = remove(((Short)local_ok).shortValue());
    return this.modified ? oldValue : this.defRetValue;
  }
  
  public boolean containsValue(Object local_v)
  {
    Short2ObjectRBTreeMap<V>.ValueIterator local_i = new ValueIterator(null);
    int local_j = this.count;
    while (local_j-- != 0)
    {
      Object local_ev = local_i.next();
      if (local_ev == null ? local_v == null : local_ev.equals(local_v)) {
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
  
  public boolean containsKey(short local_k)
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
  
  public V get(short local_k)
  {
    Entry<V> local_e = findKey(local_k);
    return local_e == null ? this.defRetValue : local_e.value;
  }
  
  public short firstShortKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.firstEntry.key;
  }
  
  public short lastShortKey()
  {
    if (this.tree == null) {
      throw new NoSuchElementException();
    }
    return this.lastEntry.key;
  }
  
  public ObjectSortedSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet()
  {
    if (this.entries == null) {
      this.entries = new AbstractObjectSortedSet()
      {
        final Comparator<? super Short2ObjectMap.Entry<V>> comparator = new Comparator()
        {
          public int compare(Short2ObjectMap.Entry<V> local_x, Short2ObjectMap.Entry<V> local_y)
          {
            return Short2ObjectRBTreeMap.this.storedComparator.compare(local_x.getKey(), local_y.getKey());
          }
        };
        
        public Comparator<? super Short2ObjectMap.Entry<V>> comparator()
        {
          return this.comparator;
        }
        
        public ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> iterator()
        {
          return new Short2ObjectRBTreeMap.EntryIterator(Short2ObjectRBTreeMap.this);
        }
        
        public ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> iterator(Short2ObjectMap.Entry<V> from)
        {
          return new Short2ObjectRBTreeMap.EntryIterator(Short2ObjectRBTreeMap.this, ((Short)from.getKey()).shortValue());
        }
        
        public boolean contains(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Short, V> local_e = (Map.Entry)local_o;
          Short2ObjectRBTreeMap.Entry<V> local_f = Short2ObjectRBTreeMap.this.findKey(((Short)local_e.getKey()).shortValue());
          return local_e.equals(local_f);
        }
        
        public boolean remove(Object local_o)
        {
          if (!(local_o instanceof Map.Entry)) {
            return false;
          }
          Map.Entry<Short, V> local_e = (Map.Entry)local_o;
          Short2ObjectRBTreeMap.Entry<V> local_f = Short2ObjectRBTreeMap.this.findKey(((Short)local_e.getKey()).shortValue());
          if (local_f != null) {
            Short2ObjectRBTreeMap.this.remove(local_f.key);
          }
          return local_f != null;
        }
        
        public int size()
        {
          return Short2ObjectRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Short2ObjectRBTreeMap.this.clear();
        }
        
        public Short2ObjectMap.Entry<V> first()
        {
          return Short2ObjectRBTreeMap.this.firstEntry;
        }
        
        public Short2ObjectMap.Entry<V> last()
        {
          return Short2ObjectRBTreeMap.this.lastEntry;
        }
        
        public ObjectSortedSet<Short2ObjectMap.Entry<V>> subSet(Short2ObjectMap.Entry<V> from, Short2ObjectMap.Entry<V> local_to)
        {
          return Short2ObjectRBTreeMap.this.subMap((Short)from.getKey(), (Short)local_to.getKey()).short2ObjectEntrySet();
        }
        
        public ObjectSortedSet<Short2ObjectMap.Entry<V>> headSet(Short2ObjectMap.Entry<V> local_to)
        {
          return Short2ObjectRBTreeMap.this.headMap((Short)local_to.getKey()).short2ObjectEntrySet();
        }
        
        public ObjectSortedSet<Short2ObjectMap.Entry<V>> tailSet(Short2ObjectMap.Entry<V> from)
        {
          return Short2ObjectRBTreeMap.this.tailMap((Short)from.getKey()).short2ObjectEntrySet();
        }
      };
    }
    return this.entries;
  }
  
  public ShortSortedSet keySet()
  {
    if (this.keys == null) {
      this.keys = new KeySet(null);
    }
    return this.keys;
  }
  
  public ObjectCollection<V> values()
  {
    if (this.values == null) {
      this.values = new AbstractObjectCollection()
      {
        public ObjectIterator<V> iterator()
        {
          return new Short2ObjectRBTreeMap.ValueIterator(Short2ObjectRBTreeMap.this, null);
        }
        
        public boolean contains(Object local_k)
        {
          return Short2ObjectRBTreeMap.this.containsValue(local_k);
        }
        
        public int size()
        {
          return Short2ObjectRBTreeMap.this.count;
        }
        
        public void clear()
        {
          Short2ObjectRBTreeMap.this.clear();
        }
      };
    }
    return this.values;
  }
  
  public ShortComparator comparator()
  {
    return this.actualComparator;
  }
  
  public Short2ObjectSortedMap<V> headMap(short local_to)
  {
    return new Submap((short)0, true, local_to, false);
  }
  
  public Short2ObjectSortedMap<V> tailMap(short from)
  {
    return new Submap(from, false, (short)0, true);
  }
  
  public Short2ObjectSortedMap<V> subMap(short from, short local_to)
  {
    return new Submap(from, false, local_to, false);
  }
  
  public Short2ObjectRBTreeMap<V> clone()
  {
    Short2ObjectRBTreeMap<V> local_c;
    try
    {
      local_c = (Short2ObjectRBTreeMap)super.clone();
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
      Entry<V> local_rp = new Entry();
      Entry<V> local_rq = new Entry();
      Entry<V> local_p = local_rp;
      local_rp.left(this.tree);
      Entry<V> local_q = local_rq;
      local_rq.pred(null);
      for (;;)
      {
        if (!local_p.pred())
        {
          Entry<V> cantHappen = local_p.left.clone();
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
          Entry<V> cantHappen = local_p.right.clone();
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
    Short2ObjectRBTreeMap<V>.EntryIterator local_i = new EntryIterator();
    local_s.defaultWriteObject();
    while (local_n-- != 0)
    {
      Entry<V> local_e = local_i.nextEntry();
      local_s.writeShort(local_e.key);
      local_s.writeObject(local_e.value);
    }
  }
  
  private Entry<V> readTree(ObjectInputStream local_s, int local_n, Entry<V> pred, Entry<V> succ)
    throws IOException, ClassNotFoundException
  {
    if (local_n == 1)
    {
      Entry<V> top = new Entry(local_s.readShort(), local_s.readObject());
      top.pred(pred);
      top.succ(succ);
      top.black(true);
      return top;
    }
    if (local_n == 2)
    {
      Entry<V> top = new Entry(local_s.readShort(), local_s.readObject());
      top.black(true);
      top.right(new Entry(local_s.readShort(), local_s.readObject()));
      top.right.pred(top);
      top.pred(pred);
      top.right.succ(succ);
      return top;
    }
    int top = local_n / 2;
    int leftN = local_n - top - 1;
    Entry<V> top = new Entry();
    top.left(readTree(local_s, leftN, pred, top));
    top.key = local_s.readShort();
    top.value = local_s.readObject();
    top.black(true);
    top.right(readTree(local_s, top, top, succ));
    if (local_n + 2 == (local_n + 2 & -(local_n + 2))) {
      top.right.black(false);
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
      for (Entry<V> local_e = this.tree; local_e.left() != null; local_e = local_e.left()) {}
      this.firstEntry = local_e;
      for (local_e = this.tree; local_e.right() != null; local_e = local_e.right()) {}
      this.lastEntry = local_e;
    }
  }
  
  private void checkNodePath() {}
  
  private int checkTree(Entry<V> local_e, int local_d, int local_D)
  {
    return 0;
  }
  
  private final class Submap
    extends AbstractShort2ObjectSortedMap<V>
    implements Serializable
  {
    public static final long serialVersionUID = -7046029254386353129L;
    short from;
    short field_47;
    boolean bottom;
    boolean top;
    protected volatile transient ObjectSortedSet<Short2ObjectMap.Entry<V>> entries;
    protected volatile transient ShortSortedSet keys;
    protected volatile transient ObjectCollection<V> values;
    
    public Submap(short from, boolean bottom, short local_to, boolean top)
    {
      if ((!bottom) && (!top) && (Short2ObjectRBTreeMap.this.compare(from, local_to) > 0)) {
        throw new IllegalArgumentException("Start key (" + from + ") is larger than end key (" + local_to + ")");
      }
      this.from = from;
      this.bottom = bottom;
      this.field_47 = local_to;
      this.top = top;
      this.defRetValue = Short2ObjectRBTreeMap.this.defRetValue;
    }
    
    public void clear()
    {
      Short2ObjectRBTreeMap<V>.Submap.SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        local_i.nextEntry();
        local_i.remove();
      }
    }
    
    final boolean in6(short local_k)
    {
      return ((this.bottom) || (Short2ObjectRBTreeMap.this.compare(local_k, this.from) >= 0)) && ((this.top) || (Short2ObjectRBTreeMap.this.compare(local_k, this.field_47) < 0));
    }
    
    public ObjectSortedSet<Short2ObjectMap.Entry<V>> short2ObjectEntrySet()
    {
      if (this.entries == null) {
        this.entries = new AbstractObjectSortedSet()
        {
          public ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> iterator()
          {
            return new Short2ObjectRBTreeMap.Submap.SubmapEntryIterator(Short2ObjectRBTreeMap.Submap.this);
          }
          
          public ObjectBidirectionalIterator<Short2ObjectMap.Entry<V>> iterator(Short2ObjectMap.Entry<V> from)
          {
            return new Short2ObjectRBTreeMap.Submap.SubmapEntryIterator(Short2ObjectRBTreeMap.Submap.this, ((Short)from.getKey()).shortValue());
          }
          
          public Comparator<? super Short2ObjectMap.Entry<V>> comparator()
          {
            return Short2ObjectRBTreeMap.this.short2ObjectEntrySet().comparator();
          }
          
          public boolean contains(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Short, V> local_e = (Map.Entry)local_o;
            Short2ObjectRBTreeMap.Entry<V> local_f = Short2ObjectRBTreeMap.this.findKey(((Short)local_e.getKey()).shortValue());
            return (local_f != null) && (Short2ObjectRBTreeMap.Submap.this.in6(local_f.key)) && (local_e.equals(local_f));
          }
          
          public boolean remove(Object local_o)
          {
            if (!(local_o instanceof Map.Entry)) {
              return false;
            }
            Map.Entry<Short, V> local_e = (Map.Entry)local_o;
            Short2ObjectRBTreeMap.Entry<V> local_f = Short2ObjectRBTreeMap.this.findKey(((Short)local_e.getKey()).shortValue());
            if ((local_f != null) && (Short2ObjectRBTreeMap.Submap.this.in6(local_f.key))) {
              Short2ObjectRBTreeMap.Submap.this.remove(local_f.key);
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
            return !new Short2ObjectRBTreeMap.Submap.SubmapIterator(Short2ObjectRBTreeMap.Submap.this).hasNext();
          }
          
          public void clear()
          {
            Short2ObjectRBTreeMap.Submap.this.clear();
          }
          
          public Short2ObjectMap.Entry<V> first()
          {
            return Short2ObjectRBTreeMap.Submap.this.firstEntry();
          }
          
          public Short2ObjectMap.Entry<V> last()
          {
            return Short2ObjectRBTreeMap.Submap.this.lastEntry();
          }
          
          public ObjectSortedSet<Short2ObjectMap.Entry<V>> subSet(Short2ObjectMap.Entry<V> from, Short2ObjectMap.Entry<V> local_to)
          {
            return Short2ObjectRBTreeMap.Submap.this.subMap((Short)from.getKey(), (Short)local_to.getKey()).short2ObjectEntrySet();
          }
          
          public ObjectSortedSet<Short2ObjectMap.Entry<V>> headSet(Short2ObjectMap.Entry<V> local_to)
          {
            return Short2ObjectRBTreeMap.Submap.this.headMap((Short)local_to.getKey()).short2ObjectEntrySet();
          }
          
          public ObjectSortedSet<Short2ObjectMap.Entry<V>> tailSet(Short2ObjectMap.Entry<V> from)
          {
            return Short2ObjectRBTreeMap.Submap.this.tailMap((Short)from.getKey()).short2ObjectEntrySet();
          }
        };
      }
      return this.entries;
    }
    
    public ShortSortedSet keySet()
    {
      if (this.keys == null) {
        this.keys = new KeySet(null);
      }
      return this.keys;
    }
    
    public ObjectCollection<V> values()
    {
      if (this.values == null) {
        this.values = new AbstractObjectCollection()
        {
          public ObjectIterator<V> iterator()
          {
            return new Short2ObjectRBTreeMap.Submap.SubmapValueIterator(Short2ObjectRBTreeMap.Submap.this, null);
          }
          
          public boolean contains(Object local_k)
          {
            return Short2ObjectRBTreeMap.Submap.this.containsValue(local_k);
          }
          
          public int size()
          {
            return Short2ObjectRBTreeMap.Submap.this.size();
          }
          
          public void clear()
          {
            Short2ObjectRBTreeMap.Submap.this.clear();
          }
        };
      }
      return this.values;
    }
    
    public boolean containsKey(short local_k)
    {
      return (in6(local_k)) && (Short2ObjectRBTreeMap.this.containsKey(local_k));
    }
    
    public boolean containsValue(Object local_v)
    {
      Short2ObjectRBTreeMap<V>.Submap.SubmapIterator local_i = new SubmapIterator();
      while (local_i.hasNext())
      {
        Object local_ev = local_i.nextEntry().value;
        if (local_ev == null ? local_v == null : local_ev.equals(local_v)) {
          return true;
        }
      }
      return false;
    }
    
    public V get(short local_k)
    {
      short local_kk = local_k;
      Short2ObjectRBTreeMap.Entry<V> local_e;
      return (in6(local_kk)) && ((local_e = Short2ObjectRBTreeMap.this.findKey(local_kk)) != null) ? local_e.value : this.defRetValue;
    }
    
    public V put(short local_k, V local_v)
    {
      Short2ObjectRBTreeMap.this.modified = false;
      if (!in6(local_k)) {
        throw new IllegalArgumentException("Key (" + local_k + ") out of range [" + (this.bottom ? "-" : String.valueOf(this.from)) + ", " + (this.top ? "-" : String.valueOf(this.field_47)) + ")");
      }
      V oldValue = Short2ObjectRBTreeMap.this.put(local_k, local_v);
      return Short2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public V put(Short local_ok, V local_ov)
    {
      V oldValue = put(local_ok.shortValue(), local_ov);
      return Short2ObjectRBTreeMap.this.modified ? this.defRetValue : oldValue;
    }
    
    public V remove(short local_k)
    {
      Short2ObjectRBTreeMap.this.modified = false;
      if (!in6(local_k)) {
        return this.defRetValue;
      }
      V oldValue = Short2ObjectRBTreeMap.this.remove(local_k);
      return Short2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public V remove(Object local_ok)
    {
      V oldValue = remove(((Short)local_ok).shortValue());
      return Short2ObjectRBTreeMap.this.modified ? oldValue : this.defRetValue;
    }
    
    public int size()
    {
      Short2ObjectRBTreeMap<V>.Submap.SubmapIterator local_i = new SubmapIterator();
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
    
    public ShortComparator comparator()
    {
      return Short2ObjectRBTreeMap.this.actualComparator;
    }
    
    public Short2ObjectSortedMap<V> headMap(short local_to)
    {
      if (this.top) {
        return new Submap(Short2ObjectRBTreeMap.this, this.from, this.bottom, local_to, false);
      }
      return Short2ObjectRBTreeMap.this.compare(local_to, this.field_47) < 0 ? new Submap(Short2ObjectRBTreeMap.this, this.from, this.bottom, local_to, false) : this;
    }
    
    public Short2ObjectSortedMap<V> tailMap(short from)
    {
      if (this.bottom) {
        return new Submap(Short2ObjectRBTreeMap.this, from, false, this.field_47, this.top);
      }
      return Short2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? new Submap(Short2ObjectRBTreeMap.this, from, false, this.field_47, this.top) : this;
    }
    
    public Short2ObjectSortedMap<V> subMap(short from, short local_to)
    {
      if ((this.top) && (this.bottom)) {
        return new Submap(Short2ObjectRBTreeMap.this, from, false, local_to, false);
      }
      if (!this.top) {
        local_to = Short2ObjectRBTreeMap.this.compare(local_to, this.field_47) < 0 ? local_to : this.field_47;
      }
      if (!this.bottom) {
        from = Short2ObjectRBTreeMap.this.compare(from, this.from) > 0 ? from : this.from;
      }
      if ((!this.top) && (!this.bottom) && (from == this.from) && (local_to == this.field_47)) {
        return this;
      }
      return new Submap(Short2ObjectRBTreeMap.this, from, false, local_to, false);
    }
    
    public Short2ObjectRBTreeMap.Entry<V> firstEntry()
    {
      if (Short2ObjectRBTreeMap.this.tree == null) {
        return null;
      }
      Short2ObjectRBTreeMap.Entry<V> local_e;
      Short2ObjectRBTreeMap.Entry<V> local_e;
      if (this.bottom)
      {
        local_e = Short2ObjectRBTreeMap.this.firstEntry;
      }
      else
      {
        local_e = Short2ObjectRBTreeMap.this.locateKey(this.from);
        if (Short2ObjectRBTreeMap.this.compare(local_e.key, this.from) < 0) {
          local_e = local_e.next();
        }
      }
      if ((local_e == null) || ((!this.top) && (Short2ObjectRBTreeMap.this.compare(local_e.key, this.field_47) >= 0))) {
        return null;
      }
      return local_e;
    }
    
    public Short2ObjectRBTreeMap.Entry<V> lastEntry()
    {
      if (Short2ObjectRBTreeMap.this.tree == null) {
        return null;
      }
      Short2ObjectRBTreeMap.Entry<V> local_e;
      Short2ObjectRBTreeMap.Entry<V> local_e;
      if (this.top)
      {
        local_e = Short2ObjectRBTreeMap.this.lastEntry;
      }
      else
      {
        local_e = Short2ObjectRBTreeMap.this.locateKey(this.field_47);
        if (Short2ObjectRBTreeMap.this.compare(local_e.key, this.field_47) >= 0) {
          local_e = local_e.prev();
        }
      }
      if ((local_e == null) || ((!this.bottom) && (Short2ObjectRBTreeMap.this.compare(local_e.key, this.from) < 0))) {
        return null;
      }
      return local_e;
    }
    
    public short firstShortKey()
    {
      Short2ObjectRBTreeMap.Entry<V> local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public short lastShortKey()
    {
      Short2ObjectRBTreeMap.Entry<V> local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.key;
    }
    
    public Short firstKey()
    {
      Short2ObjectRBTreeMap.Entry<V> local_e = firstEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    public Short lastKey()
    {
      Short2ObjectRBTreeMap.Entry<V> local_e = lastEntry();
      if (local_e == null) {
        throw new NoSuchElementException();
      }
      return local_e.getKey();
    }
    
    private final class SubmapValueIterator
      extends Short2ObjectRBTreeMap<V>.Submap.SubmapIterator
      implements ObjectListIterator<V>
    {
      private SubmapValueIterator()
      {
        super();
      }
      
      public V next()
      {
        return nextEntry().value;
      }
      
      public V previous()
      {
        return previousEntry().value;
      }
      
      public void set(V local_v)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(V local_v)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private final class SubmapKeyIterator
      extends Short2ObjectRBTreeMap.Submap.SubmapIterator
      implements ShortListIterator
    {
      public SubmapKeyIterator()
      {
        super();
      }
      
      public SubmapKeyIterator(short from)
      {
        super(from);
      }
      
      public short nextShort()
      {
        return nextEntry().key;
      }
      
      public short previousShort()
      {
        return previousEntry().key;
      }
      
      public void set(short local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(short local_k)
      {
        throw new UnsupportedOperationException();
      }
      
      public Short next()
      {
        return Short.valueOf(nextEntry().key);
      }
      
      public Short previous()
      {
        return Short.valueOf(previousEntry().key);
      }
      
      public void set(Short local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Short local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapEntryIterator
      extends Short2ObjectRBTreeMap<V>.Submap.SubmapIterator
      implements ObjectListIterator<Short2ObjectMap.Entry<V>>
    {
      SubmapEntryIterator()
      {
        super();
      }
      
      SubmapEntryIterator(short local_k)
      {
        super(local_k);
      }
      
      public Short2ObjectMap.Entry<V> next()
      {
        return nextEntry();
      }
      
      public Short2ObjectMap.Entry<V> previous()
      {
        return previousEntry();
      }
      
      public void set(Short2ObjectMap.Entry<V> local_ok)
      {
        throw new UnsupportedOperationException();
      }
      
      public void add(Short2ObjectMap.Entry<V> local_ok)
      {
        throw new UnsupportedOperationException();
      }
    }
    
    private class SubmapIterator
      extends Short2ObjectRBTreeMap.TreeIterator
    {
      SubmapIterator()
      {
        super();
        this.next = Short2ObjectRBTreeMap.Submap.this.firstEntry();
      }
      
      SubmapIterator(short local_k)
      {
        this();
        if (this.next != null) {
          if ((!Short2ObjectRBTreeMap.Submap.this.bottom) && (Short2ObjectRBTreeMap.this.compare(local_k, this.next.key) < 0))
          {
            this.prev = null;
          }
          else if ((!Short2ObjectRBTreeMap.Submap.this.top) && (Short2ObjectRBTreeMap.this.compare(local_k, (this.prev = Short2ObjectRBTreeMap.Submap.this.lastEntry()).key) >= 0))
          {
            this.next = null;
          }
          else
          {
            this.next = Short2ObjectRBTreeMap.this.locateKey(local_k);
            if (Short2ObjectRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
        if ((!Short2ObjectRBTreeMap.Submap.this.bottom) && (this.prev != null) && (Short2ObjectRBTreeMap.this.compare(this.prev.key, Short2ObjectRBTreeMap.Submap.this.from) < 0)) {
          this.prev = null;
        }
      }
      
      void updateNext()
      {
        this.next = this.next.next();
        if ((!Short2ObjectRBTreeMap.Submap.this.top) && (this.next != null) && (Short2ObjectRBTreeMap.this.compare(this.next.key, Short2ObjectRBTreeMap.Submap.this.field_47) >= 0)) {
          this.next = null;
        }
      }
    }
    
    private class KeySet
      extends AbstractShort2ObjectSortedMap.KeySet
    {
      private KeySet()
      {
        super();
      }
      
      public ShortBidirectionalIterator iterator()
      {
        return new Short2ObjectRBTreeMap.Submap.SubmapKeyIterator(Short2ObjectRBTreeMap.Submap.this);
      }
      
      public ShortBidirectionalIterator iterator(short from)
      {
        return new Short2ObjectRBTreeMap.Submap.SubmapKeyIterator(Short2ObjectRBTreeMap.Submap.this, from);
      }
    }
  }
  
  private final class ValueIterator
    extends Short2ObjectRBTreeMap<V>.TreeIterator
    implements ObjectListIterator<V>
  {
    private ValueIterator()
    {
      super();
    }
    
    public V next()
    {
      return nextEntry().value;
    }
    
    public V previous()
    {
      return previousEntry().value;
    }
    
    public void set(V local_v)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(V local_v)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class KeySet
    extends AbstractShort2ObjectSortedMap.KeySet
  {
    private KeySet()
    {
      super();
    }
    
    public ShortBidirectionalIterator iterator()
    {
      return new Short2ObjectRBTreeMap.KeyIterator(Short2ObjectRBTreeMap.this);
    }
    
    public ShortBidirectionalIterator iterator(short from)
    {
      return new Short2ObjectRBTreeMap.KeyIterator(Short2ObjectRBTreeMap.this, from);
    }
  }
  
  private final class KeyIterator
    extends Short2ObjectRBTreeMap.TreeIterator
    implements ShortListIterator
  {
    public KeyIterator()
    {
      super();
    }
    
    public KeyIterator(short local_k)
    {
      super(local_k);
    }
    
    public short nextShort()
    {
      return nextEntry().key;
    }
    
    public short previousShort()
    {
      return previousEntry().key;
    }
    
    public void set(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(short local_k)
    {
      throw new UnsupportedOperationException();
    }
    
    public Short next()
    {
      return Short.valueOf(nextEntry().key);
    }
    
    public Short previous()
    {
      return Short.valueOf(previousEntry().key);
    }
    
    public void set(Short local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Short local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class EntryIterator
    extends Short2ObjectRBTreeMap<V>.TreeIterator
    implements ObjectListIterator<Short2ObjectMap.Entry<V>>
  {
    EntryIterator()
    {
      super();
    }
    
    EntryIterator(short local_k)
    {
      super(local_k);
    }
    
    public Short2ObjectMap.Entry<V> next()
    {
      return nextEntry();
    }
    
    public Short2ObjectMap.Entry<V> previous()
    {
      return previousEntry();
    }
    
    public void set(Short2ObjectMap.Entry<V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
    
    public void add(Short2ObjectMap.Entry<V> local_ok)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private class TreeIterator
  {
    Short2ObjectRBTreeMap.Entry<V> prev;
    Short2ObjectRBTreeMap.Entry<V> next;
    Short2ObjectRBTreeMap.Entry<V> curr;
    int index = 0;
    
    TreeIterator()
    {
      this.next = Short2ObjectRBTreeMap.this.firstEntry;
    }
    
    TreeIterator(short local_k)
    {
      if ((this.next = Short2ObjectRBTreeMap.this.locateKey(local_k)) != null) {
        if (Short2ObjectRBTreeMap.this.compare(this.next.key, local_k) <= 0)
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
    
    Short2ObjectRBTreeMap.Entry<V> nextEntry()
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
    
    Short2ObjectRBTreeMap.Entry<V> previousEntry()
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
      Short2ObjectRBTreeMap.this.remove(this.curr.key);
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
  
  private static final class Entry<V>
    implements Cloneable, Short2ObjectMap.Entry<V>
  {
    private static final int BLACK_MASK = 1;
    private static final int SUCC_MASK = -2147483648;
    private static final int PRED_MASK = 1073741824;
    short key;
    V value;
    Entry<V> left;
    Entry<V> right;
    int info;
    
    Entry() {}
    
    Entry(short local_k, V local_v)
    {
      this.key = local_k;
      this.value = local_v;
      this.info = -1073741824;
    }
    
    Entry<V> left()
    {
      return (this.info & 0x40000000) != 0 ? null : this.left;
    }
    
    Entry<V> right()
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
    
    void pred(Entry<V> pred)
    {
      this.info |= 1073741824;
      this.left = pred;
    }
    
    void succ(Entry<V> succ)
    {
      this.info |= -2147483648;
      this.right = succ;
    }
    
    void left(Entry<V> left)
    {
      this.info &= -1073741825;
      this.left = left;
    }
    
    void right(Entry<V> right)
    {
      this.info &= 2147483647;
      this.right = right;
    }
    
    boolean black()
    {
      return (this.info & 0x1) != 0;
    }
    
    void black(boolean black)
    {
      if (black) {
        this.info |= 1;
      } else {
        this.info &= -2;
      }
    }
    
    Entry<V> next()
    {
      Entry<V> next = this.right;
      if ((this.info & 0x80000000) == 0) {
        while ((next.info & 0x40000000) == 0) {
          next = next.left;
        }
      }
      return next;
    }
    
    Entry<V> prev()
    {
      Entry<V> prev = this.left;
      if ((this.info & 0x40000000) == 0) {
        while ((prev.info & 0x80000000) == 0) {
          prev = prev.right;
        }
      }
      return prev;
    }
    
    public Short getKey()
    {
      return Short.valueOf(this.key);
    }
    
    public short getShortKey()
    {
      return this.key;
    }
    
    public V getValue()
    {
      return this.value;
    }
    
    public V setValue(V value)
    {
      V oldValue = this.value;
      this.value = value;
      return oldValue;
    }
    
    public Entry<V> clone()
    {
      Entry<V> local_c;
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
      Map.Entry<Short, V> local_e = (Map.Entry)local_o;
      return (this.key == ((Short)local_e.getKey()).shortValue()) && (this.value == null ? local_e.getValue() == null : this.value.equals(local_e.getValue()));
    }
    
    public int hashCode()
    {
      return this.key ^ (this.value == null ? 0 : this.value.hashCode());
    }
    
    public String toString()
    {
      return this.key + "=>" + this.value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.Short2ObjectRBTreeMap
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */