package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Enterprise;

import java.util.ArrayList;
import java.util.List;

public class SerializationServiceTest {

    private final String FILE_NAME = "EnterpriseTest.ser";

    @Test
    public void testSerializeAndDeserializeEnterpriseData() {

        List<Enterprise> enterpriseList = new ArrayList<>();
        enterpriseList.add(new Enterprise("Enterprise 1", "logo1.png", null));
        enterpriseList.add(new Enterprise("Enterprise 2", "logo2.png", null));

        ObservableList<Enterprise> enterprises = FXCollections.observableList(enterpriseList);

        // Serialize test data
        SerializationService.serializeEnterpriseData(enterprises, FILE_NAME);

        // Deserialize test data
        ObservableList<Enterprise> deserializedEnterprises = SerializationService.deSerializeEnterpriseDatao(FILE_NAME);

        // Check if deserialized data matches original data
        Assertions.assertEquals(enterprises.size(), deserializedEnterprises.size(),
                "Number of enterprises does not match.");

        for (int i = 0; i < enterprises.size(); i++) {
            Enterprise original = enterprises.get(i);
            Enterprise deserialized = deserializedEnterprises.get(i);

            Assertions.assertEquals(original.getId(), deserialized.getId(), "ID does not match.");
            Assertions.assertEquals(original.getName(), deserialized.getName(), "Name does not match.");
        }
    }
}
