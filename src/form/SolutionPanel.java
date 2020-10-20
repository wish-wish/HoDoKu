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

package form;

import sudoku.SolutionType;
import sudoku.ClipboardMode;
import sudoku.SolutionStep;
import sudoku.Options;
import sudoku.Sudoku2;
import sudoku.DifficultyType;
import utils.GuiState;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import solver.SudokuSolver;
import solver.SudokuSolverFactory;

/**
 *
 * @author  hobiwan
 */
public class SolutionPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;

    private MainFrame mainFrame;
    private SudokuSolver solver;
    //private SolutionListRenderer listRenderer = new SolutionListRenderer();
    private int rightMouseClickedIndex = -1;
    private List<String> titels = new ArrayList<String>();
    private List<JList> lists = new ArrayList<JList>();
    private List<List<SolutionStep>> tabSteps = new ArrayList<List<SolutionStep>>();
    private List<Integer> selectedIndices = new ArrayList<Integer>();
    private List<Color[]> stepBackgroundColors = new ArrayList<Color[]>();
    private List<Color[]> stepForegroundColors = new ArrayList<Color[]>();
    private JList actList = null;
    private List<SolutionStep> actSteps;
    private int actSelectedIndex = -1;
    private Color[] actStepBackgroundColors;
    private Color[] actStepForegroundColors;
    private boolean inTabbedPaneRemoveAll = false;

    /** Creates new form SolutionPanel
     * @param mainFrame 
     */
    public SolutionPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        initComponents();

        int fontSize = 12;
        if (getFont().getSize() > 12) {
            fontSize = getFont().getSize();
        }
        Font font = titleLabel.getFont();
        titleLabel.setFont(new Font(font.getName(), Font.BOLD, fontSize));
        
//        solutionTabbedPane.add("Lösung 1", solutionScrollPane);
//        solutionTabbedPane.add("Lösung 2", solutionScrollPane);
//        solutionTabbedPane.add("Lösung 3", solutionScrollPane);
        addTabPane();
        getActTab();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        solutionScrollPane = new javax.swing.JScrollPane();
        solutionList = new javax.swing.JList();
        solutionPopupMenu = new javax.swing.JPopupMenu();
        deleteFromHereMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        toStepMenuItem = new javax.swing.JMenuItem();
        solveFromHereMenuItem = new javax.swing.JMenuItem();
        tabPopupMenu = new javax.swing.JPopupMenu();
        tabNewMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        tabNewNameMenuItem = new javax.swing.JMenuItem();
        tabDeleteMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        tabPrintMenuItem = new javax.swing.JMenuItem();
        titleLabel = new javax.swing.JLabel();
        southPanel = new javax.swing.JPanel();
        weiterButton = new javax.swing.JButton();
        alleEinfachenButton = new javax.swing.JButton();
        solutionTabbedPane = new javax.swing.JTabbedPane();

        solutionList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        solutionList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solutionListMouseClicked(evt);
            }
        });
        solutionScrollPane.setViewportView(solutionList);

        deleteFromHereMenuItem.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.deleteFromHereMenuItem.mnemonic").charAt(0));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("intl/SolutionPanel"); // NOI18N
        deleteFromHereMenuItem.setText(bundle.getString("SolutionPanel.deleteFromHereMenuItem.text")); // NOI18N
        deleteFromHereMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFromHereMenuItemActionPerformed(evt);
            }
        });
        solutionPopupMenu.add(deleteFromHereMenuItem);
        solutionPopupMenu.add(jSeparator1);

        toStepMenuItem.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.toStepMenuItem.mnemonic").charAt(0));
        toStepMenuItem.setText(bundle.getString("SolutionPanel.toStepMenuItem.text")); // NOI18N
        toStepMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toStepMenuItemActionPerformed(evt);
            }
        });
        solutionPopupMenu.add(toStepMenuItem);

        solveFromHereMenuItem.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.solveFromHereMenuItem.mnemonic").charAt(0));
        solveFromHereMenuItem.setText(bundle.getString("SolutionPanel.solveFromHereMenuItem.text")); // NOI18N
        solveFromHereMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveFromHereMenuItemActionPerformed(evt);
            }
        });
        solutionPopupMenu.add(solveFromHereMenuItem);

        tabNewMenuItem.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.tabNewMenuItem.mnemonic").charAt(0));
        tabNewMenuItem.setText(bundle.getString("SolutionPanel.tabNewMenuItem.text")); // NOI18N
        tabNewMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabNewMenuItemActionPerformed(evt);
            }
        });
        tabPopupMenu.add(tabNewMenuItem);
        tabPopupMenu.add(jSeparator2);

        tabNewNameMenuItem.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.tabNewNameMenuItem.mnemonic").charAt(0));
        tabNewNameMenuItem.setText(bundle.getString("SolutionPanel.tabNewNameMenuItem.text")); // NOI18N
        tabNewNameMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabNewNameMenuItemActionPerformed(evt);
            }
        });
        tabPopupMenu.add(tabNewNameMenuItem);

        tabDeleteMenuItem.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.tabDeleteMenuItem.mnemonic").charAt(0));
        tabDeleteMenuItem.setText(bundle.getString("SolutionPanel.tabDeleteMenuItem.text")); // NOI18N
        tabDeleteMenuItem.setToolTipText(""); // NOI18N
        tabDeleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabDeleteMenuItemActionPerformed(evt);
            }
        });
        tabPopupMenu.add(tabDeleteMenuItem);
        tabPopupMenu.add(jSeparator3);

        tabPrintMenuItem.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.tabPrintMenuItem.mnemonic").charAt(0));
        tabPrintMenuItem.setText(bundle.getString("SolutionPanel.tabPrintMenuItem.text")); // NOI18N
        tabPrintMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tabPrintMenuItemActionPerformed(evt);
            }
        });
        tabPopupMenu.add(tabPrintMenuItem);

        setLayout(new java.awt.BorderLayout());

        titleLabel.setBackground(new java.awt.Color(0, 51, 255));
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText(bundle.getString("SolutionPanel.titleLabel.text")); // NOI18N
        titleLabel.setOpaque(true);
        add(titleLabel, java.awt.BorderLayout.PAGE_START);

        southPanel.setPreferredSize(new java.awt.Dimension(100, 40));

        weiterButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.weiterButton.mnemonic").charAt(0));
        weiterButton.setText(bundle.getString("SolutionPanel.weiterButton.text")); // NOI18N
        weiterButton.setToolTipText(bundle.getString("SolutionPanel.weiterButton.toolTipText")); // NOI18N
        weiterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weiterButtonActionPerformed(evt);
            }
        });

        alleEinfachenButton.setMnemonic(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.alleEinfachenButton.mnemonic").charAt(0));
        alleEinfachenButton.setText(bundle.getString("SolutionPanel.alleEinfachenButton.text")); // NOI18N
        alleEinfachenButton.setToolTipText(bundle.getString("SolutionPanel.alleEinfachenButton.toolTipText")); // NOI18N
        alleEinfachenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alleEinfachenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout southPanelLayout = new javax.swing.GroupLayout(southPanel);
        southPanel.setLayout(southPanelLayout);
        southPanelLayout.setHorizontalGroup(
            southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(southPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(weiterButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alleEinfachenButton)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        southPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {alleEinfachenButton, weiterButton});

        southPanelLayout.setVerticalGroup(
            southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(southPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(southPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weiterButton)
                    .addComponent(alleEinfachenButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(southPanel, java.awt.BorderLayout.PAGE_END);

        solutionTabbedPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        solutionTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solutionTabbedPaneMouseClicked(evt);
            }
        });
        solutionTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                solutionTabbedPaneStateChanged(evt);
            }
        });
        add(solutionTabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void toStepMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toStepMenuItemActionPerformed
        getActTab();
        setActSelectedIndex(rightMouseClickedIndex);
        resetSudokuToIndex(actSelectedIndex);
        actList.setSelectedIndex(actSelectedIndex);
        mainFrame.setSolutionStep(actSteps.get(actSelectedIndex), true);
        mainFrame.fixFocus();
    }//GEN-LAST:event_toStepMenuItemActionPerformed

    private void solveFromHereMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solveFromHereMenuItemActionPerformed
        getActTab();
        processDoubleClick(rightMouseClickedIndex);
        mainFrame.setSolutionStep(actSteps.get(rightMouseClickedIndex), true);
        mainFrame.fixFocus();
    }//GEN-LAST:event_solveFromHereMenuItemActionPerformed

    private void deleteFromHereMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFromHereMenuItemActionPerformed
        getActTab();
        for (int i = actSteps.size() - 1; i >= rightMouseClickedIndex; i--) {
            actSteps.remove(i);
        }
        actSteps.add(new SolutionStep(SolutionType.INCOMPLETE));
        setStepsInList();
        setActSelectedIndex(rightMouseClickedIndex);
        resetSudokuToIndex(actSelectedIndex);
        actList.setSelectedIndex(actSelectedIndex);
        mainFrame.setSolutionStep(null, true);
        mainFrame.fixFocus();
    }//GEN-LAST:event_deleteFromHereMenuItemActionPerformed

    private void solutionListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solutionListMouseClicked
        getActTab();
        //System.out.println("solutionListMouseClicked: " + evt.getButton() + "/" + evt.getClickCount());
        // Element in der Liste ausgewählt
        if (evt.getButton() == 1) {
            int index = solutionList.getSelectedIndex();
            if (index != -1) {
                if (evt.getClickCount() == 2) {
                    processDoubleClick(index);
                }
                // Step anzeigen
                mainFrame.setSolutionStep(actSteps.get(index), true);
            }
        } else if (evt.getButton() == 3) {
            rightMouseClickedIndex = solutionList.locationToIndex(evt.getPoint());
            if (actSteps.get(rightMouseClickedIndex).getType() == SolutionType.INCOMPLETE) {
                deleteFromHereMenuItem.setEnabled(false);
                toStepMenuItem.setEnabled(false);
                solveFromHereMenuItem.setEnabled(true);
            } else {
                deleteFromHereMenuItem.setEnabled(true);
                toStepMenuItem.setEnabled(true);
                solveFromHereMenuItem.setEnabled(false);
            }
            solutionPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
        mainFrame.fixFocus();
    }//GEN-LAST:event_solutionListMouseClicked

    private void alleEinfachenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alleEinfachenButtonActionPerformed
        if (solver == null) {
            return;
        }
        getActTab();
        if (actSelectedIndex == -1) {
            weiterButtonActionPerformed(null);
        }
        if (actSelectedIndex == -1) {
            return;
        }
        while (actSelectedIndex < actSteps.size() - 1 &&
                SolutionType.getStepConfig(actSteps.get(actSelectedIndex).getType()).getLevel() == DifficultyType.EASY.ordinal()) {
            weiterButtonActionPerformed(null);
        }
        if (actSelectedIndex < actSteps.size() &&
                SolutionType.getStepConfig(actSteps.get(actSelectedIndex).getType()).getLevel() == DifficultyType.EASY.ordinal()) {
            weiterButtonActionPerformed(null);
        }
        mainFrame.fixFocus();
    }//GEN-LAST:event_alleEinfachenButtonActionPerformed

    private void weiterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weiterButtonActionPerformed
        getActTab();
        if (actSelectedIndex != -1) {
            if (actList.getSelectedIndex() != actSelectedIndex) {
                actList.setSelectedIndex(actSelectedIndex);
                actList.ensureIndexIsVisible(actSelectedIndex);
                // Step anzeigen
                mainFrame.setSolutionStep(actSteps.get(actSelectedIndex), true);
                return;
            } else {
                mainFrame.stepAusfuehren();
            }
        }
        if (actSelectedIndex < actSteps.size() - 1) {
            setActSelectedIndex(actSelectedIndex + 1);
            actList.setSelectedIndex(actSelectedIndex);
            actList.ensureIndexIsVisible(actSelectedIndex);
            mainFrame.setSolutionStep(actSteps.get(actSelectedIndex), true);
        }
        mainFrame.fixFocus();
    }//GEN-LAST:event_weiterButtonActionPerformed

private void solutionTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solutionTabbedPaneMouseClicked
    //System.out.println("solutionTabbedPaneMouseClicked: " + evt.getButton() + "/" + evt.getClickCount());
    if (evt.getButton() != 3) {
        return;
    }
    for (int i = 0; i < solutionTabbedPane.getTabCount(); i++) {
        Rectangle rect = solutionTabbedPane.getBoundsAt(i);
        if (rect == null || !rect.contains(evt.getPoint())) {
            continue;
        }
        getActTab();
        if (actSteps == null || actSteps.isEmpty()) {
            return;
        }
        tabPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        return;
    }

}//GEN-LAST:event_solutionTabbedPaneMouseClicked

private void tabNewNameMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tabNewNameMenuItemActionPerformed
    int index = solutionTabbedPane.getSelectedIndex();
    if (index == -1) {
        return;
    }
    String init = titels.get(index);
    String newTitel = JOptionPane.showInputDialog(java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.new_name"), init);
    if (newTitel != null) {
        setNewTabTitle(index, newTitel);
    }
    mainFrame.fixFocus();
}//GEN-LAST:event_tabNewNameMenuItemActionPerformed

private void tabNewMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tabNewMenuItemActionPerformed
    getActTab();
    addTabPane(actSteps);
    mainFrame.fixFocus();
}//GEN-LAST:event_tabNewMenuItemActionPerformed

private void tabDeleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tabDeleteMenuItemActionPerformed
    deleteTabPane();
    mainFrame.fixFocus();
}//GEN-LAST:event_tabDeleteMenuItemActionPerformed

private void solutionTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_solutionTabbedPaneStateChanged
    // the newly visible list is drawn, so the variables have to be adjusted
    if (inTabbedPaneRemoveAll) {
        // nichts tun, sonst gibts Chaos
        return;
    }
    getActTab();
    if (actList != null && actSteps != null) {
        int index = actList.getSelectedIndex();
        if (index != -1) {
            resetSudokuToIndex(actSelectedIndex);
            // Step anzeigen
            mainFrame.setSolutionStep(actSteps.get(actSelectedIndex), true);
        }
    }
}//GEN-LAST:event_solutionTabbedPaneStateChanged

private void tabPrintMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tabPrintMenuItemActionPerformed
    if (actSteps != null) {
        String initialState = mainFrame.getSudokuPanel().getSudoku().getInitialState();
        if (initialState == null) {
            initialState = mainFrame.getSudokuPanel().getSudoku().getSudoku(ClipboardMode.CLUES_ONLY);
        }
//        new PrintSolutionDialog(mainFrame, true, actSteps, mainFrame.getSudokuPanel().getSudoku().getSudoku(ClipboardMode.LIBRARY)).setVisible(true);
        new PrintSolutionDialog(mainFrame, true, actSteps, initialState).setVisible(true);
        mainFrame.fixFocus();
    }
}//GEN-LAST:event_tabPrintMenuItemActionPerformed

    public void setTitleLabelColors(Color fore, Color back) {
        titleLabel.setBackground(back);
        titleLabel.setForeground(fore);
    }
    
    public void initialize(List<SolutionStep> newSteps) {
        // first reset to one Tab
        inTabbedPaneRemoveAll = true;
        solutionTabbedPane.removeAll();
        inTabbedPaneRemoveAll = false;
        lists.clear();
        titels.clear();
        tabSteps.clear();
        selectedIndices.clear();
        addTabPane();
        
        this.solver = SudokuSolverFactory.getDefaultSolverInstance();
        if (newSteps != null) {
            setActSteps(newSteps);
            setStepsInList();
            setActSelectedIndex(-1);
            actList.ensureIndexIsVisible(0);
        }
    }
    
    /**
     * Resets the panel so that all solutions are shown. All indices are
     * reset to -1.
     * 
     * @param titels The titels of the solutions
     * @param solutions The lists with SolutionSteps for all solutions
     */
    public void initialize(List<String> titels, List<List<SolutionStep>> solutions) {
        if (titels.size() != solutions.size()) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                    "SolutionPanel.initialize(): titels and solutions don''t have the same length " + "({0}/{1})", 
                    new Object[]{titels.size(), solutions.size()});
        }
        int size = titels.size();
        if (solutions.size() < size) {
            size = solutions.size();
        }
        
        // first reset to one Tab
        initialize(solutions.get(0));
        setNewTabTitle(0, titels.get(0));
        
        // now add the rest
        for (int i = 1; i < size; i++) {
            addTabPane(solutions.get(i), titels.get(i));
        }
    }
    
    public void addStep(SolutionStep step) {
        getActTab();
        if (actSteps.get(actSteps.size() - 1).getType() != SolutionType.INCOMPLETE) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.cant_add_step"), 
                    java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.error"),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        actSteps.add(actSteps.size() - 1, step);
        setStepsInList();
    }
    
    private void resetSudokuToIndex(int index) {
        getActTab();
        Sudoku2 sudoku = mainFrame.getSudokuPanel().getSudoku();
        sudoku.resetSudoku();
        for (int i = 0; i < index; i++) {
            solver.doStep(sudoku, actSteps.get(i));
        }
        setActSelectedIndex(index);
        mainFrame.getSudokuPanel().clearUndoRedo();
        mainFrame.getSudokuPanel().clearColoring();
        mainFrame.getSudokuPanel().checkProgress();
    }
    
    private void processDoubleClick(int index) {
        // bei Doppelklick wird das Sudoku2 neu geladen, dann werden alle Schritte bis zum
        // geklickten ausgeführt
        getActTab();
        resetSudokuToIndex(index);
        
        // wenn der aktuelle letzte Step INCOMPLETE ist, wird das Sudoku2 von hier weg neu gelöst
        if (actSteps.get(index).getType() == SolutionType.INCOMPLETE) {
            actSteps.remove(actSteps.size() - 1);
            Sudoku2 actSudoku = mainFrame.getSudokuPanel().getSudoku().clone();
            solver.setSudoku(actSudoku, actSteps);
            solver.solve(true);
            setActSteps(solver.getSteps());
            setStepsInList();
            //actList.setSelectedIndex(actSelectedIndex);
            setActSelectedIndex(actSelectedIndex);
            mainFrame.getSudokuPanel().checkProgress();
        }
    }
    
    @SuppressWarnings("unchecked")
    private void setStepsInList() {
        getActTab();
        String[] data = new String[actSteps.size()];
        actStepBackgroundColors = new Color[actSteps.size()];
        actStepForegroundColors = new Color[actSteps.size()];
        int tmpIndex = solutionTabbedPane.getSelectedIndex();
        stepBackgroundColors.remove(tmpIndex);
        stepForegroundColors.remove(tmpIndex);
        stepBackgroundColors.add(tmpIndex, actStepBackgroundColors);
        stepForegroundColors.add(tmpIndex, actStepForegroundColors);
        for (int i = 0; i < actSteps.size(); i++) {
            //data[i] = steps.get(i).toString(1);
//            System.out.println("setStepsInList(): " + actSteps.get(i));
            data[i] = actSteps.get(i).toString(2);
//            System.out.println("setStepsInList(): " + data[i]);
//            System.out.println("   type: " + actSteps.get(i).getType());
//            System.out.println("   config: " + SolutionType.getStepConfig(actSteps.get(i).getType()));
//            System.out.println("   level: " + SolutionType.getStepConfig(actSteps.get(i).getType()).getLevel());
//            System.out.println("   level2: " + Options.getInstance().getDifficultyLevels()[SolutionType.getStepConfig(actSteps.get(i).getType()).getLevel()]);
//            System.out.println("   background: " + Options.getInstance().getDifficultyLevels()[SolutionType.getStepConfig(actSteps.get(i).getType()).getLevel()].getBackgroundColor());
//            System.out.println("   foreground: " + Options.getInstance().getDifficultyLevels()[SolutionType.getStepConfig(actSteps.get(i).getType()).getLevel()].getForegroundColor());
            actStepBackgroundColors[i] = Options.getInstance().getDifficultyLevels()[SolutionType.getStepConfig(actSteps.get(i).getType()).getLevel()].getBackgroundColor();
            actStepForegroundColors[i] = Options.getInstance().getDifficultyLevels()[SolutionType.getStepConfig(actSteps.get(i).getType()).getLevel()].getForegroundColor();
        }
        actList.setListData(data);
    }
    
    private void stepListMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // Element in der Liste ausgewählt
        getActTab();
        if (evt.getButton() == 1) {
            int index = actList.getSelectedIndex();
            if (index != -1) {
                if (evt.getClickCount() == 2) {
                    processDoubleClick(index);
                }
                // Step anzeigen
                mainFrame.setSolutionStep(actSteps.get(index), true);
            }
        } else if (evt.getButton() == 3) {
            if (actSteps != null) {
                rightMouseClickedIndex = actList.locationToIndex(evt.getPoint());
                if (rightMouseClickedIndex != -1) {
                    if (actSteps.get(rightMouseClickedIndex).getType() == SolutionType.INCOMPLETE) {
                        deleteFromHereMenuItem.setEnabled(false);
                        toStepMenuItem.setEnabled(false);
                        solveFromHereMenuItem.setEnabled(true);
                    } else {
                        deleteFromHereMenuItem.setEnabled(true);
                        toStepMenuItem.setEnabled(true);
                        solveFromHereMenuItem.setEnabled(false);
                    }
                    solutionPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        }
        mainFrame.fixFocus();
    }      
    
    private void addTabPane() {
        addTabPane(null);
    }
    
    private void addTabPane(List<SolutionStep> steps) {
        String titel = java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.solution") + 
                " " + (solutionTabbedPane.getTabCount() + 1);
        addTabPane(steps, titel);
    }
    
    @SuppressWarnings("unchecked")
    private void addTabPane(List<SolutionStep> steps, String titel) {
        JList tmpList = new JList();
        tmpList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        //tmpList.setCellRenderer(listRenderer);
        tmpList.setCellRenderer(new SolutionListRenderer());
        tmpList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stepListMouseClicked(evt);
            }
        });
        
        JScrollPane newPane = new JScrollPane();
        newPane.setViewportView(tmpList);
        
        solutionTabbedPane.add(titel, newPane);
        solutionTabbedPane.setSelectedIndex(solutionTabbedPane.getTabCount() - 1);
        
        titels.add(titel);
        lists.add(tmpList);
        selectedIndices.add(-1);
        stepBackgroundColors.add(null); // will be replaced in setStepsInList()
        stepForegroundColors.add(null); // will be replaced in setStepsInList()
        if (steps != null) {
            List<SolutionStep> newSteps = new ArrayList<SolutionStep>();
            for (SolutionStep step : steps) {
                newSteps.add(step);
            }
            tabSteps.add(newSteps);
            setStepsInList();
        } else {
            tabSteps.add(null);
        }
        
        getActTab();
    }
    
    private void deleteTabPane() {
        if (solutionTabbedPane.getTabCount() <= 1) {
            JOptionPane.showMessageDialog(this, java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.cant_delete_step"), 
                    java.util.ResourceBundle.getBundle("intl/SolutionPanel").getString("SolutionPanel.error"),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int index = solutionTabbedPane.getSelectedIndex();
        if (index == -1) {
            return;
        }
        solutionTabbedPane.remove(index);
        titels.remove(index);
        tabSteps.remove(index);
        lists.remove(index);
        stepBackgroundColors.remove(index);
        stepForegroundColors.remove(index);
        getActTab();
    }
    
    private void getActTab() {
        int index = solutionTabbedPane.getSelectedIndex();
        if (index == -1) {
            index = 0;
        }
        if (lists.size() > index) {
            actList = lists.get(index);
            actSteps = tabSteps.get(index);
            actSelectedIndex = selectedIndices.get(index);
            actStepBackgroundColors = stepBackgroundColors.get(index);
            actStepForegroundColors = stepForegroundColors.get(index);
        } else {
            actList = null;
            actSteps = null;
            actSelectedIndex = -1;
            actStepBackgroundColors = null;
            actStepForegroundColors = null;
        }
    }
    
    private void setActSelectedIndex(int selectedIndex) {
        int index = solutionTabbedPane.getSelectedIndex();
        if (index == -1) {
            //System.out.println("setActSelectedIndex(): solutionTabbedPane.getSelectedIndex() == -1!");
            index = 0;
        }
        selectedIndices.remove(index);
        selectedIndices.add(index, selectedIndex);
        actSelectedIndex = selectedIndex;
    }
    
    private void setActSteps(List<SolutionStep> steps) {
        int index = solutionTabbedPane.getSelectedIndex();
        if (index == -1) {
            //System.out.println("setActSteps(): solutionTabbedPane.getSelectedIndex() == -1!");
            index = 0;
        }
        tabSteps.remove(index);
        List<SolutionStep> tmpSteps = new ArrayList<SolutionStep>(steps.size());
        for (SolutionStep step : steps) {
            tmpSteps.add(step);
        }
        tabSteps.add(index, tmpSteps);
        actSteps = steps;
    }

    private void setNewTabTitle(int index, String newTitel) {
        titels.remove(index);
        titels.add(index, newTitel);
        solutionTabbedPane.setTitleAt(index, newTitel);
    }

    public List<String> getTitels() {
        return titels;
    }

    public List<List<SolutionStep>> getTabSteps() {
        return tabSteps;
    }

    /**
     * Loads all relevant objects into <code>state</code>. If <code>copy</code> is true,
     * all objects are copied.<br>
     * Some objects have to be copied regardless of parameter <code>copy</code>.
     * @param state
     * @param copy
     */
    @SuppressWarnings("unchecked")
    public void getState(GuiState state, boolean copy) {
        if (copy) {
            state.setTitels((List<String>) ((ArrayList)titels).clone());
            state.setTabSteps((List<List<SolutionStep>>) ((ArrayList)tabSteps).clone());
        } else {
            state.setTitels(titels);
            state.setTabSteps(tabSteps);
        }
    }

    /**
     * Loads back a saved state. Whether the objects had been copied
     * before is irrelevant here.
     * @param state
     */
    public void setState(GuiState state) {
        initialize(state.getTitels(), state.getTabSteps());
    }
    
    class SolutionListRenderer extends JLabel implements ListCellRenderer {
        private static final long serialVersionUID = 1L;
        
        SolutionListRenderer() {
            setOpaque(true);
        }
        
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            setBackground(Color.WHITE);
            if (actStepBackgroundColors != null) {
                setBackground(actStepBackgroundColors[index]);
            }
            setForeground(Color.BLACK);
            if (actStepForegroundColors != null) {
                setForeground(actStepForegroundColors[index]);
            }
            if (isSelected) {
                setBackground(actList.getSelectionBackground());
                setForeground(actList.getSelectionForeground());
            }
            String text = (value != null) ? value.toString() : "";
            setText("  " + text);
            return this;
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alleEinfachenButton;
    private javax.swing.JMenuItem deleteFromHereMenuItem;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JList solutionList;
    private javax.swing.JPopupMenu solutionPopupMenu;
    private javax.swing.JScrollPane solutionScrollPane;
    private javax.swing.JTabbedPane solutionTabbedPane;
    private javax.swing.JMenuItem solveFromHereMenuItem;
    private javax.swing.JPanel southPanel;
    private javax.swing.JMenuItem tabDeleteMenuItem;
    private javax.swing.JMenuItem tabNewMenuItem;
    private javax.swing.JMenuItem tabNewNameMenuItem;
    private javax.swing.JPopupMenu tabPopupMenu;
    private javax.swing.JMenuItem tabPrintMenuItem;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JMenuItem toStepMenuItem;
    private javax.swing.JButton weiterButton;
    // End of variables declaration//GEN-END:variables
    
}
