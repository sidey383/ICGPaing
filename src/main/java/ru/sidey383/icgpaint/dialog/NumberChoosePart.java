package ru.sidey383.icgpaint.dialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

public abstract class NumberChoosePart extends JPanel {

    private final int min;

    private final int max;

    private final JTextField textField;

    private final JSlider slider;

    public NumberChoosePart(String name, int min, int max, int start) {
        super();
        this.min = min;
        this.max = max;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(name);
        add(label);
        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amountFormat.setParseIntegerOnly(true);
        textField = new JFormattedTextField(amountFormat);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}
            @Override
            public void focusLost(FocusEvent e) {
                textFieldUpdate();
            }
        });
        textField.setMinimumSize(new Dimension(30, 20));
        textField.setSize( 30, 20);
        textField.setPreferredSize(new Dimension(40, 20));
        add(textField);
        textField.setText(Integer.toString(start));
        textField.addActionListener(this::updateField);
        slider = new JSlider(min, max, start);
        add(slider);
        slider.addChangeListener(this::updateSlider);
    }

    private void updateField(ActionEvent e) {
        textFieldUpdate();
    }

    private void textFieldUpdate() {
        try {
            int val = Integer.parseInt(textField.getText());
            if (val < min) {
                textField.setText(Integer.toString(min));
                wrongDataDialog();
            }
            if (val > max) {
                textField.setText(Integer.toString(max));
                wrongDataDialog();
            }
            slider.setValue(val);
        } catch (NumberFormatException ex) {
            textField.setText("1");
            wrongDataDialog();
            slider.setValue(1);
        }
    }

    private void updateSlider(ChangeEvent e) {
        int val = slider.getValue();
        textField.setText(Integer.toString(val));
        setValue(val);
    }

    public void wrongDataDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Wrong data");
        dialog.setPreferredSize(new Dimension(400, 100));
        dialog.setMinimumSize(new Dimension(400, 100));
        dialog.add(new JLabel("Only integer values from " + min + " to " + max + " are available"), BorderLayout.CENTER);
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }


    protected abstract void setValue(int val);

}
