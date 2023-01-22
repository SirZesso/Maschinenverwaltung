package model.machine;

import model.Enterprise;

public class Press extends ProcessCell {

    

    private int newton;

    public Press(int id) {
        super(id);
    }

    public Press(int id, String name, Enterprise manufacturer, Enterprise customer, String type, int newton) {
        super(id, name, manufacturer, customer, type);
        this.newton = newton;
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Press []";
    }

    @Override
    public Enterprise getCustomer() {
        // TODO Auto-generated method stub
        return super.getCustomer();
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }

    @Override
    public Enterprise getManufacturer() {
        // TODO Auto-generated method stub
        return super.getManufacturer();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return super.getType();
    }

    @Override
    public void setCustomer(Enterprise customer) {
        // TODO Auto-generated method stub
        super.setCustomer(customer);
    }

    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub
        super.setId(id);
    }

    @Override
    public void setManufacturer(Enterprise manufacturer) {
        // TODO Auto-generated method stub
        super.setManufacturer(manufacturer);
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name);
    }

    @Override
    public void setType(String type) {
        // TODO Auto-generated method stub
        super.setType(type);
    }

    public int getNewton() {
        return newton;
    }

    public void setNewton(int newton) {
        this.newton = newton;
    }
    

}
