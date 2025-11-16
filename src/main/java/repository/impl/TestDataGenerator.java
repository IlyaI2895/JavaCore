package repository.impl;

import model.Manufacture;
import model.Souvenir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {
    public static List<Souvenir> generateRandomSouvenir(int count) {
        Random rand = new Random();
        String[] name = {"Keychain", "Mug", "T-shirt", "Magnet", "Postcard", "Cap", "ToteBag",
                "Sticker", "Notebook", "Pen", "Pencil", "Calendar", "SnowGlobe", "ShotGlass",
                "Badge", "Coin", "PlushToy", "Bracelet", "Necklace", "Candle", "BottleOpener",
                "Coaster", "MousePad", "PhoneCase", "Puzzle", "Umbrella", "WallClock", "WaterBottle",
                "TravelMug", "PhotoFrame", "FridgeMagnet", "BeachTowel", "Socks", "Scarf", "Beanie",
                "Hoodie", "Bookmark", "MiniStatue", "MapPoster", "Keyring", "Pin", "ArtPrint", "Patch",
                "Lanyard", "LuggageTag", "MagnetSet", "DeskCalendar", "CeramicPlate", "FridgeSticker",
                "ShotCup", "BeerMug", "WineGlass", "TotePouch", "WallPoster", "PencilCase", "CandleHolder",
                "TravelBag", "Ornament", "MiniClock", "CrystalBall", "DeskFlag", "MagnetBottleOpener",
                "TravelNotebook", "CityMap", "PostcardSet", "KeyBottleOpener", "MiniEasel", "ArtMug", "WoodenBox",
                "MagnetFrame", "CupCoaster", "Towel", "MagnetPhoto", "CityPin", "CharmBracelet", "CanvasBag",
                "FoldingFan", "Bell", "Lantern", "WoodenSpoon", "LeatherKeychain", "TravelJournal", "TeaCup",
                "WallArt", "CarSticker", "MagnetClock", "StreetSign", "CollectibleCard", "GlassFigurine",
                "MiniPuzzle", "MetalBadge", "SouvenirPlate", "PlushBear", "CrystalKeychain", "WoodenMagnet",
                "HandmadeSoap", "CityCoin", "GreetingCard", "ArtCalendar", "GiftBox"
        };

        String[] manufacture = {"John Carter", "Emily Johnson", "Michael Brown", "Sarah Miller", "David Wilson",
                "Laura Davis", "James Anderson", "Olivia Taylor", "Robert Thomas", "Sophia Moore", "Daniel Jackson",
                "Emma White", "William Harris", "Grace Martin", "Joseph Lewis", "Isabella Clark", "Benjamin Walker",
                "Ava Young", "Charles Hall", "Mia Allen", "Henry King", "Lily Scott", "Matthew Green", "Ella Adams",
                "Andrew Baker", "Chloe Nelson", "Christopher Reed",
                "Natalie Turner", "Jonathan Phillips", "Hannah Campbell"
        };

        String[] country = {"United States", "Canada", "United Kingdom", "Australia",
                "Germany", "France", "Italy", "Spain", "Japan", "China", "Brazil",
                "Mexico", "India", "South Korea", "Sweden", "Norway", "Netherlands",
                "Switzerland", "Argentina", "New Zealand"
        };

        Manufacture[] manufactures = Arrays.stream(manufacture)
                .map(s -> Manufacture.builder()
                        .firstName(s.split(" ")[0])
                        .surname(s.split(" ")[1])
                        .country(country[rand.nextInt(country.length)])
                        .age(rand.nextInt(61 - 18 + 1) + 18)
                        .build())
                .toArray(Manufacture[]::new);

        Souvenir[] souvenir = new Souvenir[count];
        for (int i = 0; i < count; i++) {
            String names = name[rand.nextInt(name.length)] + " " + (rand.nextInt(5) + 1);
            Manufacture manufacture1 = manufactures[rand.nextInt(manufactures.length)];
            int price = rand.nextInt((173) * 1000);
            int date = 1900 + rand.nextInt(125);

                    souvenir[i] = Souvenir.builder()
                            .name(names)
                            .manufacturer(manufacture1)
                            .price(price)
                            .date(date)
                    .build();

        } return new ArrayList<>(Arrays.asList(souvenir));

    }
}

