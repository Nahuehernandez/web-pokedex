package com.example.WebPokedex;

import pokedex.Pokemon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditType", value = "/EditType")
public class EditType extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();

        Adapter adapter = new Adapter("DataBase.txt");
        Pokemon pokemonExists = adapter.searchPokemon(request.getParameter("name"));

        out.println("<html><body>");
        if(pokemonExists != null){
            String newType = request.getParameter("type");
            if(pokemonExists.getTypes().stream().anyMatch(type -> type.equalsIgnoreCase(newType))){
                out.println("The type " + newType + " is already exists");
                out.println("<br><br>");
            }else {
                pokemonExists.setTypes(newType);
                adapter.saveModifyPokemon(pokemonExists, pokemonExists.getName());
                out.println("Type added");
                out.println("<br><br>");
            }
        }else{
            out.println("The pokemon " + request.getParameter("name") + " it doesn't exists");
        }
        out.println("</body></html>");*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
