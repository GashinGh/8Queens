
package pkg8queen;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author gashin gh
 */

public class Main {

  // define a method for creating the board and visualizing the results
 public static void Show(Chromosome ch){
JFrame Board;
JPanel squares[][] = new JPanel[8][8];
Board = new JFrame();
Board.setSize(650, 650);
Board.setLayout(new GridLayout(8, 8));
for (int i = 0; i < 8; i++) {
for (int j = 0; j < 8; j++) {
squares[j][i] = new JPanel();
if ((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) {
squares[j][i].setBackground(Color.white);
} else {
squares[j][i].setBackground(Color.black);
}
Board.add(squares[j][i]);
}
}
int[] Queens=new int[8];
ch.Get(Queens);
for(int i =0; i<8; i++){
squares[i][8-Queens[i]].add(new JLabel(new ImageIcon("8Queen.png")));
}
Board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
Board.setVisible(true);
}
    // running the classes and show the solution
    public static void main(String[] args) {
    GeneticAlgorithm GA=new GeneticAlgorithm();
    Chromosome[] Ch=new Chromosome[1];
    GA.Run(Ch);
    Show(Ch[0]);
    System.out.print(Ch[0].FitVal());
    }

}
