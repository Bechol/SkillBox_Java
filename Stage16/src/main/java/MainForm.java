import com.google.common.base.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel MainPanel;
    private JButton btnUninon;
    private JTextField txtFamily;
    private JTextField txtName;
    private JTextField txtPatronymic;
    private JPanel cards;
    private JPanel inputPanel;
    private JPanel resultPanel;
    private JButton btnSplit;
    private JTextField txtFio;
    private CardLayout cardLayout;

    public MainForm() {
        cardLayout = (CardLayout) cards.getLayout();
        btnUninon.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String fio = createFio();
                if (!Strings.isNullOrEmpty(fio)) {
                    txtFio.setText(createFio());
                    cardLayout.next(resultPanel.getParent());
                }
            }

            @Override
            public void setEnabled(boolean b) {

            }
        });

        btnSplit.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] fioArray = splitFio();
                if (fioArray != null || fioArray.length == 3) {
                    txtFamily.setText(fioArray[0]);
                    txtName.setText(fioArray[1]);
                    txtPatronymic.setText(fioArray[2]);
                    cardLayout.next(inputPanel.getParent());
                }
            }

            @Override
            public void setEnabled(boolean b) {

            }


        });
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    private String createFio() {
        if (Strings.isNullOrEmpty(txtFamily.getText().trim())) {
            showMessage("Family is empty!");
            return null;
        }
        if (Strings.isNullOrEmpty(txtName.getText().trim())) {
            showMessage("Name is empty!");
            return null;
        }
        if (Strings.isNullOrEmpty(txtPatronymic.getText().trim())) {
            showMessage("Patronymic is empty!");
            return null;
        }
        StringBuilder sb = new StringBuilder(txtFamily.getText());
        sb.append(" ");
        sb.append(txtName.getText());
        sb.append(" ");
        sb.append(txtPatronymic.getText());
        return sb.toString();
    }

    private String[] splitFio() {
        String fio = txtFio.getText();
        if (Strings.isNullOrEmpty(fio.trim())) {
            showMessage("FIO is empty");
            return null;
        }
        return fio.split(" ");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Alert",
                JOptionPane.WARNING_MESSAGE);
    }


}
