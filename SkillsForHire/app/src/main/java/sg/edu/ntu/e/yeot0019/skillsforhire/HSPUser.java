package sg.edu.ntu.e.yeot0019.skillsforhire;


public class HSPUser {
    private String hspName,hspStatus,hspType,hspRating;

    public String getHSPName() {
        return hspName;
    }

    public void setHSPName(String HSPName) {
        this.hspName = HSPName;
    }

    public String getHSPStatus() {
        return hspStatus;
    }

    public void setHSPStatus(String HSPStatus) {
        this.hspStatus = HSPStatus;
    }

    public String getHSPType() {
        return hspType;
    }

    public void setHSPType(String HSPType) {
        this.hspType = HSPType;
    }

    public String getHSPRating() {
        return hspRating;
    }

    public void setHSPRating(String HSPRating) {
        this.hspRating = HSPRating;
    }

    public HSPUser(String HSPName, String HSPStatus , String HSPType, String HSPRating){
        this.hspName = HSPName;
        this.hspStatus=HSPStatus;
        this.hspStatus= HSPType;
        this.hspRating = HSPRating;
    }
    public HSPUser(){

    }
    @Override
    public String toString() {
        return "User{" +
                "user_name" + hspName + '\'' +
                ", Status: '" + hspStatus + '\'' +
                ", Type: '" + hspType + '\'' +
                ", Rating: '" + hspRating + '\'' +
                '}';
    }
}