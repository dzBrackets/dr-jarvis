package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Date;

public class userData {
String name="unknown player";
String email="dont@sk.me";
String address="empty";
String phone ="N/D";
int selectedTemplate=0;
int[] monthStats=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    public int getSelectedTemplate() {
        return selectedTemplate;
    }

    public void setSelectedTemplate(int selectedTemplate) {
        this.selectedTemplate = selectedTemplate;
    }

    public userData userData(String name, String email, String address, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        return this;
    }

    public void updateDayStats(int amount){
        int today=LocalDate.now().getDayOfMonth()-1;
        monthStats[today]=amount;
        checkAndReset(today);
   }
    public void updateDayStats(){
        int today=LocalDate.now().getDayOfMonth()-1;
        monthStats[today]+=1;
        checkAndReset(today);
    }
    @JsonIgnore
    public void checkAndReset(int today){
    for (int i = today+1; i <monthStats.length ; i++) {
        monthStats[i]=0;
    }
}
    @JsonIgnore
    public int getMonthlyStat(){

        int total=0;
        for (int i:monthStats
                 ) {
            System.out.println(i);
                total+=i;
         }
        return total;

        }

    public void setMonthStats(int[] monthStats) {
        this.monthStats = monthStats;
    }
    @JsonIgnore
    public int getTodayStat(){
        return  monthStats[LocalDate.now().getDayOfMonth()-1];
    }
    public int[] getMonthStats() {
        return monthStats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
