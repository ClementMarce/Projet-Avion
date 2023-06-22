package mapper.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{
    public JButton[] planes = new JButton[5];
    private int nbPlanes = 0;

    MyFrame(){
        this.setTitle("Flights Radar"); //Sets the title of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits the application when the frame is closed
        this.setResizable(false); //Makes it so the frame cannot be resized
        this.setSize(1000,1000); //Sets the frame size to 420x420 pixels
        this.setVisible(true); //Makes the frame visible
        ImageIcon logoIcon = new ImageIcon("img/radar-logo-1.png"); //Creates an ImageIcon
        this.setIconImage(logoIcon.getImage()); //Changes the icon of the frame to the image
        this.getContentPane().setBackground(Color.GRAY); //Sets the background color to black
        this.setLayout(null);
    }

    public void CreateNewPlane(String planeName){
        JButton plane = new JButton();
        planes[nbPlanes] = plane;
        plane.setName(planeName);
        plane.setText(planeName);
        DrawNewPlane(plane);
        nbPlanes++;
    }

    private void DrawNewPlane(JButton plane)
    {
        ImageIcon planeIcon = new ImageIcon("img/plane.png"); //Creates an ImageIcon

        //plane.setBounds(0,0,120,120); //Sets the size and position of the plane
        plane.addActionListener(this);
        //plane.setText("Plane-1"); //Sets the text of the button
        plane.setIcon(planeIcon); //Sets the icon of the label to the image
        plane.setHorizontalTextPosition(JButton.CENTER);
        plane.setVerticalTextPosition(JButton.TOP);
        plane.setFont(new Font("Comic Sans",Font.BOLD,15));
        plane.setIconTextGap(-15);
        plane.setOpaque(false);
        plane.setContentAreaFilled(false);
        plane.setBorderPainted(false);

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


    @Override
    public void actionPerformed(ActionEvent e) {
    //    if (e.getSource() == plane1){
      //      System.out.println("Hello");
     //   }
    }
}
