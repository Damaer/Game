import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationJPanel extends JPanel {
    JButton begin;
    JComboBox difficultMode;

    public OperationJPanel(JButton begin,JComboBox difficultMode) {
        this.begin = begin;
        this.difficultMode = difficultMode;

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0};  //设置了总共有一列
        gridBagLayout.rowHeights = new int[]{0, 0, 0};  //设置了总共有3行
        gridBagLayout.columnWeights = new double[]{1.0};  //设置了列的宽度为容器宽度
        gridBagLayout.rowWeights = new double[]{0.4, 0.3, 0.3};  //第一行的高度占了容器的2份，第二行的高度占了容器的8份
        setLayout(gridBagLayout);

        JPanel panel_1 = createMiddle();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.insets = new Insets(0, 0, 0, 0);
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 0;
        add(panel_1, gbc_panel_1);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 0, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 1;
        add(panel, gbc_panel);

        JPanel panel2 = new JPanel();
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.insets = new Insets(0, 0, 0, 0);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 0;
        gbc_panel2.gridy = 2;
        add(panel2, gbc_panel2);

        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);
    }

    public JPanel createMiddle() {
        JPanel jPanel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0};  //设置了总共有1列
        gridBagLayout.rowHeights = new int[]{0, 0};  //设置了总共有2行
        gridBagLayout.columnWeights = new double[]{1};  //设置了列的宽度为容器宽度
        gridBagLayout.rowWeights = new double[]{0.2, 0.8};  //第一行的高度占了容器的2份，第二行的高度占了容器的8份
        jPanel.setLayout(gridBagLayout);

        JPanel but = new JPanel();
        but.add(begin);
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.CENTER;
        gbc_panel_1.insets = new Insets(0, 0, 0, 0);
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;
        jPanel.add(but, gbc_panel_1);

        JPanel type = new JPanel();
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.insets = new Insets(0, 0, 0, 0);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 0;
        gbc_panel2.gridy = 2;
        type.add(difficultMode);
        jPanel.add(type, gbc_panel2);

        setBorder(BorderFactory.createEtchedBorder());
        setVisible(true);

        return jPanel;
    }
}
