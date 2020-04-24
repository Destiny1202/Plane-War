import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;

class gameFrame extends JFrame {
    public final static  int WIDTH = 1200;
    public final static  int HEIGHT = 1000;
    public static gamePanel panel;
    JMenuBar menuBar;//����һ���˵�������
    JMenu menu, help, music,plane_choose;//�����˵�����
    JMenuItem plane_speed, enemy_speed,bullet_speed, exit, help1, about;//�����˵������
    JMenuItem plane1,plane2,plane3,plane4;
    JRadioButtonMenuItem on, off;//��ѡ�򣬽������JCheckBoxGroup()��ʵ�ֵ�ѡ
    boolean music_flag = true;//�������ֿ��ر��
    InputStream in;//��Ƶ�ļ�������
    AudioStream audioStream;
    Toolkit toolkit=Toolkit.getDefaultToolkit();
    public gameFrame() {
        //����frame�Ĵ�С��ͼ�꣬����
        super("Plane::War");
        //Dimension screenSize=toolkit.getScreenSize();
        setSize(WIDTH, HEIGHT);
        Image plane = new ImageIcon("image/plane3.png").getImage();
        setIconImage(plane);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("�˵�");
        help = new JMenu("����");
        music = new JMenu("����");
        plane_choose=new JMenu("ѡ��ɻ�");

        plane_speed = new JMenuItem("�ɻ��ٶ�");
        enemy_speed = new JMenuItem("�л��ٶ�");
        bullet_speed=new JMenuItem("�ӵ��ٶ�");
        exit = new JMenuItem("�˳�");
        help1 = new JMenuItem("����");
        about = new JMenuItem("����");
        //�洢�ɻ�ͼ��Ĳ˵���
        plane1=new JMenuItem(new ImageIcon("image/plane1.png"));
        plane2=new JMenuItem(new ImageIcon("image/plane2.png"));
        plane3=new JMenuItem(new ImageIcon("image/plane3.png"));
        plane4=new JMenuItem(new ImageIcon("image/plane4.png"));

        on = new JRadioButtonMenuItem("��", true);
        off = new JRadioButtonMenuItem("��", false);
        ButtonGroup group = new ButtonGroup();
        group.add(on);
        group.add(off);
        music.add(on);
        music.add(off);

        menuBar.add(menu);
        menuBar.add(help);
        menuBar.add(plane_choose);

        menu.add(plane_speed);
        menu.add(enemy_speed);
        menu.add(bullet_speed);
        menu.add(music);
        menu.add(exit);
        help.add(help1);
        help.add(about);
        plane_choose.add(plane1);
        plane_choose.add(plane2);
        plane_choose.add(plane3);
        plane_choose.add(plane4);

        MenuItemListener listener = new MenuItemListener();//�˵��������
        //Ϊÿ���˵�����Ӽ�����
        plane_speed.addActionListener(listener);
        enemy_speed.addActionListener(listener);
        bullet_speed.addActionListener(listener);
        exit.addActionListener(listener);
        help1.addActionListener(listener);
        about.addActionListener(listener);
        plane1.addActionListener(listener);
        plane2.addActionListener(listener);
        plane3.addActionListener(listener);
        plane4.addActionListener(listener);
        RadioButtonListener btnlistener = new RadioButtonListener();
        on.addActionListener(btnlistener);
        off.addActionListener(btnlistener);

        try {
            in = new FileInputStream("music/dragon rider.wav");
            audioStream = new AudioStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (music_flag) {
            AudioPlayer.player.start(audioStream);
        }
        panel = new gamePanel();
        panel.setFocusable(true);//����panel��ý����¼�
        getContentPane().add(panel);
        setVisible(true);
    }

    /*
    �ڲ�����������
    ʵ�ֶԲ˵�����ļ���
    ����ʵ��ѡ��ɻ������ķɻ��ٶȣ��л��ٶȣ�����ӵ��ٶȣ���������Ϸ˵��
     */
    private class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
         //��Ϸ��ͣ��־
            GamePlay.pause_flag=true;
            JMenuItem source = (JMenuItem) e.getSource();
            if (source == plane_speed) {
                String msg = JOptionPane.showInputDialog(null,
                        "������ɻ��ٶȣ�(��������ʼ�ٶ�Ϊ4)", null);
                Plane.speed = Integer.parseInt(msg);
            } else if (source == enemy_speed) {
                String msg = JOptionPane.showInputDialog(null,
                        "������л��ٶȣ�(��������ʼ�ٶ�Ϊ1)", null);
                Enemy.speed = Integer.parseInt(msg);
            }else if(source==bullet_speed){
                String msg = JOptionPane.showInputDialog(null,
                        "����������ӵ��ٶȣ�(��������ʼ�ٶ�Ϊ5)", null);
                Bullets.speed = Integer.parseInt(msg);
            }else if(source==help1){
                JOptionPane.showMessageDialog(null,
                        "����Ϸ�����ͨ����������Ʒɻ����������ƶ���ͨ���ո�������ӵ���"+
                        "\n��ҵķɻ����������������ڼ����ͨ���Ե���ǿ������ǿ��������"+
                        "\n��ǿ���������֣��ֱ�Ϊ���������ӵ����ӵ���С�ӱ���");
            }else if(source==about){
                JOptionPane.showMessageDialog(null,
                        "Plane::War" + "\n" + "2018��***");
            }else if(source==exit){
                System.exit(0);
            }else if(source==plane1){
                Plane.count=1;
                panel.plane=new Plane();
            }else if(source==plane2){
                Plane.count=2;
                panel.plane=new Plane();
            }else if(source==plane3){
                Plane.count=3;
                panel.plane=new Plane();
            }else if(source==plane4){
                Plane.count=4;
                panel.plane=new Plane();
            }
            GamePlay.pause_flag=false;
            //gameFrame.this.notifyAll();
        }
    }

    //�Ե�ѡ��ť�ļ���
    private class RadioButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JRadioButtonMenuItem source = (JRadioButtonMenuItem) e.getSource();
            if (source == on) {
                music_flag = true;
                AudioPlayer.player.start(audioStream);
            } else {
                music_flag = false;
                AudioPlayer.player.stop(audioStream);
            }
        }
    }
}
