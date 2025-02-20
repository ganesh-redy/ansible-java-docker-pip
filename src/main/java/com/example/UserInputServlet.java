package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet("/submit")
public class UserInputServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user input from the form
        String userInput = request.getParameter("userInput");

        // Write the input to a text file
        try (FileWriter writer = new FileWriter("output.txt", true)) {
            writer.write(userInput + "\n");
            response.getWriter().write("Text has been written to output.txt");
        } catch (IOException e) {
            response.getWriter().write("Error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
