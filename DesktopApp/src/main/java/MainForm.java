import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JButton collapse;
    private JTextField surname;
    private JTextField name;
    private JTextField patronymic;
    private JPanel mainPanel;
    private JLabel lSurname;
    private JLabel lPatronymic;
    private JLabel lName;
    private JTextArea expand;

    public MainForm(){
        collapse.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

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
                if (collapse.getLabel().equals("Collapse")){
                    collapseBranch();
                }else{
                    expendBranch();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void expendBranch(){
        if (expand.getText().split(" ").length >= 2) {
            lSurname.setVisible(true);
            lName.setVisible(true);
            lPatronymic.setVisible(true);
            surname.setVisible(true);
            name.setVisible(true);
            patronymic.setVisible(true);

            collapse.setLabel("Collapse");

            expand.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Please enter your name and surname",
                    "Error",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
    }

    private void collapseBranch(){
        if (name.getText().length() > 0 && surname.getText().length() > 0){
            collapse.setLabel("Expend");

            lSurname.setVisible(false);
            lName.setVisible(false);
            lPatronymic.setVisible(false);
            surname.setVisible(false);
            name.setVisible(false);
            patronymic.setVisible(false);

            expand.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Please enter your name and surname",
                    "Error",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
    }
}
