import 'package:Amfibi_App/features/helper/helper_function.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/Settings/setting_Screen.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/home/home_screen.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/home/main_screen.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/login/login_page.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/Message/message_page.dart';
import 'package:Amfibi_App/features/shared/constants.dart';
import 'package:Amfibi_App/features/user_auth/presentation/pages/transaksi_history/transaksi_history_page.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  if (kIsWeb) {
    await Firebase.initializeApp(
        options: FirebaseOptions(
            apiKey: Constants.apiKey,
            appId: Constants.appId,
            messagingSenderId: Constants.messagingSenderId,
            projectId: Constants.projectId));
  } else {
    await Firebase.initializeApp();
  }

  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool _isSignedIn = false;

  @override
  void initState() {
    super.initState();
    getUserLoggedInStatus();
  }

  getUserLoggedInStatus() async {
    await HelperFunctions.getUserLoggedInStatus().then((value) {
      if (value != null) {
        setState(() {
          _isSignedIn = value;
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          primaryColor: Constants().primaryColor,
          scaffoldBackgroundColor: Colors.white),
      debugShowCheckedModeBanner: false,
      home: _isSignedIn ? const MainScreen() : const HomeScreen(),
    );
  }
}