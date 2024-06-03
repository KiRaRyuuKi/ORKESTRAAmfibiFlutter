import 'package:Amfibi_App/features/user_auth/presentation/pages/model/cars.dart';

class CartItem {
  int quantity;
  Cars product;

  CartItem({
    required this.quantity,
    required this.product,
  });
}

List<CartItem> cartItems = [
  CartItem(quantity: 2, product: carsList[0]),
  CartItem(quantity: 1, product: carsList[1]),
];
