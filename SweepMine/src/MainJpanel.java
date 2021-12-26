import javax.swing.*;
import java.awt.*;

public class MainJpanel extends JPanel {
    public MainJpanel(Block[][] blocks) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0};  //设置了总共有一列
        gridBagLayout.rowHeights = new int[]{0, 0, 0};  //设置了总共有3行
        gridBagLayout.columnWeights = new double[]{1.0};  //设置了列的宽度为容器宽度
        gridBagLayout.rowWeights = new double[]{0.1, 0.6, 0.1};  //第一行的高度占了容器的2份，第二行的高度占了容器的8份
        setLayout(gridBagLayout);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 0, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        add(panel, gbc_panel);


        JPanel panel_1 = createMiddle(blocks);
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel.insets = new Insets(0, 0, 0, 0);
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;
        add(panel_1, gbc_panel_1);

        JPanel panel2 = new JPanel();
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.insets = new Insets(0, 0, 0, 0);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 0;
        gbc_panel2.gridy = 2;
        add(panel2, gbc_panel2);

        setVisible(true);
    }

    public JPanel createMiddle(Block[][] blocks) {
        JPanel jPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};  //设置了总共有3列
        gridBagLayout.rowHeights = new int[]{0};  //设置了总共有1行
        gridBagLayout.columnWeights = new double[]{0.1, 0.8, 0.1};  //设置了列的宽度为容器宽度
        gridBagLayout.rowWeights = new double[]{1};  //第一行的高度占了容器的2份，第二行的高度占了容器的8份
        jPanel.setLayout(gridBagLayout);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 0, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        jPanel.add(panel, gbc_panel);


        MineJpanel panel_1 = new MineJpanel(blocks);
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel.insets = new Insets(0, 0, 0, 0);
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 0;
        jPanel.add(panel_1, gbc_panel_1);

        JPanel panel2 = new JPanel();
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.insets = new Insets(0, 0, 0, 0);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 2;
        gbc_panel2.gridy = 0;
        jPanel.add(panel2, gbc_panel2);

        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);

        return jPanel;
    }
}
