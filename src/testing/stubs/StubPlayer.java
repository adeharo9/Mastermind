package testing.stubs;

import enums.*;

public class StubPlayer {

    public Object codeMake(Difficulty difficulty){return null;}
    public Object codeBreak(Difficulty difficulty, Object o, boolean b){return null;}
    public Object codeCorrect(Difficulty difficulty, Object o, Object o2){return null;}
    public String getId(){return "pep";}
    public Role getRole(){return Role.CODE_BREAKER;}

}
