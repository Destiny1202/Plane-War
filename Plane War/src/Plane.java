import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Plane {
    public static int x,y;//�ɻ�����
    private static final int panel_width=gameFrame.WIDTH-22;//��ȡpanel�ĸ߶ȿ��(22,81Ϊ��frame�Ĳ�
    private static final int panel_height=gameFrame.HEIGHT-81;
    public static int plane_width,plane_height;//�洢�ɻ���Ⱥ͸߶ȵľ�̬����
    private Image plane;
    public static int speed=4;
    public static int count=1;//���طɻ�ͼ����
    public static int life_num=3;//��������
    public static boolean up_flag=false;
    public static boolean down_flag=false;
    public static boolean left_flag=false;
    public static boolean right_flag=false;

    public Plane(){
        try{
            plane= ImageIO.read(new File("image/plane"+count+".png"));
            plane_width=plane.getWidth(null);
            plane_height=plane.getHeight(null);
        }
        catch(IOException e){System.out.println(e.toString());}
        //���ɴ�������Ϊ�ײ�����
        x=panel_width/2-plane_width/2;
        y=panel_height-plane_height;
    }
    //��ȡ�ɴ���private����
    public int getX(){return x;}
    public int getY(){return y;}
    public Image getPlane(){return plane;}
    /*
    λ�ø��¾�̬������ͨ��������paint�е��ã����������Ϊ��ʱ������ҷɻ����ƶ�
     */
    public static void updatePosition(){
        if(up_flag&&y>0)
            y=y-speed;
        if(down_flag&&(y+plane_height)<panel_height)
            y=y+speed;
        if(left_flag&&x>0)
            x=x-speed;
        if(right_flag&&(x+plane_width)<panel_width)
            x=x+speed;

    }
    public void moveup(){
        if(y>0)
            y=y-speed;
    }
    public void movedown(){
        if((y+plane_height)<panel_height)
           y=y+speed;
    }
    public void moveleft(){
        if(x>0)
            x=x-speed;
    }
    public void moveright(){
        if((x+plane_width)<panel_width)
           x=x+speed;
    }
}
