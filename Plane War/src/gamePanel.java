import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

class gamePanel extends JPanel {
    public Plane plane;
    public ArrayList bulletlist;
    public ArrayList enemylist;
    public ArrayList enemybulletlist;
    public ArrayList elementlist=new ArrayList();
    private checkEvent checkevent;
    public static JLabel game_stop=new JLabel(new ImageIcon("image/over.png"));
    public static JLabel game_win=new JLabel(new ImageIcon("image/game_win.png"));
    public boolean shot_flag=false;
    public gamePanel() {
        //�ڴ�����Ϸ���ʱ�����ɻ����󣬵л������ӵ�����
        plane = new Plane();
        enemylist = new ArrayList();
        bulletlist = new ArrayList();
        enemybulletlist=Enemy.enemybulletlist;
        //�ڴ���������ʱ��ͬʱ�����л�����
        for (int i = 0; i < 20; i++)
            enemylist.add(new Enemy(i));
        checkevent = new checkEvent(plane, enemylist, bulletlist,enemybulletlist,elementlist,this);
        //��Ӽ����¼��ӿ�
        myKeyAdapter keyadapter = new myKeyAdapter();
        addKeyListener(keyadapter);
        setVisible(true);
    }
    //����panel����
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon image=new ImageIcon("image/background"+Enemy.enemy_group+".jpg");
        g.drawImage(image.getImage(),0,0,this.getWidth(),this.getHeight(),null);
    }

    public void paint(Graphics g) {
        super.paint(g);
        checkevent.check();
        //���Ʒɻ�����
        Plane.updatePosition();
        g.drawImage(plane.getPlane(), plane.getX(), plane.getY(), this);
        enhanceElement.updatePosition(elementlist);
        for(int i=0;i<elementlist.size();i++){
            enhanceElement temp=(enhanceElement) elementlist.get(i);
            g.drawImage(temp.element,temp.x,temp.y,temp.element_width,temp.element_height,this);
        }
        /*
        �Ȼ�������ӵ�����ͨ������ӵ���color�������û������û�����ɫ��
        ��ʵ�ֶԵл��ӵ�����ĸ��ģ�ʵ�ֲ�ɫ�ӵ�
         */
        //�����ӵ�����
        Bullets.updatePosition(bulletlist);
        for (int i = 0; i < bulletlist.size(); i++) {
            Bullets bullet = (Bullets) bulletlist.get(i);
            g.setColor(bullet.color);
            g.fillOval(bullet.getX(), bullet.getY(), bullet.width, bullet.height);
            //System.out.println(bulletlist.size());
        }
        //���Ƶл��ӵ�����
        EnemyBullet.updatePosition(enemybulletlist);
        for(int i=0;i<enemybulletlist.size();i++){
            EnemyBullet enemyBullet=(EnemyBullet) enemybulletlist.get(i);
            g.fillOval(enemyBullet.getX(),enemyBullet.getY(),enemyBullet.width,enemyBullet.height);
        }
        Enemy.updatePostion(enemylist);
        for (int i = 0; i < enemylist.size(); i++) {
            Enemy enemy = (Enemy) enemylist.get(i);
            g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), Enemy.enemy_width, Enemy.enemy_height, this);
        }
    }

    //�ڲ��࣬ʵ�ֶԼ����¼�����Ӧ�����Ʒɻ����ƶ����ӵ������
    private class myKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                //plane.moveup();
                Plane.up_flag=true;
            }        //��ѹ�ϼ�
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                //plane.movedown();
                Plane.down_flag=true;
            }    //��ѹ�¼�
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //plane.moveleft();
                Plane.left_flag=true;
            }    //��ѹ���
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                //plane.moveright();
                Plane.right_flag=true;
            }    //��ѹ�Ҽ�
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                //�����ӵ�ʱ�������ʼλ������Ϊ�ɴ������м������
                try {
                    AudioPlayer.player.start(new AudioStream(new FileInputStream("music/shot.wav")));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if(enhanceElement.elementCatch==2){
                    bulletlist.add(new Bullets(plane.getX() + plane.plane_width / 2 - Bullets.width / 2, plane.getY()));
                }
                if(enhanceElement.elementCatch==1){
                    //Bullets.width=30;
                    //Bullets.height=30;
                    bulletlist.add(new Bullets(plane.getX() + plane.plane_width / 2 - Bullets.width / 2-30, plane.getY()));
                    bulletlist.add(new Bullets(plane.getX() + plane.plane_width / 2 - Bullets.width / 2+30, plane.getY()));
                }
                else
                    bulletlist.add(new Bullets(plane.getX() + plane.plane_width / 2 - Bullets.width / 2, plane.getY()));
            }
            repaint();
        }
        public void keyReleased(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                //plane.moveup();
                Plane.up_flag=false;
            }        //��ѹ�ϼ�
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                //plane.movedown();
                Plane.down_flag=false;
            }    //��ѹ�¼�
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //plane.moveleft();
                Plane.left_flag=false;
            }    //��ѹ���
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                //plane.moveright();
                Plane.right_flag=false;
            }//��ѹ�Ҽ�
            repaint();
        }
    }
}
