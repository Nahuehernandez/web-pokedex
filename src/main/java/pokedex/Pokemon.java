package pokedex;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private String name;
    private List<String> types;
    private int level;
    private List<String> abilities;
    private List<Pokemon> evolutions;

    public Pokemon(String _name, int _level){
        this.name = _name;
        this.level = _level;
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.evolutions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(String type) {
        this.types.add(type);
    }

    public void removeType(String type){
        this.types.remove(type);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<String> getAbilities(){
        return this.abilities;
    }

    public void setAbility(String ability){
        this.abilities.add(ability);
    }

    public List<Pokemon> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(Pokemon evolution) {
        this.evolutions.add(evolution);
    }
}
