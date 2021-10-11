
package servlets;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;


public class NoteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String URL;
        String href;
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
       
        String title;
        String contents;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            title = br.readLine();
            contents = br.readLine();
        }
        Note fileContents = new Note(title, contents);

        
        href = request.getParameter("edit");
        
        
        if (href == null){
            request.setAttribute("CONTENTS", fileContents);
            URL = "/WEB-INF/viewnote.jsp";   
            getServletContext().getRequestDispatcher(URL).forward(request, response);            
        }
        else
        {
            request.setAttribute("CONTENTS", fileContents);
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
        URL = "/WEB-INF/viewnote.jsp";
        Note fileContents;
        fileContents = new Note(title, contents);

        path = getServletContext().getRealPath("/WEB-INF/note.txt");
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
            pw.println(title);
            pw.println(contents);
        }

        request.setAttribute("CONTENTS", fileContents);
        getServletContext().getRequestDispatcher(URL).forward(request, response);
    }

}
