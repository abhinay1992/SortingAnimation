/************************************************************
 *                                                          *
 *  CSCI 502        Assignment-9         Fall 2018          *                        
 *                                                          *
 *  Programmer:  Abhinay Imandi                             *
 *         ZID:  Z1856513                                   *
 *                                                          *
 *  Date Due:    12/10/2018 02:00 PM                        *
 *                                                          *
 *  Description : A Test class which is used to access the  *
 *                Jframe.                                   *
 ************************************************************/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SortAnimation extends JFrame {
    
    private final JPanel paintPanel,buttonPanel;
    private final JButton populateButton,sortButton,pauseThread,resumeThread,stopButton;
    private final JComboBox threadSpeed;
    private final String[] algotithms={"-- Select --","Selection","Bubble","Insertion"};
    private final String[] speeds={"-- Select --","Slow","Medium","Fast"};
    private final Random rand = new Random();
    private Thread th1,th2;
    
    public SortAnimation()
    {
        super("Sorting Animation");
        setSize(new Dimension(720, 400));
        setLayout(new BorderLayout(5, 5));
        SortAnimationLeft sal = new SortAnimationLeft();
        SortAnimationRight sar = new SortAnimationRight();
        paintPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        paintPanel.add(sal);
        paintPanel.add(sar);
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        populateButton = new JButton("Populate");
        populateButton.setMaximumSize(new Dimension(10, 10));
        populateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 for(int i=0;i<sal.arrInts.length;i++)
                 {
                     sal.arrInts[i]=rand.nextInt(300)+1;
                 }
                 sal.repaint();
                 for(int i=0;i<sar.arrInts.length;i++)
                 {
                     sar.arrInts[i]=rand.nextInt(300)+1;
                 }
                 sar.repaint();
                 
                 sortButton.setEnabled(true);
                 sal.sortAlgorithmList.setEnabled(true);
                 sar.sortAlgorithmList.setEnabled(true);
            }
        });
        
        sortButton = new JButton("Sort");
        sortButton.setMaximumSize(new Dimension(10, 10));
        sortButton.setEnabled(false);
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    th1 = new Thread(sal.sapl);
                    th2 = new Thread(sar.sapr);
                
                    th1.start();
                    th2.start();
                    stopButton.setEnabled(true);
                    threadSpeed.setEnabled(true);
                    pauseThread.setEnabled(true);
            }
        });
        
        pauseThread = new JButton("Pause");
        pauseThread.setEnabled(false);
        pauseThread.setMaximumSize(new Dimension(10, 10));
        pauseThread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                th1.suspend();
                th2.suspend();
                
                pauseThread.setEnabled(false);
                resumeThread.setEnabled(true);
                
            }
        });
        
        resumeThread = new JButton("Resume");
        resumeThread.setEnabled(false);
        resumeThread.setMaximumSize(new Dimension(10, 10));
        resumeThread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                th1.resume();
                th2.resume();
                
                resumeThread.setEnabled(false);
                pauseThread.setEnabled(true);
            }
        });
        
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.setMaximumSize(new Dimension(10, 10));
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                th1.stop();
                th2.stop();
                
                stopButton.setEnabled(false);
                pauseThread.setEnabled(false);
                resumeThread.setEnabled(false);
                threadSpeed.setEnabled(false);
            }
        });
        threadSpeed = new JComboBox(speeds);
        threadSpeed.setMaximumSize(new Dimension(10, 10));
        threadSpeed.setEditable(false);
        threadSpeed.setEnabled(false);
        threadSpeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String sp = threadSpeed.getSelectedItem().toString();
                
                switch(sp)
                {
                    case "Slow" : sal.threadSpeed=5000;
                                  sar.threadSpeed=5000;
                                  break;
                                  
                    case "Medium" : sal.threadSpeed=1000;
                                    sar.threadSpeed=1000;
                                    break;
                                    
                    case "Fast"  : sal.threadSpeed=100;
                                   sar.threadSpeed=100;
                                   break;
                                   
                    default : break;
                }
            }
        });
        
        buttonPanel.add(populateButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(pauseThread);
        buttonPanel.add(resumeThread);
        buttonPanel.add(stopButton);
        buttonPanel.add(threadSpeed);
        
        add(paintPanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
    }
}
