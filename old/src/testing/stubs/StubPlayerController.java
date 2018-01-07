package testing.stubs;

import enums.Role;

public class StubPlayerController {
    public StubPlayerController(){}
    public StubPlayerController(StubPlayer player) {}
    public Role getRole(){return Role.CODE_BREAKER;}
    public boolean checkPassword(String password){return true;}
    public StubPlayer newHuman(String username, String password){return new StubPlayer();}
    public StubPlayer newHuman(String username){return new StubPlayer();}
    public StubPlayer newCPU(String id){return new StubPlayer();}
    public StubPlayer getPlayer(){return new StubPlayer();}
    public String getId(){return "pep";}
    public void setRole(Role role){}
    public void restart(){}
}
