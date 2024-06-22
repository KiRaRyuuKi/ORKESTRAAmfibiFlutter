class CategorysListData {
  CategorysListData({
    this.imagePath = '',
    this.titleTxt = '',
    this.startColor = '',
    this.endColor = '',
  });

  String imagePath;
  String titleTxt;
  String startColor;
  String endColor;

  static List<CategorysListData> tabIconsList = <CategorysListData>[
    CategorysListData(
      imagePath: 'assets/fitness_app/breakfast.png',
      titleTxt: 'Breakfast',
      startColor: '#FA7D82',
      endColor: '#FFB295',
    ),
    CategorysListData(
      imagePath: 'assets/fitness_app/lunch.png',
      titleTxt: 'Lunch',
      startColor: '#738AE6',
      endColor: '#5C5EDD',
    ),
    CategorysListData(
      imagePath: 'assets/fitness_app/snack.png',
      titleTxt: 'Snack',
      startColor: '#FE95B6',
      endColor: '#FF5287',
    ),
    CategorysListData(
      imagePath: 'assets/fitness_app/dinner.png',
      titleTxt: 'Dinner',
      startColor: '#6F72CA',
      endColor: '#1E1466',
    ),
  ];
}
