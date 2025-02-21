package My_Game;

import javax.swing.*;

public class MainClass {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Brick Breaker Game");
        frame.setSize(700 , 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        GamePlay gameplay = new GamePlay();
        frame.add(gameplay);
    }

}
