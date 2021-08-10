package com.example.WebPokedex;

import pokedex.Pokemon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RemoveType", value = "/RemoveType")
public class RemoveType extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();

        Adapter adapter = new Adapter("DataBase.txt");
        Pokemon pokemonExists = adapter.searchPokemon(request.getParameter("name"));

        out.println("<html><body>");
        if(pokemonExists != null){
            String removeType = request.getParameter("type");
            if(pokemonExists.getTypes().stream().anyMatch(type -> type.equals(removeType))) {
                pokemonExists.removeType(removeType);
                adapter.saveModifyPokemon(pokemonExists, pokemonExists.getName());
            }else{
                out.println("the type " + removeType + " it doesn't exists");
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
