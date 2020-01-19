// Drawing
//
import java.awt.*;
import javax.swing.*;

public class Drawing extends JFrame {
   public static void main(String[] args){
       new Drawing();
   }

   public Drawing() {
       MyJPanel myJPanel= new MyJPanel();
       Container cont = getContentPane();
       cont.add(myJPanel);

       setSize(300,250);
       setTitle("Software Development ");
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setVisible(true);
   }

   public class MyJPanel extends JPanel{
       public MyJPanel(){
           // Constractor
       }

       public void paintComponent(Graphics g) {

	   g.setColor(Color.cyan);
           g.fillOval( 30, 130, 130, 60);

	   g.setColor(Color.yellow);
	   int x[] = {150,115,185};
	   int y[] = {20,70,70};
	   g.fillPolygon(x, y, 3);
	   int m[] = {145,115,185};
	   int n[] = {85,35,35};
	   g.fillPolygon(m, n, 3);

	   g.setColor(Color.orange);
	   g.drawLine(50,10,100,30);
	   g.drawLine(50,30,100,40);
	   g.drawLine(50,50,100,50);

	   g.setColor(new Color(0,128,128));
	   int i[] = {180,220,260};
	   int j[] = {115,85,115};
	   g.fillPolygon(i, j, 3);
	   int k[] = {170,220,270};
	   int l[] = {155,105,155};
	   g.fillPolygon(k, l, 3);

	   g.setColor(new Color(153,51,0));
	   g.fillRect(210,155,30,50);

           //g.fillOval(120, 100, 20, 20);
           //g.fillRect(102, 123, 14, 30);
           //g.fillRect( 95, 162, 30, 14);
       }
   }
}
