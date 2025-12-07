package ekspres_do_kawy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ekspres implements ActionListener {
    JFrame frame = new JFrame();
    private JPanel mainPanel;
    private JPanel panel1;
    private JButton wlaczWylaczButton;
    private JButton czarnaButton;
    private JButton zMlekiemButton;
    private JButton espressoButton;
    private JButton latteButton;
    private JRadioButton duzaRadioButton;
    private JRadioButton sredniaRadioButton;
    private JRadioButton malaRadioButton;
    private JProgressBar progressBar;
    private JButton stopButton;
    private JButton startButton;
    private JButton zmienKaweButton;
    private JButton cappucinoButton;
    private JLabel robienieKawyTekst;
    private JButton kolejnaKawaButton;
    boolean czyWlaczone = false;
    String[] komunikaty = {
            "Płukanie ekspresu...",
            "Opróżnij pojemnik z fusami.",
            "Napełnij pojemnik z wodą.",
            "Dosyp kawy do ekspresu.",
            "jakas zmiana tego typu"
    };
    String wybranaKawa = "";
    String rozmiarKawy = "";
    Timer timer;
    int wartoscProgresu = 0;
    int plukanie = 7;
    int fusy = 6;
    int woda = 5;
    int dosypanie = 4;

    public static void main(String[] args) {
        new Ekspres();
    }

    Ekspres(){
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        wlaczWylaczButton.addActionListener(this);
        czarnaButton.addActionListener(this);
        zMlekiemButton.addActionListener(this);
        espressoButton.addActionListener(this);
        latteButton.addActionListener(this);
        cappucinoButton.addActionListener(this);
        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        zmienKaweButton.addActionListener(this);
        kolejnaKawaButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wlaczWylaczButton){
            if(!czyWlaczone){
                czyWlaczone = true;
                czarnaButton.setEnabled(true);
                zMlekiemButton.setEnabled(true);
                espressoButton.setEnabled(true);
                latteButton.setEnabled(true);
                cappucinoButton.setEnabled(true);
            } else {
                czyWlaczone = false;
                wylaczWszystkieButtony();
            }
        } else if (e.getSource() == czarnaButton) {
            wyborKawy();
            wybranaKawa = "czarna kawa";
        } else if (e.getSource() == zMlekiemButton) {
            wyborKawy();
            wybranaKawa = "kawa z mlekiem";
        } else if (e.getSource() == espressoButton) {
            wyborKawy();
            wybranaKawa = "espresso";
        } else if (e.getSource() == latteButton) {
            wyborKawy();
            wybranaKawa = "latte";
        } else if (e.getSource() == cappucinoButton) {
            wyborKawy();
            wybranaKawa = "cappucino";
        } else if (e.getSource() == zmienKaweButton) {
            wybranaKawa = "";
            wylaczWszystkieButtony();
            czarnaButton.setEnabled(true);
            zMlekiemButton.setEnabled(true);
            espressoButton.setEnabled(true);
            latteButton.setEnabled(true);
            cappucinoButton.setEnabled(true);
        } else if (e.getSource() == startButton) {
            wylaczWszystkieButtony();
            wysylanieKomunikatow();
            stopButton.setEnabled(true);
            if(duzaRadioButton.isSelected()){
                rozmiarKawy = "duża";
            } else if (sredniaRadioButton.isSelected()) {
                rozmiarKawy = "średnia";
            } else if (malaRadioButton.isSelected()) {
                rozmiarKawy = "mała";
            }
            progress();
        } else if (e.getSource() == stopButton) {
            wylaczWszystkieButtony();
            duzaRadioButton.setEnabled(true);
            sredniaRadioButton.setEnabled(true);
            malaRadioButton.setEnabled(true);
            startButton.setEnabled(true);
            zmienKaweButton.setEnabled(true);
            timer.stop();
            JOptionPane.showMessageDialog(null, "Przerwałeś robienie kawy. ", "STOP", JOptionPane.ERROR_MESSAGE);
            robienieKawyTekst.setText("");
        } else if (e.getSource() == kolejnaKawaButton) {
            wylaczWszystkieButtony();
            czarnaButton.setEnabled(true);
            zMlekiemButton.setEnabled(true);
            espressoButton.setEnabled(true);
            latteButton.setEnabled(true);
            cappucinoButton.setEnabled(true);
            robienieKawyTekst.setText("");
        }
    }

    private void wylaczWszystkieButtony(){
        czarnaButton.setEnabled(false);
        zMlekiemButton.setEnabled(false);
        espressoButton.setEnabled(false);
        latteButton.setEnabled(false);
        cappucinoButton.setEnabled(false);
        duzaRadioButton.setEnabled(false);
        sredniaRadioButton.setEnabled(false);
        malaRadioButton.setEnabled(false);
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        zmienKaweButton.setEnabled(false);
        kolejnaKawaButton.setEnabled(false);
        progressBar.setValue(0);
        wartoscProgresu = 0;
    }

    private void wyborKawy(){
        wylaczWszystkieButtony();
        duzaRadioButton.setEnabled(true);
        sredniaRadioButton.setEnabled(true);
        malaRadioButton.setEnabled(true);
        startButton.setEnabled(true);
        zmienKaweButton.setEnabled(true);
    }

    private void wysylanieKomunikatow(){
        if(dosypanie == 0){
            JOptionPane.showMessageDialog(null, komunikaty[3], "Komunikat", JOptionPane.ERROR_MESSAGE);
            dosypanie = 4;
        } else {
            dosypanie--;
        }
        if(woda == 0){
            JOptionPane.showMessageDialog(null, komunikaty[2], "Komunikat", JOptionPane.ERROR_MESSAGE);
            woda = 5;
        } else {
            woda--;
        }
        if(fusy == 0){
            JOptionPane.showMessageDialog(null, komunikaty[1], "Komunikat", JOptionPane.ERROR_MESSAGE);
            fusy = 6;
        } else {
            fusy--;
        }
        if(plukanie == 0){
            JOptionPane.showMessageDialog(null, komunikaty[0], "Komunikat", JOptionPane.ERROR_MESSAGE);
            plukanie = 4;
        } else {
            plukanie--;
        }
    }

    private void progress(){

        progressBar.setValue(0);

        if(timer != null && timer.isRunning())
            timer.stop();

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wartoscProgresu <= 100){
                    progressBar.setValue(wartoscProgresu);
                    wartoscProgresu += 5;
                    if(wartoscProgresu <=  50)
                        robienieKawyTekst.setText("Przygotowywanie...");
                    else
                        robienieKawyTekst.setText("Nalewanie do kubka...");
                } else {
                    timer.stop();
                    robienieKawyTekst.setText("Gotowe!");
                    String kawa = "Twoja " + rozmiarKawy + " " + wybranaKawa + " jest gotowa, smacznej kawusi!";
                    progressBar.setValue(100);
                    JOptionPane.showMessageDialog(null, kawa, "Gotowe!", JOptionPane.INFORMATION_MESSAGE);
                    wartoscProgresu = 0;
                    kolejnaKawaButton.setEnabled(true);
                    stopButton.setEnabled(false);
                }
            }
        });
        timer.start();
    }
}
