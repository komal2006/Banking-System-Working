package madt.fp.banksystem;

public class Client { // model class
    private long AccountNo;
    private String AccountType;
    private String Fname,Lname;
    private int HouseNo;
    private String StreetName;
    private String CityName;
    private String ProvinceName;
    private String Postal;
    private String NomineeFName,NomineeLName;
    private String Gender;
    private String Country;
    private long MobileNo;
    private String DOB;
    private double Amount;
    private String CustomerID;

    public long getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(long accountNo) {
        AccountNo = accountNo;

    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }


    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }


    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }


    public int getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(int houseNo) {
        HouseNo = houseNo;
    }


    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }


    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }


    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }


    public String getPostal() {
        return Postal;
    }

    public void setPostal(String postal) {
        Postal = postal;
    }


    public String getNomineeFName() {
        return NomineeFName;
    }

    public void setNomineeFName(String nomineeFName) {
        NomineeFName = nomineeFName;
    }


    public String getNomineeLName() {
        return NomineeLName;
    }

    public void setNomineeLName(String nomineeLName) {
        NomineeLName = nomineeLName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }


    public long getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(long mobileNo) {
        MobileNo = mobileNo;
    }


    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }


    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }


    public String getCustomerID() {
        return CustomerID;
    }
    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }


    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

}
