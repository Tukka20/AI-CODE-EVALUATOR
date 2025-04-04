package com.example.smartcodeevaluator.service;

import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class CodeExecutionService {
    public String executeCode(String code, String language) throws IOException, InterruptedException {
        String fileName = "temp." + (language.equals("C") ? "c" : "cpp");
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        writer.write(code);
        writer.close();

        ProcessBuilder compileProcess = new ProcessBuilder("gcc", fileName, "-o", "temp");
        Process compile = compileProcess.start();
        compile.waitFor();
        if (compile.exitValue() != 0) {
            return "Compilation failed";
        }

        ProcessBuilder runProcess = new ProcessBuilder("./temp");
        Process run = runProcess.start();
        run.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(run.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }
        return output.toString();
    }
}