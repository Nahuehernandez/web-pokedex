package com.example.WebPokedex;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddEvolution", value = "/AddEvolution")
public class AddEvolution extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();

        Adapter adapter = new Adapter("DataBase.txt");
        Pokemon pokemonExists = adapter.searchPokemon(request.getParameter("name"));

        out.println("<html><body>");
        if(pokemonExists != null){
            String evolution = request.getParameter("nameevolution");
            Pokemon evolutionPokemon = adapter.searchPokemon(evolution);
            if(evolutionPokemon != null) {
                if(pokemonExists.getEvolutions().stream().anyMatch(n -> n.getName().equalsIgnoreCase(evolution))){
                    out.println("the pokemon already has the evolution " + evolution);
                }else {
                    pokemonExists.setEvolutions(evolutionPokemon);
                    adapter.saveModifyPokemon(pokemonExists, pokemonExists.getName());
                    out.println("Evolution added");
                }
            }else{
                out.println("The evolution " + evolution + " it doesn't exists");
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
