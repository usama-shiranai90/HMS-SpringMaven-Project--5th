package Java.HMS.Data;

import java.io.Serializable;


public class Ward implements Serializable {

    private int wardno;
    private String wardname;
    private int floorno;
    private int extensiono;
    private int numberofbeds;

    public Ward(int wardno, String wardname, int floorno, int extensiono, int numberofbeds) {
        this.wardno = wardno;
        this.wardname = wardname;
        this.floorno = floorno;
        this.extensiono = extensiono;
        this.numberofbeds = numberofbeds;
    }

    public Ward(int wardno, String wardname, int numberofbeds) {
        this.wardno = wardno;
        this.wardname = wardname;
        this.numberofbeds = numberofbeds;
    }

    public int getWardno() {
        return wardno;
    }

    public void setWardno(int wardno) {
        this.wardno = wardno;
    }

    public String getWardname() {
        return wardname;
    }

    public void setWardname(String wardname) {
        this.wardname = wardname;
    }

    public int getFloorno() {
        return floorno;
    }

    public void setFloorno(int floorno) {
        this.floorno = floorno;
    }

    public int getExtensiono() {
        return extensiono;
    }

    public void setExtensiono(int extensiono) {
        this.extensiono = extensiono;
    }

    public int getNumberofbeds() {
        return numberofbeds;
    }

    public void setNumberofbeds(int numberofbeds) {
        this.numberofbeds = numberofbeds;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "wardno=" + wardno +
                ", wardname='" + wardname + '\'' +
                ", floorno=" + floorno +
                ", extensiono=" + extensiono +
                ", numberofbeds=" + numberofbeds +
                '}';
    }
}
