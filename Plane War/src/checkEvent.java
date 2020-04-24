import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class checkEvent {
    private Plane plane;
    private ArrayList enemylist;
    private ArrayList bulletlist;
    private gamePanel panel;
    private ArrayList enemybulletlist;
    private ArrayList elementlist;
    Random rand=new Random();

    public checkEvent(Plane plane, ArrayList enemylist, ArrayList bulletlist, ArrayList enemybulletlist,ArrayList elementlist, gamePanel panel) {
        this.plane = plane;
        this.enemylist = enemylist;
        this.bulletlist = bulletlist;
        this.enemybulletlist = enemybulletlist;
        this.elementlist=elementlist;
        this.panel = panel;
    }

    /*
    ��ײ��ⷽ��
    ��ҷɻ���л�����ײ����ҷɻ���л��ӵ�����ײ
    ��ҷɻ��ӵ���л�����ײ����ҷɻ�����ǿ���ߵ���ײ
    ͬʱ��ʵ����ǿ���ߵ�������
     */
    public void check() {
        //��������Խ���쳣
        try {
            //��ǿ���߳���Ƶ��
            if(rand.nextInt(1000)==1&&elementlist.size()<2){
                elementlist.add(new enhanceElement());}
            //���ڶ���л�����Ļ����ʧʱ����Ϸ��������һ�ʤ
            if (Enemy.enemy_group == 2 && enemylist.size() == 0) {
                panel.add(gamePanel.game_win);
                GamePlay.stop_flag = true;
                //panel.repaint();
            } else {
                if (enemylist.size() == 0) {
                    Enemy.enemy_group++;
                    for (int i = 0; i < 50; i++)
                        (panel.enemylist).add(new Enemy(i));
                    enemylist = panel.enemylist;
                }

            /*
            ����ӵ���л�����ײ
            �ȼ���ӵ���������Ϊ0���򲻻���л���ײ
             */
                for (int i = 0; i < bulletlist.size(); i++) {
                    for (int j = 0; j < enemylist.size(); j++) {
                        Enemy temp1 = (Enemy) enemylist.get(j);
                        Bullets temp2 = (Bullets) bulletlist.get(i);
                        Rectangle rect1 = new Rectangle(temp1.x, temp1.y, temp1.enemy_width, temp1.enemy_height);
                        Rectangle rect2 = new Rectangle(temp2.getX(), temp2.getY(), temp2.width, temp2.height);
                        if (rect1.intersects(rect2)) {
                            bulletlist.remove(i);
                            enemylist.remove(j);
                        }
                    }
                }
            /*
            ���ɻ���л�����ײ,����ײ������һ��ʱ��
             */
                for (int i = 0; i < enemylist.size(); i++) {
                    Enemy temp1 = (Enemy) enemylist.get(i);
                    Rectangle rect1 = new Rectangle(temp1.x, temp1.y, temp1.enemy_width, temp1.enemy_height);
                    Rectangle rect3 = new Rectangle(plane.x, plane.y, plane.plane_width, plane.plane_height);
                    if (rect1.intersects(rect3)) {
                        enemylist.remove(i);
                        sleep(1000);
                        Plane.life_num--;
                        if (Plane.life_num <= 0) {
                            panel.add(gamePanel.game_stop);
                            GamePlay.stop_flag = true;
                        } else {
                            //�ɻ�����ײ�󣬳�ʼ��Ϊ����ǿ����
                            enhanceElement.elementCatch=0;
                            panel.plane = new Plane();
                            plane = panel.plane;
                        }
                    }
                }
                /*
                ��ҷɻ�����ǿ���ߵ���ײ
                 */
                for(int i=0;i<elementlist.size();i++){
                    enhanceElement temp = (enhanceElement) elementlist.get(i);
                    Rectangle rect1 = new Rectangle(temp.x, temp.y, temp.element_width, temp.element_height);
                    Rectangle rect2 = new Rectangle(plane.x, plane.y, plane.plane_width, plane.plane_height);
                    if (rect1.intersects(rect2)) {
                        enhanceElement.elementCatch =temp.elementKind ;
                        try {
                            AudioPlayer.player.start(new AudioStream(new FileInputStream("music/pickelement.wav")));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        elementlist.remove(i);
                    }
                }
            /*
            ���л��ӵ����ҵķɻ���ײ,����ײ������һ��ʱ��
             */
                for (int i = 0; i < enemybulletlist.size(); i++) {
                    EnemyBullet temp = (EnemyBullet) enemybulletlist.get(i);
                    Rectangle rect1 = new Rectangle(temp.getX(), temp.getY(), temp.width, temp.height);
                    Rectangle rect2 = new Rectangle(plane.x, plane.y, plane.plane_width, plane.plane_height);
                    if (rect1.intersects(rect2)) {
                        enemybulletlist.remove(i);
                        sleep(1000);
                        Plane.life_num--;
                        if (Plane.life_num < 0) {
                            panel.add(gamePanel.game_stop);
                            GamePlay.stop_flag = true;
                        } else {
                            //�ɻ�����ײ�󣬳�ʼ��Ϊ����ǿ����
                            enhanceElement.elementCatch=0;
                            panel.plane = new Plane();
                            plane = panel.plane;
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.toString());
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
