
package ohtu;

public class Player {
    private String name;
    private String team;
    public int goals;
    public int assists;
    public String nationality;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getNationality() {
    	return nationality;
    }
    
    public void setTeam(String team) {
    	this.team = team;
    }

    @Override
    public String toString() {
        return name + " " + team + "\t" + goals + " + " + assists + " = " + (goals + assists);
    }
      
}
