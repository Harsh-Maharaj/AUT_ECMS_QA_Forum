package Question_2;

public class RepairShop {
    private boolean available = true;

    public synchronized boolean isAvailable() {
        return available;
    }

    public synchronized void setUnavailable() {
        available = false;
    }

    public synchronized void setAvailable() {
        available = true;
    }

    public synchronized void repairPhone(Phone phone) {
        // Ensure only yellow phones (GOING_TO_REPAIR) can be repaired
        if (phone.state == Phone.GOING_TO_REPAIR) {
            phone.canMove = false; // Pause movement
            System.out.println("Repairing phone...");
            try {
                // Simulate the repair time. This blocks only this thread.
                Thread.sleep(10000);
                phone.repair(); // Repair the phone
                System.out.println("Phone repaired successfully.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                phone.canMove = true; // Resume movement
                setAvailable(); // Make the repair shop available again
            }
        }
    }
}
