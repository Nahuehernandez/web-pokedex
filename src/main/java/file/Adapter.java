package file;

import pokedex.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Adapter {
    private EditFile editFile;// = new EditFile();

    public Adapter(String nameFile){
        this.editFile = new EditFile(nameFile);
    }

    public Pokemon generatePokemon(String line){
        String[] parts = line.split("#");
        String[] stringTypes;
        String[] stringAbilities;
        String[] stringEvolutions;
        Pokemon pokemon = new Pokemon(parts[0], Integer.parseInt(parts[1]));

        switch(parts.length){
            case 3:
                stringTypes = parts[2].split(",");
                for(int i = 0; i < stringTypes.length; i++){
                    pokemon.setTypes(stringTypes[i]);
                }
                break;
            case 4:
                stringTypes = parts[2].split(",");
                for(int i = 0; i < stringTypes.length; i++){
                    pokemon.setTypes(stringTypes[i]);
                }
                stringAbilities = parts[3].split(",");
                for(int i = 0; i < stringAbilities.length; i++){
                    pokemon.setAbility(stringAbilities[i]);
                }
                break;
            case 5:
                stringTypes = parts[2].split(",");
                for(int i = 0; i < stringTypes.length; i++){
                    pokemon.setTypes(stringTypes[i]);
                }
                stringAbilities = parts[3].split(",");
                for(int i = 0; i < stringAbilities.length; i++){
                    pokemon.setAbility(stringAbilities[i]);
                }
                stringEvolutions = parts[4].split(",");
                for(int i = 0; i < stringEvolutions.length; i++){
                    Pokemon evolution = searchPokemon(stringEvolutions[i]);
                    pokemon.setEvolutions(evolution);
                }
                break;
            default:
                break;
        }
        return pokemon;
    }

    public void savePokemon(Pokemon pokemon) {
        List<Pokemon> evolutions = pokemon.getEvolutions();
        List<String> namesEvolutions = new ArrayList<>();
        for(int i =0; i < evolutions.size(); i++){
            namesEvolutions.add(evolutions.get(i).getName());
        }
        editFile.writePokemon(pokemon.getName(), Integer.toString(pokemon.getLevel()), pokemon.getTypes(), pokemon.getAbilities(), namesEvolutions);
    }

    public void saveModifyPokemon(Pokemon pokemon, String oldName){
        List<Pokemon> evolutions = pokemon.getEvolutions();
        List<String> namesEvolutions = new ArrayList<>();
        for(int i =0; i < evolutions.size(); i++){
            namesEvolutions.add(evolutions.get(i).getName());
        }

        editFile.writeModifyPokemon(oldName, pokemon.getName(), Integer.toString(pokemon.getLevel()), pokemon.getTypes(), pokemon.getAbilities(), namesEvolutions);
    }

    public Pokemon searchPokemon(String name){
        String line = editFile.readPokemon(name);
        if(line.equalsIgnoreCase("error")){
            return null;
        }
        Pokemon pokemon = generatePokemon(line);
        return pokemon;
    }

    public List<Pokemon> allPokemons(){
        List<String> lines = editFile.readDataBase();
        List<Pokemon> pokemons = new ArrayList<>();

        for(int i = 0; i < lines.size(); i++){
            Pokemon pokemon = generatePokemon(lines.get(i));
            pokemons.add(pokemon);
        }

        return pokemons;
    }

}
