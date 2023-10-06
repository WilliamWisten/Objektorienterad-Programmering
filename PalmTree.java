package Sprint1.Inlämningsuppgift.v2;

import java.util.ArrayList;

public class PalmTree extends Plant { //Arv, interface

    public PalmTree(ArrayList<Plant> list, String type) {
        super(list, type, PlantHeightPrefixEnum.Palmtree.getplantHeightPrefix());
        setWateringType(WateringTypeEnum.Palmtree.getWateringType());
        setPlantHeightPrefix(PlantHeightPrefixEnum.Palmtree.getplantHeightPrefix());
        setWateringFactor(HydrationFactorEnum.Palmtree.getHydrationFactor());
        setWateringPrefix(HydrationPrefixEnum.Palmtree.getHydrationPrefix());
        setWateringAmount(wateringFormula());
    }

    @Override
    public double wateringFormula() {
        return (getWateringFactor() * getPlantHeight());
    } //Interface
}
