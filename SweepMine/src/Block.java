import constant.ImgPath;
import constant.Status;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Block extends JPanel {
    private int point_x;
    private int point_y;

    private String backgroundPath = ImgPath.DEFAULT;

    public Block(int x, int y) {
        this.point_x = x;
        this.point_y = y;
        setBorder(BorderFactory.createEtchedBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        refreshBackground();
        URL url = getClass().getClassLoader().getResource(backgroundPath);
        ImageIcon icon = new ImageIcon(url);
        if (backgroundPath.equals(ImgPath.DEFAULT) || backgroundPath.equals(ImgPath.FLAG)
                || backgroundPath.equals(String.format(ImgPath.NUM, 0))) {
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else {
            int x = (int) (getWidth() * 0.1);
            int y = (int) (getHeight() * 0.15);
            g.drawImage(icon.getImage(), x, y, getWidth() - 2 * x, getHeight() - 2 * y, this);
        }
    }

    public void refreshBackground() {
        if (Data.status == Status.FAILED && Data.maps[point_x][point_y] == 1) {
            // 如果已经失败，则暴露所有雷
            if (point_x == Data.lastVisitedPoint.x && point_y == Data.lastVisitedPoint.y) {
                backgroundPath = ImgPath.BOMB;
            } else {
                backgroundPath = ImgPath.MINE;
            }
        } else {
            if (Data.visited[point_x][point_y]) {
                if (Data.maps[point_x][point_y] == 1) {
                    backgroundPath = ImgPath.MINE;
                } else {
                    // 已经被点击过
                    backgroundPath = String.format(ImgPath.NUM, Data.nums[point_x][point_y]);
                }
            } else {
                // 没有被访问过
                if (Data.flags[point_x][point_y]) {
                    backgroundPath = ImgPath.FLAG;
                } else {
                    backgroundPath = ImgPath.DEFAULT;
                }
            }
        }
    }

    public int getPoint_x() {
        return point_x;
    }

    public void setPoint_x(int point_x) {
        this.point_x = point_x;
    }

    public int getPoint_y() {
        return point_y;
    }

    public void setPoint_y(int point_y) {
        this.point_y = point_y;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }
}
