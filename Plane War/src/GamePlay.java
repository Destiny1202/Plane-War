import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class GamePlay {
    public static boolean stop_flag = false;//Ϊtrueʱ��Ϸ����
    public static boolean pause_flag = false;//Ϊtrueʱ��Ϸ��ͣ
    private static int choose;
    public static void main(String[] args) {
        JFrame frame = new gameFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //������Ϸ���¿�ʼ��ѭ��
        do {
            //������Ϸ������ѭ��
            while (!stop_flag) {
                try {
                    sleep(5);//���߳���ͣһ��ʱ�䣬������ϷԪ�ص�λ��ˢ�¹���
                } catch (InterruptedException e) {
                }
                if (!pause_flag) {
                    frame.getContentPane().repaint();
                }

            }
            if(Plane.life_num==0)
            choose=JOptionPane.showConfirmDialog(null,"�Ƿ����¿�ʼ��",
                    "����������Ѿ��þ�",JOptionPane.YES_NO_OPTION);
            else
                choose=JOptionPane.showConfirmDialog(null,"�Ƿ����¿�ʼ��",
                        "��ϲ�㣡ͨ���ˣ�",JOptionPane.YES_NO_OPTION);
            if(choose==JOptionPane.YES_OPTION) {
                stop_flag = false;
                frame.dispose();
                //������Ϸʱ��ʼ������������ľ�̬����
                Plane.count=1;
                Enemy.enemy_group=1;
                Plane.life_num=3;
                enhanceElement.elementCatch=0;
                frame=new gameFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }while(choose==JOptionPane.YES_OPTION);
    }
}
