import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class enhanceElement {
    public int x,y;
    public Image element;
    static int panel_width = gameFrame.WIDTH - 22;
    static int panel_height = gameFrame.HEIGHT - 81;
    public static int element_width,element_height;
    public static int speed=1;
    public int move_count;
    public int t;//�����ƶ�����
    public int elementKind;
    public static int elementCatch=0;
    Random rand = new Random();
    public enhanceElement(){
        try {
            //���ѡ�������ǿ����
            elementKind=rand.nextInt(2)+1;
            element = ImageIO.read(new File("image/element"+elementKind+".png"));
            element_width=element.getWidth(null);
            element_height=element.getHeight(null);
        } catch(IOException e){System.out.println(e.toString());}
        //��ʼλ������Ļ���Ϸ������λ��
        x=rand.nextInt(200)+500;
        y=rand.nextInt(100);
        move_count=0;
    }
    public static void updatePosition(ArrayList elementlist){
        Random rand = new Random();
        for (int i = 0; i < elementlist.size(); i++) {
            enhanceElement temp = (enhanceElement) elementlist.get(i);

            if ( (temp.x < 0 || temp.x > panel_width || temp.y < 0 ))
                temp.t = rand.nextInt(4);
            if(temp.y > panel_height)
                elementlist.remove(i);
            //���move_countΪ300�����ҿ�ʼ����ƶ������������ÿ���л�������t
            if ( temp.move_count%300== 0) {
                temp.t = rand.nextInt(4);
            }
                /*
                ����ƶ�ʱ������ÿ���л�������t���������ƶ�
                ����ĳһ�����ƶ�300�κ�����move_count
                ������һ�ε��ƶ�����
                 */
                if (temp.t == 0) {
                    temp.x += speed;
                    temp.y += speed;
                } else if (temp.t == 1) {
                    temp.x -= 2*speed;
                    temp.y += speed;
                } else if (temp.t == 2) {
                    temp.x -= speed;
                    temp.y += 2*speed;
                } else {
                    temp.x += speed;
                    temp.y += 2*speed;
                }
                temp.move_count++;
            }
        }
    }
