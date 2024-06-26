import 'package:flutter/material.dart';
import 'package:ionicons/ionicons.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/model/categories..dart'; // Fixed import
import 'package:Amfibi_App/features/shared/constants.dart';
import 'package:Amfibi_App/features/user_auth/presentation/widgets/categories.dart';
import 'package:Amfibi_App/features/user_auth/presentation/widgets/home_slider.dart';
import 'package:Amfibi_App/features/user_auth/presentation/widgets/homeappbar.dart';
import 'package:Amfibi_App/features/user_auth/presentation/widgets/carscard.dart';
import 'package:Amfibi_App/features/user_auth/presentation/widgets/search_field.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/model/cars.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/cars_product/cars_screen.dart'; // Import CarsScreen

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int currentSlide = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: kscaffoldColor,
      body: SafeArea(
        child: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.all(10),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const HomeAppBar(),
                const SizedBox(height: 20),
                const SearchField(),
                const SizedBox(height: 20),
                HomeSlider(
                  onChange: (value) {
                    setState(() {
                      currentSlide = value;
                    });
                  },
                  currentSlide: currentSlide,
                ),
                const SizedBox(height: 20),
                const Categories(),
                const SizedBox(height: 25),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    const Text(
                      "Special For You",
                      style: TextStyle(
                        fontSize: 24,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    TextButton(
                      onPressed: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => CarsScreen(),
                          ),
                        );
                      },
                      child: const Text("See all"),
                    ),
                  ],
                ),
                const SizedBox(height: 10),
               GridView.builder(
                  physics: const NeverScrollableScrollPhysics(),
                  shrinkWrap: true,
                  gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 2,
                    crossAxisSpacing: 20,
                    mainAxisSpacing: 20,
                  ),
                  itemCount: carsList.length,
                  itemBuilder: (context, index) {
                    final car =
                        carsList[index]; // Get the car object from carsList
                    return CarsCard(
                        product:car); // Pass the car object as the product argument
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}