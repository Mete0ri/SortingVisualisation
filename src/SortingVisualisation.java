import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class SortingVisualisation extends JPanel {
    private int[] table;
    boolean isSorting = false;
    private JLabel timeLabel;
    public SortingVisualisation(int size, JLabel timeLabel){
        this.timeLabel = timeLabel;
        createTable(size);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTable(g);
    }
    public void createTable(int size){
        if(!isSorting) {
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
    public void bubbleSort(int delay) {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            int n = table.length;
            int temp;

            if (!isSorting) {
                isSorting = true;
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - 1; j++) {

                        if (table[j] > table[j + 1]) {
                            temp = table[j];
                            table[j] = table[j + 1];
                            table[j + 1] = temp;

                            try {
                                Thread.sleep(delay);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        repaint();
                    }
                }
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                timeLabel.setText("Sorting time: " + time + " ms");
                isSorting = false;
            }
        }).start();
    }
    public void insertionSort(int delay){
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            int n = table.length;
            int j;
            int temp;
            if (!isSorting) {
                isSorting = true;
                for (int i = 1; i < n; i++) {
                    j = i;
                    while (j > 0 && table[j - 1] > table[j]) {
                        temp = table[j];
                        table[j] = table[j - 1];
                        table[j - 1] = temp;
                        j = j - 1;
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    repaint();
                }
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                timeLabel.setText("Sorting time: " + time + " ms");
                isSorting = false;
            }
        }).start();
    }
    public void selectionSort(int delay){
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            int n = table.length;
            int min;
            int temp;
            if(!isSorting){
                isSorting = true;
                for(int i = 0; i < n - 1; i++){
                    min = i;
                    for(int j = i + 1; j < n; j++){
                        if(table[j] < table[min]){
                            min = j;
                        }
                    }
                    if(min != i){
                        temp = table[i];
                        table[i] = table[min];
                        table[min] = temp;
                    }
                    repaint();
                    try{
                        Thread.sleep(delay);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                timeLabel.setText("Sorting time: " + time + " ms");
                isSorting = false;
            }
        }).start();
    }
    public void mergSort(int delay){
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            if(!isSorting){
                isSorting = true;

                mergSortHelper(0, table.length - 1, delay);

                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                timeLabel.setText("Sorting time: " + time + " ms");
                isSorting = false;
            }
        }).start();
    }
    private void mergSortHelper(int left, int right, int delay) {
        int mid;
        if (left < right) {
            mid = (left + right) / 2;
            mergSortHelper(left, mid, delay);
            mergSortHelper(mid + 1, right, delay);
            merg(left, mid, right);
            repaint();
            try{
                Thread.sleep(delay);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private void merg(int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(table, left, leftArray, 0, n1);
        System.arraycopy(table, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                table[k] = leftArray[i];
                i++;
            } else {
                table[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            table[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            table[k] = rightArray[j];
            j++;
            k++;
        }
    }
}

