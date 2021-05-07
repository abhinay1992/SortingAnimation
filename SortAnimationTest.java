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

import javax.swing.JFrame;

public class SortAnimationTest {

    public static void main(String[] args) {
        SortAnimation sa = new SortAnimation();
        sa.setVisible(true);
        sa.setResizable(false);
        sa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
}
