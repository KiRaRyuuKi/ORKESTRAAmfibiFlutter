class CarsListData {
  CarsListData({
    this.brand = '',
    this.name = '',
    this.year = '',
    this.location = '',
    this.status = '',
    this.price = '',
    this.rating = 0.0,
    this.imagePath = '',
  });

  String imagePath;
  String brand;
  String name;
  String year;
  String location;
  String status;
  String price;
  double rating;

  static List<CarsListData> tabIconsList = <CarsListData>[
    CarsListData(
      brand: 'Toyota',
      name: 'Avanza',
      year: '2021',
      location: 'Lorem Ipsum is simply text.',
      status: 'Dijual',
      price: '30000',
      rating: 5.0,
      imagePath: 'assets/car.jpg',
    ),
    CarsListData(
      brand: 'Toyota',
      name: 'Avanza',
      year: '2021',
      location: 'Lorem Ipsum is simply dummy text.',
      status: 'Dijual',
      price: '30000',
      rating: 4.0,
      imagePath: 'assets/car.jpg',
    ),
    CarsListData(
      brand: 'Toyota',
      name: 'Avanza',
      year: '2021',
      location: 'Lorem Ipsum is simply dummy text of the printing.',
      status: 'Dijual',
      price: '30000',
      rating: 3.0,
      imagePath: 'assets/car.jpg',
    ),
  ];
}
