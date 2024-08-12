package com.kian.dragdropflutter_back.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FlutterCommandService {

    @Autowired
    private JavaMailSender mailSender;

    public String buildApk(String emailTo) throws IOException, InterruptedException, MessagingException, jakarta.mail.MessagingException {
        // Exécuter la commande "flutter build apk" avec ProcessBuilder
        String directory = "C:\\Users\\Adam\\IdeaProjects\\test2\\src\\main\\flutter";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "flutter", "build", "apk");
        builder.directory(new File(directory));

        // Démarrer le processus
        Process process = builder.start();

        // Lire la sortie de la commande de manière asynchrone
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        // Attendre que la commande se termine
        int exitCode = process.waitFor();

        // Si le build est réussi, zipper et envoyer l'APK par email
        if (exitCode == 0) {
            String apkPath = directory + "\\build\\app\\outputs\\flutter-apk\\app-release.apk";
            String zipPath = directory + "\\build\\app\\outputs\\flutter-apk\\app-release.zip";
            zipFile(apkPath, zipPath);
            sendEmailWithAttachment(emailTo, "Flutter APK Build", "Here is the latest APK build.", zipPath);
        }

        // Retourner la sortie de la commande avec le code de sortie
        return "Sortie de la commande : " + output.toString() + "\nCode de sortie : " + exitCode;
    }

    private void zipFile(String sourceFilePath, String zipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            File fileToZip = new File(sourceFilePath);
            try (FileInputStream fis = new FileInputStream(fileToZip)) {
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zos.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }
            }
        }
    }

    private void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("adem.wertani@esprit.tn"); // Utilisez l'adresse e-mail configurée
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("app-release.zip", file);

        mailSender.send(message);
    }
}
