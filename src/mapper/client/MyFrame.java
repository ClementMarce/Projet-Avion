package mapper.client;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyFrame extends JFrame{
    public JButton[] planes = new JButton[5];
    private int nbPlanes = 0;
    MyFrame(){
        this.setTitle("Flights Radar"); //Sets the title of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits the application when the frame is closed
        this.setResizable(false); //Makes it so the frame cannot be resized
        this.setSize(1000,1000); //Sets the frame size
        this.setVisible(true); //Makes the frame visible
        ImageIcon logoIcon = new ImageIcon("img/radar-logo-1.png"); //Creates an ImageIcon
        this.setIconImage(logoIcon.getImage()); //Changes the icon of the frame to the image
        this.getContentPane().setBackground(new Color(0,0,0)); //Sets the background color to black
        this.setLayout(null);
    }

    public void CreateNewPlane(String planeName){
        JButton plane = new JButton();
        planes[nbPlanes] = plane;
        plane.setName(planeName);
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem option1 = new JMenuItem("Speed +");
        JMenuItem option2 = new JMenuItem("Speed -");
        JMenuItem option3 = new JMenuItem("Flight Level +");
        JMenuItem option4 = new JMenuItem("Flight Level -");
        JMenuItem option5 = new JMenuItem("Turn Right");
        JMenuItem option6 = new JMenuItem("Turn Left");

        popupMenu.add(option1);
        option1.addActionListener(e -> {
            try {
                Client.ChangeValues(planeName, "Speed", 100);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        popupMenu.add(option2);
        option2.addActionListener(e -> {
            try {
                Client.ChangeValues(planeName, "Speed", -100);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        popupMenu.add(option3);
        option3.addActionListener(e -> {
            try {
                Client.ChangeValues(planeName, "FlightLevel", 100);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        popupMenu.add(option4);
        option4.addActionListener(e -> {
            try {
                Client.ChangeValues(planeName, "FlightLevel", -100);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        popupMenu.add(option5);
        option5.addActionListener(e -> {
            try {
                Client.ChangeValues(planeName, "ChangeCap", -90);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        popupMenu.add(option6);
        option6.addActionListener(e -> {
            try {
                Client.ChangeValues(planeName, "ChangeCap", 90);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        plane.addActionListener(e -> popupMenu.show(plane, 0, plane.getHeight()));
        DrawNewPlane(plane);
        nbPlanes++;
    }

    private void DrawNewPlane(JButton plane)
    {
        ImageIcon planeIcon = new ImageIcon("img/plane.png"); //Creates an ImageIcon
        plane.setIcon(planeIcon); //Sets the icon of the label to the image
        plane.setHorizontalTextPosition(JButton.CENTER);
        plane.setVerticalTextPosition(JButton.TOP);
        plane.setForeground(Color.WHITE);
        plane.setFont(new Font("Comic Sans",Font.BOLD,10));
        plane.setIconTextGap(-15);
        plane.setContentAreaFilled(false);
        plane.setBorderPainted(false);
        plane.setOpaque(false);
        Container parent = plane.getParent();
        if (parent instanceof JComponent) {
            ((JComponent) parent).setOpaque(false);
        }
        this.add(plane); //Adds the label to the frame
    }

    public void updatePlane(String planeName, int x, int y, String info){
        for (int i = 0; i < nbPlanes; i++){
            String iterationPlaneName=planes[i].getName();
            if (iterationPlaneName.equals(planeName)){
                _updatePlane(planes[i], x, y, info);
            }
        }
    }

    private void _updatePlane(JButton plane, int x, int y, String info){
        plane.setBounds(x,y,120,120);
        plane.setText(info); //Sets the text of the button
    }

    void drawRadar(){
        this.drawCircleByCenter(this.getGraphics(), 500, 500, 450,10);
        this.drawCircleByCenter(this.getGraphics(), 500, 500, 350,5);
        this.drawCircleByCenter(this.getGraphics(), 500, 500, 250,4);
        this.drawCircleByCenter(this.getGraphics(), 500, 500, 150,3);
        this.drawLine(this.getGraphics(), 500, 50, 500, 950, 10, 3);
        this.drawLine(this.getGraphics(), 50, 500, 950, 500, 10, 3);
        this.drawLine(this.getGraphics(), 500, 450, 500, 550, 10, 7);
        this.drawLine(this.getGraphics(), 450, 500, 550, 500, 10, 7);
        this.drawText(this.getGraphics(), 800, 100, "AeroVista Airport");
        this.drawText(this.getGraphics(), 50, 100, "Range : 100-Km ");
    }
    private void drawText(Graphics gra, int x, int y, String text) {
        gra.setColor(new Color(0,199,23));
        gra.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        gra.drawString(text, x, y);
    }
    private void drawLine(Graphics gr, int i, int i1, int i2, int i3, int i4, int strokeWidth) {
        Graphics2D g2d = (Graphics2D) gr;
        gr.setColor(new Color(0,199,23));
        Stroke oldStroke = g2d.getStroke(); // Sauvegarde de l'ancien tracé
        g2d.setStroke(new BasicStroke(strokeWidth)); // Définition du nouveau tracé
        gr.drawLine(i,i1,i2,i3);
        g2d.setStroke(oldStroke); // Restauration de l'ancien tracé
    }
    void drawCircleByCenter(Graphics g, int x, int y, int r, int strokeWidth) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(0,199,23));
        Stroke oldStroke = g2d.getStroke(); // Sauvegarde de l'ancien tracé
        g2d.setStroke(new BasicStroke(strokeWidth)); // Définition du nouveau tracé
        g.drawOval(x-r, y-r, 2*r, 2*r);
        g2d.setStroke(oldStroke); // Restauration de l'ancien tracé
    }
}
