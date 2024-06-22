class TransactionsListData {
  TransactionsListData({
    this.brand = '',
    this.name = '',
    this.year = '',
    this.status = '',
    this.price = '',
    this.imagePath = '',
  });

  String imagePath;
  String brand;
  String name;
  String year;
  String status;
  String price;

  static List<TransactionsListData> tabIconsList = <TransactionsListData>[
    TransactionsListData(
      brand: 'Toyota',
      name: 'Avanza 12345',
      year: '2021',
      status: 'Pending',
      price: '30000',
      imagePath: 'assets/car.jpg',
    ),
    TransactionsListData(
      brand: 'Toyota',
      name: 'Avanza',
      year: '2021',
      status: 'Canceled',
      price: '30000',
      imagePath: 'assets/car.jpg',
    ),
    TransactionsListData(
      brand: 'Toyota',
      name: 'Avanza',
      year: '2021',
      status: 'Accepted',
      price: '30000',
      imagePath: 'assets/car.jpg',
    ),
    TransactionsListData(
      brand: 'Toyota',
      name: 'Avanza',
      year: '2021',
      status: 'Dijual',
      price: '30000',
      imagePath: 'assets/car.jpg',
    ),
    TransactionsListData(
      brand: 'Toyota',
      name: 'Avanza',
      year: '2021',
      status: 'Dijual',
      price: '30000',
      imagePath: 'assets/car.jpg',
    ),
  ];
}
