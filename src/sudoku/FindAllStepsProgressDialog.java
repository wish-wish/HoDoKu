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

import smain.FindAllSteps;
import smain.Sudoku2;
import smain.SolutionStep;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author  hobiwan
 */
public class FindAllStepsProgressDialog extends javax.swing.JDialog {
    private static final int MAX_STEPS = 27;
    private static final long serialVersionUID = 1L;
    
    private List<SolutionStep> steps;
    private Thread thread;
    private long ticks;
    
    /** Creates new form FindAllStepsProgressDialog
     * @param parent
     * @param modal
     * @param sudoku  
     */
    public FindAllStepsProgressDialog(java.awt.Frame parent, boolean modal, Sudoku2 sudoku) {
        super(parent, modal);
        
        initComponents();
        getRootPane().setDefaultButton(abbrechenButton);
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent e) {
                abbrechenButtonActionPerformed(null);
                //setVisible( false );
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
        
        progressBar.setMinimum(0);
        progressBar.setMaximum(MAX_STEPS);
        progressBar.setValue(0);

        steps = new ArrayList<SolutionStep>();
        FindAllSteps findAllSteps = new FindAllSteps(steps, sudoku, this);
        thread = new Thread(findAllSteps);
        thread.setPriority(Thread.MAX_PRIORITY);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        abbrechenButton = new javax.swing.JButton();
        fishProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("intl/FindAllStepsProgressDialog"); // NOI18N
        setTitle(bundle.getString("FindAllStepsProgressDialog.title")); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        progressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        progressLabel.setText(bundle.getString("FindAllStepsProgressDialog.progressLabel.text")); // NOI18N

        progressBar.setStringPainted(true);

        abbrechenButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/FindAllStepsProgressDialog").getString("FindAllStepsProgressDialog.abbrechenButton.mnemonic").charAt(0));
        abbrechenButton.setText(bundle.getString("FindAllStepsProgressDialog.abbrechenButton.text")); // NOI18N
        abbrechenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abbrechenButtonActionPerformed(evt);
            }
        });

        fishProgressBar.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(progressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(abbrechenButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fishProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fishProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(abbrechenButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        abbrechenButtonActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing
    
    private void abbrechenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abbrechenButtonActionPerformed
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Interrupted while waiting for AllSteps-thread", ex);
        }
        setVisible(false);
    }//GEN-LAST:event_abbrechenButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        thread.start();
    }//GEN-LAST:event_formWindowOpened
    
    public void updateProgress(final String label, final int step) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                progressLabel.setText(java.util.ResourceBundle.getBundle("intl/FindAllStepsProgressDialog").getString("FindAllStepsProgressDialog.searching") + " " + label);
                progressBar.setValue(step);
            }
        });
    }
    
    public void resetFishProgressBar(final int maxValue) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                fishProgressBar.setMaximum(maxValue);
                fishProgressBar.setValue(0);
            }
        });
    }
    
    public void updateFishProgressBar(final int actValue) {
        if ((System.currentTimeMillis() - ticks) > 1000) {
            ticks = System.currentTimeMillis();
            EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    fishProgressBar.setValue(actValue);
                }
            });
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FindAllStepsProgressDialog(new javax.swing.JFrame(), true, null).setVisible(true);
            }
        });
    }
    
    public List<SolutionStep> getSteps() {
        return steps;
    }
    
    public int getMaxSteps() {
        return MAX_STEPS;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abbrechenButton;
    private javax.swing.JProgressBar fishProgressBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel progressLabel;
    // End of variables declaration//GEN-END:variables
    
}
