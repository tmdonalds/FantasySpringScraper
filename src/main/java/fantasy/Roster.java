package fantasy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by trevor on 10/10/15.
 */
public class Roster {
    Map<String,String> rosterMap = new HashMap<>();

    private DeciderMetaData quarterback;
    private DeciderMetaData runningBack1;
    private DeciderMetaData runningBack2;
    private DeciderMetaData wr1;
    private DeciderMetaData wr2;
    private DeciderMetaData wr3;
    private DeciderMetaData te;
    private DeciderMetaData k;
    private DeciderMetaData def;

    public Map<String, String> getRosterMap() {
        return rosterMap;
    }

    public DeciderMetaData getQuarterback() {
        return quarterback;
    }

    public void setQuarterback(DeciderMetaData quarterback) {
        this.quarterback = quarterback;
        this.rosterMap.put(quarterback.getName(), quarterback.getName());
    }

    public DeciderMetaData getRunningBack1() {
        return runningBack1;
    }

    public void setRunningBack1(DeciderMetaData runningBack1) {
        this.runningBack1 = runningBack1;
        this.rosterMap.put(runningBack1.getName(), runningBack1.getName());
    }

    public DeciderMetaData getRunningBack2() {
        return runningBack2;
    }

    public void setRunningBack2(DeciderMetaData runningBack2) {
        this.runningBack2 = runningBack2;
        this.rosterMap.put(runningBack2.getName(), runningBack2.getName());
    }

    public DeciderMetaData getWr1() {
        return wr1;
    }

    public void setWr1(DeciderMetaData wr1) {
        this.wr1 = wr1;
        this.rosterMap.put(wr1.getName(), wr1.getName());
    }

    public DeciderMetaData getWr2() {
        return wr2;
    }

    public void setWr2(DeciderMetaData wr2) {
        this.wr2 = wr2;
        this.rosterMap.put(wr2.getName(), wr2.getName());
    }

    public DeciderMetaData getWr3() {
        return wr3;
    }

    public void setWr3(DeciderMetaData wr3) {
        this.wr3 = wr3;
        this.rosterMap.put(wr3.getName(), wr3.getName());
    }

    public DeciderMetaData getTe() {
        return te;
    }

    public void setTe(DeciderMetaData te) {
        this.te = te;
        this.rosterMap.put(te.getName(), te.getName());
    }

    public DeciderMetaData getK() {
        return k;
    }

    public void setK(DeciderMetaData k) {
        this.k = k;
        this.rosterMap.put(k.getName(), k.getName());
    }

    public DeciderMetaData getDef() {
        return def;
    }

    public void setDef(DeciderMetaData def) {
        this.def = def;
        this.rosterMap.put(def.getName(), def.getName());
    }

    @Override
    public String toString() {
        return "Roster{" +
                "quarterback=" + quarterback +
                ", runningBack1=" + runningBack1 +
                ", runningBack2=" + runningBack2 +
                ", wr1=" + wr1 +
                ", wr2=" + wr2 +
                ", wr3=" + wr3 +
                ", te=" + te +
                ", k=" + k +
                ", def=" + def +
                '}';
    }

    public void printSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("*******************************************");
        sb.append(System.lineSeparator());
        sb.append(printSummaryLine(quarterback));
        sb.append(printSummaryLine(runningBack1));
        sb.append(printSummaryLine(runningBack2));
        sb.append(printSummaryLine(wr1));
        sb.append(printSummaryLine(wr2));
        sb.append(printSummaryLine(wr3));
        sb.append(printSummaryLine(te));
        sb.append(printSummaryLine(k));
        sb.append(printSummaryLine(def));
        sb.append(System.lineSeparator());
        sb.append("*******************************************");
        sb.append("salaries total [");
        sb.append(totalSalaries());
        sb.append("]");

        System.out.println(sb.toString());
    }

    private int totalSalaries() {
        return quarterback.getSalary() + runningBack1.getSalary() + runningBack2.getSalary() +
                wr1.getSalary() + wr2.getSalary() + wr3.getSalary() + te.getSalary() +
                def.getSalary() + k.getSalary();
    }

    private String printSummaryLine(DeciderMetaData deciderMetaData){
        StringBuilder sb = new StringBuilder();
        sb.append(deciderMetaData.getPosition());
        sb.append(" = ");
        sb.append(deciderMetaData.getName());
        sb.append("[salary=");
        sb.append(deciderMetaData.getSalary());
        sb.append("]");
        sb.append("[ranking=");
        sb.append(deciderMetaData.getEspnRanking().getPlayerRanking());
        sb.append("]");
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
