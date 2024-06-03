import 'package:flutter/material.dart';

class Cars {
  final String carName;
  final String description;
  final String image;
  final double price;
  final List<Color> colors;
  final String category;
  final double rate;

  Cars({
    required this.carName,
    required this.description,
    required this.image,
    required this.price,
    required this.colors,
    required this.category,
    required this.rate,
  });
}

final List<Cars> carsList = [
  Cars(
    carName: "Tesla Model S",
    description:
        "The Tesla Model S is an all-electric five-door liftback sedan produced by Tesla, Inc., and was introduced on June 22, 2012.",
    image: "assets/images/tesla-model_s.jpg",
    price: 79999,
    colors: [
      Colors.black,
      Colors.white,
      Colors.red,
    ],
    category: "Electric",
    rate: 4.9,
  ),
  Cars(
    carName: "BMW i8",
    description:
        "The BMW i8 is a plug-in hybrid sports car developed by BMW. The i8 is part of BMW's electric fleet and was introduced in 2014.",
    image: "assets/images/mobil-2.jpg",
    price: 147500,
    colors: [
      Colors.blue,
      Colors.black,
      Colors.white,
    ],
    category: "Hybrid",
    rate: 4.7,
  ),
  Cars(
    carName: "Audi R8",
    description:
        "The Audi R8 is a mid-engine, 2-seater sports car, which uses Audi's trademark quattro permanent all-wheel drive system.",
    image: "assets/images/mobil-4.jpg",
    price: 169900,
    colors: [
      Colors.red,
      Colors.blue,
      Colors.greenAccent,
    ],
    category: "Sports",
    rate: 4.8,
  ),
];
