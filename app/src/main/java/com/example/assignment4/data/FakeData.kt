package com.example.assignment4.data

import com.example.assignment4.data.entities.DairyEntry
import com.example.assignment4.data.entities.User
import com.example.assignment4.utils.map.Coordinate
import java.util.*

val fakeImageUrls = listOf(
    "https://picsum.photos/id/230/200/300",
    "https://picsum.photos/id/231/200/300",
    "https://picsum.photos/id/232/200/300",
    "https://picsum.photos/id/233/200/300",
    "https://picsum.photos/id/234/200/300",
    "https://picsum.photos/id/235/200/300",
    "https://picsum.photos/id/236/200/300",
    "https://picsum.photos/id/237/200/300",
)

val fakeUser = User(
    name = "Banana 123",
    email = "banana@libanana.com",
    profileImageUrl = "https://images.pexels.com/photos/1987301/pexels-photo-1987301.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
)

var fakeDairyEntry = com.example.assignment4.data.entities.DairyEntry(
    date = "Oct 1",
    location = "Stanley Park",
    imageUrls = listOf(fakeImageUrls[0], fakeImageUrls[1]),
    description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)."
)

val fakeLocations = listOf(
    Coordinate(49.3043, -123.1443),
    Coordinate(49.2488, -122.9805),
    Coordinate(49.1913, -122.8490),
)

val fakeMonthlyImageList = listOf(
    fakeImageUrls,
    fakeImageUrls,
    fakeImageUrls,
)

val fakeDairy = DairyEntry(
    title = "dairy title",
    date = "Oct 1",
    location = "Stanley Park",
    imageUrls = fakeImageUrls,
    description = "Lovely, beautiful and peaceful place. Trails in this park are very well marked, there is lots of parking and beautiful nature to take in all around you. You can find some ancient growth forest on some of the trails and it is breathtaking"
)

val fakeLocationEntry = DairyEntry(
    title = "Stanley Park",
    date = "Oct 1",
    location = "Stanley Park",
    imageUrls = fakeImageUrls,
    description = "Stanley Park is a magnificent green oasis in the midst of the urban landscape " +
            "of Vancouver.\n\nExplore the 400-hectare natural West Coast rainforest and enjoy " +
            "scenic views of water, mountains, sky, and majestic trees along Stanley Park's famous Seawall."
)

val fakeDairyList = listOf(
    fakeDairy,
    fakeDairy,
    fakeLocationEntry,
    fakeDairy,
)

val fakeDates = listOf(
    Date(2021, 10, 4),
    Date(2021, 10, 7),
    Date(2021, 10, 20),
    Date(2021, 10, 23)
)

