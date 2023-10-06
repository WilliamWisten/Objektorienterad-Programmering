package Sprint1.Inlämningsuppgift.v2;

import java.util.ArrayList;

public class Cactus extends Plant { //Arv, interface

    public Cactus(ArrayList<Plant> list, String type) {
        super(list, type, PlantHeightPrefixEnum.Cactus.getplantHeightPrefix());
        setWateringType(WateringTypeEnum.Cactus.getWateringType());
        setPlantHeightPrefix(PlantHeightPrefixEnum.Cactus.getplantHeightPrefix());
        setWateringPrefix(HydrationPrefixEnum.Cactus.getHydrationPrefix());
        setWateringBaseline(HydrationBaselineEnum.Cactus.getHydrationBaseline());
        setWateringAmount(wateringFormula());
    }

    @Override
    public double wateringFormula() {
        return getWateringBaseline();
    } //Interface
}
