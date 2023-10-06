package Sprint1.Inlämningsuppgift.v2;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Register register = new Register();
        register.importFile();
        Menu menu = new Menu();
        boolean menuLoop = true;
        while (menuLoop) {
            switch (menu.MenuChoice(6, menu.GetMainMenu())) {
                case 1 -> {
                    switch (menu.MenuChoice(3, menu.GetPlantMenu())) {
                        case 1 -> {
                            Plant cactus = new Cactus(register.getPlantArrayList(), "cactus"); //Polymorfism
                            register.addPlantArrayList(cactus);
                            register.saveFile();
                        }
                        case 2 -> {
                            Plant carnivorousPlant = new CarnivorousPlant(register.getPlantArrayList(), "carnivorous plant"); //Polymorfism
                            register.addPlantArrayList(carnivorousPlant);
                            register.saveFile();
                        }
                        case 3 -> {
                            Plant palmTree = new PalmTree(register.getPlantArrayList(), "palm tree"); //Polymorfism
                            register.addPlantArrayList(palmTree);
                            register.saveFile();
                        }
                    }
                }
                case 2 -> {
                    register.plantListNames();
                    int i = register.choicePlantArrayList("change the height of?", register.plantListNames());
                    try {
                        register.changeHeight(i);
                        register.getIndexPlant(i).setWateringAmount(register.getIndexPlant(i).wateringFormula());
                        register.getIndexPlant(i).setWateringAmount((register.getIndexPlant(i).getWateringFactor() *
                                register.getIndexPlant(i).getPlantHeight()) + register.getIndexPlant(i).getWateringBaseline());
                        register.saveFile();
                    } catch (NullPointerException ignored) {
                    }
                }
                case 3 -> {
                    register.plantListNames();
                    register.wateringGuide(register.choicePlantArrayList("read the watering guide for?", register.plantListNames()));
                }
                case 4 -> register.allWateringGuides();
                case 5 -> {
                    register.plantListNames();
                    register.deletePlantArrayList(register.choicePlantArrayList("remove from the register?", register.plantListNames()));
                    register.saveFile();
                }
                case 6 -> {
                    System.out.println("Exiting program");
                    menuLoop = false;
                    register.saveFile();
                }
            }
        }
    }
}
