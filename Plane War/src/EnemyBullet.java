import java.util.ArrayList;

public class EnemyBullet {
    private int x,y;//�ӵ�������
    private final int panel_width=gameFrame.WIDTH-22;//��ȡpanel�ĸ߶ȿ��(22,81Ϊ��frame�Ĳ�
    private static  int panel_height=gameFrame.HEIGHT-81;
    public static  int width=15,height=15;//�ӵ���С����
    public static  int speed=4;//�ӵ��ٶ�
    public EnemyBullet(int x,int y){
        this.x=x;
        this.y=y;
    }
    public static void updatePosition(ArrayList enemybulletlist){
        for(int i=0;i<enemybulletlist.size();i++){
            EnemyBullet temp=(EnemyBullet) enemybulletlist.get(i);
            if(temp.y>panel_height)
                enemybulletlist.remove(i);
            else
                temp.y+=speed;
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
