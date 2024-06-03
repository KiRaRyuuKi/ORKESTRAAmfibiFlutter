import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:iconsax_flutter/iconsax_flutter.dart';
import 'package:ionicons/ionicons.dart';
import 'package:Amfibi_App/features/shared/constants.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/home/home_screen.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/cars_product/cars_screen.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/transaksi_history/transaksi_history_page.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/Settings/setting_Screen.dart';
import 'package:Amfibi_App/features/helper/helper_function.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/model/cars.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int currentTab = 0;
  String userName = "";
  String email = "";
  late Cars myCar;

  List<Widget> screens = [];

  @override
  void initState() {
    super.initState();
    myCar = carsList[0]; // Ambil salah satu mobil dari daftar
    gettingUserData();
  }

  Future<void> gettingUserData() async {
    await HelperFunctions.getUserEmailFromSF().then((value) {
      setState(() {
        email = value!;
        updateScreens();
      });
    });
    await HelperFunctions.getUserNameFromSF().then((val) {
      setState(() {
        userName = val!;
        updateScreens();
      });
    });
  }

  void updateScreens() {
    setState(() {
      screens = [
        HomeScreen(),
        CarsScreen(), // Pass the Cars object here
        TransactionHistoryScreen(),
        SettingsScreen(email: email, userName: userName),
      ];
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          setState(() {
            currentTab = 0;
          });
        },
        shape: const CircleBorder(),
        backgroundColor: kprimaryColor,
        child: const Icon(
          Iconsax.home,
          color: Colors.white,
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      bottomNavigationBar: BottomAppBar(
        elevation: 0,
        height: 70,
        color: Colors.white,
        shape: const CircularNotchedRectangle(),
        notchMargin: 5,
        clipBehavior: Clip.antiAliasWithSaveLayer,
        child: Row(
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            IconButton(
              onPressed: () => setState(() {
                currentTab = 0;
              }),
              icon: Icon(
                Iconsax.home,
                color: currentTab == 0 ? kprimaryColor : Colors.grey.shade400,
              ),
            ),
            IconButton(
              onPressed: () => setState(() {
                currentTab = 1;
              }),
              icon: Icon(
                Ionicons.grid_outline,
                color: currentTab == 1 ? kprimaryColor : Colors.grey.shade400,
              ),
            ),
            IconButton(
              onPressed: () => setState(() {
                currentTab = 2;
              }),
              icon: Icon(
                Ionicons.heart_outline,
                color: currentTab == 2 ? kprimaryColor : Colors.grey.shade400,
              ),
            ),
            IconButton(
              onPressed: () => setState(() {
                currentTab = 3;
              }),
              icon: Icon(
                Ionicons.person_outline,
                color: currentTab == 3 ? kprimaryColor : Colors.grey.shade400,
              ),
            ),
          ],
        ),
      ),
      body: screens.isEmpty ? Container() : screens[currentTab],
    );
  }
}
