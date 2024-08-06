import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{
    MyFrame(){
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton bubbleSortButton = new JButton("Bubble sort");
        JButton insertionSortButton = new JButton("Insertion sort");
        JButton selectionSortButton = new JButton("Selection sort");
        JButton mergeSortButton = new JButton("Merge sort");
        JButton shuffleButton = new JButton("Shuffle");

        JSlider tableSize = new JSlider();
        JTextField tableSizeValue = new JTextField("" + tableSize.getValue());
        tableSizeValue.setEditable(false);

        JLabel tableSizeName = new JLabel("Size:");
        tableSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tableSizeValue.setText("" + ((JSlider)e.getSource()).getValue());
            }
        });

        JSlider delay = new JSlider();
        JTextField delayValue = new JTextField("" + delay.getValue());
        delayValue.setEditable(false);

        JLabel delayName = new JLabel("Delay:");

        delay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                delayValue.setText("" + ((JSlider)e.getSource()).getValue());
            }
        });

        panel.add(delayName);
        panel.add(delay);
        panel.add(delayValue);
        panel.add(bubbleSortButton);
        panel.add(insertionSortButton);
        panel.add(selectionSortButton);
        panel.add(mergeSortButton);
        panel.add(shuffleButton);
        panel.add(tableSizeName);
        panel.add(tableSize);
        panel.add(tableSizeValue);

        JLabel timeLabel = new JLabel("Sorting time: ");
        SortingVisualisation sortingVisualisation = new SortingVisualisation(tableSize.getValue(), timeLabel);
        panel.add(timeLabel);

        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                sortingVisualisation.bubbleSort(delay.getValue());
            }
        });
        insertionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingVisualisation.insertionSort(delay.getValue());
            }
        });
        selectionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingVisualisation.selectionSort(delay.getValue());
            }
        });
        mergeSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingVisualisation.mergSort(delay.getValue());

            }
        });
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingVisualisation.createTable(tableSize.getValue());
            }
        });

        add(panel, BorderLayout.SOUTH);
        add(sortingVisualisation, BorderLayout.CENTER);

        setVisible(true);
    }

}
