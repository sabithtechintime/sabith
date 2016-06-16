package com.example.user.facebook;

/**
 * Created by user on 6/9/2016.
 */
public class Bean {
    private String First_name;
    private String Last_name;
    private String Address;
    private String Email;
    private String Mobile;

    public Bean(String fname,String lname,String address,String email,String mobile) {
        this.First_name = fname;
        this.Last_name=lname;
        this.Address=address;
        this.Email=email;
        this.Mobile=mobile;
    }
    public String getFirst_name()
    {
        return First_name;
    }
    public void setFirst_name(String first_name)
    {
        this.First_name = first_name;
    }
    public String getLast_name() {
        return Last_name;
    }
    public void setLast_name(String last_name) {
        this.Last_name = last_name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        this.Address = address;
    }
    public String getMobile(){
        return Mobile;
    }
    public void setMobile_no(String Mobile_no){
        this.Mobile= Mobile_no;
    }
    public  String getemail(){
        return Email;
    }
    public void setemail(String Email){
        this.Email=Email;
    }

}
