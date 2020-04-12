package DataClass;

import java.io.Serializable;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

public class Patient implements Serializable {
    private String patientId;
    private String firstName;
    private String lastName;
    private String birthDay;
    private int gender;
    private String lastVisit;
    private String lastDiagnostic;
    private ArrayList<String> prescriptionsId;

    public Patient Patient(String patientId, String firstName, String lastName, String birthDay, int gender, String lastVisit, String lastDiagnostic) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.lastVisit = lastVisit;
        this.lastDiagnostic = lastDiagnostic;
        this.prescriptionsId = new ArrayList<>();
        return this;
    }
public int getAge(){
  return 18;
}

    public String toString()
    {

        return "Patient [id="
                + patientId
                + ", first_name="
                + firstName
                + ", last_name="
                + lastName
                + ", birthday="
                + birthDay
                + ", gender="
                + gender
                + ", last_visit="
                + lastVisit
                + ", last_diagnostic="
                + lastDiagnostic
                + ", prescriptions="
                + prescriptionsId
                + "]";
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getLastDiagnostic() {
        return lastDiagnostic;
    }

    public void setLastDiagnostic(String lastDiagnostic) {
        this.lastDiagnostic = lastDiagnostic;
    }

    public ArrayList<String> getPrescriptionsId() {
        return prescriptionsId;
    }

    public void setPrescriptionsId(ArrayList<String> prescriptionsId) {
        this.prescriptionsId = prescriptionsId;
    }
}
