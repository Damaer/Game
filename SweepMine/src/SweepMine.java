import constant.DifficultModeEnum;
import constant.ImgPath;
import constant.Status;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SweepMine extends JFrame {
    // 开始按钮
    private JButton start;
    // 困难模式
    JComboBox difficultMode;

    private Block[][] blocks;

    public SweepMine(String title) {
        init();

        setTitle(title);

        setSize(800, 600);
        GridBagLayout layout = new GridBagLayout();    //创建GridBagLayout布局管理器
        setLayout(layout);

        GridBagConstraints s = new GridBagConstraints();//定义一个GridBagConstraints，
        // 是用来控制添加进的组件的显示位置
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        s.fill = GridBagConstraints.BOTH;
        MainJpanel mainJpanel = new MainJpanel(blocks);
        getContentPane().add(mainJpanel);

        OperationJPanel operationJPanel = new OperationJPanel(start, difficultMode);
        getContentPane().add(operationJPanel);

        s.gridwidth = 7;
        s.weightx = 3;
        s.weighty = 1;
        layout.setConstraints(mainJpanel, s);


        s.gridwidth = 3;
        s.weightx = 1;
        s.weighty = 1;
        layout.setConstraints(operationJPanel, s);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {
        initDifficultType();
        Data.initMine(DifficultModeEnum.getByValue(difficultMode.getSelectedIndex()));
        initStartButton();
        initBlocks();
    }

    private void initDifficultType() {
        difficultMode = new JComboBox();
        for (int i = 0; i < DifficultModeEnum.values().length; i++) {
            difficultMode.addItem(DifficultModeEnum.values()[i].getName());
        }
        difficultMode.setSelectedIndex(0);
    }

    private void repaintBlocks() {
        for (int i = 0; i < Data.size; i++) {
            for (int j = 0; j < Data.size; j++) {
                blocks[i][j].repaint();
            }
        }
        if (Data.status.equals(Status.FAILED)) {
            JOptionPane.showMessageDialog(this, "你输了", "😭可惜", JOptionPane.WARNING_MESSAGE);
        }
        if (succeed()) {
            JOptionPane.showMessageDialog(this, "你赢了", "🎉祝贺", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean succeed() {
        for (int i = 0; i < Data.size; i++) {
            for (int j = 0; j < Data.size; j++) {
                // 如果有雷还没被标识出来，那么就不是成功
                if (Data.maps[i][j] == 1 && !Data.flags[i][j]) {
                    return false;
                }
            }
        }
        Data.status = Status.SUCCESS;
        return true;
    }

    private void initBlocks() {
        blocks = new Block[Data.size][Data.size];

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blocks[i][j] = new Block(i, j);
                blocks[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (Data.status == Status.GOING) {
                            int c = e.getButton(); // 得到按下的鼠标键
                            Block block = (Block) e.getComponent();
                            int x = block.getPoint_x();
                            int y = block.getPoint_y();
                            if (c == MouseEvent.BUTTON1) {
                                Data.visit(x, y);
                            } else if (c == MouseEvent.BUTTON3) {// 推断是鼠标右键按下
                                if (!Data.visited[x][y]) {
                                    Data.flags[x][y] = !Data.flags[x][y];
                                }
                            }
                        }
                        repaintBlocks();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }
    }

    public void initStartButton() {
        start = new JButton(" Start ");
        start.setFont(new Font("华文行楷", Font.BOLD, 16));
        start.setMargin(new Insets(5, 15, 5, 15));
        //创造监听器对象
        //把监听器注册给按钮
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Data.status.equals(Status.LOADING)) {
                    Data.status = Status.GOING;
                    start.setText(Status.GOING.getButtonName());
                } else {
                    Data.status = Status.LOADING;
                    start.setText(Status.LOADING.getButtonName());
                    Data.initData(DifficultModeEnum.getByValue(difficultMode.getSelectedIndex()));
                    refreshBlock();
                }
            }
        });
    }

    private void refreshBlock(){
        for(int i=0;i<blocks.length;i++){
            for(int j=0;j<blocks.length;j++){
                blocks[i][j].setBackgroundPath(ImgPath.DEFAULT);
            }
        }
    }
}
