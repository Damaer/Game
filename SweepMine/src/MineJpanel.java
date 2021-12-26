import javax.swing.*;
import java.awt.*;

public class MineJpanel extends JPanel {

    public MineJpanel(Block[][] blocks) {
        setLayout(new GridLayout(blocks.length, blocks.length, 0, 0));
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                add(blocks[i][j]);
            }
        }
    }
}
