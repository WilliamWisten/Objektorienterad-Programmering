package Sprint1.Inlämningsuppgift.v2;

import java.util.ArrayList;

public class CarnivorousPlant extends Plant{ //Arv, interface

    public CarnivorousPlant(ArrayList<Plant> list, String type) {
        super(list, type, PlantHeightPrefixEnum.Carnivorousplant.getplantHeightPrefix());
        setWateringType(WateringTypeEnum.Carnivorousplant.getWateringType());
        setPlantHeightPrefix(PlantHeightPrefixEnum.Carnivorousplant.getplantHeightPrefix());
        setWateringFactor(HydrationFactorEnum.Carnivorousplant.getHydrationFactor());
        setWateringPrefix(HydrationPrefixEnum.Carnivorousplant.getHydrationPrefix());
        setWateringBaseline(HydrationBaselineEnum.Carnivorousplant.getHydrationBaseline());
        setWateringAmount(wateringFormula());
    }

    @Override
    public double wateringFormula() {
        return (getWateringFactor() * getPlantHeight() + getWateringBaseline());
    } //Interface
}
