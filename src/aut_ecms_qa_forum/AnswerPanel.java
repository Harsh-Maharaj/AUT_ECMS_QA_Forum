package aut_ecms_qa_forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AnswerPanel extends JPanel {
    private DefaultListModel<Answer> listModel;
    private JList<Answer> answerList;
    private JPopupMenu contextMenu;
    private User currentUser;

    public AnswerPanel(User currentUser) {
        this.currentUser = currentUser;
        setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<>();
        answerList = new JList<>(listModel);
        answerList.setCellRenderer(new AnswerListCellRenderer());
        add(new JScrollPane(answerList), BorderLayout.CENTER);

        // Initialize the context menu
        contextMenu = new JPopupMenu();
        JMenuItem editMenuItem = new JMenuItem("Edit");
        contextMenu.add(editMenuItem);

        // Add mouse listener to show the context menu on right-click
        answerList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int index = answerList.locationToIndex(e.getPoint());
                    answerList.setSelectedIndex(index);
                    contextMenu.show(answerList, e.getX(), e.getY());
                }
            }
        });

        // Add action listener for the edit menu item
        editMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Answer selectedAnswer = answerList.getSelectedValue();
                if (selectedAnswer != null) {
                    String newContent = JOptionPane.showInputDialog("Enter new answer content:", selectedAnswer.getContent());
                    if (newContent != null && !newContent.trim().isEmpty()) {
                        currentUser.editAnswer(selectedAnswer, newContent);
                        listModel.setElementAt(selectedAnswer, answerList.getSelectedIndex()); // Update the list model
                    }
                } else {
                    JOptionPane.showMessageDialog(AnswerPanel.this, "No answer selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void displayAnswers(Question question) {
        listModel.clear();
        System.out.println("Displaying answers for question: " + question.getTitle());
        List<Answer> answers = ForumDatabase.getInstance().getAnswerManager().getAnswersByQuestion(question);
        for (Answer answer : answers) {
            if (answer.getAuthor() == null) {
                System.err.println("Error: Answer author is null for answer: " + answer.getContent());
            } else {
                System.out.println("Adding answer: " + answer.getContent() + " by " + answer.getAuthor().getUsername());
            }
            listModel.addElement(answer);
        }
    }

    public Answer getSelectedAnswer() {
        return answerList.getSelectedValue();
    }

    private class AnswerListCellRenderer extends JPanel implements ListCellRenderer<Answer> {
        private JLabel label;

        public AnswerListCellRenderer() {
            setLayout(new BorderLayout(10, 10));
            setBorder(new EmptyBorder(10, 10, 10, 10));
            label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label, BorderLayout.CENTER);
            setBackground(Color.WHITE);
            setBorder(new LineBorder(Color.BLACK, 1, true));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Answer> list, Answer value, int index, boolean isSelected, boolean cellHasFocus) {
            label.setText("<html>Answer by " + value.getAuthor().getUsername() + ":<br>" + value.getContent() + "</html>");
            if (isSelected) {
                setBackground(Color.LIGHT_GRAY);
            } else {
                setBackground(Color.WHITE);
            }
            return this;
        }
    }
}
