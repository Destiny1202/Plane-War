import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private Image enemy;//enemyͼ��
    public int x, y;//enemy����
    public static int enemy_width = 34, enemy_height = 24;//�л���ȣ��߶Ⱦ�̬����
    static int panel_width = gameFrame.WIDTH - 22;
    static int panel_height = gameFrame.HEIGHT - 81;
    public static int speed = 1;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    private boolean flag;
    //�л��ƶ���ǣ�true�����ƶ���false�����ƶ�����һ���˶�ģʽ��ǣ��ڶ����˶�ģʽ����Ϊ��ʼ����ƶ��ı��
    private int move_count;
    private int t;
    public static ArrayList enemybulletlist=new ArrayList();//���ел�����һ���л��ӵ���̬����
    public static int enemy_group=1;//����enemy_group������ͼ�������λ�ã�����λ��

    public Enemy(int i) {
        try{enemy= ImageIO.read(new File("image/enemy"+enemy_group+".png"));}
        catch(IIOException e){e.printStackTrace();}
        catch (IOException e){System.out.println(e.getMessage());}
        enemy_width=enemy.getWidth(null);
        enemy_height=enemy.getHeight(null);
        //����i��ɶԷɻ���ʼλ�õ�����,iΪ�ڶ�̬�����е�λ��
        if (enemy_group == 1) {
            x = panel_width + 2 * enemy_width * (i - 1);
            y = panel_height / 5 - 2 * enemy_height * (i - 1);
            //enemy = toolkit.getImage("image/enemy1.jpg");
            flag = true;
            move_count=0;
        }
        if (enemy_group == 2) {
            //enemy = toolkit.getImage("image/enemy1.jpg");
            //System.out.println(enemy.getHeight(null));
            //enemy_width = 46;
            //enemy_height = 60;
            flag = false;
            x = 3 * enemy_width * (-i);
            y = panel_height / 3;
            move_count = 0;
        }
    }

    //���µл�λ��,mark��־�л�֮����˶��켣
    public static void updatePostion(ArrayList enemylist) {
        //System.out.println(enemylist.size());���Խ��ĵл��Ƿ��Ƴ���̬����
        if (enemy_group == 1) {
            for (int i = 0; i < enemylist.size(); i++) {
                Enemy temp = (Enemy) enemylist.get(i);
                //�жϵл�λ��
                if (temp.y < panel_height / 6)
                    temp.flag = true;
                if (temp.y > panel_height / 3)
                    temp.flag = false;
                /*
                �ɻ�������ұ߽�
                ���Ƶл���gamePanel�ĸ߶�1/6��1/3���ƶ�
                ��������߽�ʱ��3���ٶ������ƶ�
                �������±߽�ʱɾ������л�����
                 */
                if (temp.y > panel_height) {
                    enemylist.remove(i);
                    //System.out.println(enemylist.size());
                }
                if (temp.x < 0)
                    temp.y += 3 * speed;
                else if (temp.flag) {
                    temp.x -= speed;
                    temp.y += speed;
                } else {
                    temp.x -= speed;
                    temp.y -= speed;
                }
                temp.move_count++;
                //�˴���һ����������ǰ��л������ӵ���Ƶ�ʽ������Է�ֹ��һ��л�ͬʱ�����ӵ�
                if(temp.move_count%((20-i)*50)==0)
                    enemybulletlist.add(new EnemyBullet(temp.x+enemy_width/2-EnemyBullet.width/2,temp.y+enemy_height));
            }
        }

        if (enemy_group == 2) {
            Random rand = new Random();
            for (int i = 0; i < enemylist.size(); i++) {
                Enemy temp = (Enemy) enemylist.get(i);
                //���л���x�ƶ���400��ʱ����ʼ����ƶ�
                if (temp.x > 400)
                    temp.flag = true;
                else
                    temp.x += 3 * speed;
                //����ʼ����ƶ����ж��Ƿ񳬳��߽磬��Ϊ��ʼλ����Щ�л���������
                if (temp.flag && (temp.x < 0 || temp.x > panel_width || temp.y < 0 || temp.y > panel_height))
                    enemylist.remove(i);
                //���move_countΪ300�����ҿ�ʼ����ƶ������������ÿ���л�������t
                if (temp.flag && temp.move_count%300== 0) {
                    temp.t = rand.nextInt(4);
                }
                /*
                ����ƶ�ʱ������ÿ���л�������t���������ƶ�
                ����ĳһ�����ƶ�300�κ�����move_count
                ������һ�ε��ƶ�����
                 */
                if (temp.flag) {
                    if (temp.t == 0) {
                        temp.x += 2*speed;
                        temp.y -= speed;
                    } else if (temp.t == 1) {
                        temp.x -= speed;
                        temp.y -= speed;
                    } else if (temp.t == 2) {
                        temp.x -= speed;
                        temp.y += 2*speed;
                    } else {
                        temp.x += 2*speed;
                        temp.y += 2*speed;
                    }
                    temp.move_count++;
                    if(temp.move_count%200==0)
                        enemybulletlist.add(new EnemyBullet(temp.x+enemy_width/2-EnemyBullet.width,temp.y+enemy_height));
                }
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return enemy;
    }
}
