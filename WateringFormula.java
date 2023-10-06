package Sprint1.Inlämningsuppgift.v2;

public interface WateringFormula { //Interface
    double wateringFormula(); //Alla har olika formler för att räkna ut vätskan, men alla behöver ha en formel.

    enum WateringTypeEnum { //Enum, inkapsling
        Cactus("mineral water"), Carnivorousplant("protein-mixture"), Palmtree("tap water");

        WateringTypeEnum(final String wateringType) {
            this.wateringType = wateringType;
        }

        private final String wateringType;

        public String getWateringType() {
            return wateringType;
        }
    }
    enum PlantHeightPrefixEnum { //Enum, inkapsling
        Cactus("cm"), Carnivorousplant("m"), Palmtree("m");

        PlantHeightPrefixEnum(final String plantHeightPrefix) {
            this.plantHeightPrefix = plantHeightPrefix;
        }

        private final String plantHeightPrefix;

        public String getplantHeightPrefix() {
            return plantHeightPrefix;
        }
    }
    enum HydrationPrefixEnum { //Enum, inkapsling
        Cactus("cl"), Carnivorousplant("l"), Palmtree("l");

        HydrationPrefixEnum(final String hydrationPrefix) {
            this.hydrationPrefix = hydrationPrefix;
        }

        private final String hydrationPrefix;

        public String getHydrationPrefix() {
            return hydrationPrefix;
        }
    }
    enum HydrationBaselineEnum { //Enum, inkapsling
        Cactus(2), Carnivorousplant(0.1);

        HydrationBaselineEnum(final double hydrationBaseline) {
            this.hydrationBaseline = hydrationBaseline;
        }

        private final double hydrationBaseline;

        public double getHydrationBaseline() {
            return hydrationBaseline;
        }
    }
    enum HydrationFactorEnum { //Enum, inkapsling
        Carnivorousplant(0.2), Palmtree(0.5);

        HydrationFactorEnum(final double hydrationFactor) {
            this.hydrationFactor = hydrationFactor;
        }

        private final double hydrationFactor;

        public double getHydrationFactor() {
            return hydrationFactor;
        }
    }

}
