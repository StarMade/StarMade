package org.newdawn.slick.font.effects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

public class EffectUtil
{
  private static BufferedImage scratchImage = new BufferedImage(256, 256, 2);
  
  public static BufferedImage getScratchImage()
  {
    Graphics2D local_g = (Graphics2D)scratchImage.getGraphics();
    local_g.setComposite(AlphaComposite.Clear);
    local_g.fillRect(0, 0, 256, 256);
    local_g.setComposite(AlphaComposite.SrcOver);
    local_g.setColor(Color.white);
    return scratchImage;
  }
  
  public static ConfigurableEffect.Value colorValue(String name, Color currentValue)
  {
    new DefaultValue(name, toString(currentValue))
    {
      public void showDialog()
      {
        Color newColor = JColorChooser.showDialog(null, "Choose a color", EffectUtil.fromString(this.value));
        if (newColor != null) {
          this.value = EffectUtil.toString(newColor);
        }
      }
      
      public Object getObject()
      {
        return EffectUtil.fromString(this.value);
      }
    };
  }
  
  public static ConfigurableEffect.Value intValue(String name, final int currentValue, final String description)
  {
    new DefaultValue(name, String.valueOf(currentValue))
    {
      public void showDialog()
      {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(currentValue, -32768, 32767, 1));
        if (showValueDialog(spinner, description)) {
          this.value = String.valueOf(spinner.getValue());
        }
      }
      
      public Object getObject()
      {
        return Integer.valueOf(this.value);
      }
    };
  }
  
  public static ConfigurableEffect.Value floatValue(String name, final float currentValue, final float min, final float max, final String description)
  {
    new DefaultValue(name, String.valueOf(currentValue))
    {
      public void showDialog()
      {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(currentValue, min, max, 0.1000000014901161D));
        if (showValueDialog(spinner, description)) {
          this.value = String.valueOf(((Double)spinner.getValue()).floatValue());
        }
      }
      
      public Object getObject()
      {
        return Float.valueOf(this.value);
      }
    };
  }
  
  public static ConfigurableEffect.Value booleanValue(String name, final boolean currentValue, final String description)
  {
    new DefaultValue(name, String.valueOf(currentValue))
    {
      public void showDialog()
      {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(currentValue);
        if (showValueDialog(checkBox, description)) {
          this.value = String.valueOf(checkBox.isSelected());
        }
      }
      
      public Object getObject()
      {
        return Boolean.valueOf(this.value);
      }
    };
  }
  
  public static ConfigurableEffect.Value optionValue(String name, final String currentValue, final String[][] options, final String description)
  {
    new DefaultValue(name, currentValue.toString())
    {
      public void showDialog()
      {
        int selectedIndex = -1;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int local_i = 0; local_i < options.length; local_i++)
        {
          model.addElement(options[local_i][0]);
          if (getValue(local_i).equals(currentValue)) {
            selectedIndex = local_i;
          }
        }
        JComboBox local_i = new JComboBox(model);
        local_i.setSelectedIndex(selectedIndex);
        if (showValueDialog(local_i, description)) {
          this.value = getValue(local_i.getSelectedIndex());
        }
      }
      
      private String getValue(int local_i)
      {
        if (options[local_i].length == 1) {
          return options[local_i][0];
        }
        return options[local_i][1];
      }
      
      public String toString()
      {
        for (int local_i = 0; local_i < options.length; local_i++) {
          if (getValue(local_i).equals(this.value)) {
            return options[local_i][0].toString();
          }
        }
        return "";
      }
      
      public Object getObject()
      {
        return this.value;
      }
    };
  }
  
  public static String toString(Color color)
  {
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null.");
    }
    String local_r = Integer.toHexString(color.getRed());
    if (local_r.length() == 1) {
      local_r = "0" + local_r;
    }
    String local_g = Integer.toHexString(color.getGreen());
    if (local_g.length() == 1) {
      local_g = "0" + local_g;
    }
    String local_b = Integer.toHexString(color.getBlue());
    if (local_b.length() == 1) {
      local_b = "0" + local_b;
    }
    return local_r + local_g + local_b;
  }
  
  public static Color fromString(String rgb)
  {
    if ((rgb == null) || (rgb.length() != 6)) {
      return Color.white;
    }
    return new Color(Integer.parseInt(rgb.substring(0, 2), 16), Integer.parseInt(rgb.substring(2, 4), 16), Integer.parseInt(rgb.substring(4, 6), 16));
  }
  
  private static class ValueDialog
    extends JDialog
  {
    public boolean okPressed = false;
    
    public ValueDialog(JComponent component, String name, String description)
    {
      setDefaultCloseOperation(2);
      setLayout(new GridBagLayout());
      setModal(true);
      if ((component instanceof JSpinner)) {
        ((JSpinner.DefaultEditor)((JSpinner)component).getEditor()).getTextField().setColumns(4);
      }
      JPanel descriptionPanel = new JPanel();
      descriptionPanel.setLayout(new GridBagLayout());
      getContentPane().add(descriptionPanel, new GridBagConstraints(0, 0, 2, 1, 1.0D, 0.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
      descriptionPanel.setBackground(Color.white);
      descriptionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
      JTextArea descriptionText = new JTextArea(description);
      descriptionPanel.add(descriptionText, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
      descriptionText.setWrapStyleWord(true);
      descriptionText.setLineWrap(true);
      descriptionText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      descriptionText.setEditable(false);
      JPanel descriptionText = new JPanel();
      getContentPane().add(descriptionText, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, 10, 0, new Insets(5, 5, 0, 5), 0, 0));
      descriptionText.add(new JLabel(name + ":"));
      descriptionText.add(component);
      JPanel buttonPanel = new JPanel();
      getContentPane().add(buttonPanel, new GridBagConstraints(0, 2, 2, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
      JButton okButton = new JButton("OK");
      buttonPanel.add(okButton);
      okButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent evt)
        {
          EffectUtil.ValueDialog.this.okPressed = true;
          EffectUtil.ValueDialog.this.setVisible(false);
        }
      });
      JButton okButton = new JButton("Cancel");
      buttonPanel.add(okButton);
      okButton.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent evt)
        {
          EffectUtil.ValueDialog.this.setVisible(false);
        }
      });
      setSize(new Dimension(320, 175));
    }
  }
  
  private static abstract class DefaultValue
    implements ConfigurableEffect.Value
  {
    String value;
    String name;
    
    public DefaultValue(String name, String value)
    {
      this.value = value;
      this.name = name;
    }
    
    public void setString(String value)
    {
      this.value = value;
    }
    
    public String getString()
    {
      return this.value;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String toString()
    {
      if (this.value == null) {
        return "";
      }
      return this.value.toString();
    }
    
    public boolean showValueDialog(final JComponent component, String description)
    {
      EffectUtil.ValueDialog dialog = new EffectUtil.ValueDialog(component, this.name, description);
      dialog.setTitle(this.name);
      dialog.setLocationRelativeTo(null);
      EventQueue.invokeLater(new Runnable()
      {
        public void run()
        {
          JComponent focusComponent = component;
          if ((focusComponent instanceof JSpinner)) {
            focusComponent = ((JSpinner.DefaultEditor)((JSpinner)component).getEditor()).getTextField();
          }
          focusComponent.requestFocusInWindow();
        }
      });
      dialog.setVisible(true);
      return dialog.okPressed;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.font.effects.EffectUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */