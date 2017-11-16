package testing.stubs;

import enums.Role;

public class PlayerController {
    public PlayerController(){}
    public PlayerController(Player player) {}
    public Role getRole(){return Role.CODE_BREAKER;}
    public boolean checkPassword(String password){return true;}
    public Player newHuman(String username, String password){return new Player();}
    public Player newHuman(String username){return new Player();}
    public Player newCPU(String id){return new Player();}
    public Player getPlayer(){return new Player();}
    public String getId(){return "pep";}
    public void setRole(Role role){}
    public void restart(){}
}
