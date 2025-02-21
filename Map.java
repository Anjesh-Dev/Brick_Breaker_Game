package My_Game;

import java.awt.*;

public class Map {
    public int[][] MapBrick;
    public int BrickWidtrh;
    public int BrickHeight;

    public Map(int row , int col){
        MapBrick = new int[row][col];
        for(int i =0 ; i<row ; i++){
            for(int j =0 ; j<col ; j++){
                MapBrick[i][j]=1;
            }


        }

        BrickWidtrh = 540/col;
        BrickHeight = 150/row;

    }

    public void setMapBrick(int value , int r, int c){
       this.MapBrick [r][c] = value;

    }

    public void draw (Graphics2D g){

        for (int i =0 ; i<MapBrick.length ; i++){
            for (int j = 0 ; j<MapBrick[0].length ; j++){
                if(MapBrick[i][j]>0){
                    g.setColor(Color.pink);
                    g.fillRect(j*BrickWidtrh+80 , i*BrickHeight+50, BrickWidtrh , BrickHeight);
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*BrickWidtrh+80 , i*BrickHeight+50, BrickWidtrh , BrickHeight);
                }

            }
        }


    }




}
