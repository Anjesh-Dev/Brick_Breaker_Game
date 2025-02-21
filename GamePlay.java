package My_Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener,KeyListener {
    private boolean play = false;
    private  Timer timer;
    private int delay=1;         // In milliseconds
    private int ballposX= 120;
    private int ballposY= 350;
    private int ballXdir= -1;
    private int ballYdir= -2;
    private int playerX= 350;
    private Map map;
    private int totalBrick = 21;
    private int score = 0;



    public GamePlay() {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay,this);
        timer.start();

        map= new Map(3,7);

    }

    public void paint(Graphics g) {

        // Black Background

        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);


        // Border

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 3, 3, 592);
        g.fillRect(683, 3, 3, 592);

        // Brick

        map.draw((Graphics2D)g);

        // Paddle

        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);


        // Ball

        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY, 20, 20);

        // Score
        g.setColor(Color.yellow);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Score:"+score,550 ,30);

        // Game Over

        if(ballposY==570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.yellow);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GameOver!!,score:"+score,200,300);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press Enter To Restart",230,350);
        }

        // Game Win
        if(totalBrick<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.yellow);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Congratulations,You Won!! ,score:"+score,200,300);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press Enter To Restart",230,350);

        }




    }

    @Override
    public void keyTyped(KeyEvent e) {




    }

    public void moveLeft(){
        playerX-=20;
    }

    public void moveRight(){
        playerX+=20;
    }




    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            play=true;
            if (playerX<=0) {
                playerX=0;
            }else {
                moveLeft();
            }
        }


        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            play = true;
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }

            if(e.getKeyCode()==KeyEvent.VK_ENTER){
                if(!play){
                    score=0;
                    totalBrick=21;
                    ballposX=120;
                    ballposY=350;
                    ballXdir=-1;
                    ballYdir=-2;
                    playerX=320;
                    map = new Map(3,7);


                }
            }




        repaint();


    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (play) {

            if(ballposX<=0) {
                ballXdir=-ballXdir;
            }
            if(ballposX>=670) {
                ballXdir=-ballXdir;
            }
            if(ballposY<=0) {
                ballYdir=-ballYdir;
            }

            Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
            Rectangle paddleRect = new Rectangle (playerX,550,100,8);

            if (ballRect.intersects(paddleRect)) {
                ballYdir= -ballYdir;

            }

           A: for(int i =0;i<map.MapBrick.length;i++){
                for(int j=0;j<map.MapBrick[0].length;j++){
                    if(map.MapBrick[i][j]>0){
                        int width = map.BrickWidtrh;
                        int height = map.BrickHeight;
                        int brickXpos = 80+j*width;
                        int brickYpos = 50+i*height;
                        Rectangle brickrect = new Rectangle(brickXpos,brickYpos,width,height);
                        if(ballRect.intersects(brickrect)){
                            map.setMapBrick(0,i,j);
                            totalBrick--;
                            score+=5;

                            if(ballposX+19<=brickXpos || ballposX+1>=brickXpos+width)
                                ballXdir=-ballXdir;
                            else
                                ballYdir=-ballYdir;
                            break A;

                        }

                    }

                }

            }

            ballposX += ballXdir;
            ballposY +=ballYdir;


        }

        repaint();

    }
}
