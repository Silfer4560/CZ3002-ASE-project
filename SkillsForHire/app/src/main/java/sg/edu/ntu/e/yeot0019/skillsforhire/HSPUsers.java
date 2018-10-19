package sg.edu.ntu.e.yeot0019.skillsforhire;

public class HSPUsers {
    //HSP user contains 4 fields of data UserName, Status,Type,Rating
    public String HSPUserName,HSPUserStatus, HSPUserType,HSPUserRating;

    //constructor for
    public HSPUsers(){

    }


    public HSPUsers(String username, String status, String type, String rating){
        this.HSPUserName=username;
        this.HSPUserStatus=status;
        this.HSPUserType=type;
        this.HSPUserRating=rating;
    }


    //get and set methods
    public String getHSPUserName() {
        return HSPUserName;
    }

    public void setHSPUserName(String HSPUserName) {
        this.HSPUserName = HSPUserName;
    }

    public String getHSPUserStatus() {
        return HSPUserStatus;
    }

    public void setHSPUserStatus(String HSPUserStatus) {
        this.HSPUserStatus = HSPUserStatus;
    }

    public String getHSPUserType() {
        return HSPUserType;
    }

    public void setHSPUserType(String HSPUserType) {
        this.HSPUserType = HSPUserType;
    }

    public String getHSPUserRating() {
        return HSPUserRating;
    }
}
