package Sprint1.Inlämningsuppgift.v2;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Plant implements Serializable, WateringFormula{ //Använder ett färdigt interface för att läsa objekt till fil
    private String wateringType; //Inkapsling
    private double wateringFactor;
    private double wateringBaseline;
    private double plantHeight;
    private String plantHeightPrefix;
    private String wateringPrefix;
    private double wateringAmount;
    private String plantName;

    Plant(ArrayList<Plant> list, String type, String prefix) {
        while (true) {
            String plantName;
            while (true) {
                plantName = JOptionPane.showInputDialog("What is the name of the " + type + "?");
                int nameCheck = 0;
                for (Plant plant : list) {
                    if (plantName.equals(plant.getPlantName())) {
                        nameCheck++;
                    }
                }
                if (nameCheck == 0) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "There is already a plant named " + plantName + ". Choose another name.");
                }
            }
            setPlantName(plantName);
            if (getPlantName().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The name can not be empty.");
            } else {
                break;
            }
        }
        while (true) {
            try {
                setPlantHeight(Double.parseDouble(JOptionPane.showInputDialog("How tall is the " + type + "? Answer in " + prefix + ".")));
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input has to be an integer or a double.");
            }
        }
    }


    public void setWateringType(String s) {
        this.wateringType = s;
    }//Inkapsling

    public String getWateringType() {
        return wateringType;
    }

    public void setWateringFactor(double d) {
        this.wateringFactor = d;
    }

    public double getWateringFactor() {
        return wateringFactor;
    }

    public void setPlantHeight(double d) {
        this.plantHeight = d;
    }

    public double getPlantHeight() {
        return plantHeight;
    }

    public void setPlantHeightPrefix(String s) {
        this.plantHeightPrefix = s;
    }

    public String getPlantHeightPrefix() {
        return plantHeightPrefix;
    }

    public void setWateringPrefix(String s) {
        this.wateringPrefix = s;
    }

    public String getWateringPrefix() {
        return wateringPrefix;
    }

    public void setWateringAmount(double d) {
        this.wateringAmount = Math.round(d * 100.0) / 100.0;
    }

    public double getWateringAmount() {
        return wateringAmount;
    }

    public void setWateringBaseline(double d) {
        this.wateringBaseline = d;
    }

    public double getWateringBaseline() {
        return wateringBaseline;
    }

    public void setPlantName(String s) {
        this.plantName = s;
    }

    public String getPlantName() {
        return plantName;
    }
}
