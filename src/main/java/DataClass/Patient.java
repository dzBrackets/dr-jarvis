package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dr.FinalsVal;

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

import static dr.FinalsVal.dateFilters;

public class Patient implements Serializable {
    private String patientId="N/D";
    private String firstName="N/D";
    private String lastName="N/D";
    private String birthDay="12-12-1999";
    private int gender=-1;
    private String lastVisit="2";
    private String lastDiagnostic="12-12-1999";
    private ArrayList<String> prescriptionsId;

    public Patient Patient(String patientId, String firstName, String lastName, String birthDay, int gender, LocalDate lastVisit, String lastDiagnostic) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.lastVisit =lastVisit.format(dateFilters[1]);;
        this.lastDiagnostic = lastDiagnostic;
        this.prescriptionsId = new ArrayList<>();
        return this;
    }
    public Patient Patient(String patientId, String firstName, String lastName, LocalDate birthDay, int gender, LocalDate lastVisit, String lastDiagnostic) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay.format(dateFilters[1]);
        this.gender = gender;
        this.lastVisit =lastVisit.format(dateFilters[1]);;
        this.lastDiagnostic = lastDiagnostic;
        this.prescriptionsId = new ArrayList<>();
        return this;
    }
    @JsonIgnore
    public int getAge(){

        LocalDate birth = LocalDate.parse(birthDay, dateFilters[1]);
        System.out.println(birth);
        int calc = Period.between(birth, LocalDate.now()).getYears();
        return calc;
    }
    @JsonIgnore
    public String getFullName() {
        return lastName+" "+firstName;
    }

    @JsonIgnore
    public Patient cloneMe(){
        return new Patient().Patient(patientId,firstName,lastName,birthDay,gender,LocalDate.parse(lastVisit,dateFilters[1]),lastDiagnostic);
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
    @JsonIgnore
    public void addPrescription(String id){
        prescriptionsId.add(id);
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
    @JsonIgnore
    public void updateVisit() {
        lastVisit=LocalDate.now().format(dateFilters[1]);
    }
}
