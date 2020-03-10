import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;




  class TicTacToe {
      public static void main(final String[] args) {
           
            new Player();
            
        }
   }
 class Player extends JFrame implements ActionListener {
      /**
     *
     */
     private static final long serialVersionUID = 1L;
     final JButton twop = new JButton("Two Player");
      final JButton onep = new JButton("One Player");

      public Player() {
          setSize(100, 100);
          this.add(onep);
          this.add(twop);
          this.onep.addActionListener(this);
          this.twop.addActionListener(this);
          setLayout(new GridLayout(2, 1));
          setVisible(true);
      }
      public void actionPerformed(final ActionEvent e) {
          final JButton button = (JButton) e.getSource();
          if (button==onep){
             new OnePlayer();
              System.out.println("1");
          } else {
              new TwoPlayer();
              System.out.println("2");
          }
          closeoption();

      }
      public void closeoption() {
         setVisible(false); // you can't see me!
         dispose();
      }
  }
  abstract class Board implements ActionListener{
      protected final JFrame frame;
   //   protected final JMenuBar mBar;
  //    protected final JMenu option;
  //    protected final JMenuItem oneplayer, twoplayer;
    public Board(){
        frame=new JFrame("TicTacToe");
        frame.setSize(300,350);
     //   mBar=new JMenuBar();
    //    frame.setJMenuBar(mBar);
    //    option=new JMenu("Option");
    //    oneplayer= new JMenuItem("One PLayer");
    //    twoplayer=new JMenuItem("Two Player");
     //   mBar.add(option);
     //   option.add(oneplayer);
     //   option.add(twoplayer);
     //   oneplayer.addActionListener(this);
     //   twoplayer.addActionListener(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
class TwoPlayer extends Board implements ActionListener{
    /*
    *
    */
    private final JButton button[] = new JButton[9];
    private  int alternate = 0;

       public TwoPlayer(){
        initializebuttons();
        frame.setLayout(new GridLayout(3, 3));
    }

    public void initializebuttons() {
        for (int i = 0; i <= 8; i++) {
            button[i] = new JButton();
            frame.add(button[i]);
            button[i].setText("");
            button[i].setBackground(Color.WHITE);
            button[i].addActionListener(this);
        }
    }

    public void resetButtons() {
        for (int i = 0; i <= 8; i++) {
            button[i].setText("");
            button[i].setBackground(Color.WHITE);
        }
        alternate = 0;
    }

    public void actionPerformed(final ActionEvent e) {

        final JButton buttonClicked = (JButton) e.getSource(); // get the particular button that was clicked
        while (buttonClicked.getText().equals("")) {
            if (alternate % 2 == 0) {
                buttonClicked.setText("X");
                buttonClicked.setFont(new Font("Arial", Font.PLAIN, 50));
                buttonClicked.setBackground(Color.BLUE);
                alternate++;
            } else {
                buttonClicked.setText("O");
                buttonClicked.setFont(new Font("Arial", Font.PLAIN, 50));
                buttonClicked.setBackground(Color.RED);
                alternate++;
            }
            if (checkForWin() == true) {
                if (alternate % 2 == 0) {
                    JOptionPane.showMessageDialog(null, "Red (O) win the Game.");
                } else {
                    JOptionPane.showMessageDialog(null, "Blue (X) win the Game.");
                }

                // JOptionPane.showConfirmDialog(null, "Game Over.");
                resetButtons();
                break;
            }

        }
        if (alternate == 9) {
            JOptionPane.showMessageDialog(null, "...Match Draw....");
            resetButtons();
        }
    }

    public boolean checkForWin() {
        /**
         * Reference: the button array is arranged like this as the board 
         *  0 | 1 | 2
         *  3 | 4 | 5 
         *  6 | 7 | 8
         */
        // horizontal win check
        if (checkAdjacent(0, 1) && checkAdjacent(1, 2)) // no need to put " == true" because the default check is for
                                                        // true
            return true;
        else if (checkAdjacent(3, 4) && checkAdjacent(4, 5))
            return true;
        else if (checkAdjacent(6, 7) && checkAdjacent(7, 8))
            return true;

        // vertical win check
        else if (checkAdjacent(0, 3) && checkAdjacent(3, 6))
            return true;
        else if (checkAdjacent(1, 4) && checkAdjacent(4, 7))
            return true;
        else if (checkAdjacent(2, 5) && checkAdjacent(5, 8))
            return true;

        // diagonal win check
        else if (checkAdjacent(0, 4) && checkAdjacent(4, 8))
            return true;
        else if (checkAdjacent(2, 4) && checkAdjacent(4, 6))
            return true;
        else
            return false;

    }

    public boolean checkAdjacent(final int a, final int b)
        {
            if ( button[a].getText().equals(button[b].getText()) && !button[a].getText().equals("") )
                return true;
            else
                return false;
        }
}

 //...............................AI........................................

class OnePlayer extends Board implements ActionListener{
    private final JButton button[] = new JButton[9];
    int alternate=0;
    int level=0;
    public OnePlayer(){
        initializebuttons();
        frame.setLayout(new GridLayout(3,3));
    }
    public void initializebuttons() {
        for (int i = 0; i <= 8; i++) {
            button[i] = new JButton();
            frame.add(button[i]);
            button[i].setText("");
            button[i].setBackground(Color.WHITE);
            button[i].addActionListener(this);
        }
    }
    public void actionPerformed(final ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        while (buttonClicked.getText().equals("")) {
                buttonClicked.setText("x"); /// for human =x;    
                buttonClicked.setFont(new Font("Arial", Font.PLAIN, 50));
                buttonClicked.setBackground(Color.BLUE);
                alternate++;
                if(checkForWin()){
                    JOptionPane.showMessageDialog(null, "BLUE (X) win the Game.");
                    resetButtons();
                    break;
                    //human wins x wins.
                    //reset.
                }
                if (alternate == 9) {
                    JOptionPane.showMessageDialog(null, "...Match Draw....");
                    resetButtons();
                    break;
                }
                JButton computer;
                if(alternate==1 && button[4].getText().equals("")){
                    computer=button[4];
                }
                else{
                    computer = (JButton) ComputerTurn();
                }
                 computer.setText("0"); /// for human =x;    
                 computer.setFont(new Font("Arial", Font.PLAIN, 50));
                 computer.setBackground(Color.RED);
                 if(checkForWin()){
                    JOptionPane.showMessageDialog(null, "Red (O) win the Game.");
                    resetButtons();
                    break;
                 }
                 alternate++;
            
        }
    }
     public JButton ComputerTurn(){
        JButton bestChild=new JButton();
        int previous = Integer.MIN_VALUE;
        for(int i=0;i<9;i++){
            if(button[i].getText().equals("")){
                button[i].setText("0");
                alternate++;
                int current = miniMax(false,0);
                button[i].setText("");
                alternate--;
                System.out.println("Child Score: " + current);
                System.out.println("buutton:"+i);
                if (current >= previous) {
                    bestChild = button[i];
                    previous = current;
                }
            }
        }
        return bestChild;
    }
    public int miniMax(boolean ismax,int depth){
        if(checkForWin()&& ismax){
            return -1;
        }
        else if (checkForWin()&& !ismax ){
            return 1;
        }
        else if(alternate==9){
            return 0;
        }
        if(ismax){
                int best = Integer.MIN_VALUE;
                for (int i = 0; i < 9; i++) {
                    if (button[i].getText().equals("")) {
                            button[i].setText("0");
                            alternate++;
                            best=Math.max(miniMax(false,depth+1), best);
                            button[i].setText("");
                            alternate--;
                            
                     }

                }
                return best-depth;
        }else{ 
                int best = Integer.MAX_VALUE;
                for (int i = 0; i < 9; i++) {
                     if (button[i].getText().equals("")) {
                             button[i].setText("x");
                             alternate++;
                             best=Math.min(miniMax(true,depth+1), best);
                             button[i].setText("");
                             alternate--;
                     }
                }
                return best+depth;
            }
    }

    
    public void resetButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            button[i].setText("");
            button[i].setBackground(Color.WHITE);
            alternate=0;
        }
    }
        public Boolean checkForWin() {
        /**
         * Reference: the button array is arranged like this as the board
         *  0 | 1 | 2
         *  3 | 4 | 5
         *  6 | 7 | 8
         */
        // horizontal win check
        if (checkAdjacent(0, 1) && checkAdjacent(1, 2)) // no need to put " == true" because the default check is for
                                                        // true
            return true;
        else if (checkAdjacent(3, 4) && checkAdjacent(4, 5))
            return true;
        else if (checkAdjacent(6, 7) && checkAdjacent(7, 8))
            return true;

        // vertical win check
        else if (checkAdjacent(0, 3) && checkAdjacent(3, 6))
            return true;
        else if (checkAdjacent(1, 4) && checkAdjacent(4, 7))
            return true;
        else if (checkAdjacent(2, 5) && checkAdjacent(5, 8))
            return true;

        // diagonal win check
        else if (checkAdjacent(0, 4) && checkAdjacent(4, 8))
            return true;
        else if (checkAdjacent(2, 4) && checkAdjacent(4, 6))
            return true;
        else
            return false;

    }

    public boolean checkAdjacent(final int a, final int b)
        {
            if ( button[a].getText().equals(button[b].getText()) && !button[a].getText().equals("") )
                return true;
            else
                return false;
        }
}
