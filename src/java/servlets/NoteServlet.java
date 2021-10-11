
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
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        notes = NoteSerializer.readNoteTextFile(br);
        
        String editIndex = request.getParameter("edit");
        String createParam = request.getParameter("create");
        String deleteParam = request.getParameter("delete");
        
        if (editIndex != null){
            // edit
            int index = Integer.valueOf(editIndex);
            request.setAttribute("CONTENTS", notes.get(index));
            request.setAttribute("NOTE_ID", index);
            URL = "/WEB-INF/editnote.jsp";  
            getServletContext().getRequestDispatcher(URL).forward(request, response);         
        } else if (createParam != null) {
            // create
            URL = "/WEB-INF/createnote.jsp";
            getServletContext().getRequestDispatcher(URL).forward(request, response);
        } else if (deleteParam != null) {
            // delete
            int index = Integer.valueOf(deleteParam);
            notes.remove(index);
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
            NoteSerializer.SaveNotesToTextFile(pw, notes);
            response.sendRedirect("/note");  
        } else {
            request.setAttribute("CONTENTS", notes);
            URL = "/WEB-INF/viewnote.jsp";   
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
        URL = "/WEB-INF/viewnote.jsp";

        
        String save = request.getParameter("save");
        String create = request.getParameter("create");
        
        if (save != null) {
            int saveIndex = Integer.valueOf(save);
            Note fileContents = new Note(title, contents);
            notes.set(saveIndex, fileContents);
        } else if (create != null) {
            notes.add(new Note(title, contents));
        }

        path = getServletContext().getRealPath("/WEB-INF/note.txt");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
        NoteSerializer.SaveNotesToTextFile(pw, notes);

        response.sendRedirect("/note");  
    }
}
