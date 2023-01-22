package model.machine;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.IntegerProperty;
import model.Enterprise;

public class Press2 extends ProcessCell2 {


    /**
     * @param id
     */
    public Press2(int id) {
        super(id);
    }

    public Press2(int id, String name, String type) {
        super(id, name, type);
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public IntegerProperty getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }

    @Override
    public void setId(IntegerProperty id) {
        // TODO Auto-generated method stub
        super.setId(id);
    }

    

}
