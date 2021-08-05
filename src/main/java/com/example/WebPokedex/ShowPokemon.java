package com.example.WebPokedex;

import file.Adapter;
import pokedex.Pokemon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShowPokemon", value = "/ShowPokemon")
public class ShowPokemon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Adapter adapter = new Adapter("DataBase.txt");
        Pokemon pokemon = adapter.searchPokemon(request.getParameter("name"));

        out.println("<html><body>");
        if(pokemon != null){
            out.println("Name: " + pokemon.getName());
            out.println("<br><br>");
            out.println("Level: " + pokemon.getLevel());
            out.println("<br><br>");
            out.println("Types:");
            for (int i = 0; i < pokemon.getTypes().size(); i++) {
                out.println("      " + pokemon.getTypes().get(i));
            }
            out.println("<br><br>");
            out.println("Evolutions:");
            for (int i = 0; i < pokemon.getEvolutions().size(); i++) {
                out.println("           " + pokemon.getEvolutions().get(i).getName());
            }
        }else{
            out.println("The pokemon " + request.getParameter("name") + " doesn't exists");
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
