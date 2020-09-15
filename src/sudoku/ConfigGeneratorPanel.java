/*
 * Copyright (C) 2008-12  Bernhard Hobiger
 *
 * This file is part of HoDoKu.
 *
 * HoDoKu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HoDoKu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HoDoKu. If not, see <http://www.gnu.org/licenses/>.
 */
package sudoku;

import smain.Options;
import generator.GeneratorPattern;
import generator.SudokuGeneratorFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author hobiwan
 */
public class ConfigGeneratorPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    /** The standard background color of the textfield {@link #numberOfGivensTextField}. */
    private Color okColor;
    /** The background color for "error" (a light red) */
    private Color errorColor = new Color(255, 150, 150);
    /** A list with patterns */
    private ArrayList<GeneratorPattern> patterns = new ArrayList<GeneratorPattern>();
    /** The selected index in {@link #patterns} or -1. Must only be set using {@link #setPatternIndex(int)}. */
    private int patternIndex = -1;

    /** Creates new form ConfigGeneratorPanel */
    public ConfigGeneratorPanel() {
        initComponents();
        
        okColor = numberOfGivensTextField.getBackground();
        setAnzGivens(0);
        initAll(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        generatorPatternPanel1 = new sudoku.GeneratorPatternPanel();
        jLabel1 = new javax.swing.JLabel();
        numberOfGivensTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        resetButton = new javax.swing.JButton();
        jLabelPattern = new javax.swing.JLabel();
        jComboBoxPatterns = new javax.swing.JComboBox();
        jButtonNew = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonChangeName = new javax.swing.JButton();
        jButtonCheckPattern = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel"); // NOI18N
        generatorPatternPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ConfigGeneratorPanel.generatorPatternPanel1.border.title"))); // NOI18N

        javax.swing.GroupLayout generatorPatternPanel1Layout = new javax.swing.GroupLayout(generatorPatternPanel1);
        generatorPatternPanel1.setLayout(generatorPatternPanel1Layout);
        generatorPatternPanel1Layout.setHorizontalGroup(
            generatorPatternPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        generatorPatternPanel1Layout.setVerticalGroup(
            generatorPatternPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText(bundle.getString("ConfigGeneratorPanel.jLabel1.text")); // NOI18N

        numberOfGivensTextField.setEditable(false);
        numberOfGivensTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numberOfGivensTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(generatorPatternPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generatorPatternPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(numberOfGivensTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(178, Short.MAX_VALUE))
        );

        resetButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.resetButton.mnemonic").charAt(0));
        resetButton.setText(bundle.getString("ConfigGeneratorPanel.resetButton.text")); // NOI18N
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        jLabelPattern.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.jLabelPattern.mnemonic").charAt(0));
        jLabelPattern.setLabelFor(jComboBoxPatterns);
        jLabelPattern.setText(bundle.getString("ConfigGeneratorPanel.jLabelPattern.text")); // NOI18N

        jComboBoxPatterns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPatternsActionPerformed(evt);
            }
        });

        jButtonNew.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.jButtonNew.mnemonic").charAt(0));
        jButtonNew.setText(bundle.getString("ConfigGeneratorPanel.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jButtonLoad.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.jButtonLoad.mnemonic").charAt(0));
        jButtonLoad.setText(bundle.getString("ConfigGeneratorPanel.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jButtonSave.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.jButtonSave.mnemonic").charAt(0));
        jButtonSave.setText(bundle.getString("ConfigGeneratorPanel.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonChangeName.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.jButtonChangeName.mnemonic").charAt(0));
        jButtonChangeName.setText(bundle.getString("ConfigGeneratorPanel.jButtonChangeName.text")); // NOI18N
        jButtonChangeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChangeNameActionPerformed(evt);
            }
        });

        jButtonCheckPattern.setMnemonic(java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.jButtonCheckPattern.mnemonic").charAt(0));
        jButtonCheckPattern.setText(bundle.getString("ConfigGeneratorPanel.jButtonCheckPattern.text")); // NOI18N
        jButtonCheckPattern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckPatternActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resetButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelPattern)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxPatterns, 0, 150, Short.MAX_VALUE))
                    .addComponent(jButtonNew, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonChangeName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonLoad, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonCheckPattern, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonChangeName, jButtonCheckPattern, jButtonLoad, jButtonNew, jButtonSave});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPattern)
                    .addComponent(jComboBoxPatterns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonChangeName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSave)
                .addGap(18, 18, 18)
                .addComponent(jButtonCheckPattern)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 218, Short.MAX_VALUE)
                .addComponent(resetButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        initAll(true);     
    }//GEN-LAST:event_resetButtonActionPerformed

    private void jComboBoxPatternsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPatternsActionPerformed
        if (jComboBoxPatterns.getItemAt(0) == null) {
            return;
        }
        int index = jComboBoxPatterns.getSelectedIndex();
        setPatternIndex(index - 1);
//        System.out.println("patternIndex: " + patternIndex);
//        System.out.println("name: " + jComboBoxPatterns.getSelectedItem());
        if (index > 0) {
            generatorPatternPanel1.setPattern(patterns.get(patternIndex).getPattern());
        } else if (index == 0) {
            generatorPatternPanel1.setPattern(null);
        }
    }//GEN-LAST:event_jComboBoxPatternsActionPerformed

    @SuppressWarnings("unchecked")
    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        String defaultName = java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.pattern") +
                " " + (patterns.size() + 1);
        String name = JOptionPane.showInputDialog(jButtonNew, 
                java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.patternname"), 
                defaultName);
        if (name != null) {
            patterns.add(new GeneratorPattern(name));
            setPatternIndex(patterns.size() - 1);
            jComboBoxPatterns.addItem(name);
            jComboBoxPatterns.setSelectedIndex(patternIndex + 1);
            generatorPatternPanel1.setPattern(patterns.get(patternIndex).getPattern());
        }
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonChangeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChangeNameActionPerformed
        String defaultName = (String)jComboBoxPatterns.getSelectedItem();
        String name = JOptionPane.showInputDialog(jButtonChangeName, 
                java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.changepatternname"), 
                defaultName);
        if (name != null) {
            patterns.get(patternIndex).setName(name);
            fillCombo(patternIndex);
        }
    }//GEN-LAST:event_jButtonChangeNameActionPerformed

    @SuppressWarnings("unchecked")
private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
    String extension = java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.extension");
    String description = java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.description");
    FileNameExtensionFilter hpat = new FileNameExtensionFilter(description, extension);
    JFileChooser chooser = new JFileChooser(Options.getInstance().getDefaultFileDir());
    chooser.setAcceptAllFileFilterUsed(false);
    chooser.addChoosableFileFilter(hpat);
    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String path = chooser.getSelectedFile().getPath();
        path = path.substring(0, path.lastIndexOf(File.separatorChar));
        Options.getInstance().setDefaultFileDir(path);
        path = chooser.getSelectedFile().getAbsolutePath();
        try {
            XMLDecoder in = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
            patterns = (ArrayList<GeneratorPattern>) in.readObject();
            setPatternIndex((Integer) in.readObject());
            in.close();
            fillCombo(patternIndex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.INFO, "Pattern file {0} not found (reading)", path);
        }
    }
}//GEN-LAST:event_jButtonLoadActionPerformed

private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
    String extension = java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.extension");
    String description = java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.description");
    FileNameExtensionFilter hpat = new FileNameExtensionFilter(description, extension);
    JFileChooser chooser = new JFileChooser(Options.getInstance().getDefaultFileDir());
    chooser.setAcceptAllFileFilterUsed(false);
    chooser.addChoosableFileFilter(hpat);
    int returnVal = chooser.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String path = chooser.getSelectedFile().getPath();
        path = path.substring(0, path.lastIndexOf(File.separatorChar));
        if (! path.endsWith(extension)) {
            path += "." + extension;
        }
        Options.getInstance().setDefaultFileDir(path);
        path = chooser.getSelectedFile().getAbsolutePath();
        try {
            XMLEncoder out = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
            out.writeObject(patterns);
            out.writeObject(patternIndex);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Options.class.getName()).log(Level.INFO, "Pattern file {0} not found (writing)", path);
        }
    }
}//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonCheckPatternActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckPatternActionPerformed
        if (patternIndex < 0 || patternIndex > patterns.size()) {
            // invalid index!
            return;
        }
        GeneratorPattern act = patterns.get(patternIndex);
        // first: check the number of givens; if it is below 17, the pattern cant possibly be valid
        if (act.getAnzGivens() < 17) {
            JOptionPane.showMessageDialog(this, "Pattern has to few positions set! Please change it and try again.", "Invalid", JOptionPane.INFORMATION_MESSAGE);
            act.setValid(false);
            return;
        }
        Cursor oldCursor = getCursor();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        // second: the number of givens is correct, can we create a valid sudoku with this pattern?
        if (SudokuGeneratorFactory.getDefaultGeneratorInstance().generateSudoku(true, act.getPattern()) != null) {
            JOptionPane.showMessageDialog(this, "Pattern is valid!", "Valid", JOptionPane.INFORMATION_MESSAGE);
            act.setValid(true);
        } else {
            JOptionPane.showMessageDialog(this, "Pattern is not valid! Please change it and try again.", "Invalid", JOptionPane.INFORMATION_MESSAGE);
            act.setValid(false);
        }
        setCursor(oldCursor);
    }//GEN-LAST:event_jButtonCheckPatternActionPerformed

    /**
     * Writes the new values back into {@link Options}.
     */
    public void okPressed() {
        Options.getInstance().setGeneratorPatterns(copyGeneratorPatterns(patterns));
        Options.getInstance().setGeneratorPatternIndex(patternIndex);
    }
    
    /**
     * Initialize all fields of the panel: the values from {@link Options} are copied
     * locally.
     * 
     * @param setDefault 
     */
    private void initAll(boolean setDefault) {
        if (setDefault) {
            patterns = new ArrayList<GeneratorPattern>();
            setPatternIndex(Options.GENERATOR_PATTERN_INDEX);
        } else {
            patterns = copyGeneratorPatterns(Options.getInstance().getGeneratorPatterns());
            setPatternIndex(Options.getInstance().getGeneratorPatternIndex());
        }
        fillCombo(patternIndex);
    }

    /**
     * Fills {@link #jComboBoxPatterns} with the names from {@link #patterns}.
     */
    @SuppressWarnings("unchecked")
    private void fillCombo(int index) {
        jComboBoxPatterns.removeAllItems();
        jComboBoxPatterns.addItem(java.util.ResourceBundle.getBundle("intl/ConfigGeneratorPanel").getString("ConfigGeneratorPanel.nopattern"));
        for (GeneratorPattern p : patterns) {
            jComboBoxPatterns.addItem(p.getName());
        }
        setPatternIndex(index);
        jComboBoxPatterns.setSelectedIndex(index + 1);
    }
    
    /**
     * Makes a deep copy of <code>src</code> to <code>dest</code>.
     * 
     * @param src
     * @param dest 
     */
    private ArrayList<GeneratorPattern> copyGeneratorPatterns(ArrayList<GeneratorPattern> src) {
        ArrayList<GeneratorPattern> dest = new ArrayList<GeneratorPattern>(src.size());
        for (GeneratorPattern p : src) {
            dest.add(p.clone());
        }
        return dest;
    }
    
    /**
     * Sets the number of givens in the GUI. Callback from {@link GeneratorPatternPanel}.
     * 
     * @param anz 
     */
    public final void setAnzGivens(int anz) {
        numberOfGivensTextField.setText(anz + "");
        if (anz < 17) {
            numberOfGivensTextField.setBackground(errorColor);
        } else {
            numberOfGivensTextField.setBackground(okColor);
        }
    }
    
    /**
     * Sets the {@link #patternIndex} and adjusts the state of the check button.
     * 
     * @param index 
     */
    private void setPatternIndex(int index) {
        patternIndex = index;
        jButtonCheckPattern.setEnabled(index != -1);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sudoku.GeneratorPatternPanel generatorPatternPanel1;
    private javax.swing.JButton jButtonChangeName;
    private javax.swing.JButton jButtonCheckPattern;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxPatterns;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelPattern;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField numberOfGivensTextField;
    private javax.swing.JButton resetButton;
    // End of variables declaration//GEN-END:variables
}
