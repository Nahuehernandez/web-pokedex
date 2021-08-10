package com.example.WebPokedex;

import pokedex.Pokemon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditName", value = "/EditName")
public class EditName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();

        Adapter adapter = new Adapter("DataBase.txt");
        Pokemon pokemonExists = adapter.searchPokemon(request.getParameter("oldname"));

        out.println("<html><body>");
        if(pokemonExists != null){
            Pokemon pokemonNewName = adapter.searchPokemon(request.getParameter("newname"));
            if(pokemonNewName == null){
                String oldName = request.getParameter("oldname");
                pokemonExists.setName(request.getParameter("newname"));
                adapter.saveModifyPokemon(pokemonExists, oldName);
                out.println("Pokemon modify");
            }else{
                out.println("It's already exists a pokemon with the name: " + request.getParameter("newname") + ", you can't repeat the name");
            }
        }else{
            out.println("The pokemon " + request.getParameter("oldname") + " it doesn't exists");
        }
        out.println("</body></html>");*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
