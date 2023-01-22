package model.machine;

import model.Enterprise;

public class Laser extends ProcessCell {

    int wavelength;

    public Laser(int id) {
        super(id);
    }

    public Laser(int id, String name, Enterprise manufacturer, Enterprise customer, String type, int wavelength) {
        super(id, name, manufacturer, customer, type);
        this.wavelength = wavelength;
        //TODO Auto-generated constructor stub
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    public int getWavelength() {
        return wavelength;
    }

    public void setWavelength(int wavelength) {
        this.wavelength = wavelength;
    }

}
