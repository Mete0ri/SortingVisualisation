import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SortingVisualisation extends JPanel {
    private int[] table;
    boolean isSorting = false;
    public SortingVisualisation(int size){
        createTable(size);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTable(g);
    }
    public void createTable(int size){
        if(isSorting == false) {
            table = new int[size];
            Random random = new Random();
            for (int i = 0; i < table.length; i++) {
                table[i] = random.nextInt(100) + 1;
            }
            repaint();
        }
    }
    private void drawTable(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        if (table.length == 0) {
            return;
        }

        int space = 2;
        int barWidth = (getWidth() - (space * (table.length - 1))) / table.length;
        int maxValue = Arrays.stream(table).max().orElse(0);

        for (int i = 0; i < table.length; i++) {
            int barHeight = getHeight() * table[i] / maxValue;
            int x = i * (barWidth + space);
            int y = getHeight() - barHeight;

            Color color = colorForValue(table[i], maxValue, i, table.length);
            g2D.setColor(color);
            g2D.fillRect(x, y, barWidth, barHeight);
        }
    }
    private Color colorForValue(int value, int maxValue, int index, int totalBars) {
        float ratio = (float) value / maxValue;
        float baseHue = ratio * 0.4f;
        float hueVariation = (float) index / totalBars * 0.1f;
        float hue = (baseHue + hueVariation) % 1.0f;
        return Color.getHSBColor(hue, 0.7f, 0.9f);
    }
    public void bubbleSort() {
        new Thread(() -> {
            int n = table.length;
            int temp;

            if (isSorting == false) {
                isSorting = true;
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - 1; j++) {

                        if (table[j] > table[j + 1]) {
                            temp = table[j];
                            table[j] = table[j + 1];
                            table[j + 1] = temp;

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        repaint();
                    }
                }
                isSorting = false;
            }
        }).start();
    }
    public void insertionSort(){
        new Thread(() -> {
            int n = table.length;
            int j;
            int temp;
            if(isSorting == false){
                isSorting = true;
                for(int i = 0; i < n - 1; i++){
                    j = i;
                    while(j > 0 || table[j - 1] > table[j]){
                        temp = table[j];
                        table[j] = table[j - 1];
                        j = j - 1;
                    }
                    repaint();
                }
                isSorting = false;
            }
        }).start();
    }
}

