package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Patient implements Serializable {
    private String patientId="N/D";
    private String firstName="N/D";
    private String lastName="N/D";
    private String birthDay="N/D";
    private int gender=-1;
    private String lastVisit="N/D";
    private String lastDiagnostic="N/D";
    private ArrayList<String> prescriptionsId;
private DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Patient Patient(String patientId, String firstName, String lastName, String birthDay, int gender, LocalDate lastVisit, String lastDiagnostic) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.lastVisit =lastVisit.format(df);;
        this.lastDiagnostic = lastDiagnostic;
        this.prescriptionsId = new ArrayList<>();
        return this;
    }
    public Patient Patient(String patientId, String firstName, String lastName, LocalDate birthDay, int gender, LocalDate lastVisit, String lastDiagnostic) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay.format(df);
        this.gender = gender;
        this.lastVisit =lastVisit.format(df);;
        this.lastDiagnostic = lastDiagnostic;
        this.prescriptionsId = new ArrayList<>();
        return this;
    }
    @JsonIgnore
    public int getAge(){
            return Period.between(LocalDate.parse(birthDay,df),LocalDate.now()).getYears();
    }
    @JsonIgnore
    public String getFullName() {
        return lastName+" "+firstName;
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
    @JsonIgnore
    public void setUUID(int  code) {
        setPatientId(String.format("#%06d",code));

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
