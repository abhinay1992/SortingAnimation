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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SortAnimationRight extends JPanel {
    
    public final Border bd = BorderFactory.createLineBorder(Color.BLACK);
    public int[] arrInts = new int[340];
    public final JComboBox sortAlgorithmList;
    private final String[] algotithms={"-- Select --","Selection","Bubble","Insertion"};
    private String selection=null;
    public SortAnimationPanelRight sapr;
    public int threadSpeed=100;
    
    public SortAnimationRight()
    {
        setLayout(new BorderLayout());
        sapr = new SortAnimationPanelRight();
        sortAlgorithmList = new JComboBox(algotithms);
        sortAlgorithmList.setEditable(false);
        sortAlgorithmList.setMaximumSize(new Dimension(10, 10));
        sortAlgorithmList.setEnabled(false);
        sortAlgorithmList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               selection = sortAlgorithmList.getSelectedItem().toString();
            }
        });
        add(sapr,BorderLayout.CENTER);
        add(sortAlgorithmList,BorderLayout.SOUTH);
    }
    
    public class SortAnimationPanelRight extends JPanel implements Runnable
    {
        public SortAnimationPanelRight()
        {
            setBorder(bd);
            setBackground(Color.WHITE);
            setMinimumSize(new Dimension(340, 300));
            setMaximumSize(new Dimension(340, 300));
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.MAGENTA);
            if(arrInts[0]!=0)
            {
                for(int i=0;i<340;i++)
                {
                    g.drawLine(i, 300, i, arrInts[i]);
                }
            }
        }

        @Override
        public void run() {
            switch(selection)
            {
                case "Selection" : for(int i=0;i<arrInts.length-1;i++)
                                   {
                                        int tNum=i;
                                        for(int j=i+1;j<arrInts.length;j++)
                                        {
                                            if(arrInts[j]<arrInts[tNum])
                                                tNum=j;
                                        }
                
                                        int temp = arrInts[tNum];
                                        arrInts[tNum]=arrInts[i];
                                        arrInts[i]=temp;
                                        repaint();
                
                                        try
                                        {
                                            Thread.sleep(threadSpeed);
                                        }
                                        catch(Exception e)
                                        {
                                            JOptionPane.showMessageDialog(this, e);
                                        }
                                    }
                                    break; 
                case "Bubble" :     for(int i=0;i<arrInts.length-1;i++)
                                    {
                                        for(int j=0;j<arrInts.length-i-1;j++)
                                        {
                                            if(arrInts[j]>arrInts[j+1])
                                            {
                                                int temp = arrInts[j];
                                                arrInts[j]=arrInts[j+1];
                                                arrInts[j+1]=temp;
                                            }
                                            
                                            repaint();
                                        }
                                        try
                                        {
                                            Thread.sleep(threadSpeed);
                                        }
                                        catch(Exception e)
                                        {
                                            JOptionPane.showMessageDialog(this, e);
                                        }
                                    }
                    
                case "Insertion" :  for(int i=0;i<arrInts.length;i++)
                                    {
                                        int keyElem = arrInts[i];
                                        int j=i-1;
                                        
                                        for(;j>=0&&arrInts[j]>keyElem;j--)
                                        {
                                            arrInts[j+1]=arrInts[j];
                                        }
                                        arrInts[j+1]=keyElem;
                                        
                                        repaint();
                                        
                                        try
                                        {
                                            Thread.sleep(threadSpeed);
                                        }
                                        catch(Exception e)
                                        {
                                            JOptionPane.showMessageDialog(this, e);
                                        }
                                    }
                
                default : break;
            }
        }
    }
}
