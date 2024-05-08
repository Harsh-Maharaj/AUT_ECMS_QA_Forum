/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aut_ecms_qa_forum;

/**
 *
 * @author Harsh & Dillan
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages persistence of questions to a file including loading, saving, adding, and removing questions.
 * following on with this code we have utilised one of the PDC tutorials in to helping us make the file folder and how
 * it write and save the questions and answers, we have built on with this code from the tutorials.
 */
public class FilePersistence {
    // File where questions are stored.
    private static final String FILE_NAME = "./resources/questions.txt";
    // Maps question IDs to Question objects for quick access.
    private Map<String, Question> questionMap = new HashMap<>();
    // List of all questions for sequential access.
    private List<Question> questionList = new ArrayList<>();

    /**
     * Constructor that loads questions from the file into memory.
     */
    public FilePersistence() {
        loadQuestionsFromFile();
    }

    /**
     * Saves the list of questions to the file.
     * 
     * @param questions List of questions to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void saveQuestions(List<Question> questions) throws IOException {
        System.out.println("Attempting to save questions...");
        File file = new File(FILE_NAME);
        // Ensure parent directories exist.
        file.getParentFile().mkdirs();
        System.out.println("File path: " + file.getAbsolutePath());
        
        // Try-with-resources to ensure the writer is closed after use.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Question question : questions) {
                // Write question details to the file.
                writer.write(question.getId());
                writer.newLine();
                writer.write(question.getQuestionText());
                writer.newLine();
                writer.write(question.getAnswerText());
                writer.newLine();
                // Separator line for readability.
                writer.write("-----------------------------------------------------------");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print error to console.
            throw e; // Rethrow to indicate failure.
        }
        System.out.println("Questions saved successfully.");
    }

    /**
     * Loads questions from the file into memory.
     */
    private void loadQuestionsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // Early exit if file does not exist.
        }
        
        // Try-with-resources to ensure the scanner is closed after use.
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                // Read question details from the file.
                String id = scanner.nextLine().trim();
                String questionText = scanner.nextLine().trim();
                String answerText = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
                scanner.nextLine(); // Skip separator line.
                
                // Create and populate question object.
                Question question = new Question(); // Placeholder for actual constructor or setter methods.
                question.setId(id);
                question.setQuestionText(questionText);
                question.setAnswerText(answerText);
                
                // Add question to memory structures.
                questionList.add(question);
                questionMap.put(id, question);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Adds a question to memory and persists it to the file.
     * 
     * @param question The question to be added.
     */
    public void addQuestion(Question question) {
        questionMap.put(question.getId(), question);
        questionList.add(question);
        // Save updated list of questions.
        try {
            saveQuestions(questionList);
        } catch (IOException e) {
            System.out.println("Error saving questions: " + e.getMessage());
        }
    }

    /**
     * Removes a question by ID from memory and updates the file.
     * 
     * @param id The ID of the question to remove.
     * @return true if the question was successfully removed, false otherwise.
     */
    public boolean removeQuestion(String id) {
        if (questionMap.containsKey(id)) {
            Question question = questionMap.remove(id);
            questionList.remove(question);
            // Save updated list of questions.
            try {
                saveQuestions(questionList);
            } catch (IOException e) {
                System.out.println("Error saving questions: " + e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Retrieves all questions currently in memory.
     * 
     * @return A list of all questions.
     */
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questionList);
    }

    /**
     * Searches questions by keyword in question text or answer text.
     * 
     * @param keyword The keyword to search for.
     * @return A list of questions that contain the keyword.
     */
    public List<Question> searchQuestions(String keyword) {
        List<Question> results = new ArrayList<>();
        // Iterate through questions and add matches to results
        for (Question question : questionList) {
            if (question.getQuestionText().contains(keyword) || question.getAnswerText().contains(keyword)) {
                results.add(question); // Add question to results if it contains the keyword.
            }
        }
        return results; // Return the list of questions matching the search criteria.
    }
}