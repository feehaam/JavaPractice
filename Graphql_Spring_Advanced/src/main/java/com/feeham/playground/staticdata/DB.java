//package com.feeham.playground.staticdata;
//
//import com.feeham.playground.models.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Set;
//
//public class DB {
//    // Dummy Address
//    public static final Address dummyAddress = new Address("Street", "City", "State",
//            "PostCode", "Country");
//
//    // Dummy tourists
//    public static final Tourist tourist1 = new Tourist(1,
//            "Ibn", "Batutta", dummyAddress, dummyAddress);
//    public static final Tourist tourist2 = new Tourist(2,
//            "Leo", "Vinci", dummyAddress, dummyAddress);
//    public static final Tourist tourist3 = new Tourist(3,
//            "Nadir", "On go", dummyAddress, dummyAddress);
//    public static final Tourist tourist4 = new Tourist(4,
//            "MD", "Feeham", dummyAddress, dummyAddress);
//    public static final Tourist tourist5 = new Tourist(5,
//            "Mubarak", "Shuvo", dummyAddress, dummyAddress);
//
//    // Dummy tourists list
//    public static List<Tourist> tourists = List.of(tourist1, tourist2, tourist3, tourist4, tourist5);
//
//    // Dummy geolocation & photos
//    public static final GeoLocation geolocation = new GeoLocation(29.00583, 56.343433);
//    public static final Set<String> photos = Set.of(
//            "http://dummy.com/photo1.jpg",
//            "http://dummy.com/photo2.jpg"
//    );
//
//    // Dummy ratings
//    public static final Rating rating1 = new Rating(1, 9, "Good place",
//            tourist1, LocalDateTime.now(), LocalDateTime.now());
//    public static final Rating rating2 = new Rating(2, 5, "Amazing place",
//            tourist3, LocalDateTime.now(), LocalDateTime.now());
//    public static final Rating rating3 = new Rating(3, 8, "Beautiful place",
//            tourist5, LocalDateTime.now(), LocalDateTime.now());
//    public static final Set<Rating> ratings = Set.of(rating1, rating2, rating3);
//    public static final Facilities facilities = new Facilities(1, 3000.0,
//            false, 0.0, "N/A");
//
//    // Dummy accommodations
//    public static final Accommodation accommodation1 = new Accommodation(1,
//            "Paradise Resort", "Hotel", dummyAddress,
//            "The best resort in the area. Feel the paradise.",
//            facilities, geolocation, photos, 7.9, 42, ratings);
//    public static final Accommodation accommodation2 = new Accommodation(1,
//            "Hotel Andromeda", "Hotel", dummyAddress,
//            "The best resort in the area. Feel the paradise.",
//            facilities, geolocation, photos, 7.9, 42, ratings);
//    public static List<Accommodation> accommodations = List.of(accommodation1, accommodation2);
//
//    // Dummy Tour spot
//    public static final TourSpot tourSpot1 = new TourSpot(1, "Sajek Valley",
//            "Touch the clouds with your bare hands", photos, dummyAddress, geolocation,
//            Set.of(accommodation1, accommodation2),
//            5.6, 230, ratings);
//    public static final TourSpot tourSpot2 = new TourSpot(1, "Sajek Valley",
//            "Touch the clouds with your bare hands", photos, dummyAddress, geolocation,
//            Set.of(accommodation1, accommodation2),
//            8.9, 230, ratings);
//    public static final TourSpot tourSpot3 = new TourSpot(1, "Sajek Valley",
//            "Touch the clouds with your bare hands", photos, dummyAddress, geolocation,
//            Set.of(accommodation1, accommodation2),
//            7.7, 230, ratings);
//    public static final List<TourSpot> tourSpots = List.of(tourSpot1, tourSpot2, tourSpot3);
//}