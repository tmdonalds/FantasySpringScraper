package fantasy;

/**
 * Created by trevor on 10/10/15.
 */
public class FanDuelPlayer {

    private final String injuryIndicator;
    private final String firstName;
    private final String lastName;
    private final Integer salary;
    private final String fullName;
    private final String position;

    public FanDuelPlayer(String firstName, String lastName, Integer salary, String injuryIndicator,String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.injuryIndicator = injuryIndicator;
        this.position = position.replace("\"","");
        this.fullName = firstName.replace("\"","") + " " + lastName.replace("\"","");
    }

    public String getPosition() {
        return position;
    }

    public String getInjuryIndicator() {
        return injuryIndicator;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FanDuelPlayer that = (FanDuelPlayer) o;

        if (salary != that.salary) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (injuryIndicator != null ? !injuryIndicator.equals(that.injuryIndicator) : that.injuryIndicator != null)
            return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = injuryIndicator != null ? injuryIndicator.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
