/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import models.Note;

/**
 *
 * @author jinwei
 */
public class NoteSerializer {
    public static List<Note> readNoteTextFile(BufferedReader br) {
        List<String> titles = new ArrayList<>();
        List<String> contents = new ArrayList<>();
        try {
            while (true) {
                String title = br.readLine();
                if (title == null) break;
                titles.add(title);
                StringBuilder strBuilder = new StringBuilder();
                Boolean isFirstLine = true;
                while(true) {
                    String line = br.readLine();
                    if (line == null || line.isEmpty()) break;
                    if (!isFirstLine) strBuilder.append("\n");
                    isFirstLine = false;
                    strBuilder.append(line);
                }
                contents.add(strBuilder.toString());
            }
        } catch (IOException e) {
            
        }
        List<Note> notes = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            notes.add(new Note(titles.get(i), contents.get(i)));
        }
        return notes;
    }
    
    public static void SaveNotesToTextFile(PrintWriter pw, List<Note> notes) {
        for (Note note: notes) {
            pw.write(note.getTitle());
            pw.write('\n');
            pw.write(note.getContents());
            pw.write('\n');
            pw.write('\n');
            pw.flush();
        }
    }
}
