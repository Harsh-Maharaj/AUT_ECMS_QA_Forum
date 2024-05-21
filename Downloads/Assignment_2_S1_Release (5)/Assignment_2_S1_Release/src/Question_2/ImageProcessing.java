package Question_2;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * Image Processing GUI Application
 * Author: Harsh Maharaj
 */
public class ImageProcessing extends JFrame {

    private BufferedImage bufferedImage = null;
    private JLabel imageLabel;

    public ImageProcessing() {
        setTitle("Image Noise Remover");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load Image");
        JButton cleanButton = new JButton("Clean Noise");
        JButton saveButton = new JButton("Save Image");

        loadButton.addActionListener(e -> loadImage());
        cleanButton.addActionListener(e -> cleanNoise());
        saveButton.addActionListener(e -> saveImage());

        buttonPanel.add(loadButton);
        buttonPanel.add(cleanButton);
        buttonPanel.add(saveButton);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(imageLabel);

        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                bufferedImage = ImageIO.read(selectedFile);
                imageLabel.setIcon(new ImageIcon(bufferedImage));
            } catch (IOException ex) {
                Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error loading image", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cleanNoise() {
        if (bufferedImage != null) {
            ImageProcess imageProcess = new ImageProcess(bufferedImage);
            imageProcess.cleanNoise();
            imageLabel.setIcon(new ImageIcon(bufferedImage));
        } else {
            JOptionPane.showMessageDialog(this, "No image loaded", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveImage() {
        if (bufferedImage != null) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String extension = "jpg"; // default extension
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.endsWith("." + extension)) {
                    filePath += "." + extension;
                }
                try {
                    ImageIO.write(bufferedImage, extension, new File(filePath));
                    JOptionPane.showMessageDialog(this, "Image saved successfully");
                } catch (IOException ex) {
                    Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Error saving image", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No image to save", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageProcessing gui = new ImageProcessing();
            gui.setVisible(true);
        });
    }

    class ImageProcess {

        private BufferedImage bufferedImage;

        public ImageProcess(BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
        }

        public void cleanNoise() {
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            WritableRaster writableRaster = bufferedImage.getRaster();
            for (int i = 1; i < height - 1; i++) {
                for (int j = 1; j < width - 1; j++) {
                    Integer[] intensityR = new Integer[9];
                    Integer[] intensityG = new Integer[9];
                    Integer[] intensityB = new Integer[9];
                    int index = 0;
                    int[] pixel = new int[3];

                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            int color = bufferedImage.getRGB(j + k, i + l);
                            pixel[2] = color & 0xff;
                            pixel[1] = (color & 0xff00) >> 8;
                            pixel[0] = (color & 0xff0000) >> 16;
                            intensityB[index] = pixel[2];
                            intensityG[index] = pixel[1];
                            intensityR[index] = pixel[0];
                            index++;
                        }
                    }

                    SortArray<Integer> sortArray = new SortArray<>(intensityR);
                    sortArray.quickSort();
                    sortArray.setArray(intensityG);
                    sortArray.quickSort();
                    sortArray.setArray(intensityB);
                    sortArray.quickSort();

                    pixel[2] = intensityB[4];
                    pixel[1] = intensityG[4];
                    pixel[0] = intensityR[4];

                    writableRaster.setPixel(j, i, pixel);
                }
            }
        }
    }

    class SortArray<E extends Comparable<E>> {

        private E[] array;

        public SortArray(E[] array) {
            this.array = array;
        }

        public void setArray(E[] array) {
            this.array = array;
        }

        public void quickSort() {
            quickSort(0, array.length - 1);
        }

        private void quickSort(int low, int high) {
            if (low < high) {
                int pi = partition(low, high);
                quickSort(low, pi - 1);
                quickSort(pi + 1, high);
            }
        }

        private int partition(int low, int high) {
            E pivot = array[high];
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (array[j].compareTo(pivot) <= 0) {
                    i++;
                    swap(i, j);
                }
            }
            swap(i + 1, high);
            return i + 1;
        }

        private void swap(int i, int j) {
            E temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
