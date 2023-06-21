package mapper.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{

    JButton plane1 = new JButton(); //Creates a new plane


    MyFrame(){

        ImageIcon planeIcon = new ImageIcon("img/plane.png"); //Creates an ImageIcon

        this.setTitle("Flights Radar"); //Sets the title of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits the application when the frame is closed
        this.setResizable(false); //Makes it so the frame cannot be resized
        this.setSize(1000,1000); //Sets the frame size to 420x420 pixels
        this.setVisible(true); //Makes the frame visible
        ImageIcon logoIcon = new ImageIcon("img/radar-logo-1.png"); //Creates an ImageIcon
        this.setIconImage(logoIcon.getImage()); //Changes the icon of the frame to the image
        this.getContentPane().setBackground(Color.GRAY); //Sets the background color to black
        this.setLayout(null);

        plane1.setBounds(0,0,120,120); //Sets the size and position of the plane
        plane1.addActionListener(this);
        plane1.setText("Plane-1"); //Sets the text of the button
        plane1.setIcon(planeIcon); //Sets the icon of the label to the image
        plane1.setHorizontalTextPosition(JButton.CENTER);
        plane1.setVerticalTextPosition(JButton.TOP);
        plane1.setFont(new Font("Comic Sans",Font.BOLD,15));
        plane1.setIconTextGap(-15);
        plane1.setOpaque(false);
        plane1.setContentAreaFilled(false);
        plane1.setBorderPainted(false);

        //plane1.setIcon(planeIcon); //Sets the icon of the label to the image

        this.add(plane1); //Adds the label to the frame

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plane1){
            System.out.println("Hello");
        }
    }
}
