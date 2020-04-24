import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Bullets {
    private int x,y;//�ӵ�������
    public  static int width=15,height=15;//�ӵ���С����
    public static  int speed=5;//�ӵ��ٶ�
    public  final Color color;

    public Bullets(int x,int y){
        //������񵽵ڶ�����ǿ���ߣ��ӵ���С�ӱ�,�����ӵ���С����ΪĬ�ϴ�С
        if(enhanceElement.elementCatch==2){
            width=30;
            height=30;
        }else{
            width=15;
            height=15;
        }
        this.x=x;
        this.y=y;
        //�˴�ʵ�ֶ�ÿ���ӵ���ɫ������
        Random rand=new Random();
        color=new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
    }
    //��ҷɻ��ӵ�λ�õĸ��¼��߽���
    public static void updatePosition(ArrayList bulletlist){
        for(int i=0;i<bulletlist.size();i++){
            Bullets bullet=(Bullets) bulletlist.get(i);
            if(bullet.y<0)
                bulletlist.remove(i);
            else
                bullet.y-=speed;
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
