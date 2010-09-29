/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Pro
 */
public class CarsCrossingFrame extends JFrame {

    private CarsDisplay carsDisplay;

    public CarsCrossingFrame() {

        carsDisplay = new CarsDisplay();

        setTitle("Cars Crossing, not crashing.. :-{");

        JPanel mainFrame = new JPanel();
        add(mainFrame);

        mainFrame.setLayout(new BorderLayout());

        JPanel display = new JPanel();
        JPanel buttonsPanel = new JPanel();

        mainFrame.add(display, BorderLayout.CENTER);
        mainFrame.add(buttonsPanel, BorderLayout.SOUTH);

        display.add(carsDisplay);




        pack(); // BUILD!! RAWR!!!

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {


                System.out.println("Exited Cars Crossing  bye bye...");

                System.exit(0);
            }
        });

        //maybe i had to add stuff for this to work

    }
}
