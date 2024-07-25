import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    MyFrame(){
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton bubbleSortButton = new JButton("Bubble sort");
        JButton quickSortButton = new JButton("Quick sort");
        JButton shuffleButton = new JButton("Shuffle");
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        quickSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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
        panel.add(quickSortButton);
        panel.add(shuffleButton);
        panel.add(slider);
        panel.add(sliderValue);

        SortingVisualisation sortingVisualisation = new SortingVisualisation(slider.getValue());

        add(panel, BorderLayout.SOUTH);
        add(sortingVisualisation, BorderLayout.CENTER);

        setVisible(true);
    }

}
