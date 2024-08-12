package com.kian.dragdropflutter_back.services;

import com.kian.dragdropflutter_back.data.AppPage;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class InjectService {
    public String searchCommentInFlutterProject(String comment, String flutterProjectPath, AppPage page, int x) throws Exception {
        File directory = new File(flutterProjectPath);
        if (!directory.isDirectory()) {
            return "Error: The specified path does not point to a valid directory";
        }
        return searchCommentInDirectory(directory, comment, page,x);
    }

    private String searchCommentInDirectory(File directory, String comment ,AppPage page,int x) throws Exception {
        File[] files = directory.listFiles();
        if (files == null) {
            return "Error: Unable to list files in the directory " + directory.getPath();
        }

        for (File file : files) {
            if (file.isFile()) {
                String result = searchCommentInFile(file, comment, page,x);
                if ("done".equals(result)) {
                    return "done";
                }
            } else if (file.isDirectory()) {
                String result = searchCommentInDirectory(file, comment, page,x);
                if ("done".equals(result)) {
                    return "done";
                }
            }
        }

        return "The comment '" + comment + "' was not found in the Flutter project";
    }

    private String searchCommentInFile(File file, String comment,AppPage page,int x) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            StringBuilder updatedFileContent = new StringBuilder();
            boolean commentFound = false;
            while ((line = br.readLine()) != null) {
                updatedFileContent.append(line).append("\n");
                if (line.contains(comment)) {
                    commentFound = true;
                    switch (comment) {
                        case "PAGE_UI":
                            updatedFileContent.append("\n").append("body = ").append(page.getAppComponents().toDartAsString());
                            break;
                        case "AppContextState<GeneratedPage> {":
                            updatedFileContent.append("\n").append("  bool isChecked1 = false;\n" +
                                    "  bool isChecked2 = false;\n" +
                                    "  bool isChecked3 = false;" + " " +
                                    " bool isChecked4 = false;\n" +
                                    "  bool isChecked5 = false;\n" +
                                    " String _selectedValue = 'Option 1';\n "
                            );
                            break;
                        case "IMPORT_PATH":
                            updatedFileContent.append("import '../../modules/page").append(x).append("/page_route.dart';").append("\n");
                            break;
                        case "DEFINE_ROUTES":
                            updatedFileContent.append("Page").append(x).append("Routes").append("(_appContext).registerRoutes();").append("\n");
                            // Ajoutez ici le traitement pour le cas 5
                            break;
                        default:
                            // Ajoutez ici le traitement par défaut si nécessaire
                            break;
                    }
                }
            }
            if (commentFound) {
                writeToFile(file, updatedFileContent.toString());
                return "done";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to read the file " + file.getName();
        }
        return "not found";
    }

    private void writeToFile(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }


    public static void injectIndex(int pageIndex, String dataTime, String route) {
        String separator = File.separator; // System-independent file separator
        // Correcting the file path format based on the example provided
        String[] pathComponents = {
                "src", "main", "flutter", dataTime, "lib", "modules", "page" + pageIndex, "page_route.dart"
        };

        System.out.println("Verifying each path component:");
        for (String component : pathComponents) {
            System.out.println(component);
        }

        String filePath = String.join(separator, pathComponents);
        Path path = Paths.get(filePath);
        System.out.println("Attempting to access file at: " + path.toAbsolutePath());

        try {
            if (!Files.exists(path)) {
                System.out.println("File does not exist: " + path.toAbsolutePath());
                return;
            }

            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String regex1 = "NNNN"; // Original regex to replace with pageIndex
            Pattern pattern1 = Pattern.compile(regex1);

            String regex2 = "PAGE_ROUTE"; // Additional regex to replace with route
            Pattern pattern2 = Pattern.compile(regex2);

            List<String> modifiedLines = new ArrayList<>();
            for (String line : lines) {
                String s = pattern1.matcher(line).replaceAll(String.valueOf(pageIndex));
                String string = pattern2.matcher(s).replaceAll(route);
                modifiedLines.add(string);
            }

            Files.write(path, modifiedLines, StandardCharsets.UTF_8);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }
    public static void injectRoute(String dataTime, String home,String login) {
        String separator = File.separator; // System-independent file separator
        // Correcting the file path format based on the example provided
        String[] pathComponents = {
                "src", "main", "flutter", dataTime, "lib", "config", "routes" , "routes.dart"
        };

        System.out.println("Verifying each path component:");
        for (String component : pathComponents) {
            System.out.println(component);
        }

        String filePath = String.join(separator, pathComponents);
        Path path = Paths.get(filePath);
        System.out.println("Attempting to access file at: " + path.toAbsolutePath());

        try {
            if (!Files.exists(path)) {
                System.out.println("File does not exist: " + path.toAbsolutePath());
                return;
            }

            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            String regex1 = "HOME_ROUTE"; // Original regex to replace with pageIndex
            Pattern pattern1 = Pattern.compile(regex1);

            String regex2 = "LOGIN_ROUTE"; // Additional regex to replace with route
            Pattern pattern2 = Pattern.compile(regex2);

            List<String> modifiedLines = new ArrayList<>();

            for (String line : lines) {
                String s = pattern1.matcher(line).replaceAll(String.valueOf(home));
                String string = pattern2.matcher(s).replaceAll(login);
                modifiedLines.add(string);
            }
            Files.write(path, modifiedLines, StandardCharsets.UTF_8);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }
}
