package Sprint1.Inlämningsuppgift.v2;

import javax.swing.*;

public class Menu {

    private final String mainMenu = ("""
                [1] Add a new plant
                [2] Change the height of a plant
                [3] Read the watering guide of a plant
                [4] Read the watering guide of all plants in the register
                [5] Remove a plant from the register
                [6] Exit program
                Type the number matching your choice:"""); //Inkapsling
    private final String plantMenu = ("""
                [1] Cactus
                [2] Carnivorous plant
                [3] Palm tree
                Type the number matching your choice:""");//Inkapsling

    public int MenuChoice(int numberOfMenuChoices, String menu) {
        int menuChoice;
        while (true) {
            try {
                menuChoice = Integer.parseInt(JOptionPane.showInputDialog(menu));
                if (numberOfMenuChoices >= menuChoice && menuChoice >= 1) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Input has to be an integer between 1 and " + numberOfMenuChoices);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input has to be an integer between 1 and " + numberOfMenuChoices);
            }
        }
        return menuChoice;
    }

    public String GetMainMenu() {
        return mainMenu;
    }//Inkapsling

    public String GetPlantMenu() {
        return plantMenu;
    }//Inkapsling
}