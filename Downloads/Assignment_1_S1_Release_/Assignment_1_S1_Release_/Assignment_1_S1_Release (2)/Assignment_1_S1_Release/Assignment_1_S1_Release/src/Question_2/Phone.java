package Question_2;

import java.awt.Image;
import javax.swing.ImageIcon;
// PLEASE NOTE THAT WHEN A PHONE TURNS YELLOW, OR WHEN OTHER PHONES TURNS YELLOW,
//ALL PHONES GO INSIDE THE REPAIR SHOP/ TRAVEL THERE, BUT ONLY ONE PHONE IS REPAIRED AT A TIME, FOR EXAMPLE
//THE PHONES ARE IN A QUEUE IT REPAIRS WHICH PHONE COME FIRST AND THE NEXT ONE SO ON AND SO FORTH.
// Question: "Which object(s) have you chosen for the synchronize?"
// Answer: The object chosen for synchronization is `repairLock`. This ensures that repair operations
// are performed one at a time, preventing race conditions and ensuring exclusive access to the repair shop.
public class Phone implements Runnable {
    int x, y; // Position of the phone
    volatile int state; // The current state of the phone, marked volatile for thread visibility
    int vx = 2, vy = 2; // Velocity for movement
    static final int HEALTHY = 0, INFECTED = 1, REPAIR = 2, GOING_TO_REPAIR = 3, DEAD = 4;
    static final int REPAIR_X = 0; // Repair area X coordinate
    static final int REPAIR_Y = 0; // Repair area Y coordinate
    static final int REPAIR_WIDTH = 20; // Repair area width
    static final int REPAIR_HEIGHT = 20; // Repair area height
    static final int FRAMES_TO_DEATH = 500; // Frames until an infected phone dies
    int width, height; // Screen dimensions for movement boundaries
    int framesInfected = 0; // Frames since being infected
    static RepairShop repairShop; // Shared repair shop object, access synchronized using `repairLock`
    static Object repairLock = new Object(); // Lock for synchronizing repair operations
    boolean canMove = true; // Flag to control movement
    Image phoneImage; // Current image of the phone

    public Phone(int width, int height, RepairShop repairShop) {
        this.width = width;
        this.height = height;
        x = (int) (Math.random() * width);
        y = (int) (Math.random() * height);
        Phone.repairShop = repairShop;
        state = HEALTHY;
        updatePhoneImage();
    }

    private void updatePhoneImage() {
        String imagePath;
        switch (state) {
            case HEALTHY:
                imagePath = "Healthy_Phone.png";
                break;
            case INFECTED:
                imagePath = "Infected_phone.png";
                break;
            case REPAIR:
            case GOING_TO_REPAIR:
                imagePath = "Repair_phone.png";
                break;
            default:
                imagePath = null; // Consider setting a default image path
        }

        if (imagePath != null) {
            phoneImage = new ImageIcon(imagePath).getImage();
        }
    }

 public void infect() {
        if (state == HEALTHY) {
            state = INFECTED;
            framesInfected = 0; // Reset the counter
            updatePhoneImage();
        }
    }

    public void markForRepair() {
        if (state == INFECTED) {
            state = GOING_TO_REPAIR;
            updatePhoneImage();
        }
    }

    public void repair() {
        state = HEALTHY;
        framesInfected = 0;
        updatePhoneImage();
    }

private void die() {
        state = DEAD; // Update the state to DEAD
        // Perform any cleanup needed before removing the phone
    }


@Override
public void run() {
    while (state != DEAD) {
        if (state == INFECTED) {
            framesInfected++;
            if (framesInfected >= FRAMES_TO_DEATH) {
                die(); // The phone "dies" from the infection
                return; // Exit the thread
            }
        }
        
        if (canMove) {
            move();
        }

        if (state == GOING_TO_REPAIR && isInsideRepairShop()) {
            synchronized (repairLock) {
                if (repairShop.isAvailable()) {
                    canMove = false; // Stop the phone from moving
                    repairShop.setUnavailable(); // The repair shop starts the repair
                    try {
                        Thread.sleep(10000); // Simulates repair time (10 seconds)
                        this.repair(); // Repairs the phone
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        canMove = true; // Phone can move again
                        repairShop.setAvailable(); // Repair shop becomes available
                    }
                }
            }
        }

            try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return; // Exit the thread if interrupted
        }
    }
}

    private void move() {
        if (state == GOING_TO_REPAIR && !isInsideRepairShop()) {
            moveToRepairShop();
        } else if (canMove) {
            bounce();
        }
    }

    private void moveToRepairShop() {
        int targetX = REPAIR_X + REPAIR_WIDTH / 2;
        int targetY = REPAIR_Y + REPAIR_HEIGHT / 2;
        int deltaX = targetX - x;
        int deltaY = targetY - y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        vx = (int) (2 * (deltaX / distance));
        vy = (int) (2 * (deltaY / distance));
        x += vx;
        y += vy;
    }

    private void bounce() {
        if (x <= 0 || x >= width - 10) vx = -vx;
        if (y <= 0 || y >= height - 10) vy = -vy;
        x += vx;
        y += vy;
    }

    private boolean isInsideRepairShop() {
        return x >= REPAIR_X && x <= REPAIR_X + REPAIR_WIDTH && y >= REPAIR_Y && y <= REPAIR_Y + REPAIR_HEIGHT;
    }

    public Image getPhoneImage() {
        return phoneImage;
    }
    
    // Continuation from the isInsideRepairShop method...
    public boolean isNear(Phone otherPhone, int distance) {
        int dx = this.x - otherPhone.x;
        int dy = this.y - otherPhone.y;
        return Math.sqrt(dx * dx + dy * dy) <= distance;
    }
}
