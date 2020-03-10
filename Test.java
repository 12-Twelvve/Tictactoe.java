import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 class Test { 
        public static void main(String[] args){
            new Calculator();
         }
}
  class Calculator extends JFrame implements ActionListener {
    /*...

     */
    JTextField n1, n2, rslt;
    JButton fact, perm, comb;
    JLabel head, num1, num2, result;

    public Calculator(){
      setSize(800,500);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      head=new JLabel("P.C Calculator");
      head.setBounds(360,30,500,10);

      num1=new JLabel("Num1");
      num1.setBounds(120,60,500,50);

      n1=new JTextField();
      n1.setBounds(50,100,200,40);

      num2=new JLabel("Num2");
      num2.setBounds(580,60,500,50);

      n2=new JTextField();
      n2.setBounds(500,100,200,40);

      fact=new JButton("factorial");
      fact.setBounds(70,200,150,50);
      perm=new JButton("permutation");
      perm.setBounds(300,200,150,50);
      comb=new JButton("combination");
      comb.setBounds(530,200,150,50);
      result=new JLabel("Result");
      result.setBounds(120,260,450,50);
      rslt=new JTextField();
      rslt.setBounds(50,300,650,40);
      setBackground(Color.LIGHT_GRAY);

      add(head);
      add(num1);
      add(n1);
      add(num2);
      add(n2);
      add(fact);
      add(perm);
      add(comb);
      add(result);
      add(rslt);

      setLayout(null);
      setVisible(true);
      this.perm.addActionListener(this);
      this.comb.addActionListener(this);
      this.fact.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e){
          if(e.getSource()== (JButton)fact){
            int a=Integer.parseInt(n1.getText());
            final String anser= String.valueOf(factorial(a));
            rslt.setText(anser);
          }
          if(e.getSource()== (JButton)comb){
             combination();
          }
          if(e.getSource()== (JButton)perm){
            permutation();
         }
   }
   public int factorial(int a){
      int  temp=1;
            for(int i=1; i<=a; i++)
            {
            temp= temp*i;
            }
            return temp;
   }
   public void combination(){
      try {
         int  n=Integer.parseInt(n1.getText()) , r=Integer.parseInt(n2.getText());
         String answer = String.valueOf(factorial(n) / (factorial(r) * factorial(n-r)));
         rslt.setText(answer);
      } catch (Exception exp) {
         rslt.setText("please enter valid and both the input ");
      }
   }
   public void permutation(){
      try {
         int  n=Integer.parseInt(n1.getText()) , r=Integer.parseInt(n2.getText());
         String answer = String.valueOf(factorial(n) /factorial(n-r));
         rslt.setText(answer);
      } catch (Exception ex) {
         rslt.setText("please enter valid and both the input ");
      }
   }
 
}