
public class Tesss {

    private String rrff; //|RRFF IS A STRING TYPE VARIABLE
    private String tttrrr; //|TTTRRR IS A STRING TYPE VARIABLE
    private String tttt; //|TTTT IS A STRING TYPE VARIABLE

    public Tesss(String argRrff, String argTttrrr, String argTttt) {
        this.rrff = argRrff;
        this.tttrrr = argTttrrr;
        this.tttt = argTttt;
    }
    //|――――|GETRRFF METHOD RETURNS STRING―――――――――――――――|

    public String getRrff() {
        return this.rrff;
    }
    //|――――|SETRRFF METHOD TAKE STRING TYPE ARGUMENT――――|

    public void setRrff(String argRrff) {
        this.rrff = argRrff;
    }
    //|――――|GETTTTRRR METHOD RETURNS STRING―――――――――――――|

    public String getTttrrr() {
        return this.tttrrr;
    }
    //|――――|SETTTTRRR METHOD TAKE STRING TYPE ARGUMENT――|

    public void setTttrrr(String argTttrrr) {
        this.tttrrr = argTttrrr;
    }
    //|――――|GETTTTT METHOD RETURNS STRING―――――――――――――――|

    public String getTttt() {
        return this.tttt;
    }
    //|――――|SETTTTT METHOD TAKE STRING TYPE ARGUMENT――――|

    public void setTttt(String argTttt) {
        this.tttt = argTttt;
    }

    public String toString() {
        return "STRING_VALUE_OF_THE_TESSS_MODEL: " + this.rrff + this.tttrrr + this.tttt;
    }
}
