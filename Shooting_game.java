import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Shooting_game extends JFrame {
    final int windowWidth = 800;
    final int windowHeight = 500;

    public static void main(String[] args){
        new Shooting_game();
        
    }

    public Shooting_game() {
        Dimension dimOfScreen =
               Toolkit.getDefaultToolkit().getScreenSize();

        setBounds(dimOfScreen.width/2 - windowWidth/2,
                  dimOfScreen.height/2 - windowHeight/2,
                  windowWidth, windowHeight);
        setResizable(false);
        setTitle("Software Development II");
        setTitle("Afro hair and a sergeant");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MyJPanel panel= new MyJPanel();
        Container c = getContentPane();
        c.add(panel);
        setVisible(true);
    }

    public class MyJPanel extends JPanel implements
       ActionListener, MouseListener, MouseMotionListener {
        /* Variables for global settings */
        Dimension dimOfPanel;
        Timer timer;
        ImageIcon iconMe, iconEnemy;
        Image imgMe, imgEnemy;

        /* Variables for own aircraft */
        int myHeight, myWidth;
        int myX, myY, tempMyX;
        int gap = 100;
        int myMissileX, myMissileY;
        boolean isMyMissileActive;

        /* Variable for enemy's aircraft */
        int numOfEnemy = 15;
        int numOfAlive = numOfEnemy;
        int enemyWidth, enemyHeight;
        int[] enemyX = new int[numOfEnemy];
        int[] enemyY = new int[numOfEnemy];
        int[] enemyMove = new int[numOfEnemy];
        int[] enemyMissileX = new int[numOfEnemy];
        int[] enemyMissileY = new int[numOfEnemy];
        int[] enemyMissileSpeed = new int[numOfEnemy];
        boolean[] isEnemyAlive = new boolean[numOfEnemy];
        boolean[] isEnemyMissileActive =
                                 new boolean[numOfEnemy];

        /* Constructor（Initialization at game start）*/
        public MyJPanel() {
            // Overall settings
            setBackground(Color.black);
            addMouseListener(this);
            addMouseMotionListener(this);
            timer = new Timer(40, this);
            timer.start();

            // To import images
            //imgMe = getImg("jiki.jpg");
            imgMe = getImg("ji-2.jpg");
            myWidth = imgMe.getWidth(this);
            myHeight = imgMe.getHeight(this);

            //imgEnemy = getImg("teki.jpg");
            imgEnemy = getImg("te-2.jpg");
            enemyWidth = imgEnemy.getWidth(this);
            enemyHeight = imgEnemy.getHeight(this);

            // Initialization own aircraft and enemy aircraft
            initMyPlane();
            initEnemyPlane();
        }

        /* Drawing on the panel */
        public void paintComponent(Graphics g) {
            dimOfPanel = getSize();
            super.paintComponent(g);

            // Drawing each element
            drawMyPlane(g);       // Own plane
            drawMyMissile(g);     // Own missile
            drawEnemyPlane(g);    // Enemy plane
            drawEnemyMissile(g);  // enemy's missile

            // End processing when all enemy aircraft are shot down
            if (numOfAlive == 0) {
                System.exit(0);
            }
        }

        /* Processing at regular intervals（for ActionListener ）*/
        public void actionPerformed(ActionEvent e) {
            repaint();
        }

        /* Processing for MouseListener  */
        // Click the mouse button
        public void mouseClicked(MouseEvent e) {
        }

        // Click the mouse button
        public void mousePressed(MouseEvent e) {
            if (!isMyMissileActive) {
                myMissileX = tempMyX + myWidth/2;
                myMissileY = myY;
                isMyMissileActive = true;
            }
        }

        // Release the mouse button
        public void mouseReleased(MouseEvent e) {
        }

        // The mouse goes out of panel area
        public void mouseExited(MouseEvent e) {
        }

        // The mouse enters the panel area
        public void mouseEntered(MouseEvent e) {
        }

        /* Processing for MouseMotionListener */
        // Move the mouse
        public void mouseMoved(MouseEvent e) {
            myX = e.getX();
        }

        // Drag the mouse
        public void mouseDragged(MouseEvent e) {
            myX = e.getX();
        }

        /* Convert image file to Image class */
        public Image getImg(String filename) {
            ImageIcon icon = new ImageIcon(filename);
            Image img = icon.getImage();

            return img;
        }

        /* Initialization own aircraft */
        public void initMyPlane() {
            myX = windowWidth / 2;
            myY = windowHeight - 100;
            tempMyX = windowWidth / 2;
            isMyMissileActive = false;
        }

        /* Initialization of enemy aircraft */
        public void initEnemyPlane() {
            for (int i=0; i<7; i++) {
                enemyX[i] = 70*i;
                enemyY[i] = 50;
            }

            for (int i=7; i<numOfEnemy; i++) {
                enemyX[i] = 70*(i-6);
                enemyY[i] = 100;
            }

            for (int i=0; i<numOfEnemy; i++) {
                isEnemyAlive[i] = true;
                enemyMove[i] = 1;
            }

            for (int i=0; i<numOfEnemy; i++) {
                isEnemyMissileActive[i] = true;
                enemyMissileX[i] = enemyX[i] + enemyWidth/2;
                enemyMissileY[i] = enemyY[i];
                enemyMissileSpeed[i] = 10 + (i%6);
            }
        }

        /* Drawing own aircraft */
        public void drawMyPlane(Graphics g) {
            if (Math.abs(tempMyX - myX) < gap) {
                if (myX < 0) {
                    myX = 0;
                } else if (myX+myWidth > dimOfPanel.width) {
                    myX = dimOfPanel.width - myWidth;
                }
                tempMyX = myX;
                g.drawImage(imgMe, tempMyX, myY, this);
            } else {
                g.drawImage(imgMe, tempMyX, myY, this);
            }
        }

        /* Drawing own missile */
        public void drawMyMissile(Graphics g) {
            if (isMyMissileActive) {
                // Place misiles
                myMissileY -= 15;
                g.setColor(Color.white);
                g.fillRect(myMissileX, myMissileY, 2, 5);

                // Judgment of collision of own missile against enemy aircraft
                for (int i=0; i<numOfEnemy; i++) {
                    if (isEnemyAlive[i]) {
                        if ((myMissileX >= enemyX[i]) &&
                            (myMissileX <= enemyX[i]+enemyWidth) &&
                            (myMissileY >= enemyY[i]) &&
                            (myMissileY <= enemyY[i]+enemyHeight)) {
                            isEnemyAlive[i] = false;
                            isMyMissileActive = false;
                            numOfAlive--;
                        }
                    }
                }

                // Missile reinitialization when missile goes out of window
                if (myMissileY < 0) isMyMissileActive = false;
            }
        }

        /* Drawing enemy's aircraft */
        public void drawEnemyPlane(Graphics g) {
            for (int i=0; i<numOfEnemy; i++) {
                if (isEnemyAlive[i]) {
                    if (enemyX[i] > dimOfPanel.width -
                                                   enemyWidth) {
                        enemyMove[i] = -1;
                    } else if (enemyX[i] < 0) {
                        enemyMove[i] = 1;
                    }
                    enemyX[i] += enemyMove[i]*10;
                    g.drawImage(imgEnemy, enemyX[i],
                                          enemyY[i], this);
                }
            }
        }

        /* Drawing enemy's missiles */
        public void drawEnemyMissile(Graphics g) {
            for (int i=0; i<numOfEnemy; i++) {
                // Place missiles
                if (isEnemyMissileActive[i]) {
                    enemyMissileY[i] += enemyMissileSpeed[i];
                    g.setColor(Color.red);
                    g.fillRect(enemyMissileX[i],
                               enemyMissileY[i], 2, 5);
                }

                // Judgment of enemy missiles hitting own aircraft
                if ((enemyMissileX[i] >= tempMyX) &&
                    (enemyMissileX[i] <= tempMyX+myWidth) &&
                    (enemyMissileY[i]+5 >= myY) &&
                    (enemyMissileY[i]+5 <= myY+myHeight)) {
                    System.exit(0);
                }

                // Missile reinitialization when missile goes out of window
                if (enemyMissileY[i] > dimOfPanel.height) {
                    if (isEnemyAlive[i]) {
                        enemyMissileX[i] = enemyX[i] +
                                                 enemyWidth/2;
                        enemyMissileY[i] = enemyY[i] +
                                                  enemyHeight;
                    } else {
                        isEnemyMissileActive[i] = false;
                    }
                }
            }
        }
    }
}
