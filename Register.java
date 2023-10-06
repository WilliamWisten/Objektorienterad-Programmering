package Sprint1.Inlämningsuppgift.v2;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Register implements Serializable { //Använder ett färdigt interface för att läsa objekt till fil
    private final ArrayList<Plant> plantArrayList = new ArrayList<>(); //Inkapsling

    public String filePath() {
        return "ArrayListFile.tmp";
    } //Inkapsling

    public ArrayList<Plant> getPlantArrayList() {
        return plantArrayList;
    }

    public Plant getIndexPlant(int i) { //Returnerar en planta från listan
        Plant plant = null;
        try {
            plant = plantArrayList.get(i);
        } catch (IndexOutOfBoundsException ignored) {
        }
        return plant;
    }

    public int choicePlantArrayList(String s, StringBuilder listNames) {
        int index = -1;
        if (plantArrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no plants in the register.");
        } else {
            String nameInput = JOptionPane.showInputDialog(listNames.toString() + "Which plant do you want to " + s + " Type the name of the plant.");
            for (int i = 0; i < plantArrayList.size(); i++) {
                String nameCheck = plantArrayList.get(i).getPlantName();
                if (nameCheck.equalsIgnoreCase(nameInput)) {
                    index = i;
                }
            }
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "There are no plants with the name " + nameInput + " in the register.");
            }
        }
        return index;
    }

    public StringBuilder plantListNames() {
        StringBuilder listNames = new StringBuilder();
        for (Plant plant : plantArrayList) {
            listNames.append(plant.getPlantName()).append('\n');
        }
        return listNames;
    }

    public void addPlantArrayList(Plant plant) {
        plantArrayList.add(plant);
    }

    public void deletePlantArrayList(int i) {
        try {
            String s = plantArrayList.remove(i).getPlantName();
            JOptionPane.showMessageDialog(null, s + " was removed from the register.");
            saveFile();
        } catch (IndexOutOfBoundsException | InputMismatchException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void wateringGuide(int i) {
        try {
            JOptionPane.showMessageDialog(null, plantArrayList.get(i).getPlantName() + " is " + plantArrayList.get(i).getPlantHeight() + " " +
                    plantArrayList.get(i).getPlantHeightPrefix() + " tall. Water with: " + plantArrayList.get(i).getWateringAmount() + " " +
                    plantArrayList.get(i).getWateringPrefix() + " " + plantArrayList.get(i).getWateringType());
        } catch (IndexOutOfBoundsException ignored) {
        }

    }

    public void changeHeight(int i) {
        try {
            double input = Double.parseDouble(JOptionPane.showInputDialog(plantArrayList.get(i).getPlantName() +
                    " is listed as " + plantArrayList.get(i).getPlantHeight() + " " +
                    plantArrayList.get(i).getPlantHeightPrefix() + ".\nType " + plantArrayList.get(i).getPlantName() + "'s new height:"));
            plantArrayList.get(i).setPlantHeight(input);
            JOptionPane.showMessageDialog(null, plantArrayList.get(i).getPlantName() + " is now listed as " +
                    plantArrayList.get(i).getPlantHeight() + " " + plantArrayList.get(i).getPlantHeightPrefix() + ".");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input has to be a number.");
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    public void allWateringGuides() {
        if (plantArrayList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no registered plants");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Plant plant : plantArrayList) {
                stringBuilder.append(plant.getPlantName()).append(" is ").append(plant.getPlantHeight()).append(" ")
                        .append(plant.getPlantHeightPrefix()).append(" tall. Water with: ").append(plant.getWateringAmount())
                        .append(" ").append(plant.getWateringPrefix()).append(" ").append(plant.getWateringType()).append('\n');
            }
            JOptionPane.showMessageDialog(null, stringBuilder.toString());
        }
    }

    public void importFile() throws ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            plantArrayList.addAll((ArrayList<Plant>) objectInputStream.readObject());
            objectInputStream.close();
        } catch (IOException ignored) {
        }
    }

    public void saveFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(plantArrayList);
        objectOutputStream.close();
    }
}
