package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Date;

public class userData {
String name="unknown player";
String email="dont@sk.me";
String address="empty";
String phone ="N/D";
int[] monthStats=new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


    public userData userData(String name, String email, String address, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        return this;
    }

    public int[] getMonthStats() {
        return monthStats;
    }
public void updateDayStats(int amount){
        monthStats[LocalDate.now().getDayOfMonth()-1]=amount; }
    public void setMonthStats(int[] monthStats) {
        this.monthStats = monthStats;
    }
    @JsonIgnore
    public int getTodayStat(){
        return  monthStats[LocalDate.now().getDayOfMonth()-1];
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
