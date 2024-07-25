import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SortingVisualisation extends JPanel {
    private int[] array;
    public SortingVisualisation(int size){

    }
    private void draw(Graphics g, int size){
        Graphics2D g2D = (Graphics2D) g;
        array = new int[size];
        Random random = new Random();
        for(int i = 0; i < array.length; i++){
            array[i] = random.nextInt(100) + 1;
        }
        repaint();
        int barWidth = getWidth() / array.length;
        int maxValue = Arrays.stream(array).max().orElse(0);

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) ((double) array[i] / maxValue * getHeight());
            int x = i * barWidth;
            int y = getHeight() - barHeight;

            g2D.setColor(Color.CYAN);
            g2D.fillRect(x, y, barWidth, barHeight);
        }
    }
}
