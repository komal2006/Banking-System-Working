package madt.fp.banksystem;

import java.util.ArrayList;

public class Records { // Holder class

    private ArrayList<Client> data = null;

    public ArrayList<Client> getData() {
        return data;
    }

    public void setData(ArrayList<Client> data) {
        this.data = data;
    }

}