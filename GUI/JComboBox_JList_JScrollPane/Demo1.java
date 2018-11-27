package GUI.JComboBox_JList_JScrollPane;
/**
 * 列表： JList
 * 滚动条：JScrollPane
 * 下拉框：JComboBox
 *
 * 注意：
 *      一般情况下滚动条与下拉框一起使用，下拉框加入滚动条组件中，滚动条则加入JPanel或则JFrame中，若直接将Jlist加入则无效果
 *      JComboBox， JList都是以一个字符串数组为参数，加入参数即可
 *
 */

import javax.swing.*;
import java.awt.*;

public class Demo1 extends JFrame{
    private JLabel jl1,jl2;
    private JScrollPane jsp;
    private JList jList;
    private JComboBox jcb;
    private JPanel jp1,jp2;

    Demo1(){
        jp1 = new JPanel();
        jp2 = new JPanel();



        this.add(jp1);
        this.add(jp2);

        jl1 = new JLabel("我的籍贯");
        jl2 = new JLabel("理想城市");

        String [] jcombobox_content = {"北京","天津","上海","重庆","贵州","山东","黑龙江","辽宁","吉林","浙江","江苏","福建","广东","广西","云南","安徽","江西","湖南","西藏","新疆","内蒙古","青海","甘肃","四川","河南","河北","湖北","山西","陕西","台湾","香港","澳门","宁夏"};
        jcb = new JComboBox(jcombobox_content);
        jcb.setMaximumRowCount(5);
//        jcb.setPopupVisible(false);


        String [] jList_content ={"北京","上海","重庆","遵义","济南","哈尔滨","杭州","南京","昆明","广州","厦门","三亚","咸阳","盐城","信阳","汉中","贵阳","成都","齐齐哈尔"};

        jList = new JList(jList_content);
        jList.setVisibleRowCount(5);

        jsp = new JScrollPane(jList);

        this.setLayout(new GridLayout(2,1));

        jp1.add(jl1);
        jp1.add(jcb);

        jp2.add(jl2);
        jp2.add(jsp);


        this.setTitle("旅游攻略");
        this.setSize(400,300);
        this.setLocation(550,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
    }
}
