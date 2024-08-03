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
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton bubbleSortButton = new JButton("Bubble sort");
        JButton insertionSortButton = new JButton("Insertion sort");
        JButton selectionSortButton = new JButton("Selection sort");
        JButton mergeSortButton = new JButton("Merge sort");
        JButton shuffleButton = new JButton("Shuffle");

        JSlider slider = new JSlider();
        JTextField sliderValue = new JTextField("" + slider.getValue());
        sliderValue.setEditable(false);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sliderValue.setText("" + ((JSlider)e.getSource()).getValue());
            }
        });

        panel.add(bubbleSortButton);
        panel.add(insertionSortButton);
        panel.add(selectionSortButton);
        panel.add(mergeSortButton);
        panel.add(shuffleButton);
        panel.add(slider);
        panel.add(sliderValue);

        SortingVisualisation sortingVisualisation = new SortingVisualisation(slider.getValue());

        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                sortingVisualisation.bubbleSort();
            }
        });
        insertionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingVisualisation.insertionSort();
            }
        });
        selectionSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingVisualisation.selectionSort();
            }
        });
        mergeSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingVisualisation.mergSort();
            }
        });
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingVisualisation.createTable(slider.getValue());
            }
        });

        add(panel, BorderLayout.SOUTH);
        add(sortingVisualisation, BorderLayout.CENTER);

        setVisible(true);
    }

}
