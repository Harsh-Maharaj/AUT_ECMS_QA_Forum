package Question_2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener {
    CopyOnWriteArrayList<Phone> phones = new CopyOnWriteArrayList<>();
    JFrame frame;
    RepairShop repairShop = new RepairShop();
    Image repairShopImage; // Attribute to hold the repair shop image
    int frameCount = 0; // Frame counter for triggering various events

    // Constants
    private static final int TRANSMISSION_DISTANCE = 20;
    private static final int FRAMES_TO_DEATH = 500; // Frames until an infected phone "dies"
    private static final int TRANSMISSION_INTERVAL = 30; // For example, every 30 frames


    public Panel(JFrame frame) {
        this.frame = frame;
        this.setFocusable(true);
        this.addKeyListener(this);

        // Load the repair shop image
        //Image ii = new ImageIcon(getClass().getResource("Repair_shop.png")).getImage();

        repairShopImage = new ImageIcon("Repair_shop.png").getImage();

        // Set the background color of the Panel to grey
        this.setBackground(Color.GRAY);

        // Timer to repaint at approximately 60 FPS
        Timer repaintTimer = new Timer(16, e -> {
            frameCount++;
            if (frameCount % FRAMES_TO_DEATH == 0) {
                markRandomInfectedPhoneForRepair();
            }
            if (frameCount % TRANSMISSION_INTERVAL == 0) {
                transmitVirus();
            }
            removeDeadPhones();
            repaint();
        });
        repaintTimer.start();
    }

    private void transmitVirus() {
        // For each infected phone, try to infect nearby healthy phones
        for (Phone infectedPhone : phones) {
            if (infectedPhone.state == Phone.INFECTED) {
                for (Phone targetPhone : phones) {
                    // Check if the target phone is within the transmission distance
                    if (targetPhone.state == Phone.HEALTHY && infectedPhone.isNear(targetPhone, TRANSMISSION_DISTANCE)) {
                        targetPhone.infect();
                    }
                }
            }
        }
    }

    
    private void markRandomInfectedPhoneForRepair() {
        // Filter for infected phones only
        long infectedCount = phones.stream().filter(p -> p.state == Phone.INFECTED).count();
        if (infectedCount > 0) {
            Random rand = new Random();
            Phone phoneToRepair = null;
            // Keep trying until we find an infected phone
            while (phoneToRepair == null) {
                int index = rand.nextInt(phones.size());
                Phone candidate = phones.get(index);
                if (candidate.state == Phone.INFECTED) {
                    phoneToRepair = candidate;
                }
            }
            phoneToRepair.markForRepair(); // Mark the infected phone for repair
        }
    }
    
   private void removeDeadPhones() {
        phones.removeIf(phone -> phone.state == Phone.DEAD || phone.framesInfected >= FRAMES_TO_DEATH);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the repair shop image in the top-left corner of the frame
        g.drawImage(repairShopImage, 0, 0, this);

    // Draw the phones
    for (Phone phone : phones) {
        if (phone.state != Phone.DEAD) { // Don't draw dead phones
            g.drawImage(phone.getPhoneImage(), phone.x, phone.y, this);
        }
    }

    // Remove dead phones from the simulation
    phones.removeIf(phone -> phone.state == Phone.DEAD || phone.framesInfected >= FRAMES_TO_DEATH);
        // Draw the status bar information
        long healthyCount = phones.stream().filter(p -> p.state == Phone.HEALTHY).count();
        long infectedCount = phones.stream().filter(p -> p.state == Phone.INFECTED).count();
        long goingToRepairCount = phones.stream().filter(p -> p.state == Phone.GOING_TO_REPAIR).count();

        String healthyText = "Healthy: " + healthyCount;
        String infectedText = "Infected: " + infectedCount;
        String repairText = "Going To Repair Shop: " + goingToRepairCount;

        // Calculate the X position for right alignment
        int padding = 10; // Padding from the right edge
        int healthyTextWidth = g.getFontMetrics().stringWidth(healthyText);
        int infectedTextWidth = g.getFontMetrics().stringWidth(infectedText);
        int repairTextWidth = g.getFontMetrics().stringWidth(repairText);

        int maxTextWidth = Math.max(healthyTextWidth, Math.max(infectedTextWidth, repairTextWidth));
        int textX = this.getWidth() - maxTextWidth - padding; // X position for all strings

        // Set the color and draw each string
        g.setColor(Color.GREEN);
        g.drawString(healthyText, textX, 15);
        g.setColor(Color.RED);
        g.drawString(infectedText, textX, 30);
        g.setColor(Color.YELLOW);
        g.drawString(repairText, textX, 45);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Add a new phone
            Phone newPhone = new Phone(frame.getWidth(), frame.getHeight(), repairShop);
            phones.add(newPhone);
            new Thread(newPhone).start();
        } else if (e.getKeyCode() == KeyEvent.VK_V) {
            // Infect a random healthy phone
            phones.stream()
                .filter(p -> p.state == Phone.HEALTHY)
                .findAny()
                .ifPresent(Phone::infect);
        }
        repaint();
    }
    


    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
