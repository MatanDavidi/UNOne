
import java.awt.Color;

/*
 * Copyright (C) 2018 Matan Davidi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author Matan Davidi
 */
public class ChangeColorFrame extends javax.swing.JFrame implements Runnable {

    private final Game parentGame;
    
    public final Thread mainThread;
    
    /**
     * Creates new form UNOFrame
     * @param parentGame
     */
    public ChangeColorFrame(Game parentGame) {
        initComponents();
        this.parentGame = parentGame;
        mainThread = new Thread(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        redButton = new javax.swing.JButton();
        greenButton = new javax.swing.JButton();
        blueButton = new javax.swing.JButton();
        yellowButton = new javax.swing.JButton();
        colorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        redButton.setText("RED");
        redButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                redButtonMouseClicked(evt);
            }
        });
        getContentPane().add(redButton, java.awt.BorderLayout.LINE_START);

        greenButton.setText("GREEN");
        greenButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                greenButtonMouseClicked(evt);
            }
        });
        getContentPane().add(greenButton, java.awt.BorderLayout.PAGE_START);

        blueButton.setText("BLUE");
        blueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                blueButtonMouseClicked(evt);
            }
        });
        getContentPane().add(blueButton, java.awt.BorderLayout.LINE_END);

        yellowButton.setText("YELLOW");
        yellowButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yellowButtonMouseClicked(evt);
            }
        });
        getContentPane().add(yellowButton, java.awt.BorderLayout.PAGE_END);

        colorLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        colorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        colorLabel.setText("Please pick a color");
        getContentPane().add(colorLabel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void greenButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_greenButtonMouseClicked
        changeColor(Color.GREEN);
    }//GEN-LAST:event_greenButtonMouseClicked

    private void blueButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blueButtonMouseClicked
        changeColor(Color.BLUE);
    }//GEN-LAST:event_blueButtonMouseClicked

    private void yellowButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yellowButtonMouseClicked
        changeColor(Color.YELLOW);
    }//GEN-LAST:event_yellowButtonMouseClicked

    private void redButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_redButtonMouseClicked
        changeColor(Color.RED);
    }//GEN-LAST:event_redButtonMouseClicked
    
    private void changeColor(Color newColor) {
        
        parentGame.setCurrentColor(newColor);
        setVisible(false);
        
    }
    
    @Override
    public void run() {
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blueButton;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JButton greenButton;
    private javax.swing.JButton redButton;
    private javax.swing.JButton yellowButton;
    // End of variables declaration//GEN-END:variables
}
