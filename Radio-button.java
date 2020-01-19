import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Radio-button extends JFrame {
    public static void main(String[] args) {
        new Radio-button();
    }

    public Radio-button() {
        setSize(500, 500);
        setTitle("Software Development ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MyJPanel panel= new MyJPanel();
        Container c = getContentPane();
        c.add(panel);
        setVisible(true);
    }

    public class MyJPanel extends JPanel implements ActionListener {
		
		Dimension dim;

		JLabel labelA, labelB, labelC, labelD;
		JTextField fieldA, fieldB, fieldC, fieldD;
		JRadioButton radioButton1, radioButton5, radioButton10;
		ButtonGroup group;

		double a = 1.0, b = 0.0, c = 0.0, d = 0.0;
		double s, x1, x2, y1, y2;
		double domain;
		int originX, originY, xw1, xw2, yw1,yw2;

		Font romanItalic = new Font("Times New Roman", Font.ITALIC, 16);
		Font romanPlain = new Font("Times New Roman", Font.PLAIN, 16);


        public MyJPanel() {
            // MyJPanelClass Constructor

			labelA = new JLabel("a");
			labelA.setFont(romanItalic);
			fieldA = new JTextField("1.0",4);
			fieldA.setFont(romanPlain);

			labelB = new JLabel("b");
			labelB.setFont(romanItalic);
			fieldB = new JTextField("0.0",4);
			fieldB.setFont(romanPlain);
		
			labelC = new JLabel("c");
			labelC.setFont(romanItalic);
			fieldC = new JTextField("0.0",4);
			fieldC.setFont(romanPlain);

			labelD = new JLabel("d");
			labelD.setFont(romanItalic);
			fieldD = new JTextField("0.0",4);
			fieldD.setFont(romanPlain);

			radioButton1 = new JRadioButton("1", true);
			radioButton1.setFont(romanPlain);
			radioButton1.addActionListener(this);

			radioButton5 = new JRadioButton("5", false);
			radioButton5.setFont(romanPlain);
			radioButton5.addActionListener(this);

			radioButton10 = new JRadioButton("10", false);
			radioButton10.setFont(romanPlain);
			radioButton10.addActionListener(this);

			group = new ButtonGroup();
			group.add(radioButton1);
			group.add(radioButton5);
			group.add(radioButton10);

	
			add(labelA);
			add(fieldA);
			add(labelB);
			add(fieldB);
			add(labelC);
			add(fieldC);
			add(labelD);
			add(fieldD);
			add(radioButton1);
			add(radioButton5);
			add(radioButton10);


        }

        public void paintComponent(Graphics g) {
            // Drawing Graph

			dim = getSize();
			super.paintComponent(g);

			originX = dim.width / 2;
			originY = dim.height / 2;

			g.setColor(new Color(128, 128, 128));
			g.setFont(romanItalic);
			g.drawLine(0,originY, dim.width, originY);
			g.drawLine(originX, 0, originX, dim.height);
			g.drawString("x", dim.width - 15 , originY + 15);
			g.drawString("y", originX + 10, 45);
			g.drawString("O", originX - 12 , originY + 15);

			if (radioButton1.isSelected())
			{
				domain = 1.0;
				x1 = -1.0;
			} else {
				if (radioButton5.isSelected())
				{
					domain = 5.0;
					x1 = -5.0;
				} else {
					domain = 10.0;
					x1 = -10.0;
				}
			}


			g.setColor(new Color(255,69,0));
			s = 0.05;
			
			y1 = func (x1);
			while (x1 <= domain)
			{
				x2 = x1 + s;
				y2 = func(x2);

				xw1 = (int)(originX * x1 / domain  + originX);
				yw1 = (int)(originY - originY * y1 / domain);
				xw2 = (int)(originX * x2 / domain + originX);
				yw2 = (int)(originY - originY * y2 / domain);
				g.drawLine(xw1, yw1, xw2, yw2);

				x1 = x2;
				y1 = y2;
			}

        }

        public void actionPerformed(ActionEvent e) {
            // Button click event

			a = Double.parseDouble(fieldA.getText());
			b = Double.parseDouble(fieldB.getText());
			c = Double.parseDouble(fieldC.getText());
			d = Double.parseDouble(fieldD.getText());
			repaint();
			
        }

		public double func(double x)
		{
			return a*x*x*x + b*x*x + c*x + d;
			
		}

    }
}
