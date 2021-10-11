
package servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;
import utils.NoteSerializer;


public class NoteServlet extends HttpServlet {
    private List<Note> notes;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String URL;
        String href;
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        notes = NoteSerializer.readNoteTextFile(br);
        
        href = request.getParameter("edit");
        
        if (href == null){
            request.setAttribute("CONTENTS", notes);
            URL = "/WEB-INF/viewnote.jsp";   
            getServletContext().getRequestDispatcher(URL).forward(request, response);            
        }
        else
        {
            int index = Integer.valueOf(href);
            request.setAttribute("CONTENTS", notes.get(index));
            request.setAttribute("NOTE_ID", index);
            URL = "/WEB-INF/editnote.jsp";  
            getServletContext().getRequestDispatcher(URL).forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        String URL;
        String path;
        String title = request.getParameter("EDIT_TITLE");
        String contents = request.getParameter("EDIT_CONTENTS");
        int index = Integer.valueOf(request.getParameter("save"));
        System.out.println(index);
        URL = "/WEB-INF/viewnote.jsp";
        Note fileContents;
        fileContents = new Note(title, contents);
        notes.set(index, fileContents);

        path = getServletContext().getRealPath("/WEB-INF/note.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
        NoteSerializer.SaveNotesToTextFile(pw, notes);

        request.setAttribute("CONTENTS", fileContents);
        response.sendRedirect("/note");  
    }
}
