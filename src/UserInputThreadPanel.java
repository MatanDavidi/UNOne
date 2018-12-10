
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

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

 /* 
 *
 * @author Matan Davidi
 * @version 10-dic-2018
 */
public class UserInputThreadPanel extends JPanel implements ActionListener, Runnable {

    private Thread thread;

    private JButton redButton;

    private JButton greenButton;

    private JButton blueButton;

    private JButton yellowButton;

    private Color chosenColor;
    
    private boolean printedMessage;

    public UserInputThreadPanel() {

        super(new BorderLayout());

        redButton = new JButton("Red");
        redButton.addActionListener((e) -> {

            redButtonClicked(e);

        });

        greenButton = new JButton("Green");
        greenButton.addActionListener((e) -> {

            greenButtonClicked(e);

        });

        blueButton = new JButton("Blue");
        blueButton.addActionListener((e) -> {

            blueButtonClicked(e);

        });

        yellowButton = new JButton("Yellow");
        yellowButton.addActionListener((e) -> {

            yellowButtonClicked(e);

        });

        add(redButton, BorderLayout.NORTH);

        add(greenButton, BorderLayout.EAST);

        add(blueButton, BorderLayout.WEST);

        add(yellowButton, BorderLayout.SOUTH);

        thread = new Thread(this);

    }

    public Color getChosenColor() {

        return chosenColor;

    }

    public Thread getThread() {

        return thread;

    }

    @Override
    public void run() {

        if (!printedMessage) {
            
            System.out.println("Choose a color");
            printedMessage = true;
            
        }
        
        thread.run();

    }

    private void redButtonClicked(ActionEvent e) {

        thread.interrupt();

    }

    private void greenButtonClicked(ActionEvent e) {

        thread.interrupt();

    }

    private void blueButtonClicked(ActionEvent e) {

        thread.interrupt();

    }

    private void yellowButtonClicked(ActionEvent e) {

        thread.interrupt();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
