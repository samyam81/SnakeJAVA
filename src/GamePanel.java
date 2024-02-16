import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 100;
    final int X[]= new int[GAME_UNITS];
    final int Y[]=new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame () {
        newApple();
        running=true;
        timer= new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
            //draw the grid line on the screen
            for(int i = 0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
                g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
            }
            //draw the food on the screen on a random place
            g.setColor(Color.red);
            g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);
            //draw the head and the body of the snake
            for(int i =0;i<bodyParts;i++){
                if(i==0){
                    g.setColor(Color.green);             //for the head
                }else {
                    g.setColor(new Color(45,180,0)); //for the body

                }
                g.fillRect(X[i],Y[i],UNIT_SIZE,UNIT_SIZE);
                //score
                g.setColor(Color.red);
                g.setFont(new Font("Ink Free",Font.BOLD,40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
            }
        }else {
            gameOver(g);
        }

    }
    public void newApple(){
        appleX = random.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move(){
        for(int i = bodyParts;i>0;i--){
            X[i] = X[i-1];
            Y[i]=Y[i-1];

        }
        switch (direction){
            case 'U':
                Y[0]=Y[0]-UNIT_SIZE;
                break;
            case 'D':
                Y[0]=Y[0]+UNIT_SIZE;
                break;
            case 'L':
                X[0]=X[0]-UNIT_SIZE;
                break;
            case 'R':
                X[0]=X[0]+UNIT_SIZE;
                break;
        }
    }
    public void checkApple(){
        if((X[0] == appleX) && (Y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollision(){
        //checks if head collide the body
        for(int i = bodyParts;i>0;i--){
            if((X[0]== X[i]) && (Y[0]== Y[i])){
                running = false;
            }
        }
        //check if head touches left border
        if(X[0] < 0){
            running = false;
        }
        //checks if head touches right border
        if(X[0] >SCREEN_WIDTH){
            running = false;
        }
        //checks if head touched top border
        if(Y[0] < 0){
            running = false;
        }
        //checks if head touched bottom border
        if(Y[0] > SCREEN_HEIGHT){
            running = false;
        }
        if(!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        //score
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        //Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over",(SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
