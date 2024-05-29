class Category {
  final String title;
  final String image;

  Category({
    required this.title,
    required this.image,
  });
}

final List<Category> categories = [
  Category(title: "Sport", image: "assets/images/mobil-1.jpg"),
  Category(title: "Mobil Keluarga", image: "assets/images/mobil-2.jpg"),
  Category(title: "Mobil Terbuka", image: "assets/images/mobil-3.jpg"),
  Category(title: "S", image: "assets/images/mobil-4.jpg"),
  Category(title: "Watch", image: "assets/images/mobil-1.jpg"),
];
